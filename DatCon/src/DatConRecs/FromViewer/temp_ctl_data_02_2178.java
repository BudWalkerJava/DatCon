package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_ctl_data_02_2178 extends Record {
protected boolean valid = false;

protected short dst_value_02 = (short)0;
protected short cur_dst_temp_02 = (short)0;
protected int p_02 = (int)0;
protected int i_02 = (int)0;
protected int d_02 = (int)0;
protected short dl_err_02 = (short)0;
protected short real_ctl_out_per_02 = (short)0;
protected short slope_type_02 = (short)0;
protected short temp_ctl_slope_02 = (short)0;
protected short t_finish_02 = (short)0;
protected short err_last_02 = (short)0;
protected long ctl_out_value_02 = (long)0;
protected long real_ctl_out_value_02 = (long)0;
protected int i_small_02 = (int)0;
protected int out_02 = (int)0;
protected long cnt_02 = (long)0;

      public temp_ctl_data_02_2178(ConvertDat convertDat) {
           super(convertDat, 2178, 44);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 dst_value_02 = _payload.getShort(0);
 cur_dst_temp_02 = _payload.getShort(2);
 p_02 = _payload.getInt(4);
 i_02 = _payload.getInt(8);
 d_02 = _payload.getInt(12);
 dl_err_02 = _payload.getShort(16);
real_ctl_out_per_02 = _payload.getUnsignedByte(18);
slope_type_02 = _payload.getUnsignedByte(19);
temp_ctl_slope_02 = _payload.getUnsignedByte(20);
t_finish_02 = _payload.getUnsignedByte(21);
 err_last_02 = _payload.getShort(22);
 ctl_out_value_02 = _payload.getUnsignedInt(24);
 real_ctl_out_value_02 = _payload.getUnsignedInt(28);
 i_small_02 = _payload.getInt(32);
 out_02 = _payload.getInt(36);
 cnt_02 = _payload.getUnsignedInt(40);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_ctl_data_02IntSig = Signal
.SeriesInt("temp_ctl_data_02", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_02FloatSig = Signal
.SeriesFloat("temp_ctl_data_02", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_02DoubleSig = Signal
.SeriesDouble("temp_ctl_data_02", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(dst_value_02, temp_ctl_data_02IntSig, "dst_value_02",lineT, valid);
 printCsvValue(cur_dst_temp_02, temp_ctl_data_02IntSig, "cur_dst_temp_02",lineT, valid);
 printCsvValue(p_02, temp_ctl_data_02IntSig, "p_02",lineT, valid);
 printCsvValue(i_02, temp_ctl_data_02IntSig, "i_02",lineT, valid);
 printCsvValue(d_02, temp_ctl_data_02IntSig, "d_02",lineT, valid);
 printCsvValue(dl_err_02, temp_ctl_data_02IntSig, "dl_err_02",lineT, valid);
 printCsvValue(real_ctl_out_per_02, temp_ctl_data_02IntSig, "real_ctl_out_per_02",lineT, valid);
 printCsvValue(slope_type_02, temp_ctl_data_02IntSig, "slope_type_02",lineT, valid);
 printCsvValue(temp_ctl_slope_02, temp_ctl_data_02IntSig, "temp_ctl_slope_02",lineT, valid);
 printCsvValue(t_finish_02, temp_ctl_data_02IntSig, "t_finish_02",lineT, valid);
 printCsvValue(err_last_02, temp_ctl_data_02IntSig, "err_last_02",lineT, valid);
 printCsvValue(ctl_out_value_02, temp_ctl_data_02IntSig, "ctl_out_value_02",lineT, valid);
 printCsvValue(real_ctl_out_value_02, temp_ctl_data_02IntSig, "real_ctl_out_value_02",lineT, valid);
 printCsvValue(i_small_02, temp_ctl_data_02IntSig, "i_small_02",lineT, valid);
 printCsvValue(out_02, temp_ctl_data_02IntSig, "out_02",lineT, valid);
 printCsvValue(cnt_02, temp_ctl_data_02IntSig, "cnt_02",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
