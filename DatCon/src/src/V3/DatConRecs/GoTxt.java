/* Record42_12 class

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
package src.V3.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class GoTxt extends Record {

    // 10 hz
    // length 57
    public GoTxt(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 21;
        _subType = 12;

    }

    public static GoTxt current = null;

    double longitude = 0.0;

    double latitude = 0.0;

    public float height = 0f; // height above HP meters

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
        flycCommand = (byte) (0x7F & (payloadBB.get(31)));
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
        Record.FLYC_COMMAND fc = Record.FLYC_COMMAND.find(flycCommand);
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

    private static Signal lowVoltageSig = Signal.State("lowVoltage",
            "Low Voltage", "");

    private static Signal rcModeSwitchSig = Signal.State("rcModeSwitch",
            "RC Mode Switch", "P");

    @Override
    public void printCols(lineType lineT) {
        try {
            setStateStrings();
            printCsvValue(flyc_state, "flycState", lineT, valid);
            printCsvValue(FLCS, flyCStateSig, "", lineT, valid);
            printCsvValue(FLYCCOMMAND, flyCCommandSig, "", lineT, valid);
            printCsvValue(FLACTION, flightActionSig, "", lineT, valid);
            printCsvValue(NGPE, nonGPSCauseSig, "", lineT, valid);
            //printCsvValue("compassError", compassErrorSig, lineT, valid);
            printCsvValue(ConnectedToRCString, connectedToRCSig, "", lineT,
                    valid);
            printCsvValue(LowVoltage, lowVoltageSig, "", lineT, valid);
            printCsvValue(ModeSwitch, rcModeSwitchSig, "", lineT, true);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
