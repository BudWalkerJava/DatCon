/* Payload class

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
package src.DatConRecs;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import src.Files.DatFile;
import src.Files.FileEnd;
import src.Files.Quaternion;
import src.Files.RollPitchYaw;

public class Payload {

    byte xorArray[] = null;

    ByteBuffer BB = null;

    int length = 0;

    // long filePos = 0;

    //int recType = 0;

    public long tickNo = 0;

    public long start = 0;

    int type = 0;

    public DatFile datFile = null;

    public Payload(DatFile df, long _start, int _length, int __type,
            long _tickNo) throws IOException, FileEnd {
        datFile = df;
        start = _start;
        length = _length;
        tickNo = _tickNo;
        type = __type;
        if (start + length - 1 >= datFile.getLength()) {
            throw (new FileEnd());
        }
        xorArray = new byte[length];
        BB = ByteBuffer.wrap(xorArray).order(ByteOrder.LITTLE_ENDIAN);

        byte xorKey = (byte) (tickNo % 256);
        for (int i = 0; i < length; i++) {
            xorArray[i] = (byte) (datFile.getByte(start + i) ^ xorKey);
        }
    }

    public long getStart() {
        return start;
    }

    public ByteBuffer getBB() {
        return BB;
    }

    public int getType() {
        return type;
    }

    public void printBB(PrintStream printStream) {
        printStream.print("Rec" + "_" + type + " ");
        printStream.print("FilePos = " + start + " ");
        if (tickNo >= 0)
            printStream.print("TickNo = " + tickNo);
        printStream.println("");
        for (int i = 0; i < length; i++) {
            printStream
                    .print(i + ":" + String.format("%02X", (0xff & BB.get(i)))
                            + ":" + String.format("%C", (0xff & BB.get(i)))
                            + ":" + (0xff & BB.get(i)));
            if (i < length - 1) {
                printStream.print(":Shrt " + BB.getShort(i) + " :UShrt "
                        + getUnsignedShort(i));
            }
            if (i < length - 3) {
                printStream.print(" :I " + BB.getInt(i) + " :UI "
                        + getUnsignedInt(i) + " :F " + BB.getFloat(i));
            }
            if (i < length - 7) {
                printStream.print(
                        " :L " + BB.getLong(i) + " :D " + BB.getDouble(i));
            }
            printStream.println("");
        }
    }

    public void printBBAsString(PrintStream printStream) {
        for (int i = 0; i < length; i++) {
            printStream.print(String.format("%c", (0xff & BB.get(i))));
        }
        printStream.println("");
    }

    public void printBBAsBytes(PrintStream printStream) {
        for (int i = 0; i < length; i++) {
            printStream.print(": " + String.format("%02X", (0xff & BB.get(i))));
        }
        printStream.println("");
    }

    public void printBBAsShort(PrintStream printStream) {
        for (int i = 0; i < length; i++) {
            printStream.print(":" + (0xff & BB.get(i)));
        }
        printStream.println("");
    }

    public short getByte(int index) {
        return BB.get(index);
    }

    public short getUnsignedByte(int index) {
        return (short) (0xff & BB.get(index));
    }

    public long getUnsignedInt(int index) {
        return (long) (0xff & BB.get(index))
                + (256 * (long) (0xff & BB.get(index + 1)))
                + (65536 * (long) (0xff & BB.get(index + 2)))
                + (65536 * 256 * (long) (0xff & BB.get(index + 3)));
    }

    public int getUnsignedShort(int index) {
        return (int) (0xff & BB.get(index))
                + (256 * (int) (0xff & BB.get(index + 1)));
    }

    public float getFloat(int index) {
        return BB.getFloat(index);
    }

    public short getShort(int index) {
        return BB.getShort(index);
    }

    public int getInt(int index) {
        return BB.getInt(index);
    }

    public double getDouble(int index) {
        return BB.getDouble(index);
    }

    public long getTickNo() {
        return tickNo;
    }

    public void lookforQuat() {
        //        if (recType == 44 || recType == 207 || recType == 225 || recType == 187)
        //            return;
        for (int i = 0; i < length - 16; i++) {
            double X1 = BB.getFloat(i);
            double X2 = BB.getFloat(i + 4);
            double X3 = BB.getFloat(i + 8);
            double X4 = BB.getFloat(i + 12);
            if (X1 != 0.0 && X2 != 0.0 && X3 != 0.0 && X4 != 0.0) {
                double test = Math.sqrt(X1 * X1 + X2 * X2 + X3 * X3 + X4 * X4);
                if (Math.abs(test - 1.0) < 1.0E-2) {
                    System.out.print("Rec" + "_" + type);
                    System.out.print("TickNo = " + tickNo);
                    System.out.print(":offset " + i);
                    Quaternion q = new Quaternion(X1, X2, X3, X4);
                    System.out.println(
                            " RPW " + (new RollPitchYaw(q)).toDegString());
                }
            }
        }
    }

    public String getString() {
        byte bytes[] = new byte[length];
        int l = 0;
        byte B = 0x00;
        for (int i = 0; i < length; i++) {
            B = BB.get(i);
            if (B == 0x00) {
                l = i;
                break;
            }
            bytes[i] = B;
        }
        String retv = new String(bytes, 0, l);
        return retv;
    }

    public String getAsString() {
        byte bytes[] = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = BB.get(i);
        }
        String retv = new String(bytes, 0, length - 1);
        return retv;
    }

    public String getCleanString() {
        byte bytes[] = new byte[length];
        int l = 0;
        byte B = 0x00;
        for (int i = 0; i < length; i++) {
            B = BB.get(i);
            if (B == 0x00) {
                l = i;
                break;
            } else if (B >= 0x20 && B <= 0x7F) {
                bytes[l++] = B;
            } else if (B == '\r' || B == '\n') {
                bytes[l++] = '|';
            } else if (B == '\t') {
                bytes[l++] = ' ';
            } else {
                bytes[l++] = '.';
            }
        }
        String retv = new String(bytes, 0, l);
        return retv;
    }

    //    public byte[] getEndBytes(int start) {
    //        byte retv[] = null;
    //        if (start < length) {
    //            retv = new byte[length - start];
    //            for (int i = 0; i < length - start; i++) {
    //                retv[i] = BB.get(i + start);
    //            }
    //        }
    //        return retv;
    //    }

    public String getStrings() {
        byte bytes[] = new byte[length];
        int l = 0;
        byte B = 0x00;
        for (int i = 0; i < length; i++) {
            B = BB.get(i);
            if (B >= 0x20 && B <= 0x7F) {
                bytes[l++] = B;
            } else if (B == 0x00 || B == '\r' || B == '\n') {
                bytes[l++] = '|';
            } else {
                bytes[l++] = '.';
            }
        }
        String retv = new String(bytes, 0, l);
        return retv;
    }

    public void lookForString() {
        int runLength = 0;
        for (int i = 0; i < length; i++) {
            byte b = BB.get(i);
            if (0x20 <= b && b <= 0x7E) {
                runLength++;
            } else {
                if (runLength > 8) {
                    // System.out.print("Rec" + recType + "_" + subType + ":");
                    // System.out.print("FilePos = " + start + ": ");
                    // if (tickNo >= 0)
                    // System.out.print("TickNo = " + tickNo + " ");
                    for (int j = i - runLength; j < i; j++) {
                        System.out
                                .print(String.format("%C", (0xff & BB.get(j))));
                    }
                    System.out.println();
                    // printBB();
                }
                runLength = 0;
            }
        }
    }

    public void printBB() {
        printBB(System.out);
    }

    public void lookForLatLong() {
        for (int i = 0; i < length - 16; i++) {
            double number1 = BB.getDouble(i);
            double number2 = BB.getDouble(i + 8);
            if (
            // !(recType == 42 && subType == 12)
            // && (recType != 207)
            // && !(recType == 11 && subType == 51)
            // && !(recType == 193 && subType == 43)
            // && !(recType == 94 && subType == 51)
            // && (recType != 198)
            // &&
            (((0.8 < number1 && number1 < .81)
                    && (-1.3 < number2 && number2 < -1.2))
                    || ((0.8 < number2 && number2 < .81)
                            && (-1.3 < number1 && number1 < -1.2)))) {
                System.out.print("Rec" + "_" + type + ":");
                System.out.print("Offset " + i + " ");
                System.out.print("FilePos = " + start + " ");
                if (tickNo >= 0)
                    System.out.print("TickNo = " + tickNo);
                System.out.println(" " + number1 + " " + number2);
            }
        }
    }

    public void lookForLatLong(double latRad, double longRad) {
        for (int i = 0; i < length - 16; i++) {
            double number1 = BB.getDouble(i);
            double number2 = BB.getDouble(i + 8);
            if (true
                    //            if ((recType != 207) && (recType != 54) && (recType != 104)
                    //&& (recType != 172)
                    //					&& (recType != 246)
                    //&& (recType != 207) && (Math.abs(number1) > 0.01)
                    && (Math.abs(number2) > 0.01)
                    && (((Math.abs(number1 - latRad) < 0.05)
                            && (Math.abs(number2 - longRad) < 0.05))
                            || ((Math.abs(number1 - longRad) < 0.05)
                                    && (Math.abs(number2 - latRad) < 0.05)))) {
                System.out.print("Rec" + "_" + type + ":");
                System.out.print("Offset " + i + " ");
                System.out.print("FilePos = " + start + " ");
                if (tickNo >= 0)
                    System.out.print("TickNo = " + tickNo);
                System.out.println(" " + number1 + " " + number2 + " lat "
                        + latRad + " long " + longRad);
            }
        }
    }

    public void lookForLatLong(long latDeg, long longDeg) {
        for (int i = 0; i < length - 16; i++) {
            long number1 = BB.getInt(i);
            long number2 = BB.getInt(i + 4);
            if (type == 48 && i == 8 && number1 != 0) {
                //System.out.println("XXX " + number1 + " " + number2);
            }
            if (true
                    //            if ((recType != 207) && (recType != 54) && (recType != 104)
                    //&& (recType != 172)
                    //                                  && (recType != 246)
                    //&& (recType != 207 && subType != 48)
                    && (Math.abs(number1) > 10000000)
                    && (Math.abs(number2) > 10000000)
                    && (((Math.abs(number1 - latDeg) < 10000000)
                            && (Math.abs(number2 - longDeg) < 10000000))
                            || ((Math.abs(number1 - longDeg) < 10000000)
                                    && (Math.abs(
                                            number2 - latDeg) < 10000000)))) {
                System.out.print("Rec" + "_" + type + ":");
                System.out.print("Offset " + i + " ");
                System.out.print("FilePos = " + start + " ");
                if (tickNo >= 0)
                    System.out.print("TickNo = " + tickNo);
                System.out.println(" " + number1 + " " + number2 + " lat "
                        + latDeg + " long " + longDeg);
            }
        }
    }

    public int getLength() {
        return length;
    }

}
