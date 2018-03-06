/* Record190_27 class

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

// 50 Hz

import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class RecRCStat18_1700 extends RCStatus {

    public RecRCStat18_1700(ConvertDat convertDat) {
        super(convertDat, 1700, 18);
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            statusValid = true;
            fail_safe = payloadBB.get(2);
            data_lost = ((payloadBB.get(4) == 1) ? "lost" : "");
            app_lost = ((payloadBB.get(5) == 1) ? "lost" : "");
            frame_lost = payloadBB.get(6);
            rec_cnt = ((long) payloadBB.getInt(7) & 0xffffffffL);
            connected = ((payloadBB.get(13) == 1) ? "Connected"
                    : "Disconnected");
            super.common();
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        super.printCols(lineT);
        try {
            printCsvValue(connected, RCStateSig, "connected", lineT,
                    statusValid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
