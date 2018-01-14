/* Corrupted class

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

public class Corrupted extends Exception {

    private static final long serialVersionUID = 1L;

    public enum Type {
        CRC, Other
    }

    Type type;

    static int numCRC = 0;

    static int numOther = 0;

    public static void reset() {
        numCRC = 0;
        numOther = 0;
    }

    long tickNo = 0;

    public long filePos = 0;

    public Corrupted(long _tickNo, long _filePos) {
        tickNo = _tickNo;
        filePos = _filePos;
        type = Type.Other;
        numOther++;
    }

    public Corrupted(long _tickNo, long _filePos, Type _type) {
        tickNo = _tickNo;
        filePos = _filePos;
        type = _type;
        if (type == Type.CRC) {
            numCRC++;
        } else {
            numOther++;
        }
    }

    public static int getNum(Type _type) {
        switch (_type) {
        case CRC:
            return numCRC;
        case Other:
            return numOther;
        default:
            break;
        }
        return 0;
    }

    public Corrupted() {

    }

    public String toString() {
        return "Partial or missing record at or near tickNo " + tickNo
                + ", file Position " + filePos;
    }

    public long getFilePos() {
        return filePos;
    }

}
