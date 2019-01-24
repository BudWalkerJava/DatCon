package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.Signal;
import src.Files.Units;

public class MagRawGroup extends Record {

    public short magX = 0;

    public short magY = 0;

    public short magZ = 0;

    public boolean valid;

    private int index;

    public MagRawGroup(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length);
        this.index = index;
        magRawSig = Signal.SeriesFloat("Mag" + "(" + index + ")",
                "Magnetometer", null, Units.aTesla);
    }

    protected Signal magRawSig = null;

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            magX = payloadBB.getShort(0);
            magY = payloadBB.getShort(2);
            magZ = payloadBB.getShort(4);
            valid = true;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        if (Persist.magCalcs) {
            try {
                float magMod = (int) Math
                        .sqrt(magX * magX + magY * magY + magZ * magZ);
                printCsvValue(magX, magRawSig, "rawX", lineT, valid);
                printCsvValue(magY, magRawSig, "rawY", lineT, valid);
                printCsvValue(magZ, magRawSig, "rawZ", lineT, valid);
                printCsvValue(magMod, magRawSig, "rawMod", lineT, valid);
                //            printCsvValue(magYaw, AxesAndSigs.magYawSig, "(" + index + ")",
                //                    lineT, valid);
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }
}
