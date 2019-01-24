package src.DatConRecs.String;

import java.nio.ByteBuffer;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class Sys_cfg_65535 extends Record {

    ByteBuffer payload = null;

    protected String payloadString;

    public Sys_cfg_65535(ConvertDat convertDat) {
        super(convertDat, 65535, -1);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            payload = _payload.getBB();
            payloadString = _payload.getString();
            if (convertDat.cloPS != null) {
                if (payloadString.length() > 0) {
                    convertDat.cloPS.println(_payload.getCleanString());
                }
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
