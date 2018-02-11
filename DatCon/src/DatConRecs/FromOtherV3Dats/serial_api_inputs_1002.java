package src.DatConRecs.FromOtherV3Dats;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class serial_api_inputs_1002 extends Record {
protected boolean valid = false;

protected short sdk_ctrl_F = (short)0;
protected short sdk_roll_x = (short)0;
protected short sdk_pitch_y = (short)0;
protected short sdk_thr_z = (short)0;
protected short sdk_yaw = (short)0;
protected short sdk_fdfd_x = (short)0;
protected short sdk_fdfd_y = (short)0;
protected short ctrl_dev = (short)0;
protected short sub_mode = (short)0;
protected short open_req = (short)0;
protected short open_ack = (short)0;
protected short cmd_req = (short)0;
protected short cmd_ack = (short)0;
protected short avoid_E = (short)0;
protected short bit_S = (short)0;
protected short fact_cnt = (short)0;
protected short f_test = (short)0;

      public serial_api_inputs_1002(ConvertDat convertDat) {
           super(convertDat, 1002, 23);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

sdk_ctrl_F = _payload.getUnsignedByte(0);
 sdk_roll_x = _payload.getShort(1);
 sdk_pitch_y = _payload.getShort(3);
 sdk_thr_z = _payload.getShort(5);
 sdk_yaw = _payload.getShort(7);
 sdk_fdfd_x = _payload.getShort(9);
 sdk_fdfd_y = _payload.getShort(11);
ctrl_dev = _payload.getUnsignedByte(13);
sub_mode = _payload.getUnsignedByte(14);
open_req = _payload.getUnsignedByte(15);
open_ack = _payload.getUnsignedByte(16);
cmd_req = _payload.getUnsignedByte(17);
cmd_ack = _payload.getUnsignedByte(18);
avoid_E = _payload.getUnsignedByte(19);
bit_S = _payload.getUnsignedByte(20);
fact_cnt = _payload.getUnsignedByte(21);
f_test = _payload.getUnsignedByte(22);
} catch (Exception e) {RecordException(e);}}


    protected static Signal serial_api_inputsIntSig = Signal
.SeriesInt("serial_api_inputs", "", null, Units.noUnits);
    protected static Signal serial_api_inputsFloatSig = Signal
.SeriesFloat("serial_api_inputs", "", null, Units.noUnits);
    protected static Signal serial_api_inputsDoubleSig = Signal
.SeriesDouble("serial_api_inputs", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(sdk_ctrl_F, serial_api_inputsIntSig, "sdk_ctrl_F",lineT, valid);
 printCsvValue(sdk_roll_x, serial_api_inputsIntSig, "sdk_roll_x",lineT, valid);
 printCsvValue(sdk_pitch_y, serial_api_inputsIntSig, "sdk_pitch_y",lineT, valid);
 printCsvValue(sdk_thr_z, serial_api_inputsIntSig, "sdk_thr_z",lineT, valid);
 printCsvValue(sdk_yaw, serial_api_inputsIntSig, "sdk_yaw",lineT, valid);
 printCsvValue(sdk_fdfd_x, serial_api_inputsIntSig, "sdk_fdfd_x",lineT, valid);
 printCsvValue(sdk_fdfd_y, serial_api_inputsIntSig, "sdk_fdfd_y",lineT, valid);
 printCsvValue(ctrl_dev, serial_api_inputsIntSig, "ctrl_dev",lineT, valid);
 printCsvValue(sub_mode, serial_api_inputsIntSig, "sub_mode",lineT, valid);
 printCsvValue(open_req, serial_api_inputsIntSig, "open_req",lineT, valid);
 printCsvValue(open_ack, serial_api_inputsIntSig, "open_ack",lineT, valid);
 printCsvValue(cmd_req, serial_api_inputsIntSig, "cmd_req",lineT, valid);
 printCsvValue(cmd_ack, serial_api_inputsIntSig, "cmd_ack",lineT, valid);
 printCsvValue(avoid_E, serial_api_inputsIntSig, "avoid_E",lineT, valid);
 printCsvValue(bit_S, serial_api_inputsIntSig, "bit_S",lineT, valid);
 printCsvValue(fact_cnt, serial_api_inputsIntSig, "fact_cnt",lineT, valid);
 printCsvValue(f_test, serial_api_inputsIntSig, "f_test",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
