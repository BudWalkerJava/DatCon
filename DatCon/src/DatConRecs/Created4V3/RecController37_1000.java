package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class RecController37_1000 extends RecController {
    public RecController37_1000(ConvertDat convertDat) {
        super(convertDat, 1000, 37);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        elevator = (short) payloadBB.getShort(4);
        aileron = (short) payloadBB.getShort(6);
        rudder = (short) payloadBB.getShort(8);
        throttle = (short) payloadBB.getShort(10);
        sticksValid = true;
    }
}
