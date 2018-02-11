package src.DatConRecs.FromViewer;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class drv_log_65530 extends Record {
    String text = "";

    public drv_log_65530(ConvertDat convertDat) {
        super(convertDat, 65530, -1);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {

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
