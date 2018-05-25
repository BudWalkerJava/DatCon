package src.DatConRecs.Created4V3;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class BatBox32_500X extends RecBatt {
    protected boolean valid = false;

    protected int time = (int) 0;

    protected short rec_data = (short) 0;

    protected short soc = (short) 0;

    protected int cell1 = (int) 0;

    protected int cell2 = (int) 0;

    protected int cell3 = (int) 0;

    protected int cell4 = (int) 0;

    protected int cell5 = (int) 0;

    protected int cell6 = (int) 0;

    protected short pro_curr = (short) 0;

    protected short current = (short) 0;

    protected int pack_vol = (int) 0;

    protected short temp1 = (short) 0;

    protected short temp2 = (short) 0;

    protected int flag = (int) 0;

    protected long user_def = (long) 0;

    public BatBox32_500X(ConvertDat convertDat, int id, int index) {
        super(convertDat, id, 32, index);
        BatBoxIntSig = Signal.SeriesInt("Battery", index, "", null,
                Units.noUnits);

        //        BatBoxFloatSig = Signal.SeriesFloat("BatBox", index, "", null,
        //                Units.noUnits);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            time = _payload.getInt(0);
            rec_data = _payload.getUnsignedByte(4);
            batteryPercent = payloadBB.get(5);
            //soc = _payload.getUnsignedByte(5);
            for (int i = 0; i < numCells; i++) {
                volt[i] = (float) (((float) (payloadBB.getShort(6 + (2 * i))))
                        / 1000.0);
            }
            //            cell1 = _payload.getUnsignedShort(6);
            //            cell2 = _payload.getUnsignedShort(8);
            //            cell3 = _payload.getUnsignedShort(10);
            //            cell4 = _payload.getUnsignedShort(12);
            //            cell5 = _payload.getUnsignedShort(14);
            //            cell6 = _payload.getUnsignedShort(16);
            pro_curr = _payload.getShort(18);
            current = _payload.getShort(20);
            totalVolts = (float) (((float) (payloadBB.getInt(22))) / 1000.0);
            //pack_vol = _payload.getUnsignedShort(22);
            temp1 = _payload.getUnsignedByte(24);
            temp2 = _payload.getUnsignedByte(25);
            flag = _payload.getUnsignedShort(26);
            user_def = _payload.getUnsignedInt(28);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected Signal BatBoxIntSig = null;

    //protected Signal BatBoxFloatSig = null;

    //protected Signal BatBoxDoubleSig = null;
    public void printCols(lineType lineT) {

        try {
            for (int i = 1; i <= _datFile.getNumBattCells(); i++) {
                printCsvValue(volt[i - 1], cellVoltSig, "cellVolts" + i, lineT,
                        valid);
            }
            printCsvValue(crrnt, currentSig, "current", lineT, valid);
            printCsvValue(totalVolts, voltsSig, "totalVolts", lineT, valid);
            //printCsvValue(temp, batteryTempSig, "Temp", lineT, valid);
            printCsvValue(batteryPercent, battPercent, "battery%", lineT,
                    valid);
            //printCsvValue(fcc, batteryFCC, "FullChargeCap", lineT, valid);
            //printCsvValue(remcap, batteryRemCap, "RemainingCap", lineT, valid);
            printComputedBattCols(lineT);
            printCsvValue(time, BatBoxIntSig, "time", lineT, valid);
            printCsvValue(rec_data, BatBoxIntSig, "rec_data", lineT, valid);

            printCsvValue(pro_curr, BatBoxIntSig, "pro_curr", lineT, valid);
            printCsvValue(current, BatBoxIntSig, "current", lineT, valid);
            //printCsvValue(pack_vol, bat1_boxIntSig, "pack_vol", lineT, valid);
            printCsvValue(temp1, BatBoxIntSig, "temp1", lineT, valid);
            printCsvValue(temp2, BatBoxIntSig, "temp2", lineT, valid);
            printCsvValue(flag, BatBoxIntSig, "flag", lineT, valid);
            //printCsvValue(user_def, BatBoxIntSig, "user_def", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
