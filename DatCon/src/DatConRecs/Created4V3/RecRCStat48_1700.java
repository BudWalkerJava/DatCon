/* Record190_27 class

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

// 50 Hz
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class RecRCStat48_1700 extends RCStatus {

    public RecRCStat48_1700(ConvertDat convertDat) {
        super(convertDat, 1700, 48);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        statusValid = true;
        fail_safe = payloadBB.get(2);
        data_lost = ((payloadBB.get(4) == 1) ? "lost" : "");
        app_lost = ((payloadBB.get(5) == 1) ? "lost" : "");
        frame_lost = payloadBB.get(6);
        rec_cnt = ((long) payloadBB.getInt(7) & 0xffffffffL);
        connected = ((payloadBB.get(44) == 1) ? "Connected" : "Disconnected");
        super.common();
    }

    @Override
    public void printCols(lineType lineT) {
        super.printCols(lineT);
        try {
            printCsvValue(connected, connectedSig, "", lineT, statusValid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
    //    name    rc_debug_info
    //    type    1700
    //    Op.uint16_t     rc_input_cur_command 0
    //    Op.uint8_t      fail_safe 0
    //    Op.uint8_t      ofdm_vedio_lost 0
    //    Op.uint8_t      ofdm_data_lost 0
    //    Op.uint8_t      app_connect_lost 0
    //    Op.uint8_t      frame_lost 0
    //    Op.uint32_t     rec_rc_cnt 0
    //    Op.uint32_t     app_chl_data_cnt 0
    //    Op.int16_t      KNOB_BASIC_ROLL 0
    //    Op.int16_t      KNOB_BASIC_PITCH 0
    //    Op.int16_t      KNOB_BASIC_YAW 0
    //    Op.int16_t      KNOB_BASIC_THRUST 0
    //    Op.int16_t      KNOB_ATTI_GAIN 0
    //    Op.int16_t      KNOB_GYRO_GAIN 0
    //    Op.int16_t      KNOB_ATTI_HORIZ_GAIN 0
    //    Op.int16_t      KNOB_ATTI_VERT_GAIN 0
    //    Op.int16_t      KNOB_ATTI_RANGE_GAIN 0
    //    Op.int16_t      KNOB_ATTI_VERT_UP_GAIN 0
    //    Op.int16_t      KNOB_BRAKE_GAIN 0
    //    Op.int16_t      KNOB_TILT_GAIN 0
    //    Op.int16_t      KNOB_HORIZ_POS_GAIN 0
    //    Op.int16_t      KNOB_HORIZ_VEL_GAIN 0
    //    Op.uint8_t      sky_connected 0
    //    Op.uint8_t      ground_connected 0
    //    Op.uint8_t      connected 0 ==============45
    //    Op.uint8_t      mode_switch_changed 0
    //    Op.uint8_t      arm_status 0
}
