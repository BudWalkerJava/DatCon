package src.DatConRecs;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Vector;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.RecClassSpec;
import src.Files.RecSpec;

public class Dictionary {

    public static Vector<RecClassSpec> entries = new Vector<RecClassSpec>();
    static {
        entries.add(new RecClassSpec(Record_2048.class, 2048, 120));
        entries.add(new RecClassSpec(GoTxt_12.class, 12, 50, 53, 55));
        entries.add(new RecClassSpec(RecBatt45_17.class, 17, 45));
        entries.add(new RecClassSpec(Record_1.class, 1, 120));
        entries.add(new RecClassSpec(svn_info_65534.class, 65534, -1));
    }

    public static Vector<Integer> defaultOrder = new Vector<Integer>();
    static {
        defaultOrder.add(Integer.valueOf(1));
        defaultOrder.add(Integer.valueOf(2048));
        //GPS
        defaultOrder.add(Integer.valueOf(2096));
        defaultOrder.add(Integer.valueOf(2097));
        defaultOrder.add(Integer.valueOf(2098));
        defaultOrder.add(Integer.valueOf(5));
        //Controller
        defaultOrder.add(Integer.valueOf(1000));
        defaultOrder.add(Integer.valueOf(0));
        //RCStatus
        defaultOrder.add(Integer.valueOf(1700));
        //MagGroup
        defaultOrder.add(Integer.valueOf(2256));
        defaultOrder.add(Integer.valueOf(2257));
        //MagRawGroup
        defaultOrder.add(Integer.valueOf(20350));
        defaultOrder.add(Integer.valueOf(20351));
        defaultOrder.add(Integer.valueOf(20352));
        //HomePoint
        defaultOrder.add(Integer.valueOf(13));
        //Battery
        defaultOrder.add(Integer.valueOf(5000));
        defaultOrder.add(Integer.valueOf(5001));
        defaultOrder.add(Integer.valueOf(5003));
        defaultOrder.add(Integer.valueOf(17));
        //BattStat
        defaultOrder.add(Integer.valueOf(1711));
        //SmartBat
        defaultOrder.add(Integer.valueOf(1712));
        defaultOrder.add(Integer.valueOf(18));
        //front Distance
        //ObstAvoid
        defaultOrder.add(Integer.valueOf(1121));
        defaultOrder.add(Integer.valueOf(100));
        //Motor
        defaultOrder.add(Integer.valueOf(10090));
        defaultOrder.add(Integer.valueOf(52721));
        defaultOrder.add(Integer.valueOf(52));
        // tablet Loc
        defaultOrder.add(Integer.valueOf(43));
        //        defaultOrder.add(Integer.valueOf(17));
        //AirComp
        defaultOrder.add(Integer.valueOf(10100));
        defaultOrder.add(Integer.valueOf(10099));
        //Logs
        defaultOrder.add(Integer.valueOf(0x8000));

        //defaultOrder.add(Integer.valueOf());
    }

    public static Record getRecordInst(Vector<RecClassSpec> entries,
            RecSpec recInDat, ConvertDat convertDat, boolean strictLength) {
        Record retv = null;
        Iterator<RecClassSpec> iter = entries.iterator();
        while (iter.hasNext()) {
            RecClassSpec recClassSpec = iter.next();
            if (recClassSpec.getId() == recInDat.getId()) {
                if ((recClassSpec.lengthOK(recInDat.getLength()))
                        || (!strictLength && recClassSpec.getLength() < recInDat
                                .getLength())) {
                    Class recordClass = recClassSpec.getRecClass();
                    if (recordClass != null) {
                        try {
                            retv = (Record) recordClass
                                    .getConstructor(ConvertDat.class)
                                    .newInstance(convertDat);
                            return retv;
                        } catch (InstantiationException | IllegalAccessException
                                | IllegalArgumentException
                                | InvocationTargetException
                                | NoSuchMethodException | SecurityException e) {
                            DatConLog.Exception(e);
                            if (Persist.EXPERIMENTAL_DEV) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }
                } else {
                    DatConLog.Log("getRecordInst can't use " + recClassSpec
                            + "/" + recClassSpec.getLength()
                            + " wrong length RecInDat " + recInDat);
                }
            }
        }
        return null;
    }
}
