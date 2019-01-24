package src.Files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DJIAssistantFile {

    static final int hearerLen = 283;

    static final int bufSize = 1000;

    public static boolean isDJIDat(File file) throws FileNotFoundException {
        byte arra[] = new byte[256];
        FileInputStream bfr = new FileInputStream(file);
        try {
            bfr.read(arra, 0, 256);
            if ((arra[0] == (byte) 0x78) && (arra[1] == (byte) 0x9C)
            /* && (arra[2] == (byte) 0xE4) */) {
                bfr.close();
                return true;
            }
        } catch (IOException e) {
        } finally {
            try {
                bfr.close();
            } catch (IOException e) {
            }
        }
        return false;
    }

    public class ExtractResult {
        boolean moreThanOne = false;

        File file = null;

        public ExtractResult() {
        }

        public void setFile(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        public boolean moreThanOne() {
            return moreThanOne;
        }

        public void setMoreThanOne(boolean b) {
            moreThanOne = b;
        }

        public boolean none() {
            return (file == null);
        }
    }

    // Extract a single .DAT
    public static ExtractResult extractFirst(String datFileName, String dirName)
            throws NotDatFile {
        File datFile = null;
        BufferedInputStream bufferedIS = null;
        ExtractResult retv = (new DJIAssistantFile()).new ExtractResult();
        byte buffer[] = new byte[bufSize];
        byte header[] = new byte[hearerLen];
        boolean gotDat = false;
        // int len = 0;
        int beginFNRoot = datFileName.lastIndexOf("\\");
        int endFNRoot = datFileName.lastIndexOf(".DAT");
        String datFNRoot = datFileName.substring(beginFNRoot + 1, endFNRoot);
        try {
            InputStream inStream = new FileInputStream(datFileName);
            Inflater inflater = new Inflater();
            InflaterInputStream inflaterInStream = new InflaterInputStream(
                    inStream, inflater, 1000);
            bufferedIS = new BufferedInputStream(inflaterInStream);
            while (!gotDat
                    && bufferedIS.read(header, 0, hearerLen) == hearerLen) {
                long uncompressedFileLength = Util.getUnsignedInt(header, 1);
                int len = 0;
                String flyFileName = Util.getString(header, 7);
                if (flyFileName.indexOf("FLY") == 0) {
                    gotDat = true;
                    datFile = new File(
                            dirName + "/" + datFNRoot + "." + flyFileName);
                    if (datFile.exists()) {
                        datFile.delete();
                    }
                    FileOutputStream flyOS = new FileOutputStream(datFile);
                    boolean done = false;
                    long outFileSize = 0;
                    while (!done) {
                        int bufLength = bufSize;
                        if (outFileSize + bufSize > uncompressedFileLength) {
                            bufLength = (int) (uncompressedFileLength
                                    - outFileSize);
                            done = true;
                        }
                        len = bufferedIS.read(buffer, 0, bufLength);
                        flyOS.write(buffer, 0, len);
                        outFileSize += len;
                    }
                    retv.setFile(datFile);
                    flyOS.close();
                } else {
                    for (int i = 0; i < uncompressedFileLength; i++) {
                        bufferedIS.read();
                    }
                }
            }
            if (!gotDat) {
                return retv;
            }
            while (bufferedIS.read(header, 0, hearerLen) == hearerLen) {
                long uncompressedFileLength = Util.getUnsignedInt(header, 1);
                String flyFileName = Util.getString(header, 7);
                if (flyFileName.indexOf("FLY") == 0) {
                    retv.setMoreThanOne(true);
                    return retv;
                }
                for (int i = 0; i < uncompressedFileLength; i++) {
                    bufferedIS.read();
                }
            }
        } catch (IOException e) {
            DatConLog.Exception(e);
        } finally {
            try {
                if (bufferedIS != null) {
                    bufferedIS.close();
                }
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
        return retv;
    }
}
