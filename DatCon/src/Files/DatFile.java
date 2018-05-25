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
package src.Files;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import src.DatConRecs.Record;
import src.Files.Corrupted.Type;
import src.Files.DatHeader.AcType;
import src.V1.Files.DatFileV1;
import src.V3.Files.DatFileV3;
import src.apps.DatCon;

public class DatFile {

    protected final static int headerLength = 10;

    protected final static int chksumLength = 2;

    protected int startOfRecords = 128;

    public void setStartOfRecords(int startOfRecords) {
        this.startOfRecords = startOfRecords;
    }

    protected MappedByteBuffer memory = null;

    private String acTypeName = "";

    protected long filePos = 0;

    protected File file = null;

    protected FileInputStream inputStream = null;

    protected FileChannel _channel = null;

    protected long fileLength = 0;

    public static String firmwareDate = "";

    protected int numCorrupted = 0;

    protected long numRecs = 0;

    protected AnalyzeDatResults results = null;

    public long startOfRecord = 0;

    public long lowestTickNo = -1;

    public long highestTickNo = -1;

    public long firstMotorStartTick = 0;

    public long lastMotorStopTick = -1;

    public long flightStartTick = -1;

    public long gpsLockTick = -1;

    protected int numBattCells = 0;

    public DatHeader.AcType acType = DatHeader.AcType.UNKNOWN;

    protected long lastRecordTickNo = 0;

    public static DecimalFormat timeFormat = new DecimalFormat("###.000",
            new DecimalFormatSymbols(Locale.US));

    private static DatFile datFile;

    double clockRate = 600;

    private DatHeader datHeader;

    private HashMap<Integer, RecSpec> recsInDat = new HashMap<Integer, RecSpec>();

    public long _tickNo = 0;

    public int _type = 0;

    public HashMap<Integer, RecSpec> getRecsInDat() {
        return recsInDat;
    }

    public void addRecInDat(int type, int length) {
        Integer key = Integer.valueOf(type);
        if (!recsInDat.containsKey(key)) {
            recsInDat.put(key, new RecSpec(type, length));
        }
    }

    protected void clearRecsInDat() {
        recsInDat.clear();
    }

    public DatFile(String fileName) throws IOException, NotDatFile {
        this(new File(fileName));
    }

    public static DatFile createDatFile(String datFileName)
            throws NotDatFile, IOException {
        byte arra[] = new byte[256];
        //if (true )return (new DatFileV3(datFileName));
        DatConLog.Log(" ");
        DatConLog.Log("createDatFile " + datFileName);
        FileInputStream bfr = new FileInputStream(new File(datFileName));
        bfr.read(arra, 0, 256);
        bfr.close();
        String headerString = new String(arra, 0, 21);
        if (!(headerString.substring(16, 21).equals("BUILD"))) {
            if (Persist.invalidStructOK) {
                DatConLog.Log("createDatFile invalid header - proceeding");
                datFile = new DatFileV3(datFileName);
                datFile.setStartOfRecords(256);
                return datFile;
            }
            if (headerString.substring(0, 4).equals("LOGH")) {
                throw new NotDatFile("Probably an encrypted .DAT");
            }
            throw new NotDatFile();
        }
        if ((new String(arra, 242, 10).equals("DJI_LOG_V3"))) {
            datFile = new DatFileV3(datFileName);
            datFile.setStartOfRecords(256);
        } else {
            datFile = new DatFileV1(datFileName);
            datFile.setStartOfRecords(128);
        }
        return datFile;
    }

    public static boolean isDatFile(String datFileName) {
        byte arra[] = new byte[256];
        try {
            FileInputStream bfr = new FileInputStream(new File(datFileName));
            bfr.read(arra, 0, 256);
            bfr.close();
            if ((new String(arra, 16, 5).equals("BUILD"))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static DatFile createDatFile(String datFileName, final DatCon datCon)
            throws NotDatFile, IOException {
        if (DJIAssistantFile.isDJIDat(new File(datFileName))) {
            if (Persist.autoTransDJIAFiles) {
                int lastSlash = datFileName.lastIndexOf("\\");
                String tempDirName = datFileName.substring(0, lastSlash + 1);
                Color bgColor = datCon.goButton.getBackground();
                Color fgColor = datCon.goButton.getForeground();
                boolean enabled = datCon.goButton.isEnabled();
                String text = datCon.goButton.getText();
                datCon.goButton.setBackground(Color.BLUE);
                datCon.goButton.setForeground(Color.WHITE);
                datCon.goButton.setEnabled(false);
                datCon.goButton.setText("Extracting .DAT");
                try {
                    DatConLog.Log("DJIAssistantFile.extractFirst(" + datFileName
                            + ", " + tempDirName + ")");
                    DJIAssistantFile.ExtractResult result = DJIAssistantFile
                            .extractFirst(datFileName, tempDirName);
                    if (result.moreThanOne()) {
                        //                    if (true) {
                        DatConLog.Log(
                                "DJIAssistantFile.extractFirst:moreThanOne");
                        boolean moreThanOnePopup = DatConPopups
                                .moreThanOne(DatCon.frame);
                        if (moreThanOnePopup) {
                            return new DatFileV3(result.getFile());
                        } else {
                            return null;
                        }
                    } else if (result.none()) {
                        DatConLog.Log("DJIAssistantFile.extractFirst:none");
                        DatConPopups.none(DatCon.frame);
                        return null;
                    }
                    DatConLog.Log("DJIAssistantFile.extractFirst:one");
                    return new DatFileV3(result.getFile());
                } finally {
                    datCon.goButton.setBackground(bgColor);
                    datCon.goButton.setForeground(fgColor);
                    datCon.goButton.setEnabled(enabled);
                    datCon.goButton.setText(text);
                }
            }
        }
        return createDatFile(datFileName);
    }

    public DatFile(File _file) throws NotDatFile, FileNotFoundException {
        datHeader = new DatHeader(this);
        file = _file;
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
        acType = datHeader.getAcType();
        //acTypeName = DatHeader.toString(acType);
    }

    public DatFile() {
    }

    public ConvertDat createConVertDat() {
        return (new ConvertDat(this));
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
        // tickGroups[0].reset();
        // tickGroups[1].reset();
        // tgIndex = 1;
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
        Record.totalNumRecExceptions = 0;
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

    public void setPosition(final long pos) throws FileEnd, IOException {
        filePos = pos;
        if (filePos > fileLength)
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

    public byte readByte() throws IOException {
        byte rv = getByte();
        skipOver(1);
        return rv;
    }

    protected short getShort() {
        return memory.getShort((int) filePos);
    }

    protected short getShort(long fp) {
        return memory.getShort((int) fp);
    }

    //    protected short getShort(long fp) throws FileEnd {
    //        if (fp > fileLength - 2)
    //            throw (new FileEnd());
    //        return (int) (0xff & memory.get((int) fp))
    //                + 256 * (int) (0xff & memory.get((int) (fp + 1)));
    //    }

    public int getUnsignedShort() {
        return (int) (0xff & memory.get((int) filePos))
                + 256 * (int) (0xff & memory.get((int) (filePos + 1)));
    }

    protected int getUnsignedShort(long fp) throws FileEnd {
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

    public String getFileName() {
        String retv = null;
        if (file != null) {
            retv = file.getName();
        }
        return retv;
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

    public void preAnalyze() throws NotDatFile {
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
        case M200:
            numBattCells = 6;
            break;
        case MavicPro:
            numBattCells = 3;
            break;
        case MavicAir:
            numBattCells = 3;
            break;
        case P3AP:
            numBattCells = 4;
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

    public int getNumBattCells() {
        return numBattCells;
    }

    public String getFirmwareDate() {
        return datHeader.getFWDate();
    }

    public String getACTypeString() {
        if (acType != DatHeader.AcType.UNKNOWN) {
            return acType.toString();
        }
        return acTypeName;
    }

    public RecSpec getRecId(int _type) {
        RecSpec retv = null;
        Iterator<RecSpec> iter = recsInDat.values().iterator();
        while (iter.hasNext()) {
            RecSpec tst = iter.next();
            if (tst.getId() == _type) {
                if (retv != null) {
                    return null;
                }
                retv = tst;
            }
        }
        return retv;
    }

    public void printTypes() {
        Iterator<RecSpec> iter = recsInDat.values().iterator();
        while (iter.hasNext()) {
            RecSpec tst = iter.next();
            DatConLog.Log(tst.getDescription() + " Type " + tst.getId());
        }
    }

    public boolean isTablet() {
        return false;
    }

    public AcType getACType() {
        return acType;
    }

    public double getPercntCorrupted() {
        return (((double) numCorrupted) / ((double) numRecs)) / 100.0;
    }

    static short[] arr_2A103 = new short[] { 0x00, 0x5E, 0xBC, 0xE2, 0x61, 0x3F,
            0xDD, 0x83, 0xC2, 0x9C, 0x7E, 0x20, 0xA3, 0xFD, 0x1F, 0x41, 0x9D,
            0xC3, 0x21, 0x7F, 0xFC, 0xA2, 0x40, 0x1E, 0x5F, 0x01, 0xE3, 0xBD,
            0x3E, 0x60, 0x82, 0xDC, 0x23, 0x7D, 0x9F, 0xC1, 0x42, 0x1C, 0xFE,
            0xA0, 0xE1, 0xBF, 0x5D, 0x03, 0x80, 0xDE, 0x3C, 0x62, 0xBE, 0xE0,
            0x02, 0x5C, 0xDF, 0x81, 0x63, 0x3D, 0x7C, 0x22, 0xC0, 0x9E, 0x1D,
            0x43, 0xA1, 0xFF, 0x46, 0x18, 0xFA, 0xA4, 0x27, 0x79, 0x9B, 0xC5,
            0x84, 0xDA, 0x38, 0x66, 0xE5, 0xBB, 0x59, 0x07, 0xDB, 0x85, 0x67,
            0x39, 0xBA, 0xE4, 0x06, 0x58, 0x19, 0x47, 0xA5, 0xFB, 0x78, 0x26,
            0xC4, 0x9A, 0x65, 0x3B, 0xD9, 0x87, 0x04, 0x5A, 0xB8, 0xE6, 0xA7,
            0xF9, 0x1B, 0x45, 0xC6, 0x98, 0x7A, 0x24, 0xF8, 0xA6, 0x44, 0x1A,
            0x99, 0xC7, 0x25, 0x7B, 0x3A, 0x64, 0x86, 0xD8, 0x5B, 0x05, 0xE7,
            0xB9, 0x8C, 0xD2, 0x30, 0x6E, 0xED, 0xB3, 0x51, 0x0F, 0x4E, 0x10,
            0xF2, 0xAC, 0x2F, 0x71, 0x93, 0xCD, 0x11, 0x4F, 0xAD, 0xF3, 0x70,
            0x2E, 0xCC, 0x92, 0xD3, 0x8D, 0x6F, 0x31, 0xB2, 0xEC, 0x0E, 0x50,
            0xAF, 0xF1, 0x13, 0x4D, 0xCE, 0x90, 0x72, 0x2C, 0x6D, 0x33, 0xD1,
            0x8F, 0x0C, 0x52, 0xB0, 0xEE, 0x32, 0x6C, 0x8E, 0xD0, 0x53, 0x0D,
            0xEF, 0xB1, 0xF0, 0xAE, 0x4C, 0x12, 0x91, 0xCF, 0x2D, 0x73, 0xCA,
            0x94, 0x76, 0x28, 0xAB, 0xF5, 0x17, 0x49, 0x08, 0x56, 0xB4, 0xEA,
            0x69, 0x37, 0xD5, 0x8B, 0x57, 0x09, 0xEB, 0xB5, 0x36, 0x68, 0x8A,
            0xD4, 0x95, 0xCB, 0x29, 0x77, 0xF4, 0xAA, 0x48, 0x16, 0xE9, 0xB7,
            0x55, 0x0B, 0x88, 0xD6, 0x34, 0x6A, 0x2B, 0x75, 0x97, 0xC9, 0x4A,
            0x14, 0xF6, 0xA8, 0x74, 0x2A, 0xC8, 0x96, 0x15, 0x4B, 0xA9, 0xF7,
            0xB6, 0xE8, 0x0A, 0x54, 0xD7, 0x89, 0x6B, 0x35 };

    public short calc_pkt55_hdr_checksum(byte seed, byte[] packet,
            int plength) {
        short chksum = seed;
        for (int i = 0; i < plength; i++) {
            chksum = arr_2A103[((packet[i] ^ chksum) & 0xFF)];
        }
        return chksum;
    }

    static int[] crc = new int[] { 0x0000, 0x1189, 0x2312, 0x329b, 0x4624,
            0x57ad, 0x6536, 0x74bf, 0x8c48, 0x9dc1, 0xaf5a, 0xbed3, 0xca6c,
            0xdbe5, 0xe97e, 0xf8f7, 0x1081, 0x0108, 0x3393, 0x221a, 0x56a5,
            0x472c, 0x75b7, 0x643e, 0x9cc9, 0x8d40, 0xbfdb, 0xae52, 0xdaed,
            0xcb64, 0xf9ff, 0xe876, 0x2102, 0x308b, 0x0210, 0x1399, 0x6726,
            0x76af, 0x4434, 0x55bd, 0xad4a, 0xbcc3, 0x8e58, 0x9fd1, 0xeb6e,
            0xfae7, 0xc87c, 0xd9f5, 0x3183, 0x200a, 0x1291, 0x0318, 0x77a7,
            0x662e, 0x54b5, 0x453c, 0xbdcb, 0xac42, 0x9ed9, 0x8f50, 0xfbef,
            0xea66, 0xd8fd, 0xc974, 0x4204, 0x538d, 0x6116, 0x709f, 0x0420,
            0x15a9, 0x2732, 0x36bb, 0xce4c, 0xdfc5, 0xed5e, 0xfcd7, 0x8868,
            0x99e1, 0xab7a, 0xbaf3, 0x5285, 0x430c, 0x7197, 0x601e, 0x14a1,
            0x0528, 0x37b3, 0x263a, 0xdecd, 0xcf44, 0xfddf, 0xec56, 0x98e9,
            0x8960, 0xbbfb, 0xaa72, 0x6306, 0x728f, 0x4014, 0x519d, 0x2522,
            0x34ab, 0x0630, 0x17b9, 0xef4e, 0xfec7, 0xcc5c, 0xddd5, 0xa96a,
            0xb8e3, 0x8a78, 0x9bf1, 0x7387, 0x620e, 0x5095, 0x411c, 0x35a3,
            0x242a, 0x16b1, 0x0738, 0xffcf, 0xee46, 0xdcdd, 0xcd54, 0xb9eb,
            0xa862, 0x9af9, 0x8b70, 0x8408, 0x9581, 0xa71a, 0xb693, 0xc22c,
            0xd3a5, 0xe13e, 0xf0b7, 0x0840, 0x19c9, 0x2b52, 0x3adb, 0x4e64,
            0x5fed, 0x6d76, 0x7cff, 0x9489, 0x8500, 0xb79b, 0xa612, 0xd2ad,
            0xc324, 0xf1bf, 0xe036, 0x18c1, 0x0948, 0x3bd3, 0x2a5a, 0x5ee5,
            0x4f6c, 0x7df7, 0x6c7e, 0xa50a, 0xb483, 0x8618, 0x9791, 0xe32e,
            0xf2a7, 0xc03c, 0xd1b5, 0x2942, 0x38cb, 0x0a50, 0x1bd9, 0x6f66,
            0x7eef, 0x4c74, 0x5dfd, 0xb58b, 0xa402, 0x9699, 0x8710, 0xf3af,
            0xe226, 0xd0bd, 0xc134, 0x39c3, 0x284a, 0x1ad1, 0x0b58, 0x7fe7,
            0x6e6e, 0x5cf5, 0x4d7c, 0xc60c, 0xd785, 0xe51e, 0xf497, 0x8028,
            0x91a1, 0xa33a, 0xb2b3, 0x4a44, 0x5bcd, 0x6956, 0x78df, 0x0c60,
            0x1de9, 0x2f72, 0x3efb, 0xd68d, 0xc704, 0xf59f, 0xe416, 0x90a9,
            0x8120, 0xb3bb, 0xa232, 0x5ac5, 0x4b4c, 0x79d7, 0x685e, 0x1ce1,
            0x0d68, 0x3ff3, 0x2e7a, 0xe70e, 0xf687, 0xc41c, 0xd595, 0xa12a,
            0xb0a3, 0x8238, 0x93b1, 0x6b46, 0x7acf, 0x4854, 0x59dd, 0x2d62,
            0x3ceb, 0x0e70, 0x1ff9, 0xf78f, 0xe606, 0xd49d, 0xc514, 0xb1ab,
            0xa022, 0x92b9, 0x8330, 0x7bc7, 0x6a4e, 0x58d5, 0x495c, 0x3de3,
            0x2c6a, 0x1ef1, 0x0f78 };

    public int calc_pkt55_checksum(short[] packet, byte[] bPacket,
            short plength) {
        int v = 0x3692; //  # P3
        //        int vx = 0x3692; //  # P3
        for (int i = 0; i < plength; i++) {
            int vv = v >> 8;
            //            int vvx = vx >> 8;
            //            System.out.println("vv " + vv + " v " + v
            //                    + " ((packet[i] ^ v) & 0xFF) " + ((packet[i] ^ v) & 0xFF));
            //            System.out.println("vvx " + vvx + " vx " + vx
            //                    + " ((packet[i] ^ v) & 0xFF) " + ((packet[i] ^ v) & 0xFF));
            v = vv ^ crc[((packet[i] ^ v) & 0xFF)];
            //            vx = vvx ^ crc[((bPacket[i] ^ vx) & 0xFF)];
        }

        //        if (v != vx) {
        //            if (Persist.EXPERIMENTAL_DEV) {
        //                System.out.println(" V " + v);
        //            }
        //        }
        return v;
    }

    protected int calc_checksum(MappedByteBuffer memory, long start,
            short plength) {
        int v = 0x3692; //  # P3
        for (int i = 0; i < plength; i++) {
            int vv = v >> 8;
            //            System.out.println("vv " + vv + " v " + v
            //                    + " ((packet[i] ^ v) & 0xFF) " + ((packet[i] ^ v) & 0xFF));
            //            System.out.println("vvx " + vvx + " vx " + vx
            //                    + " ((packet[i] ^ v) & 0xFF) " + ((packet[i] ^ v) & 0xFF));
            v = vv ^ crc[((memory.get((int) (start + i)) ^ v) & 0xFF)];
        }
        return v;
    }

    public double getCRCRatio() {
        double ratio = (double) Corrupted.getNum(Corrupted.Type.CRC)
                / (double) numRecs;
        return ratio;
    }

    public double getErrorRatio(Type _type) {
        switch (_type) {
        case CRC:
            return (double) Corrupted.getNum(Corrupted.Type.CRC)
                    / (double) numRecs;
        case Other:
            return (double) Corrupted.getNum(Corrupted.Type.Other)
                    / (double) numRecs;
        default:
            return 0.0;

        }
    }

}
