package src.DatConRecs.Created4V3;

import src.DatConRecs.AirCraftCondition;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class AirCraftCondition16_1001 extends AirCraftCondition {

    protected float UP_acc_t = (float) 0;

    protected float UP_TF_t = (float) 0;

    public AirCraftCondition16_1001(ConvertDat convertDat) {
        super(convertDat, 1001, 16);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            intFlightState = _payload.getUnsignedByte(0);
            flightState = _payload.getUnsignedByte(1);
            lastFlightState = _payload.getUnsignedByte(2);
            nearGndState = _payload.getUnsignedByte(3);
            UP_state = _payload.getUnsignedByte(4);
            UP_acc_t = _payload.getFloat(5);
            UP_TF_t = _payload.getFloat(9);
            landState = _payload.getUnsignedByte(13);
            safe_fltr = _payload.getShort(14);
            nearGrnd = (nearGndState != 0) ? "True" : "False";
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        super.printCols(lineT);
        try {
            printCsvValue(UP_acc_t, aircraft_conditionFloatSig, "UP_acc_t",
                    lineT, valid);
            printCsvValue(UP_TF_t, aircraft_conditionFloatSig, "UP_TF_t", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
