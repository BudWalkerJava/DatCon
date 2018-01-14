package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_bias_data_21 extends Record {
protected boolean valid = false;

protected float _bw_x = (float)0;
protected float _bw_y = (float)0;
protected float _bw_z = (float)0;
protected float _ba_x = (float)0;
protected float _ba_y = (float)0;
protected float _ba_z = (float)0;
protected float _temp = (float)0;
protected short _flag = (short)0;

      public temp_bias_data_21(ConvertDat convertDat) {
           super(convertDat, 21, 29);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 _bw_x = _payload.getFloat(0);
 _bw_y = _payload.getFloat(4);
 _bw_z = _payload.getFloat(8);
 _ba_x = _payload.getFloat(12);
 _ba_y = _payload.getFloat(16);
 _ba_z = _payload.getFloat(20);
 _temp = _payload.getFloat(24);
_flag = _payload.getUnsignedByte(28);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_bias_dataIntSig = Signal
.SeriesInt("temp_bias_data", "", null, Units.noUnits);
    protected static Signal temp_bias_dataFloatSig = Signal
.SeriesFloat("temp_bias_data", "", null, Units.noUnits);
    protected static Signal temp_bias_dataDoubleSig = Signal
.SeriesDouble("temp_bias_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(_bw_x, temp_bias_dataFloatSig, "_bw_x",lineT, valid);
 printCsvValue(_bw_y, temp_bias_dataFloatSig, "_bw_y",lineT, valid);
 printCsvValue(_bw_z, temp_bias_dataFloatSig, "_bw_z",lineT, valid);
 printCsvValue(_ba_x, temp_bias_dataFloatSig, "_ba_x",lineT, valid);
 printCsvValue(_ba_y, temp_bias_dataFloatSig, "_ba_y",lineT, valid);
 printCsvValue(_ba_z, temp_bias_dataFloatSig, "_ba_z",lineT, valid);
 printCsvValue(_temp, temp_bias_dataFloatSig, "_temp",lineT, valid);
 printCsvValue(_flag, temp_bias_dataIntSig, "_flag",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
