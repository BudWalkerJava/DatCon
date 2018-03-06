package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class imu_raw28_92 extends Record {
    protected boolean valid = false;

    protected short gyro_x_used = (short) 0;

    protected short gyro_y_used = (short) 0;

    protected short gyro_z_used = (short) 0;

    protected short acc_x_used = (short) 0;

    protected short acc_y_used = (short) 0;

    protected short acc_z_used = (short) 0;

    protected short gyro_x_unused = (short) 0;

    protected short gyro_y_unused = (short) 0;

    protected short gyro_z_unused = (short) 0;

    protected short acc_x_unused = (short) 0;

    protected short acc_y_unused = (short) 0;

    protected short acc_z_unused = (short) 0;

    protected short xxxxcnt = (short) 0;

    protected short xxbaro = (short) 0;

    //protected short xxbaro_temp = (short) 0;

    public imu_raw28_92(ConvertDat convertDat) {
        super(convertDat, 92, 28);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            gyro_x_used = _payload.getShort(0);
            gyro_y_used = _payload.getShort(2);
            gyro_z_used = _payload.getShort(4);
            acc_x_used = _payload.getShort(6);
            acc_y_used = _payload.getShort(8);
            acc_z_used = _payload.getShort(10);
            gyro_x_unused = _payload.getShort(12);
            gyro_y_unused = _payload.getShort(14);
            gyro_z_unused = _payload.getShort(16);
            acc_x_unused = _payload.getShort(18);
            acc_y_unused = _payload.getShort(20);
            acc_z_unused = _payload.getShort(22);
            xxxxcnt = _payload.getShort(24);
            xxbaro = _payload.getShort(26);
            //xxbaro_temp = _payload.getShort(28);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal imu_rawIntSig = Signal.SeriesInt("imu_raw", "",
            null, Units.noUnits);

    protected static Signal imu_rawFloatSig = Signal.SeriesFloat("imu_raw", "",
            null, Units.noUnits);

    protected static Signal imu_rawDoubleSig = Signal.SeriesDouble("imu_raw",
            "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(gyro_x_used, imu_rawIntSig, "gyro_x_used", lineT,
                    valid);
            printCsvValue(gyro_y_used, imu_rawIntSig, "gyro_y_used", lineT,
                    valid);
            printCsvValue(gyro_z_used, imu_rawIntSig, "gyro_z_used", lineT,
                    valid);
            printCsvValue(acc_x_used, imu_rawIntSig, "acc_x_used", lineT,
                    valid);
            printCsvValue(acc_y_used, imu_rawIntSig, "acc_y_used", lineT,
                    valid);
            printCsvValue(acc_z_used, imu_rawIntSig, "acc_z_used", lineT,
                    valid);
            printCsvValue(gyro_x_unused, imu_rawIntSig, "gyro_x_unused", lineT,
                    valid);
            printCsvValue(gyro_y_unused, imu_rawIntSig, "gyro_y_unused", lineT,
                    valid);
            printCsvValue(gyro_z_unused, imu_rawIntSig, "gyro_z_unused", lineT,
                    valid);
            printCsvValue(acc_x_unused, imu_rawIntSig, "acc_x_unused", lineT,
                    valid);
            printCsvValue(acc_y_unused, imu_rawIntSig, "acc_y_unused", lineT,
                    valid);
            printCsvValue(acc_z_unused, imu_rawIntSig, "acc_z_unused", lineT,
                    valid);
            printCsvValue(xxxxcnt, imu_rawIntSig, "xxxxcnt", lineT, valid);
            printCsvValue(xxbaro, imu_rawIntSig, "xxbaro", lineT, valid);
            //printCsvValue(xxbaro_temp, imu_rawIntSig, "xxbaro_temp", lineT,
            //         valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
