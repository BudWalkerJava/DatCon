package src.DatConRecs.Created4V3;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.RecSpec;
import src.Files.Signal;
import src.Files.Units;

public class RecRCStat17_1700 extends Record {
    protected boolean valid = false;

    private int _length = -1;

    protected int cur_cmd = (int) 0;

    protected short fail_safe = (short) 0;

    protected short vedio_lost = (short) 0;

    protected short data_lost = (short) 0;

    protected short app_lost = (short) 0;

    protected short frame_lost = (short) 0;

    protected long rec_cnt = (long) 0;

    protected short sky_con = (short) 0;

    protected short gnd_con = (short) 0;

    protected short connected = (short) 0;

    protected short m_changed = (short) 0;

    protected short arm_status = (short) 0;

    protected short wifi_en = (short) 0;

    protected short in_wifi = (short) 0;

    public RecRCStat17_1700(ConvertDat convertDat) {
        super(convertDat, 1700, 17);
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
        try {
            valid = true;

            cur_cmd = _payload.getUnsignedShort(0);
            fail_safe = _payload.getUnsignedByte(2);
            vedio_lost = _payload.getUnsignedByte(3);
            data_lost = _payload.getUnsignedByte(4);
            app_lost = _payload.getUnsignedByte(5);
            frame_lost = _payload.getUnsignedByte(6);
            rec_cnt = _payload.getUnsignedInt(7);
            sky_con = _payload.getUnsignedByte(11);
            gnd_con = _payload.getUnsignedByte(12);
            connected = _payload.getUnsignedByte(13);
            m_changed = _payload.getUnsignedByte(14);
            arm_status = _payload.getUnsignedByte(15);
            wifi_en = _payload.getUnsignedByte(16);
            in_wifi = _payload.getUnsignedByte(17);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    protected static Signal rc_debug_infoIntSig = Signal
            .SeriesIntExperimental("rc_debug_info", "", null, Units.noUnits);

    protected static Signal rc_debug_infoFloatSig = Signal
            .SeriesFloatExperimental("rc_debug_info", "", null, Units.noUnits);

    protected static Signal rc_debug_infoDoubleSig = Signal
            .SeriesDoubleExperimental("rc_debug_info", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(cur_cmd, rc_debug_infoIntSig, "cur_cmd", lineT,
                    valid);
            printCsvValue(fail_safe, rc_debug_infoIntSig, "fail_safe", lineT,
                    valid);
            printCsvValue(vedio_lost, rc_debug_infoIntSig, "vedio_lost", lineT,
                    valid);
            printCsvValue(data_lost, rc_debug_infoIntSig, "data_lost", lineT,
                    valid);
            printCsvValue(app_lost, rc_debug_infoIntSig, "app_lost", lineT,
                    valid);
            printCsvValue(frame_lost, rc_debug_infoIntSig, "frame_lost", lineT,
                    valid);
            printCsvValue(rec_cnt, rc_debug_infoIntSig, "rec_cnt", lineT,
                    valid);
            printCsvValue(sky_con, rc_debug_infoIntSig, "sky_con", lineT,
                    valid);
            printCsvValue(gnd_con, rc_debug_infoIntSig, "gnd_con", lineT,
                    valid);
            printCsvValue(connected, rc_debug_infoIntSig, "connected", lineT,
                    valid);
            printCsvValue(m_changed, rc_debug_infoIntSig, "m_changed", lineT,
                    valid);
            printCsvValue(arm_status, rc_debug_infoIntSig, "arm_status", lineT,
                    valid);
            printCsvValue(wifi_en, rc_debug_infoIntSig, "wifi_en", lineT,
                    valid);
            printCsvValue(in_wifi, rc_debug_infoIntSig, "in_wifi", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
