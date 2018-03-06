package src.DatConRecs.FromOtherV3Dats;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class ns_data_component_10086 extends Record {
    protected boolean valid = false;

    protected long ns_cmpnt = (long) 0;

    public ns_data_component_10086(ConvertDat convertDat) {
        super(convertDat, 10086, 4);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            ns_cmpnt = _payload.getUnsignedInt(0);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal ns_data_componentIntSig = Signal
            .SeriesInt("ns_data_component", "", null, Units.noUnits);

    protected static Signal ns_data_componentFloatSig = Signal
            .SeriesFloat("ns_data_component", "", null, Units.noUnits);

    protected static Signal ns_data_componentDoubleSig = Signal
            .SeriesDouble("ns_data_component", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(ns_cmpnt, ns_data_componentIntSig, "ns_cmpnt", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
