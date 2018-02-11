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

package src.DatConRecs.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.V3.Files.ConvertDatV3;
import src.Files.DatConLog;
import src.Files.TSAGeoMag;

public class RecFlyLog_32768 extends Record {

    public static TSAGeoMag geoMag = new TSAGeoMag();

    public String text = "";

    protected long timeOffset = 0;

    protected String payloadString;

    Pattern altiPattern = Pattern.compile(".*alti:\\s*(-*\\d+\\.\\d+)");

    Pattern dateTimePattern = Pattern.compile(".*\\[(.+)\\](.+)\\s");

    Pattern mcIDPattern = Pattern.compile(".*\\:(.+)");

    Pattern mcVerPattern = Pattern.compile(".*\\:(.+)");

    public RecFlyLog_32768(ConvertDat convertDat) {
        super(convertDat, 32768, -1);
        timeOffset = convertDat.timeOffset;
        setRecType(RecType.STRING);
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
                        + " : " + payloadString);
            }
        }
        if (payloadString.indexOf("Mc  ID  :") > -1) {
            Matcher mcIDMatcher = mcIDPattern.matcher(payloadString);
            if (mcIDMatcher.find()) {
                String mcID = payloadString.substring(mcIDMatcher.start(1),
                        mcIDMatcher.end(1));
                convertDat.addAttrValuePair("mcID(SN)", mcID);
            }
        } else if (payloadString.indexOf("Mc  Ver :") > -1) {
            Matcher mcVerMatcher = mcVerPattern.matcher(payloadString);
            if (mcVerMatcher.find()) {
                String mcVer = payloadString.substring(mcVerMatcher.start(1),
                        mcVerMatcher.end(1));
                convertDat.addAttrValuePair("mcVer", mcVer);
            }

        } else if (payloadString.indexOf("[L-BATTERY]Serial number   :") > -1) {
            String batteryBarCode = payloadString
                    .substring(payloadString.indexOf(":") + 1);
            convertDat.addAttrValuePair("BatterySN", batteryBarCode);
        } else if (payloadString.indexOf("[L-TAKEOFF]alti:") > -1) {
            Matcher altiMatcher = altiPattern.matcher(payloadString);
            if (altiMatcher.find()) {
                String takeOffAltStr = payloadString
                        .substring(altiMatcher.start(1), altiMatcher.end(1));
                try {
                    double takeOffAlt = Double.parseDouble(takeOffAltStr);
                    if (takeOffAlt != 0.0) {
                        convertDat.setTakeOffAlt(takeOffAlt);
                    }
                } catch (Exception e) {
                }
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

    //    private void printCsvValue(String value, String header, lineType lineT,
    //            boolean valid) throws IOException {
    //        if (lineT == lineType.XML)
    //            return;
    //        if (lineT == lineType.HEADER) {
    //            csvWriter.print("," + header);
    //        } else {
    //            csvWriter.print(",");
    //            if (valid)
    //                csvWriter.print("" + value);
    //        }
    //    }
}
