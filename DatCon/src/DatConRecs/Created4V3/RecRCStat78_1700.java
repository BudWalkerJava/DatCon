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

public class RecRCStat78_1700 extends RCStatus {

    public RecRCStat78_1700(ConvertDat convertDat) {
        super(convertDat, 1700, 78);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        statusValid = true;
        fail_safe = payloadBB.get(45);
        data_lost = ((payloadBB.get(47) == 1) ? "lost" : "");
        app_lost = ((payloadBB.get(48) == 1) ? "lost" : "");
        frame_lost = payloadBB.get(49);
        rec_cnt = ((long) payloadBB.getInt(50) & 0xffffffffL);
        super.common();
    }

    @Override
    public void printCols(lineType lineT) {
        super.printCols(lineT);
    }

    //    type        1700
    //    Op.uint16_t     g_status.rc.input_cur.command 0
    //    Op.uint8_t      mode_abnormal_status 0
    //    Op.uint8_t      lost_status 0
    //    Op.uint8_t      failsafe_status 0
    //    Op.int32_t      raw_COMMAND_AILERON 0
    //    Op.int32_t      raw_COMMAND_ELEVATOR 0
    //    Op.int32_t      raw_COMMAND_THROTTLE 0
    //    Op.int32_t      raw_COMMAND_RUDDER 0
    //    Op.int32_t      raw_COMMAND_MODE 0
    //    Op.int32_t      raw_gohome_arm_action 0
    //    Op.float        input_cur_pitch 0
    //    Op.float        input_cur_roll 0
    //    Op.float        input_cur_yaw 0
    //    Op.float        input_cur_throttle 0
    //    Op.uint8_t      fail_safe 0
    //    Op.uint8_t      ofdm_vedio_lost 0
    //    Op.uint8_t      ofdm_data_lost 0
    //    Op.uint8_t      app_connect_lost 0
    //    Op.uint8_t      frame_lost 0
    //    Op.uint32_t     rec_rc_cnt 0
    //    Op.uint8_t      sw1  0
    //    Op.uint8_t      sw2  0
    //    Op.uint8_t      sw3  0
    //    Op.uint8_t      sw4  0
    //    Op.uint8_t      sw5  0
    //    Op.uint8_t      sw6  0
    //    Op.uint8_t      sw7  0
    //    Op.uint8_t      sw8  0
    //    Op.uint8_t      sw9  0
    //    Op.uint8_t      sw10 0
    //    Op.uint8_t      sw11 0
    //    Op.uint8_t      sw12 0
    //    Op.uint8_t      sw13 0
    //    Op.uint8_t      sw14 0
    //    Op.uint8_t      sw15 0
    //    Op.uint8_t      sw16 0
    //    Op.uint8_t      bu1 0
    //    Op.uint8_t      bu2 0
    //    Op.uint8_t      bu3 0
    //    Op.uint8_t      bu4 0
    //    Op.uint8_t      bu5 0
    //    Op.uint8_t      bu6 0
    //    Op.uint8_t      bu7 0
    //    Op.uint8_t      bu8 0
}
