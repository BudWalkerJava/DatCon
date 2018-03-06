package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class usonic4_16 extends Record {
    protected boolean valid = false;

    protected float usonic_h = 0.0f;;

    protected short usonic_flag = (short) 0;

    protected short usonic_cnt = (short) 0;

    public usonic4_16(ConvertDat convertDat) {
        super(convertDat, 16, 4);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            usonic_h = (float) (((float) (_payload.getShort(0))) / 1000.0);
            usonic_flag = _payload.getUnsignedByte(2);
            usonic_cnt = _payload.getUnsignedByte(3);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    //    protected static Signal usonicIntSig = Signal.SeriesInt("usonic", "", null,
    //            Units.noUnits);

    protected static Signal usonicFloatSig = Signal.SeriesFloat("usonic", "",
            null, Units.meters);

    //    protected static Signal usonicDoubleSig = Signal.SeriesDouble("usonic", "",
    //            null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(usonic_h, usonicFloatSig, "usonic_h", lineT,
                    (usonic_flag > 0));
            //            printCsvValue(usonic_flag, usonicIntSig, "usonic_flag", lineT,
            //                    valid);
            //            printCsvValue(usonic_cnt, usonicIntSig, "usonic_cnt", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
