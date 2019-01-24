package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class ctrl_ccpm_53 extends Record {
protected boolean valid = false;

protected float dyn_ccpm_raw_lift = (float)0;
protected float dyn_ccpm_raw_tilt_x = (float)0;
protected float dyn_ccpm_raw_tilt_y = (float)0;
protected float dyn_ccpm_raw_torsion = (float)0;
protected float dyn_ccpm_fix_lift = (float)0;
protected float dyn_ccpm_fix_tilt_x = (float)0;
protected float dyn_ccpm_fix_tilt_y = (float)0;
protected float dyn_ccpm_fix_torsion = (float)0;
protected float dyn_ccpm_fix_lift_scale = (float)0;
protected float dyn_ccpm_fix_tilt_scale = (float)0;
protected float dyn_ccpm_fix_torsion_scale = (float)0;
protected float dyn_ccpm_saturation_value = (float)0;
protected short dyn_ccpm_saturation_flag = (short)0;

      public ctrl_ccpm_53(ConvertDat convertDat) {
           super(convertDat, 53, 49);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 dyn_ccpm_raw_lift = _payload.getFloat(0);
 dyn_ccpm_raw_tilt_x = _payload.getFloat(4);
 dyn_ccpm_raw_tilt_y = _payload.getFloat(8);
 dyn_ccpm_raw_torsion = _payload.getFloat(12);
 dyn_ccpm_fix_lift = _payload.getFloat(16);
 dyn_ccpm_fix_tilt_x = _payload.getFloat(20);
 dyn_ccpm_fix_tilt_y = _payload.getFloat(24);
 dyn_ccpm_fix_torsion = _payload.getFloat(28);
 dyn_ccpm_fix_lift_scale = _payload.getFloat(32);
 dyn_ccpm_fix_tilt_scale = _payload.getFloat(36);
 dyn_ccpm_fix_torsion_scale = _payload.getFloat(40);
 dyn_ccpm_saturation_value = _payload.getFloat(44);
dyn_ccpm_saturation_flag = _payload.getUnsignedByte(48);
} catch (Exception e) {RecordException(e);}}


    protected static Signal ctrl_ccpmIntSig = Signal
.SeriesInt("ctrl_ccpm", "", null, Units.noUnits);
    protected static Signal ctrl_ccpmFloatSig = Signal
.SeriesFloat("ctrl_ccpm", "", null, Units.noUnits);
    protected static Signal ctrl_ccpmDoubleSig = Signal
.SeriesDouble("ctrl_ccpm", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(dyn_ccpm_raw_lift, ctrl_ccpmFloatSig, "dyn_ccpm_raw_lift",lineT, valid);
 printCsvValue(dyn_ccpm_raw_tilt_x, ctrl_ccpmFloatSig, "dyn_ccpm_raw_tilt_x",lineT, valid);
 printCsvValue(dyn_ccpm_raw_tilt_y, ctrl_ccpmFloatSig, "dyn_ccpm_raw_tilt_y",lineT, valid);
 printCsvValue(dyn_ccpm_raw_torsion, ctrl_ccpmFloatSig, "dyn_ccpm_raw_torsion",lineT, valid);
 printCsvValue(dyn_ccpm_fix_lift, ctrl_ccpmFloatSig, "dyn_ccpm_fix_lift",lineT, valid);
 printCsvValue(dyn_ccpm_fix_tilt_x, ctrl_ccpmFloatSig, "dyn_ccpm_fix_tilt_x",lineT, valid);
 printCsvValue(dyn_ccpm_fix_tilt_y, ctrl_ccpmFloatSig, "dyn_ccpm_fix_tilt_y",lineT, valid);
 printCsvValue(dyn_ccpm_fix_torsion, ctrl_ccpmFloatSig, "dyn_ccpm_fix_torsion",lineT, valid);
 printCsvValue(dyn_ccpm_fix_lift_scale, ctrl_ccpmFloatSig, "dyn_ccpm_fix_lift_scale",lineT, valid);
 printCsvValue(dyn_ccpm_fix_tilt_scale, ctrl_ccpmFloatSig, "dyn_ccpm_fix_tilt_scale",lineT, valid);
 printCsvValue(dyn_ccpm_fix_torsion_scale, ctrl_ccpmFloatSig, "dyn_ccpm_fix_torsion_scale",lineT, valid);
 printCsvValue(dyn_ccpm_saturation_value, ctrl_ccpmFloatSig, "dyn_ccpm_saturation_value",lineT, valid);
 printCsvValue(dyn_ccpm_saturation_flag, ctrl_ccpmIntSig, "dyn_ccpm_saturation_flag",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
