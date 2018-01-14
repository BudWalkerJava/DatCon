package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class app_temp_cali_data_25 extends Record {
protected boolean valid = false;

protected short start_flag = (short)0;
protected short state = (short)0;
protected short cali_cnt = (short)0;
protected short temp_ready = (short)0;
protected short step = (short)0;
protected short cali_type = (short)0;
protected int tick = (int)0;
protected short grav_acc_x = (short)0;
protected short grav_acc_y = (short)0;
protected short grav_acc_z = (short)0;
protected short dst_cali_temp = (short)0;
protected float dst_cali_temp_1 = (float)0;
protected float temp_min = (float)0;
protected float temp_max = (float)0;
protected short temp_cali_status = (short)0;
protected short base_cali_status = (short)0;
protected short cfg_temp_cali_fw_version = (short)0;
protected short cur_temp_cali_fw_version = (short)0;
protected float temp_bw_x = (float)0;
protected float temp_bw_y = (float)0;
protected float temp_bw_z = (float)0;
protected float temp_ba_x = (float)0;
protected float temp_ba_y = (float)0;
protected float temp_ba_z = (float)0;
protected float temp_temp = (float)0;
protected float base_bw_x = (float)0;
protected float base_bw_y = (float)0;
protected float base_bw_z = (float)0;
protected float base_ba_x = (float)0;
protected float base_ba_y = (float)0;
protected float base_ba_z = (float)0;
protected float base_temp = (float)0;

      public app_temp_cali_data_25(ConvertDat convertDat) {
           super(convertDat, 25, 84);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

start_flag = _payload.getUnsignedByte(0);
 state = _payload.getByte(1);;
cali_cnt = _payload.getUnsignedByte(2);
temp_ready = _payload.getUnsignedByte(3);
step = _payload.getUnsignedByte(4);
cali_type = _payload.getUnsignedByte(5);
 tick = _payload.getUnsignedShort(6);
 grav_acc_x = _payload.getByte(8);;
 grav_acc_y = _payload.getByte(9);;
 grav_acc_z = _payload.getByte(10);;
 dst_cali_temp = _payload.getByte(11);;
 dst_cali_temp_1 = _payload.getFloat(12);
 temp_min = _payload.getFloat(16);
 temp_max = _payload.getFloat(20);
temp_cali_status = _payload.getUnsignedByte(24);
base_cali_status = _payload.getUnsignedByte(25);
cfg_temp_cali_fw_version = _payload.getUnsignedByte(26);
cur_temp_cali_fw_version = _payload.getUnsignedByte(27);
 temp_bw_x = _payload.getFloat(28);
 temp_bw_y = _payload.getFloat(32);
 temp_bw_z = _payload.getFloat(36);
 temp_ba_x = _payload.getFloat(40);
 temp_ba_y = _payload.getFloat(44);
 temp_ba_z = _payload.getFloat(48);
 temp_temp = _payload.getFloat(52);
 base_bw_x = _payload.getFloat(56);
 base_bw_y = _payload.getFloat(60);
 base_bw_z = _payload.getFloat(64);
 base_ba_x = _payload.getFloat(68);
 base_ba_y = _payload.getFloat(72);
 base_ba_z = _payload.getFloat(76);
 base_temp = _payload.getFloat(80);
} catch (Exception e) {RecordException(e);}}


    protected static Signal app_temp_cali_dataIntSig = Signal
.SeriesInt("app_temp_cali_data", "", null, Units.noUnits);
    protected static Signal app_temp_cali_dataFloatSig = Signal
.SeriesFloat("app_temp_cali_data", "", null, Units.noUnits);
    protected static Signal app_temp_cali_dataDoubleSig = Signal
.SeriesDouble("app_temp_cali_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(start_flag, app_temp_cali_dataIntSig, "start_flag",lineT, valid);
 printCsvValue(state, app_temp_cali_dataIntSig, "state",lineT, valid);
 printCsvValue(cali_cnt, app_temp_cali_dataIntSig, "cali_cnt",lineT, valid);
 printCsvValue(temp_ready, app_temp_cali_dataIntSig, "temp_ready",lineT, valid);
 printCsvValue(step, app_temp_cali_dataIntSig, "step",lineT, valid);
 printCsvValue(cali_type, app_temp_cali_dataIntSig, "cali_type",lineT, valid);
 printCsvValue(tick, app_temp_cali_dataIntSig, "tick",lineT, valid);
 printCsvValue(grav_acc_x, app_temp_cali_dataIntSig, "grav_acc_x",lineT, valid);
 printCsvValue(grav_acc_y, app_temp_cali_dataIntSig, "grav_acc_y",lineT, valid);
 printCsvValue(grav_acc_z, app_temp_cali_dataIntSig, "grav_acc_z",lineT, valid);
 printCsvValue(dst_cali_temp, app_temp_cali_dataIntSig, "dst_cali_temp",lineT, valid);
 printCsvValue(dst_cali_temp_1, app_temp_cali_dataFloatSig, "dst_cali_temp_1",lineT, valid);
 printCsvValue(temp_min, app_temp_cali_dataFloatSig, "temp_min",lineT, valid);
 printCsvValue(temp_max, app_temp_cali_dataFloatSig, "temp_max",lineT, valid);
 printCsvValue(temp_cali_status, app_temp_cali_dataIntSig, "temp_cali_status",lineT, valid);
 printCsvValue(base_cali_status, app_temp_cali_dataIntSig, "base_cali_status",lineT, valid);
 printCsvValue(cfg_temp_cali_fw_version, app_temp_cali_dataIntSig, "cfg_temp_cali_fw_version",lineT, valid);
 printCsvValue(cur_temp_cali_fw_version, app_temp_cali_dataIntSig, "cur_temp_cali_fw_version",lineT, valid);
 printCsvValue(temp_bw_x, app_temp_cali_dataFloatSig, "temp_bw_x",lineT, valid);
 printCsvValue(temp_bw_y, app_temp_cali_dataFloatSig, "temp_bw_y",lineT, valid);
 printCsvValue(temp_bw_z, app_temp_cali_dataFloatSig, "temp_bw_z",lineT, valid);
 printCsvValue(temp_ba_x, app_temp_cali_dataFloatSig, "temp_ba_x",lineT, valid);
 printCsvValue(temp_ba_y, app_temp_cali_dataFloatSig, "temp_ba_y",lineT, valid);
 printCsvValue(temp_ba_z, app_temp_cali_dataFloatSig, "temp_ba_z",lineT, valid);
 printCsvValue(temp_temp, app_temp_cali_dataFloatSig, "temp_temp",lineT, valid);
 printCsvValue(base_bw_x, app_temp_cali_dataFloatSig, "base_bw_x",lineT, valid);
 printCsvValue(base_bw_y, app_temp_cali_dataFloatSig, "base_bw_y",lineT, valid);
 printCsvValue(base_bw_z, app_temp_cali_dataFloatSig, "base_bw_z",lineT, valid);
 printCsvValue(base_ba_x, app_temp_cali_dataFloatSig, "base_ba_x",lineT, valid);
 printCsvValue(base_ba_y, app_temp_cali_dataFloatSig, "base_ba_y",lineT, valid);
 printCsvValue(base_ba_z, app_temp_cali_dataFloatSig, "base_ba_z",lineT, valid);
 printCsvValue(base_temp, app_temp_cali_dataFloatSig, "base_temp",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
