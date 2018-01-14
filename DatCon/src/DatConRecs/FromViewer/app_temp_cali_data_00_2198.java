package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class app_temp_cali_data_00_2198 extends Record {
protected boolean valid = false;

protected short start_flag_00 = (short)0;
protected short state_00 = (short)0;
protected short cali_cnt_00 = (short)0;
protected short temp_ready_00 = (short)0;
protected short step_00 = (short)0;
protected short cali_type_00 = (short)0;
protected int tick_00 = (int)0;
protected short grav_acc_x_00 = (short)0;
protected short grav_acc_y_00 = (short)0;
protected short grav_acc_z_00 = (short)0;
protected short dst_cali_temp_00 = (short)0;
protected float dst_cali_temp_00_1 = (float)0;
protected float temp_min_00 = (float)0;
protected float temp_max_00 = (float)0;
protected short temp_cali_status_00 = (short)0;
protected short base_cali_status_00 = (short)0;
protected short cfg_temp_cali_fw_version_00 = (short)0;
protected short cur_temp_cali_fw_version_00 = (short)0;
protected float temp_bw_x_00 = (float)0;
protected float temp_bw_y_00 = (float)0;
protected float temp_bw_z_00 = (float)0;
protected float temp_ba_x_00 = (float)0;
protected float temp_ba_y_00 = (float)0;
protected float temp_ba_z_00 = (float)0;
protected float temp_temp_00 = (float)0;
protected float base_bw_x_00 = (float)0;
protected float base_bw_y_00 = (float)0;
protected float base_bw_z_00 = (float)0;
protected float base_ba_x_00 = (float)0;
protected float base_ba_y_00 = (float)0;
protected float base_ba_z_00 = (float)0;
protected float base_temp_00 = (float)0;

      public app_temp_cali_data_00_2198(ConvertDat convertDat) {
           super(convertDat, 2198, 84);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

start_flag_00 = _payload.getUnsignedByte(0);
 state_00 = _payload.getByte(1);;
cali_cnt_00 = _payload.getUnsignedByte(2);
temp_ready_00 = _payload.getUnsignedByte(3);
step_00 = _payload.getUnsignedByte(4);
cali_type_00 = _payload.getUnsignedByte(5);
 tick_00 = _payload.getUnsignedShort(6);
 grav_acc_x_00 = _payload.getByte(8);;
 grav_acc_y_00 = _payload.getByte(9);;
 grav_acc_z_00 = _payload.getByte(10);;
 dst_cali_temp_00 = _payload.getByte(11);;
 dst_cali_temp_00_1 = _payload.getFloat(12);
 temp_min_00 = _payload.getFloat(16);
 temp_max_00 = _payload.getFloat(20);
temp_cali_status_00 = _payload.getUnsignedByte(24);
base_cali_status_00 = _payload.getUnsignedByte(25);
cfg_temp_cali_fw_version_00 = _payload.getUnsignedByte(26);
cur_temp_cali_fw_version_00 = _payload.getUnsignedByte(27);
 temp_bw_x_00 = _payload.getFloat(28);
 temp_bw_y_00 = _payload.getFloat(32);
 temp_bw_z_00 = _payload.getFloat(36);
 temp_ba_x_00 = _payload.getFloat(40);
 temp_ba_y_00 = _payload.getFloat(44);
 temp_ba_z_00 = _payload.getFloat(48);
 temp_temp_00 = _payload.getFloat(52);
 base_bw_x_00 = _payload.getFloat(56);
 base_bw_y_00 = _payload.getFloat(60);
 base_bw_z_00 = _payload.getFloat(64);
 base_ba_x_00 = _payload.getFloat(68);
 base_ba_y_00 = _payload.getFloat(72);
 base_ba_z_00 = _payload.getFloat(76);
 base_temp_00 = _payload.getFloat(80);
} catch (Exception e) {RecordException(e);}}


    protected static Signal app_temp_cali_data_00IntSig = Signal
.SeriesInt("app_temp_cali_data_00", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_00FloatSig = Signal
.SeriesFloat("app_temp_cali_data_00", "", null, Units.noUnits);
    protected static Signal app_temp_cali_data_00DoubleSig = Signal
.SeriesDouble("app_temp_cali_data_00", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(start_flag_00, app_temp_cali_data_00IntSig, "start_flag_00",lineT, valid);
 printCsvValue(state_00, app_temp_cali_data_00IntSig, "state_00",lineT, valid);
 printCsvValue(cali_cnt_00, app_temp_cali_data_00IntSig, "cali_cnt_00",lineT, valid);
 printCsvValue(temp_ready_00, app_temp_cali_data_00IntSig, "temp_ready_00",lineT, valid);
 printCsvValue(step_00, app_temp_cali_data_00IntSig, "step_00",lineT, valid);
 printCsvValue(cali_type_00, app_temp_cali_data_00IntSig, "cali_type_00",lineT, valid);
 printCsvValue(tick_00, app_temp_cali_data_00IntSig, "tick_00",lineT, valid);
 printCsvValue(grav_acc_x_00, app_temp_cali_data_00IntSig, "grav_acc_x_00",lineT, valid);
 printCsvValue(grav_acc_y_00, app_temp_cali_data_00IntSig, "grav_acc_y_00",lineT, valid);
 printCsvValue(grav_acc_z_00, app_temp_cali_data_00IntSig, "grav_acc_z_00",lineT, valid);
 printCsvValue(dst_cali_temp_00, app_temp_cali_data_00IntSig, "dst_cali_temp_00",lineT, valid);
 printCsvValue(dst_cali_temp_00_1, app_temp_cali_data_00FloatSig, "dst_cali_temp_00_1",lineT, valid);
 printCsvValue(temp_min_00, app_temp_cali_data_00FloatSig, "temp_min_00",lineT, valid);
 printCsvValue(temp_max_00, app_temp_cali_data_00FloatSig, "temp_max_00",lineT, valid);
 printCsvValue(temp_cali_status_00, app_temp_cali_data_00IntSig, "temp_cali_status_00",lineT, valid);
 printCsvValue(base_cali_status_00, app_temp_cali_data_00IntSig, "base_cali_status_00",lineT, valid);
 printCsvValue(cfg_temp_cali_fw_version_00, app_temp_cali_data_00IntSig, "cfg_temp_cali_fw_version_00",lineT, valid);
 printCsvValue(cur_temp_cali_fw_version_00, app_temp_cali_data_00IntSig, "cur_temp_cali_fw_version_00",lineT, valid);
 printCsvValue(temp_bw_x_00, app_temp_cali_data_00FloatSig, "temp_bw_x_00",lineT, valid);
 printCsvValue(temp_bw_y_00, app_temp_cali_data_00FloatSig, "temp_bw_y_00",lineT, valid);
 printCsvValue(temp_bw_z_00, app_temp_cali_data_00FloatSig, "temp_bw_z_00",lineT, valid);
 printCsvValue(temp_ba_x_00, app_temp_cali_data_00FloatSig, "temp_ba_x_00",lineT, valid);
 printCsvValue(temp_ba_y_00, app_temp_cali_data_00FloatSig, "temp_ba_y_00",lineT, valid);
 printCsvValue(temp_ba_z_00, app_temp_cali_data_00FloatSig, "temp_ba_z_00",lineT, valid);
 printCsvValue(temp_temp_00, app_temp_cali_data_00FloatSig, "temp_temp_00",lineT, valid);
 printCsvValue(base_bw_x_00, app_temp_cali_data_00FloatSig, "base_bw_x_00",lineT, valid);
 printCsvValue(base_bw_y_00, app_temp_cali_data_00FloatSig, "base_bw_y_00",lineT, valid);
 printCsvValue(base_bw_z_00, app_temp_cali_data_00FloatSig, "base_bw_z_00",lineT, valid);
 printCsvValue(base_ba_x_00, app_temp_cali_data_00FloatSig, "base_ba_x_00",lineT, valid);
 printCsvValue(base_ba_y_00, app_temp_cali_data_00FloatSig, "base_ba_y_00",lineT, valid);
 printCsvValue(base_ba_z_00, app_temp_cali_data_00FloatSig, "base_ba_z_00",lineT, valid);
 printCsvValue(base_temp_00, app_temp_cali_data_00FloatSig, "base_temp_00",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
