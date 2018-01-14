package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_EX_01_2065 extends Record {
protected boolean valid = false;

protected float vo_vx_01 = (float)0;
protected float vo_vy_01 = (float)0;
protected float vo_vz_01 = (float)0;
protected float vo_px_01 = (float)0;
protected float vo_py_01 = (float)0;
protected float vo_pz_01 = (float)0;
protected float us_v_01 = (float)0;
protected float us_p_01 = (float)0;
protected int vo_flag_navi_01 = (int)0;
protected int imu_err_flag_01 = (int)0;
protected int vo_flag_rsv_01 = (int)0;
protected int imu_ex_cnt_01 = (int)0;

      public IMU_EX_01_2065(ConvertDat convertDat) {
           super(convertDat, 2065, 40);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 vo_vx_01 = _payload.getFloat(0);
 vo_vy_01 = _payload.getFloat(4);
 vo_vz_01 = _payload.getFloat(8);
 vo_px_01 = _payload.getFloat(12);
 vo_py_01 = _payload.getFloat(16);
 vo_pz_01 = _payload.getFloat(20);
 us_v_01 = _payload.getFloat(24);
 us_p_01 = _payload.getFloat(28);
 vo_flag_navi_01 = _payload.getUnsignedShort(32);
 imu_err_flag_01 = _payload.getUnsignedShort(34);
 vo_flag_rsv_01 = _payload.getUnsignedShort(36);
 imu_ex_cnt_01 = _payload.getUnsignedShort(38);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_EX_01IntSig = Signal
.SeriesInt("IMU_EX_01", "", null, Units.noUnits);
    protected static Signal IMU_EX_01FloatSig = Signal
.SeriesFloat("IMU_EX_01", "", null, Units.noUnits);
    protected static Signal IMU_EX_01DoubleSig = Signal
.SeriesDouble("IMU_EX_01", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(vo_vx_01, IMU_EX_01FloatSig, "vo_vx_01",lineT, valid);
 printCsvValue(vo_vy_01, IMU_EX_01FloatSig, "vo_vy_01",lineT, valid);
 printCsvValue(vo_vz_01, IMU_EX_01FloatSig, "vo_vz_01",lineT, valid);
 printCsvValue(vo_px_01, IMU_EX_01FloatSig, "vo_px_01",lineT, valid);
 printCsvValue(vo_py_01, IMU_EX_01FloatSig, "vo_py_01",lineT, valid);
 printCsvValue(vo_pz_01, IMU_EX_01FloatSig, "vo_pz_01",lineT, valid);
 printCsvValue(us_v_01, IMU_EX_01FloatSig, "us_v_01",lineT, valid);
 printCsvValue(us_p_01, IMU_EX_01FloatSig, "us_p_01",lineT, valid);
 printCsvValue(vo_flag_navi_01, IMU_EX_01IntSig, "vo_flag_navi_01",lineT, valid);
 printCsvValue(imu_err_flag_01, IMU_EX_01IntSig, "imu_err_flag_01",lineT, valid);
 printCsvValue(vo_flag_rsv_01, IMU_EX_01IntSig, "vo_flag_rsv_01",lineT, valid);
 printCsvValue(imu_ex_cnt_01, IMU_EX_01IntSig, "imu_ex_cnt_01",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
