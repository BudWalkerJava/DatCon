package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class fmu_device_run_time_41 extends Record {
protected boolean valid = false;

protected long battery = (long)0;
protected long led = (long)0;
protected long baromter = (long)0;
protected long gyro_acc = (long)0;
protected long imu = (long)0;
protected long vo = (long)0;
protected long ultrasonic = (long)0;
protected long pmu = (long)0;
protected long esc = (long)0;
protected long mc = (long)0;
protected long camera = (long)0;
protected long gps = (long)0;
protected long Compass = (long)0;
protected long gimbal = (long)0;
protected long rc = (long)0;
protected long gear = (long)0;

      public fmu_device_run_time_41(ConvertDat convertDat) {
           super(convertDat, 41, 64);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 battery = _payload.getUnsignedInt(0);
 led = _payload.getUnsignedInt(4);
 baromter = _payload.getUnsignedInt(8);
 gyro_acc = _payload.getUnsignedInt(12);
 imu = _payload.getUnsignedInt(16);
 vo = _payload.getUnsignedInt(20);
 ultrasonic = _payload.getUnsignedInt(24);
 pmu = _payload.getUnsignedInt(28);
 esc = _payload.getUnsignedInt(32);
 mc = _payload.getUnsignedInt(36);
 camera = _payload.getUnsignedInt(40);
 gps = _payload.getUnsignedInt(44);
 Compass = _payload.getUnsignedInt(48);
 gimbal = _payload.getUnsignedInt(52);
 rc = _payload.getUnsignedInt(56);
 gear = _payload.getUnsignedInt(60);
} catch (Exception e) {RecordException(e);}}


    protected static Signal fmu_device_run_timeIntSig = Signal
.SeriesInt("fmu_device_run_time", "", null, Units.noUnits);
    protected static Signal fmu_device_run_timeFloatSig = Signal
.SeriesFloat("fmu_device_run_time", "", null, Units.noUnits);
    protected static Signal fmu_device_run_timeDoubleSig = Signal
.SeriesDouble("fmu_device_run_time", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(battery, fmu_device_run_timeIntSig, "battery",lineT, valid);
 printCsvValue(led, fmu_device_run_timeIntSig, "led",lineT, valid);
 printCsvValue(baromter, fmu_device_run_timeIntSig, "baromter",lineT, valid);
 printCsvValue(gyro_acc, fmu_device_run_timeIntSig, "gyro_acc",lineT, valid);
 printCsvValue(imu, fmu_device_run_timeIntSig, "imu",lineT, valid);
 printCsvValue(vo, fmu_device_run_timeIntSig, "vo",lineT, valid);
 printCsvValue(ultrasonic, fmu_device_run_timeIntSig, "ultrasonic",lineT, valid);
 printCsvValue(pmu, fmu_device_run_timeIntSig, "pmu",lineT, valid);
 printCsvValue(esc, fmu_device_run_timeIntSig, "esc",lineT, valid);
 printCsvValue(mc, fmu_device_run_timeIntSig, "mc",lineT, valid);
 printCsvValue(camera, fmu_device_run_timeIntSig, "camera",lineT, valid);
 printCsvValue(gps, fmu_device_run_timeIntSig, "gps",lineT, valid);
 printCsvValue(Compass, fmu_device_run_timeIntSig, "Compass",lineT, valid);
 printCsvValue(gimbal, fmu_device_run_timeIntSig, "gimbal",lineT, valid);
 printCsvValue(rc, fmu_device_run_timeIntSig, "rc",lineT, valid);
 printCsvValue(gear, fmu_device_run_timeIntSig, "gear",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
