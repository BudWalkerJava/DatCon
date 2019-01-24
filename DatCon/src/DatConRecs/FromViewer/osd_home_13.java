package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class osd_home_13 extends Record {
protected boolean valid = false;

protected double osd_lon = (double)0;
protected double osd_lat = (double)0;
protected float osd_alt = (float)0;
protected int osd_home_state = (int)0;
protected int fixed_altitedue = (int)0;
protected short course_lock_torsion = (short)0;

      public osd_home_13(ConvertDat convertDat) {
           super(convertDat, 13, 26);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 osd_lon = _payload.getDouble(0);
 osd_lat = _payload.getDouble(8);
 osd_alt = _payload.getFloat(16);
 osd_home_state = _payload.getUnsignedShort(20);
 fixed_altitedue = _payload.getUnsignedShort(22);
 course_lock_torsion = _payload.getShort(24);
} catch (Exception e) {RecordException(e);}}


    protected static Signal osd_homeIntSig = Signal
.SeriesInt("osd_home", "", null, Units.noUnits);
    protected static Signal osd_homeFloatSig = Signal
.SeriesFloat("osd_home", "", null, Units.noUnits);
    protected static Signal osd_homeDoubleSig = Signal
.SeriesDouble("osd_home", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(osd_lon, osd_homeDoubleSig, "osd_lon",lineT, valid);
 printCsvValue(osd_lat, osd_homeDoubleSig, "osd_lat",lineT, valid);
 printCsvValue(osd_alt, osd_homeFloatSig, "osd_alt",lineT, valid);
 printCsvValue(osd_home_state, osd_homeIntSig, "osd_home_state",lineT, valid);
 printCsvValue(fixed_altitedue, osd_homeIntSig, "fixed_altitedue",lineT, valid);
 printCsvValue(course_lock_torsion, osd_homeIntSig, "course_lock_torsion",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
