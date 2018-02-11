package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class app_temp_cali_data_01_2199 extends Record {
protected boolean valid = false;

protected short start_flag_01 = (short)0;
protected short state_01 = (short)0;
protected short cali_cnt_01 = (short)0;
protected short temp_ready_01 = (short)0;
protected short step_01 = (short)0;
protected short cali_type_01 = (short)0;
protected int tick_01 = (int)0;
protected short grav_acc_x_01 = (short)0;
protected short grav_acc_y_01 = (short)0;
protected short grav_acc_z_01 = (short)0;
protected short dst_cali_temp_01 = (short)0;
protected float dst_cali_temp_01_1 = (float)0;
protected float temp_min_01 = (float)0;
protected float temp_max_01 = (float)0;
protected short temp_cali_status_01 = (short)0;
protected short base_cali_status_01 = (short)0;
protected short cfg_temp_cali_fw_version_01 = (short)0;
protected short cur_temp_cali_fw_version_01 = (short)0;
protected float temp_bw_x_01 = (float)0;
protected float temp_bw_y_01 = (float)0;
protected float temp_bw_z_01 = (float)0;
protected float temp_ba_x_01 = (float)0;
protected float temp_ba_y_01 = (float)0;
protected float temp_ba_z_01 = (float)0;
protected float temp_temp_01 = (float)0;
protected float base_bw_x_01 = (float)0;
protected float base_bw_y_01 = (float)0;
protected float base_bw_z_01 = (float)0;
protected float base_ba_x_01 = (float)0;
protected float base_ba_y_01 = (float)0;
protected float base_ba_z_01 = (float)0;
protected float base_temp_01 = (float)0;

      public app_temp_cali_data_01_2199(ConvertDat convertDat) {
           super(convertDat, 2199, 84);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

start_flag_01 = _payload.getUnsignedByte(0);
 state_01 = _payload.getByte(1);;
cali_cnt_01 = _payload.getUnsignedByte(2);
temp_ready_01 = _payload.getUnsignedByte(3);
step_01 = _payload.getUnsignedByte(4);
cali_type_01 = _payload.getUnsignedByte(5);
 tick_01 = _payload.getUnsignedShort(6);
 grav_acc_x_01 = _payload.getByte(8);;
 grav_acc_y_01 = _payload.getByte(9);;
 grav_acc_z_01 = _payload.getByte(10);;
 dst_cali_temp_01 = _payload.getByte(11);;
 dst_cali_temp_01_1 = _payload.getFloat(12);
 temp_min_01 = _payload.getFloat(16);
 temp_max_01 = _payload.getFloat(20);
temp_cali_status_01 = _payload.getUnsignedByte(24);
base_cali_status_01 = _payload.getUnsignedByte(25);
cfg_temp_cali_fw_version_01 = _payload.getUnsignedByte(26);
cur_temp_cali_fw_version_01 = _payload.getUnsignedByte(27);
 temp_bw_x_01 = _payload.getFloat(28);
 temp_bw_y_01 = _payload.getFloat(32);
 temp_bw_z_01 = _payload.getFloat(36);
 temp_ba_x_01 = _payload.getFloat(40);
 temp_ba_y_01 = _payload.getFloat(44);
 temp_ba_z_01 = _payload.getFloat(48);
 temp_temp_01 = _payload.getFloat(52);
 base_bw_x_01 = _payload.getFloat(56);
 base_bw_y_01 = _payload.getFloat(60);
 base_bw_z_01 = _payload.getFloat(64);
 base_ba_x_01 = _payload.getFloat(68);
 base_ba_y_01 = _payload.getFloat(72);
 base_ba_z_01 = _payload.getFloat(76);
 base_temp_01 = _payload.getFloat(80);
} catch (Exception e) {RecordException(e);}}


    protected static Signal app_temp_cali_data_01IntSig = Signal
.SeriesInt("app_temp_cali_data_01", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_01FloatSig = Signal
.SeriesFloat("app_temp_cali_data_01", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_01DoubleSig = Signal
.SeriesDouble("app_temp_cali_data_01", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(start_flag_01, app_temp_cali_data_01IntSig, "start_flag_01",lineT, valid);
 printCsvValue(state_01, app_temp_cali_data_01IntSig, "state_01",lineT, valid);
 printCsvValue(cali_cnt_01, app_temp_cali_data_01IntSig, "cali_cnt_01",lineT, valid);
 printCsvValue(temp_ready_01, app_temp_cali_data_01IntSig, "temp_ready_01",lineT, valid);
 printCsvValue(step_01, app_temp_cali_data_01IntSig, "step_01",lineT, valid);
 printCsvValue(cali_type_01, app_temp_cali_data_01IntSig, "cali_type_01",lineT, valid);
 printCsvValue(tick_01, app_temp_cali_data_01IntSig, "tick_01",lineT, valid);
 printCsvValue(grav_acc_x_01, app_temp_cali_data_01IntSig, "grav_acc_x_01",lineT, valid);
 printCsvValue(grav_acc_y_01, app_temp_cali_data_01IntSig, "grav_acc_y_01",lineT, valid);
 printCsvValue(grav_acc_z_01, app_temp_cali_data_01IntSig, "grav_acc_z_01",lineT, valid);
 printCsvValue(dst_cali_temp_01, app_temp_cali_data_01IntSig, "dst_cali_temp_01",lineT, valid);
 printCsvValue(dst_cali_temp_01_1, app_temp_cali_data_01FloatSig, "dst_cali_temp_01_1",lineT, valid);
 printCsvValue(temp_min_01, app_temp_cali_data_01FloatSig, "temp_min_01",lineT, valid);
 printCsvValue(temp_max_01, app_temp_cali_data_01FloatSig, "temp_max_01",lineT, valid);
 printCsvValue(temp_cali_status_01, app_temp_cali_data_01IntSig, "temp_cali_status_01",lineT, valid);
 printCsvValue(base_cali_status_01, app_temp_cali_data_01IntSig, "base_cali_status_01",lineT, valid);
 printCsvValue(cfg_temp_cali_fw_version_01, app_temp_cali_data_01IntSig, "cfg_temp_cali_fw_version_01",lineT, valid);
 printCsvValue(cur_temp_cali_fw_version_01, app_temp_cali_data_01IntSig, "cur_temp_cali_fw_version_01",lineT, valid);
 printCsvValue(temp_bw_x_01, app_temp_cali_data_01FloatSig, "temp_bw_x_01",lineT, valid);
 printCsvValue(temp_bw_y_01, app_temp_cali_data_01FloatSig, "temp_bw_y_01",lineT, valid);
 printCsvValue(temp_bw_z_01, app_temp_cali_data_01FloatSig, "temp_bw_z_01",lineT, valid);
 printCsvValue(temp_ba_x_01, app_temp_cali_data_01FloatSig, "temp_ba_x_01",lineT, valid);
 printCsvValue(temp_ba_y_01, app_temp_cali_data_01FloatSig, "temp_ba_y_01",lineT, valid);
 printCsvValue(temp_ba_z_01, app_temp_cali_data_01FloatSig, "temp_ba_z_01",lineT, valid);
 printCsvValue(temp_temp_01, app_temp_cali_data_01FloatSig, "temp_temp_01",lineT, valid);
 printCsvValue(base_bw_x_01, app_temp_cali_data_01FloatSig, "base_bw_x_01",lineT, valid);
 printCsvValue(base_bw_y_01, app_temp_cali_data_01FloatSig, "base_bw_y_01",lineT, valid);
 printCsvValue(base_bw_z_01, app_temp_cali_data_01FloatSig, "base_bw_z_01",lineT, valid);
 printCsvValue(base_ba_x_01, app_temp_cali_data_01FloatSig, "base_ba_x_01",lineT, valid);
 printCsvValue(base_ba_y_01, app_temp_cali_data_01FloatSig, "base_ba_y_01",lineT, valid);
 printCsvValue(base_ba_z_01, app_temp_cali_data_01FloatSig, "base_ba_z_01",lineT, valid);
 printCsvValue(base_temp_01, app_temp_cali_data_01FloatSig, "base_temp_01",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
