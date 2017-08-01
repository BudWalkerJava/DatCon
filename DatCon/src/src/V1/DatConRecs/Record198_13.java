/* Record198_13 class

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

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.TSAGeoMag;
import src.Files.Util;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class Record198_13 extends Record {
    //length 36
    // 1 hZ
    public static Record198_13 current = null;

    private double longitudeHP = 0.0;

    private double latitudeHP = 0.0;

    private boolean valid = false;

    private double declination = 0.0;

    public static TSAGeoMag geoMag = new TSAGeoMag();

    public Record198_13(ConvertDat convertDat) {
        super(convertDat);
        _type = 198;
        _subType = 13;
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        payloadBB = _payload.getBB();
        double longRad = payloadBB.getDouble(0);
        double latRad = payloadBB.getDouble(8);
        if (!valid) {
            // following depends on .DAT having 800000.0 for these values before they're good
            if (longRad < 100.0 && latRad < 100.0) {
                valid = true;
                double longDeg = Math.toDegrees(longRad);
                double latDeg = Math.toDegrees(latRad);
                declination = geoMag.getDeclination(latDeg, longDeg);
            }
        }
        if (valid) {
            longitudeHP = longRad;
            latitudeHP = latRad;
        }
    }
    @Override
    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called");
    }
}
