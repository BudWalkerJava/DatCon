package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class mpu6500_raw_data_35 extends Record {
protected boolean valid = false;

protected float mpu6500_ax = (float)0;
protected float mpu6500_ay = (float)0;
protected float mpu6500_az = (float)0;
protected float mpu6500_wx = (float)0;
protected float mpu6500_wy = (float)0;
protected float mpu6500_wz = (float)0;

      public mpu6500_raw_data_35(ConvertDat convertDat) {
           super(convertDat, 35, 24);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 mpu6500_ax = _payload.getFloat(0);
 mpu6500_ay = _payload.getFloat(4);
 mpu6500_az = _payload.getFloat(8);
 mpu6500_wx = _payload.getFloat(12);
 mpu6500_wy = _payload.getFloat(16);
 mpu6500_wz = _payload.getFloat(20);
} catch (Exception e) {RecordException(e);}}


    protected static Signal mpu6500_raw_dataIntSig = Signal
.SeriesInt("mpu6500_raw_data", "", null, Units.noUnits);
    protected static Signal mpu6500_raw_dataFloatSig = Signal
.SeriesFloat("mpu6500_raw_data", "", null, Units.noUnits);
    protected static Signal mpu6500_raw_dataDoubleSig = Signal
.SeriesDouble("mpu6500_raw_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(mpu6500_ax, mpu6500_raw_dataFloatSig, "mpu6500_ax",lineT, valid);
 printCsvValue(mpu6500_ay, mpu6500_raw_dataFloatSig, "mpu6500_ay",lineT, valid);
 printCsvValue(mpu6500_az, mpu6500_raw_dataFloatSig, "mpu6500_az",lineT, valid);
 printCsvValue(mpu6500_wx, mpu6500_raw_dataFloatSig, "mpu6500_wx",lineT, valid);
 printCsvValue(mpu6500_wy, mpu6500_raw_dataFloatSig, "mpu6500_wy",lineT, valid);
 printCsvValue(mpu6500_wz, mpu6500_raw_dataFloatSig, "mpu6500_wz",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
