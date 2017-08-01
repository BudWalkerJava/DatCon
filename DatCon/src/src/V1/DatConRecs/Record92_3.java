/* Record92_3 class

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

// offset 28 float height vps in meters  <=================
// offset 32 Short validity of height 0 not valid, 190 and over valid
// 34   40 non-GPS 36 compass error
public class Record92_3 extends Record {

    public static Record92_3 current = null;

    // 200 Hz
    // width 44
    public float vpsHeight = 0f;

    public short vpsQuality = 0;

    public byte errorStatus = 0x00;

    public Record92_3(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 92;
        _subType = 3;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        payloadBB = _payload.getBB();
        vpsHeight = payloadBB.getFloat(28);
        vpsQuality = payloadBB.getShort(32);
        errorStatus = payloadBB.get(34);
    }

}
