package src.V1.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import src.DatConRecs.Payload;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatFile;
import src.Files.DatHeader;
import src.Files.FileEnd;
import src.Files.NotDatFile;
import src.Files.Persist;

public class DatFileV1 extends DatFile {

    long nextFilPos = startOfRecords; // beginning filePos of next tick group

    int nextTickGroup = 0; // tickNo of next group

    private static final int maxNumElements = 50;

    public class tickGroup {
        public int numElements = 0;

        public long start[] = new long[maxNumElements];

        public int length[] = new int[maxNumElements];

        public int payloadType[] = new int[maxNumElements];

        public long tickNo = -1;

        public void reset() {
            numElements = 0;
            tickNo = -1;
        }

        public void add(long _tickNo, long _start, int _length, int _Type) {
            numElements++;
            tickNo = _tickNo;
            start[numElements - 1] = _start;
            length[numElements - 1] = _length;
            payloadType[numElements - 1] = _Type;

        }

        public void add(long _start, int _length, int _Type) {
            numElements++;
            start[numElements - 1] = _start;
            length[numElements - 1] = _length;
            payloadType[numElements - 1] = _Type;
        }
    }

    public tickGroup tickGroups[] = { new tickGroup(), new tickGroup() };

    protected int tgIndex = 1;

    protected long lastRecordTickNo = 0;

    protected boolean alternateStructure = false;

    public static DecimalFormat timeFormat = new DecimalFormat("###.000",
            new DecimalFormatSymbols(Locale.US));

    double clockRate = 600;

    int count = 0;

    public tickGroup getTickGroup() throws FileEnd, Corrupted {
        tickGroup thisTickGroup = null;
        tickGroup nextTickGroup = null;
        if (tgIndex == 1) {
            tgIndex = 0;
            thisTickGroup = tickGroups[0];
            nextTickGroup = tickGroups[1];
        } else {
            tgIndex = 1;
            thisTickGroup = tickGroups[1];
            nextTickGroup = tickGroups[0];
        }
        long thisGroupsTickNo = thisTickGroup.tickNo;
        nextTickGroup.numElements = 0; // reset the nextTickGroup to be empty

        boolean done = false;
        int length = 0;
        long nextStartOfRecord = 0;
        long thisRecordTickNo = 0;
        while (!done) {
            try {
                setPosition(startOfRecord);
                if (getByte(startOfRecord) != 0x55) { // if not positioned at
                                                          // next 0x55, then its
                                                      // corrupted
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
                }
                length = (0xFF & getByte(startOfRecord + 1));
                //byte always0 = (byte) getByte(startOfRecord + 2);
                nextStartOfRecord = startOfRecord + length; // the next 0x55 , we hope
                if (nextStartOfRecord > fileLength)
                    throw (new FileEnd());
                //short hdrChksum = (short) (0xFF & getByte(startOfRecord + 3));
                int type = getUnsignedShort(startOfRecord + 4);
                thisRecordTickNo = getUnsignedInt(startOfRecord + 6);
                int calcChksum = calc_checksum(memory, startOfRecord,
                        (short) (length - 2));
                int chksum = getUnsignedShort(startOfRecord + length - 2);
                if (calcChksum != chksum) {
                    //                    if (Persist.EXPERIMENTAL_DEV) {
                    //System.out.println("CCRC " + calcChksum + " CRC " + chksum);
                    //                    }
                    throw (new Corrupted(thisRecordTickNo, startOfRecord + 1,
                            Corrupted.Type.CRC));
                }
                numRecs++;
                if (thisRecordTickNo < 0) {
                    throw (new Corrupted(lastRecordTickNo, startOfRecord + 1));
                }
                lastRecordTickNo = thisRecordTickNo;
                if (length == 0) {
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord + 1));
                }

                // if not positioned at next 0x55, then it's corrupted
                if (getByte(nextStartOfRecord) != 0x55) {
                    throw (new Corrupted(thisGroupsTickNo, nextStartOfRecord));
                }

                // thisTickGroup doesn't yet have a tickNo
                if (thisGroupsTickNo == -1) {
                    thisGroupsTickNo = thisRecordTickNo;
                    thisTickGroup.tickNo = thisRecordTickNo;
                    _tickNo = thisRecordTickNo;
                }

                if (thisRecordTickNo > thisTickGroup.tickNo) { // start next group
                    nextTickGroup.reset();
                    nextTickGroup.add(thisRecordTickNo,
                            startOfRecord + headerLength,
                            length - headerLength - chksumLength, type);
                    done = true;
                } else if (thisRecordTickNo == thisTickGroup.tickNo) {
                    thisTickGroup.add(startOfRecord + headerLength,
                            length - headerLength - chksumLength, type);
                } else { // (tickNo < thisTickGroup.tickNo) in the last group
                             // for now, just ignore
                }

                startOfRecord = nextStartOfRecord;
            } catch (Corrupted c) {
                if (getPos() > fileLength - (int) clockRate) {
                    throw (new FileEnd());
                }
                numCorrupted++;
                if ((numRecs > 1000)
                        && ((float) numCorrupted / (float) numRecs) > 0.02) {
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
                }
                try {
                    setPosition(c.filePos);
                    byte fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (new FileEnd());
                        }
                        fiftyfive = readByte();
                    }
                } catch (Exception e) {
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
                }
                // set position right before the next 0x55
                startOfRecord = getPos() - 1;
            } catch (FileEnd f) {
                throw (new FileEnd());
            } catch (Exception e) {
                e.printStackTrace();
                results.setResultCode(AnalyzeDatResults.ResultCode.CORRUPTED);
                throw (new Corrupted(thisGroupsTickNo, startOfRecord));
            }
        }
        return thisTickGroup;
    }

    public DatFileV1(String fileName) throws IOException, NotDatFile {
        //this(new File(fileName));
        super(fileName);
    }

    public DatFileV1(File _file) throws NotDatFile, FileNotFoundException {
        super(_file);
        //file = _file;
        tickGroups[0] = new tickGroup();
        tickGroups[1] = new tickGroup();
        //results = new AnalyzeDatResults();
        //fileLength = file.length();
        //inputStream = new FileInputStream(file);
        //_channel = inputStream.getChannel();
        //        try {
        //            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //        memory.order(ByteOrder.LITTLE_ENDIAN);
        //acType = DatHeader.AcType.P3I1;
    }

    //    public DatFileV1() {
    //        acType = DatHeader.AcType.P3I1;
    //    }

    public ConvertDat createConVertDat() {
        return (new ConvertDatV1(this));
    }

    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
                if (inputStream.getChannel() != null) {
                    inputStream.getChannel().close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        memory = null;
        System.gc();
        System.runFinalization();
    }

    public void reset() throws IOException, FileEnd {
        tickGroups[0].reset();
        tickGroups[1].reset();
        tgIndex = 1;
        super.reset();
        //        numCorrupted = 0;
        //        numRecs = 0;
        //        Corrupted.reset();
        //        results = new AnalyzeDatResults();
        //        if (inputStream == null) {
        //            inputStream = new FileInputStream(file);
        //            _channel = inputStream.getChannel();
        //            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
        //            memory.order(ByteOrder.LITTLE_ENDIAN);
        //        }
        //        startOfRecord = startOfRecords;
        //        setPosition(startOfRecord);
    }

    public AnalyzeDatResults getResults() {
        return results;
    }

    public void preAnalyze() throws NotDatFile {

        findOtherStuff();
        findLowestTickNo();
        int length = 0;
        boolean done = false;
        long nextStartOfRecord = 0;
        long filePos = fileLength - 200000;
        try {
            setPosition(filePos);
            byte fiftyfive = readByte();
            while (fiftyfive != 0X55) {
                if (getPos() > fileLength - 1000) {
                    throw (new FileEnd());
                }
                fiftyfive = readByte();
            }
            filePos = getPos() - 1;
            while (!done) {
                try {
                    setPosition(filePos);
                    length = (0xFF & getByte(filePos + 1));
                    if (length == 0) {
                        throw (new Corrupted());
                    }
                    nextStartOfRecord = filePos + length;
                    if (nextStartOfRecord > fileLength) {
                        throw (new FileEnd());
                    }
                    if (getByte(nextStartOfRecord) != 0x55) {
                        throw (new Corrupted());
                    }
                    long tickNo = getUnsignedInt(filePos + 6);
                    if (lowestTickNo < 0) {
                        lowestTickNo = tickNo;
                    }
                    if (tickNo > highestTickNo) {
                        highestTickNo = tickNo;
                    }
                    filePos = nextStartOfRecord;
                } catch (Corrupted c) {
                    setPosition(getPos() + 1);
                    fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (new FileEnd());
                        }
                        fiftyfive = readByte();
                    }
                    filePos = getPos() - 1;
                }
            }
        } catch (Exception e) {
        } finally {
            super.preAnalyze();
        }
    }

    protected void findLowestTickNo() {
        int length = 0;
        boolean done = false;
        long nextStartOfRecord = 0;
        long filePos = startOfRecords;
        try {
            setPosition(filePos);
            byte fiftyfive = readByte();
            while (fiftyfive != 0X55) {
                if (getPos() > fileLength - 1000) {
                    throw (new FileEnd());
                }
                fiftyfive = readByte();
            }
            filePos = getPos() - 1;
            while (!done) {
                try {
                    setPosition(filePos);
                    length = (0xFF & getByte(filePos + 1));
                    if (length == 0) {
                        throw (new Corrupted());
                    }
                    nextStartOfRecord = filePos + length;
                    if (nextStartOfRecord > fileLength) {
                        throw (new FileEnd());
                    }
                    if (getByte(nextStartOfRecord) != 0x55) {
                        throw (new Corrupted());
                    }
                    long tickNo = getUnsignedInt(filePos + 6);
                    lowestTickNo = tickNo;
                    done = true;
                } catch (Corrupted c) {
                    setPosition(getPos() + 1);
                    fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (new FileEnd());
                        }
                        fiftyfive = readByte();
                    }
                    filePos = getPos() - 1;
                }
            }
        } catch (Exception e) {
        }
    }

    public void findOtherStuff() {
        try {
            reset();
            clearRecsInDat();
            long tickNo = 0;
            while (true) {
                if (getPos() > fileLength - 8) {
                    throw (new FileEnd());
                }
                DatFileV1.tickGroup tG = getTickGroup();
                tickNo = tG.tickNo;
                for (int tgIndex = 0; tgIndex < tG.numElements; tgIndex++) {
                    int payloadType = tG.payloadType[tgIndex];
                    long payloadStart = tG.start[tgIndex];
                    int payloadLength = tG.length[tgIndex];
                    addRecInDat(payloadType, payloadLength);

                    if (payloadType == 0x8000) {
                        Payload xorBB = new Payload(this, payloadStart,
                                payloadLength, payloadType, tickNo);
                        String payloadString = xorBB.getString();
                        if (payloadString.indexOf("M.Start") > 0
                                && firstMotorStartTick == 0) {
                            firstMotorStartTick = tickNo;
                        }
                        if (payloadString.indexOf("M. Stop") > 0) {
                            lastMotorStopTick = tickNo;
                        }
                        if (acType == DatHeader.AcType.P3I1) {
                            if (payloadString.indexOf("Board") > -1) {
                                if (payloadString.indexOf("wm320v2") > -1) {
                                    acType = DatHeader.AcType.P3AP;
                                } else if (payloadString
                                        .indexOf("wm610") > -1) {
                                    acType = DatHeader.AcType.I1;
                                } else if (payloadString
                                        .indexOf("wm321") > -1) {
                                    acType = DatHeader.AcType.P3S;
                                } else if (payloadString
                                        .indexOf("wm328") > -1) {
                                    acType = DatHeader.AcType.P3S;
                                } else if (payloadString
                                        .indexOf("tp1406") > -1) {
                                    // / don't know what this is, involves
                                    // landing gear
                                }
                            }
                        }
                    } else if (gpsLockTick == -1 && payloadType == 12) {
                        Payload payload = new Payload(this, payloadStart,
                                payloadLength, payloadType, tickNo);
                        double longitude = Math.toDegrees(payload.getDouble(0));
                        double latitude = Math.toDegrees(payload.getDouble(8));
                        if (longitude != 0.0 && latitude != 0.0
                                && Math.abs(longitude) > 0.0175
                                && Math.abs(latitude) > 0.0175) {
                            gpsLockTick = tickNo;
                        }
                    } else if (flightStartTick == -1 && payloadType == 12) {
                        Payload payload = new Payload(this, payloadStart,
                                payloadLength, payloadType, tickNo);
                        if (payload.getShort(42) > 0) {
                            flightStartTick = tickNo - (long) (0.6f
                                    * ((float) (payload.getShort(42) * 100)));
                        }
                    }
                }
            }
        } catch (FileEnd ex) {
        } catch (Corrupted ex) {
        } catch (IOException e) {
        } finally {
            if (Persist.EXPERIMENTAL_DEV)
                printTypes();
        }
    }
}
