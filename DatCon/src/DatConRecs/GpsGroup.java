package src.DatConRecs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class GpsGroup extends Record {
    //    private String suffix = "";

    protected boolean valid = false;

    static DateFormat df = null;

    static Calendar dateTime = null;

    public GpsGroup(ConvertDat convertDat, int index, int id, int length) {
        super(convertDat, id, length);

        dateTime = new Calendar.Builder()
                .setTimeZone(TimeZone.getTimeZone("UTC")).build();
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setCalendar(dateTime);

        gpsCoordSig = Signal.SeriesDouble("GPS", index, "", null,
                Units.gpsCoord);

        gpsDateTimeSig = Signal.SeriesInt("GPS", index, "", null,
                Units.noUnits);

        gpsHeightMSLSig = Signal.SeriesFloat("GPS", index, "heightMSL", null,
                Units.meters);

        gpsDopSig = Signal.SeriesFloat("GPS", index, "DOP", null,
                Units.noUnits);

        gpsAccSig = Signal.SeriesFloat("GPS", index, "Acc", null,
                Units.noUnits);
        gpsNumSVSig = Signal.SeriesInt("GPS", index, "Visible", null,
                Units.noUnits);
        velocitySig = Signal.SeriesInt("GPS", index, "", null,
                Units.metersPerSec);
    }

    protected long date = 0;

    protected long time = 0;

    protected double LongP = 0.0;

    protected double LatP = 0.0;

    protected boolean dtValid = false;

    protected boolean dopValid = false;

    protected float heightMSL = 0;

    protected float hdop = 0.0f;

    protected float pdop = 0.0f;

    protected float hacc = 0.0f;

    protected float sacc = 0.0f;

    protected long numGPS = 0;

    protected long numGLN = 0;

    protected int numSV = 0;

    private float velN = 0.0f;;

    private float velE = 0.0f;;

    private float velD = 0.0f;;

    protected Signal gpsCoordSig = null;

    protected Signal gpsDateTimeSig = null;

    protected Signal gpsHeightMSLSig = null;

    protected Signal gpsDopSig = null;

    protected Signal gpsAccSig = null;

    protected Signal gpsNumSVSig = null;

    private Signal velocitySig = null;

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            date = _payload.getUnsignedInt(0);
            time = _payload.getUnsignedInt(4);
            LongP = ((double) _payload.getInt(8)) / 1.0E7;
            LatP = ((double) _payload.getInt(12)) / 1.0E7;
            heightMSL = ((float) _payload.getInt(16)) / 1000.0f;
            velN = _payload.getFloat(20) / 100.0f;
            velE = _payload.getFloat(24) / 100.0f;
            velD = _payload.getFloat(28) / 100.0f;
            hdop = _payload.getFloat(32);
            pdop = _payload.getFloat(36);
            hacc = _payload.getFloat(40);
            sacc = _payload.getFloat(44);
            numGPS = _payload.getUnsignedInt(56);
            numGLN = _payload.getUnsignedInt(60);
            numSV = _payload.getUnsignedShort(64);
            if (!dtValid && sacc < 8000.0f) {
                dtValid = true;
            }
            processDateTime();
            if (hdop < 1000.0 && pdop < 1000.0)
                dopValid = true;
            else
                dopValid = false;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {
            printCsvValue(LongP, gpsCoordSig, "Long", lineT, valid);
            printCsvValue(LatP, gpsCoordSig, "Lat", lineT, valid);
            printCsvValue(date, gpsDateTimeSig, "Date", lineT, valid);
            printCsvValue(time, gpsDateTimeSig, "Time", lineT, valid);
            printCsvValue(dtS, "GPS:dateTimeStamp", lineT, dopValid);
            printCsvValue(heightMSL, gpsHeightMSLSig, "heightMSL", lineT,
                    dopValid);
            printCsvValue(hdop, gpsDopSig, "hDOP", lineT, dopValid);
            printCsvValue(pdop, gpsDopSig, "pDOP", lineT, dopValid);
            //printCsvValue(hacc, gpsAccSig, "H" , lineT, valid);
            printCsvValue(sacc, gpsAccSig, "sAcc", lineT, dopValid);
            //printCsvValue(numSV, gpsNumSVSig, "" , lineT, valid);
            printCsvValue(numGPS, gpsNumSVSig, "numGPS", lineT, valid);
            printCsvValue(numGLN, gpsNumSVSig, "numGLNAS", lineT, valid);
            printCsvValue(numSV, gpsNumSVSig, "numSV", lineT, valid);
            printCsvValue(velN, velocitySig, "velN", lineT, valid);
            printCsvValue(velE, velocitySig, "velE", lineT, valid);
            printCsvValue(velD, velocitySig, "velD", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    private boolean dateTimeRecorded = false;

    private String dtS = "";

    protected void processDateTime() {
        int year = (int) (date / 10000);
        int resid = (int) (date - (year * 10000));
        int month = resid / 100;
        int day = resid - (month * 100);
        int hour = (int) (time / 10000);
        resid = (int) (time - (hour * 10000));
        int mins = resid / 100;
        int secs = resid - (mins * 100);
        if (!dateTimeRecorded && dopValid) {
            convertDat.addAttrValuePair("dateTime", year + "-" + month + "-"
                    + day + " " + hour + ":" + mins + ":" + secs + " GMT");
            dateTimeRecorded = true;
        }
        dateTime.set(year, month - 1, day, hour, mins, secs);
        dtS = df.format(dateTime.getTime());
    }
}
