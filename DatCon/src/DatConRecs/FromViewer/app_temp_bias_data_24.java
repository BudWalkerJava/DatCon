package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class app_temp_bias_data_24 extends Record {
protected boolean valid = false;

protected float bw_x = (float)0;
protected float bw_y = (float)0;
protected float bw_z = (float)0;
protected float ba_x = (float)0;
protected float ba_y = (float)0;
protected float ba_z = (float)0;
protected float temp = (float)0;
protected short flag = (short)0;

      public app_temp_bias_data_24(ConvertDat convertDat) {
           super(convertDat, 24, 29);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 bw_x = _payload.getFloat(0);
 bw_y = _payload.getFloat(4);
 bw_z = _payload.getFloat(8);
 ba_x = _payload.getFloat(12);
 ba_y = _payload.getFloat(16);
 ba_z = _payload.getFloat(20);
 temp = _payload.getFloat(24);
flag = _payload.getUnsignedByte(28);
} catch (Exception e) {RecordException(e);}}


    protected static Signal app_temp_bias_dataIntSig = Signal
.SeriesInt("app_temp_bias_data", "", null, Units.noUnits);
    protected static Signal app_temp_bias_dataFloatSig = Signal
.SeriesFloat("app_temp_bias_data", "", null, Units.noUnits);
    protected static Signal app_temp_bias_dataDoubleSig = Signal
.SeriesDouble("app_temp_bias_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(bw_x, app_temp_bias_dataFloatSig, "bw_x",lineT, valid);
 printCsvValue(bw_y, app_temp_bias_dataFloatSig, "bw_y",lineT, valid);
 printCsvValue(bw_z, app_temp_bias_dataFloatSig, "bw_z",lineT, valid);
 printCsvValue(ba_x, app_temp_bias_dataFloatSig, "ba_x",lineT, valid);
 printCsvValue(ba_y, app_temp_bias_dataFloatSig, "ba_y",lineT, valid);
 printCsvValue(ba_z, app_temp_bias_dataFloatSig, "ba_z",lineT, valid);
 printCsvValue(temp, app_temp_bias_dataFloatSig, "temp",lineT, valid);
 printCsvValue(flag, app_temp_bias_dataIntSig, "flag",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
