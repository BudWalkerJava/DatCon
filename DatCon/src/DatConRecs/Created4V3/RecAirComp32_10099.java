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

public class RecAirComp32_10099 extends RecAirComp {

    private float velNorm;

    private float vgx = 0.0f;

    private float vgy = 0.0f;

    public RecAirComp32_10099(ConvertDat convertDat) {
        super(convertDat, 10099, 32);

    }
    /*
     * 
     * P4 Record 87_115  WBN3/FLY048
     name   air_compensate_data
    type    10099
    Op.float        air_speed_est_vel_norm 0
    Op.float        air_speed_est_vel_ground_x 0
    Op.float        air_speed_est_vel_ground_y 0
    Op.float        air_speed_est_vel_ground_fltr_x 0
    Op.float        air_speed_est_vel_ground_fltr_y 0
    Op.float        air_speed_est_vel_body_x 0
    Op.float        air_speed_est_vel_body_y 0
    Op.float        airspeed_comp_alti 0
     * */

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        velNorm = payloadBB.getFloat(0);
        vgx = payloadBB.getFloat(12);
        vgy = payloadBB.getFloat(16);
        vbx = payloadBB.getFloat(20);
        vby = payloadBB.getFloat(24);
        compAlti = payloadBB.getFloat(8);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(vbx, vbSig, "X", lineT, valid);
            printCsvValue(vby, vbSig, "Y", lineT, valid);
            printCsvValue(compAlti, compAltiSig, "", lineT, valid);
            printCsvValue(velNorm, velNormSig, "", lineT, valid);
            printCsvValue(vgx, vgSig, "X", lineT, valid);
            printCsvValue(vgy, vgSig, "Y", lineT, valid);
            printCsvValue(velLevel, velLevelSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
