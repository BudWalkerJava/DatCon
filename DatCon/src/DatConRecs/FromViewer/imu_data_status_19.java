package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class imu_data_status_19 extends Record {
protected boolean valid = false;

protected short start_fan = (short)0;
protected short led_status = (short)0;

      public imu_data_status_19(ConvertDat convertDat) {
           super(convertDat, 19, 2);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

start_fan = _payload.getUnsignedByte(0);
led_status = _payload.getUnsignedByte(1);
} catch (Exception e) {RecordException(e);}}


    protected static Signal imu_data_statusIntSig = Signal
.SeriesInt("imu_data_status", "", null, Units.noUnits);
    protected static Signal imu_data_statusFloatSig = Signal
.SeriesFloat("imu_data_status", "", null, Units.noUnits);
    protected static Signal imu_data_statusDoubleSig = Signal
.SeriesDouble("imu_data_status", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(start_fan, imu_data_statusIntSig, "start_fan",lineT, valid);
 printCsvValue(led_status, imu_data_statusIntSig, "led_status",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
