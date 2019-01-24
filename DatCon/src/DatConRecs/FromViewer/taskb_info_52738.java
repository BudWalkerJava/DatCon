package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class taskb_info_52738 extends Record {
protected boolean valid = false;

protected int period_jitter0 = (int)0;
protected int exec_time0 = (int)0;
protected int pending0 = (int)0;

      public taskb_info_52738(ConvertDat convertDat) {
           super(convertDat, 52738, 8);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 period_jitter0 = _payload.getInt(0);
 exec_time0 = _payload.getUnsignedShort(4);
 pending0 = _payload.getUnsignedShort(6);
} catch (Exception e) {RecordException(e);}}


    protected static Signal taskb_infoIntSig = Signal
.SeriesInt("taskb_info", "", null, Units.noUnits);
    protected static Signal taskb_infoFloatSig = Signal
.SeriesFloat("taskb_info", "", null, Units.noUnits);
    protected static Signal taskb_infoDoubleSig = Signal
.SeriesDouble("taskb_info", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(period_jitter0, taskb_infoIntSig, "period_jitter0",lineT, valid);
 printCsvValue(exec_time0, taskb_infoIntSig, "exec_time0",lineT, valid);
 printCsvValue(pending0, taskb_infoIntSig, "pending0",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
