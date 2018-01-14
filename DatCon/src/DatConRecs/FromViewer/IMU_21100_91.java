package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_21100_91 extends Record {
protected boolean valid = false;

protected short gyro_x_21100 = (short)0;
protected short gyro_y_21100 = (short)0;
protected short gyro_z_21100 = (short)0;
protected short acc_x_21100 = (short)0;
protected short acc_y_21100 = (short)0;
protected short acc_z_21100 = (short)0;
protected long cnt_21100 = (long)0;

      public IMU_21100_91(ConvertDat convertDat) {
           super(convertDat, 91, 16);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 gyro_x_21100 = _payload.getShort(0);
 gyro_y_21100 = _payload.getShort(2);
 gyro_z_21100 = _payload.getShort(4);
 acc_x_21100 = _payload.getShort(6);
 acc_y_21100 = _payload.getShort(8);
 acc_z_21100 = _payload.getShort(10);
 cnt_21100 = _payload.getUnsignedInt(12);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_21100IntSig = Signal
.SeriesInt("IMU_21100", "", null, Units.noUnits);
    protected static Signal IMU_21100FloatSig = Signal
.SeriesFloat("IMU_21100", "", null, Units.noUnits);
    protected static Signal IMU_21100DoubleSig = Signal
.SeriesDouble("IMU_21100", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(gyro_x_21100, IMU_21100IntSig, "gyro_x_21100",lineT, valid);
 printCsvValue(gyro_y_21100, IMU_21100IntSig, "gyro_y_21100",lineT, valid);
 printCsvValue(gyro_z_21100, IMU_21100IntSig, "gyro_z_21100",lineT, valid);
 printCsvValue(acc_x_21100, IMU_21100IntSig, "acc_x_21100",lineT, valid);
 printCsvValue(acc_y_21100, IMU_21100IntSig, "acc_y_21100",lineT, valid);
 printCsvValue(acc_z_21100, IMU_21100IntSig, "acc_z_21100",lineT, valid);
 printCsvValue(cnt_21100, IMU_21100IntSig, "cnt_21100",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
