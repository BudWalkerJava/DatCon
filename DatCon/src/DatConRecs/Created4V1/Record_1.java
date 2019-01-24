package src.DatConRecs.Created4V1;

import src.DatConRecs.GoTxt50_12;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class Record_1 extends src.DatConRecs.RecIMU {
    public Record_1(ConvertDat convertDat) {
        super(convertDat, 1, 120, 0);
        current = this;
        heightSig = Signal.SeriesDouble("General", "Height", null,
                Units.meters);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        imuTemp *= 100.0;
    }

    public void printCols(lineType lineT) {
        super.printCols(lineT);
        try {
            if (GoTxt50_12.current != null) {
                printCsvValue(GoTxt50_12.current.flightTime, flightTimeSig, "",
                        lineT, GoTxt50_12.current.valid);
            }

            if (GoTxt50_12.current != null) {
                printCsvValue(GoTxt50_12.current.gpsLevel, gpsHealthSig, "",
                        lineT, GoTxt50_12.current.valid);
            }

            if (GoTxt50_12.current != null) {
                printCsvValue(GoTxt50_12.current.vpsHeight, heightSig,
                        "vpsHeight", lineT,
                        GoTxt50_12.current.valid & GoTxt50_12.current.waveWork);
            }
            printCsvValue(convertDat.getRelativeHeight(), heightSig,
                    "relativeHeight", lineT, convertDat.isRelativeHeightOK());

            printCsvValue(convertDat.getAbsoluteHeight(), heightSig,
                    "absoluteHeight", lineT, convertDat.absoluteHeightValid);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
