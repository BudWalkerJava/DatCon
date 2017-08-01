package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record11_51 extends Record {

    public Record11_51() {
        _type = 11;
        _subType = 51;
    }

    private double longRad = 0.0;

    private double latRad = 0.0;

    private double longitude = 0.0;

    private double latitude = 0.0;

    public void process(Payload _payload) {
        super.process(_payload);
        // following may be reversed
        longRad = payloadBB.getDouble(3);
        latRad = payloadBB.getDouble(11);
        longitude = Math.toDegrees(longRad);
        latitude = Math.toDegrees(latRad);
    }
}
