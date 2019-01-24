package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class serial_api_inputs_60 extends Record {
protected boolean valid = false;

protected short user_req_ctrl_flag = (short)0;
protected float user_req_roll_or_x = (float)0;
protected float user_req_pitch_or_y = (float)0;
protected float user_req_thr_z = (float)0;
protected float user_req_yaw = (float)0;
protected short nav_cur_dev = (short)0;
protected short api_cur_sub_mode = (short)0;
protected short api_user_ctrl_data_health = (short)0;
protected short api_app_ctrl_data_health = (short)0;
protected short user_open_close_req = (short)0;
protected short user_open_close_ack = (short)0;
protected short user_flight_cmd_req = (short)0;
protected short user_flight_cmd_ack = (short)0;

      public serial_api_inputs_60(ConvertDat convertDat) {
           super(convertDat, 60, 25);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

user_req_ctrl_flag = _payload.getUnsignedByte(0);
 user_req_roll_or_x = _payload.getFloat(1);
 user_req_pitch_or_y = _payload.getFloat(5);
 user_req_thr_z = _payload.getFloat(9);
 user_req_yaw = _payload.getFloat(13);
nav_cur_dev = _payload.getUnsignedByte(17);
api_cur_sub_mode = _payload.getUnsignedByte(18);
api_user_ctrl_data_health = _payload.getUnsignedByte(19);
api_app_ctrl_data_health = _payload.getUnsignedByte(20);
user_open_close_req = _payload.getUnsignedByte(21);
user_open_close_ack = _payload.getUnsignedByte(22);
user_flight_cmd_req = _payload.getUnsignedByte(23);
user_flight_cmd_ack = _payload.getUnsignedByte(24);
} catch (Exception e) {RecordException(e);}}


    protected static Signal serial_api_inputsIntSig = Signal
.SeriesInt("serial_api_inputs", "", null, Units.noUnits);
    protected static Signal serial_api_inputsFloatSig = Signal
.SeriesFloat("serial_api_inputs", "", null, Units.noUnits);
    protected static Signal serial_api_inputsDoubleSig = Signal
.SeriesDouble("serial_api_inputs", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(user_req_ctrl_flag, serial_api_inputsIntSig, "user_req_ctrl_flag",lineT, valid);
 printCsvValue(user_req_roll_or_x, serial_api_inputsFloatSig, "user_req_roll_or_x",lineT, valid);
 printCsvValue(user_req_pitch_or_y, serial_api_inputsFloatSig, "user_req_pitch_or_y",lineT, valid);
 printCsvValue(user_req_thr_z, serial_api_inputsFloatSig, "user_req_thr_z",lineT, valid);
 printCsvValue(user_req_yaw, serial_api_inputsFloatSig, "user_req_yaw",lineT, valid);
 printCsvValue(nav_cur_dev, serial_api_inputsIntSig, "nav_cur_dev",lineT, valid);
 printCsvValue(api_cur_sub_mode, serial_api_inputsIntSig, "api_cur_sub_mode",lineT, valid);
 printCsvValue(api_user_ctrl_data_health, serial_api_inputsIntSig, "api_user_ctrl_data_health",lineT, valid);
 printCsvValue(api_app_ctrl_data_health, serial_api_inputsIntSig, "api_app_ctrl_data_health",lineT, valid);
 printCsvValue(user_open_close_req, serial_api_inputsIntSig, "user_open_close_req",lineT, valid);
 printCsvValue(user_open_close_ack, serial_api_inputsIntSig, "user_open_close_ack",lineT, valid);
 printCsvValue(user_flight_cmd_req, serial_api_inputsIntSig, "user_flight_cmd_req",lineT, valid);
 printCsvValue(user_flight_cmd_ack, serial_api_inputsIntSig, "user_flight_cmd_ack",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
