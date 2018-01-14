package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class IMU_TAIL_00_2080 extends Record {
protected boolean valid = false;

protected float wa_x_00 = (float)0;
protected float wa_y_00 = (float)0;
protected float wa_z_00 = (float)0;
protected float w_x_00 = (float)0;
protected float w_y_00 = (float)0;
protected float w_z_00 = (float)0;

      public IMU_TAIL_00_2080(ConvertDat convertDat) {
           super(convertDat, 2080, 24);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 wa_x_00 = _payload.getFloat(0);
 wa_y_00 = _payload.getFloat(4);
 wa_z_00 = _payload.getFloat(8);
 w_x_00 = _payload.getFloat(12);
 w_y_00 = _payload.getFloat(16);
 w_z_00 = _payload.getFloat(20);
} catch (Exception e) {RecordException(e);}}


    protected static Signal IMU_TAIL_00IntSig = Signal
.SeriesInt("IMU_TAIL_00", "", null, Units.noUnits);
    protected static Signal IMU_TAIL_00FloatSig = Signal
.SeriesFloat("IMU_TAIL_00", "", null, Units.noUnits);
    protected static Signal IMU_TAIL_00DoubleSig = Signal
.SeriesDouble("IMU_TAIL_00", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(wa_x_00, IMU_TAIL_00FloatSig, "wa_x_00",lineT, valid);
 printCsvValue(wa_y_00, IMU_TAIL_00FloatSig, "wa_y_00",lineT, valid);
 printCsvValue(wa_z_00, IMU_TAIL_00FloatSig, "wa_z_00",lineT, valid);
 printCsvValue(w_x_00, IMU_TAIL_00FloatSig, "w_x_00",lineT, valid);
 printCsvValue(w_y_00, IMU_TAIL_00FloatSig, "w_y_00",lineT, valid);
 printCsvValue(w_z_00, IMU_TAIL_00FloatSig, "w_z_00",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
