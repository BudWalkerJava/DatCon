package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class hp_data_42 extends Record {
protected boolean valid = false;

protected float tgt_hp_alti = (float)0;
protected float tgt_ang_rate = (float)0;
protected float tgt_radius = (float)0;
protected float distance_to_hp = (float)0;
protected float cosine_angle = (float)0;
protected float angle_rate = (float)0;
protected float radius = (float)0;
protected float pos_error_x = (float)0;
protected float pos_error_y = (float)0;
protected float pos_error_z = (float)0;
protected float vel_error_x = (float)0;
protected float vel_error_y = (float)0;
protected float vel_error_z = (float)0;
protected float head_error = (float)0;

      public hp_data_42(ConvertDat convertDat) {
           super(convertDat, 42, 56);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 tgt_hp_alti = _payload.getFloat(0);
 tgt_ang_rate = _payload.getFloat(4);
 tgt_radius = _payload.getFloat(8);
 distance_to_hp = _payload.getFloat(12);
 cosine_angle = _payload.getFloat(16);
 angle_rate = _payload.getFloat(20);
 radius = _payload.getFloat(24);
 pos_error_x = _payload.getFloat(28);
 pos_error_y = _payload.getFloat(32);
 pos_error_z = _payload.getFloat(36);
 vel_error_x = _payload.getFloat(40);
 vel_error_y = _payload.getFloat(44);
 vel_error_z = _payload.getFloat(48);
 head_error = _payload.getFloat(52);
} catch (Exception e) {RecordException(e);}}


    protected static Signal hp_dataIntSig = Signal
.SeriesInt("hp_data", "", null, Units.noUnits);
    protected static Signal hp_dataFloatSig = Signal
.SeriesFloat("hp_data", "", null, Units.noUnits);
    protected static Signal hp_dataDoubleSig = Signal
.SeriesDouble("hp_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(tgt_hp_alti, hp_dataFloatSig, "tgt_hp_alti",lineT, valid);
 printCsvValue(tgt_ang_rate, hp_dataFloatSig, "tgt_ang_rate",lineT, valid);
 printCsvValue(tgt_radius, hp_dataFloatSig, "tgt_radius",lineT, valid);
 printCsvValue(distance_to_hp, hp_dataFloatSig, "distance_to_hp",lineT, valid);
 printCsvValue(cosine_angle, hp_dataFloatSig, "cosine_angle",lineT, valid);
 printCsvValue(angle_rate, hp_dataFloatSig, "angle_rate",lineT, valid);
 printCsvValue(radius, hp_dataFloatSig, "radius",lineT, valid);
 printCsvValue(pos_error_x, hp_dataFloatSig, "pos_error_x",lineT, valid);
 printCsvValue(pos_error_y, hp_dataFloatSig, "pos_error_y",lineT, valid);
 printCsvValue(pos_error_z, hp_dataFloatSig, "pos_error_z",lineT, valid);
 printCsvValue(vel_error_x, hp_dataFloatSig, "vel_error_x",lineT, valid);
 printCsvValue(vel_error_y, hp_dataFloatSig, "vel_error_y",lineT, valid);
 printCsvValue(vel_error_z, hp_dataFloatSig, "vel_error_z",lineT, valid);
 printCsvValue(head_error, hp_dataFloatSig, "head_error",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
