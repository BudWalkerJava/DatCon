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

package src.V3.Generic.DatConRecs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.DatConRecs.Payload;
import src.V3.Files.ConvertDatV3;
import src.apps.DatCon;

public class Record255_1 extends src.DatConRecs.Record255_1 {

    public static Record255_1 current = null;

    Pattern latlonPattern = Pattern.compile(
            ".*?lati:\\s*(-*\\d+\\.\\d+)\\s+longti:\\s*(-*\\d+\\.\\d+)\\s+alti:\\s*(-*\\d+\\.\\d+)");

    Pattern dateTimePattern = Pattern.compile(".*\\[(.+)\\](.+)\\s");

    Pattern mcIDPattern = Pattern.compile(".*\\:(.+)");

    Pattern mcVerPattern = Pattern.compile(".*\\:(.+)");

    public Record255_1(ConvertDatV3 convertDat) {
        super(convertDat);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        if (payloadString.length() > 0) {
            if (payloadString.indexOf(">calender_setup_time") > -1) {
                Matcher dateTimeMatcher = dateTimePattern
                        .matcher(payloadString);
                if (dateTimeMatcher.find()) {
                    String date = payloadString.substring(
                            dateTimeMatcher.start(1), dateTimeMatcher.end(1));
                    String time = payloadString.substring(
                            dateTimeMatcher.start(2), dateTimeMatcher.end(2));
                    convertDat.addAttrValuePair("dateTime", date + " " + time);
                }
            } else if (payloadString.indexOf("Mc  ID  :") > -1) {
                Matcher mcIDMatcher = mcIDPattern.matcher(payloadString);
                if (mcIDMatcher.find()) {
                    String mcID = payloadString.substring(mcIDMatcher.start(1),
                            mcIDMatcher.end(1));
                    convertDat.addAttrValuePair("mcID(SN)", mcID);
                }
            } else if (payloadString.indexOf("Mc  Ver :") > -1) {
                Matcher mcVerMatcher = mcVerPattern.matcher(payloadString);
                if (mcVerMatcher.find()) {
                    String mcVer = payloadString.substring(
                            mcVerMatcher.start(1), mcVerMatcher.end(1));
                    convertDat.addAttrValuePair("mcVer", mcVer);
                }

            } else if (payloadString
                    .indexOf("[L-BATTERY]Serial number   :") > -1) {
                String batteryBarCode = payloadString
                        .substring(payloadString.indexOf(":") + 1);
                convertDat.addAttrValuePair("BatterySN", batteryBarCode);
            }
        } else {
            System.out.println("255_1 non String");
        }
    }

}
