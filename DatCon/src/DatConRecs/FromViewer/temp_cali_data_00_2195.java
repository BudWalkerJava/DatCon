package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_cali_data_00_2195 extends Record {
protected boolean valid = false;

protected short _start_flag_00 = (short)0;
protected short _state_00 = (short)0;
protected short _cali_cnt_00 = (short)0;
protected short _temp_ready_00 = (short)0;
protected short _step_00 = (short)0;
protected short _cali_type_00 = (short)0;
protected int _tick_00 = (int)0;
protected short _grav_acc_x_00 = (short)0;
protected short _grav_acc_y_00 = (short)0;
protected short _grav_acc_z_00 = (short)0;
protected short _dst_cali_temp_00 = (short)0;
protected float _dst_cali_temp_00_1 = (float)0;
protected float _temp_min_00 = (float)0;
protected float _temp_max_00 = (float)0;
protected short _temp_cali_status_00 = (short)0;
protected short _base_cali_status_00 = (short)0;
protected short _cfg_temp_cali_fw_version_00 = (short)0;
protected short _cur_temp_cali_fw_version_00 = (short)0;
protected float _temp_bw_x_00 = (float)0;
protected float _temp_bw_y_00 = (float)0;
protected float _temp_bw_z_00 = (float)0;
protected float _temp_ba_x_00 = (float)0;
protected float _temp_ba_y_00 = (float)0;
protected float _temp_ba_z_00 = (float)0;
protected float _temp_temp_00 = (float)0;
protected float _base_bw_x_00 = (float)0;
protected float _base_bw_y_00 = (float)0;
protected float _base_bw_z_00 = (float)0;
protected float _base_ba_x_00 = (float)0;
protected float _base_ba_y_00 = (float)0;
protected float _base_ba_z_00 = (float)0;
protected float _base_temp_00 = (float)0;

      public temp_cali_data_00_2195(ConvertDat convertDat) {
           super(convertDat, 2195, 84);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

_start_flag_00 = _payload.getUnsignedByte(0);
 _state_00 = _payload.getByte(1);;
_cali_cnt_00 = _payload.getUnsignedByte(2);
_temp_ready_00 = _payload.getUnsignedByte(3);
_step_00 = _payload.getUnsignedByte(4);
_cali_type_00 = _payload.getUnsignedByte(5);
 _tick_00 = _payload.getUnsignedShort(6);
 _grav_acc_x_00 = _payload.getByte(8);;
 _grav_acc_y_00 = _payload.getByte(9);;
 _grav_acc_z_00 = _payload.getByte(10);;
 _dst_cali_temp_00 = _payload.getByte(11);;
 _dst_cali_temp_00_1 = _payload.getFloat(12);
 _temp_min_00 = _payload.getFloat(16);
 _temp_max_00 = _payload.getFloat(20);
_temp_cali_status_00 = _payload.getUnsignedByte(24);
_base_cali_status_00 = _payload.getUnsignedByte(25);
_cfg_temp_cali_fw_version_00 = _payload.getUnsignedByte(26);
_cur_temp_cali_fw_version_00 = _payload.getUnsignedByte(27);
 _temp_bw_x_00 = _payload.getFloat(28);
 _temp_bw_y_00 = _payload.getFloat(32);
 _temp_bw_z_00 = _payload.getFloat(36);
 _temp_ba_x_00 = _payload.getFloat(40);
 _temp_ba_y_00 = _payload.getFloat(44);
 _temp_ba_z_00 = _payload.getFloat(48);
 _temp_temp_00 = _payload.getFloat(52);
 _base_bw_x_00 = _payload.getFloat(56);
 _base_bw_y_00 = _payload.getFloat(60);
 _base_bw_z_00 = _payload.getFloat(64);
 _base_ba_x_00 = _payload.getFloat(68);
 _base_ba_y_00 = _payload.getFloat(72);
 _base_ba_z_00 = _payload.getFloat(76);
 _base_temp_00 = _payload.getFloat(80);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_cali_data_00IntSig = Signal
.SeriesInt("temp_cali_data_00", "", null, Units.noUnits);
    protected static Signal temp_cali_data_00FloatSig = Signal
.SeriesFloat("temp_cali_data_00", "", null, Units.noUnits);
    protected static Signal temp_cali_data_00DoubleSig = Signal
.SeriesDouble("temp_cali_data_00", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(_start_flag_00, temp_cali_data_00IntSig, "_start_flag_00",lineT, valid);
 printCsvValue(_state_00, temp_cali_data_00IntSig, "_state_00",lineT, valid);
 printCsvValue(_cali_cnt_00, temp_cali_data_00IntSig, "_cali_cnt_00",lineT, valid);
 printCsvValue(_temp_ready_00, temp_cali_data_00IntSig, "_temp_ready_00",lineT, valid);
 printCsvValue(_step_00, temp_cali_data_00IntSig, "_step_00",lineT, valid);
 printCsvValue(_cali_type_00, temp_cali_data_00IntSig, "_cali_type_00",lineT, valid);
 printCsvValue(_tick_00, temp_cali_data_00IntSig, "_tick_00",lineT, valid);
 printCsvValue(_grav_acc_x_00, temp_cali_data_00IntSig, "_grav_acc_x_00",lineT, valid);
 printCsvValue(_grav_acc_y_00, temp_cali_data_00IntSig, "_grav_acc_y_00",lineT, valid);
 printCsvValue(_grav_acc_z_00, temp_cali_data_00IntSig, "_grav_acc_z_00",lineT, valid);
 printCsvValue(_dst_cali_temp_00, temp_cali_data_00IntSig, "_dst_cali_temp_00",lineT, valid);
 printCsvValue(_dst_cali_temp_00_1, temp_cali_data_00FloatSig, "_dst_cali_temp_00_1",lineT, valid);
 printCsvValue(_temp_min_00, temp_cali_data_00FloatSig, "_temp_min_00",lineT, valid);
 printCsvValue(_temp_max_00, temp_cali_data_00FloatSig, "_temp_max_00",lineT, valid);
 printCsvValue(_temp_cali_status_00, temp_cali_data_00IntSig, "_temp_cali_status_00",lineT, valid);
 printCsvValue(_base_cali_status_00, temp_cali_data_00IntSig, "_base_cali_status_00",lineT, valid);
 printCsvValue(_cfg_temp_cali_fw_version_00, temp_cali_data_00IntSig, "_cfg_temp_cali_fw_version_00",lineT, valid);
 printCsvValue(_cur_temp_cali_fw_version_00, temp_cali_data_00IntSig, "_cur_temp_cali_fw_version_00",lineT, valid);
 printCsvValue(_temp_bw_x_00, temp_cali_data_00FloatSig, "_temp_bw_x_00",lineT, valid);
 printCsvValue(_temp_bw_y_00, temp_cali_data_00FloatSig, "_temp_bw_y_00",lineT, valid);
 printCsvValue(_temp_bw_z_00, temp_cali_data_00FloatSig, "_temp_bw_z_00",lineT, valid);
 printCsvValue(_temp_ba_x_00, temp_cali_data_00FloatSig, "_temp_ba_x_00",lineT, valid);
 printCsvValue(_temp_ba_y_00, temp_cali_data_00FloatSig, "_temp_ba_y_00",lineT, valid);
 printCsvValue(_temp_ba_z_00, temp_cali_data_00FloatSig, "_temp_ba_z_00",lineT, valid);
 printCsvValue(_temp_temp_00, temp_cali_data_00FloatSig, "_temp_temp_00",lineT, valid);
 printCsvValue(_base_bw_x_00, temp_cali_data_00FloatSig, "_base_bw_x_00",lineT, valid);
 printCsvValue(_base_bw_y_00, temp_cali_data_00FloatSig, "_base_bw_y_00",lineT, valid);
 printCsvValue(_base_bw_z_00, temp_cali_data_00FloatSig, "_base_bw_z_00",lineT, valid);
 printCsvValue(_base_ba_x_00, temp_cali_data_00FloatSig, "_base_ba_x_00",lineT, valid);
 printCsvValue(_base_ba_y_00, temp_cali_data_00FloatSig, "_base_ba_y_00",lineT, valid);
 printCsvValue(_base_ba_z_00, temp_cali_data_00FloatSig, "_base_ba_z_00",lineT, valid);
 printCsvValue(_base_temp_00, temp_cali_data_00FloatSig, "_base_temp_00",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
