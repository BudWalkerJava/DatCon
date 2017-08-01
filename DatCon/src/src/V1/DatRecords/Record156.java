package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;

public class Record156 extends Record {
    //5 Hz
    // length 68
    // 12 unsigned byte GPS signal strength
    // 27 unsigned byte GPS signal strength
    // 45 unsigned byte GPS signal strength
    public Record156() {
        _type = 156;
        _subType = 97;

    }

    public void process(Payload _payload) {
        super.process(_payload);
        payloadBB = _payload.getBB();
    }

}
