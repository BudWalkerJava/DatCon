/* Record68_17 class

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
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.Signal;
import src.Files.Units;

public class RecAirComp29_10100 extends RecAirComp {

    private float windSpeed = 0.0f;

    private float windX = 0.0f;

    private float windY = 0.0f;

    private float motorSpeed = 0.0f;

    public RecAirComp29_10100(ConvertDat convertDat) {
        super(convertDat, 10100, 29);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        vbx = payloadBB.getFloat(0);
        vby = payloadBB.getFloat(4);
        compAlti = payloadBB.getFloat(8);
        windSpeed = payloadBB.getFloat(12);
        windX = payloadBB.getFloat(16);
        windY = payloadBB.getFloat(20);
        motorSpeed = payloadBB.getFloat(24);
        velLevel = (short) (0xff & payloadBB.get(26));
        if (Persist.EXPERIMENTAL_FIELDS) {
            super.windComps();
        }
    }

    public static Signal windSpeedSig = Signal.SeriesFloat("AirComp:WindSpeed",
            "AirComp WindSpeed", null, Units.metersPerSec);

    public static Signal windSig = Signal.SeriesFloat("AirComp:Wind",
            "AirComp Wind", null, Units.metersPerSec);

    public static Signal motorSpeedSig = Signal.SeriesFloat(
            "AirComp:MotorSpeed", "AirComp Motor Speed", null, Units.rpm);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(vbx, vbSig, "X", lineT, valid);
            printCsvValue(vby, vbSig, "Y", lineT, valid);
            printCsvValue(compAlti, compAltiSig, "", lineT, valid);
            printCsvValue(windSpeed, windSpeedSig, "", lineT, valid);
            printCsvValue(windX, windSig, "X", lineT, valid);
            printCsvValue(windY, windSig, "Y", lineT, valid);
            printCsvValue(motorSpeed, motorSpeedSig, "", lineT, valid);
            printCsvValue(velLevel, velLevelSig, "", lineT, valid);
            if (Persist.EXPERIMENTAL_FIELDS) {
                super.printWindComps(lineT);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
