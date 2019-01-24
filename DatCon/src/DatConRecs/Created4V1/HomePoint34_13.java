package src.DatConRecs.Created4V1;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class HomePoint34_13 extends Record {

    public short rthHeight = 0;

    public boolean valid = false;

    public double longitudeHP;

    public double latitudeHP;

    private float height = 0f;

    public HomePoint34_13(ConvertDat convertDat) {
        super(convertDat, 13, 34);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            double longRad = payloadBB.getDouble(0);
            double latRad = payloadBB.getDouble(8);
            height = payloadBB.getFloat(16);
            //short homeState = payloadBB.getShort(20);
            rthHeight = payloadBB.getShort(22);
            longitudeHP = Math.toDegrees(longRad);
            latitudeHP = Math.toDegrees(latRad);
            if (!valid) {
                if (longRad < 100.0 && latRad < 100.0) {
                    valid = true;
                }
            }
            if (valid) {
                convertDat.processHomePoint(latitudeHP, longitudeHP, height);
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(convertDat.getHPLongDeg(), AxesAndSigs.hpLongitudeSig,
                    "", lineT, valid);
            printCsvValue(convertDat.getHPLatDeg(), AxesAndSigs.hpLatitudeSig,
                    "", lineT, valid);
            printCsvValue(rthHeight, AxesAndSigs.rthHeightSig, "", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
