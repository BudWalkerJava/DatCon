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
package src.V1.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.DatFile;
import src.Files.Util;
import src.Files.ConvertDat.lineType;

public class Record207 extends Record {

    // 200 hZ
    // length 122

    public static Record207 current = null;

    public double longRad = 0.0;

    public double latRad = 0.0;

    // public double longDeg = 0.0;
    //
    // public double latDeg = 0.0;

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

    public int magX = 0;

    public int magY = 0;

    public int magZ = 0;

    boolean valid = false;

    public float diffX = (float) 0.0;

    public float diffY = (float) 0.0;

    public float diffZ = (float) 0.0;

    float x4 = (float) 0.0;

    float x5 = (float) 0.0;

    float x6 = (float) 0.0;

    public int imuTemp = 0;

    int i2 = 0;

    int i3 = 0;

    int i4 = 0;

    int i5 = 0;

    public double totalZGyro = 0.0;

    public long lastTickNo = 0;

    // distance travelled stuff
    long dtLastTickNo = 0;

    public double distanceTraveled = 0.0;

    double dtLastLat = 0.0;

    double dtLastLong = 0.0;

    public double bearingDeclined = 0.0;

    public double bearingTrue = 0.0;
    //
    //    private ConvertDat convertDat;
    //
    //    private DatFile _datFile;

    public Record207(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 207;
        _subType = 1;
    }

    public void process(Payload _payload) {
        super.process(_payload);
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
        imuTemp = payloadBB.getShort(106);
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
                bearingTrue = Util.bearing(dtLastLat, dtLastLong, latRad,
                        longRad);
                double x = bearingTrue - convertDat.declination;
                if (x < -180.0)
                    bearingDeclined = 360.0 + x;
                else if (x > 180.0)
                    bearingDeclined = x - 360.0;
                else
                    bearingDeclined = x;
                distanceTraveled += dist;
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

    @Override
    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called");
    }
}
