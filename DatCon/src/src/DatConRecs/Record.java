/* RecordType class

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that redistribution of source code include
the following disclaimer in the documentation and/or other materials provided
with the distribution.

THIS SOFTWARE IS PROVIDED BY ITS CREATOR "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE CREATOR OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package src.DatConRecs;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.CsvWriter;
import src.Files.DatFile;

public abstract class Record {

    protected ByteBuffer payloadBB = null;

    protected CsvWriter csvWriter = null;

    protected ConvertDat convertDat = null;

    protected DatFile _datFile;

    public enum cvsTermType {
        FLOAT4, DOUBLE, BYTE, SHORT
    };

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

    public static enum FLYC_STATE {
        Manual(0), Atti(1), Atti_CL(2), Atti_Hover(3), Hover(4), GPS_Blake(
                5), GPS_Atti(6), GPS_CL(7), GPS_HomeLock(8), GPS_HotPoint(
                        9), AssitedTakeoff(10), AutoTakeoff(11), AutoLanding(
                                12), AttiLangding(13), NaviGo(14), GoHome(
                                        15), ClickGo(16), Joystick(
                                                17), Atti_Limited(
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

    protected int _type = -1;

    protected int _subType = -1;

    static DecimalFormat df = new DecimalFormat("000.#############",
            new DecimalFormatSymbols(Locale.US));

    public Record() {
        throw new RuntimeException("Record() called");
    }

    public Record(ConvertDat convertDat) {
        this.convertDat = convertDat;
        _datFile = this.convertDat.getDatFile();
        this.csvWriter = convertDat.csvWriter;
    }

    public void process(Payload _record) {
        payloadBB = _record.getBB();
    }

    public boolean isType(int _Type, short _subType) {
        return (this._type == _Type && this._subType == _subType);
    }

    protected float maxVolt(float... floatVolts) {
        float retv = -Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts > retv) {
                retv = volts;
            }
        }
        return retv;
    }

    protected float minVolt(float... floatVolts) {
        float retv = Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts < retv) {
                retv = volts;
            }
        }
        return retv;
    }

    protected float minVolts(float[] volts) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] < min)
                min = volts[i];
        }
        return min;
    }

    protected float maxVolts(float[] volts) {
        float max = Float.MIN_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] > max)
                max = volts[i];
        }
        return max;
    }

    protected double computeThrustTheta(short lbSpeed, short rfSpeed,
            short rbSpeed, short lfSpeed) {
        double lbrfDiff = lbSpeed - rfSpeed;
        double rblfDiff = rbSpeed - lfSpeed;
        double thrust1 = Math.toDegrees(Math.atan2(lbrfDiff, rblfDiff));
        double thrust2 = (thrust1 + 315.0) % 360.0;
        double thrustTheta = thrust2;
        if (thrust2 > 180.0) {
            thrustTheta = thrust2 - 360.0;
        }
        return thrustTheta;
    }

    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called in Record");
    }

    protected void printCsvValue(Number value, Signal signal, String suffix,
            lineType lineT, boolean valid) throws IOException {
        if (lineT == lineType.XML) {
            printXmlSig(signal.getName(), suffix, signal);
            return;
        }
        if (ConvertDat.EXPERIMENTAL_FIELDS || !signal.isExperimental()) {
            if (lineT == lineType.HEADER) {
                csvWriter.print("," + signal.getName());
                if (suffix != "") {
                    csvWriter.print(":" + suffix);
                }
                if (convertDat.showUnits && signal.hasUnits()) {
                    csvWriter.print("[" + signal.getUnitsNoComma() + "]");
                }
            } else if (lineT == lineType.LINE) {
                csvWriter.print(",");
                if (valid)
                    csvWriter.print("" + value);
            }
        }
    }

    protected void printCsvValue(String value, Signal signal, String suffix,
            lineType lineT, boolean valid) throws IOException {
        if (lineT == lineType.XML) {
            printXmlSig(signal.getName(), suffix, signal);
            return;
        }
        if (ConvertDat.EXPERIMENTAL_FIELDS || !signal.isExperimental()) {
            if (lineT == lineType.HEADER) {
                csvWriter.print("," + signal.getName());
                if (suffix != "") {
                    csvWriter.print(":" + suffix);
                }
                if (convertDat.showUnits && signal.hasUnits()) {
                    csvWriter.print("[" + signal.getUnitsNoComma() + "]");
                }
            } else if (lineT == lineType.LINE) {
                csvWriter.print(",");
                if (valid)
                    csvWriter.print("" + value);
            }
        }
    }

    // Following won't create entries in XML file
    protected void printCsvValue(boolean value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid) {
                if (value) {
                    csvWriter.print("1");
                } else {
                    csvWriter.print("0");
                }
            }
        }
    }

    protected void printCsvValue(int value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    protected void printCsvValue(float value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    protected void printCsvValue(String value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    private void printXmlSig(String name, String suffix, Signal signal)
            throws IOException {
        String colName = name;
        String description;
        if (suffix != "") {
            colName = name + ":" + suffix;
        }
        switch (signal.getType()) {
        case SERIES:
            csvWriter.println("<series>");
            csvWriter.println("  <sigName>" + colName + "</sigName>");
            csvWriter.println("  <colName>" + colName + "</colName>");
            Axis axis = signal.getAxis();
            if (axis != null) {
                csvWriter.println("  <axis>" + axis.getName() + "</axis>");
                convertDat.axes.add(axis);
            }
            switch (signal.getNumType()) {
            case DOUBLE:
                csvWriter.println("  <numType>double</numType>");
                break;
            case FLOAT4:
                csvWriter.println("  <numType>float</numType>");
                break;
            case INT:
                csvWriter.println("  <numType>int</numType>");
                break;
            case UNDEFINED:
                break;
            default:
                break;
            }
            description = signal.getDescription();
            if (description != null) {
                csvWriter.println(
                        "  <description>" + description + "</description>");
            }
            if (signal.isExperimental()) {
                csvWriter.println("  <experimental>true</experimental>");
            }
            if (signal.hasUnits()) {
                csvWriter.println("  <units>" + signal.getUnits() + "</units>");
            }
            csvWriter.println("</series>");
            break;
        case STATE:
            csvWriter.println("<state>");
            csvWriter.println("  <sigName>" + colName + "</sigName>");
            csvWriter.println("  <colName>" + colName + "</colName>");
            csvWriter.println("  <inverse></inverse>");
            description = signal.getDescription();
            if (description != null) {
                csvWriter.println(
                        "  <description>" + description + "</description>");
            }
            csvWriter.println("  <stateSpec>");
            csvWriter.println("     <stateName>" + signal.getDefaultState()
                    + "</stateName>");
            csvWriter.println("     <color>white</color>");
            csvWriter.println("  </stateSpec>");
            csvWriter.println("</state>");
            break;
        case TIMEAXIS:
            break;
        case UNDEFINED:
            break;
        default:
            break;
        }
    }

    public void setCsvWriter(CsvWriter writer) {
        csvWriter = writer;
    }

    public void setType(int type) {
        _type = type;
    }

    public void setSubType(int subType) {
        _subType = subType;
    }
}
