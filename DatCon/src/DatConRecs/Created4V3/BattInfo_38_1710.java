package src.DatConRecs.Created4V3;

import src.DatConRecs.BattInfo;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class BattInfo_38_1710 extends BattInfo {
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

    public BattInfo_38_1710(ConvertDat convertDat) {
        super(convertDat, 1710, 38);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            Vol = _payload.getFloat(0);
            Current = (float) (((float) (_payload.getFloat(4))) / 1000.0);
            R_time = _payload.getUnsignedShort(8);
            CellVol = (float) (((float) (_payload.getUnsignedShort(10)))
                    / 1000.0);
            LowVolThreshold = _payload.getFloat(12);
            BatVol = (float) (((float) (_payload.getInt(16))) / 1000.0);
            BatCurrent = -(float) (((float) (_payload.getInt(20))) / 1000.0);
            BatCap = _payload.getUnsignedShort(24);
            CapPercnt = _payload.getUnsignedByte(26);
            BatTemp = (float) (((float) (_payload.getShort(27))) / 10.0);
            BatAuth = _payload.getUnsignedByte(29);
            BatDataCnt = _payload.getUnsignedInt(30);
            FullCap = _payload.getUnsignedInt(34);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {

            printCsvValue(Vol, battery_infoFloatSig, "Vol", lineT, valid);
            printCsvValue(Current, currentSig, "Current", lineT, valid);
            printCsvValue(R_time, battInfoRemainTimeSig, "remainingTime", lineT,
                    valid);
            printCsvValue(CellVol, cellVoltSig, "CellVol", lineT, valid);
            printCsvValue(LowVolThreshold, battery_infoFloatSig,
                    "LowVolThreshold", lineT, valid);
            printCsvValue(BatVol, BattVoltSig, "BatVol", lineT, valid);
            printCsvValue(BatCurrent, currentSig, "BatCurrent", lineT, valid);
            printCsvValue(BatCap, batteryCap, "FullChargeCap", lineT, valid);
            printCsvValue(CapPercnt, batteryCapPrcnt, "Remaining%", lineT,
                    valid);
            printCsvValue(BatTemp, batteryTempSig, "BatTemp", lineT, valid);
            //printCsvValue(BatAuth, battery_infoIntSig, "BatAuth", lineT, valid);
            printCsvValue(BatDataCnt, battery_infoIntSig, "BatDataCnt", lineT,
                    valid);
            printCsvValue(FullCap, batteryCap, "OriginalCap", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
