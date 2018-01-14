package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_ATTI_02_2050 extends Record {
protected boolean valid = false;

protected double longtiI_02 = (double)0;
protected double latiI_02 = (double)0;
protected float altiI_02 = (float)0;
protected float acc_x_02 = (float)0;
protected float acc_y_02 = (float)0;
protected float acc_z_02 = (float)0;
protected float gyro_x_02 = (float)0;
protected float gyro_y_02 = (float)0;
protected float gyro_z_02 = (float)0;
protected float press_02 = (float)0;
protected float q0_02 = (float)0;
protected float q1_02 = (float)0;
protected float q2_02 = (float)0;
protected float q3_02 = (float)0;
protected float ag_x_02 = (float)0;
protected float ag_y_02 = (float)0;
protected float ag_z_02 = (float)0;
protected float vg_x_02 = (float)0;
protected float vg_y_02 = (float)0;
protected float vg_z_02 = (float)0;
protected float gb_x_02 = (float)0;
protected float gb_y_02 = (float)0;
protected float gb_z_02 = (float)0;
protected short m_x_02 = (short)0;
protected short m_y_02 = (short)0;
protected short m_z_02 = (short)0;
protected short temp_x_02 = (short)0;
protected short temp_y_02 = (short)0;
protected short temp_z_02 = (short)0;
protected int sensor_monitor_02 = (int)0;
protected int filter_status_02 = (int)0;
protected int svn_02 = (int)0;
protected int cnt_atti_02 = (int)0;

      public IMU_ATTI_02_2050(ConvertDat convertDat) {
           super(convertDat, 2050, 120);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 longtiI_02 = _payload.getDouble(0);
 latiI_02 = _payload.getDouble(8);
 altiI_02 = _payload.getFloat(16);
 acc_x_02 = _payload.getFloat(20);
 acc_y_02 = _payload.getFloat(24);
 acc_z_02 = _payload.getFloat(28);
 gyro_x_02 = _payload.getFloat(32);
 gyro_y_02 = _payload.getFloat(36);
 gyro_z_02 = _payload.getFloat(40);
 press_02 = _payload.getFloat(44);
 q0_02 = _payload.getFloat(48);
 q1_02 = _payload.getFloat(52);
 q2_02 = _payload.getFloat(56);
 q3_02 = _payload.getFloat(60);
 ag_x_02 = _payload.getFloat(64);
 ag_y_02 = _payload.getFloat(68);
 ag_z_02 = _payload.getFloat(72);
 vg_x_02 = _payload.getFloat(76);
 vg_y_02 = _payload.getFloat(80);
 vg_z_02 = _payload.getFloat(84);
 gb_x_02 = _payload.getFloat(88);
 gb_y_02 = _payload.getFloat(92);
 gb_z_02 = _payload.getFloat(96);
 m_x_02 = _payload.getShort(100);
 m_y_02 = _payload.getShort(102);
 m_z_02 = _payload.getShort(104);
 temp_x_02 = _payload.getShort(106);
 temp_y_02 = _payload.getShort(108);
 temp_z_02 = _payload.getShort(110);
 sensor_monitor_02 = _payload.getUnsignedShort(112);
 filter_status_02 = _payload.getUnsignedShort(114);
 svn_02 = _payload.getUnsignedShort(116);
 cnt_atti_02 = _payload.getUnsignedShort(118);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_ATTI_02IntSig = Signal
.SeriesInt("IMU_ATTI_02", "", null, Units.noUnits);
    protected static Signal IMU_ATTI_02FloatSig = Signal
.SeriesFloat("IMU_ATTI_02", "", null, Units.noUnits);
    protected static Signal IMU_ATTI_02DoubleSig = Signal
.SeriesDouble("IMU_ATTI_02", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(longtiI_02, IMU_ATTI_02DoubleSig, "longtiI_02",lineT, valid);
 printCsvValue(latiI_02, IMU_ATTI_02DoubleSig, "latiI_02",lineT, valid);
 printCsvValue(altiI_02, IMU_ATTI_02FloatSig, "altiI_02",lineT, valid);
 printCsvValue(acc_x_02, IMU_ATTI_02FloatSig, "acc_x_02",lineT, valid);
 printCsvValue(acc_y_02, IMU_ATTI_02FloatSig, "acc_y_02",lineT, valid);
 printCsvValue(acc_z_02, IMU_ATTI_02FloatSig, "acc_z_02",lineT, valid);
 printCsvValue(gyro_x_02, IMU_ATTI_02FloatSig, "gyro_x_02",lineT, valid);
 printCsvValue(gyro_y_02, IMU_ATTI_02FloatSig, "gyro_y_02",lineT, valid);
 printCsvValue(gyro_z_02, IMU_ATTI_02FloatSig, "gyro_z_02",lineT, valid);
 printCsvValue(press_02, IMU_ATTI_02FloatSig, "press_02",lineT, valid);
 printCsvValue(q0_02, IMU_ATTI_02FloatSig, "q0_02",lineT, valid);
 printCsvValue(q1_02, IMU_ATTI_02FloatSig, "q1_02",lineT, valid);
 printCsvValue(q2_02, IMU_ATTI_02FloatSig, "q2_02",lineT, valid);
 printCsvValue(q3_02, IMU_ATTI_02FloatSig, "q3_02",lineT, valid);
 printCsvValue(ag_x_02, IMU_ATTI_02FloatSig, "ag_x_02",lineT, valid);
 printCsvValue(ag_y_02, IMU_ATTI_02FloatSig, "ag_y_02",lineT, valid);
 printCsvValue(ag_z_02, IMU_ATTI_02FloatSig, "ag_z_02",lineT, valid);
 printCsvValue(vg_x_02, IMU_ATTI_02FloatSig, "vg_x_02",lineT, valid);
 printCsvValue(vg_y_02, IMU_ATTI_02FloatSig, "vg_y_02",lineT, valid);
 printCsvValue(vg_z_02, IMU_ATTI_02FloatSig, "vg_z_02",lineT, valid);
 printCsvValue(gb_x_02, IMU_ATTI_02FloatSig, "gb_x_02",lineT, valid);
 printCsvValue(gb_y_02, IMU_ATTI_02FloatSig, "gb_y_02",lineT, valid);
 printCsvValue(gb_z_02, IMU_ATTI_02FloatSig, "gb_z_02",lineT, valid);
 printCsvValue(m_x_02, IMU_ATTI_02IntSig, "m_x_02",lineT, valid);
 printCsvValue(m_y_02, IMU_ATTI_02IntSig, "m_y_02",lineT, valid);
 printCsvValue(m_z_02, IMU_ATTI_02IntSig, "m_z_02",lineT, valid);
 printCsvValue(temp_x_02, IMU_ATTI_02IntSig, "temp_x_02",lineT, valid);
 printCsvValue(temp_y_02, IMU_ATTI_02IntSig, "temp_y_02",lineT, valid);
 printCsvValue(temp_z_02, IMU_ATTI_02IntSig, "temp_z_02",lineT, valid);
 printCsvValue(sensor_monitor_02, IMU_ATTI_02IntSig, "sensor_monitor_02",lineT, valid);
 printCsvValue(filter_status_02, IMU_ATTI_02IntSig, "filter_status_02",lineT, valid);
 printCsvValue(svn_02, IMU_ATTI_02IntSig, "svn_02",lineT, valid);
 printCsvValue(cnt_atti_02, IMU_ATTI_02IntSig, "cnt_atti_02",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
