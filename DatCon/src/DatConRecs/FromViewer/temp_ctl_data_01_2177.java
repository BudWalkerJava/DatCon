package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class temp_ctl_data_01_2177 extends Record {
protected boolean valid = false;

protected short dst_value_01 = (short)0;
protected short cur_dst_temp_01 = (short)0;
protected int p_01 = (int)0;
protected int i_01 = (int)0;
protected int d_01 = (int)0;
protected short dl_err_01 = (short)0;
protected short real_ctl_out_per_01 = (short)0;
protected short slope_type_01 = (short)0;
protected short temp_ctl_slope_01 = (short)0;
protected short t_finish_01 = (short)0;
protected short err_last_01 = (short)0;
protected long ctl_out_value_01 = (long)0;
protected long real_ctl_out_value_01 = (long)0;
protected int i_small_01 = (int)0;
protected int out_01 = (int)0;
protected long cnt_01 = (long)0;

      public temp_ctl_data_01_2177(ConvertDat convertDat) {
           super(convertDat, 2177, 44);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 dst_value_01 = _payload.getShort(0);
 cur_dst_temp_01 = _payload.getShort(2);
 p_01 = _payload.getInt(4);
 i_01 = _payload.getInt(8);
 d_01 = _payload.getInt(12);
 dl_err_01 = _payload.getShort(16);
real_ctl_out_per_01 = _payload.getUnsignedByte(18);
slope_type_01 = _payload.getUnsignedByte(19);
temp_ctl_slope_01 = _payload.getUnsignedByte(20);
t_finish_01 = _payload.getUnsignedByte(21);
 err_last_01 = _payload.getShort(22);
 ctl_out_value_01 = _payload.getUnsignedInt(24);
 real_ctl_out_value_01 = _payload.getUnsignedInt(28);
 i_small_01 = _payload.getInt(32);
 out_01 = _payload.getInt(36);
 cnt_01 = _payload.getUnsignedInt(40);
} catch (Exception e) {RecordException(e);}}


    protected static Signal temp_ctl_data_01IntSig = Signal
.SeriesInt("temp_ctl_data_01", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_01FloatSig = Signal
.SeriesFloat("temp_ctl_data_01", "", null, Units.noUnits);
    protected static Signal temp_ctl_data_01DoubleSig = Signal
.SeriesDouble("temp_ctl_data_01", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(dst_value_01, temp_ctl_data_01IntSig, "dst_value_01",lineT, valid);
 printCsvValue(cur_dst_temp_01, temp_ctl_data_01IntSig, "cur_dst_temp_01",lineT, valid);
 printCsvValue(p_01, temp_ctl_data_01IntSig, "p_01",lineT, valid);
 printCsvValue(i_01, temp_ctl_data_01IntSig, "i_01",lineT, valid);
 printCsvValue(d_01, temp_ctl_data_01IntSig, "d_01",lineT, valid);
 printCsvValue(dl_err_01, temp_ctl_data_01IntSig, "dl_err_01",lineT, valid);
 printCsvValue(real_ctl_out_per_01, temp_ctl_data_01IntSig, "real_ctl_out_per_01",lineT, valid);
 printCsvValue(slope_type_01, temp_ctl_data_01IntSig, "slope_type_01",lineT, valid);
 printCsvValue(temp_ctl_slope_01, temp_ctl_data_01IntSig, "temp_ctl_slope_01",lineT, valid);
 printCsvValue(t_finish_01, temp_ctl_data_01IntSig, "t_finish_01",lineT, valid);
 printCsvValue(err_last_01, temp_ctl_data_01IntSig, "err_last_01",lineT, valid);
 printCsvValue(ctl_out_value_01, temp_ctl_data_01IntSig, "ctl_out_value_01",lineT, valid);
 printCsvValue(real_ctl_out_value_01, temp_ctl_data_01IntSig, "real_ctl_out_value_01",lineT, valid);
 printCsvValue(i_small_01, temp_ctl_data_01IntSig, "i_small_01",lineT, valid);
 printCsvValue(out_01, temp_ctl_data_01IntSig, "out_01",lineT, valid);
 printCsvValue(cnt_01, temp_ctl_data_01IntSig, "cnt_01",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
