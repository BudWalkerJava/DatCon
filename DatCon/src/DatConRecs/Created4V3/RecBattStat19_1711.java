/* Record144_106 class

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
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;

public class RecBattStat19_1711 extends Record {

    public static RecBattStat19_1711 current = null;

    public RecBattStat19_1711(ConvertDat convertDat) {
        super(convertDat, 1711, 19);
    }

    String status = "";

    public void process(Payload _payload) {
        super.process(_payload);
        status = "";
        if (payloadBB.get(0) != 0x00) {
            status += "NotReady|";
        }
        if (payloadBB.get(1) != 0x00) {
            status += "CommError|";
        }
        if (payloadBB.get(5) != 0x00) {
            status += "VolVeryLow|";
        }
        if (payloadBB.get(6) != 0x00) {
            status += "VolNotSafe|";
        }
        if (status.equalsIgnoreCase("")) {
            status = "OK";
        }
    }

    //    name        battery_status
    //    type    1711
    //    Op.uint8_t      not_ready 0
    //    Op.uint8_t      comm_err 0
    //    Op.uint8_t      first_auth 0
    //    Op.uint8_t      auth_fail 0
    //    Op.uint8_t      need_re 0
    //    Op.uint8_t      volVerylow 0
    //    Op.uint8_t      volNotsafe 0
    //    Op.uint8_t      volLevel1 0
    //    Op.uint8_t      vollevel2 0
    //    Op.uint8_t      capLevel1 0
    //    Op.uint8_t      capLevel2 0
    //    Op.uint8_t      smartCap1 0
    //    Op.uint8_t      smartCap2 0
    //    Op.uint8_t      d_flg 0
    //    Op.uint8_t      ccsc 0
    //    Op.uint32_t     all 0

    private static Signal batteryStatusSig = Signal.State("Battery:status",
            "Battery Status", "OK");

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(status, batteryStatusSig, "", lineT, true);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
