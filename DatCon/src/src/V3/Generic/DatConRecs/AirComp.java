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
package src.V3.Generic.DatConRecs;

import java.util.ArrayList;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.V3.Files.ConvertDatV3;

public class AirComp extends Record {
    protected boolean valid = false;

    protected short velLevel;

    protected float vbx = 0.0f;

    protected float vby = 0.0f;

    protected float compAlti = 0.0f;

    private double windHeading = 0.0f;

    private double windMag = 0.0;

    public static Signal vbSig = Signal.SeriesFloat("AirComp:AirSpeedBody",
            "AirComp AirSpeedBody", null, Units.metersPerSec);

    public static Signal experimentalSig = Signal.SeriesFloatExperimental(
            "AirComp:exp", "AirComp AirSpeedBody", null, Units.noUnits);

    public static Signal compAltiSig = Signal.SeriesFloat("AirComp:Alti",
            "AirComp Altitude", null, Units.meters);

    public static Signal velLevelSig = Signal.SeriesInt("AirComp:VelLevel",
            "AirComp VelLevel", null, Units.noUnits);

    public static Signal windHeadingSig = Signal.SeriesInt(
            "AirCompD:WindHeading", "AirComp Derived Wind Heading", null,
            Units.degrees180);

    public static Signal windMagSig = Signal.SeriesInt("AirCompD:WindMagnitude",
            "AirComp Derived Wind Magnitude", null, Units.metersPerSec);

    public AirComp(ConvertDatV3 convertDat) {
        super(convertDat);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    public static ArrayList<Record> create(ConvertDatV3 convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        if (convertDat.getDatFile().typeSubTypeExists(168, 116)) {
            retv.add(new Record168_116(convertDat));
        } else if (convertDat.getDatFile().typeSubTypeExists(222, 116)) {
            retv.add(new Record222_116(convertDat));
        } else if (convertDat.getDatFile().typeSubTypeExists(87, 115)) {
            retv.add(new Record87_115(convertDat));
        } else {
            DatConLog.Log(
                    "Can't find AirComp Record, tried 168_116, 222_116, 87_115");
        }
        return retv;
    }

    double gX = 0.0;

    double gY = 0.0;

    double wX = 0.0;

    double wY = 0.0;

    double alpha = 0.0;

    double theta = 0.0;

    double yaw = 0.0;

    private double Vh = 0.0;

    private double windMag2 = 0.0;

    protected void windComps() {
        yaw = Record207_0.current.getYaw();
        double Vn = Record207_0.current.getVn();
        double Ve = Record207_0.current.getVe();
        Vh = Math.sqrt(Ve * Ve + Vn * Vn);
        Vh = Vh * ((double) velLevel) / 100.0;
        alpha = Math.atan2(Ve, Vn);
        theta = alpha - yaw;
        // * (((double) velLevel) / 100.0);
        gX = Math.cos(theta) * Vh;
        gY = Math.sin(theta) * Vh;
        //        vbx = vbx * (((float) velLevel) / 100.0f);
        //        vby = vby * (((float) velLevel) / 100.0f);
        wX = (vbx - gX);
        wY = (vby - gY);
        double head = Math.toDegrees(Math.atan2(wY, wX) + yaw);
        if (head > 180.0) {
            head = head - 360.0;
        }
        windHeading = head;
        windMag = Math.sqrt(wX * wX + wY * wY);
        windMag2 = windMag * ((double) velLevel) / 100.0;
    }

    protected void printWindComps(lineType lineT) {
        try {
            printCsvValue(Math.toDegrees(yaw), experimentalSig, "yawDegrees",
                    lineT, valid);
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
