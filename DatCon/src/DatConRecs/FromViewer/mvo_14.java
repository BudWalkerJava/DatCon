package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class mvo_14 extends Record {
protected boolean valid = false;

protected float mvo_px = (float)0;
protected float mvo_py = (float)0;
protected float mvo_pz = (float)0;
protected short mvo_vx = (short)0;
protected short mvo_vy = (short)0;
protected short mvo_vz = (short)0;
protected short mvo_cnt = (short)0;
protected short mvo_flag = (short)0;

      public mvo_14(ConvertDat convertDat) {
           super(convertDat, 14, 20);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 mvo_px = _payload.getFloat(0);
 mvo_py = _payload.getFloat(4);
 mvo_pz = _payload.getFloat(8);
 mvo_vx = _payload.getShort(12);
 mvo_vy = _payload.getShort(14);
 mvo_vz = _payload.getShort(16);
mvo_cnt = _payload.getUnsignedByte(18);
mvo_flag = _payload.getUnsignedByte(19);
} catch (Exception e) {RecordException(e);}}


    protected static Signal mvoIntSig = Signal
.SeriesInt("mvo", "", null, Units.noUnits);
    protected static Signal mvoFloatSig = Signal
.SeriesFloat("mvo", "", null, Units.noUnits);
    protected static Signal mvoDoubleSig = Signal
.SeriesDouble("mvo", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(mvo_px, mvoFloatSig, "mvo_px",lineT, valid);
 printCsvValue(mvo_py, mvoFloatSig, "mvo_py",lineT, valid);
 printCsvValue(mvo_pz, mvoFloatSig, "mvo_pz",lineT, valid);
 printCsvValue(mvo_vx, mvoIntSig, "mvo_vx",lineT, valid);
 printCsvValue(mvo_vy, mvoIntSig, "mvo_vy",lineT, valid);
 printCsvValue(mvo_vz, mvoIntSig, "mvo_vz",lineT, valid);
 printCsvValue(mvo_cnt, mvoIntSig, "mvo_cnt",lineT, valid);
 printCsvValue(mvo_flag, mvoIntSig, "mvo_flag",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
