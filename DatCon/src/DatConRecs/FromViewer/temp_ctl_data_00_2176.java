package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_ctl_data_00_2176 extends Record {
protected boolean valid = false;

protected short dst_value_00 = (short)0;
protected short cur_dst_temp_00 = (short)0;
protected int p_00 = (int)0;
protected int i_00 = (int)0;
protected int d_00 = (int)0;
protected short dl_err_00 = (short)0;
protected short real_ctl_out_per_00 = (short)0;
protected short slope_type_00 = (short)0;
protected short temp_ctl_slope_00 = (short)0;
protected short t_finish_00 = (short)0;
protected short err_last_00 = (short)0;
protected long ctl_out_value_00 = (long)0;
protected long real_ctl_out_value_00 = (long)0;
protected int i_small_00 = (int)0;
protected int out_00 = (int)0;
protected long cnt_00 = (long)0;

      public temp_ctl_data_00_2176(ConvertDat convertDat) {
           super(convertDat, 2176, 44);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 dst_value_00 = _payload.getShort(0);
 cur_dst_temp_00 = _payload.getShort(2);
 p_00 = _payload.getInt(4);
 i_00 = _payload.getInt(8);
 d_00 = _payload.getInt(12);
 dl_err_00 = _payload.getShort(16);
real_ctl_out_per_00 = _payload.getUnsignedByte(18);
slope_type_00 = _payload.getUnsignedByte(19);
temp_ctl_slope_00 = _payload.getUnsignedByte(20);
t_finish_00 = _payload.getUnsignedByte(21);
 err_last_00 = _payload.getShort(22);
 ctl_out_value_00 = _payload.getUnsignedInt(24);
 real_ctl_out_value_00 = _payload.getUnsignedInt(28);
 i_small_00 = _payload.getInt(32);
 out_00 = _payload.getInt(36);
 cnt_00 = _payload.getUnsignedInt(40);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_ctl_data_00IntSig = Signal
.SeriesInt("temp_ctl_data_00", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_00FloatSig = Signal
.SeriesFloat("temp_ctl_data_00", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_00DoubleSig = Signal
.SeriesDouble("temp_ctl_data_00", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(dst_value_00, temp_ctl_data_00IntSig, "dst_value_00",lineT, valid);
 printCsvValue(cur_dst_temp_00, temp_ctl_data_00IntSig, "cur_dst_temp_00",lineT, valid);
 printCsvValue(p_00, temp_ctl_data_00IntSig, "p_00",lineT, valid);
 printCsvValue(i_00, temp_ctl_data_00IntSig, "i_00",lineT, valid);
 printCsvValue(d_00, temp_ctl_data_00IntSig, "d_00",lineT, valid);
 printCsvValue(dl_err_00, temp_ctl_data_00IntSig, "dl_err_00",lineT, valid);
 printCsvValue(real_ctl_out_per_00, temp_ctl_data_00IntSig, "real_ctl_out_per_00",lineT, valid);
 printCsvValue(slope_type_00, temp_ctl_data_00IntSig, "slope_type_00",lineT, valid);
 printCsvValue(temp_ctl_slope_00, temp_ctl_data_00IntSig, "temp_ctl_slope_00",lineT, valid);
 printCsvValue(t_finish_00, temp_ctl_data_00IntSig, "t_finish_00",lineT, valid);
 printCsvValue(err_last_00, temp_ctl_data_00IntSig, "err_last_00",lineT, valid);
 printCsvValue(ctl_out_value_00, temp_ctl_data_00IntSig, "ctl_out_value_00",lineT, valid);
 printCsvValue(real_ctl_out_value_00, temp_ctl_data_00IntSig, "real_ctl_out_value_00",lineT, valid);
 printCsvValue(i_small_00, temp_ctl_data_00IntSig, "i_small_00",lineT, valid);
 printCsvValue(out_00, temp_ctl_data_00IntSig, "out_00",lineT, valid);
 printCsvValue(cnt_00, temp_ctl_data_00IntSig, "cnt_00",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
