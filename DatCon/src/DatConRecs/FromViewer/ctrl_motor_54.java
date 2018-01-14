package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class ctrl_motor_54 extends Record {
protected boolean valid = false;

protected short horiz_motor_status = (short)0;
protected short horiz_motor_cmd_id = (short)0;
protected short horiz_motor_feedback_id = (short)0;
protected int thrust_1 = (int)0;
protected int thrust_2 = (int)0;
protected int thrust_3 = (int)0;
protected int thrust_4 = (int)0;
protected int thrust_5 = (int)0;
protected int thrust_6 = (int)0;
protected int thrust_7 = (int)0;
protected int thrust_8 = (int)0;
protected int pwm_1 = (int)0;
protected int pwm_2 = (int)0;
protected int pwm_3 = (int)0;
protected int pwm_4 = (int)0;
protected int pwm_5 = (int)0;
protected int pwm_6 = (int)0;
protected int pwm_7 = (int)0;
protected int pwm_8 = (int)0;

      public ctrl_motor_54(ConvertDat convertDat) {
           super(convertDat, 54, 35);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

horiz_motor_status = _payload.getUnsignedByte(0);
horiz_motor_cmd_id = _payload.getUnsignedByte(1);
horiz_motor_feedback_id = _payload.getUnsignedByte(2);
 thrust_1 = _payload.getUnsignedShort(3);
 thrust_2 = _payload.getUnsignedShort(5);
 thrust_3 = _payload.getUnsignedShort(7);
 thrust_4 = _payload.getUnsignedShort(9);
 thrust_5 = _payload.getUnsignedShort(11);
 thrust_6 = _payload.getUnsignedShort(13);
 thrust_7 = _payload.getUnsignedShort(15);
 thrust_8 = _payload.getUnsignedShort(17);
 pwm_1 = _payload.getUnsignedShort(19);
 pwm_2 = _payload.getUnsignedShort(21);
 pwm_3 = _payload.getUnsignedShort(23);
 pwm_4 = _payload.getUnsignedShort(25);
 pwm_5 = _payload.getUnsignedShort(27);
 pwm_6 = _payload.getUnsignedShort(29);
 pwm_7 = _payload.getUnsignedShort(31);
 pwm_8 = _payload.getUnsignedShort(33);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ctrl_motorIntSig = Signal
.SeriesInt("ctrl_motor", "", null, Units.noUnits);
    protected static Signal ctrl_motorFloatSig = Signal
.SeriesFloat("ctrl_motor", "", null, Units.noUnits);
    protected static Signal ctrl_motorDoubleSig = Signal
.SeriesDouble("ctrl_motor", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(horiz_motor_status, ctrl_motorIntSig, "horiz_motor_status",lineT, valid);
 printCsvValue(horiz_motor_cmd_id, ctrl_motorIntSig, "horiz_motor_cmd_id",lineT, valid);
 printCsvValue(horiz_motor_feedback_id, ctrl_motorIntSig, "horiz_motor_feedback_id",lineT, valid);
 printCsvValue(thrust_1, ctrl_motorIntSig, "thrust_1",lineT, valid);
 printCsvValue(thrust_2, ctrl_motorIntSig, "thrust_2",lineT, valid);
 printCsvValue(thrust_3, ctrl_motorIntSig, "thrust_3",lineT, valid);
 printCsvValue(thrust_4, ctrl_motorIntSig, "thrust_4",lineT, valid);
 printCsvValue(thrust_5, ctrl_motorIntSig, "thrust_5",lineT, valid);
 printCsvValue(thrust_6, ctrl_motorIntSig, "thrust_6",lineT, valid);
 printCsvValue(thrust_7, ctrl_motorIntSig, "thrust_7",lineT, valid);
 printCsvValue(thrust_8, ctrl_motorIntSig, "thrust_8",lineT, valid);
 printCsvValue(pwm_1, ctrl_motorIntSig, "pwm_1",lineT, valid);
 printCsvValue(pwm_2, ctrl_motorIntSig, "pwm_2",lineT, valid);
 printCsvValue(pwm_3, ctrl_motorIntSig, "pwm_3",lineT, valid);
 printCsvValue(pwm_4, ctrl_motorIntSig, "pwm_4",lineT, valid);
 printCsvValue(pwm_5, ctrl_motorIntSig, "pwm_5",lineT, valid);
 printCsvValue(pwm_6, ctrl_motorIntSig, "pwm_6",lineT, valid);
 printCsvValue(pwm_7, ctrl_motorIntSig, "pwm_7",lineT, valid);
 printCsvValue(pwm_8, ctrl_motorIntSig, "pwm_8",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
