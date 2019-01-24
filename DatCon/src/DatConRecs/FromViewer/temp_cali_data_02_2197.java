package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_cali_data_02_2197 extends Record {
protected boolean valid = false;

protected short _start_flag_02 = (short)0;
protected short _state_02 = (short)0;
protected short _cali_cnt_02 = (short)0;
protected short _temp_ready_02 = (short)0;
protected short _step_02 = (short)0;
protected short _cali_type_02 = (short)0;
protected int _tick_02 = (int)0;
protected short _grav_acc_x_02 = (short)0;
protected short _grav_acc_y_02 = (short)0;
protected short _grav_acc_z_02 = (short)0;
protected short _dst_cali_temp_02 = (short)0;
protected float _temp_min_02 = (float)0;
protected float _temp_max_02 = (float)0;
protected short _temp_cali_status_02 = (short)0;
protected short _base_cali_status_02 = (short)0;
protected short _cfg_temp_cali_fw_version_02 = (short)0;
protected short _cur_temp_cali_fw_version_02 = (short)0;
protected float _temp_bw_x_02 = (float)0;
protected float _temp_bw_y_02 = (float)0;
protected float _temp_bw_z_02 = (float)0;
protected float _temp_ba_x_02 = (float)0;
protected float _temp_ba_y_02 = (float)0;
protected float _temp_ba_z_02 = (float)0;
protected float _temp_temp_02 = (float)0;
protected float _base_bw_x_02 = (float)0;
protected float _base_bw_y_02 = (float)0;
protected float _base_bw_z_02 = (float)0;
protected float _base_ba_x_02 = (float)0;
protected float _base_ba_y_02 = (float)0;
protected float _base_ba_z_02 = (float)0;
protected float _base_temp_02 = (float)0;

      public temp_cali_data_02_2197(ConvertDat convertDat) {
           super(convertDat, 2197, 80);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

_start_flag_02 = _payload.getUnsignedByte(0);
 _state_02 = _payload.getByte(1);;
_cali_cnt_02 = _payload.getUnsignedByte(2);
_temp_ready_02 = _payload.getUnsignedByte(3);
_step_02 = _payload.getUnsignedByte(4);
_cali_type_02 = _payload.getUnsignedByte(5);
 _tick_02 = _payload.getUnsignedShort(6);
 _grav_acc_x_02 = _payload.getByte(8);;
 _grav_acc_y_02 = _payload.getByte(9);;
 _grav_acc_z_02 = _payload.getByte(10);;
 _dst_cali_temp_02 = _payload.getByte(11);;
 _temp_min_02 = _payload.getFloat(12);
 _temp_max_02 = _payload.getFloat(16);
_temp_cali_status_02 = _payload.getUnsignedByte(20);
_base_cali_status_02 = _payload.getUnsignedByte(21);
_cfg_temp_cali_fw_version_02 = _payload.getUnsignedByte(22);
_cur_temp_cali_fw_version_02 = _payload.getUnsignedByte(23);
 _temp_bw_x_02 = _payload.getFloat(24);
 _temp_bw_y_02 = _payload.getFloat(28);
 _temp_bw_z_02 = _payload.getFloat(32);
 _temp_ba_x_02 = _payload.getFloat(36);
 _temp_ba_y_02 = _payload.getFloat(40);
 _temp_ba_z_02 = _payload.getFloat(44);
 _temp_temp_02 = _payload.getFloat(48);
 _base_bw_x_02 = _payload.getFloat(52);
 _base_bw_y_02 = _payload.getFloat(56);
 _base_bw_z_02 = _payload.getFloat(60);
 _base_ba_x_02 = _payload.getFloat(64);
 _base_ba_y_02 = _payload.getFloat(68);
 _base_ba_z_02 = _payload.getFloat(72);
 _base_temp_02 = _payload.getFloat(76);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_cali_data_02IntSig = Signal
.SeriesInt("temp_cali_data_02", "", null, Units.noUnits);
    protected static Signal temp_cali_data_02FloatSig = Signal
.SeriesFloat("temp_cali_data_02", "", null, Units.noUnits);
    protected static Signal temp_cali_data_02DoubleSig = Signal
.SeriesDouble("temp_cali_data_02", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(_start_flag_02, temp_cali_data_02IntSig, "_start_flag_02",lineT, valid);
 printCsvValue(_state_02, temp_cali_data_02IntSig, "_state_02",lineT, valid);
 printCsvValue(_cali_cnt_02, temp_cali_data_02IntSig, "_cali_cnt_02",lineT, valid);
 printCsvValue(_temp_ready_02, temp_cali_data_02IntSig, "_temp_ready_02",lineT, valid);
 printCsvValue(_step_02, temp_cali_data_02IntSig, "_step_02",lineT, valid);
 printCsvValue(_cali_type_02, temp_cali_data_02IntSig, "_cali_type_02",lineT, valid);
 printCsvValue(_tick_02, temp_cali_data_02IntSig, "_tick_02",lineT, valid);
 printCsvValue(_grav_acc_x_02, temp_cali_data_02IntSig, "_grav_acc_x_02",lineT, valid);
 printCsvValue(_grav_acc_y_02, temp_cali_data_02IntSig, "_grav_acc_y_02",lineT, valid);
 printCsvValue(_grav_acc_z_02, temp_cali_data_02IntSig, "_grav_acc_z_02",lineT, valid);
 printCsvValue(_dst_cali_temp_02, temp_cali_data_02IntSig, "_dst_cali_temp_02",lineT, valid);
 printCsvValue(_temp_min_02, temp_cali_data_02FloatSig, "_temp_min_02",lineT, valid);
 printCsvValue(_temp_max_02, temp_cali_data_02FloatSig, "_temp_max_02",lineT, valid);
 printCsvValue(_temp_cali_status_02, temp_cali_data_02IntSig, "_temp_cali_status_02",lineT, valid);
 printCsvValue(_base_cali_status_02, temp_cali_data_02IntSig, "_base_cali_status_02",lineT, valid);
 printCsvValue(_cfg_temp_cali_fw_version_02, temp_cali_data_02IntSig, "_cfg_temp_cali_fw_version_02",lineT, valid);
 printCsvValue(_cur_temp_cali_fw_version_02, temp_cali_data_02IntSig, "_cur_temp_cali_fw_version_02",lineT, valid);
 printCsvValue(_temp_bw_x_02, temp_cali_data_02FloatSig, "_temp_bw_x_02",lineT, valid);
 printCsvValue(_temp_bw_y_02, temp_cali_data_02FloatSig, "_temp_bw_y_02",lineT, valid);
 printCsvValue(_temp_bw_z_02, temp_cali_data_02FloatSig, "_temp_bw_z_02",lineT, valid);
 printCsvValue(_temp_ba_x_02, temp_cali_data_02FloatSig, "_temp_ba_x_02",lineT, valid);
 printCsvValue(_temp_ba_y_02, temp_cali_data_02FloatSig, "_temp_ba_y_02",lineT, valid);
 printCsvValue(_temp_ba_z_02, temp_cali_data_02FloatSig, "_temp_ba_z_02",lineT, valid);
 printCsvValue(_temp_temp_02, temp_cali_data_02FloatSig, "_temp_temp_02",lineT, valid);
 printCsvValue(_base_bw_x_02, temp_cali_data_02FloatSig, "_base_bw_x_02",lineT, valid);
 printCsvValue(_base_bw_y_02, temp_cali_data_02FloatSig, "_base_bw_y_02",lineT, valid);
 printCsvValue(_base_bw_z_02, temp_cali_data_02FloatSig, "_base_bw_z_02",lineT, valid);
 printCsvValue(_base_ba_x_02, temp_cali_data_02FloatSig, "_base_ba_x_02",lineT, valid);
 printCsvValue(_base_ba_y_02, temp_cali_data_02FloatSig, "_base_ba_y_02",lineT, valid);
 printCsvValue(_base_ba_z_02, temp_cali_data_02FloatSig, "_base_ba_z_02",lineT, valid);
 printCsvValue(_base_temp_02, temp_cali_data_02FloatSig, "_base_temp_02",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
