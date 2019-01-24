package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class ATTI_MINI0_2208 extends Record {
    protected boolean valid = false;

    protected float s_qw0 = (float) 0;

    protected float s_qx0 = (float) 0;

    protected float s_qy0 = (float) 0;

    protected float s_qz0 = (float) 0;

    protected float s_pgz0 = (float) 0;

    protected float s_vgz0 = (float) 0;

    protected float s_agz0 = (float) 0;

    protected long s_rsv00 = (long) 0;

    protected long s_rsv10 = (long) 0;

    protected long s_cnt0 = (long) 0;

    public ATTI_MINI0_2208(ConvertDat convertDat) {
        super(convertDat, 2208, 40);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            s_qw0 = _payload.getFloat(0);
            s_qx0 = _payload.getFloat(4);
            s_qy0 = _payload.getFloat(8);
            s_qz0 = _payload.getFloat(12);
            s_pgz0 = _payload.getFloat(16);
            s_vgz0 = _payload.getFloat(20);
            s_agz0 = _payload.getFloat(24);
            s_rsv00 = _payload.getUnsignedInt(28);
            s_rsv10 = _payload.getUnsignedInt(32);
            s_cnt0 = _payload.getUnsignedInt(36);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal ATTI_MINI0IntSig = Signal.SeriesInt("ATTI_MINI0",
            "", null, Units.noUnits);

    protected static Signal ATTI_MINI0FloatSig = Signal
            .SeriesFloat("ATTI_MINI0", "", null, Units.noUnits);

    protected static Signal ATTI_MINI0DoubleSig = Signal
            .SeriesDouble("ATTI_MINI0", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(s_qw0, ATTI_MINI0FloatSig, "s_qw0", lineT, valid);
            printCsvValue(s_qx0, ATTI_MINI0FloatSig, "s_qx0", lineT, valid);
            printCsvValue(s_qy0, ATTI_MINI0FloatSig, "s_qy0", lineT, valid);
            printCsvValue(s_qz0, ATTI_MINI0FloatSig, "s_qz0", lineT, valid);
            printCsvValue(s_pgz0, ATTI_MINI0FloatSig, "s_pgz0", lineT, valid);
            printCsvValue(s_vgz0, ATTI_MINI0FloatSig, "s_vgz0", lineT, valid);
            printCsvValue(s_agz0, ATTI_MINI0FloatSig, "s_agz0", lineT, valid);
            printCsvValue(s_rsv00, ATTI_MINI0IntSig, "s_rsv00", lineT, valid);
            printCsvValue(s_rsv10, ATTI_MINI0IntSig, "s_rsv10", lineT, valid);
            printCsvValue(s_cnt0, ATTI_MINI0IntSig, "s_cnt0", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
