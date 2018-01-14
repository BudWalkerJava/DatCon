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

package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatHeader.AcType;
import src.Files.DatConLog;

public class RecMotor185_10090 extends Motor {

    public RecMotor185_10090(ConvertDat convertDat) {
        super(convertDat, 10090, 185);
        if (convertDat._datFile.acType == AcType.M600) {
            setNumNotors(6);
        } else {
            setNumNotors(4);
        }
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            if (numMotors == 4) {
                valid = true;
                rfStatus = payloadBB.get(0);
                rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
                rfSpeed = payloadBB.getShort(3);
                rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
                rfTemp = payloadBB.getShort(7);
                rfPPM = payloadBB.getShort(9);
                rfPWM = ((float) payloadBB.getShort(11)) / 10.0f;
                //
                lfStatus = payloadBB.get(23);
                lfCurrent = ((float) payloadBB.getShort(24)) / 100.0f;
                lfSpeed = payloadBB.getShort(26);
                lfVolts = ((float) payloadBB.getShort(28)) / 10.0f;
                lfTemp = payloadBB.getShort(30);
                lfPPM = payloadBB.getShort(32);
                lfPWM = ((float) payloadBB.getShort(34)) / 10.0f;
                //
                lbStatus = payloadBB.get(46);
                lbCurrent = ((float) payloadBB.getShort(47)) / 100.0f;
                lbSpeed = payloadBB.getShort(49);
                lbVolts = ((float) payloadBB.getShort(51)) / 10.0f;
                lbTemp = payloadBB.getShort(53);
                lbPPM = payloadBB.getShort(55);
                lbPWM = ((float) payloadBB.getShort(57)) / 10.0f;
                //
                rbStatus = payloadBB.get(69);
                rbCurrent = ((float) payloadBB.getShort(70)) / 100.0f;
                rbSpeed = payloadBB.getShort(72);
                rbVolts = ((float) payloadBB.getShort(74)) / 10.0f;
                rbTemp = payloadBB.getShort(76);
                rbPPM = payloadBB.getShort(78);
                rbPWM = ((float) payloadBB.getShort(80)) / 10.0f;

                thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed,
                        lfSpeed);
                recordPower();
            } else if (numMotors == 6) {
                valid = true;
                rfStatus = payloadBB.get(0);
                rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
                rfSpeed = payloadBB.getShort(3);
                rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
                rfTemp = payloadBB.getShort(7);
                rfPPM = payloadBB.getShort(9);
                rfPWM = ((float) payloadBB.getShort(11)) / 10.0f;
                //
                lfStatus = payloadBB.get(0);
                lfCurrent = ((float) payloadBB.getShort(24)) / 100.0f;
                lfSpeed = payloadBB.getShort(26);
                lfVolts = ((float) payloadBB.getShort(28)) / 10.0f;
                lfTemp = payloadBB.getShort(30);
                lfPPM = payloadBB.getShort(32);
                lfPWM = ((float) payloadBB.getShort(34)) / 10.0f;
                //
                lsStatus = payloadBB.get(0);
                lsCurrent = ((float) payloadBB.getShort(47)) / 100.0f;
                lsSpeed = payloadBB.getShort(49);
                lsVolts = ((float) payloadBB.getShort(51)) / 10.0f;
                lsTemp = payloadBB.getShort(30);
                lsPPM = payloadBB.getShort(55);
                lsPWM = ((float) payloadBB.getShort(57)) / 10.0f;
                //
                lbStatus = payloadBB.get(0);
                lbCurrent = ((float) payloadBB.getShort(70)) / 100.0f;
                lbSpeed = payloadBB.getShort(72);
                lbVolts = ((float) payloadBB.getShort(74)) / 10.0f;
                lbTemp = payloadBB.getShort(30);
                lbPPM = payloadBB.getShort(78);
                lbPWM = ((float) payloadBB.getShort(80)) / 10.0f;

                rbStatus = payloadBB.get(0);
                rbCurrent = ((float) payloadBB.getShort(93)) / 100.0f;
                rbSpeed = payloadBB.getShort(95);
                rbVolts = ((float) payloadBB.getShort(97)) / 10.0f;
                rbTemp = payloadBB.getShort(30);
                rbPPM = payloadBB.getShort(101);
                rbPWM = ((float) payloadBB.getShort(103)) / 10.0f;

                rsStatus = payloadBB.get(0);
                rsCurrent = ((float) payloadBB.getShort(116)) / 100.0f;
                rsSpeed = payloadBB.getShort(118);
                rsVolts = ((float) payloadBB.getShort(120)) / 10.0f;
                rsTemp = payloadBB.getShort(30);
                rsPPM = payloadBB.getShort(124);
                rsPWM = ((float) payloadBB.getShort(126)) / 10.0f;
            }

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
