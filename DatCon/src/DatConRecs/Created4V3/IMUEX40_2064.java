package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class IMUEX40_2064 extends IMUEX40 {

    public IMUEX40_2064(ConvertDat convertDat) {
        super(convertDat, 2064, 40, 0);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
    }

}
