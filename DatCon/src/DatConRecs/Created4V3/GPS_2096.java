
package src.DatConRecs.Created4V3;

import src.DatConRecs.GpsGroup;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class GPS_2096 extends GpsGroup {

    public GPS_2096(ConvertDat convertDat) {
        super(convertDat, 0, 2096, 66);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
