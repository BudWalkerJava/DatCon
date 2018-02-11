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

package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

// Slightly different from Record144_106
public class RecMotor169_10090 extends Motor {

    public RecMotor169_10090(ConvertDat convertDat) {
        super(convertDat, 10090, 169);
        PPMsend = true;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        rfStatus = payloadBB.get(0);
        rfCurrent = ((float) payloadBB.getShort(1)) / 100.0f;
        rfSpeed = payloadBB.getShort(3);
        rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
        rfTemp = payloadBB.getShort(7);
        rfPPM_recv = payloadBB.getShort(9);
        rfV_out = ((float) payloadBB.getShort(11)) / 10.0f;
        rfPPM_send = _payload.getUnsignedShort(19);
        //
        lfStatus = payloadBB.get(21);
        lfCurrent = ((float) payloadBB.getShort(22)) / 100.0f;
        lfSpeed = payloadBB.getShort(24);
        lfVolts = ((float) payloadBB.getShort(26)) / 10.0f;
        lfTemp = payloadBB.getShort(28);
        lfPPM_recv = payloadBB.getShort(30);
        lfV_out = ((float) payloadBB.getShort(32)) / 10.0f;
        lfPPM_send = _payload.getUnsignedShort(40);
        //
        lbStatus = payloadBB.get(42);
        lbCurrent = ((float) payloadBB.getShort(43)) / 100.0f;
        lbSpeed = payloadBB.getShort(45);
        lbVolts = ((float) payloadBB.getShort(47)) / 10.0f;
        lbTemp = payloadBB.getShort(49);
        lbPPM_recv = payloadBB.getShort(51);
        lbV_out = ((float) payloadBB.getShort(53)) / 10.0f;
        lbPPM_send = _payload.getUnsignedShort(61);
        //
        rbStatus = payloadBB.get(63);
        rbCurrent = ((float) payloadBB.getShort(64)) / 100.0f;
        rbSpeed = payloadBB.getShort(66);
        rbVolts = ((float) payloadBB.getShort(68)) / 10.0f;
        rbTemp = payloadBB.getShort(70);
        rbPPM_recv = payloadBB.getShort(72);
        rbV_out = ((float) payloadBB.getShort(74)) / 10.0f;
        rbPPM_send = _payload.getUnsignedShort(82);
        thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed, lfSpeed);
        //        computePower(rfVolts, rfCurrent, lfVolts, lfCurrent, lbVolts, lbCurrent,
        //                rbVolts, rbCurrent);
    }
}
