
package src.DatConRecs.Created4V3;

import src.DatConRecs.GpsGroup;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.RecSpec;

public class GPS_2097 extends GpsGroup {

    public GPS_2097(ConvertDat convertDat) {
        super(convertDat, 1, 2097, 66);
    }

//    public RecSpec.RecType getRecType() {
//        return RecSpec.RecType.BINARY;
//    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
