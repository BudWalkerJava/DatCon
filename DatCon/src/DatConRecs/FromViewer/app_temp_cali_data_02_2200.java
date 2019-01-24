package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class app_temp_cali_data_02_2200 extends Record {
protected boolean valid = false;

protected short start_flag_02 = (short)0;
protected short state_02 = (short)0;
protected short cali_cnt_02 = (short)0;
protected short temp_ready_02 = (short)0;
protected short step_02 = (short)0;
protected short cali_type_02 = (short)0;
protected int tick_02 = (int)0;
protected short grav_acc_x_02 = (short)0;
protected short grav_acc_y_02 = (short)0;
protected short grav_acc_z_02 = (short)0;
protected short dst_cali_temp_02 = (short)0;
protected float temp_min_02 = (float)0;
protected float temp_max_02 = (float)0;
protected short temp_cali_status_02 = (short)0;
protected short base_cali_status_02 = (short)0;
protected short cfg_temp_cali_fw_version_02 = (short)0;
protected short cur_temp_cali_fw_version_02 = (short)0;
protected float temp_bw_x_02 = (float)0;
protected float temp_bw_y_02 = (float)0;
protected float temp_bw_z_02 = (float)0;
protected float temp_ba_x_02 = (float)0;
protected float temp_ba_y_02 = (float)0;
protected float temp_ba_z_02 = (float)0;
protected float temp_temp_02 = (float)0;
protected float base_bw_x_02 = (float)0;
protected float base_bw_y_02 = (float)0;
protected float base_bw_z_02 = (float)0;
protected float base_ba_x_02 = (float)0;
protected float base_ba_y_02 = (float)0;
protected float base_ba_z_02 = (float)0;
protected float base_temp_02 = (float)0;

      public app_temp_cali_data_02_2200(ConvertDat convertDat) {
           super(convertDat, 2200, 80);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

start_flag_02 = _payload.getUnsignedByte(0);
 state_02 = _payload.getByte(1);;
cali_cnt_02 = _payload.getUnsignedByte(2);
temp_ready_02 = _payload.getUnsignedByte(3);
step_02 = _payload.getUnsignedByte(4);
cali_type_02 = _payload.getUnsignedByte(5);
 tick_02 = _payload.getUnsignedShort(6);
 grav_acc_x_02 = _payload.getByte(8);;
 grav_acc_y_02 = _payload.getByte(9);;
 grav_acc_z_02 = _payload.getByte(10);;
 dst_cali_temp_02 = _payload.getByte(11);;
 temp_min_02 = _payload.getFloat(12);
 temp_max_02 = _payload.getFloat(16);
temp_cali_status_02 = _payload.getUnsignedByte(20);
base_cali_status_02 = _payload.getUnsignedByte(21);
cfg_temp_cali_fw_version_02 = _payload.getUnsignedByte(22);
cur_temp_cali_fw_version_02 = _payload.getUnsignedByte(23);
 temp_bw_x_02 = _payload.getFloat(24);
 temp_bw_y_02 = _payload.getFloat(28);
 temp_bw_z_02 = _payload.getFloat(32);
 temp_ba_x_02 = _payload.getFloat(36);
 temp_ba_y_02 = _payload.getFloat(40);
 temp_ba_z_02 = _payload.getFloat(44);
 temp_temp_02 = _payload.getFloat(48);
 base_bw_x_02 = _payload.getFloat(52);
 base_bw_y_02 = _payload.getFloat(56);
 base_bw_z_02 = _payload.getFloat(60);
 base_ba_x_02 = _payload.getFloat(64);
 base_ba_y_02 = _payload.getFloat(68);
 base_ba_z_02 = _payload.getFloat(72);
 base_temp_02 = _payload.getFloat(76);
} catch (Exception e) {RecordException(e);}}


    protected static Signal app_temp_cali_data_02IntSig = Signal
.SeriesInt("app_temp_cali_data_02", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_02FloatSig = Signal
.SeriesFloat("app_temp_cali_data_02", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_02DoubleSig = Signal
.SeriesDouble("app_temp_cali_data_02", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(start_flag_02, app_temp_cali_data_02IntSig, "start_flag_02",lineT, valid);
 printCsvValue(state_02, app_temp_cali_data_02IntSig, "state_02",lineT, valid);
 printCsvValue(cali_cnt_02, app_temp_cali_data_02IntSig, "cali_cnt_02",lineT, valid);
 printCsvValue(temp_ready_02, app_temp_cali_data_02IntSig, "temp_ready_02",lineT, valid);
 printCsvValue(step_02, app_temp_cali_data_02IntSig, "step_02",lineT, valid);
 printCsvValue(cali_type_02, app_temp_cali_data_02IntSig, "cali_type_02",lineT, valid);
 printCsvValue(tick_02, app_temp_cali_data_02IntSig, "tick_02",lineT, valid);
 printCsvValue(grav_acc_x_02, app_temp_cali_data_02IntSig, "grav_acc_x_02",lineT, valid);
 printCsvValue(grav_acc_y_02, app_temp_cali_data_02IntSig, "grav_acc_y_02",lineT, valid);
 printCsvValue(grav_acc_z_02, app_temp_cali_data_02IntSig, "grav_acc_z_02",lineT, valid);
 printCsvValue(dst_cali_temp_02, app_temp_cali_data_02IntSig, "dst_cali_temp_02",lineT, valid);
 printCsvValue(temp_min_02, app_temp_cali_data_02FloatSig, "temp_min_02",lineT, valid);
 printCsvValue(temp_max_02, app_temp_cali_data_02FloatSig, "temp_max_02",lineT, valid);
 printCsvValue(temp_cali_status_02, app_temp_cali_data_02IntSig, "temp_cali_status_02",lineT, valid);
 printCsvValue(base_cali_status_02, app_temp_cali_data_02IntSig, "base_cali_status_02",lineT, valid);
 printCsvValue(cfg_temp_cali_fw_version_02, app_temp_cali_data_02IntSig, "cfg_temp_cali_fw_version_02",lineT, valid);
 printCsvValue(cur_temp_cali_fw_version_02, app_temp_cali_data_02IntSig, "cur_temp_cali_fw_version_02",lineT, valid);
 printCsvValue(temp_bw_x_02, app_temp_cali_data_02FloatSig, "temp_bw_x_02",lineT, valid);
 printCsvValue(temp_bw_y_02, app_temp_cali_data_02FloatSig, "temp_bw_y_02",lineT, valid);
 printCsvValue(temp_bw_z_02, app_temp_cali_data_02FloatSig, "temp_bw_z_02",lineT, valid);
 printCsvValue(temp_ba_x_02, app_temp_cali_data_02FloatSig, "temp_ba_x_02",lineT, valid);
 printCsvValue(temp_ba_y_02, app_temp_cali_data_02FloatSig, "temp_ba_y_02",lineT, valid);
 printCsvValue(temp_ba_z_02, app_temp_cali_data_02FloatSig, "temp_ba_z_02",lineT, valid);
 printCsvValue(temp_temp_02, app_temp_cali_data_02FloatSig, "temp_temp_02",lineT, valid);
 printCsvValue(base_bw_x_02, app_temp_cali_data_02FloatSig, "base_bw_x_02",lineT, valid);
 printCsvValue(base_bw_y_02, app_temp_cali_data_02FloatSig, "base_bw_y_02",lineT, valid);
 printCsvValue(base_bw_z_02, app_temp_cali_data_02FloatSig, "base_bw_z_02",lineT, valid);
 printCsvValue(base_ba_x_02, app_temp_cali_data_02FloatSig, "base_ba_x_02",lineT, valid);
 printCsvValue(base_ba_y_02, app_temp_cali_data_02FloatSig, "base_ba_y_02",lineT, valid);
 printCsvValue(base_ba_z_02, app_temp_cali_data_02FloatSig, "base_ba_z_02",lineT, valid);
 printCsvValue(base_temp_02, app_temp_cali_data_02FloatSig, "base_temp_02",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
