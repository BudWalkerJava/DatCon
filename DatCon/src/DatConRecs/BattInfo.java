package src.DatConRecs;

import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.Signal;
import src.Files.Units;

public class BattInfo extends Record {
    protected boolean valid = false;

    protected float Vol = (float) 0;

    protected float Current = (float) 0;

    protected int R_time = (int) 0;

    protected float CellVol = 0.0f;

    protected float LowVolThreshold = (float) 0;

    protected float BatVol = 0.0f;

    protected float BatCurrent = 0.0f;

    protected int BatCap = (int) 0;

    protected short CapPercnt = (short) 0;

    protected float BatTemp = 0.0f;

    protected short BatAuth = (short) 0;

    protected long BatDataCnt = (long) 0;

    protected long FullCap = (long) 0;

    public BattInfo(ConvertDat convertDat) {
        super(convertDat, 1710, 38);
    }

    public BattInfo(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
    }

    protected static Signal battery_infoIntSig = Signal.SeriesInt("BattInfo",
            "", null, Units.noUnits);

    protected static Signal battery_infoFloatSig = Signal
            .SeriesFloat("BattInfo", "", null, Units.noUnits);

    public static Signal battInfoRemainTimeSig = Signal.SeriesInt("BattInfo",
            "RemainingTime", null, Units.seconds);

    public final static Signal currentSig = Signal.SeriesFloat("BattInfo",
            "Current", null, Units.amps);

    public final static Signal cellVoltSig = Signal.SeriesFloat("BattInfo",
            "Cell Volts", AxesAndSigs.cellVoltsAxis, Units.volts);

    public final static Signal BattVoltSig = Signal.SeriesFloat("BattInfo",
            "Cell Volts", null, Units.volts);

    public final static Signal batteryTempSig = Signal.SeriesFloat("BattInfo",
            "Battery Temp", null, Units.degreesC);

    public final static Signal batteryCap = Signal.SeriesFloat("BattInfo",
            "Battery Capacity", null, Units.mAh);

    public final static Signal batteryCapPrcnt = Signal.SeriesFloat("BattInfo",
            "Battery Capacity %", null, Units.percentage);;

    //    public void printCols(lineType lineT) {
    //        try {
    //
    //            printCsvValue(Vol, battery_infoFloatSig, "Vol", lineT, valid);
    //            printCsvValue(Current, currentSig, "Current", lineT, valid);
    //            printCsvValue(R_time, battery_infoIntSig, "R_time", lineT, valid);
    //            printCsvValue(CellVol, cellVoltSig, "CellVol", lineT, valid);
    //            printCsvValue(LowVolThreshold, battery_infoFloatSig,
    //                    "LowVolThreshold", lineT, valid);
    //            printCsvValue(BatVol, BattVoltSig, "BatVol", lineT, valid);
    //            printCsvValue(BatCurrent, currentSig, "BatCurrent", lineT, valid);
    //            printCsvValue(BatCap, batteryCap, "FullChargeCap", lineT, valid);
    //            printCsvValue(CapPercnt, batteryCapPrcnt, "Remaining%", lineT,
    //                    valid);
    //            printCsvValue(BatTemp, batteryTempSig, "BatTemp", lineT, valid);
    //            //printCsvValue(BatAuth, battery_infoIntSig, "BatAuth", lineT, valid);
    //            printCsvValue(BatDataCnt, battery_infoIntSig, "BatDataCnt", lineT,
    //                    valid);
    //            printCsvValue(FullCap, batteryCap, "OriginalCap", lineT, valid);
    //        } catch (Exception e) {
    //            DatConLog.Exception(e);
    //        }
    //    }

}
