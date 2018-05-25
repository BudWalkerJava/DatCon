package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.RecIMU;
import src.Files.ConvertDat;

public class IMU120_2050 extends RecIMU {
    public IMU120_2050(ConvertDat convertDat) {
        super(convertDat, 2050, 120, 2);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
