package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class ofdm_cnt_1999 extends Record {
    protected boolean valid = false;

    protected int recv_total = (int) 0;

    protected int header_error = (int) 0;

    protected int v1_error = (int) 0;

    protected int v0_error = (int) 0;

    protected int seccuss = (int) 0;

    public ofdm_cnt_1999(ConvertDat convertDat) {
        super(convertDat, 1999, 10);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            recv_total = _payload.getUnsignedShort(0);
            header_error = _payload.getUnsignedShort(2);
            v1_error = _payload.getUnsignedShort(4);
            v0_error = _payload.getUnsignedShort(6);
            seccuss = _payload.getUnsignedShort(8);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal ofdm_cntIntSig = Signal.SeriesInt("ofdm_cnt", "",
            null, Units.noUnits);

    protected static Signal ofdm_cntFloatSig = Signal.SeriesFloat("ofdm_cnt",
            "", null, Units.noUnits);

    protected static Signal ofdm_cntDoubleSig = Signal.SeriesDouble("ofdm_cnt",
            "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(recv_total, ofdm_cntIntSig, "recv_total", lineT,
                    valid);
            printCsvValue(header_error, ofdm_cntIntSig, "header_error", lineT,
                    valid);
            printCsvValue(v1_error, ofdm_cntIntSig, "v1_error", lineT, valid);
            printCsvValue(v0_error, ofdm_cntIntSig, "v0_error", lineT, valid);
            printCsvValue(seccuss, ofdm_cntIntSig, "seccuss", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
