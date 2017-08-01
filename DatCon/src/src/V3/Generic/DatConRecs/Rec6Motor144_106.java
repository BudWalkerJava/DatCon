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

public class Rec6Motor144_106 extends Record {

    public static Rec6Motor144_106 current = null;

    public boolean valid = false;

    public short rfPPM = 0;

    public short lfPPM = 0;

    public short lsPPM = 0;

    public short lbPPM = 0;

    public short rbPPM = 0;

    public short rsPPM = 0;

    public float rfVolts = 0;

    public float lfVolts = 0;

    public float lsVolts = 0;

    public float lbVolts = 0;

    public float rbVolts = 0;

    public float rsVolts = 0;

    public float rfPWM = 0;

    public float lfPWM = 0;

    public float lsPWM = 0;

    public float lbPWM = 0;

    public float rbPWM = 0;

    public float rsPWM = 0;

    public short rfSpeed = 0;

    public short lfSpeed = 0;

    public short lsSpeed = 0;

    public short lbSpeed = 0;

    public short rbSpeed = 0;

    public short rsSpeed = 0;

    public short rfLoad = 0;

    public short lfLoad = 0;

    public short lsLoad = 0;

    public short lbLoad = 0;

    public short rbLoad = 0;

    public short rsLoad = 0;

    double thrustTheta = 0.9;

    private short rfTemp = 0;

    private short lfTemp = 0;

    private short lsTemp = 0;

    private short lbTemp = 0;

    private short rbTemp = 0;

    private short rsTemp = 0;

    public short rfStatus = 0;

    public short lfStatus = 0;

    public short lsStatus = 0;

    public short lbStatus = 0;

    public short rbStatus = 0;

    public short rsStatus = 0;

    public Rec6Motor144_106(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 144;
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
        lfStatus = payloadBB.get(0);
        lfLoad = payloadBB.getShort(24);
        lfSpeed = payloadBB.getShort(26);
        lfVolts = ((float) payloadBB.getShort(28)) / 10.0f;
        lfTemp = payloadBB.getShort(30);
        lfPPM = payloadBB.getShort(32);
        lfPWM = ((float) payloadBB.getShort(34)) / 10.0f;
        //
        lsStatus = payloadBB.get(0);
        lsLoad = payloadBB.getShort(47);
        lsSpeed = payloadBB.getShort(49);
        lsVolts = ((float) payloadBB.getShort(51)) / 10.0f;
        lsTemp = payloadBB.getShort(30);
        lsPPM = payloadBB.getShort(55);
        lsPWM = ((float) payloadBB.getShort(57)) / 10.0f;
        //
        lbStatus = payloadBB.get(0);
        lbLoad = payloadBB.getShort(70);
        lbSpeed = payloadBB.getShort(72);
        lbVolts = ((float) payloadBB.getShort(74)) / 10.0f;
        lbTemp = payloadBB.getShort(30);
        lbPPM = payloadBB.getShort(78);
        lbPWM = ((float) payloadBB.getShort(80)) / 10.0f;

        rbStatus = payloadBB.get(0);
        rbLoad = payloadBB.getShort(93);
        rbSpeed = payloadBB.getShort(95);
        rbVolts = ((float) payloadBB.getShort(97)) / 10.0f;
        rbTemp = payloadBB.getShort(30);
        rbPPM = payloadBB.getShort(101);
        rbPWM = ((float) payloadBB.getShort(103)) / 10.0f;

        rsStatus = payloadBB.get(0);
        rsLoad = payloadBB.getShort(116);
        rsSpeed = payloadBB.getShort(118);
        rsVolts = ((float) payloadBB.getShort(120)) / 10.0f;
        rsTemp = payloadBB.getShort(30);
        rsPPM = payloadBB.getShort(124);
        rsPWM = ((float) payloadBB.getShort(126)) / 10.0f;

        //        thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed, lfSpeed);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(rfStatus, AxesAndSigs.motorStatusSig, "RFront", lineT,
                    valid);
            printCsvValue(lfStatus, AxesAndSigs.motorStatusSig, "LFront", lineT,
                    valid);
            printCsvValue(lsStatus, AxesAndSigs.motorStatusSig, "LSide", lineT,
                    valid);
            printCsvValue(lbStatus, AxesAndSigs.motorStatusSig, "LBack", lineT,
                    valid);
            printCsvValue(rbStatus, AxesAndSigs.motorStatusSig, "RBack", lineT,
                    valid);
            printCsvValue(rsStatus, AxesAndSigs.motorStatusSig, "RSide", lineT,
                    valid);

            printCsvValue(rfSpeed, AxesAndSigs.motorSpeedSig, "RFront", lineT,
                    valid);
            printCsvValue(lfSpeed, AxesAndSigs.motorSpeedSig, "LFront", lineT,
                    valid);
            printCsvValue(lsSpeed, AxesAndSigs.motorSpeedSig, "LSide", lineT,
                    valid);
            printCsvValue(lbSpeed, AxesAndSigs.motorSpeedSig, "LBack", lineT,
                    valid);
            printCsvValue(rbSpeed, AxesAndSigs.motorSpeedSig, "RBack", lineT,
                    valid);
            printCsvValue(rsSpeed, AxesAndSigs.motorSpeedSig, "RSide", lineT,
                    valid);

            printCsvValue(rfVolts, AxesAndSigs.motorVoltsSig, "RFront", lineT,
                    valid);
            printCsvValue(lfVolts, AxesAndSigs.motorVoltsSig, "LFront", lineT,
                    valid);
            printCsvValue(lsVolts, AxesAndSigs.motorVoltsSig, "LSide", lineT,
                    valid);
            printCsvValue(lbVolts, AxesAndSigs.motorVoltsSig, "LBack", lineT,
                    valid);
            printCsvValue(rbVolts, AxesAndSigs.motorVoltsSig, "RBack", lineT,
                    valid);
            printCsvValue(rsVolts, AxesAndSigs.motorVoltsSig, "RSide", lineT,
                    valid);

            printCsvValue(rfTemp, AxesAndSigs.motorEscTempSig, "RFront", lineT,
                    valid);
            printCsvValue(lfTemp, AxesAndSigs.motorEscTempSig, "LFront", lineT,
                    valid);
            printCsvValue(lsTemp, AxesAndSigs.motorEscTempSig, "LSide", lineT,
                    valid);
            printCsvValue(lbTemp, AxesAndSigs.motorEscTempSig, "LBack", lineT,
                    valid);
            printCsvValue(rbTemp, AxesAndSigs.motorEscTempSig, "RBack", lineT,
                    valid);
            printCsvValue(rsTemp, AxesAndSigs.motorEscTempSig, "RSide", lineT,
                    valid);

            printCsvValue(rfPPM, AxesAndSigs.motorPPMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPPM, AxesAndSigs.motorPPMSig, "LFront", lineT,
                    valid);
            printCsvValue(lsPPM, AxesAndSigs.motorPPMSig, "LSide", lineT,
                    valid);
            printCsvValue(lbPPM, AxesAndSigs.motorPPMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPPM, AxesAndSigs.motorPPMSig, "RBack", lineT,
                    valid);
            printCsvValue(rsPPM, AxesAndSigs.motorPPMSig, "RSide", lineT,
                    valid);

            printCsvValue(rfPWM, AxesAndSigs.motorPWMSig, "RFront", lineT,
                    valid);
            printCsvValue(lfPWM, AxesAndSigs.motorPWMSig, "LFront", lineT,
                    valid);
            printCsvValue(lsPWM, AxesAndSigs.motorPWMSig, "LSide", lineT,
                    valid);
            printCsvValue(lbPWM, AxesAndSigs.motorPWMSig, "LBack", lineT,
                    valid);
            printCsvValue(rbPWM, AxesAndSigs.motorPWMSig, "RBack", lineT,
                    valid);
            printCsvValue(rsPWM, AxesAndSigs.motorPWMSig, "RSide", lineT,
                    valid);

            printCsvValue(rfLoad, AxesAndSigs.motorCurrentSig, "RFront", lineT,
                    valid);
            printCsvValue(lfLoad, AxesAndSigs.motorCurrentSig, "LFront", lineT,
                    valid);
            printCsvValue(lsLoad, AxesAndSigs.motorCurrentSig, "LSide", lineT,
                    valid);
            printCsvValue(lbLoad, AxesAndSigs.motorCurrentSig, "LBack", lineT,
                    valid);
            printCsvValue(rbLoad, AxesAndSigs.motorCurrentSig, "RBack", lineT,
                    valid);
            printCsvValue(rsLoad, AxesAndSigs.motorCurrentSig, "RSide", lineT,
                    valid);

            //            printCsvValue(thrustTheta, AxesAndSigs.thrustThetaSig, "", lineT,
            //                    valid);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
