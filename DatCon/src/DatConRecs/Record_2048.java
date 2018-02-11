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
package src.DatConRecs;

import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.MagYaw;
import src.Files.Persist;
import src.Files.Quaternion;
import src.Files.RecSpec;
import src.Files.RecSpec.RecType;
import src.Files.Signal;
import src.Files.Units;
import src.Files.Util;
import src.V1.Files.ConvertDatV1;
import src.V3.Files.ConvertDatV3;

public class Record_2048 extends Record {

    // 200 hZ
    // length 122

    public static Record_2048 current = null;

    protected double longRad = 0.0;

    protected double latRad = 0.0;

    protected int numSats = 0;

    protected float quatW = (float) 0.0;

    protected float quatX = (float) 0.0;

    protected float quatY = (float) 0.0;

    protected float quatZ = (float) 0.0;

    protected float baroRaw = (float) 0.0;

    protected float accelX = (float) 0.0;

    protected float accelY = (float) 0.0;

    protected float accelZ = (float) 0.0;

    protected float gyroX = (float) 0.0;

    protected float gyroY = (float) 0.0;

    protected float gyroZ = (float) 0.0;

    protected float gyroXLast = (float) 0.0;

    protected float gyroYLast = (float) 0.0;

    protected float gyroZLast = (float) 0.0;

    protected float baroSmooth = (float) 0.0;

    protected float velN = (float) 0.0;

    protected float velE = (float) 0.0;

    protected float velD = (float) 0.0;

    protected float velGPS = (float) 0.0;

    protected float velDiff = (float) 0.0;

    protected double lastLatRad = 0.0;

    protected double lastLongRad = 0.0;

    protected int magX = 0;

    protected int magY = 0;

    protected int magZ = 0;

    protected boolean valid = false;

    protected float ag_X = (float) 0.0;

    protected float ag_Y = (float) 0.0;

    protected float ag_Z = (float) 0.0;

    protected float gb_X = (float) 0.0;

    protected float gb_Y = (float) 0.0;

    protected float gb_Z = (float) 0.0;

    protected double imuTemp = 0;

    protected int i2 = 0;

    protected int i3 = 0;

    protected int i4 = 0;

    protected int i5 = 0;

    protected double totalXGyro = 0.0;

    protected double totalYGyro = 0.0;

    protected double totalZGyro = 0.0;

    protected long lastTickNo = 0;

    protected long dtLastTickNo = 0;

    protected long integrationLastTickNo = 0;

    protected double distanceTravelled = 0.0;

    protected double dtLastLat = 0.0;

    protected double dtLastLong = 0.0;

    protected double bearingDeclined = 0.0;

    protected double bearingTrue = 0.0;

    protected boolean bearingValid;

    protected MagYaw magYaw = new MagYaw();

    public Record_2048(ConvertDat convertDat) {
        super(convertDat, 2048, 120);
        current = this;
    }

    public Record_2048(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
        current = this;
    }

    @Override
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
        baroSmooth = payloadBB.getFloat(44);
        quatW = payloadBB.getFloat(48);
        quatX = payloadBB.getFloat(52);
        quatY = payloadBB.getFloat(56);
        quatZ = payloadBB.getFloat(60);
        ag_X = payloadBB.getFloat(64);
        ag_Y = payloadBB.getFloat(68);
        ag_Z = payloadBB.getFloat(72);
        velN = payloadBB.getFloat(76);
        velE = payloadBB.getFloat(80);
        velD = payloadBB.getFloat(84);
        gb_X = payloadBB.getFloat(88);
        gb_Y = payloadBB.getFloat(92);
        gb_Z = payloadBB.getFloat(96);
        magX = payloadBB.getShort(100);
        magY = payloadBB.getShort(102);
        magZ = payloadBB.getShort(104);
        imuTemp = (payloadBB.getShort(106)) / 100.0;
        i2 = payloadBB.getShort(108);
        i3 = payloadBB.getShort(110);
        i4 = payloadBB.getShort(112);
        i5 = payloadBB.getShort(114);
        numSats = payloadBB.get(116);
        if (_payload.tickNo > _payload.datFile.flightStartTick) {
            if ((_payload.tickNo > dtLastTickNo + 60)) {
                if (dtLastLat == 0.0 && dtLastLong == 0.0) {
                    dtLastLat = latRad;
                    dtLastLong = longRad;
                }
                double dist = Util.distance(latRad, longRad, dtLastLat,
                        dtLastLong);
                if (dist > 100.0) {
                }
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
        if (integrationLastTickNo != 0) {
            double dt = ((_payload.tickNo - integrationLastTickNo)
                    / _datFile.getClockRate());
            totalZGyro += (0.5 * (gyroZ + gyroZLast)) * dt;
            totalXGyro += (0.5 * (gyroX + gyroXLast)) * dt;
            totalYGyro += (0.5 * (gyroY + gyroYLast)) * dt;
            gyroYLast = gyroY;
            gyroXLast = gyroX;
            gyroZLast = gyroZ;
        }
        integrationLastTickNo = _payload.tickNo;
    }

    public static Signal experimentalSig = Signal
            .SeriesFloatExperimental("IMU:exp", "IMU exp", null, Units.noUnits);

    protected boolean notFirstLine = false;

    protected double yawRadians = 0.0;

    protected double pitchRadians = 0.0;

    protected double rollRadians = 0.0;

    protected double velH = 0.0;

    @Override
    public void printCols(lineType lineT) {
        try {
            if (lineT == lineType.HEADER)
                notFirstLine = false;
            float vel = (float) Math
                    .sqrt(velN * velN + velE * velE + velD * velD);
            velH = Math.sqrt(velN * velN + velE * velE);
            float magMod = (int) Math
                    .sqrt(magX * magX + magY * magY + magZ * magZ);
            float accel = (float) Math
                    .sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
            float gyro = (float) Math
                    .sqrt(gyroX * gyroX + gyroY * gyroY + gyroZ * gyroZ);
            Quaternion q = new Quaternion(quatW, quatX, quatY, quatZ);
            double[] eulerAngs = q.toEuler();
            pitchRadians = eulerAngs[0];
            rollRadians = eulerAngs[1];
            yawRadians = eulerAngs[2];
            double pitch = Math.toDegrees(pitchRadians);
            double roll = Math.toDegrees(rollRadians);
            double yaw = Math.toDegrees(yawRadians);
            double longitudeDegrees = Math.toDegrees(longRad);
            double latitudeDegrees = Math.toDegrees(latRad);
            if (GoTxt_12.current == null) {
                convertDat.processCoordsNoGoTxt(longitudeDegrees,
                        latitudeDegrees, baroSmooth);
            }

            if (GoTxt_12.current != null) {
                printCsvValue(GoTxt_12.current.flightTime,
                        GoTxt_12.flightTimeSig, "", lineT,
                        GoTxt_12.current.valid);
            }
            printCsvValue(longitudeDegrees, AxesAndSigs.longitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(latitudeDegrees, AxesAndSigs.latitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(numSats, AxesAndSigs.numSatsSig, "", lineT, valid);
            if (GoTxt_12.current != null) {
                printCsvValue(GoTxt_12.current.gpsLevel, GoTxt_12.gpsHealthSig,
                        "", lineT, GoTxt_12.current.valid);
            }

            printCsvValue(baroRaw, AxesAndSigs.barometerSig, "Raw", lineT,
                    valid);
            printCsvValue(baroSmooth, AxesAndSigs.barometerSig, "Smooth", lineT,
                    valid);

            if (GoTxt_12.current != null) {
                printCsvValue(GoTxt_12.current.vpsHeight, GoTxt_12.vpsHeightSig,
                        "", lineT,
                        GoTxt_12.current.valid & GoTxt_12.current.waveWork);
            }
            printCsvValue(convertDat.getRelativeHeight(),
                    GoTxt_12.relativeHeightSig, "", lineT,
                    convertDat.isRelativeHeightOK());

            printCsvValue(convertDat.getAbsoluteHeight(), "absoluteHeight",
                    lineT, convertDat.absoluteHeightValid);

            printCsvValue(accelX, AxesAndSigs.accelSig, "X", lineT, valid);
            printCsvValue(accelY, AxesAndSigs.accelSig, "Y", lineT, valid);
            printCsvValue(accelZ, AxesAndSigs.accelSig, "Z", lineT, valid);
            printCsvValue(accel, AxesAndSigs.accelSig, "Composite", lineT,
                    valid);

            printCsvValue(gyroX, AxesAndSigs.gyroSig, "X", lineT, valid);
            printCsvValue(gyroY, AxesAndSigs.gyroSig, "Y", lineT, valid);
            printCsvValue(gyroZ, AxesAndSigs.gyroSig, "Z", lineT, valid);
            printCsvValue(gyro, AxesAndSigs.gyroSig, "Composite", lineT, valid);

            //                      printCsvValue("errorX", diffX, lineT, valid);
            //            printCsvValue("errorY", diffY, lineT, valid);
            //            printCsvValue("errorZ", diffZ, lineT, valid);
            //            printCsvValue("error", error, lineT, valid);

            printCsvValue(magX, AxesAndSigs.magSig, "X", lineT, valid);
            printCsvValue(magY, AxesAndSigs.magSig, "Y", lineT, valid);
            printCsvValue(magZ, AxesAndSigs.magSig, "Z", lineT, valid);
            printCsvValue(magMod, AxesAndSigs.magSig, "Mod", lineT, valid);

            printCsvValue(velN, AxesAndSigs.velocitySig, "N", lineT, valid);
            printCsvValue(velE, AxesAndSigs.velocitySig, "E", lineT, valid);
            printCsvValue(velD, AxesAndSigs.velocitySig, "D", lineT, valid);
            printCsvValue(vel, AxesAndSigs.velocitySig, "Composite", lineT,
                    valid);
            printCsvValue(velH, AxesAndSigs.velocitySig, "H", lineT, valid);

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
            printCsvValue(velGPS - velH, AxesAndSigs.velocitySig, "GPS-H",
                    lineT, valid);

            printCsvValue(quatW, AxesAndSigs.quaternionSig, "W", lineT, valid);
            printCsvValue(quatX, AxesAndSigs.quaternionSig, "X", lineT, valid);
            printCsvValue(quatY, AxesAndSigs.quaternionSig, "Y", lineT, valid);
            printCsvValue(quatZ, AxesAndSigs.quaternionSig, "Z", lineT, valid);

            printCsvValue(roll, AxesAndSigs.rollSig, "", lineT, valid);
            printCsvValue(pitch, AxesAndSigs.pitchSig, "", lineT, valid);
            printCsvValue(yaw, AxesAndSigs.yawSig, "", lineT, valid);
            printCsvValue(((yaw + 360.0) % 360.0), AxesAndSigs.yaw360Sig, "",
                    lineT, valid);

            printCsvValue(totalZGyro, AxesAndSigs.totalGyroSig, "Z", lineT,
                    valid);
            printCsvValue(totalXGyro, AxesAndSigs.totalGyroSig, "X", lineT,
                    valid);
            printCsvValue(totalYGyro, AxesAndSigs.totalGyroSig, "Y", lineT,
                    valid);
            if (lineT == lineType.LINE) {
                notFirstLine = true;
                lastTickNo = convertDat.tickNo;
            }

            double distanceHP = 0.0;
            if (lineT == lineType.LINE) {
                magYaw.compute(Math.toRadians(pitch), Math.toRadians(roll),
                        magX, magY, magZ, magMod);
                distanceHP = Util.distance(latRad, longRad,
                        convertDat.latitudeHP, convertDat.longitudeHP);
            }
            printCsvValue(magYaw.getDegrees(), AxesAndSigs.magYawSig, "", lineT,
                    valid);
            printCsvValue(distanceHP, AxesAndSigs.distanceHPSig, "", lineT,
                    convertDat.validHP);
            printCsvValue(distanceTravelled, AxesAndSigs.distanceTravelledSig,
                    "", lineT, valid);
            printCsvValue(bearingDeclined, AxesAndSigs.directionOfTravelSig,
                    "mag", lineT, bearingValid);
            printCsvValue(bearingTrue, AxesAndSigs.directionOfTravelSig, "true",
                    lineT, bearingValid);
            printCsvValue(imuTemp, AxesAndSigs.imuTempSig, "", lineT, valid);
            if (Persist.EXPERIMENTAL_FIELDS) {
                printCsvValue(ag_X, experimentalSig, "ag_X", lineT, valid);
                printCsvValue(ag_Y, experimentalSig, "ag_Y", lineT, valid);
                printCsvValue(ag_Z, experimentalSig, "ag_Z", lineT, valid);
                printCsvValue(gb_X, experimentalSig, "gb_X", lineT, valid);
                printCsvValue(gb_Y, experimentalSig, "gb_Y", lineT, valid);
                printCsvValue(gb_Z, experimentalSig, "gb_Z", lineT, valid);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    public double getYawRadians() {
        return yawRadians;
    }

    public double getPitchRadians() {
        return pitchRadians;
    }

    public double getRollRadians() {
        return rollRadians;
    }

    public double getVelH() {
        return velH;
    }

    public double getVn() {
        return velN;
    }

    public double getVe() {
        return velE;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public double getVelD() {
        return velD;
    }
}
