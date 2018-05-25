package src.Files;

import src.DatConRecs.RecIMU;

public class MagYaw {

    private static boolean debug = false;

    static double halfInterval = 3.0;

    private double magYawDegrees;

    public static double compute(short magX, short magY, short magZ,
            float magMod) {
        double xmag = ((double) magX) / magMod;
        double ymag = ((double) magY) / magMod;
        double zmag = ((double) magZ) / magMod;
        double pitch = RecIMU.current.getPitchRadians();
        double roll = RecIMU.current.getRollRadians();

        double magYaw = Math.atan2(
                (-ymag * Math.cos(roll) + zmag * Math.sin(roll)),
                (xmag * Math.cos(pitch)
                        + ymag * Math.sin(pitch) * Math.sin(roll)
                        + zmag * Math.sin(pitch) * Math.cos(roll)));
        return Math.toDegrees(magYaw);
    }

    public void compute(double pitch, double roll, int magX, int magY, int magZ,
            float magMod) {
        double xmag = ((double) magX) / magMod;
        double ymag = ((double) magY) / magMod;
        double zmag = ((double) magZ) / magMod;

        double magYaw = Math.atan2(
                (-ymag * Math.cos(roll) + zmag * Math.sin(roll)),
                (xmag * Math.cos(pitch)
                        + ymag * Math.sin(pitch) * Math.sin(roll)
                        + zmag * Math.sin(pitch) * Math.cos(roll)));
        magYawDegrees = Math.toDegrees(magYaw);
        if (debug) {
            double E_yaw_from_m_00 = Math.atan2(
                    -(magY * Math.cos(roll) - magZ * Math.sin(roll)),
                    magX * Math.cos(pitch)
                            + magY * Math.sin(pitch) * Math.sin(roll)
                            + magZ * Math.sin(pitch) * Math.cos(roll));
            double diff = Math.abs(magYaw - E_yaw_from_m_00);
            if (Math.abs(diff) > 1.0e-15) {
                if (Persist.EXPERIMENTAL_DEV) {
                    System.out.println("Diff " + diff);
                }
            }
        }

    }

    public double getDegrees() {
        return magYawDegrees;
    }

    //    private double accelYawDegrees = 0.0;
    //
    //    public double getAccelYawDegrees() {
    //        return accelYawDegrees;
    //    }
    //
    //    private double accelMod = 0.0;
    //
    //    public double getAccelMod() {
    //        return accelMod;
    //    }
    //
    //    public void computeAccel(double pitch, double roll, float accelX,
    //            float accelY, float accelZ, double dt) {
    //        double accelYaw = Math.atan2(
    //                (-accelY * Math.cos(roll) + accelZ * Math.sin(roll)),
    //                (accelX * Math.cos(pitch)
    //                        + accelY * Math.sin(pitch) * Math.sin(roll)
    //                        + accelZ * Math.sin(pitch) * Math.cos(roll)));
    //        accelYawDegrees = Math.toDegrees(accelYaw);
    //        accelMod = Math.sqrt(accelX * accelX + accelY * accelY
    //                + (accelZ + 1.0) * (accelZ + 1.0));
    //    }

}
