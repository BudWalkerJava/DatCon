package src.V3.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

import src.DatConRecs.Payload;
import src.Files.Corrupted;
import src.Files.DatConLog;
import src.Files.ConvertDat;
import src.Files.DatHeader;
import src.Files.FileEnd;
import src.Files.NotDatFile;
import src.Files.TypeSubType;
import src.Files.AnalyzeDatResults;
import src.V3.Generic.Files.ConvertDatGeneric;

public class DatFileV3 extends src.Files.DatFile {

    static boolean debug = true;

    public DatFileV3(String fileName) throws IOException, NotDatFile {
        super(fileName);
    }

    public DatFileV3(File _file) throws IOException, NotDatFile {
        super(_file);
    }

    public DatFileV3() {
    }

    public ConvertDat createConVertDat() {
        //        if (acType == DatHeader.AcType.SPARK && false) {
        //            return (new ConvertDatSpark(this));
        //        } else {
        //            return (new ConvertDatGeneric(this));
        //        }
        return (new ConvertDatGeneric(this));
        //        switch (acType) {
        //        case MAVIC: {
        //            return (new ConvertDatMavic(this));
        //        }
        //        case P4: {
        //            return (new ConvertDatP4(this));
        //        }
        //        case P4P: {
        //            return (new ConvertDatP4P(this));
        //        }
        //        case I2: {
        //            return (new ConvertDatI2(this));
        //        }
        //        case M600: {
        //            return (new ConvertDatM600(this));
        //        }
        //        case M100: {
        //            return (new ConvertDatM100(this));
        //        }
        //        case I1:
        //        case P3AP:
        //        case P3I1:
        //        case P3S:
        //            LoggingPanel.instance
        //                    .Error("DatFileV3:createConvertDat:no ConvertDat for V1 type"
        //                            + acType);
        //            return null;
        //        case UNKNOWN:
        //            //            LoggingPanel.instance
        //            //                    .Error("DatFileV3:createConvertDat:no ConvertDat for unknown type"
        //            //                            + acType);
        //            //            return null;
        //            return (new ConvertDatGeneric(this));
        //        default:
        //            break;
        //        }
        //        LoggingPanel.instance.Error(
        //                "DatFileV3:createConvertDat:no ConvertDat for " + acType);
        //        return null;
    }

    public void reset() throws IOException {
        numCorrupted = 0;
        results = new AnalyzeDatResults();
        if (inputStream == null) {
            inputStream = new FileInputStream(file);
            _channel = inputStream.getChannel();
            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
            memory.order(ByteOrder.LITTLE_ENDIAN);
        }
        startOfRecord = 256;
        datRecTickNo = -1;
        lastRecordTickNo = 0;
        lastActualTickNo = 0;
        presentOffset = 0;
        inRollover = false;
        numRolloverRecs = 0;
        numRecs = 0;
        try {
            setPosition(startOfRecord);
        } catch (FileEnd | IOException e) {
            throw new RuntimeException("reset:setPosition failed");
        }
    }

    public short _type = 0;

    public short _subType = 0;

    public byte _msg = 0;

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

    long numRecs = 0;

    public int lengthOfRecord = 0;

    private int count = 0;

    private int numBattCells = 0;;

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
                // if positioned at 0x00 then try to skip over the 0x00s , this from Spark .DAT
                if (getByte(startOfRecord) == 0x00) {
                    int x = 1;
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
                short type = (short) (0xFF & getByte(startOfRecord + 3));
                short subType = (short) (0xFF & getByte(startOfRecord + 4));
                byte msg = (byte) getByte(startOfRecord + 5);
                long thisRecordTickNo = getUnsignedInt(startOfRecord + 6);
                numRecs++;

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
                if (lengthOfRecord == 0) {
                    throw (new Corrupted(actualTickNo, startOfRecord + 1));
                }

                nextStartOfRecord = startOfRecord + lengthOfRecord; // the next 0x55 , we hope
                if (nextStartOfRecord > fileLength)
                    throw (_fileEnd);
                // if nextStartOfRecord not positioned at next 0x55, then this
                // is corrupted, but if it's 0x00 let it be handled by the
                // processing of the next record
                if (getByte(nextStartOfRecord) != 0x55
                        && getByte(nextStartOfRecord) != 0x00) {
                    //                    if (ConvertDat.EXPERIMENTAL_DEV) {
                    //                        System.out.println("xx " + numCorrupted + ":" + type
                    //                                + " ST " + subType + " MSG " + msg + " SONR "
                    //                                + getByte(nextStartOfRecord));
                    //                    }
                    //                        for (int i = 0; i < lengthOfRecord; i++) {
                    //                            System.out.print(String.format("%02X",
                    //                                    (0xff & getByte(startOfRecord + i))) + ",");
                    //                        }
                    //                        System.out.println("");
                    //                        for (int i = 0; i < 200; i++) {
                    //                            System.out
                    //                                    .print(String.format("%02X",
                    //                                            (0xff & getByte(startOfRecord
                    //                                                    + lengthOfRecord + i)))
                    //                                            + ",");
                    //                        }
                    //                        System.out.println("");
                    //                    }
                    throw (new Corrupted(actualTickNo, nextStartOfRecord));
                }

                if (eofProcessing
                        && (((actualTickNo - lastActualTickNo) > 22500000)
                                || ((lastActualTickNo
                                        - actualTickNo) > 22500000))
                        && (fileLength - nextStartOfRecord < 50000)) {
                    throw (_fileEnd);
                }
                if (!sequence || (actualTickNo > lastActualTickNo)) {
                    lastActualTickNo = actualTickNo;
                    if (translate) {
                        if ((0xff & msg) == 0x80) {
                            type = 255;
                            subType = 1;
                        } else if ((0xff & msg) == 0xFF) {
                            type = 255;
                            //subType = 2;
                        }
                    }
                    if (!filter || !(type == 208
                            && (subType == 76 || subType == 77))) {
                        _type = type;
                        _subType = subType;
                        _msg = msg;
                        _payloadLength = lengthOfRecord - headerLength
                                - chksumLength;
                        _tickNo = actualTickNo;
                        _start = startOfRecord + headerLength;
                        startOfRecord = nextStartOfRecord;
                        return true;
                    }
                }
                startOfRecord = nextStartOfRecord;
            } catch (Corrupted c) {
                if (getPos() > fileLength - 600) {
                    throw (_fileEnd);
                }
                numCorrupted++;
                //                System.out.println("CR :" + numCorrupted + " "
                //                        + (float) numCorrupted / (float) numRecs);
                if (numCorrupted > 2430) { /// Spark always has 2405 corrupted recs, gotta check this
                    throw (new Corrupted(actualTickNo, startOfRecord));
                }
                try {
                    //int numZeros = 0;
                    setPosition(c.filePos);
                    byte fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (_fileEnd);
                        }
                        fiftyfive = readByte();
                        //numZeros++;
                    }
                    //                    if (ConvertDat.EXPERIMENTAL_DEV) {
                    //                        System.out.println("NumZ " + numZeros);
                    //                    }
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

    @Override
    public void preAnalyze() throws NotDatFile {
        try {
            reset();
            long tickNo = 0;
            while (true) {
                if (getPos() > fileLength - 8) {
                    throw (_fileEnd);
                }
                getNextDatRec(true, true, true, true);
                typeSubTypes.add(new TypeSubType(_type, _subType));

                tickNo = _tickNo;
                if (lowestTickNo < 0) {
                    lowestTickNo = tickNo;
                }
                if (tickNo > highestTickNo) {
                    highestTickNo = tickNo;
                }
                if ((_type == 255 && _subType == 1) && (firstMotorStartTick == 0
                        || lastMotorStopTick == 0)) {
                    Payload xorBB = new Payload(this, _start, _payloadLength,
                            _type, _subType, tickNo);
                    String payloadString = xorBB.getString();
                    if (firstMotorStartTick == 0 && payloadString
                            .indexOf("[L-FMU/MOTOR]Start.") > -1) {
                        firstMotorStartTick = tickNo;
                    }
                    if (payloadString.indexOf("[L-FMU/MOTOR] Stop.") > -1) {
                        lastMotorStopTick = tickNo;
                    }
                }
                if (numBattCells == 0 && _type == 255 && _subType == 255) {
                    Payload xorBB = new Payload(this, _start, _payloadLength,
                            _type, _subType, tickNo);
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
                if (gpsLockTick == -1 && _type == 21 && _subType == 12) {
                    Payload payload = new Payload(this, _start, _payloadLength,
                            _type, _subType, tickNo);
                    double longitude = Math.toDegrees(payload.getDouble(0));
                    double latitude = Math.toDegrees(payload.getDouble(8));
                    if (longitude != 0.0 && latitude != 0.0
                            && Math.abs(longitude) > 0.0175
                            && Math.abs(latitude) > 0.0175) {
                        gpsLockTick = tickNo;
                    }
                }
                if (flightStartTick == -1 && _type == 21 && _subType == 12) {
                    Payload payload = new Payload(this, _start, _payloadLength,
                            _type, _subType, tickNo);
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
            if (numBattCells == 0) {
                if (acType == DatHeader.AcType.SPARK) {
                    numBattCells = 3;
                } else if (acType == DatHeader.AcType.M100) {
                    numBattCells = 6;
                } else {
                    numBattCells = 4;
                    DatConLog.Log("Assuming 4 cellls per battery");
                }
            }
            close();
        }
    }

    public int getNumBattCells() {

        return numBattCells;
    }

}
