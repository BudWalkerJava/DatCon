package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class battery_info_17 extends Record {
protected boolean valid = false;

protected int design_capacity = (int)0;
protected int full_charge_capacity = (int)0;
protected int remaining_capacity = (int)0;
protected int pack_voltage = (int)0;
protected short current = (short)0;
protected short life_percentage = (short)0;
protected short capacity_percentage = (short)0;
protected short temperature = (short)0;
protected int cycle_count = (int)0;
protected int serial_number = (int)0;
protected int cell1 = (int)0;
protected int cell2 = (int)0;
protected int cell3 = (int)0;
protected int cell4 = (int)0;
protected int cell5 = (int)0;
protected int cell6 = (int)0;
protected short average_current = (short)0;
protected short right = (short)0;
protected long error_count = (long)0;
protected long n_discharge_times = (long)0;
protected long current_status = (long)0;
protected int vol_main = (int)0;

      public battery_info_17(ConvertDat convertDat) {
           super(convertDat, 17, 47);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 design_capacity = _payload.getUnsignedShort(0);
 full_charge_capacity = _payload.getUnsignedShort(2);
 remaining_capacity = _payload.getUnsignedShort(4);
 pack_voltage = _payload.getUnsignedShort(6);
 current = _payload.getShort(8);
life_percentage = _payload.getUnsignedByte(10);
capacity_percentage = _payload.getUnsignedByte(11);
 temperature = _payload.getShort(12);
 cycle_count = _payload.getUnsignedShort(14);
 serial_number = _payload.getUnsignedShort(16);
 cell1 = _payload.getUnsignedShort(18);
 cell2 = _payload.getUnsignedShort(20);
 cell3 = _payload.getUnsignedShort(22);
 cell4 = _payload.getUnsignedShort(24);
 cell5 = _payload.getUnsignedShort(26);
 cell6 = _payload.getUnsignedShort(28);
 average_current = _payload.getShort(30);
right = _payload.getUnsignedByte(32);
 error_count = _payload.getUnsignedInt(33);
 n_discharge_times = _payload.getUnsignedInt(37);
 current_status = _payload.getUnsignedInt(41);
 vol_main = _payload.getUnsignedShort(45);
} catch (Exception e) {RecordException(e);}}


    protected static Signal battery_infoIntSig = Signal
.SeriesInt("battery_info", "", null, Units.noUnits);
    protected static Signal battery_infoFloatSig = Signal
.SeriesFloat("battery_info", "", null, Units.noUnits);
    protected static Signal battery_infoDoubleSig = Signal
.SeriesDouble("battery_info", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(design_capacity, battery_infoIntSig, "design_capacity",lineT, valid);
 printCsvValue(full_charge_capacity, battery_infoIntSig, "full_charge_capacity",lineT, valid);
 printCsvValue(remaining_capacity, battery_infoIntSig, "remaining_capacity",lineT, valid);
 printCsvValue(pack_voltage, battery_infoIntSig, "pack_voltage",lineT, valid);
 printCsvValue(current, battery_infoIntSig, "current",lineT, valid);
 printCsvValue(life_percentage, battery_infoIntSig, "life_percentage",lineT, valid);
 printCsvValue(capacity_percentage, battery_infoIntSig, "capacity_percentage",lineT, valid);
 printCsvValue(temperature, battery_infoIntSig, "temperature",lineT, valid);
 printCsvValue(cycle_count, battery_infoIntSig, "cycle_count",lineT, valid);
 printCsvValue(serial_number, battery_infoIntSig, "serial_number",lineT, valid);
 printCsvValue(cell1, battery_infoIntSig, "cell1",lineT, valid);
 printCsvValue(cell2, battery_infoIntSig, "cell2",lineT, valid);
 printCsvValue(cell3, battery_infoIntSig, "cell3",lineT, valid);
 printCsvValue(cell4, battery_infoIntSig, "cell4",lineT, valid);
 printCsvValue(cell5, battery_infoIntSig, "cell5",lineT, valid);
 printCsvValue(cell6, battery_infoIntSig, "cell6",lineT, valid);
 printCsvValue(average_current, battery_infoIntSig, "average_current",lineT, valid);
 printCsvValue(right, battery_infoIntSig, "right",lineT, valid);
 printCsvValue(error_count, battery_infoIntSig, "error_count",lineT, valid);
 printCsvValue(n_discharge_times, battery_infoIntSig, "n_discharge_times",lineT, valid);
 printCsvValue(current_status, battery_infoIntSig, "current_status",lineT, valid);
 printCsvValue(vol_main, battery_infoIntSig, "vol_main",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
