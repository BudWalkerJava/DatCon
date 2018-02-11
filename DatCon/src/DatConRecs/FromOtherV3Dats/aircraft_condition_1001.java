package src.DatConRecs.FromOtherV3Dats;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class aircraft_condition_1001 extends Record {
protected boolean valid = false;

protected short int_fsm = (short)0;
protected short fsm_state = (short)0;
protected short last_fsm = (short)0;
protected short near_gnd = (short)0;
protected short UP_state = (short)0;
protected short land_state = (short)0;
protected short safe_fltr = (short)0;

      public aircraft_condition_1001(ConvertDat convertDat) {
           super(convertDat, 1001, 8);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

int_fsm = _payload.getUnsignedByte(0);
fsm_state = _payload.getUnsignedByte(1);
last_fsm = _payload.getUnsignedByte(2);
near_gnd = _payload.getUnsignedByte(3);
UP_state = _payload.getUnsignedByte(4);
land_state = _payload.getUnsignedByte(5);
 safe_fltr = _payload.getShort(6);
} catch (Exception e) {RecordException(e);}}


    protected static Signal aircraft_conditionIntSig = Signal
.SeriesInt("aircraft_condition", "", null, Units.noUnits);
    protected static Signal aircraft_conditionFloatSig = Signal
.SeriesFloat("aircraft_condition", "", null, Units.noUnits);
    protected static Signal aircraft_conditionDoubleSig = Signal
.SeriesDouble("aircraft_condition", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(int_fsm, aircraft_conditionIntSig, "int_fsm",lineT, valid);
 printCsvValue(fsm_state, aircraft_conditionIntSig, "fsm_state",lineT, valid);
 printCsvValue(last_fsm, aircraft_conditionIntSig, "last_fsm",lineT, valid);
 printCsvValue(near_gnd, aircraft_conditionIntSig, "near_gnd",lineT, valid);
 printCsvValue(UP_state, aircraft_conditionIntSig, "UP_state",lineT, valid);
 printCsvValue(land_state, aircraft_conditionIntSig, "land_state",lineT, valid);
 printCsvValue(safe_fltr, aircraft_conditionIntSig, "safe_fltr",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
