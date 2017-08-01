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
package src.V1.DatConRecs;

import src.Files.ConvertDat;

public class GoTxt extends src.V3.DatConRecs.GoTxt {

    public GoTxt(ConvertDat convertDat) {
        super(convertDat);
        _type = 42;
        _subType = 12;
    }
}

//public class GoTxt extends Record {
//
//    public static GoTxt current = null;
//
//    double longitude = 0.0;
//
//    double latitude = 0.0;
//
//    public float height = 0f; // height above HP meters
//
//    public int flightTime = 0;
//
//    public float roll = 0;
//
//    public float pitch = 0;
//
//    public float yaw = 0;
//
//    private ConvertDat convertDat = null;
//
//    public byte failure = 0x00;
//
//    public byte flyc_state = 0x00;
//
//    public byte nonGPSError = 0x00;
//
//    public String NGPE = "";
//
//    public int dwflyState = 0;
//
//    public String FLCS = "";
//
//    public int connectedToRC = 0x80; // 0x80 not connected, 0x00 connected
//
//    public boolean gpsCoordsOK;
//
//    public float absoluteHeight = 0.0f;
//
//    public boolean absoluteHeightValid = false;
//
//    // 10 hz
//    // length 52
//
//    public GoTxt(ConvertDatV1 convertDat) {
//        super(convertDat);
//        current = this;
//        _type = 42;
//        _subType = 12;
//        this.convertDat = convertDat;
//    }
//
//    public void process(Payload _payload) {
//        super.process(_payload);
//        payloadBB = _payload.getBB();
//        longitude = Math.toDegrees(payloadBB.getDouble(0));
//        latitude = Math.toDegrees(payloadBB.getDouble(8));
//        height = ((float) (payloadBB.getShort(16))) / 10f;
//        if (!gpsCoordsOK) {
//            gpsCoordsOK = (longitude != 0.0 && latitude != 0.0
//                    && height != 0.0f);
//        }
//        pitch = ((float) (payloadBB.getShort(24))) / 10f;
//        roll = ((float) (payloadBB.getShort(26))) / 10f;
//        yaw = ((float) (payloadBB.getShort(28))) / 10f;
//        flyc_state = (byte) (0x7F & (payloadBB.get(30)));
//        connectedToRC = (0x80 & (payloadBB.get(30)));
//        failure = payloadBB.get(38); // possible linked to
//                                     // MOTOR_START_FAILED_CAUSE in osd.js
//        nonGPSError = (byte) (0x07 & (payloadBB.get(39)));
//        flightTime = payloadBB.getShort(42) * 100;
//        convertDat.processCoords(longitude, latitude, height);
//        if (convertDat.kmlType >= 0
//                && convertDat.tickRangeLower <= _payload.getTickNo()) {
//            if (gpsCoordsOK) {
//                float alt = height;
//                if (convertDat.kmlType == 1) {
//                    alt += convertDat.homePointElevation;
//                    absoluteHeight = alt;
//                    absoluteHeightValid = true;
//                }
//                convertDat.kmlPS.println("              " + longitude + ","
//                        + latitude + "," + alt);
//            }
//        }
//    }
//
//    public void setStateStrings() {
//        switch (nonGPSError) {
//        case 1:
//            NGPE = "FORBIN";
//            break;
//        case 2:
//            NGPE = "GPSNUM_NONENOUGH";
//            break;
//        case 3:
//            NGPE = "GPS_HDOP_LARGE";
//            break;
//        case 4:
//            NGPE = "GPS_POSITION_NON_MATCH";
//            break;
//        case 5:
//            NGPE = "SPEED_ERROR_LARGE";
//            break;
//        case 6:
//            NGPE = "YAW_ERROR_LARGE";
//            break;
//        case 7:
//            NGPE = "COMPASS_ERROR_LARGE";
//            break;
//        }
//
//        switch (flyc_state) {
//        case 0:
//            FLCS = "MANUAL";
//            dwflyState = 1;
//            break;
//        case 1:
//            FLCS = "ATTI";
//            dwflyState = 2;
//            break;
//        case 2:
//            FLCS = "ATTI_CL";
//            dwflyState = 3;
//            break;
//        case 3:
//            FLCS = "ATTI_HOVER";
//            dwflyState = 4;
//            break;
//        case 4:
//            FLCS = "HOVER";
//            dwflyState = 5;
//            break;
//        case 5:
//            FLCS = "GSP_BLAKE";
//            dwflyState = 6;
//            break;
//        case 6:
//            FLCS = "GPS_ATTI";
//            dwflyState = 7;
//            break;
//        case 7:
//            FLCS = "GPS_CL";
//            dwflyState = 8;
//            break;
//        case 8:
//            FLCS = "GPS_HOME_LOCK";
//            dwflyState = 9;
//            break;
//        case 9:
//            FLCS = "GPS_HOT_POINT";
//            dwflyState = 20;
//            break;
//        case 10:
//            FLCS = "ASSISTED_TAKEOFF";
//            dwflyState = 30;
//            break;
//        case 11:
//            FLCS = "AUTO_TAKEOFF";
//            dwflyState = 40;
//            break;
//        case 12:
//            FLCS = "AUTO_LANDING";
//            dwflyState = 50;
//            break;
//        case 13:
//            FLCS = "ATTI_LANDING";
//            dwflyState = 60;
//            break;
//        case 14:
//            FLCS = "NAVI_GO";
//            dwflyState = 70;
//            break;
//        case 15:
//            FLCS = "GO_HOME";
//            dwflyState = 80;
//            break;
//        case 16:
//            FLCS = "CLICK_GO";
//            dwflyState = 90;
//            break;
//        case 17:
//            FLCS = "JOYSTICK";
//            dwflyState = 200;
//            break;
//        case 23:
//            FLCS = "ATTI_LIMITED";
//            dwflyState = 300;
//            break;
//        case 24:
//            FLCS = "GPS_ATTI_LIMITED";
//            dwflyState = 400;
//            break;
//        case 25:
//            FLCS = "FOLLOW_ME";
//            dwflyState = 500;
//            break;
//        case 100:
//            FLCS = "OTHER";
//            dwflyState = 600;
//            break;
//        }
//    }
//}
