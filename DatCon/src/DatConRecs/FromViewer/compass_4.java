package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class compass_4 extends Record {
protected boolean valid = false;

protected short magx = (short)0;
protected short magy = (short)0;
protected short magz = (short)0;
protected int mag_cnt = (int)0;

      public compass_4(ConvertDat convertDat) {
           super(convertDat, 4, 8);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 magx = _payload.getShort(0);
 magy = _payload.getShort(2);
 magz = _payload.getShort(4);
 mag_cnt = _payload.getUnsignedShort(6);
} catch (Exception e) {RecordException(e);}}


    protected static Signal compassIntSig = Signal
.SeriesInt("compass", "", null, Units.noUnits);
    protected static Signal compassFloatSig = Signal
.SeriesFloat("compass", "", null, Units.noUnits);
    protected static Signal compassDoubleSig = Signal
.SeriesDouble("compass", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(magx, compassIntSig, "magx",lineT, valid);
 printCsvValue(magy, compassIntSig, "magy",lineT, valid);
 printCsvValue(magz, compassIntSig, "magz",lineT, valid);
 printCsvValue(mag_cnt, compassIntSig, "mag_cnt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
