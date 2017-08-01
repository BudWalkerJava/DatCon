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

package src.V1.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;

public class Record218_241 extends Record {

    public static Record218_241 current = null;

    boolean valid = false;

    public short rfLoad = 0;

    public short lfLoad = 0;

    public short lbLoad = 0;

    public short rbLoad = 0;

    public short rfSpeed = 0;

    public short lfSpeed = 0;

    public short lbSpeed = 0;

    public short rbSpeed = 0;

    public float rfVolts = 0f;

    public float lfVolts = 0f;

    public float lbVolts = 0f;

    public float rbVolts = 0f;

    public byte rfStatus = 0x00;

    public byte lfStatus = 0x00;

    public byte lbStatus = 0x00;

    public byte rbStatus = 0x00;

    public short rfTemp = 0;

    public short lfTemp = 0;

    public short lbTemp = 0;

    public short rbTemp = 0;

    public short rfPPM = 0;

    public short lfPPM = 0;

    public short lbPPM = 0;

    public short rbPPM = 0;

    public float rfPWM = 0f;

    public float lfPWM = 0f;

    public float lbPWM = 0f;

    public float rbPWM = 0f;

    public double thrustTheta = 0;

    public Record218_241(ConvertDat convertDat) {
        super(convertDat);
        _type = 218;
        _subType = 241;
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        rfStatus = payloadBB.get(0);
        rfLoad = payloadBB.getShort(1);
        rfSpeed = payloadBB.getShort(3);
        rfVolts = ((float) payloadBB.getShort(5)) / 10.0f;
        rfTemp = payloadBB.getShort(7);
        rfPPM = payloadBB.getShort(9);
        rfPWM = ((float) payloadBB.getShort(11)) / 10.0f;

        lfStatus = payloadBB.get(19);
        lfLoad = payloadBB.getShort(20);
        lfSpeed = payloadBB.getShort(22);
        lfVolts = ((float) payloadBB.getShort(24)) / 10.0f;
        lfTemp = payloadBB.getShort(26);
        lfPPM = payloadBB.getShort(28);
        lfPWM = ((float) payloadBB.getShort(30)) / 10.0f;

        lbStatus = payloadBB.get(38);
        lbLoad = payloadBB.getShort(39);
        lbSpeed = payloadBB.getShort(41);
        lbVolts = ((float) payloadBB.getShort(43)) / 10.0f;
        lbTemp = payloadBB.getShort(45);
        lbPPM = payloadBB.getShort(47);
        lbPWM = ((float) payloadBB.getShort(49)) / 10.0f;

        rbStatus = payloadBB.get(57);
        rbLoad = payloadBB.getShort(58);
        rbSpeed = payloadBB.getShort(60);
        rbVolts = ((float) payloadBB.getShort(62)) / 10.0f;
        rbTemp = payloadBB.getShort(64);
        rbPPM = payloadBB.getShort(66);
        rbPWM = ((float) payloadBB.getShort(68)) / 10.0f;
        thrustTheta = computeThrustTheta(lbSpeed, rfSpeed, rbSpeed, lfSpeed);
    }

}
