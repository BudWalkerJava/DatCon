/* Record38_106 class

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

package src.V3.Generic.DatConRecs;

import src.DatConRecs.AxesAndSigs;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

// Slightly different from Record144_106
public class RecMotor38_106 extends Record {

    public static RecMotor38_106 current = null;

    public boolean valid = false;

    public short rfPpm = 0;

    public short lfPpm = 0;

    public short lbPpm = 0;

    public short rbPpm = 0;

    public float rfVolts = 0;

    public float lfVolts = 0;

    public float lbVolts = 0;

    public float rbVolts = 0;

    public float rfPwm = 0;

    public float lfPwm = 0;

    public float lbPwm = 0;

    public float rbPwm = 0;

    public short rfSpeed = 0;

    public short lfSpeed = 0;

    public short lbSpeed = 0;

    public short rbSpeed = 0;

    public short rfLoad = 0;

    public short lfLoad = 0;

    public short lbLoad = 0;

    public short rbLoad = 0;

    private double thrustTheta;

    public RecMotor38_106(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 38;
        _subType = 106;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        rfLoad = payloadBB.getShort(1);
        rfSpeed = payloadBB.getShort(3);
        rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
        rfPpm = payloadBB.getShort(9);
        rfPwm = ((float) payloadBB.getShort(11)) / 10.0f;
        //
        lfLoad = payloadBB.getShort(22);
        lfSpeed = payloadBB.getShort(24);
        lfVolts = ((float) payloadBB.getShort(26)) / 10.0f;
        lfPpm = payloadBB.getShort(30);
        lfPwm = ((float) payloadBB.getShort(32)) / 10.0f;
        //
        lbLoad = payloadBB.getShort(43);
        lbSpeed = payloadBB.getShort(45);
        lbVolts = ((float) payloadBB.getShort(47)) / 10.0f;
        lbPpm = payloadBB.getShort(51);
        lbPwm = ((float) payloadBB.getShort(53)) / 10.0f;
        //
        rbLoad = payloadBB.getShort(64);
        rbSpeed = payloadBB.getShort(66);
        rbVolts = ((float) payloadBB.getShort(68)) / 10.0f;
        rbPpm = payloadBB.getShort(72);
        rbPwm = ((float) payloadBB.getShort(74)) / 10.0f;

        thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed, lfSpeed);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(rfSpeed, AxesAndSigs.motorSpeedSig, "RFront", lineT,
                    valid);
            printCsvValue(lfSpeed, AxesAndSigs.motorSpeedSig, "LFront", lineT,
                    valid);
            printCsvValue(lbSpeed, AxesAndSigs.motorSpeedSig, "LBack", lineT,
                    valid);
            printCsvValue(rbSpeed, AxesAndSigs.motorSpeedSig, "RBack", lineT,
                    valid);

            printCsvValue(rfVolts, AxesAndSigs.motorVoltsSig, "RFront", lineT,
                    valid);
            printCsvValue(lfVolts, AxesAndSigs.motorVoltsSig, "LFront", lineT,
                    valid);
            printCsvValue(lbVolts, AxesAndSigs.motorVoltsSig, "LBack", lineT,
                    valid);
            printCsvValue(rbVolts, AxesAndSigs.motorVoltsSig, "RBack", lineT,
                    valid);

            printCsvValue(rfPpm, AxesAndSigs.motorPPMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPpm, AxesAndSigs.motorPPMSig, "LFront", lineT,
                    valid);
            printCsvValue(lbPpm, AxesAndSigs.motorPPMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPpm, AxesAndSigs.motorPPMSig, "RBack", lineT,
                    valid);

            printCsvValue(rfPwm, AxesAndSigs.motorPWMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPwm, AxesAndSigs.motorPWMSig, "LFront", lineT,
                    valid);
            printCsvValue(lbPwm, AxesAndSigs.motorPWMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPwm, AxesAndSigs.motorPWMSig, "RBack", lineT,
                    valid);

            printCsvValue(rfLoad, AxesAndSigs.motorCurrentSig, "RFront", lineT,
                    valid);
            printCsvValue(lfLoad, AxesAndSigs.motorCurrentSig, "LFront", lineT,
                    valid);
            printCsvValue(lbLoad, AxesAndSigs.motorCurrentSig, "LBack", lineT,
                    valid);
            printCsvValue(rbLoad, AxesAndSigs.motorCurrentSig, "RBack", lineT,
                    valid);

            printCsvValue(thrustTheta, AxesAndSigs.thrustThetaSig, "", lineT,
                    valid);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
