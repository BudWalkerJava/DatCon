package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_cali_data_22 extends Record {
protected boolean valid = false;

protected short _start_flag = (short)0;
protected short _state = (short)0;
protected short _cali_cnt = (short)0;
protected short _temp_ready = (short)0;
protected short _step = (short)0;
protected short _cali_type = (short)0;
protected int _tick = (int)0;
protected short _grav_acc_x = (short)0;
protected short _grav_acc_y = (short)0;
protected short _grav_acc_z = (short)0;
protected short _dst_cali_temp = (short)0;
protected float _dst_cali_temp_1 = (float)0;
protected float _temp_min = (float)0;
protected float _temp_max = (float)0;
protected short _temp_cali_status = (short)0;
protected short _base_cali_status = (short)0;
protected short _cfg_temp_cali_fw_version = (short)0;
protected short _cur_temp_cali_fw_version = (short)0;
protected float _temp_bw_x = (float)0;
protected float _temp_bw_y = (float)0;
protected float _temp_bw_z = (float)0;
protected float _temp_ba_x = (float)0;
protected float _temp_ba_y = (float)0;
protected float _temp_ba_z = (float)0;
protected float _temp_temp = (float)0;
protected float _base_bw_x = (float)0;
protected float _base_bw_y = (float)0;
protected float _base_bw_z = (float)0;
protected float _base_ba_x = (float)0;
protected float _base_ba_y = (float)0;
protected float _base_ba_z = (float)0;
protected float _base_temp = (float)0;

      public temp_cali_data_22(ConvertDat convertDat) {
           super(convertDat, 22, 84);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

_start_flag = _payload.getUnsignedByte(0);
 _state = _payload.getByte(1);;
_cali_cnt = _payload.getUnsignedByte(2);
_temp_ready = _payload.getUnsignedByte(3);
_step = _payload.getUnsignedByte(4);
_cali_type = _payload.getUnsignedByte(5);
 _tick = _payload.getUnsignedShort(6);
 _grav_acc_x = _payload.getByte(8);;
 _grav_acc_y = _payload.getByte(9);;
 _grav_acc_z = _payload.getByte(10);;
 _dst_cali_temp = _payload.getByte(11);;
 _dst_cali_temp_1 = _payload.getFloat(12);
 _temp_min = _payload.getFloat(16);
 _temp_max = _payload.getFloat(20);
_temp_cali_status = _payload.getUnsignedByte(24);
_base_cali_status = _payload.getUnsignedByte(25);
_cfg_temp_cali_fw_version = _payload.getUnsignedByte(26);
_cur_temp_cali_fw_version = _payload.getUnsignedByte(27);
 _temp_bw_x = _payload.getFloat(28);
 _temp_bw_y = _payload.getFloat(32);
 _temp_bw_z = _payload.getFloat(36);
 _temp_ba_x = _payload.getFloat(40);
 _temp_ba_y = _payload.getFloat(44);
 _temp_ba_z = _payload.getFloat(48);
 _temp_temp = _payload.getFloat(52);
 _base_bw_x = _payload.getFloat(56);
 _base_bw_y = _payload.getFloat(60);
 _base_bw_z = _payload.getFloat(64);
 _base_ba_x = _payload.getFloat(68);
 _base_ba_y = _payload.getFloat(72);
 _base_ba_z = _payload.getFloat(76);
 _base_temp = _payload.getFloat(80);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_cali_dataIntSig = Signal
.SeriesInt("temp_cali_data", "", null, Units.noUnits);
    protected static Signal temp_cali_dataFloatSig = Signal
.SeriesFloat("temp_cali_data", "", null, Units.noUnits);
    protected static Signal temp_cali_dataDoubleSig = Signal
.SeriesDouble("temp_cali_data", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(_start_flag, temp_cali_dataIntSig, "_start_flag",lineT, valid);
 printCsvValue(_state, temp_cali_dataIntSig, "_state",lineT, valid);
 printCsvValue(_cali_cnt, temp_cali_dataIntSig, "_cali_cnt",lineT, valid);
 printCsvValue(_temp_ready, temp_cali_dataIntSig, "_temp_ready",lineT, valid);
 printCsvValue(_step, temp_cali_dataIntSig, "_step",lineT, valid);
 printCsvValue(_cali_type, temp_cali_dataIntSig, "_cali_type",lineT, valid);
 printCsvValue(_tick, temp_cali_dataIntSig, "_tick",lineT, valid);
 printCsvValue(_grav_acc_x, temp_cali_dataIntSig, "_grav_acc_x",lineT, valid);
 printCsvValue(_grav_acc_y, temp_cali_dataIntSig, "_grav_acc_y",lineT, valid);
 printCsvValue(_grav_acc_z, temp_cali_dataIntSig, "_grav_acc_z",lineT, valid);
 printCsvValue(_dst_cali_temp, temp_cali_dataIntSig, "_dst_cali_temp",lineT, valid);
 printCsvValue(_dst_cali_temp_1, temp_cali_dataFloatSig, "_dst_cali_temp_1",lineT, valid);
 printCsvValue(_temp_min, temp_cali_dataFloatSig, "_temp_min",lineT, valid);
 printCsvValue(_temp_max, temp_cali_dataFloatSig, "_temp_max",lineT, valid);
 printCsvValue(_temp_cali_status, temp_cali_dataIntSig, "_temp_cali_status",lineT, valid);
 printCsvValue(_base_cali_status, temp_cali_dataIntSig, "_base_cali_status",lineT, valid);
 printCsvValue(_cfg_temp_cali_fw_version, temp_cali_dataIntSig, "_cfg_temp_cali_fw_version",lineT, valid);
 printCsvValue(_cur_temp_cali_fw_version, temp_cali_dataIntSig, "_cur_temp_cali_fw_version",lineT, valid);
 printCsvValue(_temp_bw_x, temp_cali_dataFloatSig, "_temp_bw_x",lineT, valid);
 printCsvValue(_temp_bw_y, temp_cali_dataFloatSig, "_temp_bw_y",lineT, valid);
 printCsvValue(_temp_bw_z, temp_cali_dataFloatSig, "_temp_bw_z",lineT, valid);
 printCsvValue(_temp_ba_x, temp_cali_dataFloatSig, "_temp_ba_x",lineT, valid);
 printCsvValue(_temp_ba_y, temp_cali_dataFloatSig, "_temp_ba_y",lineT, valid);
 printCsvValue(_temp_ba_z, temp_cali_dataFloatSig, "_temp_ba_z",lineT, valid);
 printCsvValue(_temp_temp, temp_cali_dataFloatSig, "_temp_temp",lineT, valid);
 printCsvValue(_base_bw_x, temp_cali_dataFloatSig, "_base_bw_x",lineT, valid);
 printCsvValue(_base_bw_y, temp_cali_dataFloatSig, "_base_bw_y",lineT, valid);
 printCsvValue(_base_bw_z, temp_cali_dataFloatSig, "_base_bw_z",lineT, valid);
 printCsvValue(_base_ba_x, temp_cali_dataFloatSig, "_base_ba_x",lineT, valid);
 printCsvValue(_base_ba_y, temp_cali_dataFloatSig, "_base_ba_y",lineT, valid);
 printCsvValue(_base_ba_z, temp_cali_dataFloatSig, "_base_ba_z",lineT, valid);
 printCsvValue(_base_temp, temp_cali_dataFloatSig, "_base_temp",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
