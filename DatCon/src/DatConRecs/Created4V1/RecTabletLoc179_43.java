/* Record193_43 class

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
package src.DatConRecs.Created4V1;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

// 15 HZ
public class RecTabletLoc179_43 extends Record {

    public static RecTabletLoc179_43 current = null;

    public double longRad = 0.0;

    public double latRad = 0.0;

    public double longitudeTablet = 0.0;

    public double latitudeTablet = 0.0;

    public boolean valid = false;

    public RecTabletLoc179_43(ConvertDat convertDat) {
        super(convertDat, 43, 179);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            latRad = payloadBB.getDouble(155);
            longRad = payloadBB.getDouble(163);
            longitudeTablet = Math.toDegrees(longRad);
            latitudeTablet = Math.toDegrees(latRad);
            if (!valid) {
                if (longitudeTablet != 0.0 && latitudeTablet != 0.0) {
                    valid = true;
                }
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public final static Signal tabletGPSSig = Signal.SeriesDouble("Tablet",
            "Tablet", null, Units.degrees180);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(longitudeTablet, tabletGPSSig, "Longitude", lineT,
                    valid);
            printCsvValue(latitudeTablet, tabletGPSSig, "Latitude", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
