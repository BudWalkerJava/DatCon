package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class err_code_112 extends Record {
    protected boolean valid = false;

    protected short imu1_break = (short) 0;

    protected short imu2_break = (short) 0;

    protected short gcm0_break = (short) 0;

    protected short gcm1_break = (short) 0;

    protected short gcm2_break = (short) 0;

    protected short imu0_fault = (short) 0;

    protected short imu1_fault = (short) 0;

    protected short imu2_fault = (short) 0;

    protected short gcm0_fault = (short) 0;

    protected short gcm1_fault = (short) 0;

    protected short gcm2_fault = (short) 0;

    protected short mc_fault = (short) 0;

    protected short mc_dev = (short) 0;

    protected short mc_err = (short) 0;

    protected short mc_act = (short) 0;

    protected short imu_stat = (short) 0;

    protected short mag_stat = (short) 0;

    protected short imu0_dev = (short) 0;

    protected short imu0_err = (short) 0;

    protected short imu0_act = (short) 0;

    protected short imu1_dev = (short) 0;

    protected short imu1_err = (short) 0;

    protected short imu1_act = (short) 0;

    protected short imu2_dev = (short) 0;

    protected short imu2_err = (short) 0;

    protected short imu2_act = (short) 0;

    protected short gcm0_dev = (short) 0;

    protected short gcm0_err = (short) 0;

    protected short gcm0_act = (short) 0;

    protected short gcm1_dev = (short) 0;

    protected short gcm1_err = (short) 0;

    protected short gcm1_act = (short) 0;

    protected short gcm2_dev = (short) 0;

    protected short gcm2_err = (short) 0;

    protected short gcm2_act = (short) 0;

    public err_code_112(ConvertDat convertDat) {
        super(convertDat, 112, 35);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            imu1_break = _payload.getUnsignedByte(0);
            imu2_break = _payload.getUnsignedByte(1);
            gcm0_break = _payload.getUnsignedByte(2);
            gcm1_break = _payload.getUnsignedByte(3);
            gcm2_break = _payload.getUnsignedByte(4);
            imu0_fault = _payload.getUnsignedByte(5);
            imu1_fault = _payload.getUnsignedByte(6);
            imu2_fault = _payload.getUnsignedByte(7);
            gcm0_fault = _payload.getUnsignedByte(8);
            gcm1_fault = _payload.getUnsignedByte(9);
            gcm2_fault = _payload.getUnsignedByte(10);
            mc_fault = _payload.getUnsignedByte(11);
            mc_dev = _payload.getUnsignedByte(12);
            mc_err = _payload.getUnsignedByte(13);
            mc_act = _payload.getUnsignedByte(14);
            imu_stat = _payload.getUnsignedByte(15);
            mag_stat = _payload.getUnsignedByte(16);
            imu0_dev = _payload.getUnsignedByte(17);
            imu0_err = _payload.getUnsignedByte(18);
            imu0_act = _payload.getUnsignedByte(19);
            imu1_dev = _payload.getUnsignedByte(20);
            imu1_err = _payload.getUnsignedByte(21);
            imu1_act = _payload.getUnsignedByte(22);
            imu2_dev = _payload.getUnsignedByte(23);
            imu2_err = _payload.getUnsignedByte(24);
            imu2_act = _payload.getUnsignedByte(25);
            gcm0_dev = _payload.getUnsignedByte(26);
            gcm0_err = _payload.getUnsignedByte(27);
            gcm0_act = _payload.getUnsignedByte(28);
            gcm1_dev = _payload.getUnsignedByte(29);
            gcm1_err = _payload.getUnsignedByte(30);
            gcm1_act = _payload.getUnsignedByte(31);
            gcm2_dev = _payload.getUnsignedByte(32);
            gcm2_err = _payload.getUnsignedByte(33);
            gcm2_act = _payload.getUnsignedByte(34);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal err_codeIntSig = Signal.SeriesInt("err_code", "",
            null, Units.noUnits);

    protected static Signal err_codeFloatSig = Signal.SeriesFloat("err_code",
            "", null, Units.noUnits);

    protected static Signal err_codeDoubleSig = Signal.SeriesDouble("err_code",
            "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(imu1_break, err_codeIntSig, "imu1_break", lineT,
                    valid);
            printCsvValue(imu2_break, err_codeIntSig, "imu2_break", lineT,
                    valid);
            printCsvValue(gcm0_break, err_codeIntSig, "gcm0_break", lineT,
                    valid);
            printCsvValue(gcm1_break, err_codeIntSig, "gcm1_break", lineT,
                    valid);
            printCsvValue(gcm2_break, err_codeIntSig, "gcm2_break", lineT,
                    valid);
            printCsvValue(imu0_fault, err_codeIntSig, "imu0_fault", lineT,
                    valid);
            printCsvValue(imu1_fault, err_codeIntSig, "imu1_fault", lineT,
                    valid);
            printCsvValue(imu2_fault, err_codeIntSig, "imu2_fault", lineT,
                    valid);
            printCsvValue(gcm0_fault, err_codeIntSig, "gcm0_fault", lineT,
                    valid);
            printCsvValue(gcm1_fault, err_codeIntSig, "gcm1_fault", lineT,
                    valid);
            printCsvValue(gcm2_fault, err_codeIntSig, "gcm2_fault", lineT,
                    valid);
            printCsvValue(mc_fault, err_codeIntSig, "mc_fault", lineT, valid);
            printCsvValue(mc_dev, err_codeIntSig, "mc_dev", lineT, valid);
            printCsvValue(mc_err, err_codeIntSig, "mc_err", lineT, valid);
            printCsvValue(mc_act, err_codeIntSig, "mc_act", lineT, valid);
            printCsvValue(imu_stat, err_codeIntSig, "imu_stat", lineT, valid);
            printCsvValue(mag_stat, err_codeIntSig, "mag_stat", lineT, valid);
            printCsvValue(imu0_dev, err_codeIntSig, "imu0_dev", lineT, valid);
            printCsvValue(imu0_err, err_codeIntSig, "imu0_err", lineT, valid);
            printCsvValue(imu0_act, err_codeIntSig, "imu0_act", lineT, valid);
            printCsvValue(imu1_dev, err_codeIntSig, "imu1_dev", lineT, valid);
            printCsvValue(imu1_err, err_codeIntSig, "imu1_err", lineT, valid);
            printCsvValue(imu1_act, err_codeIntSig, "imu1_act", lineT, valid);
            printCsvValue(imu2_dev, err_codeIntSig, "imu2_dev", lineT, valid);
            printCsvValue(imu2_err, err_codeIntSig, "imu2_err", lineT, valid);
            printCsvValue(imu2_act, err_codeIntSig, "imu2_act", lineT, valid);
            printCsvValue(gcm0_dev, err_codeIntSig, "gcm0_dev", lineT, valid);
            printCsvValue(gcm0_err, err_codeIntSig, "gcm0_err", lineT, valid);
            printCsvValue(gcm0_act, err_codeIntSig, "gcm0_act", lineT, valid);
            printCsvValue(gcm1_dev, err_codeIntSig, "gcm1_dev", lineT, valid);
            printCsvValue(gcm1_err, err_codeIntSig, "gcm1_err", lineT, valid);
            printCsvValue(gcm1_act, err_codeIntSig, "gcm1_act", lineT, valid);
            printCsvValue(gcm2_dev, err_codeIntSig, "gcm2_dev", lineT, valid);
            printCsvValue(gcm2_err, err_codeIntSig, "gcm2_err", lineT, valid);
            printCsvValue(gcm2_act, err_codeIntSig, "gcm2_act", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
