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
package src.V1.DatConRecs;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.Quaternion;
import src.Files.RollPitchYaw;

// 50 HZ
// length 237
public class Record44_52 extends Record {

    public static Record44_52 current = null;

    public float quatW = 0.0f;

    public float quatX = 0.0f;

    public float quatY = 0.0f;

    public float quatZ = 0.0f;

    public float yaw = 0.0f;

    public float roll = 0.0f;

    public float pitch = 0.0f;

    public short rFront = 0;

    public short lFront = 0;

    public short lBack = 0;

    public short rBack = 0;

    public Record44_52(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 44;
        _subType = 52;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        // 70 Float some kind of yaw?
        // 74 float correction for gimbal?
        // 54, 58, 62, 66 the same as 78, 82, 86, 90
        quatW = payloadBB.getFloat(78);
        quatX = payloadBB.getFloat(82);
        quatY = payloadBB.getFloat(86);
        quatZ = payloadBB.getFloat(90);

        yaw = payloadBB.getFloat(94);
        roll = payloadBB.getFloat(98);
        pitch = payloadBB.getFloat(102);

        rFront = payloadBB.getShort(219);
        lFront = payloadBB.getShort(221);
        lBack = payloadBB.getShort(223);
        rBack = payloadBB.getShort(225);
    }
}
