package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class Controller_44_1000 extends RecController {

    protected long ctrl_tick = (long) 0;

    protected short ctrl_mode = (short) 0;

    protected short mode_switch = (short) 0;

    protected short motor_state = (short) 0;

    protected short sim_model = (short) 0;

    protected int max_height = (int) 0;

    protected int max_radius = (int) 0;

    protected float D2H_x = (float) 0;

    protected float D2H_y = (float) 0;

    protected short act_req_id = (short) 0;

    protected short act_act_id = (short) 0;

    protected short cmd_mod = (short) 0;

    protected short mod_req_id = (short) 0;

    protected short is_soaring_up = (short) 0;

    protected short eagle_temp_level = (short) 0;

    public Controller_44_1000(ConvertDat convertDat) {
        super(convertDat, 1000, 44);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            ctrl_tick = _payload.getUnsignedInt(0);
            ctrl_pitch = (short) (_payload.getFloat(4) * 10000);
            ctrl_roll = (short) (_payload.getFloat(8) * 10000);
            ctrl_yaw = (short) (_payload.getFloat(12) * 10000);
            ctrl_thr = (short) (_payload.getFloat(16) * 10000);
            ctrl_mode = _payload.getUnsignedByte(20);
            mode_switch = _payload.getUnsignedByte(21);
            motor_state = _payload.getUnsignedByte(22);
            sig_level = _payload.getUnsignedByte(23);
            ctrl_level = _payload.getUnsignedByte(24);
            sim_model = _payload.getUnsignedByte(25);
            max_height = _payload.getUnsignedShort(26);
            max_radius = _payload.getUnsignedShort(28);
            D2H_x = _payload.getFloat(30);
            D2H_y = _payload.getFloat(34);
            act_req_id = _payload.getUnsignedByte(38);
            act_act_id = _payload.getUnsignedByte(39);
            cmd_mod = _payload.getUnsignedByte(40);
            mod_req_id = _payload.getUnsignedByte(41);
            is_soaring_up = _payload.getUnsignedByte(42);
            eagle_temp_level = _payload.getUnsignedByte(43);
        } catch (Exception e) {
            RecordException(e);
        }
    }
}
