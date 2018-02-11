package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class aircraft_condition_monitor_70 extends Record {
protected boolean valid = false;

protected short craft_flight_mode = (short)0;
protected short near_gnd_state = (short)0;
protected float launch_acc_duration = (float)0;
protected float launch_delta_v = (float)0;
protected short launch_state = (short)0;
protected float thrust_proj_gnd = (float)0;
protected float thrust_proj_gnd_compen = (float)0;
protected float thrust_compensator = (float)0;
protected float hover_thrust = (float)0;
protected float dynamic_thrust = (float)0;
protected float cos_safe_tilt = (float)0;
protected float safe_tilt = (float)0;

      public aircraft_condition_monitor_70(ConvertDat convertDat) {
           super(convertDat, 70, 39);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

craft_flight_mode = _payload.getUnsignedByte(0);
near_gnd_state = _payload.getUnsignedByte(1);
 launch_acc_duration = _payload.getFloat(2);
 launch_delta_v = _payload.getFloat(6);
launch_state = _payload.getUnsignedByte(10);
 thrust_proj_gnd = _payload.getFloat(11);
 thrust_proj_gnd_compen = _payload.getFloat(15);
 thrust_compensator = _payload.getFloat(19);
 hover_thrust = _payload.getFloat(23);
 dynamic_thrust = _payload.getFloat(27);
 cos_safe_tilt = _payload.getFloat(31);
 safe_tilt = _payload.getFloat(35);
} catch (Exception e) {RecordException(e);}}


    protected static Signal aircraft_condition_monitorIntSig = Signal
.SeriesInt("aircraft_condition_monitor", "", null, Units.noUnits);
    protected static Signal aircraft_condition_monitorFloatSig = Signal
.SeriesFloat("aircraft_condition_monitor", "", null, Units.noUnits);
    protected static Signal aircraft_condition_monitorDoubleSig = Signal
.SeriesDouble("aircraft_condition_monitor", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(craft_flight_mode, aircraft_condition_monitorIntSig, "craft_flight_mode",lineT, valid);
 printCsvValue(near_gnd_state, aircraft_condition_monitorIntSig, "near_gnd_state",lineT, valid);
 printCsvValue(launch_acc_duration, aircraft_condition_monitorFloatSig, "launch_acc_duration",lineT, valid);
 printCsvValue(launch_delta_v, aircraft_condition_monitorFloatSig, "launch_delta_v",lineT, valid);
 printCsvValue(launch_state, aircraft_condition_monitorIntSig, "launch_state",lineT, valid);
 printCsvValue(thrust_proj_gnd, aircraft_condition_monitorFloatSig, "thrust_proj_gnd",lineT, valid);
 printCsvValue(thrust_proj_gnd_compen, aircraft_condition_monitorFloatSig, "thrust_proj_gnd_compen",lineT, valid);
 printCsvValue(thrust_compensator, aircraft_condition_monitorFloatSig, "thrust_compensator",lineT, valid);
 printCsvValue(hover_thrust, aircraft_condition_monitorFloatSig, "hover_thrust",lineT, valid);
 printCsvValue(dynamic_thrust, aircraft_condition_monitorFloatSig, "dynamic_thrust",lineT, valid);
 printCsvValue(cos_safe_tilt, aircraft_condition_monitorFloatSig, "cos_safe_tilt",lineT, valid);
 printCsvValue(safe_tilt, aircraft_condition_monitorFloatSig, "safe_tilt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
