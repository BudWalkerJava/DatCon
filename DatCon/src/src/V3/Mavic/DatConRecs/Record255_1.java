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

package src.V3.Mavic.DatConRecs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatFile;
import src.Files.DatHeader.AcType;
import src.Files.TSAGeoMag;
import src.V3.Files.ConvertDatV3;
import src.apps.DatCon;

public class Record255_1 extends src.DatConRecs.Record255_1 {

    public static Record255_1 current = null;

    private static final HashMap<String, String> firmWares;;
    static {
        firmWares = new HashMap<String, String>();
        firmWares.put("v3.2.13.10", "01.03.0000");
        firmWares.put("v3.2.13.12", "01.03.0200");
        firmWares.put("v3.2.13.16", "01.03.0400");
        firmWares.put("v3.2.21.19", "01.03.0500");
        firmWares.put("v3.2.21.31", "01.03.0550");
        firmWares.put("v3.2.30.13", "01.03.0600");
        firmWares.put("v3.2.35.5", "01.03.0800");
    }

    Pattern latlonPattern = Pattern.compile(
            ".*?lati:\\s*(-*\\d+\\.\\d+)\\s+longti:\\s*(-*\\d+\\.\\d+)\\s+alti:\\s*(-*\\d+\\.\\d+)");

    Pattern dateTimePattern = Pattern.compile(".*\\[(.+)\\](.+)\\s");

    Pattern mcIDPattern = Pattern.compile(".*\\:(.+)");

    Pattern mcVerPattern = Pattern.compile(".*\\:(.+)");

    public Record255_1(ConvertDat convertDat) {
        super(convertDat);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            if (payloadString.length() > 0) {
                if (payloadString.indexOf("[L-HOME]") > -1) {
                    // 1148 [L-HOME]First. lati: 0.8671141 longti: 0.1164908
                    // alti:430.87
                    if (payloadString.indexOf("Update") > -1
                            || payloadString.indexOf("First") > -1) {
                        Matcher latlonMatcher = latlonPattern
                                .matcher(payloadString);
                        if (latlonMatcher.find()) {
                            String lat = payloadString.substring(
                                    latlonMatcher.start(1),
                                    latlonMatcher.end(1));
                            String lon = payloadString.substring(
                                    latlonMatcher.start(2),
                                    latlonMatcher.end(2));
                            String hei = payloadString.substring(
                                    latlonMatcher.start(3),
                                    latlonMatcher.end(3));

                            double longitudeHPDegrees = Double.parseDouble(lon);
                            double latitudeHPDegrees = Double.parseDouble(lat);
                            float height = Float.parseFloat(hei);
                            convertDat.processHomePoint(latitudeHPDegrees,
                                    longitudeHPDegrees, height);
                        } else {
                            DatCon.instance.log
                                    .Error("Rec255_1 can't match Home Point");
                        }
                    }
                } else if (payloadString.indexOf(">calender_setup_time") > -1) {
                    Matcher dateTimeMatcher = dateTimePattern
                            .matcher(payloadString);
                    if (dateTimeMatcher.find()) {
                        String date = payloadString.substring(
                                dateTimeMatcher.start(1),
                                dateTimeMatcher.end(1));
                        String time = payloadString.substring(
                                dateTimeMatcher.start(2),
                                dateTimeMatcher.end(2));
                        convertDat.addAttrValuePair("dateTime",
                                date + " " + time);
                    }
                } else if (payloadString.indexOf("Mc  ID  :") > -1) {
                    Matcher mcIDMatcher = mcIDPattern.matcher(payloadString);
                    if (mcIDMatcher.find()) {
                        String mcID = payloadString.substring(
                                mcIDMatcher.start(1), mcIDMatcher.end(1));
                        convertDat.addAttrValuePair("mcID(SN)", mcID);
                    }
                } else if (payloadString.indexOf("Mc  Ver :") > -1) {
                    Matcher mcVerMatcher = mcVerPattern.matcher(payloadString);
                    if (mcVerMatcher.find()) {
                        String mcVer = payloadString.substring(
                                mcVerMatcher.start(1), mcVerMatcher.end(1));
                        String firmware = firmWares.get(mcVer);
                        if (firmware != null) {
                            convertDat.addAttrValuePair("firmare", firmware);
                        } else {
                            convertDat.addAttrValuePair("mcVer", mcVer);
                        }
                    }
                }
            } else {
                System.out.println("255_1 non String");
            }

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
