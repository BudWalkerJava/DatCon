package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class fdi_26 extends Record {
protected boolean valid = false;

protected long ns_abnormal_all = (long)0;
protected long history_ns_abnormal_all = (long)0;
protected short gyro_bias_raw_flag = (short)0;
protected float gyrox_bias_raw = (float)0;
protected float gyroy_bias_raw = (float)0;
protected float gyroz_bias_raw = (float)0;

      public fdi_26(ConvertDat convertDat) {
           super(convertDat, 26, 21);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 ns_abnormal_all = _payload.getUnsignedInt(0);
 history_ns_abnormal_all = _payload.getUnsignedInt(4);
gyro_bias_raw_flag = _payload.getUnsignedByte(8);
 gyrox_bias_raw = _payload.getFloat(9);
 gyroy_bias_raw = _payload.getFloat(13);
 gyroz_bias_raw = _payload.getFloat(17);
} catch (Exception e) {RecordException(e);}}


    protected static Signal fdiIntSig = Signal
.SeriesInt("fdi", "", null, Units.noUnits);
    protected static Signal fdiFloatSig = Signal
.SeriesFloat("fdi", "", null, Units.noUnits);
    protected static Signal fdiDoubleSig = Signal
.SeriesDouble("fdi", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(ns_abnormal_all, fdiIntSig, "ns_abnormal_all",lineT, valid);
 printCsvValue(history_ns_abnormal_all, fdiIntSig, "history_ns_abnormal_all",lineT, valid);
 printCsvValue(gyro_bias_raw_flag, fdiIntSig, "gyro_bias_raw_flag",lineT, valid);
 printCsvValue(gyrox_bias_raw, fdiFloatSig, "gyrox_bias_raw",lineT, valid);
 printCsvValue(gyroy_bias_raw, fdiFloatSig, "gyroy_bias_raw",lineT, valid);
 printCsvValue(gyroz_bias_raw, fdiFloatSig, "gyroz_bias_raw",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
