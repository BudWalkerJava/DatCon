package src.V1.DatRecords;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record.cvsTermType;
import src.Files.Quaternion;
// 200 HZ
//length 50
// have values with motors off

public class Record187_7 extends Record {
    float quatW = 0.0f;

    float quatX = 0.0f;

    float quatY = 0.0f;

    float quatZ = 0.0f;

    public Record187_7() {
        _type = 187;
        _subType = 7;
        //addCSVTerm("IMUTemp", cvsTermType.FLOAT4, 0); // could be temp
        //addCSVTerm("REC187:X1", cvsTermType.FLOAT4, 4);
        //addCSVTerm("MysteryTemp", cvsTermType.FLOAT4, 8); // could be temp
        //        addCSVTerm("REC187:X3", cvsTermType.FLOAT4, 12);
        //        addCSVTerm("REC187:X4", cvsTermType.FLOAT4, 16);
        //        addCSVTerm("REC187:X9", cvsTermType.FLOAT4, 36);  // signal strength ??
        //        addCSVTerm("REC187:X10", cvsTermType.FLOAT4, 40); //NOT SURE ABOUT THIS ONE
        // 36 float possible signal strength
    }

    public void process(Payload _payload) {
        super.process(_payload);
        quatW = payloadBB.getFloat(20);
        quatX = payloadBB.getFloat(24);
        quatY = payloadBB.getFloat(28);
        quatZ = payloadBB.getFloat(32);
    }

    public void printCsvHeader(PrintStream _csv) {
        _csv.print(",X187:roll" + ",X187:pitch" + ",X187:yaw");
    }

    public void printCsvLine(PrintStream _csv) {
        Quaternion q = new Quaternion(quatW, quatX, quatY, quatZ);
        double[] eu = q.toEuler();
        _csv.print("," + (Math.toDegrees(eu[0])) + ","
                + (Math.toDegrees(eu[1])) + "," + (Math.toDegrees(eu[2])));
    }
}
