package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class special_cmd_23 extends Record {
protected boolean valid = false;

protected short byte1 = (short)0;
protected short byte2 = (short)0;
protected int word1 = (int)0;
protected short byte3 = (short)0;
protected short ctrl_action = (short)0;
protected short byte4 = (short)0;
protected short byte5 = (short)0;
protected short byte6 = (short)0;
protected short byte7 = (short)0;

      public special_cmd_23(ConvertDat convertDat) {
           super(convertDat, 23, 10);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

byte1 = _payload.getUnsignedByte(0);
byte2 = _payload.getUnsignedByte(1);
 word1 = _payload.getUnsignedShort(2);
byte3 = _payload.getUnsignedByte(4);
ctrl_action = _payload.getUnsignedByte(5);
byte4 = _payload.getUnsignedByte(6);
byte5 = _payload.getUnsignedByte(7);
byte6 = _payload.getUnsignedByte(8);
byte7 = _payload.getUnsignedByte(9);
} catch (Exception e) {RecordException(e);}}


    protected static Signal special_cmdIntSig = Signal
.SeriesInt("special_cmd", "", null, Units.noUnits);
    protected static Signal special_cmdFloatSig = Signal
.SeriesFloat("special_cmd", "", null, Units.noUnits);
    protected static Signal special_cmdDoubleSig = Signal
.SeriesDouble("special_cmd", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(byte1, special_cmdIntSig, "byte1",lineT, valid);
 printCsvValue(byte2, special_cmdIntSig, "byte2",lineT, valid);
 printCsvValue(word1, special_cmdIntSig, "word1",lineT, valid);
 printCsvValue(byte3, special_cmdIntSig, "byte3",lineT, valid);
 printCsvValue(ctrl_action, special_cmdIntSig, "ctrl_action",lineT, valid);
 printCsvValue(byte4, special_cmdIntSig, "byte4",lineT, valid);
 printCsvValue(byte5, special_cmdIntSig, "byte5",lineT, valid);
 printCsvValue(byte6, special_cmdIntSig, "byte6",lineT, valid);
 printCsvValue(byte7, special_cmdIntSig, "byte7",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
