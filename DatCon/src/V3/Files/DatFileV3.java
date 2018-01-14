package src.V3.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Vector;

import src.DatConRecs.Payload;
import src.DatConRecs.RecDef.OpConfig;
import src.DatConRecs.RecDef.RecordDef;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatConLog;
import src.Files.FileEnd;
import src.Files.NotDatFile;
import src.Files.Persist;
import src.Files.RecSpec;
import src.GUI.LogFilesPanel;
import src.GUI.LoggingPanel;

public class DatFileV3 extends src.Files.DatFile {

    static boolean debug = true;

    private Vector<RecordDef> recordDefs = null;

    public Vector<RecordDef> getRecordDefs() {
        return recordDefs;
    }

    public DatFileV3(String fileName) throws IOException, NotDatFile {
        super(fileName);
    }

    public DatFileV3(File _file) throws IOException, NotDatFile {
        super(_file);
    }

    public DatFileV3() {
    }

    public ConvertDat createConVertDat() {
        return (new ConvertDatV3(this));
    }

    public void reset() throws IOException {
        results = new AnalyzeDatResults();
        if (inputStream == null) {
            inputStream = new FileInputStream(file);
            _channel = inputStream.getChannel();
            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
            memory.order(ByteOrder.LITTLE_ENDIAN);
        }
        startOfRecord = startOfRecords;
        datRecTickNo = -1;
        lastRecordTickNo = 0;
        lastActualTickNo = 0;
        presentOffset = 0;
        inRollover = false;
        numRolloverRecs = 0;
        numRecs = 0;
        numCorrupted = 0;
        Corrupted.reset();
        try {
            setPosition(startOfRecord);
        } catch (FileEnd | IOException e) {
            throw new RuntimeException("reset:setPosition failed");
        }
    }

    public int _type = 0;

    public long _tickNo = 0;

    public int _payloadLength = 0;

    public long _start;

    static long datRecTickNo = -1;

    long lastRecordTickNo = 0;

    long presentOffset = 0;

    long prevOffset = 0;

    long lastActualTickNo = 0;

    long upperTickLim = Long.parseLong("4292717296");

    long tickNoBoundary = Long.parseLong("4294967296");

    boolean inRollover = false;

    int numRolloverRecs = 0;

    public int lengthOfRecord = 0;

    long xxxx = Long.parseLong("14156619143");

    // filter - filter recId 208
    // translate - translate into 255 recs
    // sequence - remove recs out of sequence
    // eofProcessing -
    public boolean getNextDatRec(boolean filter, boolean translate,
            boolean sequence, boolean eofProcessing) throws Corrupted, FileEnd {
        boolean done = false;
        long nextStartOfRecord = 0;
        long actualTickNo = 0;
        long offset = 0;
        while (!done) {
            try {
                setPosition(startOfRecord);
                // if positioned at a 0x00 then try to skip over the 0x00s , this from Spark .DAT
                if (getByte(startOfRecord) == 0x00) {
                    while (getByte(startOfRecord) == 0x00) {
                        startOfRecord++;
                        if (startOfRecord > fileLength)
                            throw (_fileEnd);
                    }
                }
                // if not positioned at next 0x55, then its corrupted
                if (getByte(startOfRecord) != 0x55) {
                    throw (new Corrupted(actualTickNo, startOfRecord));
                }
                lengthOfRecord = (0xFF & getByte(startOfRecord + 1));
                byte always0 = (byte) getByte(startOfRecord + 2);
                nextStartOfRecord = startOfRecord + lengthOfRecord;
                if (nextStartOfRecord > fileLength)
                    throw (_fileEnd);
                short hdrChksum = (short) (0xFF & getByte(startOfRecord + 3));
                int type = getUnsignedShort(startOfRecord + 4);
                long thisRecordTickNo = getUnsignedInt(startOfRecord + 6);
                int calcChksum = calc_checksum(memory, startOfRecord,
                        (short) (lengthOfRecord - 2));
                int chksum = getUnsignedShort(
                        startOfRecord + lengthOfRecord - 2);
                if (calcChksum != chksum) {
                    throw (new Corrupted(thisRecordTickNo, startOfRecord + 1,
                            Corrupted.Type.CRC));
                }
                numRecs++;
                if (always0 != 0) {
                    throw (new Corrupted(thisRecordTickNo, startOfRecord + 1));
                }

                if (!inRollover && lastRecordTickNo > upperTickLim
                        && thisRecordTickNo < 2225000) {
                    prevOffset = presentOffset;
                    presentOffset += tickNoBoundary;
                    inRollover = true;
                    numRolloverRecs = 0;
                }
                offset = presentOffset;
                if (inRollover) {
                    numRolloverRecs++;
                    if (thisRecordTickNo > upperTickLim) {
                        offset = prevOffset;
                    }
                    if (numRolloverRecs > 100) {
                        inRollover = false;
                        numRolloverRecs = 0;
                    }
                }
                actualTickNo = thisRecordTickNo + offset;
                lastRecordTickNo = thisRecordTickNo;

                // look for large delta in tickNo
                if (Math.abs(lastActualTickNo - actualTickNo) > 22000000) {
                    if (eofProcessing && !isTablet()
                            && (fileLength - nextStartOfRecord < 40000)) { // the end of the file is corrupted
                        throw (_fileEnd);
                    }
                    // just this record is corrupted
                    lastActualTickNo = actualTickNo;
                    throw (new Corrupted(thisRecordTickNo, startOfRecord + 1));
                }

                if (lengthOfRecord == 0) {
                    throw (new Corrupted(actualTickNo, startOfRecord + 1));
                }

                // if nextStartOfRecord not positioned at next 0x55, then this
                // is corrupted, but if it's 0x00 let it be handled by the
                // processing of the next record
                if (getByte(nextStartOfRecord) != 0x55
                        && getByte(nextStartOfRecord) != 0x00) {
                    throw (new Corrupted(actualTickNo, nextStartOfRecord));
                }
                if (!sequence || (actualTickNo > lastActualTickNo)) {
                    lastActualTickNo = actualTickNo;
                    //                    if (translate) {
                    //                        if ((0xff & msg) == 0x80) {
                    //                            hdrChksum = 255;
                    //                            type = 1;
                    //                        } else if ((0xff & msg) == 0xFF) {
                    //                            hdrChksum = 255;
                    //                            //subType = 2;
                    //                        }
                    //                    }
                    //                    if (!filter || !(hdrChksum == 208
                    //                            && (type == 76 || type == 77))) {
                    //_type = hdrChksum;
                    _type = type;
                    _payloadLength = lengthOfRecord - headerLength
                            - chksumLength;
                    _tickNo = actualTickNo;
                    _start = startOfRecord + headerLength;
                    startOfRecord = nextStartOfRecord;
                    return true;
                }

                startOfRecord = nextStartOfRecord;
            } catch (Corrupted c) {
                if (getPos() > fileLength - 600) {
                    throw (_fileEnd);
                }
                numCorrupted++;
                //                                                System.out.println("CR :" + numCorrupted + " "
                //                                                        + (float) numCorrupted / (float) numRecs);
                if ((numRecs > 1000)
                        && ((float) numCorrupted / (float) numRecs) > 0.02) {
                    throw (new Corrupted(actualTickNo, startOfRecord));
                }
                try {
                    setPosition(c.filePos);
                    byte fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (_fileEnd);
                        }
                        fiftyfive = readByte();
                    }
                } catch (FileEnd f) {
                    throw (_fileEnd);
                } catch (IOException e) {
                    throw (new Corrupted(actualTickNo, nextStartOfRecord));
                }
                // set position right before the next 0x55
                startOfRecord = getPos() - 1;
            } catch (FileEnd f) {
                throw (_fileEnd);
            } catch (Exception e) {
                throw (new Corrupted(actualTickNo, startOfRecord));
            }
        }
        return false;
    }

    Vector<OpConfig.Line> opLines = new Vector<OpConfig.Line>();

    @Override
    public void preAnalyze() throws NotDatFile {
        try {
            reset();
            clearRecsInDat();
            long tickNo = 0;
            while (true) {
                if (getPos() > fileLength - 8) {
                    throw (_fileEnd);
                }
                getNextDatRec(true, true, true, true);
                //getNextDatRec(true, true, true, false);
                if (_type == 0XFFFD) {
                    Payload xorBB = new Payload(this, _start, _payloadLength,
                            _type, _tickNo);
                    String payloadString = xorBB.getAsString();
                    String lines[] = payloadString.split("\\n");
                    for (int i = 0; i < lines.length; i++) {
                        //System.out.println("XX " + payloadString);
                        opLines.add(new OpConfig.Line(lines[i]));
                    }
                }
                addRecInDat(_type, _payloadLength);
                tickNo = _tickNo;
                if (lowestTickNo < 0) {
                    lowestTickNo = tickNo;
                }
                if (tickNo > highestTickNo) {
                    highestTickNo = tickNo;
                }
                if ((_type == 0x8000) && (firstMotorStartTick == 0
                        || lastMotorStopTick == 0)) {
                    Payload xorBB = new Payload(this, _start, _payloadLength,
                            _type, tickNo);
                    String payloadString = xorBB.getString();
                    if (firstMotorStartTick == 0 && payloadString
                            .indexOf("[L-FMU/MOTOR]Start.") > -1) {
                        firstMotorStartTick = tickNo;
                    }
                    if (payloadString.indexOf("[L-FMU/MOTOR] Stop.") > -1) {
                        lastMotorStopTick = tickNo;
                    }
                }
                if (numBattCells == 0 && _type == 0xFFFD) {
                    Payload xorBB = new Payload(this, _start, _payloadLength,
                            _type, tickNo);
                    String payloadString = xorBB.getString();
                    if (payloadString.indexOf(
                            "[            3] => raw_battery_cell_num") > -1) {
                        numBattCells = 3;
                    }
                    if (payloadString.indexOf(
                            "[            4] => raw_battery_cell_num") > -1) {
                        numBattCells = 4;
                    }
                    if (payloadString.indexOf(
                            "[            6] => raw_battery_cell_num") > -1) {
                        numBattCells = 6;
                    }
                }
                if (gpsLockTick == -1 && _type == 12) {
                    Payload payload = new Payload(this, _start, _payloadLength,
                            _type, tickNo);
                    double longitude = Math.toDegrees(payload.getDouble(0));
                    double latitude = Math.toDegrees(payload.getDouble(8));
                    if (longitude != 0.0 && latitude != 0.0
                            && Math.abs(longitude) > 0.0175
                            && Math.abs(latitude) > 0.0175) {
                        gpsLockTick = tickNo;
                    }
                }
                if (flightStartTick == -1 && _type == 12) {
                    Payload payload = new Payload(this, _start, _payloadLength,
                            _type, tickNo);
                    if (payload.getShort(42) > 0) {
                        flightStartTick = tickNo - (long) (0.6f
                                * ((float) (payload.getShort(42) * 100)));
                    }
                }
            }
        } catch (FileEnd ex) {
        } catch (Corrupted ex) {
        } catch (IOException e) {
        } finally {
            try {
                OpConfig opConfig = new OpConfig(opLines);
                recordDefs = opConfig.getRecords();
            } catch (Exception e) {
                if (Persist.EXPERIMENTAL_DEV) {
                    e.printStackTrace();
                } else {
                    DatConLog.Exception(e, "Can't create recDefs");
                }
            }
            if (numBattCells == 0) {
                switch (acType) {
                case I1:
                    numBattCells = 6;
                    break;
                case I2:
                    numBattCells = 6;
                    break;
                case M100:
                    numBattCells = 6;
                    break;
                case M600:
                    numBattCells = 6;
                    break;
                case MAVIC:
                    numBattCells = 3;
                    break;
                case P3AP:
                    numBattCells = 4;
                    break;
                case P3I1:
                    numBattCells = 6;
                    break;
                case P3S:
                    numBattCells = 4;
                    break;
                case P4:
                    numBattCells = 4;
                    break;
                case P4A:
                    numBattCells = 4;
                    break;
                case P4P:
                    numBattCells = 4;
                    break;
                case SPARK:
                    numBattCells = 3;
                    break;
                case UNKNOWN:
                    numBattCells = 4;
                    DatConLog.Log("Assuming 4 cellls per battery");
                    break;
                default:
                    numBattCells = 4;
                    DatConLog.Log("Assuming 4 cellls per battery");
                    break;
                }
            }
            close();
        }
    }

    @Override
    public boolean isTablet() {
        // for now just base this on existence of GoTxt (subType == 12)
        return (getRecId(12) == null);
    }
}
