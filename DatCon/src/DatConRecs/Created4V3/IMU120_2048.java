package src.DatConRecs.Created4V3;

import src.DatConRecs.GoTxt50_12;
import src.DatConRecs.Payload;
import src.DatConRecs.RecIMU;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class IMU120_2048 extends RecIMU {
    public IMU120_2048(ConvertDat convertDat) {
        super(convertDat, 2048, 120, 0);
        current = this;
        heightSig = Signal.SeriesDouble("General", "Height", null,
                Units.meters);
    }

    public void process(Payload _payload) {
        super.process(_payload);

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
