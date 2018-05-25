package src.DatConRecs.Created4V1;

import java.util.Vector;

import src.Files.RecClassSpec;

public class Dictionary {
    public static Vector<RecClassSpec> entries = new Vector<RecClassSpec>();
    static {
        entries.add(new RecClassSpec(GPS_GLNS68_5.class, 5, 68));
        entries.add(new RecClassSpec(RecMotor76_52721.class, 52721, 76));
        entries.add(new RecClassSpec(RecSmartBatt77_18.class, 18, 77));
        entries.add(new RecClassSpec(RecController43_0.class, 0, 43));
        entries.add(new RecClassSpec(RecTabletLoc179_43.class, 43, 179));
        entries.add(new RecClassSpec(HomePoint34_13.class, 13, 34, 68));
        entries.add(new RecClassSpec(MotorCtrl35_54.class, 54, 35));
        entries.add(new RecClassSpec(Mag8_4.class, 4, 8));
        entries.add(new RecClassSpec(AirCraftCondition39_70.class, 70, 39));
        entries.add(new RecClassSpec(IMUEX42_3.class, 3, 42));
        entries.add(new RecClassSpec(Record_1.class, 1, 120));
        //        entries.add(new RecClassSpec());
    }
}
