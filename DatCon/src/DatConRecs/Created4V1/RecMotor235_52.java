/* Record44_52 class

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
package src.DatConRecs.Created4V1;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatHeader.AcType;
import src.Files.Quaternion;
import src.Files.RollPitchYaw;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

// 50 HZ
// length 237
public class RecMotor235_52 extends Record {

    public short rfPWM = 0;

    public short lfPWM = 0;

    public short lbPWM = 0;

    public short rbPWM = 0;

    private boolean valid = false;

    private AcType acType;

    public RecMotor235_52(ConvertDat convertDat) {
        super(convertDat, 52, 235);
        acType = convertDat._datFile.getACType();
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;

        rfPWM = payloadBB.getShort(219);
        lfPWM = payloadBB.getShort(221);
        lbPWM = payloadBB.getShort(223);
        rbPWM = payloadBB.getShort(225);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            if (acType == AcType.P3S) {
                printCsvValue(rfPWM, AxesAndSigs.motorPWMSig, "RFront", lineT,
                        valid);
                printCsvValue(lfPWM, AxesAndSigs.motorPWMSig, "LFront", lineT,
                        valid);
                printCsvValue(lbPWM, AxesAndSigs.motorPWMSig, "LBack", lineT,
                        valid);
                printCsvValue(rbPWM, AxesAndSigs.motorPWMSig, "RBack", lineT,
                        valid);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
