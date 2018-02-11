package src.DatConRecs.Created4V3;

import java.util.Vector;

import src.Files.RecClassSpec;

public class Dictionary extends src.DatConRecs.Dictionary {

    public static Vector<RecClassSpec> entries = new Vector<RecClassSpec>();
    static {

        entries.add(new RecClassSpec(GPS_2096.class, 2096, 66, 68, 72));
        entries.add(new RecClassSpec(GPS_2097.class, 2097, 66, 68, 72));
        entries.add(new RecClassSpec(GPS_2098.class, 2098, 66, 68, 72));

        entries.add(new RecClassSpec(HomePoint24_13.class, 13, 24, 45, 68, 69));

        entries.add(new RecClassSpec(RecSmartBatt10_1712.class, 1712, 10));

        entries.add(new RecClassSpec(RecAirComp21_10100.class, 10100, 21));
        entries.add(new RecClassSpec(RecAirComp29_10100.class, 10100, 29));
        entries.add(new RecClassSpec(RecAirComp32_10099.class, 10099, 32));

        entries.add(new RecClassSpec(RecBatt26_5000.class, 5000, 26));
        entries.add(new RecClassSpec(RecBatt38_5000.class, 5000, 38));
        entries.add(new RecClassSpec(RecBatt38_5001.class, 5001, 38));
        entries.add(new RecClassSpec(RecBatt38_5002.class, 5002, 38));

        entries.add(new RecClassSpec(RecBattStat19_1711.class, 1711, 19, 20));

        //        entries.add(new RecClassSpec(RecController36_1000.class, 1000, 36));
        //        entries.add(new RecClassSpec(RecController37_1000.class, 1000, 37));
        //        entries.add(new RecClassSpec(RecController39_1000.class, 1000, 39));
        //        entries.add(new RecClassSpec(RecController41_1000.class, 1000, 41));
        //        entries.add(new RecClassSpec(RecController47_1000.class, 1000, 47));
        //        entries.add(new RecClassSpec(RecController52_1000.class, 1000, 52));
        //        entries.add(new RecClassSpec(RecController53_1000.class, 1000, 53));

        entries.add(new RecClassSpec(Controller_36_1000.class, 1000, 36));
        entries.add(new RecClassSpec(Controller_37_1000.class, 1000, 37));
        entries.add(new RecClassSpec(Controller_39_1000.class, 1000, 39));
        entries.add(new RecClassSpec(Controller_40_1000.class, 1000, 40));
        entries.add(new RecClassSpec(Controller_41_1000.class, 1000, 41));
        entries.add(new RecClassSpec(Controller_44_1000.class, 1000, 44));
        entries.add(new RecClassSpec(Controller_47_1000.class, 1000, 47));
        entries.add(new RecClassSpec(Controller_49_1000.class, 1000, 49));
        entries.add(new RecClassSpec(Controller_51_1000.class, 1000, 51));
        entries.add(new RecClassSpec(Controller_52_1000.class, 1000, 52));
        entries.add(new RecClassSpec(Controller_53_1000.class, 1000, 53));

        //entries.add(new RecClassSpec());
        entries.add(new RecClassSpec(RecMotor152_10090.class, 10090, 152));
        entries.add(new RecClassSpec(RecMotor169_10090.class, 10090, 169));
        entries.add(new RecClassSpec(RecMotor185_10090.class, 10090, 185));

        entries.add(new RecClassSpec(MotorCtrl16_1307.class, 1307, 16));
        entries.add(new RecClassSpec(MotorCtrl17_1307.class, 1307, 17));
        entries.add(new RecClassSpec(MotorCtrl17_1308.class, 1308, 17));
        //entries.add(new RecClassSpec());
        //entries.add(new RecClassSpec());
        entries.add(new RecClassSpec(RecRCStat17_1700.class, 1700, 17));
        entries.add(new RecClassSpec(RecRCStat18_1700.class, 1700, 18));
        entries.add(new RecClassSpec(RecRCStat48_1700.class, 1700, 48));
        entries.add(new RecClassSpec(RecRCStat78_1700.class, 1700, 78));
        entries.add(new RecClassSpec(RecRCStat82_1700.class, 1700, 82));

        entries.add(new RecClassSpec(RecOA27_100.class, 100, 27));
        entries.add(new RecClassSpec(RecSVOAVOID15_1121.class, 1121, 15));

        entries.add(new RecClassSpec(RecMag6_2256.class, 2256, 6, 8, 10, 12));
        entries.add(new RecClassSpec(RecMag6_2257.class, 2257, 6, 8, 10, 12));

        entries.add(new RecClassSpec(RecMagRaw6_20350.class, 20350, 6));
        entries.add(new RecClassSpec(RecMagRaw6_20351.class, 20351, 6));
        entries.add(new RecClassSpec(RecMagRaw6_20352.class, 20352, 6));
        //entries.add(new RecClassSpec());
        //entries.add(new RecClassSpec(, , ));
        //entries.add(new RecClassSpec(RecDefs_65533.class, 65533, -1));
    }

}
