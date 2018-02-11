package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_EX_3 extends Record {
protected boolean valid = false;

protected float vo_vx = (float)0;
protected float vo_vy = (float)0;
protected float vo_vz = (float)0;
protected float vo_px = (float)0;
protected float vo_py = (float)0;
protected float vo_pz = (float)0;
protected float us_v = (float)0;
protected float us_p = (float)0;
protected double rtk_longti = (double)0;
protected double rtk_lati = (double)0;
protected float rtk_alti = (float)0;
protected int vo_flag_navi = (int)0;
protected int imu_err_flag = (int)0;
protected int vo_flag_rsv = (int)0;
protected int imu_ex_cnt = (int)0;

      public IMU_EX_3(ConvertDat convertDat) {
           super(convertDat, 3, 60);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 vo_vx = _payload.getFloat(0);
 vo_vy = _payload.getFloat(4);
 vo_vz = _payload.getFloat(8);
 vo_px = _payload.getFloat(12);
 vo_py = _payload.getFloat(16);
 vo_pz = _payload.getFloat(20);
 us_v = _payload.getFloat(24);
 us_p = _payload.getFloat(28);
 rtk_longti = _payload.getDouble(32);
 rtk_lati = _payload.getDouble(40);
 rtk_alti = _payload.getFloat(48);
 vo_flag_navi = _payload.getUnsignedShort(52);
 imu_err_flag = _payload.getUnsignedShort(54);
 vo_flag_rsv = _payload.getUnsignedShort(56);
 imu_ex_cnt = _payload.getUnsignedShort(58);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_EXIntSig = Signal
.SeriesInt("IMU_EX", "", null, Units.noUnits);
    protected static Signal IMU_EXFloatSig = Signal
.SeriesFloat("IMU_EX", "", null, Units.noUnits);
    protected static Signal IMU_EXDoubleSig = Signal
.SeriesDouble("IMU_EX", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(vo_vx, IMU_EXFloatSig, "vo_vx",lineT, valid);
 printCsvValue(vo_vy, IMU_EXFloatSig, "vo_vy",lineT, valid);
 printCsvValue(vo_vz, IMU_EXFloatSig, "vo_vz",lineT, valid);
 printCsvValue(vo_px, IMU_EXFloatSig, "vo_px",lineT, valid);
 printCsvValue(vo_py, IMU_EXFloatSig, "vo_py",lineT, valid);
 printCsvValue(vo_pz, IMU_EXFloatSig, "vo_pz",lineT, valid);
 printCsvValue(us_v, IMU_EXFloatSig, "us_v",lineT, valid);
 printCsvValue(us_p, IMU_EXFloatSig, "us_p",lineT, valid);
 printCsvValue(rtk_longti, IMU_EXDoubleSig, "rtk_longti",lineT, valid);
 printCsvValue(rtk_lati, IMU_EXDoubleSig, "rtk_lati",lineT, valid);
 printCsvValue(rtk_alti, IMU_EXFloatSig, "rtk_alti",lineT, valid);
 printCsvValue(vo_flag_navi, IMU_EXIntSig, "vo_flag_navi",lineT, valid);
 printCsvValue(imu_err_flag, IMU_EXIntSig, "imu_err_flag",lineT, valid);
 printCsvValue(vo_flag_rsv, IMU_EXIntSig, "vo_flag_rsv",lineT, valid);
 printCsvValue(imu_ex_cnt, IMU_EXIntSig, "imu_ex_cnt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
