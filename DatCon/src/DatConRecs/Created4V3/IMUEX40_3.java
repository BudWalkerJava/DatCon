package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class IMUEX40_3 extends IMUEX40 {

    public IMUEX40_3(ConvertDat convertDat) {
        super(convertDat, 3, 40, 0);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
    }

}
