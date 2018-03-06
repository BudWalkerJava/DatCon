package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Persist;

public class console_52719 extends Record {
    String text = "";

    public console_52719(ConvertDat convertDat) {
        super(convertDat, 52719, -1);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            String payloadString = _payload.getString();
            if (Persist.EXPERIMENTAL_DEV) {
                System.out.println("console_52719 " + payloadString);
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
