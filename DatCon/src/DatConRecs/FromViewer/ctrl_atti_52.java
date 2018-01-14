package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class ctrl_atti_52 extends Record {
protected boolean valid = false;

protected short horiz_atti_tilt_tag_status = (short)0;
protected short horiz_atti_tilt_tag_cmd_id = (short)0;
protected short horiz_atti_tilt_tag_feedback_id = (short)0;
protected short horiz_atti_torsion_tag_status = (short)0;
protected short horiz_atti_torsion_tag_cmd_id = (short)0;
protected short horiz_atti_torsion_tag_feedback_id = (short)0;
protected float horiz_atti_tgt_acc_x = (float)0;
protected float horiz_atti_tgt_acc_y = (float)0;
protected float horiz_atti_tgt_tilt_x = (float)0;
protected float horiz_atti_tgt_tilt_y = (float)0;
protected float horiz_atti_tgt_body_tilt_x = (float)0;
protected float horiz_atti_tgt_body_tilt_y = (float)0;
protected float horiz_atti_tgt_ground_tilt_x = (float)0;
protected float horiz_atti_tgt_ground_tilt_y = (float)0;
protected float horiz_atti_tgt_tilt_before_limit_x = (float)0;
protected float horiz_atti_tgt_tilt_before_limit_y = (float)0;
protected float horiz_atti_tgt_tilt_after_limit_x = (float)0;
protected float horiz_atti_tgt_tilt_after_limit_y = (float)0;
protected float horiz_atti_tgt_quat_0 = (float)0;
protected float horiz_atti_tgt_quat_1 = (float)0;
protected float horiz_atti_tgt_quat_2 = (float)0;
protected float horiz_atti_tgt_quat_3 = (float)0;
protected float horiz_atti_tgt_torsion = (float)0;
protected float horiz_atti_tgt_torsion_rate = (float)0;
protected float horiz_atti_feedback_quat_0 = (float)0;
protected float horiz_atti_feedback_quat_1 = (float)0;
protected float horiz_atti_feedback_quat_2 = (float)0;
protected float horiz_atti_feedback_quat_3 = (float)0;
protected float horiz_atti_locked_torsion = (float)0;
protected float horiz_atti_err_tilt_x = (float)0;
protected float horiz_atti_err_tilt_y = (float)0;
protected float horiz_atti_err_torsion = (float)0;
protected float horiz_atti_output_x = (float)0;
protected float horiz_atti_output_y = (float)0;
protected float horiz_atti_output_z = (float)0;
protected short horiz_ang_vel_status = (short)0;
protected short horiz_ang_vel_cmd_id = (short)0;
protected short horiz_ang_vel_feedback_id = (short)0;
protected short horiz_ang_vel_cmd_x = (short)0;
protected short horiz_ang_vel_cmd_y = (short)0;
protected short horiz_ang_vel_cmd_z = (short)0;
protected short horiz_ang_vel_feedback_x = (short)0;
protected short horiz_ang_vel_feedback_y = (short)0;
protected short horiz_ang_vel_feedback_z = (short)0;
protected short horiz_ang_vel_feedback_P_x = (short)0;
protected short horiz_ang_vel_feedback_P_y = (short)0;
protected short horiz_ang_vel_feedback_P_z = (short)0;
protected short horiz_ang_vel_feedback_D_x = (short)0;
protected short horiz_ang_vel_feedback_D_y = (short)0;
protected short horiz_ang_vel_feedback_D_z = (short)0;
protected short horiz_ang_vel_output_x = (short)0;
protected short horiz_ang_vel_output_y = (short)0;
protected short horiz_ang_vel_output_z = (short)0;
protected short horiz_ang_acc_status = (short)0;
protected short horiz_ang_acc_cmd_id = (short)0;
protected short horiz_ang_acc_feedback_id = (short)0;
protected short horiz_ang_acc_cmd_x = (short)0;
protected short horiz_ang_acc_cmd_y = (short)0;
protected short horiz_ang_acc_cmd_z = (short)0;
protected short horiz_ang_acc_feedback_x = (short)0;
protected short horiz_ang_acc_feedback_y = (short)0;
protected short horiz_ang_acc_feedback_z = (short)0;
protected short horiz_ang_acc_P_ctrl_x = (short)0;
protected short horiz_ang_acc_P_ctrl_y = (short)0;
protected short horiz_ang_acc_P_ctrl_z = (short)0;
protected short horiz_ang_acc_I_ctrl_x = (short)0;
protected short horiz_ang_acc_I_ctrl_y = (short)0;
protected short horiz_ang_acc_I_ctrl_z = (short)0;
protected short horiz_ang_acc_feedforward_direct_x = (short)0;
protected short horiz_ang_acc_feedforward_direct_y = (short)0;
protected short horiz_ang_acc_feedforward_direct_z = (short)0;
protected short horiz_ang_acc_feedforward_compen_x = (short)0;
protected short horiz_ang_acc_feedforward_compen_y = (short)0;
protected short horiz_ang_acc_feedforward_compen_z = (short)0;
protected short horiz_ang_acc_output_x = (short)0;
protected short horiz_ang_acc_output_y = (short)0;
protected short horiz_ang_acc_output_z = (short)0;

      public ctrl_atti_52(ConvertDat convertDat) {
           super(convertDat, 52, 200);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

horiz_atti_tilt_tag_status = _payload.getUnsignedByte(0);
horiz_atti_tilt_tag_cmd_id = _payload.getUnsignedByte(1);
horiz_atti_tilt_tag_feedback_id = _payload.getUnsignedByte(2);
horiz_atti_torsion_tag_status = _payload.getUnsignedByte(3);
horiz_atti_torsion_tag_cmd_id = _payload.getUnsignedByte(4);
horiz_atti_torsion_tag_feedback_id = _payload.getUnsignedByte(5);
 horiz_atti_tgt_acc_x = _payload.getFloat(6);
 horiz_atti_tgt_acc_y = _payload.getFloat(10);
 horiz_atti_tgt_tilt_x = _payload.getFloat(14);
 horiz_atti_tgt_tilt_y = _payload.getFloat(18);
 horiz_atti_tgt_body_tilt_x = _payload.getFloat(22);
 horiz_atti_tgt_body_tilt_y = _payload.getFloat(26);
 horiz_atti_tgt_ground_tilt_x = _payload.getFloat(30);
 horiz_atti_tgt_ground_tilt_y = _payload.getFloat(34);
 horiz_atti_tgt_tilt_before_limit_x = _payload.getFloat(38);
 horiz_atti_tgt_tilt_before_limit_y = _payload.getFloat(42);
 horiz_atti_tgt_tilt_after_limit_x = _payload.getFloat(46);
 horiz_atti_tgt_tilt_after_limit_y = _payload.getFloat(50);
 horiz_atti_tgt_quat_0 = _payload.getFloat(54);
 horiz_atti_tgt_quat_1 = _payload.getFloat(58);
 horiz_atti_tgt_quat_2 = _payload.getFloat(62);
 horiz_atti_tgt_quat_3 = _payload.getFloat(66);
 horiz_atti_tgt_torsion = _payload.getFloat(70);
 horiz_atti_tgt_torsion_rate = _payload.getFloat(74);
 horiz_atti_feedback_quat_0 = _payload.getFloat(78);
 horiz_atti_feedback_quat_1 = _payload.getFloat(82);
 horiz_atti_feedback_quat_2 = _payload.getFloat(86);
 horiz_atti_feedback_quat_3 = _payload.getFloat(90);
 horiz_atti_locked_torsion = _payload.getFloat(94);
 horiz_atti_err_tilt_x = _payload.getFloat(98);
 horiz_atti_err_tilt_y = _payload.getFloat(102);
 horiz_atti_err_torsion = _payload.getFloat(106);
 horiz_atti_output_x = _payload.getFloat(110);
 horiz_atti_output_y = _payload.getFloat(114);
 horiz_atti_output_z = _payload.getFloat(118);
horiz_ang_vel_status = _payload.getUnsignedByte(122);
horiz_ang_vel_cmd_id = _payload.getUnsignedByte(123);
horiz_ang_vel_feedback_id = _payload.getUnsignedByte(124);
 horiz_ang_vel_cmd_x = _payload.getShort(125);
 horiz_ang_vel_cmd_y = _payload.getShort(127);
 horiz_ang_vel_cmd_z = _payload.getShort(129);
 horiz_ang_vel_feedback_x = _payload.getShort(131);
 horiz_ang_vel_feedback_y = _payload.getShort(133);
 horiz_ang_vel_feedback_z = _payload.getShort(135);
 horiz_ang_vel_feedback_P_x = _payload.getShort(137);
 horiz_ang_vel_feedback_P_y = _payload.getShort(139);
 horiz_ang_vel_feedback_P_z = _payload.getShort(141);
 horiz_ang_vel_feedback_D_x = _payload.getShort(143);
 horiz_ang_vel_feedback_D_y = _payload.getShort(145);
 horiz_ang_vel_feedback_D_z = _payload.getShort(147);
 horiz_ang_vel_output_x = _payload.getShort(149);
 horiz_ang_vel_output_y = _payload.getShort(151);
 horiz_ang_vel_output_z = _payload.getShort(153);
horiz_ang_acc_status = _payload.getUnsignedByte(155);
horiz_ang_acc_cmd_id = _payload.getUnsignedByte(156);
horiz_ang_acc_feedback_id = _payload.getUnsignedByte(157);
 horiz_ang_acc_cmd_x = _payload.getShort(158);
 horiz_ang_acc_cmd_y = _payload.getShort(160);
 horiz_ang_acc_cmd_z = _payload.getShort(162);
 horiz_ang_acc_feedback_x = _payload.getShort(164);
 horiz_ang_acc_feedback_y = _payload.getShort(166);
 horiz_ang_acc_feedback_z = _payload.getShort(168);
 horiz_ang_acc_P_ctrl_x = _payload.getShort(170);
 horiz_ang_acc_P_ctrl_y = _payload.getShort(172);
 horiz_ang_acc_P_ctrl_z = _payload.getShort(174);
 horiz_ang_acc_I_ctrl_x = _payload.getShort(176);
 horiz_ang_acc_I_ctrl_y = _payload.getShort(178);
 horiz_ang_acc_I_ctrl_z = _payload.getShort(180);
 horiz_ang_acc_feedforward_direct_x = _payload.getShort(182);
 horiz_ang_acc_feedforward_direct_y = _payload.getShort(184);
 horiz_ang_acc_feedforward_direct_z = _payload.getShort(186);
 horiz_ang_acc_feedforward_compen_x = _payload.getShort(188);
 horiz_ang_acc_feedforward_compen_y = _payload.getShort(190);
 horiz_ang_acc_feedforward_compen_z = _payload.getShort(192);
 horiz_ang_acc_output_x = _payload.getShort(194);
 horiz_ang_acc_output_y = _payload.getShort(196);
 horiz_ang_acc_output_z = _payload.getShort(198);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ctrl_attiIntSig = Signal
.SeriesInt("ctrl_atti", "", null, Units.noUnits);
    protected static Signal ctrl_attiFloatSig = Signal
.SeriesFloat("ctrl_atti", "", null, Units.noUnits);
    protected static Signal ctrl_attiDoubleSig = Signal
.SeriesDouble("ctrl_atti", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(horiz_atti_tilt_tag_status, ctrl_attiIntSig, "horiz_atti_tilt_tag_status",lineT, valid);
 printCsvValue(horiz_atti_tilt_tag_cmd_id, ctrl_attiIntSig, "horiz_atti_tilt_tag_cmd_id",lineT, valid);
 printCsvValue(horiz_atti_tilt_tag_feedback_id, ctrl_attiIntSig, "horiz_atti_tilt_tag_feedback_id",lineT, valid);
 printCsvValue(horiz_atti_torsion_tag_status, ctrl_attiIntSig, "horiz_atti_torsion_tag_status",lineT, valid);
 printCsvValue(horiz_atti_torsion_tag_cmd_id, ctrl_attiIntSig, "horiz_atti_torsion_tag_cmd_id",lineT, valid);
 printCsvValue(horiz_atti_torsion_tag_feedback_id, ctrl_attiIntSig, "horiz_atti_torsion_tag_feedback_id",lineT, valid);
 printCsvValue(horiz_atti_tgt_acc_x, ctrl_attiFloatSig, "horiz_atti_tgt_acc_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_acc_y, ctrl_attiFloatSig, "horiz_atti_tgt_acc_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_x, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_y, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_body_tilt_x, ctrl_attiFloatSig, "horiz_atti_tgt_body_tilt_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_body_tilt_y, ctrl_attiFloatSig, "horiz_atti_tgt_body_tilt_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_ground_tilt_x, ctrl_attiFloatSig, "horiz_atti_tgt_ground_tilt_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_ground_tilt_y, ctrl_attiFloatSig, "horiz_atti_tgt_ground_tilt_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_before_limit_x, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_before_limit_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_before_limit_y, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_before_limit_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_after_limit_x, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_after_limit_x",lineT, valid);
 printCsvValue(horiz_atti_tgt_tilt_after_limit_y, ctrl_attiFloatSig, "horiz_atti_tgt_tilt_after_limit_y",lineT, valid);
 printCsvValue(horiz_atti_tgt_quat_0, ctrl_attiFloatSig, "horiz_atti_tgt_quat_0",lineT, valid);
 printCsvValue(horiz_atti_tgt_quat_1, ctrl_attiFloatSig, "horiz_atti_tgt_quat_1",lineT, valid);
 printCsvValue(horiz_atti_tgt_quat_2, ctrl_attiFloatSig, "horiz_atti_tgt_quat_2",lineT, valid);
 printCsvValue(horiz_atti_tgt_quat_3, ctrl_attiFloatSig, "horiz_atti_tgt_quat_3",lineT, valid);
 printCsvValue(horiz_atti_tgt_torsion, ctrl_attiFloatSig, "horiz_atti_tgt_torsion",lineT, valid);
 printCsvValue(horiz_atti_tgt_torsion_rate, ctrl_attiFloatSig, "horiz_atti_tgt_torsion_rate",lineT, valid);
 printCsvValue(horiz_atti_feedback_quat_0, ctrl_attiFloatSig, "horiz_atti_feedback_quat_0",lineT, valid);
 printCsvValue(horiz_atti_feedback_quat_1, ctrl_attiFloatSig, "horiz_atti_feedback_quat_1",lineT, valid);
 printCsvValue(horiz_atti_feedback_quat_2, ctrl_attiFloatSig, "horiz_atti_feedback_quat_2",lineT, valid);
 printCsvValue(horiz_atti_feedback_quat_3, ctrl_attiFloatSig, "horiz_atti_feedback_quat_3",lineT, valid);
 printCsvValue(horiz_atti_locked_torsion, ctrl_attiFloatSig, "horiz_atti_locked_torsion",lineT, valid);
 printCsvValue(horiz_atti_err_tilt_x, ctrl_attiFloatSig, "horiz_atti_err_tilt_x",lineT, valid);
 printCsvValue(horiz_atti_err_tilt_y, ctrl_attiFloatSig, "horiz_atti_err_tilt_y",lineT, valid);
 printCsvValue(horiz_atti_err_torsion, ctrl_attiFloatSig, "horiz_atti_err_torsion",lineT, valid);
 printCsvValue(horiz_atti_output_x, ctrl_attiFloatSig, "horiz_atti_output_x",lineT, valid);
 printCsvValue(horiz_atti_output_y, ctrl_attiFloatSig, "horiz_atti_output_y",lineT, valid);
 printCsvValue(horiz_atti_output_z, ctrl_attiFloatSig, "horiz_atti_output_z",lineT, valid);
 printCsvValue(horiz_ang_vel_status, ctrl_attiIntSig, "horiz_ang_vel_status",lineT, valid);
 printCsvValue(horiz_ang_vel_cmd_id, ctrl_attiIntSig, "horiz_ang_vel_cmd_id",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_id, ctrl_attiIntSig, "horiz_ang_vel_feedback_id",lineT, valid);
 printCsvValue(horiz_ang_vel_cmd_x, ctrl_attiIntSig, "horiz_ang_vel_cmd_x",lineT, valid);
 printCsvValue(horiz_ang_vel_cmd_y, ctrl_attiIntSig, "horiz_ang_vel_cmd_y",lineT, valid);
 printCsvValue(horiz_ang_vel_cmd_z, ctrl_attiIntSig, "horiz_ang_vel_cmd_z",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_x, ctrl_attiIntSig, "horiz_ang_vel_feedback_x",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_y, ctrl_attiIntSig, "horiz_ang_vel_feedback_y",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_z, ctrl_attiIntSig, "horiz_ang_vel_feedback_z",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_P_x, ctrl_attiIntSig, "horiz_ang_vel_feedback_P_x",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_P_y, ctrl_attiIntSig, "horiz_ang_vel_feedback_P_y",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_P_z, ctrl_attiIntSig, "horiz_ang_vel_feedback_P_z",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_D_x, ctrl_attiIntSig, "horiz_ang_vel_feedback_D_x",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_D_y, ctrl_attiIntSig, "horiz_ang_vel_feedback_D_y",lineT, valid);
 printCsvValue(horiz_ang_vel_feedback_D_z, ctrl_attiIntSig, "horiz_ang_vel_feedback_D_z",lineT, valid);
 printCsvValue(horiz_ang_vel_output_x, ctrl_attiIntSig, "horiz_ang_vel_output_x",lineT, valid);
 printCsvValue(horiz_ang_vel_output_y, ctrl_attiIntSig, "horiz_ang_vel_output_y",lineT, valid);
 printCsvValue(horiz_ang_vel_output_z, ctrl_attiIntSig, "horiz_ang_vel_output_z",lineT, valid);
 printCsvValue(horiz_ang_acc_status, ctrl_attiIntSig, "horiz_ang_acc_status",lineT, valid);
 printCsvValue(horiz_ang_acc_cmd_id, ctrl_attiIntSig, "horiz_ang_acc_cmd_id",lineT, valid);
 printCsvValue(horiz_ang_acc_feedback_id, ctrl_attiIntSig, "horiz_ang_acc_feedback_id",lineT, valid);
 printCsvValue(horiz_ang_acc_cmd_x, ctrl_attiIntSig, "horiz_ang_acc_cmd_x",lineT, valid);
 printCsvValue(horiz_ang_acc_cmd_y, ctrl_attiIntSig, "horiz_ang_acc_cmd_y",lineT, valid);
 printCsvValue(horiz_ang_acc_cmd_z, ctrl_attiIntSig, "horiz_ang_acc_cmd_z",lineT, valid);
 printCsvValue(horiz_ang_acc_feedback_x, ctrl_attiIntSig, "horiz_ang_acc_feedback_x",lineT, valid);
 printCsvValue(horiz_ang_acc_feedback_y, ctrl_attiIntSig, "horiz_ang_acc_feedback_y",lineT, valid);
 printCsvValue(horiz_ang_acc_feedback_z, ctrl_attiIntSig, "horiz_ang_acc_feedback_z",lineT, valid);
 printCsvValue(horiz_ang_acc_P_ctrl_x, ctrl_attiIntSig, "horiz_ang_acc_P_ctrl_x",lineT, valid);
 printCsvValue(horiz_ang_acc_P_ctrl_y, ctrl_attiIntSig, "horiz_ang_acc_P_ctrl_y",lineT, valid);
 printCsvValue(horiz_ang_acc_P_ctrl_z, ctrl_attiIntSig, "horiz_ang_acc_P_ctrl_z",lineT, valid);
 printCsvValue(horiz_ang_acc_I_ctrl_x, ctrl_attiIntSig, "horiz_ang_acc_I_ctrl_x",lineT, valid);
 printCsvValue(horiz_ang_acc_I_ctrl_y, ctrl_attiIntSig, "horiz_ang_acc_I_ctrl_y",lineT, valid);
 printCsvValue(horiz_ang_acc_I_ctrl_z, ctrl_attiIntSig, "horiz_ang_acc_I_ctrl_z",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_direct_x, ctrl_attiIntSig, "horiz_ang_acc_feedforward_direct_x",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_direct_y, ctrl_attiIntSig, "horiz_ang_acc_feedforward_direct_y",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_direct_z, ctrl_attiIntSig, "horiz_ang_acc_feedforward_direct_z",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_compen_x, ctrl_attiIntSig, "horiz_ang_acc_feedforward_compen_x",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_compen_y, ctrl_attiIntSig, "horiz_ang_acc_feedforward_compen_y",lineT, valid);
 printCsvValue(horiz_ang_acc_feedforward_compen_z, ctrl_attiIntSig, "horiz_ang_acc_feedforward_compen_z",lineT, valid);
 printCsvValue(horiz_ang_acc_output_x, ctrl_attiIntSig, "horiz_ang_acc_output_x",lineT, valid);
 printCsvValue(horiz_ang_acc_output_y, ctrl_attiIntSig, "horiz_ang_acc_output_y",lineT, valid);
 printCsvValue(horiz_ang_acc_output_z, ctrl_attiIntSig, "horiz_ang_acc_output_z",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
