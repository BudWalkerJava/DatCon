package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class imu_data_00_2160 extends Record {
protected boolean valid = false;

protected float imu_gyro_tempX_00 = (float)0;
protected float imu_gyro_tempY_00 = (float)0;
protected float imu_gyro_tempZ_00 = (float)0;
protected float imu_gyro_x_00 = (float)0;
protected float imu_gyro_y_00 = (float)0;
protected float imu_gyro_z_00 = (float)0;
protected float imu_acc_x_00 = (float)0;
protected float imu_acc_y_00 = (float)0;
protected float imu_acc_z_00 = (float)0;
protected float imu_airpress_00 = (float)0;
protected float imu_Vin_00 = (float)0;
protected float imu_Ref_00 = (float)0;

      public imu_data_00_2160(ConvertDat convertDat) {
           super(convertDat, 2160, 48);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 imu_gyro_tempX_00 = _payload.getFloat(0);
 imu_gyro_tempY_00 = _payload.getFloat(4);
 imu_gyro_tempZ_00 = _payload.getFloat(8);
 imu_gyro_x_00 = _payload.getFloat(12);
 imu_gyro_y_00 = _payload.getFloat(16);
 imu_gyro_z_00 = _payload.getFloat(20);
 imu_acc_x_00 = _payload.getFloat(24);
 imu_acc_y_00 = _payload.getFloat(28);
 imu_acc_z_00 = _payload.getFloat(32);
 imu_airpress_00 = _payload.getFloat(36);
 imu_Vin_00 = _payload.getFloat(40);
 imu_Ref_00 = _payload.getFloat(44);
} catch (Exception e) {RecordException(e);}}


    protected static Signal imu_data_00IntSig = Signal
.SeriesInt("imu_data_00", "", null, Units.noUnits);
    protected static Signal imu_data_00FloatSig = Signal
.SeriesFloat("imu_data_00", "", null, Units.noUnits);
    protected static Signal imu_data_00DoubleSig = Signal
.SeriesDouble("imu_data_00", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(imu_gyro_tempX_00, imu_data_00FloatSig, "imu_gyro_tempX_00",lineT, valid);
 printCsvValue(imu_gyro_tempY_00, imu_data_00FloatSig, "imu_gyro_tempY_00",lineT, valid);
 printCsvValue(imu_gyro_tempZ_00, imu_data_00FloatSig, "imu_gyro_tempZ_00",lineT, valid);
 printCsvValue(imu_gyro_x_00, imu_data_00FloatSig, "imu_gyro_x_00",lineT, valid);
 printCsvValue(imu_gyro_y_00, imu_data_00FloatSig, "imu_gyro_y_00",lineT, valid);
 printCsvValue(imu_gyro_z_00, imu_data_00FloatSig, "imu_gyro_z_00",lineT, valid);
 printCsvValue(imu_acc_x_00, imu_data_00FloatSig, "imu_acc_x_00",lineT, valid);
 printCsvValue(imu_acc_y_00, imu_data_00FloatSig, "imu_acc_y_00",lineT, valid);
 printCsvValue(imu_acc_z_00, imu_data_00FloatSig, "imu_acc_z_00",lineT, valid);
 printCsvValue(imu_airpress_00, imu_data_00FloatSig, "imu_airpress_00",lineT, valid);
 printCsvValue(imu_Vin_00, imu_data_00FloatSig, "imu_Vin_00",lineT, valid);
 printCsvValue(imu_Ref_00, imu_data_00FloatSig, "imu_Ref_00",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
