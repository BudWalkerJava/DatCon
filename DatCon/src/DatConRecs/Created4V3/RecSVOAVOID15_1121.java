/* Record144_106 class

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
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.ConvertDat.lineType;

public class RecSVOAVOID15_1121 extends Record {

    public String emergBrake = "Off";

    private boolean valid = false;

    public RecSVOAVOID15_1121(ConvertDat convertDat) {
        super(convertDat, 1121, 15);
           }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        if (payloadBB.get(3) == 1) {
            emergBrake = "On";
        } else {
            emergBrake = "Off";
        }
    }
    
//    name        svo_avoid
//    type    1121
//    Op.uint8_t      osd_avoid_obstacle_enable 0
//    Op.uint8_t      osd_user_avoid_enable 0
//    Op.uint8_t      osd_avoid_obstacle_work_flag 0
//    Op.uint8_t      osd_emergency_brake_work_flag 0
//    Op.uint8_t      go_home_avoid_enable 0
//    Op.uint8_t      avoid_ground_force_landing_flag 0
//    Op.uint8_t      radius_limit_work_flag 0
//    Op.uint8_t      airport_limit_work_flag 0
//    Op.uint8_t      avoid_obstacle_work_flag 0
//    Op.uint8_t      horiz_near_boundary_flag 0
//    Op.uint8_t      is_avoid_overshoot_act_flag 0
//    Op.uint8_t      vert_low_limit_work_flag 0
//    Op.uint8_t      vert_airport_limit_work_flag 0
//    Op.uint8_t      roof_limit_flag 0
//    Op.uint8_t      hit_ground_limit_work_flag 0


    private static Signal emergBrakeSig = Signal.State("OA:emergBrake",
            "Emergency Brake", "Off");

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(emergBrake, emergBrakeSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
