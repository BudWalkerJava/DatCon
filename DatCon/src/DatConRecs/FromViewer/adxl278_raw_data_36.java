package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class adxl278_raw_data_36 extends Record {
protected boolean valid = false;

protected float adxl278_ax = (float)0;
protected float adxl278_ay = (float)0;
protected float adxl278_az = (float)0;

      public adxl278_raw_data_36(ConvertDat convertDat) {
           super(convertDat, 36, 12);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 adxl278_ax = _payload.getFloat(0);
 adxl278_ay = _payload.getFloat(4);
 adxl278_az = _payload.getFloat(8);
} catch (Exception e) {RecordException(e);}}


    protected static Signal adxl278_raw_dataIntSig = Signal
.SeriesInt("adxl278_raw_data", "", null, Units.noUnits);
    protected static Signal adxl278_raw_dataFloatSig = Signal
.SeriesFloat("adxl278_raw_data", "", null, Units.noUnits);
    protected static Signal adxl278_raw_dataDoubleSig = Signal
.SeriesDouble("adxl278_raw_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(adxl278_ax, adxl278_raw_dataFloatSig, "adxl278_ax",lineT, valid);
 printCsvValue(adxl278_ay, adxl278_raw_dataFloatSig, "adxl278_ay",lineT, valid);
 printCsvValue(adxl278_az, adxl278_raw_dataFloatSig, "adxl278_az",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
