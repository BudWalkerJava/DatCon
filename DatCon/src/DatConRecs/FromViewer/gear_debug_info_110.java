package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class gear_debug_info_110 extends Record {
protected boolean valid = false;

protected short gear_mode = (short)0;
protected short gear_state = (short)0;
protected short gear_cmd = (short)0;
protected short gear_speed = (short)0;
protected long gear_counter = (long)0;
protected short gear_pack_flag = (short)0;
protected short gear_pack_req = (short)0;
protected short gear_pack_type = (short)0;
protected short gear_pack_state = (short)0;
protected short gear_pack_manual_cmd = (short)0;
protected short gear_rc_cmd = (short)0;
protected short gear_app_req = (short)0;
protected short gear_app_cmd = (short)0;
protected short gear_rc_raw_input = (short)0;

      public gear_debug_info_110(ConvertDat convertDat) {
           super(convertDat, 110, 18);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

gear_mode = _payload.getUnsignedByte(0);
gear_state = _payload.getUnsignedByte(1);
gear_cmd = _payload.getUnsignedByte(2);
gear_speed = _payload.getUnsignedByte(3);
 gear_counter = _payload.getUnsignedInt(4);
gear_pack_flag = _payload.getUnsignedByte(8);
gear_pack_req = _payload.getUnsignedByte(9);
gear_pack_type = _payload.getUnsignedByte(10);
gear_pack_state = _payload.getUnsignedByte(11);
gear_pack_manual_cmd = _payload.getUnsignedByte(12);
gear_rc_cmd = _payload.getUnsignedByte(13);
gear_app_req = _payload.getUnsignedByte(14);
gear_app_cmd = _payload.getUnsignedByte(15);
 gear_rc_raw_input = _payload.getShort(16);
} catch (Exception e) {RecordException(e);}}


    protected static Signal gear_debug_infoIntSig = Signal
.SeriesInt("gear_debug_info", "", null, Units.noUnits);
    protected static Signal gear_debug_infoFloatSig = Signal
.SeriesFloat("gear_debug_info", "", null, Units.noUnits);
    protected static Signal gear_debug_infoDoubleSig = Signal
.SeriesDouble("gear_debug_info", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(gear_mode, gear_debug_infoIntSig, "gear_mode",lineT, valid);
 printCsvValue(gear_state, gear_debug_infoIntSig, "gear_state",lineT, valid);
 printCsvValue(gear_cmd, gear_debug_infoIntSig, "gear_cmd",lineT, valid);
 printCsvValue(gear_speed, gear_debug_infoIntSig, "gear_speed",lineT, valid);
 printCsvValue(gear_counter, gear_debug_infoIntSig, "gear_counter",lineT, valid);
 printCsvValue(gear_pack_flag, gear_debug_infoIntSig, "gear_pack_flag",lineT, valid);
 printCsvValue(gear_pack_req, gear_debug_infoIntSig, "gear_pack_req",lineT, valid);
 printCsvValue(gear_pack_type, gear_debug_infoIntSig, "gear_pack_type",lineT, valid);
 printCsvValue(gear_pack_state, gear_debug_infoIntSig, "gear_pack_state",lineT, valid);
 printCsvValue(gear_pack_manual_cmd, gear_debug_infoIntSig, "gear_pack_manual_cmd",lineT, valid);
 printCsvValue(gear_rc_cmd, gear_debug_infoIntSig, "gear_rc_cmd",lineT, valid);
 printCsvValue(gear_app_req, gear_debug_infoIntSig, "gear_app_req",lineT, valid);
 printCsvValue(gear_app_cmd, gear_debug_infoIntSig, "gear_app_cmd",lineT, valid);
 printCsvValue(gear_rc_raw_input, gear_debug_infoIntSig, "gear_rc_raw_input",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
