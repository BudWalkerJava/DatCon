package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class osd_general_12 extends Record {
protected boolean valid = false;

protected double longtitude = (double)0;
protected double latitude = (double)0;
protected short relative_height = (short)0;
protected short vgx = (short)0;
protected short vgy = (short)0;
protected short vgz = (short)0;
protected short pitch = (short)0;
protected short roll = (short)0;
protected short yaw = (short)0;
protected short mode1 = (short)0;
protected short latest_cmd = (short)0;
protected long controller_state = (long)0;
protected short gps_nums = (short)0;
protected short gohome_landing_reason = (short)0;
protected short start_fail_reason = (short)0;
protected short controller_state_ext = (short)0;
protected short rsvd2 = (short)0;
protected short ultrasonic_height = (short)0;
protected int motor_startup_time = (int)0;
protected short motor_startup_times = (short)0;
protected short bat_alarm1 = (short)0;
protected short bat_alarm2 = (short)0;
protected short version_match = (short)0;
protected short product_type = (short)0;

      public osd_general_12(ConvertDat convertDat) {
           super(convertDat, 12, 49);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 longtitude = _payload.getDouble(0);
 latitude = _payload.getDouble(8);
 relative_height = _payload.getShort(16);
 vgx = _payload.getShort(18);
 vgy = _payload.getShort(20);
 vgz = _payload.getShort(22);
 pitch = _payload.getShort(24);
 roll = _payload.getShort(26);
 yaw = _payload.getShort(28);
mode1 = _payload.getUnsignedByte(30);
latest_cmd = _payload.getUnsignedByte(31);
 controller_state = _payload.getUnsignedInt(32);
gps_nums = _payload.getUnsignedByte(36);
gohome_landing_reason = _payload.getUnsignedByte(37);
start_fail_reason = _payload.getUnsignedByte(38);
controller_state_ext = _payload.getUnsignedByte(39);
rsvd2 = _payload.getUnsignedByte(40);
ultrasonic_height = _payload.getUnsignedByte(41);
 motor_startup_time = _payload.getUnsignedShort(42);
motor_startup_times = _payload.getUnsignedByte(44);
bat_alarm1 = _payload.getUnsignedByte(45);
bat_alarm2 = _payload.getUnsignedByte(46);
version_match = _payload.getUnsignedByte(47);
product_type = _payload.getUnsignedByte(48);
} catch (Exception e) {RecordException(e);}}


    protected static Signal osd_generalIntSig = Signal
.SeriesInt("osd_general", "", null, Units.noUnits);
    protected static Signal osd_generalFloatSig = Signal
.SeriesFloat("osd_general", "", null, Units.noUnits);
    protected static Signal osd_generalDoubleSig = Signal
.SeriesDouble("osd_general", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(longtitude, osd_generalDoubleSig, "longtitude",lineT, valid);
 printCsvValue(latitude, osd_generalDoubleSig, "latitude",lineT, valid);
 printCsvValue(relative_height, osd_generalIntSig, "relative_height",lineT, valid);
 printCsvValue(vgx, osd_generalIntSig, "vgx",lineT, valid);
 printCsvValue(vgy, osd_generalIntSig, "vgy",lineT, valid);
 printCsvValue(vgz, osd_generalIntSig, "vgz",lineT, valid);
 printCsvValue(pitch, osd_generalIntSig, "pitch",lineT, valid);
 printCsvValue(roll, osd_generalIntSig, "roll",lineT, valid);
 printCsvValue(yaw, osd_generalIntSig, "yaw",lineT, valid);
 printCsvValue(mode1, osd_generalIntSig, "mode1",lineT, valid);
 printCsvValue(latest_cmd, osd_generalIntSig, "latest_cmd",lineT, valid);
 printCsvValue(controller_state, osd_generalIntSig, "controller_state",lineT, valid);
 printCsvValue(gps_nums, osd_generalIntSig, "gps_nums",lineT, valid);
 printCsvValue(gohome_landing_reason, osd_generalIntSig, "gohome_landing_reason",lineT, valid);
 printCsvValue(start_fail_reason, osd_generalIntSig, "start_fail_reason",lineT, valid);
 printCsvValue(controller_state_ext, osd_generalIntSig, "controller_state_ext",lineT, valid);
 printCsvValue(rsvd2, osd_generalIntSig, "rsvd2",lineT, valid);
 printCsvValue(ultrasonic_height, osd_generalIntSig, "ultrasonic_height",lineT, valid);
 printCsvValue(motor_startup_time, osd_generalIntSig, "motor_startup_time",lineT, valid);
 printCsvValue(motor_startup_times, osd_generalIntSig, "motor_startup_times",lineT, valid);
 printCsvValue(bat_alarm1, osd_generalIntSig, "bat_alarm1",lineT, valid);
 printCsvValue(bat_alarm2, osd_generalIntSig, "bat_alarm2",lineT, valid);
 printCsvValue(version_match, osd_generalIntSig, "version_match",lineT, valid);
 printCsvValue(product_type, osd_generalIntSig, "product_type",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
