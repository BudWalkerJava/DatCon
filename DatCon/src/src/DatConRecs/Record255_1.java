/* Record255_1 class

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

package src.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatFile;
import src.Files.TSAGeoMag;
import src.Files.ConvertDat.lineType;

public class Record255_1 extends Record {

    public static TSAGeoMag geoMag = new TSAGeoMag();

    public String text = "";
    //
    //    protected DatFile datFile;

    protected long timeOffset = 0;

    protected String payloadString;

    public Record255_1(ConvertDat convertDat) {
        super(convertDat);
        _type = 255;
        _subType = 1;
        timeOffset = convertDat.timeOffset;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        timeOffset = convertDat.timeOffset;
        payloadString = _payload.getCleanString();
        if (payloadString.length() > 0) {
            if (convertDat.csvEventLogOutput
                    && convertDat.tickRangeLower <= _payload.getTickNo()) {
                if (text.length() > 0)
                    text += "|";
                text += payloadString;
            }
            if (convertDat.eloPS != null
                    && convertDat.tickRangeLower <= _payload.getTickNo()) {
                long tickNo = _payload.getTickNo();
                convertDat.eloPS.println(_datFile.timeString(tickNo, timeOffset)
                        + " : " + tickNo + " : " + payloadString);
            }
        }
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            if (convertDat.csvEventLogOutput) {
                String noComma = text.replaceAll(",", ".");
                printCsvValue(noComma, "eventLog", lineT, true);
            }
            text = "";
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
