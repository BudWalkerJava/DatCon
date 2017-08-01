/* Record144_106 class

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
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class RecMotor14_106 extends Record {

    public static RecMotor14_106 current = null;

    public boolean valid = false;

    public short rfPPM = 0;

    public short lfPPM = 0;

    public short lbPPM = 0;

    public short rbPPM = 0;

    public float rfVolts = 0;

    public float lfVolts = 0;

    public float lbVolts = 0;

    public float rbVolts = 0;

    public float rfPWM = 0;

    public float lfPWM = 0;

    public float lbPWM = 0;

    public float rbPWM = 0;

    public short rfSpeed = 0;

    public short lfSpeed = 0;

    public short lbSpeed = 0;

    public short rbSpeed = 0;

    public short rfLoad = 0;

    public short lfLoad = 0;

    public short lbLoad = 0;

    public short rbLoad = 0;

    public short rfTemp = 0;

    public short lfTemp = 0;

    public short lbTemp = 0;

    public short rbTemp = 0;

    public short rfStatus = 0;

    public short lfStatus = 0;

    public short lbStatus = 0;

    public short rbStatus = 0;

    double thrustTheta = 0.9;

    public RecMotor14_106(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 14;
        _subType = 106;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        rfStatus = payloadBB.get(0);
        rfLoad = payloadBB.getShort(1);
        rfSpeed = payloadBB.getShort(3);
        rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
        rfTemp = payloadBB.getShort(7);
        rfPPM = payloadBB.getShort(9);
        rfPWM = ((float) payloadBB.getShort(11)) / 10.0f;
        //
        lfStatus = payloadBB.get(19);
        lfLoad = payloadBB.getShort(20);
        lfSpeed = payloadBB.getShort(22);
        lfVolts = ((float) payloadBB.getShort(24)) / 10.0f;
        lfTemp = payloadBB.getShort(26);
        lfPPM = payloadBB.getShort(28);
        lfPWM = ((float) payloadBB.getShort(30)) / 10.0f;
        //
        lbStatus = payloadBB.get(38);
        lbLoad = payloadBB.getShort(39);
        lbSpeed = payloadBB.getShort(41);
        lbVolts = ((float) payloadBB.getShort(43)) / 10.0f;
        lbTemp = payloadBB.getShort(45);
        lbPPM = payloadBB.getShort(47);
        lbPWM = ((float) payloadBB.getShort(49)) / 10.0f;
        //
        rbStatus = payloadBB.get(57);
        rbLoad = payloadBB.getShort(58);
        rbSpeed = payloadBB.getShort(60);
        rbVolts = ((float) payloadBB.getShort(62)) / 10.0f;
        rbTemp = payloadBB.getShort(64);
        rbPPM = payloadBB.getShort(66);
        rbPWM = ((float) payloadBB.getShort(68)) / 10.0f;

        thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed, lfSpeed);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(rfStatus, AxesAndSigs.motorStatusSig, "RFront", lineT,
                    valid);
            printCsvValue(lfStatus, AxesAndSigs.motorStatusSig, "LFront", lineT,
                    valid);
            printCsvValue(lbStatus, AxesAndSigs.motorStatusSig, "LBack", lineT,
                    valid);
            printCsvValue(rbStatus, AxesAndSigs.motorStatusSig, "RBack", lineT,
                    valid);
            
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

            printCsvValue(rfTemp, AxesAndSigs.motorEscTempSig, "RFront", lineT,
                    valid);
            printCsvValue(lfTemp, AxesAndSigs.motorEscTempSig, "LFront", lineT,
                    valid);
            printCsvValue(lbTemp, AxesAndSigs.motorEscTempSig, "LBack", lineT,
                    valid);
            printCsvValue(rbTemp, AxesAndSigs.motorEscTempSig, "RBack", lineT,
                    valid);

            printCsvValue(rfPPM, AxesAndSigs.motorPPMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPPM, AxesAndSigs.motorPPMSig, "LFront", lineT,
                    valid);
            printCsvValue(lbPPM, AxesAndSigs.motorPPMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPPM, AxesAndSigs.motorPPMSig, "RBack", lineT,
                    valid);

            printCsvValue(rfPWM, AxesAndSigs.motorPWMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPWM, AxesAndSigs.motorPWMSig, "LFront", lineT,
                    valid);
            printCsvValue(lbPWM, AxesAndSigs.motorPWMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPWM, AxesAndSigs.motorPWMSig, "RBack", lineT,
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
