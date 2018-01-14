package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class escm_52721 extends Record {
protected boolean valid = false;

protected short esc1_status = (short)0;
protected short esc1_current = (short)0;
protected short esc1_speed = (short)0;
protected short esc1_voltage = (short)0;
protected short esc1_temperature = (short)0;
protected short esc1_ppm_recv = (short)0;
protected short esc1_v_out = (short)0;
protected short esc1_debug0 = (short)0;
protected short esc1_debug1 = (short)0;
protected short esc1_debug2 = (short)0;
protected short esc2_status = (short)0;
protected short esc2_current = (short)0;
protected short esc2_speed = (short)0;
protected short esc2_voltage = (short)0;
protected short esc2_temperature = (short)0;
protected short esc2_ppm_recv = (short)0;
protected short esc2_v_out = (short)0;
protected short esc2_debug0 = (short)0;
protected short esc2_debug1 = (short)0;
protected short esc2_debug2 = (short)0;
protected short esc3_status = (short)0;
protected short esc3_current = (short)0;
protected short esc3_speed = (short)0;
protected short esc3_voltage = (short)0;
protected short esc3_temperature = (short)0;
protected short esc3_ppm_recv = (short)0;
protected short esc3_v_out = (short)0;
protected short esc3_debug0 = (short)0;
protected short esc3_debug1 = (short)0;
protected short esc3_debug2 = (short)0;
protected short esc4_status = (short)0;
protected short esc4_current = (short)0;
protected short esc4_speed = (short)0;
protected short esc4_voltage = (short)0;
protected short esc4_temperature = (short)0;
protected short esc4_ppm_recv = (short)0;
protected short esc4_v_out = (short)0;
protected short esc4_debug0 = (short)0;
protected short esc4_debug1 = (short)0;
protected short esc4_debug2 = (short)0;

      public escm_52721(ConvertDat convertDat) {
           super(convertDat, 52721, 76);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

esc1_status = _payload.getUnsignedByte(0);
 esc1_current = _payload.getShort(1);
 esc1_speed = _payload.getShort(3);
 esc1_voltage = _payload.getShort(5);
 esc1_temperature = _payload.getShort(7);
 esc1_ppm_recv = _payload.getShort(9);
 esc1_v_out = _payload.getShort(11);
 esc1_debug0 = _payload.getShort(13);
 esc1_debug1 = _payload.getShort(15);
 esc1_debug2 = _payload.getShort(17);
esc2_status = _payload.getUnsignedByte(19);
 esc2_current = _payload.getShort(20);
 esc2_speed = _payload.getShort(22);
 esc2_voltage = _payload.getShort(24);
 esc2_temperature = _payload.getShort(26);
 esc2_ppm_recv = _payload.getShort(28);
 esc2_v_out = _payload.getShort(30);
 esc2_debug0 = _payload.getShort(32);
 esc2_debug1 = _payload.getShort(34);
 esc2_debug2 = _payload.getShort(36);
esc3_status = _payload.getUnsignedByte(38);
 esc3_current = _payload.getShort(39);
 esc3_speed = _payload.getShort(41);
 esc3_voltage = _payload.getShort(43);
 esc3_temperature = _payload.getShort(45);
 esc3_ppm_recv = _payload.getShort(47);
 esc3_v_out = _payload.getShort(49);
 esc3_debug0 = _payload.getShort(51);
 esc3_debug1 = _payload.getShort(53);
 esc3_debug2 = _payload.getShort(55);
esc4_status = _payload.getUnsignedByte(57);
 esc4_current = _payload.getShort(58);
 esc4_speed = _payload.getShort(60);
 esc4_voltage = _payload.getShort(62);
 esc4_temperature = _payload.getShort(64);
 esc4_ppm_recv = _payload.getShort(66);
 esc4_v_out = _payload.getShort(68);
 esc4_debug0 = _payload.getShort(70);
 esc4_debug1 = _payload.getShort(72);
 esc4_debug2 = _payload.getShort(74);
} catch (Exception e) {RecordException(e);}}


    protected static Signal escmIntSig = Signal
.SeriesInt("escm", "", null, Units.noUnits);
    protected static Signal escmFloatSig = Signal
.SeriesFloat("escm", "", null, Units.noUnits);
    protected static Signal escmDoubleSig = Signal
.SeriesDouble("escm", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(esc1_status, escmIntSig, "esc1_status",lineT, valid);
 printCsvValue(esc1_current, escmIntSig, "esc1_current",lineT, valid);
 printCsvValue(esc1_speed, escmIntSig, "esc1_speed",lineT, valid);
 printCsvValue(esc1_voltage, escmIntSig, "esc1_voltage",lineT, valid);
 printCsvValue(esc1_temperature, escmIntSig, "esc1_temperature",lineT, valid);
 printCsvValue(esc1_ppm_recv, escmIntSig, "esc1_ppm_recv",lineT, valid);
 printCsvValue(esc1_v_out, escmIntSig, "esc1_v_out",lineT, valid);
 printCsvValue(esc1_debug0, escmIntSig, "esc1_debug0",lineT, valid);
 printCsvValue(esc1_debug1, escmIntSig, "esc1_debug1",lineT, valid);
 printCsvValue(esc1_debug2, escmIntSig, "esc1_debug2",lineT, valid);
 printCsvValue(esc2_status, escmIntSig, "esc2_status",lineT, valid);
 printCsvValue(esc2_current, escmIntSig, "esc2_current",lineT, valid);
 printCsvValue(esc2_speed, escmIntSig, "esc2_speed",lineT, valid);
 printCsvValue(esc2_voltage, escmIntSig, "esc2_voltage",lineT, valid);
 printCsvValue(esc2_temperature, escmIntSig, "esc2_temperature",lineT, valid);
 printCsvValue(esc2_ppm_recv, escmIntSig, "esc2_ppm_recv",lineT, valid);
 printCsvValue(esc2_v_out, escmIntSig, "esc2_v_out",lineT, valid);
 printCsvValue(esc2_debug0, escmIntSig, "esc2_debug0",lineT, valid);
 printCsvValue(esc2_debug1, escmIntSig, "esc2_debug1",lineT, valid);
 printCsvValue(esc2_debug2, escmIntSig, "esc2_debug2",lineT, valid);
 printCsvValue(esc3_status, escmIntSig, "esc3_status",lineT, valid);
 printCsvValue(esc3_current, escmIntSig, "esc3_current",lineT, valid);
 printCsvValue(esc3_speed, escmIntSig, "esc3_speed",lineT, valid);
 printCsvValue(esc3_voltage, escmIntSig, "esc3_voltage",lineT, valid);
 printCsvValue(esc3_temperature, escmIntSig, "esc3_temperature",lineT, valid);
 printCsvValue(esc3_ppm_recv, escmIntSig, "esc3_ppm_recv",lineT, valid);
 printCsvValue(esc3_v_out, escmIntSig, "esc3_v_out",lineT, valid);
 printCsvValue(esc3_debug0, escmIntSig, "esc3_debug0",lineT, valid);
 printCsvValue(esc3_debug1, escmIntSig, "esc3_debug1",lineT, valid);
 printCsvValue(esc3_debug2, escmIntSig, "esc3_debug2",lineT, valid);
 printCsvValue(esc4_status, escmIntSig, "esc4_status",lineT, valid);
 printCsvValue(esc4_current, escmIntSig, "esc4_current",lineT, valid);
 printCsvValue(esc4_speed, escmIntSig, "esc4_speed",lineT, valid);
 printCsvValue(esc4_voltage, escmIntSig, "esc4_voltage",lineT, valid);
 printCsvValue(esc4_temperature, escmIntSig, "esc4_temperature",lineT, valid);
 printCsvValue(esc4_ppm_recv, escmIntSig, "esc4_ppm_recv",lineT, valid);
 printCsvValue(esc4_v_out, escmIntSig, "esc4_v_out",lineT, valid);
 printCsvValue(esc4_debug0, escmIntSig, "esc4_debug0",lineT, valid);
 printCsvValue(esc4_debug1, escmIntSig, "esc4_debug1",lineT, valid);
 printCsvValue(esc4_debug2, escmIntSig, "esc4_debug2",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
