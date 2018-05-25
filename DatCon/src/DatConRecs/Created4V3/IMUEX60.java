package src.DatConRecs.Created4V3;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class IMUEX60 extends IMUEX {

    protected double rtk_long = (double) 0;

    protected double rtk_lati = (double) 0;

    protected float rtk_alti = (float) 0;

    public IMUEX60(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length, index);

        coordSig = Signal.SeriesDouble("IMUEX" + "(" + index + ")", "", null,
                Units.degrees180);
        altiSig = Signal.SeriesDouble("IMUEX" + "(" + index + ")", "", null,
                Units.meters);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            vo_vx = _payload.getFloat(0);
            vo_vy = _payload.getFloat(4);
            vo_vz = _payload.getFloat(8);
            vo_px = _payload.getFloat(12);
            vo_py = _payload.getFloat(16);
            vo_pz = _payload.getFloat(20);
            us_v = _payload.getFloat(24);
            us_p = _payload.getFloat(28);
            rtk_long = _payload.getDouble(32);
            rtk_lati = _payload.getDouble(40);
            rtk_alti = _payload.getFloat(48);
            vo_flag_navi = _payload.getUnsignedShort(52);
            flag_err = _payload.getUnsignedShort(54);
            vo_flag_rsv = _payload.getUnsignedShort(56);
            ex_cnt = _payload.getUnsignedShort(58);
            errString = getErrString(flag_err);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public Signal coordSig = null;

    public Signal altiSig = null;

    public void printCols(lineType lineT) {
        try {
            super.printCols(lineT);
            printCsvValue(Math.toDegrees(rtk_long), coordSig, "rtk_Longitude",
                    lineT, valid);
            printCsvValue(Math.toDegrees(rtk_lati), coordSig, "rtk_Latitude",
                    lineT, valid);
            printCsvValue(rtk_alti, altiSig, "rtk_Alti", lineT, valid);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
