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
package src.V3.Generic.DatConRecs;

import java.util.ArrayList;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.TypeSubType;
import src.Files.ConvertDat.lineType;

public class HomePoint extends Record {

    public short rthHeight = 0;

    public boolean valid = false;

    public double longitudeHP;

    public double latitudeHP;

    private float height = 0f;

    public HomePoint(ConvertDat convertDat) {
        super(convertDat);
    }

    public static ArrayList<Record> create(ConvertDat convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        TypeSubType hpTST = convertDat.getDatFile().getTypeSubType(13);
        if (hpTST != null) {
            HomePoint hpRec = new HomePoint(convertDat);
            hpRec.setType(hpTST.getType());
            hpRec.setSubType(13);
            retv.add(hpRec);
            DatConLog.Log("HomePoint adds " + hpRec);
        }
        return retv;
    }

    //    public static Signal magRawSig = Signal.SeriesFloat("MagRaw",
    //            "Magnetometer", null, Units.aTesla);

    public void process(Payload _payload) {
        super.process(_payload);
        double longRad = payloadBB.getDouble(0);
        double latRad = payloadBB.getDouble(8);
        height = payloadBB.getFloat(16);
        short homeState = payloadBB.getShort(16);
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

    }
    //    name    osd_home
    //    type    13
    //    double  osd_lon
    //    double  osd_lat
    //    float   osd_alt
    //    uint16_t osd_home_state
    //    uint16_t fixed_altitedue
    //    int16_t  course_lock_torsion
    //    expr     E_homepoint_set       bitand(osd_home_state,1)
    //    expr     E_method              bitand(shift_r(osd_home_state,1),1)
    //    expr     E_heading             bitand(shift_r(osd_home_state,2),1)
    //    expr     E_is_dyn_homepoint    bitand(shift_r(osd_home_state,3),1)
    //    expr     E_multiple            bitand(shift_r(osd_home_state,6),1)
    //    expr     E_ioc_enable          bitand(shift_r(osd_home_state,12),1)

    private static Signal rthHeightSig = Signal.SeriesDouble("HP:rthHeight",
            "Return To Home Height", null, Units.meters);

    private final static Signal hpLongitudeSig = Signal
            .SeriesDouble("HP:Longitude", "Longitude", null, Units.degrees180);

    private final static Signal hpLatitudeSig = Signal
            .SeriesDouble("HP:Latitude", "Longitude", null, Units.degrees180);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(convertDat.longitudeHPDegrees, hpLongitudeSig, "",
                    lineT, valid);
            printCsvValue(convertDat.latitudeHPDegrees, hpLatitudeSig, "",
                    lineT, valid);
            printCsvValue(rthHeight, rthHeightSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
