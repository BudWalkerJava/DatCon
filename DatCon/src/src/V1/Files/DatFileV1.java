/* DatFile class

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that redistribution of source code include
the following disclaimer in the documentation and/or other materials provided
with the distribution.

THIS SOFTWARE IS PROVIDED BY ITS CREATOR "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE CREATOR OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package src.V1.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import src.DatConRecs.Payload;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatFile;
import src.Files.DatHeader;
import src.Files.DatHeader.AcType;
import src.Files.FileEnd;
import src.Files.NotDatFile;

public class DatFileV1 extends DatFile {

    long nextFilPos = startOfRecords; // beginning filePos of next tick group

    int nextTickGroup = 0; // tickNo of next group

    private static final int maxNumElements = 50;

    public class tickGroup {
        public int numElements = 0;

        public long start[] = new long[maxNumElements];

        public short subType[] = new short[maxNumElements];

        public byte msgs[] = new byte[maxNumElements];

        public int length[] = new int[maxNumElements];

        public short payloadType[] = new short[maxNumElements];

        public long tickNo = -1;

        public void reset() {
            numElements = 0;
            tickNo = -1;
        }

        public void add(long _tickNo, long _start, int _length, short _ptType,
                short _subType, byte _msgs) {
            numElements++;
            tickNo = _tickNo;
            start[numElements - 1] = _start;
            length[numElements - 1] = _length;
            payloadType[numElements - 1] = _ptType;
            subType[numElements - 1] = _subType;
            msgs[numElements - 1] = _msgs;
        }

        public void add(long _start, int _length, short _ptType, short _subType,
                byte _msgs) {
            numElements++;
            start[numElements - 1] = _start;
            length[numElements - 1] = _length;
            payloadType[numElements - 1] = _ptType;
            subType[numElements - 1] = _subType;
            msgs[numElements - 1] = _msgs;
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
        while (!done) {
            try {
                setPosition(startOfRecord);
                if (getByte(startOfRecord) != 0x55) { // if not positioned at
                                                          // next 0x55, then its
                                                      // corrupted
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
                }
                length = (0xFF & getByte(startOfRecord + 1));
                byte always0 = (byte) getByte(startOfRecord + 2);
                short type = (short) (0xFF & getByte(startOfRecord + 3));
                short subType = (short) (0xFF & getByte(startOfRecord + 4));
                byte msg = (byte) getByte(startOfRecord + 5);
                int thisRecordTickNo = (int) getUnsignedInt(startOfRecord + 6);
                if (thisRecordTickNo < 0
                        || (alternateStructure && thisRecordTickNo > 4500000)
                        || (!alternateStructure
                                && thisRecordTickNo > 1500000)) {
                    throw (new Corrupted(lastRecordTickNo, startOfRecord + 1));
                }
                lastRecordTickNo = thisRecordTickNo;
                if (length == 0) {
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord + 1));
                }
                nextStartOfRecord = startOfRecord + length; // the next 0x55 ,
                                                            // we hope
                if (nextStartOfRecord > fileLength)
                    throw (_fileEnd);
                if (getByte(nextStartOfRecord) != 0x55) { // if not positioned
                                                              // at next 0x55,
                                                          // then its
                                                          // corrupted
                    throw (new Corrupted(thisGroupsTickNo, nextStartOfRecord));
                }
                if ((0xff & msg) == 0x80) {
                    type = 255;
                    subType = 1;
                }
                if ((0xff & msg) == 0xFF) {
                    type = 255;
                    subType = 2;
                }
                if (thisGroupsTickNo == -1) { // thisTickGroup doesn't yet have
                    // a tickNo
                    thisGroupsTickNo = thisRecordTickNo;
                    thisTickGroup.tickNo = thisRecordTickNo;
                }
                if (thisRecordTickNo > thisTickGroup.tickNo) { // start next
                                                                   // group
                    nextTickGroup.reset();
                    nextTickGroup.add(thisRecordTickNo,
                            startOfRecord + headerLength, length - headerLength,
                            type, subType, msg);
                    done = true;
                } else if (thisRecordTickNo == thisTickGroup.tickNo) {
                    thisTickGroup.add(startOfRecord + headerLength,
                            length - headerLength, type, subType, msg);
                } else { // (tickNo < thisTickGroup.tickNo) in the last group
                             // for now, just ignore
                }

                startOfRecord = nextStartOfRecord;
            } catch (Corrupted c) {
                if (getPos() > fileLength - (int) clockRate) {
                    throw (_fileEnd);
                }
                numCorrupted++;
                // results.addMessage(c.toString() + "\n");
                // results.setResultCode(AnalyzeDatResults.ResultCode.SOME_ERRORS);
                if (numCorrupted > 25) {
                    results.setResultCode(
                            AnalyzeDatResults.ResultCode.CORRUPTED);
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
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
                } catch (Exception e) {
                    throw (new Corrupted(thisGroupsTickNo, startOfRecord));
                }
                // set position right before the next 0x55
                startOfRecord = getPos() - 1;
            } catch (FileEnd f) {
                throw (_fileEnd);
            } catch (Exception e) {
                e.printStackTrace();
                results.setResultCode(AnalyzeDatResults.ResultCode.CORRUPTED);
                throw (new Corrupted(thisGroupsTickNo, startOfRecord));
            }
        }
        return thisTickGroup;
    }

    public DatFileV1(String fileName) throws IOException, NotDatFile {
        this(new File(fileName));
    }

    public DatFileV1(File _file) throws NotDatFile, FileNotFoundException {
        file = _file;
        tickGroups[0] = new tickGroup();
        tickGroups[1] = new tickGroup();
        results = new AnalyzeDatResults();
        fileLength = file.length();
        inputStream = new FileInputStream(file);
        _channel = inputStream.getChannel();
        try {
            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        memory.order(ByteOrder.LITTLE_ENDIAN);
        acType = DatHeader.AcType.P3I1;
    }

    public DatFileV1() {
        acType = DatHeader.AcType.P3I1;
    }

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
        numCorrupted = 0;
        results = new AnalyzeDatResults();
        if (inputStream == null) {
            inputStream = new FileInputStream(file);
            _channel = inputStream.getChannel();
            memory = _channel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
            memory.order(ByteOrder.LITTLE_ENDIAN);
        }
        startOfRecord = startOfRecords;
        setPosition(startOfRecord);
    }

    public void skipOver(int num) throws IOException {
        filePos = filePos + num;
        if (filePos > fileLength)
            throw (new IOException());
        _channel.position(filePos);
    }

    public String toString() {
        return file.getName();
    }

    // public String bufferToString() throws FileEnd {
    // return filePos + ":" + String.format("%02X", (0xff & getByte()))
    // + " : " + (0xff & getByte()) + " :Shrt " + getShort()
    // + " :UShrt " + getUnsignedShort() + " :I " + getInt() + " :UI "
    // + getUnsignedInt() + " :L " + getLong() + " :F " + getFloat()
    // + " :D " + getDouble();
    // }

    public void setPosition(long pos) throws FileEnd, IOException {
        filePos = pos;
        if (filePos >= fileLength)
            throw (new FileEnd());
        _channel.position(pos);
    }

    public long getPos() {
        return filePos;
    }

    public long getLength() {
        return fileLength;
    }

    public byte getByte() {
        return memory.get((int) filePos);
    }

    public int getByte(long fp) throws FileEnd {
        if (fp >= fileLength)
            throw (new FileEnd());
        return memory.get((int) fp);
    }

    private String getString(long fp) {
        int length = 256;
        byte bytes[] = new byte[length];
        int l = 0;
        int B = 0x00;
        for (int i = 0; i < length; i++) {
            try {
                B = getByte((int) fp + i);
            } catch (FileEnd e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (B == 0x00 || B == '\r' || B == '\n') {
                l = i;
                break;
            }
            bytes[i] = (byte) B;
        }
        String retv = new String(bytes, 0, l);
        return retv;
    }

    public byte readByte() throws IOException {
        byte rv = getByte();
        skipOver(1);
        return rv;
    }

    protected short getShort() {
        return memory.getShort((int) filePos);
    }

    public int getUnsignedShort() {
        return (int) (0xff & memory.get((int) filePos))
                + 256 * (int) (0xff & memory.get((int) (filePos + 1)));
    }

    private int getUnsignedShort(long fp) throws FileEnd {
        if (fp > fileLength - 2)
            throw (new FileEnd());
        return (int) (0xff & memory.get((int) fp))
                + 256 * (int) (0xff & memory.get((int) (fp + 1)));
    }

    public int getInt() {
        return memory.getInt((int) filePos);
    }

    public long getUnsignedInt() throws FileEnd {
        return getUnsignedInt(filePos);
    }

    public long getUnsignedInt(long fp) throws FileEnd {
        if (fp > fileLength - 4)
            throw (new FileEnd());
        return (long) (0xff & memory.get((int) fp))
                + (256 * (long) (0xff & memory.get((int) (fp + 1))))
                + (65536 * (long) (0xff & memory.get((int) (fp + 2))))
                + (65536 * 256 * (long) (0xff & memory.get((int) (fp + 3))));
    }

    public long getLong() {
        return memory.getLong((int) filePos);
    }

    public float getFloat() {
        return memory.getFloat((int) filePos);
    }

    public double getDouble() {
        return memory.getDouble((int) filePos);
    }

    public AnalyzeDatResults getResults() {
        return results;
    }

    public File getFile() {
        return file;
    }

    public void setStartOfRecord(long sor) {
        startOfRecord = sor;
    }

    public String fileName() {
        String retv = "Unknown";
        try {
            retv = file.getCanonicalPath();
        } catch (IOException e) {

        }
        return retv;
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
                    throw (_fileEnd);
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
                        throw (_fileEnd);
                    }
                    if (getByte(nextStartOfRecord) != 0x55) {
                        throw (new Corrupted());
                    }
                    long tickNo = (int) getUnsignedInt(filePos + 6);
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
                            throw (_fileEnd);
                        }
                        fiftyfive = readByte();
                    }
                    filePos = getPos() - 1;
                }
            }
        } catch (Exception e) {
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
                    throw (_fileEnd);
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
                        throw (_fileEnd);
                    }
                    if (getByte(nextStartOfRecord) != 0x55) {
                        throw (new Corrupted());
                    }
                    long tickNo = (int) getUnsignedInt(filePos + 6);
                    lowestTickNo = tickNo;
                    done = true;
                } catch (Corrupted c) {
                    setPosition(getPos() + 1);
                    fiftyfive = readByte();
                    while (fiftyfive != 0X55) {
                        if (getPos() > fileLength - 1000) {
                            throw (_fileEnd);
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
            long tickNo = 0;
            while (true) {
                if (getPos() > fileLength - 8) {
                    throw (_fileEnd);
                }
                DatFileV1.tickGroup tG = getTickGroup();
                tickNo = tG.tickNo;
                for (int tgIndex = 0; tgIndex < tG.numElements; tgIndex++) {
                    short payloadType = tG.payloadType[tgIndex];
                    long payloadStart = tG.start[tgIndex];
                    int payloadLength = tG.length[tgIndex];
                    short subType = tG.subType[tgIndex];
                    if (payloadType == 255 && subType == 1) {
                        Payload xorBB = new Payload(this, payloadStart,
                                payloadLength, payloadType, subType, tickNo);
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
                                        .indexOf("tp1406") > -1) {
                                    // / don't know what this is, involves
                                    // landing gear
                                }
                            }
                        }
                    } else if (gpsLockTick == -1 && payloadType == 42
                            && subType == 12) {
                        Payload payload = new Payload(this, payloadStart,
                                payloadLength, payloadType, subType, tickNo);
                        double longitude = Math.toDegrees(payload.getDouble(0));
                        double latitude = Math.toDegrees(payload.getDouble(8));
                        if (longitude != 0.0 && latitude != 0.0
                                && Math.abs(longitude) > 0.0175
                                && Math.abs(latitude) > 0.0175) {
                            gpsLockTick = tickNo;
                        }
                    } else if (flightStartTick == -1 && payloadType == 42
                            && subType == 12) {
                        Payload payload = new Payload(this, payloadStart,
                                payloadLength, payloadType, subType, tickNo);
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
        }
    }

    public double getClockRate() {
        return clockRate;
    }

    public String timeString(long tickNo, long offset) {
        return timeFormat.format(time(tickNo, offset));
    }

    public float time(long tickNo, long offset) {
        return (float) (tickNo - offset) / (float) clockRate;
    }

    public void setClockRate(double rate) {
        clockRate = rate;
    }

    public long getTickFromTime(Number time, long offset) {
        return ((long) (clockRate * time.doubleValue())) + offset;
    }

}
