package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class Controller_0 extends Record {
protected boolean valid = false;

protected long g_real_clock = (long)0;
protected short g_real_input_channel_COMMAND_AILERON = (short)0;
protected short g_real_input_channel_COMMAND_ELEVATOR = (short)0;
protected short g_real_input_channel_COMMAND_THROTTLE = (short)0;
protected short g_real_input_channel_COMMAND_RUDDER = (short)0;
protected short g_real_input_channel_COMMAND_MODE = (short)0;
protected short g_real_input_channel_COMMAND_IOC = (short)0;
protected short g_real_input_channel_COMMAND_GO_HOME = (short)0;
protected short g_real_input_channel_COMMAND_D4 = (short)0;
protected short g_real_input_control_core_pitch = (short)0;
protected short g_real_input_control_core_roll = (short)0;
protected short g_real_input_control_core_alti = (short)0;
protected short g_real_input_control_core_tail = (short)0;
protected short g_real_status_cotrol_command_mode = (short)0;
protected short g_real_status_control_real_mode = (short)0;
protected short g_real_status_ioc_control_command_mode = (short)0;
protected short g_real_status_rc_state = (short)0;
protected short g_real_status_motor_status = (short)0;
protected long imu_package_lost_count = (long)0;
protected int g_real_status_main_batery_voltage = (int)0;
protected short imu_temp_real_ctl_out_per = (short)0;
protected short us_fail_flag = (short)0;
protected short gps_signal_levels = (short)0;
protected short gps_ctrl_levels = (short)0;

      public Controller_0(ConvertDat convertDat) {
           super(convertDat, 0, 43);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 g_real_clock = _payload.getUnsignedInt(0);
 g_real_input_channel_COMMAND_AILERON = _payload.getShort(4);
 g_real_input_channel_COMMAND_ELEVATOR = _payload.getShort(6);
 g_real_input_channel_COMMAND_THROTTLE = _payload.getShort(8);
 g_real_input_channel_COMMAND_RUDDER = _payload.getShort(10);
 g_real_input_channel_COMMAND_MODE = _payload.getShort(12);
 g_real_input_channel_COMMAND_IOC = _payload.getShort(14);
 g_real_input_channel_COMMAND_GO_HOME = _payload.getShort(16);
 g_real_input_channel_COMMAND_D4 = _payload.getShort(18);
 g_real_input_control_core_pitch = _payload.getShort(20);
 g_real_input_control_core_roll = _payload.getShort(22);
 g_real_input_control_core_alti = _payload.getShort(24);
 g_real_input_control_core_tail = _payload.getShort(26);
g_real_status_cotrol_command_mode = _payload.getUnsignedByte(28);
g_real_status_control_real_mode = _payload.getUnsignedByte(29);
g_real_status_ioc_control_command_mode = _payload.getUnsignedByte(30);
g_real_status_rc_state = _payload.getUnsignedByte(31);
g_real_status_motor_status = _payload.getUnsignedByte(32);
 imu_package_lost_count = _payload.getUnsignedInt(33);
 g_real_status_main_batery_voltage = _payload.getUnsignedShort(37);
imu_temp_real_ctl_out_per = _payload.getUnsignedByte(39);
us_fail_flag = _payload.getUnsignedByte(40);
gps_signal_levels = _payload.getUnsignedByte(41);
gps_ctrl_levels = _payload.getUnsignedByte(42);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ControllerIntSig = Signal
.SeriesInt("Controller", "", null, Units.noUnits);
    protected static Signal ControllerFloatSig = Signal
.SeriesFloat("Controller", "", null, Units.noUnits);
    protected static Signal ControllerDoubleSig = Signal
.SeriesDouble("Controller", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(g_real_clock, ControllerIntSig, "g_real_clock",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_AILERON, ControllerIntSig, "g_real_input_channel_COMMAND_AILERON",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_ELEVATOR, ControllerIntSig, "g_real_input_channel_COMMAND_ELEVATOR",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_THROTTLE, ControllerIntSig, "g_real_input_channel_COMMAND_THROTTLE",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_RUDDER, ControllerIntSig, "g_real_input_channel_COMMAND_RUDDER",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_MODE, ControllerIntSig, "g_real_input_channel_COMMAND_MODE",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_IOC, ControllerIntSig, "g_real_input_channel_COMMAND_IOC",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_GO_HOME, ControllerIntSig, "g_real_input_channel_COMMAND_GO_HOME",lineT, valid);
 printCsvValue(g_real_input_channel_COMMAND_D4, ControllerIntSig, "g_real_input_channel_COMMAND_D4",lineT, valid);
 printCsvValue(g_real_input_control_core_pitch, ControllerIntSig, "g_real_input_control_core_pitch",lineT, valid);
 printCsvValue(g_real_input_control_core_roll, ControllerIntSig, "g_real_input_control_core_roll",lineT, valid);
 printCsvValue(g_real_input_control_core_alti, ControllerIntSig, "g_real_input_control_core_alti",lineT, valid);
 printCsvValue(g_real_input_control_core_tail, ControllerIntSig, "g_real_input_control_core_tail",lineT, valid);
 printCsvValue(g_real_status_cotrol_command_mode, ControllerIntSig, "g_real_status_cotrol_command_mode",lineT, valid);
 printCsvValue(g_real_status_control_real_mode, ControllerIntSig, "g_real_status_control_real_mode",lineT, valid);
 printCsvValue(g_real_status_ioc_control_command_mode, ControllerIntSig, "g_real_status_ioc_control_command_mode",lineT, valid);
 printCsvValue(g_real_status_rc_state, ControllerIntSig, "g_real_status_rc_state",lineT, valid);
 printCsvValue(g_real_status_motor_status, ControllerIntSig, "g_real_status_motor_status",lineT, valid);
 printCsvValue(imu_package_lost_count, ControllerIntSig, "imu_package_lost_count",lineT, valid);
 printCsvValue(g_real_status_main_batery_voltage, ControllerIntSig, "g_real_status_main_batery_voltage",lineT, valid);
 printCsvValue(imu_temp_real_ctl_out_per, ControllerIntSig, "imu_temp_real_ctl_out_per",lineT, valid);
 printCsvValue(us_fail_flag, ControllerIntSig, "us_fail_flag",lineT, valid);
 printCsvValue(gps_signal_levels, ControllerIntSig, "gps_signal_levels",lineT, valid);
 printCsvValue(gps_ctrl_levels, ControllerIntSig, "gps_ctrl_levels",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
