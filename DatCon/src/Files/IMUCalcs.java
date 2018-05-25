package src.Files;

import java.io.IOException;

import src.DatConRecs.RecIMU;
import src.Files.ConvertDat.lineType;

public class IMUCalcs {
    private static boolean debug = true;

    private double accelYawDegrees = 0.0;

    private RecIMU recIMU = null;

    public IMUCalcs(RecIMU recIMU, int index) {
        this.recIMU = recIMU;
        imuCalcsSig = Signal.SeriesFloat("InertialOnlyCalcs", index, "", null,
                Units.noUnits);
    }

    public double getAccelYawDegrees() {
        return accelYawDegrees;
    }

    private double accelMod = 0.0;

    private double accelNorth = 0.0;

    public double getAccelNorth() {
        return accelNorth;
    }

    private double velNorth = 0.0;

    public double getVelNorth() {
        return velNorth;
    }

    private double accelNorthLast = 0.0;

    public double getAccelMod() {
        return accelMod;
    }

    private double agNLast = 0.0;

    private double vgXLast = 0.0;

    private double pgX = 0.0;

    private double agN = 0.0;

    private double vgN = 0.0;

    private double agELast = 0.0;

    private double vgYLast = 0.0;

    private double pgY = 0.0;

    private double agE = 0.0;

    private double vgE = 0.0;

    private double vgZLast = 0.0;

    private double pgZ = 0.0;

    private double agDLast = 0.0;

    private double agD = 0.0;

    private double vgD = 0.0;

    private double abX = 0.0;

    private double abY = 0.0;

    private double abZ = 0.0;

    public void computeAccel(double qW, double qX, double qY, double qZ,
            double abX, double abY, float abZ, double dt) {
        abX *= 9.80665;
        abY *= 9.80665;
        abZ += 1.0;
        abZ *= 9.80665;
        this.abX = abX;
        this.abY = abY;
        this.abZ = abZ;
        //        double[] quat = { qW, qX, qY, qZ };
        //        double[] accelBVec = {abX, abY, abZ};
        //        double[] accelGVec = rotate(quat, accelBVec);
        //        agX = accelGVec[0];
        //        agY = accelGVec[1];
        //        agZ = accelGVec[2];
        agN = abX * (1.0 - 2.0 * qY * qY - 2.0 * qZ * qZ)
                + abY * (2.0 * qX * qY - 2.0 * qZ * qW)
                + abZ * (2.0 * qX * qZ + 2.0 * qY * qW);
        //agX = recIMU.getAg_X();

        agE = abX * (2.0 * qX * qY + 2.0 * qZ * qW)
                + abY * (1.0 - 2.0 * qX * qX - 2.0 * qZ * qZ)
                + abZ * (2.0 * qY * qZ - 2.0 * qX * qW);

        vgN += (0.5 * (agN + agNLast)) * dt;
        vgE += (0.5 * (agE + agELast)) * dt;
        vgD += (0.5 * (agD + agDLast)) * dt;

        agNLast = agN;
        agELast = agE;
        agDLast = agD;
    }

    public void computeAccel2(double qW, double qX, double qY, double qZ,
            double abX, double abY, float abZ, double dt) {
        abX *= 9.80665;
        abY *= 9.80665;
        abZ += 1.0;
        abZ *= 9.80665;
        this.abX = abX;
        this.abY = abY;
        this.abZ = abZ;
        //        double[] quat = { qW, qX, qY, qZ };
        //        double[] accelBVec = {abX, abY, abZ};
        //        double[] accelGVec = rotate(quat, accelBVec);
        //        agX = accelGVec[0];
        //        agY = accelGVec[1];
        //        agZ = accelGVec[2];
        agN = abX * (1.0 - 2.0 * qY * qY - 2.0 * qZ * qZ)
                + abY * (2.0 * qX * qY - 2.0 * qZ * qW)
                + abZ * (2.0 * qX * qZ + 2.0 * qY * qW);
        //agX = recIMU.getAg_X();

        agE = abX * (2.0 * qX * qY + 2.0 * qZ * qW)
                + abY * (1.0 - 2.0 * qX * qX - 2.0 * qZ * qZ)
                + abZ * (2.0 * qY * qZ - 2.0 * qX * qW);

        vgN += (0.5 * (agN + agNLast)) * dt;
        vgE += (0.5 * (agE + agELast)) * dt;
        vgD += (0.5 * (agD + agDLast)) * dt;

        agNLast = agN;
        agELast = agE;
        agDLast = agD;
    }

    private double[] rotate(double[] quat, double[] accelBVec) {
        double[] retv = new double[3];
        retv[0] = accelBVec[0]
                * (1.0 - 2.0 * quat[2] * quat[2] - 2.0 * quat[3] * quat[3])
                + accelBVec[1]
                        * (2.0 * quat[1] * quat[2] - 2.0 * quat[3] * quat[0])
                + accelBVec[2]
                        * (2.0 * quat[1] * quat[3] + 2.0 * quat[2] * quat[0]);

        retv[1] = accelBVec[0]
                * (2.0 * quat[1] * quat[2] + 2.0 * quat[3] * quat[0])
                + accelBVec[1] * (1.0 - 2.0 * quat[1] * quat[1]
                        - 2.0 * quat[3] * quat[3])
                + accelBVec[2]
                        * (2.0 * quat[2] * quat[3] - 2.0 * quat[1] * quat[0]);
        //2*qx*qz - 2*qy*qw
        retv[2] = accelBVec[0]
                * (2.0 * quat[1] * quat[3] - 2.0 * quat[2] * quat[0])
                // 2*qy*qz + 2*qx*qw
                + accelBVec[1]
                        * (2.0 * quat[1] * quat[3] + 2.0 * quat[1] * quat[0])
                //1 - 2*qx2 - 2*qy2
                + accelBVec[2] * (1.0 - 2.0 * quat[1] * quat[1]
                        - 2.0 * quat[2] * quat[2]);

        return retv;
    }

    public void computeAccel(float ag_X, float ag_Y, float ag_Z, double dt) {
        agN = ag_X;
        agE = ag_Y;
        agD = ag_Z;
        vgN += (0.5 * (agN + agNLast)) * dt;
        vgE += (0.5 * (agE + agELast)) * dt;
        vgD += (0.5 * (agD + agDLast)) * dt;
        agNLast = agN;
        agELast = agE;
        agDLast = agD;

        pgX += (0.5 * (vgN + vgXLast)) * dt;
        pgY += (0.5 * (vgE + vgYLast)) * dt;
        pgZ += (0.5 * (vgD + vgZLast)) * dt;
        vgXLast = vgN;
        vgYLast = vgE;
        vgZLast = vgD;
    }

    private Signal imuCalcsSig = null;

    public void printCols(lineType lineT, boolean valid) throws IOException {

        recIMU.printCsvValue(vgN, imuCalcsSig, "VelN", lineT, valid);
        recIMU.printCsvValue(vgE, imuCalcsSig, "VelE", lineT, valid);
        recIMU.printCsvValue(vgD, imuCalcsSig, "VelD", lineT, valid);
        recIMU.printCsvValue(pgX, imuCalcsSig, "PosN", lineT, valid);
        recIMU.printCsvValue(pgY, imuCalcsSig, "PosE", lineT, valid);
        recIMU.printCsvValue(pgZ, imuCalcsSig, "PosD", lineT, valid);
        recIMU.printCsvValue(agN, imuCalcsSig, "agX", lineT, valid);
        recIMU.printCsvValue(agE, imuCalcsSig, "agY", lineT, valid);
        recIMU.printCsvValue(agD, imuCalcsSig, "agZ", lineT, valid);
        recIMU.printCsvValue(abX, imuCalcsSig, "abX", lineT, valid);
        recIMU.printCsvValue(abY, imuCalcsSig, "abY", lineT, valid);
        recIMU.printCsvValue(abZ, imuCalcsSig, "abZ", lineT, valid);

        recIMU.printCsvValue(recIMU.getVn() - vgN, imuCalcsSig,
                "getVelN() - vgX", lineT, valid);
        recIMU.printCsvValue(recIMU.getVe() - vgE, imuCalcsSig, "getVE() - vgY",
                lineT, valid);
        recIMU.printCsvValue(recIMU.getVd() - vgD, imuCalcsSig, "getVd() - vgZ",
                lineT, valid);
    }

    public void computeAccel(double pitch, double roll, double yaw, float abX,
            float abY, float abZ, double dt) {

        this.abX = abX;
        this.abY = abY;
        this.abZ = abZ;
        if (!Double.isNaN(pitch) && !Double.isNaN(roll) && !Double.isNaN(yaw)) {
            agN = abX * Math.cos(pitch) * Math.cos(yaw)
                    + abY * (Math.sin(roll) * Math.sin(pitch) * Math.cos(yaw)
                            - Math.cos(roll) * Math.sin(yaw))
                    + abZ * (Math.sin(roll) * Math.sin(yaw)
                            + Math.cos(roll) * Math.sin(pitch) * Math.cos(yaw));

            agE = abX * Math.cos(pitch) * Math.sin(yaw)
                    + abY * (Math.cos(roll) * Math.cos(yaw)
                            + Math.sin(roll) * Math.sin(pitch) * Math.sin(yaw))
                    + abZ * (Math.cos(roll) * Math.sin(pitch) * Math.sin(yaw)
                            - Math.sin(roll) * Math.cos(yaw));

            agD = (-abX * Math.sin(pitch)
                    + abY * Math.sin(roll) * Math.cos(pitch)
                    + abZ * Math.cos(roll) * Math.cos(pitch)) + 1;

            vgN += (0.5 * ((agN * 9.80665) + agNLast)) * dt;
            vgE += (0.5 * ((agE * 9.80665) + agELast)) * dt;
            vgD += (0.5 * (((agD + 1.0) * 9.80665) + agDLast)) * dt;

            agNLast = agN;
            agELast = agE;
            agDLast = agD;
        }
    }

}
