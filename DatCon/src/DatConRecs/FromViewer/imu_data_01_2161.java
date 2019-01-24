package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class imu_data_01_2161 extends Record {
protected boolean valid = false;

protected float imu_gyro_tempX_01 = (float)0;
protected float imu_gyro_tempY_01 = (float)0;
protected float imu_gyro_tempZ_01 = (float)0;
protected float imu_gyro_x_01 = (float)0;
protected float imu_gyro_y_01 = (float)0;
protected float imu_gyro_z_01 = (float)0;
protected float imu_acc_x_01 = (float)0;
protected float imu_acc_y_01 = (float)0;
protected float imu_acc_z_01 = (float)0;
protected float imu_airpress_01 = (float)0;
protected float imu_Vin_01 = (float)0;
protected float imu_Ref_01 = (float)0;

      public imu_data_01_2161(ConvertDat convertDat) {
           super(convertDat, 2161, 48);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 imu_gyro_tempX_01 = _payload.getFloat(0);
 imu_gyro_tempY_01 = _payload.getFloat(4);
 imu_gyro_tempZ_01 = _payload.getFloat(8);
 imu_gyro_x_01 = _payload.getFloat(12);
 imu_gyro_y_01 = _payload.getFloat(16);
 imu_gyro_z_01 = _payload.getFloat(20);
 imu_acc_x_01 = _payload.getFloat(24);
 imu_acc_y_01 = _payload.getFloat(28);
 imu_acc_z_01 = _payload.getFloat(32);
 imu_airpress_01 = _payload.getFloat(36);
 imu_Vin_01 = _payload.getFloat(40);
 imu_Ref_01 = _payload.getFloat(44);
} catch (Exception e) {RecordException(e);}}


    protected static Signal imu_data_01IntSig = Signal
.SeriesInt("imu_data_01", "", null, Units.noUnits);
    protected static Signal imu_data_01FloatSig = Signal
.SeriesFloat("imu_data_01", "", null, Units.noUnits);
    protected static Signal imu_data_01DoubleSig = Signal
.SeriesDouble("imu_data_01", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(imu_gyro_tempX_01, imu_data_01FloatSig, "imu_gyro_tempX_01",lineT, valid);
 printCsvValue(imu_gyro_tempY_01, imu_data_01FloatSig, "imu_gyro_tempY_01",lineT, valid);
 printCsvValue(imu_gyro_tempZ_01, imu_data_01FloatSig, "imu_gyro_tempZ_01",lineT, valid);
 printCsvValue(imu_gyro_x_01, imu_data_01FloatSig, "imu_gyro_x_01",lineT, valid);
 printCsvValue(imu_gyro_y_01, imu_data_01FloatSig, "imu_gyro_y_01",lineT, valid);
 printCsvValue(imu_gyro_z_01, imu_data_01FloatSig, "imu_gyro_z_01",lineT, valid);
 printCsvValue(imu_acc_x_01, imu_data_01FloatSig, "imu_acc_x_01",lineT, valid);
 printCsvValue(imu_acc_y_01, imu_data_01FloatSig, "imu_acc_y_01",lineT, valid);
 printCsvValue(imu_acc_z_01, imu_data_01FloatSig, "imu_acc_z_01",lineT, valid);
 printCsvValue(imu_airpress_01, imu_data_01FloatSig, "imu_airpress_01",lineT, valid);
 printCsvValue(imu_Vin_01, imu_data_01FloatSig, "imu_Vin_01",lineT, valid);
 printCsvValue(imu_Ref_01, imu_data_01FloatSig, "imu_Ref_01",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
