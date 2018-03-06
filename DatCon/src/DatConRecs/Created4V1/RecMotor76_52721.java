/* Record218_241 class

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

import src.DatConRecs.Payload;
import src.DatConRecs.Created4V3.Motor;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class RecMotor76_52721 extends Motor {

    public static RecMotor76_52721 current = null;

    public double thrustTheta = 0;

    public RecMotor76_52721(ConvertDat convertDat) {
        super(convertDat, 52721, 76);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            rfStatus = payloadBB.get(0);
            rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
            rfSpeed = payloadBB.getShort(3);
            rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
            rfTemp = payloadBB.getShort(7);
            rfPPM_recv = payloadBB.getShort(9);
            rfV_out = ((float) payloadBB.getShort(11)) / 10.0f;

            lfStatus = payloadBB.get(19);
            lfCurrent = ((float) payloadBB.getShort(20)) / 100.0f;
            lfSpeed = payloadBB.getShort(22);
            lfVolts = ((float) payloadBB.getShort(24)) / 10.0f;
            lfTemp = payloadBB.getShort(26);
            lfPPM_recv = payloadBB.getShort(28);
            lfV_out = ((float) payloadBB.getShort(30)) / 10.0f;

            lbStatus = payloadBB.get(38);
            lbCurrent = ((float) payloadBB.getShort(39)) / 100.0f;
            lbSpeed = payloadBB.getShort(41);
            lbVolts = ((float) payloadBB.getShort(43)) / 10.0f;
            lbTemp = payloadBB.getShort(45);
            lbPPM_recv = payloadBB.getShort(47);
            lbV_out = ((float) payloadBB.getShort(49)) / 10.0f;

            rbStatus = payloadBB.get(57);
            rbCurrent = ((float) payloadBB.getShort(58)) / 100.0f;
            rbSpeed = payloadBB.getShort(60);
            rbVolts = ((float) payloadBB.getShort(62)) / 10.0f;
            rbTemp = payloadBB.getShort(64);
            rbPPM_recv = payloadBB.getShort(66);
            rbV_out = ((float) payloadBB.getShort(68)) / 10.0f;
            thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed,
                    lfSpeed);
            recordPower();
        } catch (Exception e) {
            RecordException(e);
        }
    }
}
