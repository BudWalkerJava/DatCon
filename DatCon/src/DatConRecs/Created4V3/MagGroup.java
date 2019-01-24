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
import src.DatConRecs.RecIMU;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.MagYaw;
import src.Files.Persist;
import src.Files.Signal;
import src.Files.Units;

public class MagGroup extends Record {

    public short magX = 0;

    public short magY = 0;

    public short magZ = 0;

    public boolean valid;

    protected Signal magSig = null;

    protected Signal magYawSig = null;

//    private int index;

    public MagGroup(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length);
        //this.index = index;
        magSig = Signal.SeriesFloat("Mag", index, "Magnetometer", null,
                Units.aTesla);
        magYawSig = Signal.SeriesDouble("Mag", index,
                "Yaw computed from magnetometers", null, Units.degrees180);
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

                printCsvValue(magX, magSig, "X", lineT, valid);
                printCsvValue(magY, magSig, "Y", lineT, valid);
                printCsvValue(magZ, magSig, "Z", lineT, valid);
                printCsvValue(magMod, magSig, "Mod", lineT, valid);
                printCsvValue(magYaw, magYawSig, "magYaw", lineT, valid);

                double diff = 0.0;
                double yaw = Math.toDegrees(RecIMU.current.getYawRadians());
                if (yaw > magYaw + 180) {
                    diff = magYaw - yaw + 360.0;
                } else if (yaw < magYaw - 180) {
                    diff = magYaw - yaw - 360.0;
                } else {
                    diff = magYaw - yaw;
                }
                printCsvValue(diff, magYawSig, "Yaw-magYaw", lineT, valid);
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }
}
