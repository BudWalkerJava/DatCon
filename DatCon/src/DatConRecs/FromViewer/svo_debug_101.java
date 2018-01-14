package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class svo_debug_101 extends Record {
protected boolean valid = false;

protected float VisionDebug1 = (float)0;
protected float VisionDebug2 = (float)0;
protected float VisionDebug3 = (float)0;
protected float VisionDebug4 = (float)0;
protected float VisionDebug5 = (float)0;
protected float VisionDebug6 = (float)0;
protected float VisionDebug7 = (float)0;
protected float VisionDebug8 = (float)0;

      public svo_debug_101(ConvertDat convertDat) {
           super(convertDat, 101, 32);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 VisionDebug1 = _payload.getFloat(0);
 VisionDebug2 = _payload.getFloat(4);
 VisionDebug3 = _payload.getFloat(8);
 VisionDebug4 = _payload.getFloat(12);
 VisionDebug5 = _payload.getFloat(16);
 VisionDebug6 = _payload.getFloat(20);
 VisionDebug7 = _payload.getFloat(24);
 VisionDebug8 = _payload.getFloat(28);
} catch (Exception e) {RecordException(e);}}


    protected static Signal svo_debugIntSig = Signal
.SeriesInt("svo_debug", "", null, Units.noUnits);
    protected static Signal svo_debugFloatSig = Signal
.SeriesFloat("svo_debug", "", null, Units.noUnits);
    protected static Signal svo_debugDoubleSig = Signal
.SeriesDouble("svo_debug", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(VisionDebug1, svo_debugFloatSig, "VisionDebug1",lineT, valid);
 printCsvValue(VisionDebug2, svo_debugFloatSig, "VisionDebug2",lineT, valid);
 printCsvValue(VisionDebug3, svo_debugFloatSig, "VisionDebug3",lineT, valid);
 printCsvValue(VisionDebug4, svo_debugFloatSig, "VisionDebug4",lineT, valid);
 printCsvValue(VisionDebug5, svo_debugFloatSig, "VisionDebug5",lineT, valid);
 printCsvValue(VisionDebug6, svo_debugFloatSig, "VisionDebug6",lineT, valid);
 printCsvValue(VisionDebug7, svo_debugFloatSig, "VisionDebug7",lineT, valid);
 printCsvValue(VisionDebug8, svo_debugFloatSig, "VisionDebug8",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
