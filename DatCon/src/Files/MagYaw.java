package src.Files;

import src.DatConRecs.Record_2048;

public class MagYaw {

    //private double magYaw = 0.0;

    private static boolean debug = false;

    static double halfInterval = 3.0;

    //    private RealInterval magYawRIDegrees = null;
    //private RealInterval magYawRI = null;

    private double magYawDegrees;

    //    double X1 = 0.0;
    //
    //    double X2 = 0.0;
    //
    //    double X10 = 0.0;
    //
    //    double X11 = 0.0;
    //
    //    double X12 = 0.0;

    public static double compute(short magX, short magY, short magZ,
            float magMod) {
        double xmag = ((double) magX) / magMod;
        double ymag = ((double) magY) / magMod;
        double zmag = ((double) magZ) / magMod;
        double pitch = Record_2048.current.getPitchRadians();
        double roll = Record_2048.current.getRollRadians();

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
        //        X1 = (-ymag * Math.cos(roll) + zmag * Math.sin(roll));
        //        X10 = (xmag * Math.cos(pitch));
        //        X11 = ymag * Math.sin(pitch) * Math.sin(roll);
        //        X12 = zmag * Math.sin(pitch) * Math.cos(roll);
        //        X2 = X10 + X11 + X12;

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
 

}
