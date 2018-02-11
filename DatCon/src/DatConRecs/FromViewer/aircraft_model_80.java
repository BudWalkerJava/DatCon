package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class aircraft_model_80 extends Record {
protected boolean valid = false;

protected float m1_current = (float)0;
protected float m1_w = (float)0;
protected float m1_lift = (float)0;
protected float m1_torque = (float)0;
protected float m2_current = (float)0;
protected float m2_w = (float)0;
protected float m2_lift = (float)0;
protected float m2_torque = (float)0;
protected float m3_current = (float)0;
protected float m3_w = (float)0;
protected float m3_lift = (float)0;
protected float m3_torque = (float)0;
protected float m4_current = (float)0;
protected float m4_w = (float)0;
protected float m4_lift = (float)0;
protected float m4_torque = (float)0;
protected float m5_current = (float)0;
protected float m5_w = (float)0;
protected float m5_lift = (float)0;
protected float m5_torque = (float)0;
protected float m6_current = (float)0;
protected float m6_w = (float)0;
protected float m6_lift = (float)0;
protected float m6_torque = (float)0;
protected float m7_current = (float)0;
protected float m7_w = (float)0;
protected float m7_lift = (float)0;
protected float m7_torque = (float)0;
protected float m8_current = (float)0;
protected float m8_w = (float)0;
protected float m8_lift = (float)0;
protected float m8_torque = (float)0;

      public aircraft_model_80(ConvertDat convertDat) {
           super(convertDat, 80, 128);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 m1_current = _payload.getFloat(0);
 m1_w = _payload.getFloat(4);
 m1_lift = _payload.getFloat(8);
 m1_torque = _payload.getFloat(12);
 m2_current = _payload.getFloat(16);
 m2_w = _payload.getFloat(20);
 m2_lift = _payload.getFloat(24);
 m2_torque = _payload.getFloat(28);
 m3_current = _payload.getFloat(32);
 m3_w = _payload.getFloat(36);
 m3_lift = _payload.getFloat(40);
 m3_torque = _payload.getFloat(44);
 m4_current = _payload.getFloat(48);
 m4_w = _payload.getFloat(52);
 m4_lift = _payload.getFloat(56);
 m4_torque = _payload.getFloat(60);
 m5_current = _payload.getFloat(64);
 m5_w = _payload.getFloat(68);
 m5_lift = _payload.getFloat(72);
 m5_torque = _payload.getFloat(76);
 m6_current = _payload.getFloat(80);
 m6_w = _payload.getFloat(84);
 m6_lift = _payload.getFloat(88);
 m6_torque = _payload.getFloat(92);
 m7_current = _payload.getFloat(96);
 m7_w = _payload.getFloat(100);
 m7_lift = _payload.getFloat(104);
 m7_torque = _payload.getFloat(108);
 m8_current = _payload.getFloat(112);
 m8_w = _payload.getFloat(116);
 m8_lift = _payload.getFloat(120);
 m8_torque = _payload.getFloat(124);
} catch (Exception e) {RecordException(e);}}


    protected static Signal aircraft_modelIntSig = Signal
.SeriesInt("aircraft_model", "", null, Units.noUnits);
    protected static Signal aircraft_modelFloatSig = Signal
.SeriesFloat("aircraft_model", "", null, Units.noUnits);
    protected static Signal aircraft_modelDoubleSig = Signal
.SeriesDouble("aircraft_model", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(m1_current, aircraft_modelFloatSig, "m1_current",lineT, valid);
 printCsvValue(m1_w, aircraft_modelFloatSig, "m1_w",lineT, valid);
 printCsvValue(m1_lift, aircraft_modelFloatSig, "m1_lift",lineT, valid);
 printCsvValue(m1_torque, aircraft_modelFloatSig, "m1_torque",lineT, valid);
 printCsvValue(m2_current, aircraft_modelFloatSig, "m2_current",lineT, valid);
 printCsvValue(m2_w, aircraft_modelFloatSig, "m2_w",lineT, valid);
 printCsvValue(m2_lift, aircraft_modelFloatSig, "m2_lift",lineT, valid);
 printCsvValue(m2_torque, aircraft_modelFloatSig, "m2_torque",lineT, valid);
 printCsvValue(m3_current, aircraft_modelFloatSig, "m3_current",lineT, valid);
 printCsvValue(m3_w, aircraft_modelFloatSig, "m3_w",lineT, valid);
 printCsvValue(m3_lift, aircraft_modelFloatSig, "m3_lift",lineT, valid);
 printCsvValue(m3_torque, aircraft_modelFloatSig, "m3_torque",lineT, valid);
 printCsvValue(m4_current, aircraft_modelFloatSig, "m4_current",lineT, valid);
 printCsvValue(m4_w, aircraft_modelFloatSig, "m4_w",lineT, valid);
 printCsvValue(m4_lift, aircraft_modelFloatSig, "m4_lift",lineT, valid);
 printCsvValue(m4_torque, aircraft_modelFloatSig, "m4_torque",lineT, valid);
 printCsvValue(m5_current, aircraft_modelFloatSig, "m5_current",lineT, valid);
 printCsvValue(m5_w, aircraft_modelFloatSig, "m5_w",lineT, valid);
 printCsvValue(m5_lift, aircraft_modelFloatSig, "m5_lift",lineT, valid);
 printCsvValue(m5_torque, aircraft_modelFloatSig, "m5_torque",lineT, valid);
 printCsvValue(m6_current, aircraft_modelFloatSig, "m6_current",lineT, valid);
 printCsvValue(m6_w, aircraft_modelFloatSig, "m6_w",lineT, valid);
 printCsvValue(m6_lift, aircraft_modelFloatSig, "m6_lift",lineT, valid);
 printCsvValue(m6_torque, aircraft_modelFloatSig, "m6_torque",lineT, valid);
 printCsvValue(m7_current, aircraft_modelFloatSig, "m7_current",lineT, valid);
 printCsvValue(m7_w, aircraft_modelFloatSig, "m7_w",lineT, valid);
 printCsvValue(m7_lift, aircraft_modelFloatSig, "m7_lift",lineT, valid);
 printCsvValue(m7_torque, aircraft_modelFloatSig, "m7_torque",lineT, valid);
 printCsvValue(m8_current, aircraft_modelFloatSig, "m8_current",lineT, valid);
 printCsvValue(m8_w, aircraft_modelFloatSig, "m8_w",lineT, valid);
 printCsvValue(m8_lift, aircraft_modelFloatSig, "m8_lift",lineT, valid);
 printCsvValue(m8_torque, aircraft_modelFloatSig, "m8_torque",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
