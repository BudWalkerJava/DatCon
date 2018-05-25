package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class IMU_EX_00_2064 extends Record {
    protected boolean valid = false;

    protected float vo_vx_00 = (float) 0;

    protected float vo_vy_00 = (float) 0;

    protected float vo_vz_00 = (float) 0;

    protected float vo_px_00 = (float) 0;

    protected float vo_py_00 = (float) 0;

    protected float vo_pz_00 = (float) 0;

    protected float us_v_00 = (float) 0;

    protected float us_p_00 = (float) 0;

    protected int vo_flag_navi_00 = (int) 0;

    protected int imu_err_flag_00 = (int) 0;

    protected int vo_flag_rsv_00 = (int) 0;

    protected int imu_ex_cnt_00 = (int) 0;

    public IMU_EX_00_2064(ConvertDat convertDat) {
        super(convertDat, 2064, 40);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            vo_vx_00 = _payload.getFloat(0);
            vo_vy_00 = _payload.getFloat(4);
            vo_vz_00 = _payload.getFloat(8);
            vo_px_00 = _payload.getFloat(12);
            vo_py_00 = _payload.getFloat(16);
            vo_pz_00 = _payload.getFloat(20);
            us_v_00 = _payload.getFloat(24);
            us_p_00 = _payload.getFloat(28);
            vo_flag_navi_00 = _payload.getUnsignedShort(32);
            imu_err_flag_00 = _payload.getUnsignedShort(34);
            vo_flag_rsv_00 = _payload.getUnsignedShort(36);
            imu_ex_cnt_00 = _payload.getUnsignedShort(38);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal IMU_EX_00IntSig = Signal.SeriesInt("IMU_EX_00", "",
            null, Units.noUnits);

    protected static Signal IMU_EX_00FloatSig = Signal.SeriesFloat("IMU_EX_00",
            "", null, Units.noUnits);

    protected static Signal IMU_EX_00DoubleSig = Signal
            .SeriesDouble("IMU_EX_00", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(vo_vx_00, IMU_EX_00FloatSig, "vo_vx_00", lineT,
                    valid);
            printCsvValue(vo_vy_00, IMU_EX_00FloatSig, "vo_vy_00", lineT,
                    valid);
            printCsvValue(vo_vz_00, IMU_EX_00FloatSig, "vo_vz_00", lineT,
                    valid);
            printCsvValue(vo_px_00, IMU_EX_00FloatSig, "vo_px_00", lineT,
                    valid);
            printCsvValue(vo_py_00, IMU_EX_00FloatSig, "vo_py_00", lineT,
                    valid);
            printCsvValue(vo_pz_00, IMU_EX_00FloatSig, "vo_pz_00", lineT,
                    valid);
            printCsvValue(us_v_00, IMU_EX_00FloatSig, "us_v_00", lineT, valid);
            printCsvValue(us_p_00, IMU_EX_00FloatSig, "us_p_00", lineT, valid);
            printCsvValue(vo_flag_navi_00, IMU_EX_00IntSig, "vo_flag_navi_00",
                    lineT, valid);
            printCsvValue(imu_err_flag_00, IMU_EX_00IntSig, "imu_err_flag_00",
                    lineT, valid);
            printCsvValue(vo_flag_rsv_00, IMU_EX_00IntSig, "vo_flag_rsv_00",
                    lineT, valid);
            printCsvValue(imu_ex_cnt_00, IMU_EX_00IntSig, "imu_ex_cnt_00",
                    lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
