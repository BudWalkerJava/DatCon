package src.Files;

import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;

public class MagYaw {

    private double magYaw = 0.0;

    private static boolean debug = true;

    static double halfInterval = 3.0;

    //    private RealInterval magYawRIDegrees = null;
    private RealInterval magYawRI = null;

    private double magYawDegrees;

    public void compute(double pitch, double roll, int magX, int magY, int magZ,
            float magMod) {

        double xmag = ((double) magX) / magMod;
        double ymag = ((double) magY) / magMod;
        double zmag = ((double) magZ) / magMod;
        double X1 = (-ymag * Math.cos(roll) + zmag * Math.sin(roll));
        double X10 = (xmag * Math.cos(pitch));
        double X11 = ymag * Math.sin(pitch) * Math.sin(roll);
        double X12 = zmag * Math.sin(pitch) * Math.cos(roll);
        double X2 = X10 + X11 + X12;
        double magYaw = Math.atan2(
                (-ymag * Math.cos(roll) + zmag * Math.sin(roll)),
                (xmag * Math.cos(pitch)
                        + ymag * Math.sin(pitch) * Math.sin(roll)
                        + zmag * Math.sin(pitch) * Math.cos(roll)));
        magYawDegrees = Math.toDegrees(magYaw);

        double E_yaw_from_m_00 = Math
                .atan2(-(magY * Math.cos(roll) - magZ * Math.sin(roll)),
                        magX * Math.cos(pitch)
                                + magY * Math.sin(pitch) * Math.sin(roll)
                                + magZ * Math.sin(pitch) * Math.cos(roll));
        double diff = Math.abs(magYaw - E_yaw_from_m_00);
        if (Math.abs(diff) > 1.0e-15) {
            System.out.println("Diff " + diff);
        }

        magYawRI = null;

        if (!Double.isNaN(pitch) && !Double.isNaN(roll)) {
            //            double halfMagMod = halfInterval / magMod;
            //            RealInterval xMagRI = new RealInterval(xmag - halfMagMod,
            //                    xmag + halfMagMod);
            //            RealInterval yMagRI = new RealInterval(ymag - halfMagMod,
            //                    ymag + halfMagMod);
            //            RealInterval zMagRI = new RealInterval(zmag - halfMagMod,
            //                    zmag + halfMagMod);

            RealInterval xMagRI = new RealInterval(magX - halfInterval,
                    magX + halfInterval);
            RealInterval yMagRI = new RealInterval(magY - halfInterval,
                    magY + halfInterval);
            RealInterval zMagRI = new RealInterval(magZ - halfInterval,
                    magZ + halfInterval);
            RealInterval rollRI = new RealInterval(roll, roll);
            RealInterval pitchRI = new RealInterval(pitch, pitch);
            RealInterval x1 = IAMath.sub(IAMath.mul(zMagRI, IAMath.sin(rollRI)),
                    IAMath.mul(yMagRI, IAMath.cos(rollRI)));
            RealInterval x10 = IAMath.mul(xMagRI, IAMath.cos(pitchRI));
            RealInterval x11 = IAMath.mul(yMagRI,
                    IAMath.mul(IAMath.sin(pitchRI), IAMath.sin(rollRI)));
            RealInterval x12 = IAMath.mul(zMagRI,
                    IAMath.mul(IAMath.sin(pitchRI), IAMath.cos(rollRI)));
            RealInterval x2 = IAMath.add(x10, IAMath.add(x11, x12));
            //            System.out.println("X1 " + X1 + " X10 " + X10 + " X11 " + X11
            //                    + " X12 " + X12 + " X2 " + X2);
            //            System.out.println("x1 " + x1 + " x10 " + x10 + " x11" + x11
            //                    + " x12 " + x12 + " x2 " + x2);
            magYawRI = intervalAtan2(x1, x2);
            if (magYawRI != null) {
                if (!(magYawRI.lo() <= magYaw && magYaw <= magYawRI.hi())) {
                    System.out.println(
                            "MagYaw not contained " + magYawRI + " " + magYaw);
                }
            }
        }
    }

    public static void main(String[] args) {
        MagYaw mYaw = new MagYaw();
        double halfInterval = 0.05;

        //        int magX = 614;
        //        int magY = -155;
        //        int magZ = 1341;
        //        int iPitch = -29;
        //        int iRoll = -4;
        int magX = 667;
        int magY = -203;
        int magZ = 1328;
        int iPitch = -34;
        int iRoll = -9;
        float magMod = (float) Math.sqrt(((double) magX * (double) magX)
                + ((double) magY * (double) magY)
                + ((double) magZ * (double) magZ));
        //        for (int iPitch = -2; iPitch < 4; iPitch++) {
        //            for (int iRoll = -2; iRoll < 4; iRoll++) {

        double pitch = Math.toRadians((double) iPitch);
        double roll = Math.toRadians((double) iRoll);

        mYaw.compute(pitch, roll, magX, magY, magZ, magMod);
        System.out.println("degrees " + mYaw.getDegrees() + " ,mYaw.EB "
                + mYaw.getErrorBound() + " pitch " + iPitch + " Roll " + iRoll);
        //            }
        //        }

        //        for (int i = 0; i < 160; i++) {
        //            double theta = Math.PI * ((double) i) / 80.0;
        //            double y = Math.cos(theta);
        //            double x = Math.sin(theta);
        //            RealInterval xI = new RealInterval(x - halfInterval,
        //                    x + halfInterval);
        //            RealInterval yI = new RealInterval(y - halfInterval,
        //                    y + halfInterval);
        //            double at = Math.atan2(y, x);
        //            RealInterval atI = intervalAtan2(yI, xI);
        //            if (atI == null) {
        //                System.out.println("ATI null " + i + " theta " + theta);
        //            }
        //            if (atI != null && !(atI.contains(at))) {
        //                System.out.println("i " + i + " AT " + at + " ATI " + atI);
        //            }
        //        }
    }

    public double getErrorBound() {
        if (magYawRI != null) {
            return Math.toDegrees((magYawRI.hi() - magYawRI.lo()));
        }
        return Double.NaN;
    }

    public static boolean intervalGtrZero(RealInterval interval) {
        return (interval.lo() > 0.0 && interval.hi() > 0.0);
    }

    public static boolean intervalLTZero(RealInterval interval) {
        return (interval.lo() < 0.0 && interval.hi() < 0.0);
    }

    public static boolean intervalEQZero(RealInterval interval) {
        return (interval.lo() == 0.0 && interval.hi() == 0.0);
    }

    public static boolean intervalGTEQzero(RealInterval interval) {
        return (interval.lo() >= 0.0 && interval.hi() >= 0.0);
    }

    public static RealInterval piOver2 = new RealInterval(Math.PI / 2.0,
            Math.PI / 2.0);

    public static RealInterval piInterval = new RealInterval(Math.PI, Math.PI);

    public static RealInterval minusPiOver2 = new RealInterval(-Math.PI / 2.0,
            -Math.PI / 2.0);

    public static RealInterval intervalAtan2(RealInterval y, RealInterval x) {
        RealInterval retv = null;
        if (intervalGtrZero(x)) {
            retv = IAMath.atan(IAMath.div(y, x));
        } else if (intervalLTZero(x) && intervalGTEQzero(y)) {
            retv = IAMath.add(IAMath.atan(IAMath.div(y, x)), piInterval);
        } else if (intervalLTZero(x) && intervalLTZero(y)) {
            retv = IAMath.sub(IAMath.atan(IAMath.div(y, x)), piInterval);
        } else if (intervalEQZero(x) && intervalGtrZero(y)) {
            retv = piOver2;
        } else if (intervalEQZero(x) && intervalLTZero(y)) {
            retv = minusPiOver2;
        } else {
            retv = null;
        }
        return retv;

    }

    public double getDegrees() {
        return magYawDegrees;
    }

    public boolean validInterval() {
        return (magYawRI != null);
    }

}
