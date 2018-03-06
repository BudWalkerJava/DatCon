package src.DatConRecs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class GpsGroup extends Record {
    private String suffix = "";

    protected boolean valid = false;

    static DateFormat df = null;

    static Calendar dateTime = null;

    public GpsGroup(ConvertDat convertDat, String suffix, int id, int length) {
        super(convertDat, id, length);
        this.suffix = suffix;
        dateTime = new Calendar.Builder()
                .setTimeZone(TimeZone.getTimeZone("UTC")).build();
        df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setCalendar(dateTime);
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

    protected static Signal gpsCoordSig = Signal.SeriesDouble("GPS", "", null,
            Units.gpsCoord);

    protected static Signal gpsDateTimeSig = Signal.SeriesInt("GPS", "", null,
            Units.noUnits);

    protected static Signal gpsHeightMSLSig = Signal
            .SeriesFloat("GPS:heightMSL", "", null, Units.meters);

    protected static Signal gpsDopSig = Signal.SeriesFloat("GPS:DOP", "", null,
            Units.noUnits);

    protected static Signal gpsAccSig = Signal.SeriesFloat("GPS:Acc", "", null,
            Units.noUnits);

    protected static Signal gpsNumSVSig = Signal.SeriesInt("GPS:Visible", "",
            null, Units.noUnits);

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            date = _payload.getUnsignedInt(0);
            time = _payload.getUnsignedInt(4);
            LongP = ((double) _payload.getInt(8)) / 1.0E7;
            LatP = ((double) _payload.getInt(12)) / 1.0E7;
            heightMSL = ((float) _payload.getInt(16)) / 1000.0f;
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
            printCsvValue(LongP, gpsCoordSig, "Long" + suffix, lineT, valid);
            printCsvValue(LatP, gpsCoordSig, "Lat" + suffix, lineT, valid);
            printCsvValue(date, gpsDateTimeSig, "Date" + suffix, lineT, valid);
            printCsvValue(time, gpsDateTimeSig, "Time" + suffix, lineT, valid);
            printCsvValue(dtS, "GPS:dateTimeStamp", lineT, dopValid);
            printCsvValue(heightMSL, gpsHeightMSLSig, "" + suffix, lineT,
                    dopValid);
            printCsvValue(hdop, gpsDopSig, "H" + suffix, lineT, dopValid);
            printCsvValue(pdop, gpsDopSig, "P" + suffix, lineT, dopValid);
            //printCsvValue(hacc, gpsAccSig, "H" + suffix, lineT, valid);
            printCsvValue(sacc, gpsAccSig, "S" + suffix, lineT, dopValid);
            //printCsvValue(numSV, gpsNumSVSig, "" + suffix, lineT, valid);
            printCsvValue(numGPS, gpsNumSVSig, "GPS" + suffix, lineT, valid);
            printCsvValue(numGLN, gpsNumSVSig, "GLNAS" + suffix, lineT, valid);
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
