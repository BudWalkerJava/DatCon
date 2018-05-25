package src.DatConRecs;

import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.MagYaw;
import src.Files.IMUCalcs;
import src.Files.Persist;
import src.Files.Quaternion;
import src.Files.Signal;
import src.Files.Units;
import src.Files.Util;

public class RecIMU extends Record {

    public static RecIMU current = null;

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

    protected float accelXLast = (float) 0.0;

    protected float accelYLast = (float) 0.0;

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

    protected IMUCalcs imuCalcs = null;

    public RecIMU(ConvertDat convertDat, int id, int length, int index) {
        super(convertDat, id, length);

        current = this;
        imuCalcs = new IMUCalcs(this, index);
        coordSig = Signal.SeriesDouble(recName, index, "GPS coord", null,
                Units.degrees180);

        numSatsSig = Signal.SeriesFloat(recName, index, "Number of Satellites",
                null, Units.noUnits);

        barometerSig = Signal.SeriesFloat(recName, index, "Barometer", null,
                Units.meters);

        accelSig = Signal.SeriesFloat(recName, index, "Accelerometer",
                AxesAndSigs.accelAxis, Units.G);

        gyroSig = Signal.SeriesFloat(recName, index, "Gyroscope",
                AxesAndSigs.gyroAxis, Units.degreesPerSec);

        magSig = Signal.SeriesFloat(recName, index, "Magnetometer", null,
                Units.aTesla);

        velocitySig = Signal.SeriesFloat(recName, index, "Velocity", null,
                Units.metersPerSec);

        attitudeSig = Signal.SeriesDouble(recName, index, "Attitude", null,
                Units.degrees180);

        totalGyroSig = Signal.SeriesDouble(recName, index,
                "Integrate and sum gyro values", null, Units.degrees);

        directionSig = Signal.SeriesDouble(recName, index, "Direction", null,
                Units.degrees180);

        distanceSig = Signal.SeriesDouble(recName, index, "Distance", null,
                Units.meters);

        imuTempSig = Signal.SeriesDouble(recName, index, "IMU Temp", null,
                Units.degreesC);

        quaternionSig = Signal.SeriesDoubleExperimental(recName, index,
                "Quaternion", null, Units.noUnits);
        experimentalSig = Signal.SeriesFloatExperimental(recName, index,
                "IMU exp", null, Units.noUnits);
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

                if (dist > 0.001) {
                    bearingValid = true;
                } else {
                    bearingValid = false;
                }
                bearingTrue = Util.bearing(dtLastLat, dtLastLong, latRad,
                        longRad);
                double x = bearingTrue - convertDat.getGeoDeclination();
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
            if (Persist.inertialOnlyCalcs) {
                //                imuCalcs.computeAccel(quatW, quatX, quatY, quatZ, accelX,
                //                        accelY, accelZ, dt);
                //imuCalcs.computeAccel(ag_X, ag_Y, ag_Z, dt);
            }
        }
        integrationLastTickNo = _payload.tickNo;
    }

    public float getAg_X() {
        return ag_X;
    }

    protected boolean notFirstLine = false;

    protected double yawRadians = 0.0;

    protected double pitchRadians = 0.0;

    protected double rollRadians = 0.0;

    protected double velH = 0.0;

    protected Signal coordSig = null;

    protected Signal numSatsSig = null;

    protected Signal barometerSig = null;

    protected Signal accelSig = null;

    protected Signal gyroSig = null;

    protected Signal magSig = null;

    protected Signal velocitySig = null;

    protected Signal heightSig = null;

    protected Signal attitudeSig = null;

    protected Signal totalGyroSig = null;

    protected Signal directionSig = null;

    protected Signal distanceSig = null;

    protected Signal imuTempSig = null;

    protected Signal quaternionSig = null;

    protected Signal experimentalSig = null;

    public static Signal flightTimeSig = Signal.SeriesInt("flightTime",
            "Flight Time", null, Units.msec);

    public static Signal gpsHealthSig = Signal.SeriesInt("gpsHealth",
            "GPS Health", null, Units.gpsHealth);

    public static String recName = "IMU_ATTI";

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
            if (GoTxt50_12.current == null) {
                convertDat.processCoordsNoGoTxt(longitudeDegrees,
                        latitudeDegrees, baroRaw);
            }
            printCsvValue(longitudeDegrees, coordSig, "Longitude", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(latitudeDegrees, coordSig, "Latitude", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(numSats, numSatsSig, "numSats", lineT, valid);

            printCsvValue(baroRaw, barometerSig, "barometer:Raw", lineT, valid);
            printCsvValue(baroSmooth, barometerSig, "barometer:Smooth", lineT,
                    valid);

            printCsvValue(accelX, accelSig, "accel:X", lineT, valid);
            printCsvValue(accelY, accelSig, "accel:Y", lineT, valid);
            printCsvValue(accelZ, accelSig, "accel:Z", lineT, valid);
            printCsvValue(accel, accelSig, "accel:Composite", lineT, valid);

            printCsvValue(gyroX, gyroSig, "gyro:X", lineT, valid);
            printCsvValue(gyroY, gyroSig, "gyro:Y", lineT, valid);
            printCsvValue(gyroZ, gyroSig, "gyro:Z", lineT, valid);
            printCsvValue(gyro, gyroSig, "gyro:Composite", lineT, valid);

            printCsvValue(magX, magSig, "mag:X", lineT, valid);
            printCsvValue(magY, magSig, "mag:Y", lineT, valid);
            printCsvValue(magZ, magSig, "mag:Z", lineT, valid);
            printCsvValue(magMod, magSig, "mag:Mod", lineT, valid);

            printCsvValue(velN, velocitySig, "velN", lineT, valid);
            printCsvValue(velE, velocitySig, "velE", lineT, valid);
            printCsvValue(velD, velocitySig, "velD", lineT, valid);
            printCsvValue(vel, velocitySig, "velComposite", lineT, valid);
            printCsvValue(velH, velocitySig, "velH", lineT, valid);

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
            printCsvValue(velGPS - velH, velocitySig, "GPS-H", lineT, valid);

            printCsvValue(quatW, quaternionSig, "quatW", lineT, valid);
            printCsvValue(quatX, quaternionSig, "quatX", lineT, valid);
            printCsvValue(quatY, quaternionSig, "quatY", lineT, valid);
            printCsvValue(quatZ, quaternionSig, "quatZ", lineT, valid);

            printCsvValue(roll, attitudeSig, "roll", lineT, valid);
            printCsvValue(pitch, attitudeSig, "pitch", lineT, valid);
            printCsvValue(yaw, attitudeSig, "yaw", lineT, valid);
            printCsvValue(((yaw + 360.0) % 360.0), attitudeSig, "yaw360", lineT,
                    valid);

            printCsvValue(totalZGyro, totalGyroSig, "totalGyro:Z", lineT,
                    valid);
            printCsvValue(totalXGyro, totalGyroSig, "totalGyro:X", lineT,
                    valid);
            printCsvValue(totalYGyro, totalGyroSig, "totalGyro:Y", lineT,
                    valid);
            if (lineT == lineType.LINE) {
                notFirstLine = true;
                if (Persist.inertialOnlyCalcs) {
                    double dt = (((double) (convertDat.tickNo - lastTickNo))
                            / _datFile.getClockRate());
                    imuCalcs.computeAccel(Math.toRadians(pitch),
                            Math.toRadians(roll), Math.toRadians(yaw), accelX,
                            accelY, accelZ, dt);
                }
                lastTickNo = convertDat.tickNo;
            }

            double distanceHP = 0.0;
            if (lineT == lineType.LINE) {
                magYaw.compute(Math.toRadians(pitch), Math.toRadians(roll),
                        magX, magY, magZ, magMod);
                distanceHP = Util.distance(latRad, longRad,
                        convertDat.getHPLatRad(), convertDat.getHPLongRad());
            }
            printCsvValue(magYaw.getDegrees(), magSig, "magYaw", lineT, valid);
            if (Persist.magCalcs) {
                double diff = 0.0;
                if (yaw > magYaw.getDegrees() + 180) {
                    diff = magYaw.getDegrees() - yaw + 360.0;
                } else if (yaw < magYaw.getDegrees() - 180) {
                    diff = magYaw.getDegrees() - yaw - 360.0;
                } else {
                    diff = magYaw.getDegrees() - yaw;
                }
                printCsvValue(diff, magSig, "Yaw-magYaw", lineT, valid);
            }
            if (Persist.inertialOnlyCalcs) {
                imuCalcs.printCols(lineT, valid);
            }
            printCsvValue(distanceHP, distanceSig, "distanceHP", lineT,
                    convertDat.isHpValid());
            printCsvValue(distanceTravelled, distanceSig, "distanceTravelled",
                    lineT, valid);
            printCsvValue(bearingDeclined, directionSig,
                    "directionOfTravel[mag]", lineT, bearingValid);
            printCsvValue(bearingTrue, directionSig, "directionOfTravel[true]",
                    lineT, bearingValid);
            printCsvValue(imuTemp, imuTempSig, "temperature", lineT, valid);
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

    public double getVh() {
        return velH;
    }

    public double getVn() {
        return velN;
    }

    public double getVe() {
        return velE;
    }

    public double getVd() {
        return velD;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

}
