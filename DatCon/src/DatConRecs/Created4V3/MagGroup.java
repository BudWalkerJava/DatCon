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
import src.DatConRecs.Record_2048;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.MagYaw;
import src.Files.Persist;

public class MagGroup extends Record {

    public short magX = 0;

    public short magY = 0;

    public short magZ = 0;

    public boolean valid;

    private int index;

    public MagGroup(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length);
        this.index = index;
    }

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
                double magYaw = 0.0;
                if (lineT == lineType.LINE) {
                    magYaw = MagYaw.compute(magX, magY, magZ, magMod);
                }

                printCsvValue(magX, AxesAndSigs.magSig, "X(" + index + ")",
                        lineT, valid);
                printCsvValue(magY, AxesAndSigs.magSig, "Y(" + index + ")",
                        lineT, valid);
                printCsvValue(magZ, AxesAndSigs.magSig, "Z(" + index + ")",
                        lineT, valid);
                printCsvValue(magMod, AxesAndSigs.magSig, "Mod(" + index + ")",
                        lineT, valid);
                printCsvValue(magYaw, AxesAndSigs.magYawSig, "(" + index + ")",
                        lineT, valid);

                double diff = 0.0;
                double yaw = Math
                        .toDegrees(Record_2048.current.getYawRadians());
                if (yaw > magYaw + 180) {
                    diff = magYaw - yaw + 360.0;
                } else if (yaw < magYaw - 180) {
                    diff = magYaw - yaw - 360.0;
                } else {
                    diff = magYaw - yaw;
                }

                printCsvValue(diff, AxesAndSigs.magYawDiffSig,
                        "(" + index + ")", lineT, valid);
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }
}
