package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class imu_cali_data_8 extends Record {
protected boolean valid = false;

protected float gyrox_temp = (float)0;
protected float g_cfg_temper_bias_bias_bw_0 = (float)0;
protected float g_cfg_temper_bias_bias_bw_1 = (float)0;
protected float g_cfg_temper_bias_bias_bw_2 = (float)0;
protected float g_cfg_temper_bias_bias_ba_0 = (float)0;
protected float g_cfg_temper_bias_bias_ba_1 = (float)0;
protected float g_cfg_temper_bias_bias_ba_2 = (float)0;
protected int g_cfg_temper_bias_flag = (int)0;
protected int g_cfg_temper_bias_cali = (int)0;
protected int g_cfg_gyro_bias_flag = (int)0;
protected int g_cfg_gyro_bias_cali = (int)0;
protected short imu_cali_bias_sta_flag = (short)0;
protected short imu_cali_bias_sta_cnt = (short)0;
protected short g_cali_state = (short)0;
protected int clock = (int)0;
protected short time = (short)0;

      public imu_cali_data_8(ConvertDat convertDat) {
           super(convertDat, 8, 43);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 gyrox_temp = _payload.getFloat(0);
 g_cfg_temper_bias_bias_bw_0 = _payload.getFloat(4);
 g_cfg_temper_bias_bias_bw_1 = _payload.getFloat(8);
 g_cfg_temper_bias_bias_bw_2 = _payload.getFloat(12);
 g_cfg_temper_bias_bias_ba_0 = _payload.getFloat(16);
 g_cfg_temper_bias_bias_ba_1 = _payload.getFloat(20);
 g_cfg_temper_bias_bias_ba_2 = _payload.getFloat(24);
 g_cfg_temper_bias_flag = _payload.getUnsignedShort(28);
 g_cfg_temper_bias_cali = _payload.getUnsignedShort(30);
 g_cfg_gyro_bias_flag = _payload.getUnsignedShort(32);
 g_cfg_gyro_bias_cali = _payload.getUnsignedShort(34);
imu_cali_bias_sta_flag = _payload.getUnsignedByte(36);
imu_cali_bias_sta_cnt = _payload.getUnsignedByte(37);
g_cali_state = _payload.getUnsignedByte(38);
 clock = _payload.getUnsignedShort(39);
 time = _payload.getShort(41);
} catch (Exception e) {RecordException(e);}}


    protected static Signal imu_cali_dataIntSig = Signal
.SeriesInt("imu_cali_data", "", null, Units.noUnits);
    protected static Signal imu_cali_dataFloatSig = Signal
.SeriesFloat("imu_cali_data", "", null, Units.noUnits);
    protected static Signal imu_cali_dataDoubleSig = Signal
.SeriesDouble("imu_cali_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(gyrox_temp, imu_cali_dataFloatSig, "gyrox_temp",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_bw_0, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_bw_0",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_bw_1, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_bw_1",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_bw_2, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_bw_2",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_ba_0, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_ba_0",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_ba_1, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_ba_1",lineT, valid);
 printCsvValue(g_cfg_temper_bias_bias_ba_2, imu_cali_dataFloatSig, "g_cfg_temper_bias_bias_ba_2",lineT, valid);
 printCsvValue(g_cfg_temper_bias_flag, imu_cali_dataIntSig, "g_cfg_temper_bias_flag",lineT, valid);
 printCsvValue(g_cfg_temper_bias_cali, imu_cali_dataIntSig, "g_cfg_temper_bias_cali",lineT, valid);
 printCsvValue(g_cfg_gyro_bias_flag, imu_cali_dataIntSig, "g_cfg_gyro_bias_flag",lineT, valid);
 printCsvValue(g_cfg_gyro_bias_cali, imu_cali_dataIntSig, "g_cfg_gyro_bias_cali",lineT, valid);
 printCsvValue(imu_cali_bias_sta_flag, imu_cali_dataIntSig, "imu_cali_bias_sta_flag",lineT, valid);
 printCsvValue(imu_cali_bias_sta_cnt, imu_cali_dataIntSig, "imu_cali_bias_sta_cnt",lineT, valid);
 printCsvValue(g_cali_state, imu_cali_dataIntSig, "g_cali_state",lineT, valid);
 printCsvValue(clock, imu_cali_dataIntSig, "clock",lineT, valid);
 printCsvValue(time, imu_cali_dataIntSig, "time",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
