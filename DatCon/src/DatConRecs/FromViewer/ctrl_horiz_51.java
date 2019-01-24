package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class ctrl_horiz_51 extends Record {
protected boolean valid = false;

protected short horiz_module_module_mode = (short)0;
protected short horiz_module_horiz_hover_state = (short)0;
protected short horiz_module_horiz_hover_enable = (short)0;
protected double horiz_module_horiz_hover_abs_pos_0 = (double)0;
protected double horiz_module_horiz_hover_abs_pos_1 = (double)0;
protected float horiz_module_horiz_hover_rel_pos_0 = (float)0;
protected float horiz_module_horiz_hover_rel_pos_1 = (float)0;
protected float horiz_module_horiz_hover_brake_timer = (float)0;
protected float horiz_module_horiz_pos_offset_0 = (float)0;
protected float horiz_module_horiz_pos_offset_1 = (float)0;
protected short api_horiz_ctrl_mode = (short)0;
protected short api_horiz_ctrl_cmd_id = (short)0;
protected short api_torsion_ctrl_mode = (short)0;
protected short api_torsion_ctrl_cmd_id = (short)0;
protected short api_atti_cmd_type = (short)0;
protected short horiz_pos_tag_status = (short)0;
protected short horiz_pos_tag_cmd_id = (short)0;
protected short horiz_pos_tag_feedback_id = (short)0;
protected short horiz_pos_cmd_x = (short)0;
protected short horiz_pos_cmd_y = (short)0;
protected short horiz_pos_feedback_x = (short)0;
protected short horiz_pos_feedback_y = (short)0;
protected short horiz_pos_P_ctrl_x = (short)0;
protected short horiz_pos_P_ctrl_y = (short)0;
protected short horiz_pos_D_ctrl_x = (short)0;
protected short horiz_pos_D_ctrl_y = (short)0;
protected short horiz_pos_output_x = (short)0;
protected short horiz_pos_output_y = (short)0;
protected short horiz_vel_tag_status = (short)0;
protected short horiz_vel_tag_cmd_id = (short)0;
protected short horiz_vel_tag_feedback_id = (short)0;
protected short horiz_vel_cmd_x = (short)0;
protected short horiz_vel_cmd_y = (short)0;
protected short horiz_vel_feedback_x = (short)0;
protected short horiz_vel_feedback_y = (short)0;
protected short horiz_vel_P_ctrl_x = (short)0;
protected short horiz_vel_P_ctrl_y = (short)0;
protected short horiz_vel_D_ctrl_x = (short)0;
protected short horiz_vel_D_ctrl_y = (short)0;
protected short horiz_vel_I_ctrl_x = (short)0;
protected short horiz_vel_I_ctrl_y = (short)0;
protected short horiz_vel_feedforward_x = (short)0;
protected short horiz_vel_feedforward_y = (short)0;
protected short horiz_vel_output_x = (short)0;
protected short horiz_vel_output_y = (short)0;
protected short horiz_acc_tag_status = (short)0;
protected short horiz_acc_tag_cmd_id = (short)0;
protected short horiz_acc_tag_feedback_id = (short)0;
protected short horiz_acc_cmd_x = (short)0;
protected short horiz_acc_cmd_y = (short)0;
protected short horiz_acc_feedback_x = (short)0;
protected short horiz_acc_feedback_y = (short)0;
protected short horiz_acc_feedback_I_x = (short)0;
protected short horiz_acc_feedback_I_y = (short)0;
protected short horiz_acc_feedforward_x = (short)0;
protected short horiz_acc_feedforward_y = (short)0;
protected short horiz_acc_output_x = (short)0;
protected short horiz_acc_output_y = (short)0;

      public ctrl_horiz_51(ConvertDat convertDat) {
           super(convertDat, 51, 121);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

horiz_module_module_mode = _payload.getUnsignedByte(0);
horiz_module_horiz_hover_state = _payload.getUnsignedByte(1);
horiz_module_horiz_hover_enable = _payload.getUnsignedByte(2);
 horiz_module_horiz_hover_abs_pos_0 = _payload.getDouble(3);
 horiz_module_horiz_hover_abs_pos_1 = _payload.getDouble(11);
 horiz_module_horiz_hover_rel_pos_0 = _payload.getFloat(19);
 horiz_module_horiz_hover_rel_pos_1 = _payload.getFloat(23);
 horiz_module_horiz_hover_brake_timer = _payload.getFloat(27);
 horiz_module_horiz_pos_offset_0 = _payload.getFloat(31);
 horiz_module_horiz_pos_offset_1 = _payload.getFloat(35);
api_horiz_ctrl_mode = _payload.getUnsignedByte(39);
api_horiz_ctrl_cmd_id = _payload.getUnsignedByte(40);
api_torsion_ctrl_mode = _payload.getUnsignedByte(41);
api_torsion_ctrl_cmd_id = _payload.getUnsignedByte(42);
api_atti_cmd_type = _payload.getUnsignedByte(43);
horiz_pos_tag_status = _payload.getUnsignedByte(44);
horiz_pos_tag_cmd_id = _payload.getUnsignedByte(45);
horiz_pos_tag_feedback_id = _payload.getUnsignedByte(46);
 horiz_pos_cmd_x = _payload.getShort(47);
 horiz_pos_cmd_y = _payload.getShort(49);
 horiz_pos_feedback_x = _payload.getShort(51);
 horiz_pos_feedback_y = _payload.getShort(53);
 horiz_pos_P_ctrl_x = _payload.getShort(55);
 horiz_pos_P_ctrl_y = _payload.getShort(57);
 horiz_pos_D_ctrl_x = _payload.getShort(59);
 horiz_pos_D_ctrl_y = _payload.getShort(61);
 horiz_pos_output_x = _payload.getShort(63);
 horiz_pos_output_y = _payload.getShort(65);
horiz_vel_tag_status = _payload.getUnsignedByte(67);
horiz_vel_tag_cmd_id = _payload.getUnsignedByte(68);
horiz_vel_tag_feedback_id = _payload.getUnsignedByte(69);
 horiz_vel_cmd_x = _payload.getShort(70);
 horiz_vel_cmd_y = _payload.getShort(72);
 horiz_vel_feedback_x = _payload.getShort(74);
 horiz_vel_feedback_y = _payload.getShort(76);
 horiz_vel_P_ctrl_x = _payload.getShort(78);
 horiz_vel_P_ctrl_y = _payload.getShort(80);
 horiz_vel_D_ctrl_x = _payload.getShort(82);
 horiz_vel_D_ctrl_y = _payload.getShort(84);
 horiz_vel_I_ctrl_x = _payload.getShort(86);
 horiz_vel_I_ctrl_y = _payload.getShort(88);
 horiz_vel_feedforward_x = _payload.getShort(90);
 horiz_vel_feedforward_y = _payload.getShort(92);
 horiz_vel_output_x = _payload.getShort(94);
 horiz_vel_output_y = _payload.getShort(96);
horiz_acc_tag_status = _payload.getUnsignedByte(98);
horiz_acc_tag_cmd_id = _payload.getUnsignedByte(99);
horiz_acc_tag_feedback_id = _payload.getUnsignedByte(100);
 horiz_acc_cmd_x = _payload.getShort(101);
 horiz_acc_cmd_y = _payload.getShort(103);
 horiz_acc_feedback_x = _payload.getShort(105);
 horiz_acc_feedback_y = _payload.getShort(107);
 horiz_acc_feedback_I_x = _payload.getShort(109);
 horiz_acc_feedback_I_y = _payload.getShort(111);
 horiz_acc_feedforward_x = _payload.getShort(113);
 horiz_acc_feedforward_y = _payload.getShort(115);
 horiz_acc_output_x = _payload.getShort(117);
 horiz_acc_output_y = _payload.getShort(119);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ctrl_horizIntSig = Signal
.SeriesInt("ctrl_horiz", "", null, Units.noUnits);
    protected static Signal ctrl_horizFloatSig = Signal
.SeriesFloat("ctrl_horiz", "", null, Units.noUnits);
    protected static Signal ctrl_horizDoubleSig = Signal
.SeriesDouble("ctrl_horiz", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(horiz_module_module_mode, ctrl_horizIntSig, "horiz_module_module_mode",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_state, ctrl_horizIntSig, "horiz_module_horiz_hover_state",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_enable, ctrl_horizIntSig, "horiz_module_horiz_hover_enable",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_abs_pos_0, ctrl_horizDoubleSig, "horiz_module_horiz_hover_abs_pos_0",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_abs_pos_1, ctrl_horizDoubleSig, "horiz_module_horiz_hover_abs_pos_1",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_rel_pos_0, ctrl_horizFloatSig, "horiz_module_horiz_hover_rel_pos_0",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_rel_pos_1, ctrl_horizFloatSig, "horiz_module_horiz_hover_rel_pos_1",lineT, valid);
 printCsvValue(horiz_module_horiz_hover_brake_timer, ctrl_horizFloatSig, "horiz_module_horiz_hover_brake_timer",lineT, valid);
 printCsvValue(horiz_module_horiz_pos_offset_0, ctrl_horizFloatSig, "horiz_module_horiz_pos_offset_0",lineT, valid);
 printCsvValue(horiz_module_horiz_pos_offset_1, ctrl_horizFloatSig, "horiz_module_horiz_pos_offset_1",lineT, valid);
 printCsvValue(api_horiz_ctrl_mode, ctrl_horizIntSig, "api_horiz_ctrl_mode",lineT, valid);
 printCsvValue(api_horiz_ctrl_cmd_id, ctrl_horizIntSig, "api_horiz_ctrl_cmd_id",lineT, valid);
 printCsvValue(api_torsion_ctrl_mode, ctrl_horizIntSig, "api_torsion_ctrl_mode",lineT, valid);
 printCsvValue(api_torsion_ctrl_cmd_id, ctrl_horizIntSig, "api_torsion_ctrl_cmd_id",lineT, valid);
 printCsvValue(api_atti_cmd_type, ctrl_horizIntSig, "api_atti_cmd_type",lineT, valid);
 printCsvValue(horiz_pos_tag_status, ctrl_horizIntSig, "horiz_pos_tag_status",lineT, valid);
 printCsvValue(horiz_pos_tag_cmd_id, ctrl_horizIntSig, "horiz_pos_tag_cmd_id",lineT, valid);
 printCsvValue(horiz_pos_tag_feedback_id, ctrl_horizIntSig, "horiz_pos_tag_feedback_id",lineT, valid);
 printCsvValue(horiz_pos_cmd_x, ctrl_horizIntSig, "horiz_pos_cmd_x",lineT, valid);
 printCsvValue(horiz_pos_cmd_y, ctrl_horizIntSig, "horiz_pos_cmd_y",lineT, valid);
 printCsvValue(horiz_pos_feedback_x, ctrl_horizIntSig, "horiz_pos_feedback_x",lineT, valid);
 printCsvValue(horiz_pos_feedback_y, ctrl_horizIntSig, "horiz_pos_feedback_y",lineT, valid);
 printCsvValue(horiz_pos_P_ctrl_x, ctrl_horizIntSig, "horiz_pos_P_ctrl_x",lineT, valid);
 printCsvValue(horiz_pos_P_ctrl_y, ctrl_horizIntSig, "horiz_pos_P_ctrl_y",lineT, valid);
 printCsvValue(horiz_pos_D_ctrl_x, ctrl_horizIntSig, "horiz_pos_D_ctrl_x",lineT, valid);
 printCsvValue(horiz_pos_D_ctrl_y, ctrl_horizIntSig, "horiz_pos_D_ctrl_y",lineT, valid);
 printCsvValue(horiz_pos_output_x, ctrl_horizIntSig, "horiz_pos_output_x",lineT, valid);
 printCsvValue(horiz_pos_output_y, ctrl_horizIntSig, "horiz_pos_output_y",lineT, valid);
 printCsvValue(horiz_vel_tag_status, ctrl_horizIntSig, "horiz_vel_tag_status",lineT, valid);
 printCsvValue(horiz_vel_tag_cmd_id, ctrl_horizIntSig, "horiz_vel_tag_cmd_id",lineT, valid);
 printCsvValue(horiz_vel_tag_feedback_id, ctrl_horizIntSig, "horiz_vel_tag_feedback_id",lineT, valid);
 printCsvValue(horiz_vel_cmd_x, ctrl_horizIntSig, "horiz_vel_cmd_x",lineT, valid);
 printCsvValue(horiz_vel_cmd_y, ctrl_horizIntSig, "horiz_vel_cmd_y",lineT, valid);
 printCsvValue(horiz_vel_feedback_x, ctrl_horizIntSig, "horiz_vel_feedback_x",lineT, valid);
 printCsvValue(horiz_vel_feedback_y, ctrl_horizIntSig, "horiz_vel_feedback_y",lineT, valid);
 printCsvValue(horiz_vel_P_ctrl_x, ctrl_horizIntSig, "horiz_vel_P_ctrl_x",lineT, valid);
 printCsvValue(horiz_vel_P_ctrl_y, ctrl_horizIntSig, "horiz_vel_P_ctrl_y",lineT, valid);
 printCsvValue(horiz_vel_D_ctrl_x, ctrl_horizIntSig, "horiz_vel_D_ctrl_x",lineT, valid);
 printCsvValue(horiz_vel_D_ctrl_y, ctrl_horizIntSig, "horiz_vel_D_ctrl_y",lineT, valid);
 printCsvValue(horiz_vel_I_ctrl_x, ctrl_horizIntSig, "horiz_vel_I_ctrl_x",lineT, valid);
 printCsvValue(horiz_vel_I_ctrl_y, ctrl_horizIntSig, "horiz_vel_I_ctrl_y",lineT, valid);
 printCsvValue(horiz_vel_feedforward_x, ctrl_horizIntSig, "horiz_vel_feedforward_x",lineT, valid);
 printCsvValue(horiz_vel_feedforward_y, ctrl_horizIntSig, "horiz_vel_feedforward_y",lineT, valid);
 printCsvValue(horiz_vel_output_x, ctrl_horizIntSig, "horiz_vel_output_x",lineT, valid);
 printCsvValue(horiz_vel_output_y, ctrl_horizIntSig, "horiz_vel_output_y",lineT, valid);
 printCsvValue(horiz_acc_tag_status, ctrl_horizIntSig, "horiz_acc_tag_status",lineT, valid);
 printCsvValue(horiz_acc_tag_cmd_id, ctrl_horizIntSig, "horiz_acc_tag_cmd_id",lineT, valid);
 printCsvValue(horiz_acc_tag_feedback_id, ctrl_horizIntSig, "horiz_acc_tag_feedback_id",lineT, valid);
 printCsvValue(horiz_acc_cmd_x, ctrl_horizIntSig, "horiz_acc_cmd_x",lineT, valid);
 printCsvValue(horiz_acc_cmd_y, ctrl_horizIntSig, "horiz_acc_cmd_y",lineT, valid);
 printCsvValue(horiz_acc_feedback_x, ctrl_horizIntSig, "horiz_acc_feedback_x",lineT, valid);
 printCsvValue(horiz_acc_feedback_y, ctrl_horizIntSig, "horiz_acc_feedback_y",lineT, valid);
 printCsvValue(horiz_acc_feedback_I_x, ctrl_horizIntSig, "horiz_acc_feedback_I_x",lineT, valid);
 printCsvValue(horiz_acc_feedback_I_y, ctrl_horizIntSig, "horiz_acc_feedback_I_y",lineT, valid);
 printCsvValue(horiz_acc_feedforward_x, ctrl_horizIntSig, "horiz_acc_feedforward_x",lineT, valid);
 printCsvValue(horiz_acc_feedforward_y, ctrl_horizIntSig, "horiz_acc_feedforward_y",lineT, valid);
 printCsvValue(horiz_acc_output_x, ctrl_horizIntSig, "horiz_acc_output_x",lineT, valid);
 printCsvValue(horiz_acc_output_y, ctrl_horizIntSig, "horiz_acc_output_y",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
