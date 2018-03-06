package src.DatConRecs;

import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.RecSpec;
import src.Files.Signal;
import src.Files.Units;

// Stuff that ends up in the .txt file produced by the Go App

public class GoTxt50_12 extends Record {

    public static enum NON_GPS_CAUSE {
        ALREADY(0), FORBIN(1), GPSNUM_NONENOUGH(2), GPS_HDOP_LARGE(
                3), GPS_POSITION_NONMATCH(4), SPEED_ERROR_LARGE(
                        5), YAW_ERROR_LARGE(
                                6), COMPASS_ERROR_LARGE(7), UNKNOWN(8);

        private int data = 0;

        private NON_GPS_CAUSE(int n3) {
            this.data = n3;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int n2) {
            return this.data == n2;
        }

        public static NON_GPS_CAUSE find(int n2) {
            NON_GPS_CAUSE nON_GPS_CAUSE = UNKNOWN;
            for (int i2 = 0; i2 < NON_GPS_CAUSE.values().length; ++i2) {
                if (!NON_GPS_CAUSE.values()[i2]._equals(n2))
                    continue;
                nON_GPS_CAUSE = NON_GPS_CAUSE.values()[i2];
                break;
            }
            return nON_GPS_CAUSE;
        }
    }

    public static enum FLIGHT_ACTION {
        NONE(0), WARNING_POWER_GOHOME(1), WARNING_POWER_LANDING(
                2), SMART_POWER_GOHOME(3), SMART_POWER_LANDING(
                        4), LOW_VOLTAGE_LANDING(5), LOW_VOLTAGE_GOHOME(
                                6), SERIOUS_LOW_VOLTAGE_LANDING(
                                        7), RC_ONEKEY_GOHOME(
                                                8), RC_ASSISTANT_TAKEOFF(
                                                        9), RC_AUTO_TAKEOFF(
                                                                10), RC_AUTO_LANDING(
                                                                        11), APP_AUTO_GOHOME(
                                                                                12), APP_AUTO_LANDING(
                                                                                        13), APP_AUTO_TAKEOFF(
                                                                                                14), OUTOF_CONTROL_GOHOME(
                                                                                                        15), API_AUTO_TAKEOFF(
                                                                                                                16), API_AUTO_LANDING(
                                                                                                                        17), API_AUTO_GOHOME(
                                                                                                                                18), AVOID_GROUND_LANDING(
                                                                                                                                        19), AIRPORT_AVOID_LANDING(
                                                                                                                                                20), TOO_CLOSE_GOHOME_LANDING(
                                                                                                                                                        21), TOO_FAR_GOHOME_LANDING(
                                                                                                                                                                22), APP_WP_MISSION(
                                                                                                                                                                        23), WP_AUTO_TAKEOFF(
                                                                                                                                                                                24), GOHOME_AVOID(
                                                                                                                                                                                        25), GOHOME_FINISH(
                                                                                                                                                                                                26), VERT_LOW_LIMIT_LANDING(
                                                                                                                                                                                                        27), BATTERY_FORCE_LANDING(
                                                                                                                                                                                                                28), MC_PROTECT_GOHOME(
                                                                                                                                                                                                                        29);

        private int _value = 0;

        private FLIGHT_ACTION(int paramInt) {
            this._value = paramInt;
        }

        public int value() {
            return this._value;
        }

        public boolean belongsTo(int paramInt) {
            return this._value == paramInt;
        }

        public static FLIGHT_ACTION find(int paramInt) {
            FLIGHT_ACTION localObject = NONE;
            for (FLIGHT_ACTION localFLIGHT_ACTION : values()) {
                if (localFLIGHT_ACTION.belongsTo(paramInt)) {
                    localObject = localFLIGHT_ACTION;
                    break;
                }
            }
            return localObject;
        }
    }

    public static enum FLYC_STATE {
        Manual(0), //
        Atti(1), //
        Atti_CL(2), Atti_Hover(3), Hover(4), GPS_Blake(5), GPS_Atti(6), GPS_CL(
                7), GPS_HomeLock(8), GPS_HotPoint(9), AssitedTakeoff(
                        10), AutoTakeoff(11), AutoLanding(12), AttiLangding(
                                13), NaviGo(14), GoHome(15), ClickGo(
                                        16), Joystick(17), Atti_Limited(
                                                23), GPS_Atti_Limited(
                                                        24), NaviMissionFollow(
                                                                25), NaviSubMode_Tracking(
                                                                        26), NaviSubMode_Pointing(
                                                                                27), PANO(
                                                                                        28), Farming(
                                                                                                29), FPV(
                                                                                                        30), SPORT(
                                                                                                                31), NOVICE(
                                                                                                                        32), FORCE_LANDING(
                                                                                                                                33), TERRAIN_TRACKING(
                                                                                                                                        35), NAVI_ADV_GOHOME(
                                                                                                                                                36), NAVI_ADV_LANDING(
                                                                                                                                                        37), TRIPOD_GPS(
                                                                                                                                                                38), TRACK_HEADLOCK(
                                                                                                                                                                        39), ASST_TAKEOFF(
                                                                                                                                                                                41), GENTLE_GPS(
                                                                                                                                                                                        43), OTHER(
                                                                                                                                                                                                100);

        private int data;

        private FLYC_STATE(int n3) {
            this.data = n3;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int n2) {
            return this.data == n2;
        }

        public static FLYC_STATE find(int n2) {
            FLYC_STATE fLYC_STATE = OTHER;
            for (int i2 = 0; i2 < FLYC_STATE.values().length; ++i2) {
                if (!FLYC_STATE.values()[i2]._equals(n2))
                    continue;
                fLYC_STATE = FLYC_STATE.values()[i2];
                break;
            }
            return fLYC_STATE;
        }
    }

    public static enum FLYC_COMMAND {
        AUTO_FLY(1), AUTO_LANDING(2), HOMEPOINT_NOW(3), HOMEPOINT_HOT(
                4), HOMEPOINT_LOC(5), GOHOME(6), START_MOTOR(7), STOP_MOTOR(
                        8), Calibration(9), DeformProtecClose(
                                10), DeformProtecOpen(11), DropGohome(
                                        12), DropTakeOff(13), DropLanding(
                                                14), DynamicHomePointOpen(
                                                        15), DynamicHomePointClose(
                                                                16), FollowFunctioonOpen(
                                                                        17), FollowFunctionClose(
                                                                                18), IOCOpen(
                                                                                        19), IOCClose(
                                                                                                20), DropCalibration(
                                                                                                        21), PackMode(
                                                                                                                22), UnPackMode(
                                                                                                                        23), EnterManaualMode(
                                                                                                                                24), StopDeform(
                                                                                                                                        25), DownDeform(
                                                                                                                                                28), UpDeform(
                                                                                                                                                        29), ForceLanding(
                                                                                                                                                                30), ForceLanding2(
                                                                                                                                                                        31), OTHER(
                                                                                                                                                                                100);

        private int data;

        private FLYC_COMMAND(int n3) {
            this.data = n3;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int n2) {
            return this.data == n2;
        }

        public static FLYC_COMMAND find(int n2) {
            FLYC_COMMAND fLYC_COMMAND = OTHER;
            for (int i2 = 0; i2 < FLYC_COMMAND.values().length; ++i2) {
                if (!FLYC_COMMAND.values()[i2]._equals(n2))
                    continue;
                fLYC_COMMAND = FLYC_COMMAND.values()[i2];
                break;
            }
            return fLYC_COMMAND;
        }
    }

    public static GoTxt50_12 current = null;

    public GoTxt50_12(ConvertDat convertDat) {
        super(convertDat, 12, 50);
        current = this;
    }

    public RecSpec.RecType getRecType() {
        return RecSpec.RecType.BINARY;
    }

    double longitude = 0.0;

    double latitude = 0.0;

    public float relativeHeight = 0f; // height above HP meters

    public int flightTime = 0;

    public float roll = 0;

    public float pitch = 0;

    public float yaw = 0;

    public byte failure = 0x00;

    public byte flyc_state = 0x00;

    public byte flycCommand = 0x00;

    public byte nonGPSError = 0x00;

    public byte flight_action = 0x00;

    public String NGPE = "";

    public boolean valid = false;

    public String FLCS = "";

    public int connectedToRC = 0x80; // 0x80 not connected, 0x00 connected

    public String ConnectedToRCString = "Connected";

    public String FLACTION = "";

    public String FLYCCOMMAND = "";

    private int word32 = 0;

    public int gpsLevel = 0;

    public boolean compassError = false;

    public boolean waveWork = false;

    public float vpsHeight = 0;

    public boolean waveError = false;

    public int voltageWarning = 0;

    String LowVoltage = "";

    public int rcModeChannel = 0;

    public String ModeSwitch = "NONE";

    public int dwflyState = 0;

    private boolean gpsUsed = false;

    private boolean visionUsed = false;

    private String gpsUsedString = "False";

    private String visionUsedString = "False";

    public void process(Payload _payload) {
        super.process(_payload);
        payloadBB = _payload.getBB();
        valid = true;
        longitude = Math.toDegrees(payloadBB.getDouble(0));
        latitude = Math.toDegrees(payloadBB.getDouble(8));
        relativeHeight = ((float) (payloadBB.getShort(16))) / 10f;
        pitch = ((float) (payloadBB.getShort(24))) / 10f;
        roll = ((float) (payloadBB.getShort(26))) / 10f;
        yaw = ((float) (payloadBB.getShort(28))) / 10f;
        flyc_state = (byte) (0x7F & (payloadBB.get(30)));
        flycCommand = (byte) (0x7F & (payloadBB.get(31)));
        flight_action = (byte) (payloadBB.get(37));
        connectedToRC = (0x80 & (payloadBB.get(30)));
        word32 = payloadBB.getInt(32);
        gpsLevel = (word32 >>> 18) & 0xF;
        compassError = ((word32 & 0x10000) != 0);
        waveWork = ((word32 & 0x10) != 0);
        gpsUsed = ((word32 & 0x8000) != 0);
        visionUsed = ((word32 & 0x100) != 0);
        waveError = ((word32 & 0x20000) != 0);
        voltageWarning = (word32 & 0x600) >>> 9;
        rcModeChannel = (word32 & 0x6000) >>> 13;
        failure = payloadBB.get(38); // possible linked to
        nonGPSError = (byte) (0x07 & (payloadBB.get(39)));
        vpsHeight = ((float) (0xff & payloadBB.get(41))) / 10.0f;
        flightTime = payloadBB.getShort(42) * 100;
        convertDat.processCoords(longitude, latitude, relativeHeight);
    }

    public void setStateStrings() {
        NON_GPS_CAUSE ngc = NON_GPS_CAUSE.find(nonGPSError);
        NGPE = ngc.name();
        GoTxt50_12.FLYC_STATE fs = GoTxt50_12.FLYC_STATE.find(flyc_state);
        FLCS = fs.name();
        GoTxt50_12.FLIGHT_ACTION fa = GoTxt50_12.FLIGHT_ACTION.find(flight_action);
        FLACTION = fa.name();
        GoTxt50_12.FLYC_COMMAND fc = GoTxt50_12.FLYC_COMMAND.find(flycCommand);
        FLYCCOMMAND = fc.name();
        ModeSwitch = "P";
        if (rcModeChannel == 1) {
            ModeSwitch = "Sport";
        }
        ConnectedToRCString = "Connected";
        if (connectedToRC != 0) {
            ConnectedToRCString = "DisConnected";
        }
        LowVoltage = "";
        if (voltageWarning != 0) {
            LowVoltage = "low";
        }
        gpsUsedString = "False";
        if (gpsUsed) {
            gpsUsedString = "True";
        }
        visionUsedString = "False";
        if (visionUsed) {
            visionUsedString = "True";
        }
    }

    public static Signal flightTimeSig = Signal.SeriesInt("flightTime",
            "Flight Time", null, Units.msec);

    public static Signal gpsHealthSig = Signal.SeriesInt("gpsHealth",
            "GPS Health", null, Units.gpsHealth);

    public static Signal vpsHeightSig = Signal.SeriesDouble("vpsHeight",
            "vps Height", null, Units.meters);

    public static Signal relativeHeightSig = Signal.SeriesDouble(
            "relativeHeight", "Height above Launch HomePoint", null,
            Units.meters);

    private static Signal flyCStateSig = Signal.State("flyCState", "FlyCState",
            "GPS_Atti");

    private static Signal flyCCommandSig = Signal.State("flycCommand",
            "FlycCommand", "AUTO_FLY");

    private static Signal flightActionSig = Signal.State("flightAction",
            "Flight Action", "NONE");

    private static Signal nonGPSCauseSig = Signal.State("nonGPSCause",
            "nonGPSCause", "ALREADY");

    private static Signal connectedToRCSig = Signal.State("connectedToRC",
            "Connected to RC", "Connected");

    private static Signal lowVoltageSig = Signal.State("Battery:lowVoltage",
            "Low Voltage", "");

    private static Signal rcModeSwitchSig = Signal.State("RC:ModeSwitch",
            "RC Mode Switch", "P");

    private static Signal gpsUsedSig = Signal.State("gpsUsed",
            "GPS Used for Horizontal Speed", "True");

    private static Signal visionUsedSig = Signal.State("visionUsed",
            "Vision Used for Horizontal Speed", "True");

    //    private static Signal attExpSig = Signal.SeriesFloatExperimental(
    //            "attitudeExperimental", "AttitudeExp", null, Units.degrees);

    @Override
    public void printCols(lineType lineT) {
        try {
            setStateStrings();
            //printCsvValue(flyc_state, "flycState", lineT, valid);
            printCsvValue(FLCS, flyCStateSig, "", lineT, valid);
            printCsvValue(FLYCCOMMAND, flyCCommandSig, "", lineT, valid);
            printCsvValue(FLACTION, flightActionSig, "", lineT, valid);
            printCsvValue(NGPE, nonGPSCauseSig, "", lineT, valid);
            //printCsvValue("compassError", compassErrorSig, lineT, valid);
            printCsvValue(ConnectedToRCString, connectedToRCSig, "", lineT,
                    valid);
            printCsvValue(LowVoltage, lowVoltageSig, "", lineT, valid);
            printCsvValue(ModeSwitch, rcModeSwitchSig, "", lineT, true);
            printCsvValue(gpsUsedString, gpsUsedSig, "", lineT, valid);
            printCsvValue(visionUsedString, visionUsedSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
