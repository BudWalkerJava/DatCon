package src.V1.DatRecords;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;


public class Record172_10 extends Record {

    public float imuTemp = (float) 0.0;
    
    public Record172_10() {
        _type = 172;
        _subType = 0;
        //addCSVTerm("IMUTemp(C)", cvsTermType.SHORT, 46); // * 100 Celcius
    }
    // 56 short another regulated temp

    public void process(Payload _payload) {
        super.process(_payload);
        imuTemp = (((float)payloadBB.getShort(46)) / ((float)100.00));
    }

}
