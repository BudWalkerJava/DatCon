package src.DatConRecs.FromOtherV3Dats;

import java.util.Vector;

import src.Files.RecClassSpec;

public class Dictionary {
    public static Vector<RecClassSpec> entries = new Vector<RecClassSpec>();
    static {
        entries.add(new RecClassSpec(err_code_112.class, 112, 35));
        entries.add(new RecClassSpec(ns_data_debug_10085.class, 10085, 80));
        entries.add(new RecClassSpec(ns_data_component_10086.class, 10086, 4));
        entries.add(new RecClassSpec(aircraft_condition_1001.class, 1001, 8));
        entries.add(new RecClassSpec(ATTI_MINI0_2208.class, 2208, 40));
        entries.add(new RecClassSpec(ATTI_MINI1_2209.class, 2209, 40));
        entries.add(new RecClassSpec(ns_data_debug_10085.class, 10085, 80));
        entries.add(new RecClassSpec(ns_data_component_10086.class, 10086, 4));
        entries.add(new RecClassSpec(serial_api_inputs_1002.class, 1002, 23));
        entries.add(new RecClassSpec(battery_info_1710.class, 1710, 44));
        entries.add(new RecClassSpec(Controller_39_1000.class, 1000, 39));
       
        //entries.add(new RecClassSpec(.class, ));
    }
}
