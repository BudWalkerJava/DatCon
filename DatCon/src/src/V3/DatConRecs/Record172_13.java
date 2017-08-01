/* Record172_13 class

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
package src.V3.DatConRecs;

import src.DatConRecs.AxesAndSigs;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.DatConLog;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class Record172_13 extends Record {

    // length 70, 3.5Hz
    // 52 -65 serial number

    public static Record172_13 current = null;

    public short rthHeight = 0;

    public boolean valid = false;

    public double longitudeHP;

    public double latitudeHP;

    public Record172_13(ConvertDat convertDat) {
        super(convertDat);
        _type = 172;
        _subType = 13;
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        double longRad = payloadBB.getDouble(0);
        double latRad = payloadBB.getDouble(8);
        longitudeHP = Math.toDegrees(longRad);
        latitudeHP = Math.toDegrees(latRad);
        if (!valid) {
            if (longRad < 100.0 && latRad < 100.0) {
                valid = true;
            }
        }
        rthHeight = payloadBB.getShort(22);
    }

    public static Signal rthHeightSig = Signal.SeriesDouble("rthHeight",
            "Return To Home Height", null, Units.meters);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(rthHeight, rthHeightSig, "", lineT, valid);

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
