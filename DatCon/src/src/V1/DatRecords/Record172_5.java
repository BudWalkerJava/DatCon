package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record172_5 extends Record {

    // 5 HZ
    // length 70
    public Record172_5() {
        _type = 172;
        _subType = 5;

        // 56 GPS signal strength
        // 60 GPS
        // 64 num sats
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
