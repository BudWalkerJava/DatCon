package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class imu_data_7 extends Record {
protected boolean valid = false;

protected float imu_gyro_tempX = (float)0;
protected float imu_gyro_tempY = (float)0;
protected float imu_gyro_tempZ = (float)0;
protected float imu_gyro_x = (float)0;
protected float imu_gyro_y = (float)0;
protected float imu_gyro_z = (float)0;
protected float imu_acc_x = (float)0;
protected float imu_acc_y = (float)0;
protected float imu_acc_z = (float)0;
protected float imu_airpress = (float)0;
protected float imu_Vin = (float)0;
protected float imu_Ref = (float)0;

      public imu_data_7(ConvertDat convertDat) {
           super(convertDat, 7, 48);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 imu_gyro_tempX = _payload.getFloat(0);
 imu_gyro_tempY = _payload.getFloat(4);
 imu_gyro_tempZ = _payload.getFloat(8);
 imu_gyro_x = _payload.getFloat(12);
 imu_gyro_y = _payload.getFloat(16);
 imu_gyro_z = _payload.getFloat(20);
 imu_acc_x = _payload.getFloat(24);
 imu_acc_y = _payload.getFloat(28);
 imu_acc_z = _payload.getFloat(32);
 imu_airpress = _payload.getFloat(36);
 imu_Vin = _payload.getFloat(40);
 imu_Ref = _payload.getFloat(44);
} catch (Exception e) {RecordException(e);}}


    protected static Signal imu_dataIntSig = Signal
.SeriesInt("imu_data", "", null, Units.noUnits);
    protected static Signal imu_dataFloatSig = Signal
.SeriesFloat("imu_data", "", null, Units.noUnits);
    protected static Signal imu_dataDoubleSig = Signal
.SeriesDouble("imu_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(imu_gyro_tempX, imu_dataFloatSig, "imu_gyro_tempX",lineT, valid);
 printCsvValue(imu_gyro_tempY, imu_dataFloatSig, "imu_gyro_tempY",lineT, valid);
 printCsvValue(imu_gyro_tempZ, imu_dataFloatSig, "imu_gyro_tempZ",lineT, valid);
 printCsvValue(imu_gyro_x, imu_dataFloatSig, "imu_gyro_x",lineT, valid);
 printCsvValue(imu_gyro_y, imu_dataFloatSig, "imu_gyro_y",lineT, valid);
 printCsvValue(imu_gyro_z, imu_dataFloatSig, "imu_gyro_z",lineT, valid);
 printCsvValue(imu_acc_x, imu_dataFloatSig, "imu_acc_x",lineT, valid);
 printCsvValue(imu_acc_y, imu_dataFloatSig, "imu_acc_y",lineT, valid);
 printCsvValue(imu_acc_z, imu_dataFloatSig, "imu_acc_z",lineT, valid);
 printCsvValue(imu_airpress, imu_dataFloatSig, "imu_airpress",lineT, valid);
 printCsvValue(imu_Vin, imu_dataFloatSig, "imu_Vin",lineT, valid);
 printCsvValue(imu_Ref, imu_dataFloatSig, "imu_Ref",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
