package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.RecIMU;
import src.Files.ConvertDat;

public class IMU120_2049 extends RecIMU {
    public IMU120_2049(ConvertDat convertDat) {
        super(convertDat, 2049, 120, 1);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
