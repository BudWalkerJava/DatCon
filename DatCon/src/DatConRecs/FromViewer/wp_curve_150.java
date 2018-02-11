package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class wp_curve_150 extends Record {
protected boolean valid = false;

protected float px = (float)0;
protected float py = (float)0;
protected float pz = (float)0;
protected float vx = (float)0;
protected float vy = (float)0;
protected float vz = (float)0;
protected float v_norm = (float)0;
protected float T = (float)0;
protected short wp_state = (short)0;

      public wp_curve_150(ConvertDat convertDat) {
           super(convertDat, 150, 33);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 px = _payload.getFloat(0);
 py = _payload.getFloat(4);
 pz = _payload.getFloat(8);
 vx = _payload.getFloat(12);
 vy = _payload.getFloat(16);
 vz = _payload.getFloat(20);
 v_norm = _payload.getFloat(24);
 T = _payload.getFloat(28);
wp_state = _payload.getUnsignedByte(32);
} catch (Exception e) {RecordException(e);}}


    protected static Signal wp_curveIntSig = Signal
.SeriesInt("wp_curve", "", null, Units.noUnits);
    protected static Signal wp_curveFloatSig = Signal
.SeriesFloat("wp_curve", "", null, Units.noUnits);
    protected static Signal wp_curveDoubleSig = Signal
.SeriesDouble("wp_curve", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(px, wp_curveFloatSig, "px",lineT, valid);
 printCsvValue(py, wp_curveFloatSig, "py",lineT, valid);
 printCsvValue(pz, wp_curveFloatSig, "pz",lineT, valid);
 printCsvValue(vx, wp_curveFloatSig, "vx",lineT, valid);
 printCsvValue(vy, wp_curveFloatSig, "vy",lineT, valid);
 printCsvValue(vz, wp_curveFloatSig, "vz",lineT, valid);
 printCsvValue(v_norm, wp_curveFloatSig, "v_norm",lineT, valid);
 printCsvValue(T, wp_curveFloatSig, "T",lineT, valid);
 printCsvValue(wp_state, wp_curveIntSig, "wp_state",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
