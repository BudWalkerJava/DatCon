/* Record152_0 class

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

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class HomePoint34_13 extends Record {

    public short rthHeight = 0;

    public boolean valid = false;

    public double longitudeHP;

    public double latitudeHP;

    private float height = 0f;

    public HomePoint34_13(ConvertDat convertDat) {
        super(convertDat, 13, 34);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            double longRad = payloadBB.getDouble(0);
            double latRad = payloadBB.getDouble(8);
            height = payloadBB.getFloat(16);
            //short homeState = payloadBB.getShort(20);
            rthHeight = payloadBB.getShort(22);
            longitudeHP = Math.toDegrees(longRad);
            latitudeHP = Math.toDegrees(latRad);
            if (!valid) {
                if (longRad < 100.0 && latRad < 100.0) {
                    valid = true;
                }
            }
            if (valid) {
                convertDat.processHomePoint(latitudeHP, longitudeHP, height);
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }
    //    name    osd_home
    //    type    13
    //    double  osd_lon
    //    double  osd_lat
    //    float   osd_alt
    //    uint16_t osd_home_state
    //    uint16_t fixed_altitedue
    //    int16_t  course_lock_torsion

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(convertDat.longitudeHPDegrees,
                    AxesAndSigs.hpLongitudeSig, "", lineT, valid);
            printCsvValue(convertDat.latitudeHPDegrees,
                    AxesAndSigs.hpLatitudeSig, "", lineT, valid);
            printCsvValue(rthHeight, AxesAndSigs.rthHeightSig, "", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
