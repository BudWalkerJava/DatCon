package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;

public class Record87_13 extends Record {

    // not every flight

    private double longRad = 0.0;

    private double latRad = 0.0;

    private double longitude = 0.0;

    private double latitude = 0.0;

    public Record87_13() {
        _type = 87;
        _subType = 13;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        // following may be reversed
        longRad = payloadBB.getDouble(0);
        latRad = payloadBB.getDouble(8);
        longitude = Math.toDegrees(longRad);
        latitude = Math.toDegrees(latRad);
    }
}
