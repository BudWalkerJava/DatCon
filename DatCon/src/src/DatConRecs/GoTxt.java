package src.DatConRecs;

import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

// Stuff that ends up in the .txt file produced by the Go App

public class GoTxt extends Record {

    public static GoTxt current = null;

    double longitude = 0.0;

    double latitude = 0.0;

    public float height = 0f; // height above HP meters

    public int flightTime = 0;

    public float roll = 0;

    public float pitch = 0;

    public float yaw = 0;

    protected ConvertDat convertDat = null;

    public byte failure = 0x00;

    public byte flyc_state = 0x00;

    public byte nonGPSError = 0x00;

    public byte flight_action = 0x00;

    public String NGPE = "";

    public boolean valid = false;

    public String FLCS = "";

    public int connectedToRC = 0x80; // 0x80 not connected, 0x00 connected

    public String FLACTION = "";

    private int word32 = 0;

    public int gpsLevel = 0;

    public boolean compassError = false;

    public boolean waveWork = false;

    public float vpsHeight = 0;

    public boolean waveError = false;

    public int voltageWarning = 0;

    public int rcModeChannel = 0;

    public String ModeSwitch = "NONE";

    public int dwflyState = 0;

    // 10 hz
    // length 57
    public void process(Payload _payload) {
        super.process(_payload);
        payloadBB = _payload.getBB();
        valid = true;
        longitude = Math.toDegrees(payloadBB.getDouble(0));
        latitude = Math.toDegrees(payloadBB.getDouble(8));
        height = ((float) (payloadBB.getShort(16))) / 10f;
        pitch = ((float) (payloadBB.getShort(24))) / 10f;
        roll = ((float) (payloadBB.getShort(26))) / 10f;
        yaw = ((float) (payloadBB.getShort(28))) / 10f;
        flyc_state = (byte) (0x7F & (payloadBB.get(30)));
        flight_action = (byte) (payloadBB.get(37));
        connectedToRC = (0x80 & (payloadBB.get(30)));
        word32 = payloadBB.getInt(32);
        gpsLevel = (word32 >>> 18) & 0xF;
        compassError = ((word32 & 0x10000) != 0);
        waveWork = ((word32 & 0x10) != 0);
        waveError = ((word32 & 0x20000) != 0);
        voltageWarning = (word32 & 0x600) >>> 9;
        rcModeChannel = (word32 & 0x6000) >>> 13;
        boolean outOfLimit = ((word32 >>> 31 & 1) == 1);
        failure = payloadBB.get(38); // possible linked to
        nonGPSError = (byte) (0x07 & (payloadBB.get(39)));
        vpsHeight = ((float) (0xff & payloadBB.get(41))) / 10.0f;
        flightTime = payloadBB.getShort(42) * 100;
        convertDat.processCoords(longitude, latitude, height);
    }

    public void setStateStrings() {
        Record.NON_GPS_CAUSE ngc = Record.NON_GPS_CAUSE.find(nonGPSError);
        NGPE = ngc.name();
        Record.FLYC_STATE fs = Record.FLYC_STATE.find(flyc_state);
        FLCS = fs.name();
        Record.FLIGHT_ACTION fa = Record.FLIGHT_ACTION.find(flight_action);
        FLACTION = fa.name();
        ModeSwitch = "P";
        if (rcModeChannel == 1) {
            ModeSwitch = "Sport";
        }
    }

    @Override
    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called");
    }

}
