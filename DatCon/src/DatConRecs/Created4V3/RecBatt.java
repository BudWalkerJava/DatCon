package src.DatConRecs.Created4V3;

import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class RecBatt extends Record {

    public float crrnt = (float) 0.0;

    //public short batteryPercent = 0;

    public float volt[];

    protected int numCells = 0;

    public float temp = (float) 0.0;;

    public float totalVolts = (float) 0.0;

    public float maxVolts = (float) 0.0;

    public float minVolts = (float) 0.0;

    public float sumOfVolts = (float) 0.0;

    public float avgVolts = (float) 0.0;

    protected long sumOfCurrents = 0;

    protected long numSamples = 0;

    public float voltDiff = (float) 0.0;

    public float maxCurrent = (float) 0.0;

    public float minCurrent = (float) 0.0;

    public float avgCurrent = (float) 0.0;

    public float watts = (float) 0.0;

    public float maxWatts = (float) 0.0;

    public float minWatts = (float) 0.0;

    protected float sumOfWatts = (float) 0.0;

    public float avgWatts = (float) 0.0;

    public boolean valid = false;

    protected Signal statusSig = Signal.SeriesIntExperimental("Battery:Status",
            "Battery Status", null, Units.noUnits);

    //    public RecBattery(ConvertDat convertDat) {
    //        super(convertDat);
    //        numCells = convertDat.getDatFile().getNumBattCells();
    //        volt = new float[numCells];
    //        for (int i = 0; i < numCells; i++) {
    //            volt[i] = 0.0f;
    //        }
    //    }

    public RecBatt(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
        numCells = convertDat.getDatFile().getNumBattCells();
        volt = new float[numCells];
        for (int i = 0; i < numCells; i++) {
            volt[i] = 0.0f;
        }
    }

    protected void init() {
        maxVolts = (float) -1.0;
        minVolts = Float.MAX_VALUE;
        minCurrent = Float.MAX_VALUE;
        avgCurrent = (float) 0.0;
        maxWatts = (float) -1.0;
        minWatts = Float.MAX_VALUE;
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

    protected float minVolts(float[] volts) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] < min)
                min = volts[i];
        }
        return min;
    }

    protected float maxVolts(float[] volts) {
        float max = Float.MIN_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] > max)
                max = volts[i];
        }
        return max;
    }


    protected void processComputedBatt() {
        if (totalVolts > maxVolts)
            maxVolts = totalVolts;
        if (totalVolts < minVolts)
            minVolts = totalVolts;
        sumOfVolts += totalVolts;
        avgVolts = sumOfVolts / (float) numSamples;

        if (crrnt > maxCurrent)
            maxCurrent = crrnt;
        if (crrnt < minCurrent)
            minCurrent = crrnt;
        sumOfCurrents += crrnt;
        avgCurrent = sumOfCurrents / (float) numSamples;

        watts = totalVolts * crrnt;
        if (watts > maxWatts)
            maxWatts = watts;
        if (watts < minWatts)
            minWatts = watts;
        sumOfWatts += watts;
        avgWatts = sumOfWatts / (float) numSamples;
    }

    protected void printComputedBattCols(lineType lineT) throws Exception {
        //        printCsvValue(crrnt, AxesAndSigs.currentSig, "", lineT, valid);
        //        printCsvValue(totalVolts, AxesAndSigs.voltsSig, "total", lineT, valid);
        printCsvValue(voltDiff, AxesAndSigs.voltsSig, "spread", lineT, valid);
        printCsvValue(watts, AxesAndSigs.wattsSig, "", lineT, valid);
        //        printCsvValue(temp, AxesAndSigs.batteryTempSig, "", lineT, valid);

        printCsvValue(minCurrent, AxesAndSigs.currentSig, "min", lineT, valid);
        printCsvValue(maxCurrent, AxesAndSigs.currentSig, "max", lineT, valid);
        printCsvValue(avgCurrent, AxesAndSigs.currentSig, "avg", lineT, valid);

        printCsvValue(minVolts, AxesAndSigs.voltsSig, "min", lineT, valid);
        printCsvValue(maxVolts, AxesAndSigs.voltsSig, "max", lineT, valid);
        printCsvValue(avgVolts, AxesAndSigs.voltsSig, "avg", lineT, valid);

        printCsvValue(minWatts, AxesAndSigs.wattsSig, "min", lineT, valid);
        printCsvValue(maxWatts, AxesAndSigs.wattsSig, "max", lineT, valid);
        printCsvValue(avgWatts, AxesAndSigs.wattsSig, "avg", lineT, valid);
    }
}
