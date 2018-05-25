package src.DatConRecs.Created4V3;

import src.DatConRecs.IMUEX;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class IMUEX40 extends IMUEX {

    public IMUEX40(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length, index);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            vo_vx = _payload.getFloat(0);
            vo_vy = _payload.getFloat(4);
            vo_vz = _payload.getFloat(8);
            vo_px = _payload.getFloat(12);
            vo_py = _payload.getFloat(16);
            vo_pz = _payload.getFloat(20);
            us_v = _payload.getFloat(24);
            us_p = _payload.getFloat(28);
            vo_flag_navi = _payload.getUnsignedShort(32);
            flag_err = _payload.getUnsignedShort(34);
            vo_flag_rsv = _payload.getUnsignedShort(36);
            ex_cnt = _payload.getUnsignedShort(38);
            errString = getErrString(flag_err);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        super.printCols(lineT);
    }
}
