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

public class RecAirComp21_10100 extends RecAirComp {

    private float velNorm;

    private short velLevel1Time;

    private short velLevel2Time;

    public RecAirComp21_10100(ConvertDat convertDat) {
        super(convertDat, 10100, 21);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        vbx = payloadBB.getFloat(0);
        vby = payloadBB.getFloat(4);
        compAlti = payloadBB.getFloat(8);
        velNorm = payloadBB.getFloat(12);
        velLevel1Time = (short) (payloadBB.getShort(16));
        velLevel2Time = (short) (payloadBB.getShort(18));
        velLevel = (short) (payloadBB.get(20));
    }

    //    public static Signal velNormSig = Signal.SeriesFloat("AirComp:VelNorm",
    //            "AirComp Velocity Norm", null, Units.noUnits);
    //
    //    public static Signal velLevelTimeSig = Signal.SeriesInt("AirComp:VelTime",
    //            "AirComp Velocity Time", null, Units.noUnits);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(vbx, vbSig, "X", lineT, valid);
            printCsvValue(vby, vbSig, "Y", lineT, valid);
            printCsvValue(compAlti, compAltiSig, "", lineT, valid);
            printCsvValue(velNorm, velNormSig, "", lineT, valid);
            printCsvValue(velLevel1Time, velLevelTimeSig, "1", lineT, valid);
            printCsvValue(velLevel2Time, velLevelTimeSig, "2", lineT, valid);
            printCsvValue(velLevel, velLevelSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
    //    name        air_compensate_data
    //    type    10100
    //    Op.float        air_speed_est_vel_body_x 0
    //    Op.float        air_speed_est_vel_body_y 0
    //    Op.float        airspeed_comp_alti 0
    //    Op.float        air_speed_vel_norm 0
    //    Op.uint16_t     airspeed_level1_time 0
    //    Op.uint16_t     airspeed_level2_time 0
    //    Op.uint8_t      air_speed_vel_level 0

}
