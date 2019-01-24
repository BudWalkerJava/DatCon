package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class rtkData_53233 extends Record {
protected boolean valid = false;

protected int Lat = (int)0;
protected int Lon = (int)0;
protected int Height = (int)0;
protected int satNum = (int)0;
protected int posType = (int)0;
protected int reserve1 = (int)0;
protected int reserve2 = (int)0;
protected int reserve3 = (int)0;
protected int reserve4 = (int)0;
protected int reserve5 = (int)0;
protected int reserve6 = (int)0;
protected int reserve7 = (int)0;
protected int reserve8 = (int)0;
protected int reserve9 = (int)0;
protected int reserve10 = (int)0;
protected long cntRTK = (long)0;

      public rtkData_53233(ConvertDat convertDat) {
           super(convertDat, 53233, 60);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 Lat = _payload.getInt(0);
 Lon = _payload.getInt(4);
 Height = _payload.getInt(8);
 satNum = _payload.getUnsignedShort(12);
 posType = _payload.getUnsignedShort(14);
 reserve1 = _payload.getInt(16);
 reserve2 = _payload.getInt(20);
 reserve3 = _payload.getInt(24);
 reserve4 = _payload.getInt(28);
 reserve5 = _payload.getInt(32);
 reserve6 = _payload.getInt(36);
 reserve7 = _payload.getInt(40);
 reserve8 = _payload.getInt(44);
 reserve9 = _payload.getInt(48);
 reserve10 = _payload.getInt(52);
 cntRTK = _payload.getUnsignedInt(56);
} catch (Exception e) {RecordException(e);}}


    protected static Signal rtkDataIntSig = Signal
.SeriesInt("rtkData", "", null, Units.noUnits);
    protected static Signal rtkDataFloatSig = Signal
.SeriesFloat("rtkData", "", null, Units.noUnits);
    protected static Signal rtkDataDoubleSig = Signal
.SeriesDouble("rtkData", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(Lat, rtkDataIntSig, "Lat",lineT, valid);
 printCsvValue(Lon, rtkDataIntSig, "Lon",lineT, valid);
 printCsvValue(Height, rtkDataIntSig, "Height",lineT, valid);
 printCsvValue(satNum, rtkDataIntSig, "satNum",lineT, valid);
 printCsvValue(posType, rtkDataIntSig, "posType",lineT, valid);
 printCsvValue(reserve1, rtkDataIntSig, "reserve1",lineT, valid);
 printCsvValue(reserve2, rtkDataIntSig, "reserve2",lineT, valid);
 printCsvValue(reserve3, rtkDataIntSig, "reserve3",lineT, valid);
 printCsvValue(reserve4, rtkDataIntSig, "reserve4",lineT, valid);
 printCsvValue(reserve5, rtkDataIntSig, "reserve5",lineT, valid);
 printCsvValue(reserve6, rtkDataIntSig, "reserve6",lineT, valid);
 printCsvValue(reserve7, rtkDataIntSig, "reserve7",lineT, valid);
 printCsvValue(reserve8, rtkDataIntSig, "reserve8",lineT, valid);
 printCsvValue(reserve9, rtkDataIntSig, "reserve9",lineT, valid);
 printCsvValue(reserve10, rtkDataIntSig, "reserve10",lineT, valid);
 printCsvValue(cntRTK, rtkDataIntSig, "cntRTK",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
