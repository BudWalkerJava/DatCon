package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record94_51 extends Record {

    // 50 HZ
    // length 124
    
    // not every flight
    
    //VPS offset 74 may be VPS height derivative
    //VPS offset 88 may be on/off
    
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
