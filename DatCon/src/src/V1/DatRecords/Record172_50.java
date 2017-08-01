package src.V1.DatRecords;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;

public class Record172_50 extends Record {
    // 50HZ
    // length 70
    public Record172_50() {
        _type = 172;
        _subType = 50;
        //        addCSVTerm("REC172:X0", cvsTermType.FLOAT4, 3);
        //        addCSVTerm("REC172:X1", cvsTermType.FLOAT4, 7);
        //        addCSVTerm("REC172:X2", cvsTermType.FLOAT4, 11);
        //        addCSVTerm("REC172:X3", cvsTermType.FLOAT4, 15);
        //addCSVTerm("REC172:X4", cvsTermType.FLOAT4, 19);
        //addCSVTerm("REC172:X5", cvsTermType.FLOAT4, 23); 
        //addCSVTerm("REC172:X6", cvsTermType.FLOAT4, 27);
        //addCSVTerm("REC172:X7", cvsTermType.FLOAT4, 31);
        // motors off
        // offset 20 Byte goes from 0 to 1 when connection is made of flight starts
        // and then 1 to 3 when motors come on
        // offset 27 Unsigned byte some trending up signal
        // 44 Short
        // 34 Byte VPS on/off signal?
        // 36, 38, 40, 42 identical? maybe something for each motor
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

}
