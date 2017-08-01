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

package src.V1.DatConRecs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class Record255_1 extends src.DatConRecs.Record255_1 {

    public static Record255_1 current = null;

    public String batteryBarCode = "";

    public Record255_1(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 255;
        _subType = 1;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        if (payloadString.length() > 0) {
            if (payloadString.indexOf("Home Point") > -1) {
                Pattern latlonPattern = Pattern.compile(
                        ".*?(-*\\d+\\.\\d+)\\s+(-*\\d+\\.\\d+)\\s+(-*\\d+\\.\\d+)");
                Matcher latlonMatcher = latlonPattern.matcher(payloadString);
                if (latlonMatcher.find()) {
                    String lat = payloadString.substring(latlonMatcher.start(1),
                            latlonMatcher.end(1));
                    String lon = payloadString.substring(latlonMatcher.start(2),
                            latlonMatcher.end(2));
                    String hei = payloadString.substring(latlonMatcher.start(3),
                            latlonMatcher.end(3));
                    double longitudeHPDegrees = Double.parseDouble(lon);
                    double latitudeHPDegrees = Double.parseDouble(lat);
                    float height = Float.parseFloat(hei);
                    convertDat.processHomePoint(latitudeHPDegrees,
                            longitudeHPDegrees, height);
                }
            } else if (payloadString.indexOf("Battery barcode:") > -1) {
                batteryBarCode = payloadString
                        .substring(payloadString.indexOf(":") + 1);
            }
        }
    }

}
