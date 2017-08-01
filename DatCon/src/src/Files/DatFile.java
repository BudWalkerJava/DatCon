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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

import src.Files.DatHeader.AcType;
import src.V1.Files.DatFileV1;
import src.V3.Files.DatFileV3;
import src.apps.DatCon;

public class DatFile {

    protected final static int headerLength = 10;

    protected final static int chksumLength = 2;

    protected final static int startOfRecords = 128;

    protected MappedByteBuffer memory = null;

    protected String acTypeName = "";

    protected long filePos = 0;

    protected File file = null;

    protected FileInputStream inputStream = null;

    protected FileChannel _channel = null;

    protected long fileLength = 0;

    public static String firmwareDate = "";

    protected FileEnd _fileEnd = new FileEnd();

    protected int numCorrupted = 0;

    protected AnalyzeDatResults results = null;

    public long startOfRecord = 0;

    public long lowestTickNo = -1;

    public long highestTickNo = -1;

    public long firstMotorStartTick = 0;

    public long lastMotorStopTick = -1;

    public long flightStartTick = -1;

    public long gpsLockTick = -1;

    public DatHeader.AcType acType = DatHeader.AcType.UNKNOWN;

    public enum DatVersion {
        V1, V3, DJIASSISTANT, UNKNOWN
    }

    protected long lastRecordTickNo = 0;

    public static DecimalFormat timeFormat = new DecimalFormat("###.000",
            new DecimalFormatSymbols(Locale.US));

    double clockRate = 600;

    private DatHeader datHeader;

    protected HashSet<TypeSubType> typeSubTypes = new HashSet<TypeSubType>();

    protected DatFile(String fileName) throws IOException, NotDatFile {
        this(new File(fileName));
    }

    public static DatFile createDatFile(String datFileName)
            throws NotDatFile, IOException {
        byte arra[] = new byte[256];
        FileInputStream bfr = new FileInputStream(new File(datFileName));
        bfr.read(arra, 0, 256);
        bfr.close();
        if (!(new String(arra, 16, 5).equals("BUILD"))) {
            throw new NotDatFile();
        }
        if ((new String(arra, 242, 10).equals("DJI_LOG_V3"))) {
            return (new DatFileV3(datFileName));
        }
        return new DatFileV1(datFileName);
    }

    public static DatFile createDatFile(String datFileName, final DatCon datCon)
            throws NotDatFile, IOException {
        if (DJIAssistantFile.isDJIDat(new File(datFileName))) {
            if (datCon.autoTransDJIAFiles) {
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
        //AcType acType = datHeader.getAcType();
        acTypeName = DatHeader.toString(acType);
    }

    public DatFile() {
    }

    public DatFile(DatHeader.AcType acType) {
        this.acType = acType;
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
        throw new RuntimeException("DatFile: mpreAnalyze() called");
    }

    public String getFirmwareDate() {
        return datHeader.getFWDate();
    }

    public String getACTypeString() {
        return acTypeName;
    }

    public HashSet<TypeSubType> getTypeSubTypes() {
        return typeSubTypes;
    }

    public TypeSubType getTypeSubType(int subType) {
        TypeSubType retv = null;
        Iterator<TypeSubType> iter = typeSubTypes.iterator();
        while (iter.hasNext()) {
            TypeSubType tst = iter.next();
            if (tst.getSubType() == subType) {
                if (retv != null) {
                    return null;
                }
                retv = tst;
            }
        }
        return retv;
    }

    public boolean typeSubTypeExists(int t, int st) {
        Iterator<TypeSubType> iter = typeSubTypes.iterator();
        while (iter.hasNext()) {
            TypeSubType tst = iter.next();
            if (tst.getType() == t && tst.getSubType() == st) {
                return true;
            }
        }
        return false;
    }
}
