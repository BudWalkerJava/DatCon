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
        entries.add(new RecClassSpec(HomePoint34_13.class, 34, 13));
        //        entries.add(new RecClassSpec());
        //        entries.add(new RecClassSpec());
    }
}
