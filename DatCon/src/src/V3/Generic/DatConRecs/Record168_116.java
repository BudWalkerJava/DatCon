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
package src.V3.Generic.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.DatConLog;
import src.V3.Files.ConvertDatV3;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class Record168_116 extends AirComp {

    private float windSpeed = 0.0f;

    private float windX = 0.0f;

    private float windY = 0.0f;

    private float motorSpeed = 0.0f;

    public Record168_116(ConvertDatV3 convertDat) {
        super(convertDat);
        _type = 168;
        _subType = 116;
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
        super.windComps();
    }

    //    name        air_compensate_data
    //    type    10100
    //    Op.fp32 air_vbx 0
    //    Op.fp32 air_vby 0
    //    Op.fp32 comp_alti 0
    //    Op.fp32 wind_spd 0
    //    Op.fp32 wind_x 0
    //    Op.fp32 wind_y 0
    //    Op.fp32 MotorSpd 0
    //    Op.uint8_t      vel_level 0

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

            super.printWindComps(lineT);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
