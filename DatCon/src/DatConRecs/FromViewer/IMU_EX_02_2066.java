package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_EX_02_2066 extends Record {
protected boolean valid = false;

protected float vo_vx_02 = (float)0;
protected float vo_vy_02 = (float)0;
protected float vo_vz_02 = (float)0;
protected float vo_px_02 = (float)0;
protected float vo_py_02 = (float)0;
protected float vo_pz_02 = (float)0;
protected float us_v_02 = (float)0;
protected float us_p_02 = (float)0;
protected int vo_flag_navi_02 = (int)0;
protected int imu_err_flag_02 = (int)0;
protected int vo_flag_rsv_02 = (int)0;
protected int imu_ex_cnt_02 = (int)0;

      public IMU_EX_02_2066(ConvertDat convertDat) {
           super(convertDat, 2066, 40);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 vo_vx_02 = _payload.getFloat(0);
 vo_vy_02 = _payload.getFloat(4);
 vo_vz_02 = _payload.getFloat(8);
 vo_px_02 = _payload.getFloat(12);
 vo_py_02 = _payload.getFloat(16);
 vo_pz_02 = _payload.getFloat(20);
 us_v_02 = _payload.getFloat(24);
 us_p_02 = _payload.getFloat(28);
 vo_flag_navi_02 = _payload.getUnsignedShort(32);
 imu_err_flag_02 = _payload.getUnsignedShort(34);
 vo_flag_rsv_02 = _payload.getUnsignedShort(36);
 imu_ex_cnt_02 = _payload.getUnsignedShort(38);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_EX_02IntSig = Signal
.SeriesInt("IMU_EX_02", "", null, Units.noUnits);
    protected static Signal IMU_EX_02FloatSig = Signal
.SeriesFloat("IMU_EX_02", "", null, Units.noUnits);
    protected static Signal IMU_EX_02DoubleSig = Signal
.SeriesDouble("IMU_EX_02", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(vo_vx_02, IMU_EX_02FloatSig, "vo_vx_02",lineT, valid);
 printCsvValue(vo_vy_02, IMU_EX_02FloatSig, "vo_vy_02",lineT, valid);
 printCsvValue(vo_vz_02, IMU_EX_02FloatSig, "vo_vz_02",lineT, valid);
 printCsvValue(vo_px_02, IMU_EX_02FloatSig, "vo_px_02",lineT, valid);
 printCsvValue(vo_py_02, IMU_EX_02FloatSig, "vo_py_02",lineT, valid);
 printCsvValue(vo_pz_02, IMU_EX_02FloatSig, "vo_pz_02",lineT, valid);
 printCsvValue(us_v_02, IMU_EX_02FloatSig, "us_v_02",lineT, valid);
 printCsvValue(us_p_02, IMU_EX_02FloatSig, "us_p_02",lineT, valid);
 printCsvValue(vo_flag_navi_02, IMU_EX_02IntSig, "vo_flag_navi_02",lineT, valid);
 printCsvValue(imu_err_flag_02, IMU_EX_02IntSig, "imu_err_flag_02",lineT, valid);
 printCsvValue(vo_flag_rsv_02, IMU_EX_02IntSig, "vo_flag_rsv_02",lineT, valid);
 printCsvValue(imu_ex_cnt_02, IMU_EX_02IntSig, "imu_ex_cnt_02",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
