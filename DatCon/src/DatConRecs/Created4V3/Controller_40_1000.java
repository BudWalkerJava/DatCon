package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class Controller_40_1000 extends RecController {

    protected long ctrl_tick = (long) 0;

    protected short COMMAND_AILERON = (short) 0;

    protected short COMMAND_ELEVATOR = (short) 0;

    protected short COMMAND_THROTTLE = (short) 0;

    protected short COMMAND_RUDDER = (short) 0;

    protected short COMMAND_MODE = (short) 0;

    protected short COMMAND_IOC = (short) 0;

    protected short COMMAND_GO_HOME = (short) 0;

    protected short COMMAND_D4 = (short) 0;

    protected short ctrl_mode_cur = (short) 0;

    protected short ctrl_real_mode = (short) 0;

    protected short ioc_control_command_mode = (short) 0;

    protected short cur_mode_switch = (short) 0;

    protected short motor_state_cur = (short) 0;

    protected int bat_ad_voltage = (int) 0;

    protected short real_ctl_out_per = (short) 0;

    protected short us_fail = (short) 0;

    protected short in_sim_model = (short) 0;

    public Controller_40_1000(ConvertDat convertDat) {
        super(convertDat, 1000, 40);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            ctrl_tick = _payload.getUnsignedInt(0);
            COMMAND_AILERON = _payload.getShort(4);
            COMMAND_ELEVATOR = _payload.getShort(6);
            COMMAND_THROTTLE = _payload.getShort(8);
            COMMAND_RUDDER = _payload.getShort(10);
            COMMAND_MODE = _payload.getShort(12);
            COMMAND_IOC = _payload.getShort(14);
            COMMAND_GO_HOME = _payload.getShort(16);
            COMMAND_D4 = _payload.getShort(18);
            ctrl_pitch = _payload.getShort(20);
            ctrl_roll = _payload.getShort(22);
            ctrl_yaw = _payload.getShort(24);
            ctrl_thr = _payload.getShort(26);
            ctrl_mode_cur = _payload.getUnsignedByte(28);
            ctrl_real_mode = _payload.getUnsignedByte(29);
            ioc_control_command_mode = _payload.getUnsignedByte(30);
            cur_mode_switch = _payload.getUnsignedByte(31);
            motor_state_cur = _payload.getUnsignedByte(32);
            bat_ad_voltage = _payload.getUnsignedShort(33);
            real_ctl_out_per = _payload.getUnsignedByte(35);
            us_fail = _payload.getUnsignedByte(36);
            sig_level = _payload.getUnsignedByte(37);
            ctrl_level = _payload.getUnsignedByte(38);
            in_sim_model = _payload.getUnsignedByte(39);
        } catch (Exception e) {
            RecordException(e);
        }
    }
}
