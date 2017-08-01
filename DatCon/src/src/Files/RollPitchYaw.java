/* RollPitchYaw class

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

public class RollPitchYaw {
    static DecimalFormat df = new DecimalFormat("0.####");

    double roll = 0.0;

    double pitch = 0.0;

    public double yaw = 0.0;

    public RollPitchYaw(double roll, double pitch, double yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public String toString() {
        return "Roll: " + df.format(roll) + " Pitch: " + df.format(pitch)
                + " Yaw: " + df.format(yaw);
    }

    public String toDegString() {
        return "Roll: " + df.format(Math.toDegrees(roll)) + " Degs"
                + " Pitch: " + df.format(Math.toDegrees(pitch)) + " Degs"
                + " Yaw: " + df.format(Math.toDegrees(yaw)) + " Degs";
    }

    public double getRollDeg() {
        return Math.toDegrees(roll);
    }

    public double getPitchDeg() {
        return Math.toDegrees(pitch);
    }

    public double getYawDeg() {
        return Math.toDegrees(yaw);
    }

    public RollPitchYaw(Quaternion q) {

    }

    public RollPitchYaw() {
    }
}
