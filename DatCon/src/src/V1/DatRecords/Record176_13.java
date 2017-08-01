package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record176_13 extends Record {

    private double longRad = 0.0;

    private double latRad = 0.0;

    private double longitude = 0.0;

    private double latitude = 0.0;

    public Record176_13() {
        _type = 176;
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
