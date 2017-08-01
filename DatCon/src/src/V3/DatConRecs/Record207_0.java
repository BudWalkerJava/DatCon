/* Record207 class

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

import java.io.IOException;

import src.DatConRecs.AxesAndSigs;
import src.DatConRecs.Axis;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Signal.NumType;
import src.DatConRecs.Signal.SigType;
import src.DatConRecs.Units;
import src.Files.DatConLog;
import src.Files.DatFile;
import src.Files.Quaternion;
import src.Files.Util;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.V3.Files.ConvertDatV3;
import src.V3.Files.DatFileV3;

public class Record207_0 extends Record {

    // 200 hZ
    // length 122

    public static Record207_0 current = null;

    public double longRad = 0.0;

    public double latRad = 0.0;

    public int numSats = 0;

    public float quatW = (float) 0.0;

    public float quatX = (float) 0.0;

    public float quatY = (float) 0.0;

    public float quatZ = (float) 0.0;

    public float baroRaw = (float) 0.0;

    public float accelX = (float) 0.0;

    public float accelY = (float) 0.0;

    public float accelZ = (float) 0.0;

    public float gyroX = (float) 0.0;

    public float gyroY = (float) 0.0;

    public float gyroZ = (float) 0.0;

    public float baroAlt = (float) 0.0;

    public float velN = (float) 0.0;

    public float velE = (float) 0.0;

    public float velD = (float) 0.0;

    float velGPS = (float) 0.0;

    float velDiff = (float) 0.0;

    double lastLatRad = 0.0;

    double lastLongRad = 0.0;

    public int magX = 0;

    public int magY = 0;

    public int magZ = 0;

    public boolean valid = false;

    public float diffX = (float) 0.0;

    public float diffY = (float) 0.0;

    public float diffZ = (float) 0.0;

    float x4 = (float) 0.0;

    float x5 = (float) 0.0;

    float x6 = (float) 0.0;

    public double imuTemp = 0;

    int i2 = 0;

    int i3 = 0;

    int i4 = 0;

    int i5 = 0;

    public double totalZGyro = 0.0;

    public long lastTickNo = 0;

    long dtLastTickNo = 0;

    public double distanceTravelled = 0.0;

    double dtLastLat = 0.0;

    double dtLastLong = 0.0;

    public double bearingDeclined = 0.0;

    public double bearingTrue = 0.0;

    private boolean bearingValid;

    public Record207_0(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 207;
        _subType = 0;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        longRad = payloadBB.getDouble(0);
        latRad = payloadBB.getDouble(8);
        baroRaw = payloadBB.getFloat(16);
        accelX = payloadBB.getFloat(20);
        accelY = payloadBB.getFloat(24);
        accelZ = payloadBB.getFloat(28);
        gyroX = (float) Math.toDegrees(payloadBB.getFloat(32));
        gyroY = (float) Math.toDegrees(payloadBB.getFloat(36));
        gyroZ = (float) Math.toDegrees(payloadBB.getFloat(40));
        baroAlt = payloadBB.getFloat(44);
        quatW = payloadBB.getFloat(48);
        quatX = payloadBB.getFloat(52);
        quatY = payloadBB.getFloat(56);
        quatZ = payloadBB.getFloat(60);
        diffX = payloadBB.getFloat(64);
        diffY = payloadBB.getFloat(68);
        diffZ = payloadBB.getFloat(72);
        velN = payloadBB.getFloat(76);
        velE = payloadBB.getFloat(80);
        velD = payloadBB.getFloat(84);
        x4 = payloadBB.getFloat(88);
        x5 = payloadBB.getFloat(92);
        x6 = payloadBB.getFloat(96);
        magX = payloadBB.getShort(100);
        magY = payloadBB.getShort(102);
        magZ = payloadBB.getShort(104);
        imuTemp = ((double) payloadBB.getShort(106)) / 100.0;
        i2 = payloadBB.getShort(108);
        i3 = payloadBB.getShort(110);
        i4 = payloadBB.getShort(112);
        i5 = payloadBB.getShort(114);
        numSats = payloadBB.get(116);
        if (_payload.tickNo > _payload.datFile.flightStartTick) {
            if (_payload.tickNo > dtLastTickNo + 60) {
                if (dtLastLat == 0.0 && dtLastLong == 0.0) {
                    dtLastLat = latRad;
                    dtLastLong = longRad;
                }
                double dist = Util.distance(latRad, longRad, dtLastLat,
                        dtLastLong);
                if (dist > 0.001) {
                    bearingValid = true;
                } else {
                    bearingValid = false;
                }
                bearingTrue = Util.bearing(dtLastLat, dtLastLong, latRad,
                        longRad);
                double x = bearingTrue - convertDat.declination;
                if (x < -180.0)
                    bearingDeclined = 360.0 + x;
                else if (x > 180.0)
                    bearingDeclined = x - 360.0;
                else
                    bearingDeclined = x;
                distanceTravelled += dist;
                dtLastLat = latRad;
                dtLastLong = longRad;
                dtLastTickNo = _payload.tickNo;
            }

        }
        if (lastTickNo != 0) {
            totalZGyro += gyroZ * ((double) (_payload.tickNo - lastTickNo))
                    / _datFile.getClockRate();
        }
        lastTickNo = _payload.tickNo;
    }

    public static Axis gyroAxis = new Axis("gyro", "Gyro", Units.degreesPerSec);

    public static Axis accelAxis = new Axis("accel", "Accelerometer", Units.G);

    private Signal numSatsSig = Signal.SeriesFloat("numSats",
            "Number of Satellites", null, Units.noUnits);

    private Signal barometerSig = Signal.SeriesFloat("Barometer", "Barometer",
            null, Units.meters);

    private Signal accelSig = Signal.SeriesFloat("Accel", "Accelerometer",
            accelAxis, Units.G);

    public static Signal gyroSig = Signal.SeriesFloat("Gyro", "Gyroscope",
            gyroAxis, Units.degreesPerSec);

    public static Signal magSig = Signal.SeriesFloat("Mag", "Magnetometer",
            null, Units.aTesla);

    private Signal velocitySig = Signal.SeriesFloat("Vel", "Velocity", null,
            Units.metersPerSec);

    private Signal rollSig = Signal.SeriesDouble("Roll", "Roll", null,
            Units.degrees180);

    private Signal pitchSig = Signal.SeriesDouble("Pitch", "Pitch", null,
            Units.degrees180);

    private Signal yawSig = Signal.SeriesDouble("Yaw", "Yaw", null,
            Units.degrees180);

    private Signal yaw360Sig = Signal.SeriesDouble("Yaw(360)",
            "Yaw 360 degrees scale", null, Units.degrees360);

    private Signal totalZGyroSig = Signal.SeriesDouble("totalGyroZ",
            "Integrate and sum gyroZ values", null, Units.degrees);

    private Signal magYawSig = Signal.SeriesDouble("magYaw",
            "Yaw computed from magnetometers", null, Units.degrees180);

    private Signal directionOfTravelSig = Signal.SeriesDouble(
            "directionOfTravel", "Direction of Travel", null, Units.degrees180);

    private Signal distanceTravelledSig = Signal.SeriesDouble(
            "distanceTravelled", "Distance Travelled", null, Units.meters);

    private Signal distanceHPSig = Signal.SeriesDouble("distanceHP",
            "Distance From HP", null, Units.meters);

    private Signal imuTempSig = Signal.SeriesDouble("ImuTemp", "IMU Temp", null,
            Units.degreesC);

    private Signal quaternionSig = Signal.SeriesDoubleExperimental("quat",
            "Quaternion", null, Units.noUnits);

    private boolean notFirstLine = false;

    @Override
    public void printCols(lineType lineT) {
        try {
            if (lineT == lineType.HEADER)
                notFirstLine = false;
            float vel = (float) Math
                    .sqrt(velN * velN + velE * velE + velD * velD);
            float velH = (float) Math.sqrt(velN * velN + velE * velE);
            float magMod = (int) Math
                    .sqrt(magX * magX + magY * magY + magZ * magZ);
            float accel = (float) Math
                    .sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
            float gyro = (float) Math
                    .sqrt(gyroX * gyroX + gyroY * gyroY + gyroZ * gyroZ);
            Quaternion q = new Quaternion(quatW, quatX, quatY, quatZ);
            double[] eulerAngs = q.toEuler();
            double pitch = Math.toDegrees(eulerAngs[0]);
            double roll = Math.toDegrees(eulerAngs[1]);
            double yaw = Math.toDegrees(eulerAngs[2]);
            double longitude = Math.toDegrees(longRad);
            double latitude = Math.toDegrees(latRad);

            printCsvValue(GoTxt.current.flightTime, GoTxt.flightTimeSig, "",
                    lineT, GoTxt.current.valid);
            printCsvValue(longitude, AxesAndSigs.longitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(latitude, AxesAndSigs.latitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(numSats, numSatsSig, "", lineT, true);
            printCsvValue(GoTxt.current.gpsLevel, GoTxt.gpsHealthSig, "", lineT,
                    true);
            printCsvValue(baroRaw, barometerSig, "Raw", lineT, true);
            printCsvValue(baroAlt, barometerSig, "Smooth", lineT, true);
            printCsvValue(GoTxt.current.vpsHeight, GoTxt.vpsHeightSig, "",
                    lineT, GoTxt.current.valid & GoTxt.current.waveWork);
            printCsvValue(GoTxt.current.height, GoTxt.relativeHeightSig, "",
                    lineT, GoTxt.current.valid);
            printCsvValue(convertDat.absoluteHeight, "absoluteHeight", lineT,
                    convertDat.absoluteHeightValid);

            printCsvValue(accelX, accelSig, "X", lineT, true);
            printCsvValue(accelY, accelSig, "Y", lineT, true);
            printCsvValue(accelZ, accelSig, "Z", lineT, true);
            printCsvValue(accel, accelSig, "Composite", lineT, true);

            printCsvValue(gyroX, gyroSig, "X", lineT, true);
            printCsvValue(gyroY, gyroSig, "Y", lineT, true);
            printCsvValue(gyroZ, gyroSig, "Z", lineT, true);
            printCsvValue(gyro, gyroSig, "Composite", lineT, true);

            //                      printCsvValue("errorX", diffX, lineT, true);
            //            printCsvValue("errorY", diffY, lineT, true);
            //            printCsvValue("errorZ", diffZ, lineT, true);
            //            printCsvValue("error", error, lineT, true);

            printCsvValue(magX, magSig, "X", lineT, true);
            printCsvValue(magY, magSig, "Y", lineT, true);
            printCsvValue(magZ, magSig, "Z", lineT, true);
            printCsvValue(magMod, magSig, "Mod", lineT, true);

            printCsvValue(velN, velocitySig, "N", lineT, true);
            printCsvValue(velE, velocitySig, "E", lineT, true);
            printCsvValue(velD, velocitySig, "D", lineT, true);
            printCsvValue(vel, velocitySig, "Composite", lineT, true);
            printCsvValue(velH, velocitySig, "H", lineT, true);

            double velGPS = 0.0;
            if (notFirstLine) {
                double distance = Util.distance(latRad, longRad, lastLatRad,
                        lastLongRad);
                velGPS = distance / (((double) (convertDat.tickNo - lastTickNo))
                        / _datFile.getClockRate());
            } else {
                velGPS = 0.0;
            }

            lastLatRad = latRad;
            lastLongRad = longRad;
            printCsvValue(velGPS - velH, velocitySig, "GPS-H", lineT, true);

            printCsvValue(quatW, quaternionSig, "W", lineT, true);
            printCsvValue(quatX, quaternionSig, "X", lineT, true);
            printCsvValue(quatY, quaternionSig, "Y", lineT, true);
            printCsvValue(quatZ, quaternionSig, "Z", lineT, true);

            printCsvValue(roll, rollSig, "", lineT, valid);
            printCsvValue(pitch, pitchSig, "", lineT, valid);
            printCsvValue(yaw, yawSig, "", lineT, valid);
            printCsvValue(((yaw + 360.0) % 360.0), yaw360Sig, "", lineT, valid);

            printCsvValue(totalZGyro, totalZGyroSig, "", lineT, true);
            if (lineT == lineType.LINE) {
                notFirstLine = true;
                lastTickNo = convertDat.tickNo;
            }
            double magYaw = 0.0;
            double distanceHP = 0.0;
            if (lineT == lineType.LINE) {
                magYaw = convertDat.computeMagYaw1(Math.toRadians(pitch),
                        Math.toRadians(roll), magX, magY, magZ, magMod);
                distanceHP = Util.distance(latRad, longRad,
                        convertDat.latitudeHP, convertDat.longitudeHP);

            }
            printCsvValue(magYaw, magYawSig, "", lineT, valid);

            printCsvValue(distanceHP, distanceHPSig, "", lineT,
                    convertDat.validHP);
            printCsvValue(convertDat.longitudeHPDegrees,
                    AxesAndSigs.longitudeSig, "HP", lineT,
                    (convertDat.validHP));
            printCsvValue(convertDat.latitudeHPDegrees, AxesAndSigs.latitudeSig,
                    "HP", lineT, (convertDat.validHP));
            printCsvValue(distanceTravelled, distanceTravelledSig, "", lineT,
                    true);
            printCsvValue(bearingDeclined, directionOfTravelSig, "mag", lineT,
                    bearingValid);
            printCsvValue(bearingTrue, directionOfTravelSig, "true", lineT,
                    bearingValid);
            printCsvValue(imuTemp, imuTempSig, "", lineT, true);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
