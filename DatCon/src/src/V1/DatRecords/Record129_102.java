package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;

// 50 Hz
//length 86
public class Record129_102 extends Record {

    //All 0 if motors off
    public Record129_102() {
        _type = 129;
        _subType = 102;

    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
