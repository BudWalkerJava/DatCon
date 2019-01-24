/* Record157_80 class

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
package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class RecSmartBatt10_1712 extends Record {

    public int goHomeBatt = 0;

    public int landBatt = 0;

    public int goHomeTime = 0;

    public int landTime = 0;

    public boolean valid = false;

    public RecSmartBatt10_1712(ConvertDat convertDat) {
        super(convertDat, 1712, 10);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            goHomeBatt = _payload.getUnsignedShort(2);
            landBatt = _payload.getUnsignedShort(4);
            goHomeTime = _payload.getUnsignedShort(6);
            landTime = _payload.getUnsignedShort(8);
            valid = true;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    //    name        smart_battery_info
    //    type    1712
    //    Op.uint8_t      go_home_cnt 0
    //    Op.uint8_t      go_home_cmd 0
    //    Op.uint16_t     gh_level 0
    //    Op.uint16_t     land_level 0
    //    Op.uint16_t     fly_t_for_gh 0
    //    Op.uint16_t     fly_t_for_land 0

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(goHomeBatt, AxesAndSigs.battGoHome, "", lineT, valid);
            printCsvValue(landBatt, AxesAndSigs.battLand, "", lineT, valid);
            printCsvValue(goHomeTime, AxesAndSigs.battGoHomeTime, "", lineT,
                    valid);
            printCsvValue(landTime, AxesAndSigs.battLandTime, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
