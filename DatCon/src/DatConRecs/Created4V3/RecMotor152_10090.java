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
import src.Files.ConvertDat;

public class RecMotor152_10090 extends Motor {

    public static RecMotor152_10090 current = null;

    double thrustTheta = 0.9;

    public RecMotor152_10090(ConvertDat convertDat) {
        super(convertDat, 10090, 152);

    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            if (convertDat.getNumMotors() == 4) {
                valid = true;
                rfStatus = payloadBB.get(0);
                rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
                rfSpeed = payloadBB.getShort(3);
                rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
                rfTemp = payloadBB.getShort(7);
                rfPPM_recv = payloadBB.getShort(9);
                rfV_out = ((float) payloadBB.getShort(11)) / 10.0f;
                //
                lfStatus = payloadBB.get(19);
                lfCurrent = ((float) payloadBB.getShort(20)) / 100.0f;
                lfSpeed = payloadBB.getShort(22);
                lfVolts = ((float) payloadBB.getShort(24)) / 10.0f;
                lfTemp = payloadBB.getShort(26);
                lfPPM_recv = payloadBB.getShort(28);
                lfV_out = ((float) payloadBB.getShort(30)) / 10.0f;
                //
                lbStatus = payloadBB.get(38);
                lbCurrent = ((float) payloadBB.getShort(39)) / 100.0f;
                lbSpeed = payloadBB.getShort(41);
                lbVolts = ((float) payloadBB.getShort(43)) / 10.0f;
                lbTemp = payloadBB.getShort(45);
                lbPPM_recv = payloadBB.getShort(47);
                lbV_out = ((float) payloadBB.getShort(49)) / 10.0f;
                //
                rbStatus = payloadBB.get(57);
                rbCurrent = ((float) payloadBB.getShort(58)) / 100.0f;
                rbSpeed = payloadBB.getShort(60);
                rbVolts = ((float) payloadBB.getShort(62)) / 10.0f;
                rbTemp = payloadBB.getShort(64);
                rbPPM_recv = payloadBB.getShort(66);
                rbV_out = ((float) payloadBB.getShort(68)) / 10.0f;
                thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed,
                        lfSpeed);
            } else if (convertDat.getNumMotors() == 6) {
                valid = true;
                rfStatus = payloadBB.get(0);
                rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
                rfSpeed = payloadBB.getShort(3);
                rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
                rfTemp = payloadBB.getShort(7);
                rfPPM_recv = payloadBB.getShort(9);
                rfV_out = ((float) payloadBB.getShort(11)) / 10.0f;
                //
                lfStatus = payloadBB.get(19);
                lfCurrent = ((float) payloadBB.getShort(20)) / 100.0f;
                lfSpeed = payloadBB.getShort(22);
                lfVolts = ((float) payloadBB.getShort(24)) / 10.0f;
                lfTemp = payloadBB.getShort(26);
                lfPPM_recv = payloadBB.getShort(28);
                lfV_out = ((float) payloadBB.getShort(30)) / 10.0f;
                //
                lsStatus = payloadBB.get(38);
                lsCurrent = ((float) payloadBB.getShort(39)) / 100.0f;
                lsSpeed = payloadBB.getShort(41);
                lsVolts = ((float) payloadBB.getShort(43)) / 10.0f;
                lsTemp = payloadBB.getShort(45);
                lsPPM_recv = payloadBB.getShort(47);
                lsV_out = ((float) payloadBB.getShort(49)) / 10.0f;
                //
                lbStatus = payloadBB.get(57);
                lbCurrent = ((float) payloadBB.getShort(58)) / 100.0f;
                lbSpeed = payloadBB.getShort(60);
                lbVolts = ((float) payloadBB.getShort(62)) / 10.0f;
                lbTemp = payloadBB.getShort(64);
                lbPPM_recv = payloadBB.getShort(66);
                lbV_out = ((float) payloadBB.getShort(68)) / 10.0f;

                rbStatus = payloadBB.get(76);
                rbCurrent = ((float) payloadBB.getShort(77)) / 100.0f;
                rbSpeed = payloadBB.getShort(79);
                rbVolts = ((float) payloadBB.getShort(81)) / 10.0f;
                rbTemp = payloadBB.getShort(83);
                rbPPM_recv = payloadBB.getShort(85);
                rbV_out = ((float) payloadBB.getShort(87)) / 10.0f;

                rsStatus = payloadBB.get(95);
                rsCurrent = ((float) payloadBB.getShort(96)) / 100.0f;
                rsSpeed = payloadBB.getShort(98);
                rsVolts = ((float) payloadBB.getShort(100)) / 10.0f;
                rsTemp = payloadBB.getShort(102);
                rsPPM_recv = payloadBB.getShort(104);
                rsV_out = ((float) payloadBB.getShort(106)) / 10.0f;
            }

            recordPower();
        } catch (Exception e) {
            RecordException(e);
        }
    }

}
