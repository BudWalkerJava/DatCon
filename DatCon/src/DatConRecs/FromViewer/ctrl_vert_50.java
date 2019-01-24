package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class ctrl_vert_50 extends Record {
protected boolean valid = false;

protected short vert_module_module_mode = (short)0;
protected short vert_module_vert_hover_state = (short)0;
protected short vert_module_vert_hover_enable = (short)0;
protected float vert_module_vert_hover_pos = (float)0;
protected float vert_module_vert_hover_brake_timer = (float)0;
protected float vert_module_take_off_thrust = (float)0;
protected short vert_module_auto_take_off_state = (short)0;
protected float vert_module_auto_take_off_height = (float)0;
protected short api_vert_ctrl_mode = (short)0;
protected short api_vert_ctrl_cmd_id = (short)0;
protected short vert_pos_status = (short)0;
protected short vert_pos_cmd_id = (short)0;
protected short vert_pos_feedback_id = (short)0;
protected short vert_pos_cmd = (short)0;
protected short vert_pos_feedback = (short)0;
protected short vert_pos_P_ctrl = (short)0;
protected short vert_pos_output = (short)0;
protected short vert_vel_status = (short)0;
protected short vert_vel_cmd_id = (short)0;
protected short vert_vel_feedback_id = (short)0;
protected short vert_vel_cmd = (short)0;
protected short vert_vel_cmd_before_limit = (short)0;
protected short vert_vel_cmd_after_limit = (short)0;
protected short vert_vel_feedback = (short)0;
protected short vert_vel_P_ctrl = (short)0;
protected short vert_vel_output = (short)0;
protected short vert_acc_status = (short)0;
protected short vert_acc_cmd_id = (short)0;
protected short vert_acc_feedback_id = (short)0;
protected short vert_acc_cmd = (short)0;
protected short vert_acc_feedback = (short)0;
protected short vert_acc_P_ctrl = (short)0;
protected short vert_acc_I_ctrl = (short)0;
protected short vert_acc_feedforward = (short)0;
protected short vert_acc_output = (short)0;
protected short vert_thrust_status = (short)0;
protected short vert_thrust_cmd_id = (short)0;
protected short vert_thrust_feedback_id = (short)0;
protected short vert_thrust_cmd_data = (short)0;

      public ctrl_vert_50(ConvertDat convertDat) {
           super(convertDat, 50, 68);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

vert_module_module_mode = _payload.getUnsignedByte(0);
vert_module_vert_hover_state = _payload.getUnsignedByte(1);
vert_module_vert_hover_enable = _payload.getUnsignedByte(2);
 vert_module_vert_hover_pos = _payload.getFloat(3);
 vert_module_vert_hover_brake_timer = _payload.getFloat(7);
 vert_module_take_off_thrust = _payload.getFloat(11);
vert_module_auto_take_off_state = _payload.getUnsignedByte(15);
 vert_module_auto_take_off_height = _payload.getFloat(16);
api_vert_ctrl_mode = _payload.getUnsignedByte(20);
api_vert_ctrl_cmd_id = _payload.getUnsignedByte(21);
vert_pos_status = _payload.getUnsignedByte(22);
vert_pos_cmd_id = _payload.getUnsignedByte(23);
vert_pos_feedback_id = _payload.getUnsignedByte(24);
 vert_pos_cmd = _payload.getShort(25);
 vert_pos_feedback = _payload.getShort(27);
 vert_pos_P_ctrl = _payload.getShort(29);
 vert_pos_output = _payload.getShort(31);
vert_vel_status = _payload.getUnsignedByte(33);
vert_vel_cmd_id = _payload.getUnsignedByte(34);
vert_vel_feedback_id = _payload.getUnsignedByte(35);
 vert_vel_cmd = _payload.getShort(36);
 vert_vel_cmd_before_limit = _payload.getShort(38);
 vert_vel_cmd_after_limit = _payload.getShort(40);
 vert_vel_feedback = _payload.getShort(42);
 vert_vel_P_ctrl = _payload.getShort(44);
 vert_vel_output = _payload.getShort(46);
vert_acc_status = _payload.getUnsignedByte(48);
vert_acc_cmd_id = _payload.getUnsignedByte(49);
vert_acc_feedback_id = _payload.getUnsignedByte(50);
 vert_acc_cmd = _payload.getShort(51);
 vert_acc_feedback = _payload.getShort(53);
 vert_acc_P_ctrl = _payload.getShort(55);
 vert_acc_I_ctrl = _payload.getShort(57);
 vert_acc_feedforward = _payload.getShort(59);
 vert_acc_output = _payload.getShort(61);
vert_thrust_status = _payload.getUnsignedByte(63);
vert_thrust_cmd_id = _payload.getUnsignedByte(64);
vert_thrust_feedback_id = _payload.getUnsignedByte(65);
 vert_thrust_cmd_data = _payload.getShort(66);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ctrl_vertIntSig = Signal
.SeriesInt("ctrl_vert", "", null, Units.noUnits);
    protected static Signal ctrl_vertFloatSig = Signal
.SeriesFloat("ctrl_vert", "", null, Units.noUnits);
    protected static Signal ctrl_vertDoubleSig = Signal
.SeriesDouble("ctrl_vert", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(vert_module_module_mode, ctrl_vertIntSig, "vert_module_module_mode",lineT, valid);
 printCsvValue(vert_module_vert_hover_state, ctrl_vertIntSig, "vert_module_vert_hover_state",lineT, valid);
 printCsvValue(vert_module_vert_hover_enable, ctrl_vertIntSig, "vert_module_vert_hover_enable",lineT, valid);
 printCsvValue(vert_module_vert_hover_pos, ctrl_vertFloatSig, "vert_module_vert_hover_pos",lineT, valid);
 printCsvValue(vert_module_vert_hover_brake_timer, ctrl_vertFloatSig, "vert_module_vert_hover_brake_timer",lineT, valid);
 printCsvValue(vert_module_take_off_thrust, ctrl_vertFloatSig, "vert_module_take_off_thrust",lineT, valid);
 printCsvValue(vert_module_auto_take_off_state, ctrl_vertIntSig, "vert_module_auto_take_off_state",lineT, valid);
 printCsvValue(vert_module_auto_take_off_height, ctrl_vertFloatSig, "vert_module_auto_take_off_height",lineT, valid);
 printCsvValue(api_vert_ctrl_mode, ctrl_vertIntSig, "api_vert_ctrl_mode",lineT, valid);
 printCsvValue(api_vert_ctrl_cmd_id, ctrl_vertIntSig, "api_vert_ctrl_cmd_id",lineT, valid);
 printCsvValue(vert_pos_status, ctrl_vertIntSig, "vert_pos_status",lineT, valid);
 printCsvValue(vert_pos_cmd_id, ctrl_vertIntSig, "vert_pos_cmd_id",lineT, valid);
 printCsvValue(vert_pos_feedback_id, ctrl_vertIntSig, "vert_pos_feedback_id",lineT, valid);
 printCsvValue(vert_pos_cmd, ctrl_vertIntSig, "vert_pos_cmd",lineT, valid);
 printCsvValue(vert_pos_feedback, ctrl_vertIntSig, "vert_pos_feedback",lineT, valid);
 printCsvValue(vert_pos_P_ctrl, ctrl_vertIntSig, "vert_pos_P_ctrl",lineT, valid);
 printCsvValue(vert_pos_output, ctrl_vertIntSig, "vert_pos_output",lineT, valid);
 printCsvValue(vert_vel_status, ctrl_vertIntSig, "vert_vel_status",lineT, valid);
 printCsvValue(vert_vel_cmd_id, ctrl_vertIntSig, "vert_vel_cmd_id",lineT, valid);
 printCsvValue(vert_vel_feedback_id, ctrl_vertIntSig, "vert_vel_feedback_id",lineT, valid);
 printCsvValue(vert_vel_cmd, ctrl_vertIntSig, "vert_vel_cmd",lineT, valid);
 printCsvValue(vert_vel_cmd_before_limit, ctrl_vertIntSig, "vert_vel_cmd_before_limit",lineT, valid);
 printCsvValue(vert_vel_cmd_after_limit, ctrl_vertIntSig, "vert_vel_cmd_after_limit",lineT, valid);
 printCsvValue(vert_vel_feedback, ctrl_vertIntSig, "vert_vel_feedback",lineT, valid);
 printCsvValue(vert_vel_P_ctrl, ctrl_vertIntSig, "vert_vel_P_ctrl",lineT, valid);
 printCsvValue(vert_vel_output, ctrl_vertIntSig, "vert_vel_output",lineT, valid);
 printCsvValue(vert_acc_status, ctrl_vertIntSig, "vert_acc_status",lineT, valid);
 printCsvValue(vert_acc_cmd_id, ctrl_vertIntSig, "vert_acc_cmd_id",lineT, valid);
 printCsvValue(vert_acc_feedback_id, ctrl_vertIntSig, "vert_acc_feedback_id",lineT, valid);
 printCsvValue(vert_acc_cmd, ctrl_vertIntSig, "vert_acc_cmd",lineT, valid);
 printCsvValue(vert_acc_feedback, ctrl_vertIntSig, "vert_acc_feedback",lineT, valid);
 printCsvValue(vert_acc_P_ctrl, ctrl_vertIntSig, "vert_acc_P_ctrl",lineT, valid);
 printCsvValue(vert_acc_I_ctrl, ctrl_vertIntSig, "vert_acc_I_ctrl",lineT, valid);
 printCsvValue(vert_acc_feedforward, ctrl_vertIntSig, "vert_acc_feedforward",lineT, valid);
 printCsvValue(vert_acc_output, ctrl_vertIntSig, "vert_acc_output",lineT, valid);
 printCsvValue(vert_thrust_status, ctrl_vertIntSig, "vert_thrust_status",lineT, valid);
 printCsvValue(vert_thrust_cmd_id, ctrl_vertIntSig, "vert_thrust_cmd_id",lineT, valid);
 printCsvValue(vert_thrust_feedback_id, ctrl_vertIntSig, "vert_thrust_feedback_id",lineT, valid);
 printCsvValue(vert_thrust_cmd_data, ctrl_vertIntSig, "vert_thrust_cmd_data",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
