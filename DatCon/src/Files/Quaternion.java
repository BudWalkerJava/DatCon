/* Quaternion class

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
package src.Files;

import java.text.DecimalFormat;

/******************************************************************************
 * Compilation: javac Quaternion.java Execution: java Quaternion
 * 
 * Data type for quaternions.
 * 
 * http://mathworld.wolfram.com/Quaternion.html
 * 
 * The data type is "immutable" so once you create and initialize a Quaternion,
 * you cannot change it.
 * 
 * % java Quaternion
 * 
 ******************************************************************************/

public class Quaternion {
    static DecimalFormat df = new DecimalFormat("0.####");

    private final double SCALAR; // scalar

    private final double X, Y, Z; // vector

    // create a new object with the given components
    public Quaternion(double scalar, double x1, double x2, double x3) {
        this.SCALAR = scalar;
        this.X = x1;
        this.Y = x2;
        this.Z = x3;
    }

    public Quaternion(double x, double y, double z) {
        // Converts 3 angles to a Quaternion
        x *= 0.5d;
        y *= 0.5d;
        z *= 0.5d;

        double c1 = (double) Math.cos(z);
        double c2 = (double) Math.cos(y);
        double c3 = (double) Math.cos(x);

        double s1 = (double) Math.sin(z);
        double s2 = (double) Math.sin(y);
        double s3 = (double) Math.sin(x);

        this.SCALAR = c1 * c2 * c3 - s1 * s2 * s3;
        this.X = c1 * s2 * c3 - s1 * c2 * s3;
        this.Y = s1 * s2 * c3 + c1 * c2 * s3;
        this.Z = s1 * c2 * c3 + c1 * s2 * s3;
    }

    public static Quaternion fromAngles(double pitch, double roll, double yaw) {
        // x=pitch, y=roll, z=yaw??????
        pitch *= 0.5d;
        roll *= 0.5d;
        yaw *= 0.5d;

        double c1 = (double) Math.cos(yaw);
        double c2 = (double) Math.cos(roll);
        double c3 = (double) Math.cos(pitch);

        double s1 = (double) Math.sin(yaw);
        double s2 = (double) Math.sin(roll);
        double s3 = (double) Math.sin(pitch);

        return new Quaternion(c1 * c2 * c3 - s1 * s2 * s3,
                c1 * s2 * c3 - s1 * c2 * s3, s1 * s2 * c3 + c1 * c2 * s3,
                s1 * c2 * c3 + c1 * s2 * s3);
    }

    public static Quaternion fromVector(double x, double y, double z) {
        double xy = Math.atan2(y, x);
        double xz = Math.atan2(z, x);
        double yz = Math.atan2(y, z);
        Quaternion retv = new Quaternion(yz, xz, xy);
        return retv;
    }

    public static Quaternion fromXYVector(double x, double y) {
        double xy = Math.atan2(y, x);
        Quaternion retv = new Quaternion(0.0, 0.0, xy);
        return retv;
    }

    public static Quaternion fromZYVector(double z, double y) {
        double zy = Math.atan2(z, y);
        Quaternion retv = new Quaternion(0.0, zy, 0.0);
        return retv;
    }

    public static Quaternion fromZXVector(double z, double x) {
        double zx = Math.atan2(z, x);
        Quaternion retv = new Quaternion(zx, 0.0, 0.0);
        return retv;
    }

    // return a string representation of the invoking object
    public String toString() {
        return df.format(SCALAR) + " + " + df.format(X) + "i + " + df.format(Y)
                + "j + " + df.format(Z) + "k";
    }

    // return the quaternion norm
    public double norm() {
        return Math.sqrt(SCALAR * SCALAR + X * X + Y * Y + Z * Z);
    }

    // return the quaternion conjugate
    public Quaternion conjugate() {
        return new Quaternion(SCALAR, -X, -Y, -Z);
    }

    // return a new Quaternion whose value is (this + b)
    public Quaternion plus(Quaternion b) {
        Quaternion a = this;
        return new Quaternion(a.SCALAR + b.SCALAR, a.X + b.X, a.Y + b.Y,
                a.Z + b.Z);
    }

    // return a new Quaternion whose value is (this * b)
    public Quaternion times(Quaternion b) {
        Quaternion a = this;
        double y0 = a.SCALAR * b.SCALAR - a.X * b.X - a.Y * b.Y - a.Z * b.Z;
        double y1 = a.SCALAR * b.X + a.X * b.SCALAR + a.Y * b.Z - a.Z * b.Y;
        double y2 = a.SCALAR * b.Y - a.X * b.Z + a.Y * b.SCALAR + a.Z * b.X;
        double y3 = a.SCALAR * b.Z + a.X * b.Y - a.Y * b.X + a.Z * b.SCALAR;
        return new Quaternion(y0, y1, y2, y3);
    }

    // return a new Quaternion whose value is the inverse of this
    public Quaternion inverse() {
        double d = SCALAR * SCALAR + X * X + Y * Y + Z * Z;
        return new Quaternion(SCALAR / d, -X / d, -Y / d, -Z / d);
    }

    // return a / b
    // we use the definition a * b^-1 (as opposed to b^-1 a)
    public Quaternion divides(Quaternion b) {
        Quaternion a = this;
        return a.times(b.inverse());
    }

    public RollPitchYaw toRollPitchYaw() {
        double sqW = SCALAR * SCALAR;
        double sqX = X * X;
        double sqY = Y * Y;
        double sqZ = Z * Z;
        double yaw = 0.0;
        double pitch = 0.0;
        double roll = 0.0;
        double unit = sqX + sqY + sqZ + sqW; // if normalised is one, otherwise
                                             // is correction factor
        double test = SCALAR * X + Y * Z;
        if (test > 0.499 * unit) { // singularity at north pole
            yaw = 2 * Math.atan2(Y, SCALAR);
            pitch = Math.PI / 2;
            roll = 0;
        } else if (test < -0.499 * unit) { // singularity at south pole
            yaw = -2 * Math.atan2(Y, SCALAR);
            pitch = -Math.PI / 2;
            roll = 0;
        } else {
            yaw = Math.atan2(2.0 * (SCALAR * Z - X * Y),
                    1.0 - 2.0 * (sqZ + sqX));
            // pitch = Math.asin(2.0 * test / unit);
            // roll = Math.atan2(2.0 * (W * Y - X * Z), 1.0 - 2.0 * (sqY +
            // sqX));
            roll = Math.asin(2.0 * test / unit);
            pitch = Math.atan2(2.0 * (SCALAR * Y - X * Z),
                    1.0 - 2.0 * (sqY + sqX));
        }
        return (new RollPitchYaw(roll, pitch, yaw));
    }

    public double[] toEuler() {
        double sqW = SCALAR * SCALAR;
        double sqX = X * X;
        double sqY = Y * Y;
        double sqZ = Z * Z;
        double yaw = 0.0;
        double roll = 0.0;
        double pitch = 0.0;
        double[] retv = new double[3];
        double unit = sqX + sqY + sqZ + sqW; // if normalised is one, otherwise
                                             // is correction factor
        double test = SCALAR * X + Y * Z;
        if (test > 0.499 * unit) { // singularity at north pole
            yaw = 2 * Math.atan2(Y, SCALAR);
            pitch = Math.PI / 2;
            roll = 0;
        } else if (test < -0.499 * unit) { // singularity at south pole
            yaw = -2 * Math.atan2(Y, SCALAR);
            pitch = -Math.PI / 2;
            roll = 0;
        } else {
            yaw = Math.atan2(2.0 * (SCALAR * Z - X * Y),
                    1.0 - 2.0 * (sqZ + sqX));
            roll = Math.asin(2.0 * test / unit);
            pitch = Math.atan2(2.0 * (SCALAR * Y - X * Z),
                    1.0 - 2.0 * (sqY + sqX));
        }
        retv[0] = pitch;
        retv[1] = roll;
        retv[2] = yaw;
        return retv;
    }

    public double getScalar() {
        return SCALAR;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    //

}
