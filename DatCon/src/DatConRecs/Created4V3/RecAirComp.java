/* Motor class

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
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class RecAirComp extends Record {
    protected boolean valid = false;

    protected short velLevel;

    protected float vbx = 0.0f;

    protected float vby = 0.0f;

    protected float compAlti = 0.0f;

    private double windHeading = 0.0f;

    private double windMag = 0.0;

    protected static Signal vbSig = Signal.SeriesFloat("AirComp:AirSpeedBody",
            "AirComp AirSpeedBody", null, Units.metersPerSec);

    protected static Signal experimentalSig = Signal.SeriesFloatExperimental(
            "AirComp:exp", "AirComp AirSpeedBody", null, Units.noUnits);

    protected static Signal compAltiSig = Signal.SeriesFloat("AirComp:Alti",
            "AirComp Altitude", null, Units.meters);

    protected static Signal velLevelSig = Signal.SeriesInt("AirComp:VelLevel",
            "AirComp VelLevel", null, Units.noUnits);

    protected static Signal windHeadingSig = Signal.SeriesInt(
            "AirCompD:WindHeading", "AirComp Derived Wind Heading", null,
            Units.degrees180);

    protected static Signal windMagSig = Signal.SeriesInt(
            "AirCompD:WindMagnitude", "AirComp Derived Wind Magnitude", null,
            Units.metersPerSec);

    protected static Signal velNormSig = Signal.SeriesFloat("AirComp:VelNorm",
            "AirComp Velocity Norm", null, Units.noUnits);

    protected static Signal velLevelTimeSig = Signal.SeriesInt(
            "AirComp:VelTime", "AirComp Velocity Time", null, Units.noUnits);

    protected static Signal vgSig = Signal.SeriesFloat("AirComp:AirSpeedGround",
            "AirComp AirSpeedGround", null, Units.metersPerSec);

    public RecAirComp(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    double gX = 0.0;

    double gY = 0.0;

    double wX = 0.0;

    double wY = 0.0;

    double alpha = 0.0;

    double theta = 0.0;

    double yawRadians = 0.0;

    private double Vh = 0.0;

    private double windMag2 = 0.0;

    protected void windComps() {
        yawRadians = Record_2048.current.getYawRadians();
        double Vn = Record_2048.current.getVn();
        double Ve = Record_2048.current.getVe();
        Vh = Math.sqrt(Ve * Ve + Vn * Vn);
        Vh = Vh * ((double) velLevel) / 100.0;
        alpha = Math.atan2(Ve, Vn);
        theta = alpha - yawRadians;
        // * (((double) velLevel) / 100.0);
        gX = Math.cos(theta) * Vh;
        gY = Math.sin(theta) * Vh;
        //        vbx = vbx * (((float) velLevel) / 100.0f);
        //        vby = vby * (((float) velLevel) / 100.0f);
        wX = (vbx - gX);
        wY = (vby - gY);
        double head = Math.toDegrees(Math.atan2(wY, wX) + yawRadians);
        if (head > 180.0) {
            head = head - 360.0;
        }
        windHeading = head;
        windMag = Math.sqrt(wX * wX + wY * wY);
        windMag2 = windMag * ((double) velLevel) / 100.0;
    }

    protected void printWindComps(lineType lineT) {
        try {
            printCsvValue(Math.toDegrees(yawRadians), experimentalSig,
                    "yawDegrees", lineT, valid);
            printCsvValue(Math.toDegrees(alpha), experimentalSig,
                    "alphaDegrees", lineT, valid);
            printCsvValue(theta, experimentalSig, "theta", lineT, valid);
            printCsvValue(Vh, experimentalSig, "Vh", lineT, valid);
            printCsvValue(gX, experimentalSig, "gX", lineT, valid);
            printCsvValue(gY, experimentalSig, "gY", lineT, valid);
            printCsvValue(wX, experimentalSig, "wX", lineT, valid);
            printCsvValue(wY, experimentalSig, "wY", lineT, valid);
            printCsvValue(windHeading, windHeadingSig, "", lineT, valid);
            printCsvValue(windMag, windMagSig, "", lineT, valid);
            printCsvValue(windMag2, windMagSig, "2", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
