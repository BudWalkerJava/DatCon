package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class asr_115 extends Record {
protected boolean valid = false;

protected long lead = (long)0;

      public asr_115(ConvertDat convertDat) {
           super(convertDat, 115, 4);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 lead = _payload.getUnsignedInt(0);
} catch (Exception e) {RecordException(e);}}


    protected static Signal asrIntSig = Signal
.SeriesInt("asr", "", null, Units.noUnits);
    protected static Signal asrFloatSig = Signal
.SeriesFloat("asr", "", null, Units.noUnits);
    protected static Signal asrDoubleSig = Signal
.SeriesDouble("asr", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(lead, asrIntSig, "lead",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
