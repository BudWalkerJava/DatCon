package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class ATTI_MINI1_2209 extends Record {
    protected boolean valid = false;

    protected float s_qw1 = (float) 0;

    protected float s_qx1 = (float) 0;

    protected float s_qy1 = (float) 0;

    protected float s_qz1 = (float) 0;

    protected float s_pgz1 = (float) 0;

    protected float s_vgz1 = (float) 0;

    protected float s_agz1 = (float) 0;

    protected long s_rsv01 = (long) 0;

    protected long s_rsv11 = (long) 0;

    protected long s_cnt1 = (long) 0;

    public ATTI_MINI1_2209(ConvertDat convertDat) {
        super(convertDat, 2209, 40);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            s_qw1 = _payload.getFloat(0);
            s_qx1 = _payload.getFloat(4);
            s_qy1 = _payload.getFloat(8);
            s_qz1 = _payload.getFloat(12);
            s_pgz1 = _payload.getFloat(16);
            s_vgz1 = _payload.getFloat(20);
            s_agz1 = _payload.getFloat(24);
            s_rsv01 = _payload.getUnsignedInt(28);
            s_rsv11 = _payload.getUnsignedInt(32);
            s_cnt1 = _payload.getUnsignedInt(36);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal ATTI_MINI1IntSig = Signal.SeriesInt("ATTI_MINI1",
            "", null, Units.noUnits);

    protected static Signal ATTI_MINI1FloatSig = Signal
            .SeriesFloat("ATTI_MINI1", "", null, Units.noUnits);

    protected static Signal ATTI_MINI1DoubleSig = Signal
            .SeriesDouble("ATTI_MINI1", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(s_qw1, ATTI_MINI1FloatSig, "s_qw1", lineT, valid);
            printCsvValue(s_qx1, ATTI_MINI1FloatSig, "s_qx1", lineT, valid);
            printCsvValue(s_qy1, ATTI_MINI1FloatSig, "s_qy1", lineT, valid);
            printCsvValue(s_qz1, ATTI_MINI1FloatSig, "s_qz1", lineT, valid);
            printCsvValue(s_pgz1, ATTI_MINI1FloatSig, "s_pgz1", lineT, valid);
            printCsvValue(s_vgz1, ATTI_MINI1FloatSig, "s_vgz1", lineT, valid);
            printCsvValue(s_agz1, ATTI_MINI1FloatSig, "s_agz1", lineT, valid);
            printCsvValue(s_rsv01, ATTI_MINI1IntSig, "s_rsv01", lineT, valid);
            printCsvValue(s_rsv11, ATTI_MINI1IntSig, "s_rsv11", lineT, valid);
            printCsvValue(s_cnt1, ATTI_MINI1IntSig, "s_cnt1", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
