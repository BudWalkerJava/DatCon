package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class sensor_cfg_temp_9 extends Record {
protected boolean valid = false;

protected float bias_gyrox = (float)0;
protected float bias_gyroy = (float)0;
protected float bias_gyroz = (float)0;
protected float bias_accx = (float)0;
protected float bias_accy = (float)0;
protected float bias_accz = (float)0;
protected float tw = (float)0;
protected float ta = (float)0;
protected int fw = (int)0;
protected int fa = (int)0;

      public sensor_cfg_temp_9(ConvertDat convertDat) {
           super(convertDat, 9, 36);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 bias_gyrox = _payload.getFloat(0);
 bias_gyroy = _payload.getFloat(4);
 bias_gyroz = _payload.getFloat(8);
 bias_accx = _payload.getFloat(12);
 bias_accy = _payload.getFloat(16);
 bias_accz = _payload.getFloat(20);
 tw = _payload.getFloat(24);
 ta = _payload.getFloat(28);
 fw = _payload.getUnsignedShort(32);
 fa = _payload.getUnsignedShort(34);
} catch (Exception e) {RecordException(e);}}


    protected static Signal sensor_cfg_tempIntSig = Signal
.SeriesInt("sensor_cfg_temp", "", null, Units.noUnits);
    protected static Signal sensor_cfg_tempFloatSig = Signal
.SeriesFloat("sensor_cfg_temp", "", null, Units.noUnits);
    protected static Signal sensor_cfg_tempDoubleSig = Signal
.SeriesDouble("sensor_cfg_temp", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(bias_gyrox, sensor_cfg_tempFloatSig, "bias_gyrox",lineT, valid);
 printCsvValue(bias_gyroy, sensor_cfg_tempFloatSig, "bias_gyroy",lineT, valid);
 printCsvValue(bias_gyroz, sensor_cfg_tempFloatSig, "bias_gyroz",lineT, valid);
 printCsvValue(bias_accx, sensor_cfg_tempFloatSig, "bias_accx",lineT, valid);
 printCsvValue(bias_accy, sensor_cfg_tempFloatSig, "bias_accy",lineT, valid);
 printCsvValue(bias_accz, sensor_cfg_tempFloatSig, "bias_accz",lineT, valid);
 printCsvValue(tw, sensor_cfg_tempFloatSig, "tw",lineT, valid);
 printCsvValue(ta, sensor_cfg_tempFloatSig, "ta",lineT, valid);
 printCsvValue(fw, sensor_cfg_tempIntSig, "fw",lineT, valid);
 printCsvValue(fa, sensor_cfg_tempIntSig, "fa",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
