package src.DatConRecs.Created4V1;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.RecSpec;
import src.Files.Signal;
import src.Files.Units;

public class GPS_GLNS68_5 extends GpsGroup {

    private int _length = -68;

    public GPS_GLNS68_5(ConvertDat convertDat) {
        super(convertDat, "", 5, 68);
    }

    public int getLength() {
        return _length;
    }

    public RecSpec.RecType getRecType() {
        return RecSpec.RecType.BINARY;
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);

    }

}
