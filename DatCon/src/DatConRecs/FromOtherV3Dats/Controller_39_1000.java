package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class Controller_39_1000 extends Record {
    protected boolean valid = false;

    protected long ctrl_tick = (long) 0;

    protected short ctrl_pitch = (short) 0;

    protected short ctrl_roll = (short) 0;

    protected short ctrl_yaw = (short) 0;

    protected short ctrl_thr = (short) 0;

    protected short ctrl_mode = (short) 0;

    protected short mode_switch = (short) 0;

    protected short motor_state = (short) 0;

    protected short sig_level = (short) 0;

    protected short ctrl_level = (short) 0;

    protected short sim_model = (short) 0;

    protected int max_height = (int) 0;

    protected int max_radius = (int) 0;

    protected float D2H_x = (float) 0;

    protected float D2H_y = (float) 0;

    protected short act_req_id = (short) 0;

    protected short act_act_id = (short) 0;

    protected short cmd_mod = (short) 0;

    protected short mod_req_id = (short) 0;

    protected short fw_flag = (short) 0;

    protected short mot_sta = (short) 0;

    protected short OH_take = (short) 0;

    protected short rc_cnt = (short) 0;

    protected short sup_rc = (short) 0;

    public Controller_39_1000(ConvertDat convertDat) {
        super(convertDat, 1000, 39);
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
            ctrl_mode = _payload.getUnsignedByte(12);
            mode_switch = _payload.getUnsignedByte(13);
            motor_state = _payload.getUnsignedByte(14);
            sig_level = _payload.getUnsignedByte(15);
            ctrl_level = _payload.getUnsignedByte(16);
            sim_model = _payload.getUnsignedByte(17);
            max_height = _payload.getUnsignedShort(18);
            max_radius = _payload.getUnsignedShort(20);
            D2H_x = _payload.getFloat(22);
            D2H_y = _payload.getFloat(26);
            act_req_id = _payload.getUnsignedByte(30);
            act_act_id = _payload.getUnsignedByte(31);
            cmd_mod = _payload.getUnsignedByte(32);
            mod_req_id = _payload.getUnsignedByte(33);
            fw_flag = _payload.getUnsignedByte(34);
            mot_sta = _payload.getUnsignedByte(35);
            OH_take = _payload.getUnsignedByte(36);
            rc_cnt = _payload.getUnsignedByte(37);
            sup_rc = _payload.getUnsignedByte(38);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal ControllerIntSig = Signal.SeriesInt("Controller",
            "", null, Units.noUnits);

    protected static Signal ControllerFloatSig = Signal
            .SeriesFloat("Controller", "", null, Units.noUnits);

    protected static Signal ControllerDoubleSig = Signal
            .SeriesDouble("Controller", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(ctrl_tick, ControllerIntSig, "ctrl_tick", lineT,
                    valid);
            printCsvValue(ctrl_pitch, ControllerIntSig, "ctrl_pitch", lineT,
                    valid);
            printCsvValue(ctrl_roll, ControllerIntSig, "ctrl_roll", lineT,
                    valid);
            printCsvValue(ctrl_yaw, ControllerIntSig, "ctrl_yaw", lineT, valid);
            printCsvValue(ctrl_thr, ControllerIntSig, "ctrl_thr", lineT, valid);
            printCsvValue(ctrl_mode, ControllerIntSig, "ctrl_mode", lineT,
                    valid);
            printCsvValue(mode_switch, ControllerIntSig, "mode_switch", lineT,
                    valid);
            printCsvValue(motor_state, ControllerIntSig, "motor_state", lineT,
                    valid);
            printCsvValue(sig_level, ControllerIntSig, "sig_level", lineT,
                    valid);
            printCsvValue(ctrl_level, ControllerIntSig, "ctrl_level", lineT,
                    valid);
            printCsvValue(sim_model, ControllerIntSig, "sim_model", lineT,
                    valid);
            printCsvValue(max_height, ControllerIntSig, "max_height", lineT,
                    valid);
            printCsvValue(max_radius, ControllerIntSig, "max_radius", lineT,
                    valid);
            printCsvValue(D2H_x, ControllerFloatSig, "D2H_x", lineT, valid);
            printCsvValue(D2H_y, ControllerFloatSig, "D2H_y", lineT, valid);
            printCsvValue(act_req_id, ControllerIntSig, "act_req_id", lineT,
                    valid);
            printCsvValue(act_act_id, ControllerIntSig, "act_act_id", lineT,
                    valid);
            printCsvValue(cmd_mod, ControllerIntSig, "cmd_mod", lineT, valid);
            printCsvValue(mod_req_id, ControllerIntSig, "mod_req_id", lineT,
                    valid);
            printCsvValue(fw_flag, ControllerIntSig, "fw_flag", lineT, valid);
            printCsvValue(mot_sta, ControllerIntSig, "mot_sta", lineT, valid);
            printCsvValue(OH_take, ControllerIntSig, "OH_take", lineT, valid);
            printCsvValue(rc_cnt, ControllerIntSig, "rc_cnt", lineT, valid);
            printCsvValue(sup_rc, ControllerIntSig, "sup_rc", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
