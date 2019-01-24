package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_ATTI_1 extends Record {
protected boolean valid = false;

protected double longti = (double)0;
protected double lati = (double)0;
protected float alti = (float)0;
protected float acc_x = (float)0;
protected float acc_y = (float)0;
protected float acc_z = (float)0;
protected float gyro_x = (float)0;
protected float gyro_y = (float)0;
protected float gyro_z = (float)0;
protected float press = (float)0;
protected float q0 = (float)0;
protected float q1 = (float)0;
protected float q2 = (float)0;
protected float q3 = (float)0;
protected float ag_x = (float)0;
protected float ag_y = (float)0;
protected float ag_z = (float)0;
protected float vg_x = (float)0;
protected float vg_y = (float)0;
protected float vg_z = (float)0;
protected float gb_x = (float)0;
protected float gb_y = (float)0;
protected float gb_z = (float)0;
protected short m_x = (short)0;
protected short m_y = (short)0;
protected short m_z = (short)0;
protected short temp_x = (short)0;
protected short temp_y = (short)0;
protected short temp_z = (short)0;
protected int sensor_monitor = (int)0;
protected int filter_status = (int)0;
protected int svn = (int)0;
protected int cnt_atti = (int)0;

      public IMU_ATTI_1(ConvertDat convertDat) {
           super(convertDat, 1, 120);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 longti = _payload.getDouble(0);
 lati = _payload.getDouble(8);
 alti = _payload.getFloat(16);
 acc_x = _payload.getFloat(20);
 acc_y = _payload.getFloat(24);
 acc_z = _payload.getFloat(28);
 gyro_x = _payload.getFloat(32);
 gyro_y = _payload.getFloat(36);
 gyro_z = _payload.getFloat(40);
 press = _payload.getFloat(44);
 q0 = _payload.getFloat(48);
 q1 = _payload.getFloat(52);
 q2 = _payload.getFloat(56);
 q3 = _payload.getFloat(60);
 ag_x = _payload.getFloat(64);
 ag_y = _payload.getFloat(68);
 ag_z = _payload.getFloat(72);
 vg_x = _payload.getFloat(76);
 vg_y = _payload.getFloat(80);
 vg_z = _payload.getFloat(84);
 gb_x = _payload.getFloat(88);
 gb_y = _payload.getFloat(92);
 gb_z = _payload.getFloat(96);
 m_x = _payload.getShort(100);
 m_y = _payload.getShort(102);
 m_z = _payload.getShort(104);
 temp_x = _payload.getShort(106);
 temp_y = _payload.getShort(108);
 temp_z = _payload.getShort(110);
 sensor_monitor = _payload.getUnsignedShort(112);
 filter_status = _payload.getUnsignedShort(114);
 svn = _payload.getUnsignedShort(116);
 cnt_atti = _payload.getUnsignedShort(118);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_ATTIIntSig = Signal
.SeriesInt("IMU_ATTI", "", null, Units.noUnits);
    protected static Signal IMU_ATTIFloatSig = Signal
.SeriesFloat("IMU_ATTI", "", null, Units.noUnits);
    protected static Signal IMU_ATTIDoubleSig = Signal
.SeriesDouble("IMU_ATTI", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(longti, IMU_ATTIDoubleSig, "longti",lineT, valid);
 printCsvValue(lati, IMU_ATTIDoubleSig, "lati",lineT, valid);
 printCsvValue(alti, IMU_ATTIFloatSig, "alti",lineT, valid);
 printCsvValue(acc_x, IMU_ATTIFloatSig, "acc_x",lineT, valid);
 printCsvValue(acc_y, IMU_ATTIFloatSig, "acc_y",lineT, valid);
 printCsvValue(acc_z, IMU_ATTIFloatSig, "acc_z",lineT, valid);
 printCsvValue(gyro_x, IMU_ATTIFloatSig, "gyro_x",lineT, valid);
 printCsvValue(gyro_y, IMU_ATTIFloatSig, "gyro_y",lineT, valid);
 printCsvValue(gyro_z, IMU_ATTIFloatSig, "gyro_z",lineT, valid);
 printCsvValue(press, IMU_ATTIFloatSig, "press",lineT, valid);
 printCsvValue(q0, IMU_ATTIFloatSig, "q0",lineT, valid);
 printCsvValue(q1, IMU_ATTIFloatSig, "q1",lineT, valid);
 printCsvValue(q2, IMU_ATTIFloatSig, "q2",lineT, valid);
 printCsvValue(q3, IMU_ATTIFloatSig, "q3",lineT, valid);
 printCsvValue(ag_x, IMU_ATTIFloatSig, "ag_x",lineT, valid);
 printCsvValue(ag_y, IMU_ATTIFloatSig, "ag_y",lineT, valid);
 printCsvValue(ag_z, IMU_ATTIFloatSig, "ag_z",lineT, valid);
 printCsvValue(vg_x, IMU_ATTIFloatSig, "vg_x",lineT, valid);
 printCsvValue(vg_y, IMU_ATTIFloatSig, "vg_y",lineT, valid);
 printCsvValue(vg_z, IMU_ATTIFloatSig, "vg_z",lineT, valid);
 printCsvValue(gb_x, IMU_ATTIFloatSig, "gb_x",lineT, valid);
 printCsvValue(gb_y, IMU_ATTIFloatSig, "gb_y",lineT, valid);
 printCsvValue(gb_z, IMU_ATTIFloatSig, "gb_z",lineT, valid);
 printCsvValue(m_x, IMU_ATTIIntSig, "m_x",lineT, valid);
 printCsvValue(m_y, IMU_ATTIIntSig, "m_y",lineT, valid);
 printCsvValue(m_z, IMU_ATTIIntSig, "m_z",lineT, valid);
 printCsvValue(temp_x, IMU_ATTIIntSig, "temp_x",lineT, valid);
 printCsvValue(temp_y, IMU_ATTIIntSig, "temp_y",lineT, valid);
 printCsvValue(temp_z, IMU_ATTIIntSig, "temp_z",lineT, valid);
 printCsvValue(sensor_monitor, IMU_ATTIIntSig, "sensor_monitor",lineT, valid);
 printCsvValue(filter_status, IMU_ATTIIntSig, "filter_status",lineT, valid);
 printCsvValue(svn, IMU_ATTIIntSig, "svn",lineT, valid);
 printCsvValue(cnt_atti, IMU_ATTIIntSig, "cnt_atti",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
