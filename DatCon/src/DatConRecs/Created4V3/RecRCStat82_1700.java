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
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class RecRCStat82_1700 extends RCStatus {

    public RecRCStat82_1700(ConvertDat convertDat) {
        super(convertDat, 1700, 82);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        statusValid = true;
        //        fail_safe = payloadBB.get(45);
        //        data_lost = ((payloadBB.get(47) == 1) ? "lost" : "");
        //        app_lost = ((payloadBB.get(48) == 1) ? "lost" : "");
        //        frame_lost = payloadBB.get(49);
        //        rec_cnt = ((long) payloadBB.getInt(50) & 0xffffffffL);
        fail_safe = payloadBB.get(37);
        data_lost = ((payloadBB.get(39) == 1) ? "lost" : "");
        app_lost = ((payloadBB.get(40) == 1) ? "lost" : "");
        frame_lost = payloadBB.get(41);
        rec_cnt = ((long) payloadBB.getInt(42) & 0xffffffffL);
        super.common();
    }

    @Override
    public void printCols(lineType lineT) {
        super.printCols(lineT);
    }

    //    name        rc_debug_info
    //    type    1700
    //    Op.uint16_t     rc_input_cur_command 0
    //    Op.uint8_t      mode_abnormal_status 0
    //    Op.uint8_t      lost_status 0
    //    Op.uint8_t      failsafe_status 0
    //    Op.int32_t      raw_COMMAND_AILERON 0
    //    Op.int32_t      raw_COMMAND_ELEVATOR 0
    //    Op.int32_t      raw_COMMAND_THROTTLE 0
    //    Op.int32_t      raw_COMMAND_RUDDER 0
    //    Op.int32_t      raw_COMMAND_MODE 0
    //    Op.int32_t      raw_COMMAND_GEAR 0
    //    Op.int32_t      raw_COMMAND_GO_HOME 0
    //    Op.int32_t      raw_gohome_arm_action 0
    //    Op.uint8_t      fail_safe 0  =============37
    //    Op.uint8_t      ofdm_vedio_lost 0
    //    Op.uint8_t      ofdm_data_lost 0
    //    Op.uint8_t      app_connect_lost 0
    //    Op.uint8_t      frame_lost 0 ==============41
    //    Op.uint32_t     rec_rc_cnt 0
    //    Op.uint8_t      c1 0
    //    Op.uint8_t      c2 0
    //    Op.int8_t       wheel_value 0
    //    Op.uint8_t      arm_status 0
    //    Op.int32_t      app_chl[0] 0
    //    Op.int32_t      app_chl[1] 0
    //    Op.int32_t      app_chl[2] 0
    //    Op.int32_t      app_chl[3] 0
    //    Op.int32_t      app_chl[4] 0
    //    Op.int32_t      app_chl[5] 0
    //    Op.int32_t      app_chl[6] 0
    //    Op.int32_t      app_chl[7] 0
}
