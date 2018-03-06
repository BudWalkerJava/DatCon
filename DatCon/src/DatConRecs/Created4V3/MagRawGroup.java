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
package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.Signal;
import src.Files.Units;

public class MagRawGroup extends Record {

    public short magX = 0;

    public short magY = 0;

    public short magZ = 0;

    public boolean valid;

    private int index;

    public MagRawGroup(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length);
        this.index = index;
    }

    public static Signal magRawSig = Signal.SeriesFloat("MagRaw",
            "Magnetometer", null, Units.aTesla);

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            magX = payloadBB.getShort(0);
            magY = payloadBB.getShort(2);
            magZ = payloadBB.getShort(4);
            valid = true;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        if (Persist.magCalcs) {
            try {
                float magMod = (int) Math
                        .sqrt(magX * magX + magY * magY + magZ * magZ);
                printCsvValue(magX, magRawSig, "X(" + index + ")", lineT,
                        valid);
                printCsvValue(magY, magRawSig, "Y(" + index + ")", lineT,
                        valid);
                printCsvValue(magZ, magRawSig, "Z(" + index + ")", lineT,
                        valid);
                printCsvValue(magMod, magRawSig, "Mod(" + index + ")", lineT,
                        valid);
                //            printCsvValue(magYaw, AxesAndSigs.magYawSig, "(" + index + ")",
                //                    lineT, valid);
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }
}
