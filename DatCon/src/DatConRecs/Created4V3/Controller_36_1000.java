package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class Controller_36_1000 extends RecController {

    protected long ctrl_tick = (long) 0;

    protected short ctrl_mode_cur = (short) 0;

    protected short cur_mode_switch = (short) 0;

    protected short motor_state_cur = (short) 0;

    protected short motor_average_speed = (short) 0;

    protected short in_sim_model = (short) 0;

    protected int real_max_height = (int) 0;

    protected int real_max_radius = (int) 0;

    protected float distance_to_home_x = (float) 0;

    protected float distance_to_home_y = (float) 0;

    protected short action_reqest_id = (short) 0;

    protected short action_action_id = (short) 0;

    protected short cur_cmd_mode = (short) 0;

    protected short cur_cmd_mode_req_id = (short) 0;

    public Controller_36_1000(ConvertDat convertDat) {
        super(convertDat, 1000, 36);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            ctrl_tick = _payload.getUnsignedInt(0);
            ctrl_pitch = _payload.getShort(4);
            ctrl_roll = _payload.getShort(6);
            ctrl_yaw = _payload.getShort(8);
            ctrl_thr = _payload.getShort(10);
            ctrl_mode_cur = _payload.getUnsignedByte(12);
            cur_mode_switch = _payload.getUnsignedByte(13);
            motor_state_cur = _payload.getUnsignedByte(14);
            motor_average_speed = _payload.getShort(15);
            sig_level = _payload.getUnsignedByte(17);
            ctrl_level = _payload.getUnsignedByte(18);
            in_sim_model = _payload.getUnsignedByte(19);
            real_max_height = _payload.getUnsignedShort(20);
            real_max_radius = _payload.getUnsignedShort(22);
            distance_to_home_x = _payload.getFloat(24);
            distance_to_home_y = _payload.getFloat(28);
            action_reqest_id = _payload.getUnsignedByte(32);
            action_action_id = _payload.getUnsignedByte(33);
            cur_cmd_mode = _payload.getUnsignedByte(34);
            cur_cmd_mode_req_id = _payload.getUnsignedByte(35);
        } catch (Exception e) {
            RecordException(e);
        }
    }
}
