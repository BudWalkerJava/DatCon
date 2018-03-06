package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class IMU_ATTI_00_2048 extends Record {
    protected boolean valid = false;

    protected double longti_00 = (double) 0;

    protected double lati_00 = (double) 0;

    protected float alti_00 = (float) 0;

    protected float acc_x_00 = (float) 0;

    protected float acc_y_00 = (float) 0;

    protected float acc_z_00 = (float) 0;

    protected float gyro_x_00 = (float) 0;

    protected float gyro_y_00 = (float) 0;

    protected float gyro_z_00 = (float) 0;

    protected float press_00 = (float) 0;

    protected float q0_00 = (float) 0;

    protected float q1_00 = (float) 0;

    protected float q2_00 = (float) 0;

    protected float q3_00 = (float) 0;

    protected float ag_x_00 = (float) 0;

    protected float ag_y_00 = (float) 0;

    protected float ag_z_00 = (float) 0;

    protected float vg_x_00 = (float) 0;

    protected float vg_y_00 = (float) 0;

    protected float vg_z_00 = (float) 0;

    protected float gb_x_00 = (float) 0;

    protected float gb_y_00 = (float) 0;

    protected float gb_z_00 = (float) 0;

    protected short m_x_00 = (short) 0;

    protected short m_y_00 = (short) 0;

    protected short m_z_00 = (short) 0;

    protected short temp_x_00 = (short) 0;

    protected short temp_y_00 = (short) 0;

    protected short temp_z_00 = (short) 0;

    protected int sensor_monitor_00 = (int) 0;

    protected int filter_status_00 = (int) 0;

    protected int svn_00 = (int) 0;

    protected int cnt_atti_00 = (int) 0;

    public IMU_ATTI_00_2048(ConvertDat convertDat) {
        super(convertDat, 2048, 120);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            longti_00 = _payload.getDouble(0);
            lati_00 = _payload.getDouble(8);
            alti_00 = _payload.getFloat(16);
            acc_x_00 = _payload.getFloat(20);
            acc_y_00 = _payload.getFloat(24);
            acc_z_00 = _payload.getFloat(28);
            gyro_x_00 = _payload.getFloat(32);
            gyro_y_00 = _payload.getFloat(36);
            gyro_z_00 = _payload.getFloat(40);
            press_00 = _payload.getFloat(44);
            q0_00 = _payload.getFloat(48);
            q1_00 = _payload.getFloat(52);
            q2_00 = _payload.getFloat(56);
            q3_00 = _payload.getFloat(60);
            ag_x_00 = _payload.getFloat(64);
            ag_y_00 = _payload.getFloat(68);
            ag_z_00 = _payload.getFloat(72);
            vg_x_00 = _payload.getFloat(76);
            vg_y_00 = _payload.getFloat(80);
            vg_z_00 = _payload.getFloat(84);
            gb_x_00 = _payload.getFloat(88);
            gb_y_00 = _payload.getFloat(92);
            gb_z_00 = _payload.getFloat(96);
            m_x_00 = _payload.getShort(100);
            m_y_00 = _payload.getShort(102);
            m_z_00 = _payload.getShort(104);
            temp_x_00 = _payload.getShort(106);
            temp_y_00 = _payload.getShort(108);
            temp_z_00 = _payload.getShort(110);
            sensor_monitor_00 = _payload.getUnsignedShort(112);
            filter_status_00 = _payload.getUnsignedShort(114);
            svn_00 = _payload.getUnsignedShort(116);
            cnt_atti_00 = _payload.getUnsignedShort(118);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal IMU_ATTI_00IntSig = Signal.SeriesInt("IMU_ATTI_00",
            "", null, Units.noUnits);

    protected static Signal IMU_ATTI_00FloatSig = Signal
            .SeriesFloat("IMU_ATTI_00", "", null, Units.noUnits);

    protected static Signal IMU_ATTI_00DoubleSig = Signal
            .SeriesDouble("IMU_ATTI_00", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(longti_00, IMU_ATTI_00DoubleSig, "longti_00", lineT,
                    valid);
            printCsvValue(lati_00, IMU_ATTI_00DoubleSig, "lati_00", lineT,
                    valid);
            printCsvValue(alti_00, IMU_ATTI_00FloatSig, "alti_00", lineT,
                    valid);
            printCsvValue(acc_x_00, IMU_ATTI_00FloatSig, "acc_x_00", lineT,
                    valid);
            printCsvValue(acc_y_00, IMU_ATTI_00FloatSig, "acc_y_00", lineT,
                    valid);
            printCsvValue(acc_z_00, IMU_ATTI_00FloatSig, "acc_z_00", lineT,
                    valid);
            printCsvValue(gyro_x_00, IMU_ATTI_00FloatSig, "gyro_x_00", lineT,
                    valid);
            printCsvValue(gyro_y_00, IMU_ATTI_00FloatSig, "gyro_y_00", lineT,
                    valid);
            printCsvValue(gyro_z_00, IMU_ATTI_00FloatSig, "gyro_z_00", lineT,
                    valid);
            printCsvValue(press_00, IMU_ATTI_00FloatSig, "press_00", lineT,
                    valid);
            printCsvValue(q0_00, IMU_ATTI_00FloatSig, "q0_00", lineT, valid);
            printCsvValue(q1_00, IMU_ATTI_00FloatSig, "q1_00", lineT, valid);
            printCsvValue(q2_00, IMU_ATTI_00FloatSig, "q2_00", lineT, valid);
            printCsvValue(q3_00, IMU_ATTI_00FloatSig, "q3_00", lineT, valid);
            printCsvValue(ag_x_00, IMU_ATTI_00FloatSig, "ag_x_00", lineT,
                    valid);
            printCsvValue(ag_y_00, IMU_ATTI_00FloatSig, "ag_y_00", lineT,
                    valid);
            printCsvValue(ag_z_00, IMU_ATTI_00FloatSig, "ag_z_00", lineT,
                    valid);
            printCsvValue(vg_x_00, IMU_ATTI_00FloatSig, "vg_x_00", lineT,
                    valid);
            printCsvValue(vg_y_00, IMU_ATTI_00FloatSig, "vg_y_00", lineT,
                    valid);
            printCsvValue(vg_z_00, IMU_ATTI_00FloatSig, "vg_z_00", lineT,
                    valid);
            printCsvValue(gb_x_00, IMU_ATTI_00FloatSig, "gb_x_00", lineT,
                    valid);
            printCsvValue(gb_y_00, IMU_ATTI_00FloatSig, "gb_y_00", lineT,
                    valid);
            printCsvValue(gb_z_00, IMU_ATTI_00FloatSig, "gb_z_00", lineT,
                    valid);
            printCsvValue(m_x_00, IMU_ATTI_00IntSig, "m_x_00", lineT, valid);
            printCsvValue(m_y_00, IMU_ATTI_00IntSig, "m_y_00", lineT, valid);
            printCsvValue(m_z_00, IMU_ATTI_00IntSig, "m_z_00", lineT, valid);
            printCsvValue(temp_x_00, IMU_ATTI_00IntSig, "temp_x_00", lineT,
                    valid);
            printCsvValue(temp_y_00, IMU_ATTI_00IntSig, "temp_y_00", lineT,
                    valid);
            printCsvValue(temp_z_00, IMU_ATTI_00IntSig, "temp_z_00", lineT,
                    valid);
            printCsvValue(sensor_monitor_00, IMU_ATTI_00IntSig,
                    "sensor_monitor_00", lineT, valid);
            printCsvValue(filter_status_00, IMU_ATTI_00IntSig,
                    "filter_status_00", lineT, valid);
            printCsvValue(svn_00, IMU_ATTI_00IntSig, "svn_00", lineT, valid);
            printCsvValue(cnt_atti_00, IMU_ATTI_00IntSig, "cnt_atti_00", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
