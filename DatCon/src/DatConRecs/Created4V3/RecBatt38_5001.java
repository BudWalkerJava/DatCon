
package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class RecBatt38_5001 extends RecBatt {

    public RecBatt38_5001(ConvertDat convertDat) {
        super(convertDat, 5001, 38);
    }

    public short batteryPercent = 0;

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            if (numSamples == 0) { // first time
                init();
            }
            valid = true;
            numSamples++;
            totalVolts = (float) (((float) (payloadBB.getShort(0))) / 1000.0);
            crrnt = -(float) (((float) (_payload.getUnsignedShort(4) - 65536))
                    / 1000.0);
            temp = (float) (((float) (payloadBB.getShort(16))) / 10.0);
            batteryPercent = payloadBB.get(18);
            for (int i = 0; i < numCells; i++) {
                volt[i] = (float) (((float) (payloadBB.getShort(19 + (2 * i))))
                        / 1000.0);
            }
            float voltMax = maxVolts(volt);
            float voltMin = minVolts(volt);
            voltDiff = voltMax - voltMin;
            processComputedBatt();
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            for (int i = 1; i <= 6; i++) {
                printCsvValue(volt[i - 1], AxesAndSigs.cellVoltSig,
                        ("" + i + "(1)"), lineT, valid);
            }
            printCsvValue(crrnt, AxesAndSigs.currentSig, "(1)", lineT, valid);
            printCsvValue(totalVolts, AxesAndSigs.voltsSig, "total(1)", lineT,
                    valid);
            printCsvValue(temp, AxesAndSigs.batteryTempSig, "(1)", lineT,
                    valid);
            printCsvValue(batteryPercent, AxesAndSigs.battPercent, "(1)", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
