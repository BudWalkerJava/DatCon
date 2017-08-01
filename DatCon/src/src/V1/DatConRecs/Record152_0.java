/* Record152_0 class

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
import src.Files.ConvertDat.lineType;

public class Record152_0 extends Record {
    // 50 hZ
    // length 45

    public static Record152_0 current = null;

    public short aileron = 0;

    public short elevator = 0;

    public short throttle = 0;

    public short rudder = 0;

    public byte modeSwitch = 0;

    public short gpsHealth = 0;

    public Record152_0(ConvertDat convertDat) {
        super(convertDat);
        _type = 152;
        _subType = 0;
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        aileron = payloadBB.getShort(4);
        elevator = payloadBB.getShort(6);
        throttle = payloadBB.getShort(8);
        rudder = payloadBB.getShort(10);
        modeSwitch = (byte) (payloadBB.get(31) & 0x03);
        gpsHealth = payloadBB.get(41);
    }

    @Override
    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called");
    }
}
