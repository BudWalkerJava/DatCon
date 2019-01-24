package src.DatConRecs.Created4V1;

import src.DatConRecs.Payload;
import src.DatConRecs.RecBatt;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class RecBatt45_17 extends RecBatt {

    public RecBatt45_17(ConvertDat convertDat) {
        super(convertDat, 17, 45, 0);
    }

    protected float maxVolt(float... floatVolts) {
        float retv = -Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts > retv) {
                retv = volts;
            }
        }
        return retv;
    }

    protected float minVolt(float... floatVolts) {
        float retv = Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts < retv) {
                retv = volts;
            }
        }
        return retv;
    }

    //public float remainingCapacity = (float) 0.0;

    public float ratedCapacity = (float) 0.0;

    //public int capacityPercentage;

    //protected int design_capacity = (int) 0;

    public void process(Payload _payload) {
        super.process(_payload);
        if (numSamples == 0) { // first time
            init();
        }
        valid = true;
        numSamples++;
        fcc = _payload.getUnsignedShort(0);
        ratedCapacity = (float) (((float) (payloadBB.getShort(2))));
        remcap = (float) (((float) (payloadBB.getShort(4))));
        totalVolts = (float) (((float) (payloadBB.getShort(6))) / 1000.0);
        crrnt = -(float) (((float) (_payload.getUnsignedShort(8) - 65536))
                / 1000.0);
        batteryPercent = payloadBB.get(11);
        temp = (float) (((float) (payloadBB.get(12))));
        for (int i = 0; i < numCells; i++) {
            volt[i] = (float) (((float) (payloadBB.getShort(18 + (2 * i))))
                    / 1000.0);
        }
        float voltMax = maxVolt(volt);
        float voltMin = minVolt(volt);
        voltDiff = voltMax - voltMin;
        processComputedBatt();
    }

    //    design_capacity = _payload.getUnsignedShort(0);
    //    full_charge_capacity = _payload.getUnsignedShort(2);
    //    remaining_capacity = _payload.getUnsignedShort(4);
    //    pack_voltage = _payload.getUnsignedShort(6);
    //    current = _payload.getShort(8);
    //    life_percentage = _payload.getUnsignedByte(10);
    //    capacity_percentage = _payload.getUnsignedByte(11);
    //    temperature = _payload.getShort(12);
    //    cycle_count = _payload.getUnsignedShort(14);
    //    serial_number = _payload.getUnsignedShort(16);
    //    cell1 = _payload.getUnsignedShort(18);
    //    cell2 = _payload.getUnsignedShort(20);
    //    cell3 = _payload.getUnsignedShort(22);
    //    cell4 = _payload.getUnsignedShort(24);
    //    cell5 = _payload.getUnsignedShort(26);
    //    cell6 = _payload.getUnsignedShort(28);
    //    average_current = _payload.getShort(30);
    //    right = _payload.getUnsignedByte(32);
    //    error_count = _payload.getUnsignedInt(33);
    //    n_discharge_times = _payload.getUnsignedInt(37);
    //    current_status = _payload.getUnsignedInt(41);
    //    @Override
    //    public void printCols(lineType lineT) {
    //        try {
    //            for (int i = 0; i < numCells; i++) {
    //                printCsvValue(volt[i], AxesAndSigs.cellVoltSig, "" + (i + 1),
    //                        lineT, valid);
    //            }
    //            printCsvValue(crrnt, AxesAndSigs.currentSig, "", lineT, valid);
    //            printCsvValue(totalVolts, AxesAndSigs.voltsSig, "total", lineT,
    //                    valid);
    //            printCsvValue(temp, AxesAndSigs.batteryTempSig, "", lineT, valid);
    //            printCsvValue(capacityPercentage, AxesAndSigs.battPercent, "",
    //                    lineT, valid);
    //            printComputedBattCols(lineT);
    //        } catch (Exception e) {
    //            DatConLog.Exception(e);
    //        }
    //    }
}
