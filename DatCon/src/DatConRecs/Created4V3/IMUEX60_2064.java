package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class IMUEX60_2064 extends IMUEX60 {

    public IMUEX60_2064(ConvertDat convertDat) {
        super(convertDat, 2064, 60, 0);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
    }

}
