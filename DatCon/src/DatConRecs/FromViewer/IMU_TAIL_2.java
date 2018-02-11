package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_TAIL_2 extends Record {
protected boolean valid = false;

protected short raw_w_x = (short)0;
protected short raw_w_y = (short)0;
protected short raw_w_z = (short)0;
protected short raw_a_x = (short)0;
protected short raw_a_y = (short)0;
protected short raw_a_z = (short)0;
protected short raw_wa_x = (short)0;
protected short raw_wa_y = (short)0;
protected short raw_wa_z = (short)0;
protected short iir_w_x = (short)0;
protected short iir_w_y = (short)0;
protected short iir_w_z = (short)0;
protected short iir_a_x = (short)0;
protected short iir_a_y = (short)0;
protected short iir_a_z = (short)0;
protected short iir_wa_x = (short)0;
protected short iir_wa_y = (short)0;
protected short iir_wa_z = (short)0;
protected long gyro_hf_cnt = (long)0;

      public IMU_TAIL_2(ConvertDat convertDat) {
           super(convertDat, 2, 40);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 raw_w_x = _payload.getShort(0);
 raw_w_y = _payload.getShort(2);
 raw_w_z = _payload.getShort(4);
 raw_a_x = _payload.getShort(6);
 raw_a_y = _payload.getShort(8);
 raw_a_z = _payload.getShort(10);
 raw_wa_x = _payload.getShort(12);
 raw_wa_y = _payload.getShort(14);
 raw_wa_z = _payload.getShort(16);
 iir_w_x = _payload.getShort(18);
 iir_w_y = _payload.getShort(20);
 iir_w_z = _payload.getShort(22);
 iir_a_x = _payload.getShort(24);
 iir_a_y = _payload.getShort(26);
 iir_a_z = _payload.getShort(28);
 iir_wa_x = _payload.getShort(30);
 iir_wa_y = _payload.getShort(32);
 iir_wa_z = _payload.getShort(34);
 gyro_hf_cnt = _payload.getUnsignedInt(36);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_TAILIntSig = Signal
.SeriesInt("IMU_TAIL", "", null, Units.noUnits);
    protected static Signal IMU_TAILFloatSig = Signal
.SeriesFloat("IMU_TAIL", "", null, Units.noUnits);
    protected static Signal IMU_TAILDoubleSig = Signal
.SeriesDouble("IMU_TAIL", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(raw_w_x, IMU_TAILIntSig, "raw_w_x",lineT, valid);
 printCsvValue(raw_w_y, IMU_TAILIntSig, "raw_w_y",lineT, valid);
 printCsvValue(raw_w_z, IMU_TAILIntSig, "raw_w_z",lineT, valid);
 printCsvValue(raw_a_x, IMU_TAILIntSig, "raw_a_x",lineT, valid);
 printCsvValue(raw_a_y, IMU_TAILIntSig, "raw_a_y",lineT, valid);
 printCsvValue(raw_a_z, IMU_TAILIntSig, "raw_a_z",lineT, valid);
 printCsvValue(raw_wa_x, IMU_TAILIntSig, "raw_wa_x",lineT, valid);
 printCsvValue(raw_wa_y, IMU_TAILIntSig, "raw_wa_y",lineT, valid);
 printCsvValue(raw_wa_z, IMU_TAILIntSig, "raw_wa_z",lineT, valid);
 printCsvValue(iir_w_x, IMU_TAILIntSig, "iir_w_x",lineT, valid);
 printCsvValue(iir_w_y, IMU_TAILIntSig, "iir_w_y",lineT, valid);
 printCsvValue(iir_w_z, IMU_TAILIntSig, "iir_w_z",lineT, valid);
 printCsvValue(iir_a_x, IMU_TAILIntSig, "iir_a_x",lineT, valid);
 printCsvValue(iir_a_y, IMU_TAILIntSig, "iir_a_y",lineT, valid);
 printCsvValue(iir_a_z, IMU_TAILIntSig, "iir_a_z",lineT, valid);
 printCsvValue(iir_wa_x, IMU_TAILIntSig, "iir_wa_x",lineT, valid);
 printCsvValue(iir_wa_y, IMU_TAILIntSig, "iir_wa_y",lineT, valid);
 printCsvValue(iir_wa_z, IMU_TAILIntSig, "iir_wa_z",lineT, valid);
 printCsvValue(gyro_hf_cnt, IMU_TAILIntSig, "gyro_hf_cnt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
