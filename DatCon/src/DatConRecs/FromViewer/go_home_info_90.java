package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class go_home_info_90 extends Record {
protected boolean valid = false;

protected short go_home_stage = (short)0;
protected float go_home_timer = (float)0;
protected float dis_to_home_x = (float)0;
protected float dis_to_home_y = (float)0;

      public go_home_info_90(ConvertDat convertDat) {
           super(convertDat, 90, 13);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

go_home_stage = _payload.getUnsignedByte(0);
 go_home_timer = _payload.getFloat(1);
 dis_to_home_x = _payload.getFloat(5);
 dis_to_home_y = _payload.getFloat(9);
} catch (Exception e) {RecordException(e);}}


    protected static Signal go_home_infoIntSig = Signal
.SeriesInt("go_home_info", "", null, Units.noUnits);
    protected static Signal go_home_infoFloatSig = Signal
.SeriesFloat("go_home_info", "", null, Units.noUnits);
    protected static Signal go_home_infoDoubleSig = Signal
.SeriesDouble("go_home_info", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(go_home_stage, go_home_infoIntSig, "go_home_stage",lineT, valid);
 printCsvValue(go_home_timer, go_home_infoFloatSig, "go_home_timer",lineT, valid);
 printCsvValue(dis_to_home_x, go_home_infoFloatSig, "dis_to_home_x",lineT, valid);
 printCsvValue(dis_to_home_y, go_home_infoFloatSig, "dis_to_home_y",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
