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
import src.Files.Persist;
import src.Files.Quaternion;
import src.Files.RecSpec;
import src.Files.Signal;
import src.Files.Units;
import src.Files.Util;

public class Record_1 extends src.DatConRecs.Record_2048 {
    private int _length;

    public Record_1(ConvertDat convertDat) {
        super(convertDat, 1, 120);
        current = this;
           }


    public void process(Payload _payload) {
        super.process(_payload);
    }


    public static Signal experimentalSig = Signal
            .SeriesFloatExperimental("IMU:exp", "IMU exp", null, Units.noUnits);

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
            //            ((ConvertDatV3) convertDat).processCoordsNew(longitudeDegrees,
            //                    latitudeDegrees, baroSmooth);

            printCsvValue(GoTxt_12.current.flightTime, GoTxt_12.flightTimeSig,
                    "", lineT, GoTxt_12.current.valid);
            printCsvValue(longitudeDegrees, AxesAndSigs.longitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(latitudeDegrees, AxesAndSigs.latitudeSig, "", lineT,
                    convertDat.gpsCoordsOK);
            printCsvValue(numSats, AxesAndSigs.numSatsSig, "", lineT, true);
            printCsvValue(GoTxt_12.current.gpsLevel, GoTxt_12.gpsHealthSig, "",
                    lineT, GoTxt_12.current.valid);
            printCsvValue(baroRaw, AxesAndSigs.barometerSig, "Raw", lineT,
                    true);
            printCsvValue(baroSmooth, AxesAndSigs.barometerSig, "Smooth", lineT,
                    true);
            //            if (convertDat instanceof ConvertDatV3) {
            //                printCsvValue(GoTxt.current.vpsHeight, GoTxt.vpsHeightSig, "",
            //                        lineT, GoTxt.current.valid & GoTxt.current.waveWork);
            //            } else if (convertDat instanceof ConvertDatV1) {
            //                printCsvValue(GoTxt.current.vpsHeight, GoTxt.vpsHeightSig, "",
            //                        lineT, (Record92_3.current.vpsQuality > 190));
            //            }
            //                        printCsvValue(((ConvertDatV3) convertDat).getRelativeHeight(),
            //                                GoTxt.relativeHeightSig, "", lineT,
            //                                ((ConvertDatV3) convertDat).isRelativeHeightOK());
            printCsvValue(GoTxt_12.current.relativeHeight, GoTxt_12.relativeHeightSig,
                    "", lineT, GoTxt_12.current.valid);
            //            printCsvValue(((ConvertDatV3) convertDat).getAbsoluteHeight(),
            //                    "absoluteHeight", lineT,
            //                    ((ConvertDatV3) convertDat).absoluteHeightValid);
            printCsvValue(convertDat.absoluteHeight, "absoluteHeight", lineT,
                    convertDat.absoluteHeightValid);

            printCsvValue(accelX, AxesAndSigs.accelSig, "X", lineT, true);
            printCsvValue(accelY, AxesAndSigs.accelSig, "Y", lineT, true);
            printCsvValue(accelZ, AxesAndSigs.accelSig, "Z", lineT, true);
            printCsvValue(accel, AxesAndSigs.accelSig, "Composite", lineT,
                    true);

            printCsvValue(gyroX, AxesAndSigs.gyroSig, "X", lineT, true);
            printCsvValue(gyroY, AxesAndSigs.gyroSig, "Y", lineT, true);
            printCsvValue(gyroZ, AxesAndSigs.gyroSig, "Z", lineT, true);
            printCsvValue(gyro, AxesAndSigs.gyroSig, "Composite", lineT, true);

            //                      printCsvValue("errorX", diffX, lineT, true);
            //            printCsvValue("errorY", diffY, lineT, true);
            //            printCsvValue("errorZ", diffZ, lineT, true);
            //            printCsvValue("error", error, lineT, true);

            printCsvValue(magX, AxesAndSigs.magSig, "X", lineT, true);
            printCsvValue(magY, AxesAndSigs.magSig, "Y", lineT, true);
            printCsvValue(magZ, AxesAndSigs.magSig, "Z", lineT, true);
            printCsvValue(magMod, AxesAndSigs.magSig, "Mod", lineT, true);

            printCsvValue(velN, AxesAndSigs.velocitySig, "N", lineT, true);
            printCsvValue(velE, AxesAndSigs.velocitySig, "E", lineT, true);
            printCsvValue(velD, AxesAndSigs.velocitySig, "D", lineT, true);
            printCsvValue(vel, AxesAndSigs.velocitySig, "Composite", lineT,
                    true);
            printCsvValue(velH, AxesAndSigs.velocitySig, "H", lineT, true);

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
                    lineT, true);

            printCsvValue(quatW, AxesAndSigs.quaternionSig, "W", lineT, true);
            printCsvValue(quatX, AxesAndSigs.quaternionSig, "X", lineT, true);
            printCsvValue(quatY, AxesAndSigs.quaternionSig, "Y", lineT, true);
            printCsvValue(quatZ, AxesAndSigs.quaternionSig, "Z", lineT, true);

            printCsvValue(roll, AxesAndSigs.rollSig, "", lineT, valid);
            printCsvValue(pitch, AxesAndSigs.pitchSig, "", lineT, valid);
            printCsvValue(yaw, AxesAndSigs.yawSig, "", lineT, valid);
            printCsvValue(((yaw + 360.0) % 360.0), AxesAndSigs.yaw360Sig, "",
                    lineT, valid);

            printCsvValue(totalZGyro, AxesAndSigs.totalGyroSig, "Z", lineT,
                    true);
            printCsvValue(totalXGyro, AxesAndSigs.totalGyroSig, "X", lineT,
                    true);
            printCsvValue(totalYGyro, AxesAndSigs.totalGyroSig, "Y", lineT,
                    true);
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
                    "", lineT, true);
            printCsvValue(bearingDeclined, AxesAndSigs.directionOfTravelSig,
                    "mag", lineT, bearingValid);
            printCsvValue(bearingTrue, AxesAndSigs.directionOfTravelSig, "true",
                    lineT, bearingValid);
            printCsvValue(imuTemp, AxesAndSigs.imuTempSig, "", lineT, true);
            if (Persist.EXPERIMENTAL_FIELDS) {
                printCsvValue(ag_X, experimentalSig, "ag_X", lineT, true);
                printCsvValue(ag_Y, experimentalSig, "ag_Y", lineT, true);
                printCsvValue(ag_Z, experimentalSig, "ag_Z", lineT, true);
                printCsvValue(gb_X, experimentalSig, "gb_X", lineT, true);
                printCsvValue(gb_Y, experimentalSig, "gb_Y", lineT, true);
                printCsvValue(gb_Z, experimentalSig, "gb_Z", lineT, true);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    //    public double getYawRadians() {
    //        return yawRadians;
    //    }
    //
    //    public double getPitchRadians() {
    //        return pitchRadians;
    //    }
    //
    //    public double getRollRadians() {
    //        return rollRadians;
    //    }
    //
    //    public double getVelH() {
    //        return velH;
    //    }
    //
    //    public double getVn() {
    //        return velN;
    //    }
    //
    //    public double getVe() {
    //        return velE;
    //    }
    //
    //    public double getDistanceTravelled() {
    //        return distanceTravelled;
    //    }
    //
    //    public double getVelD() {
    //        return velD;
    //    }
}
