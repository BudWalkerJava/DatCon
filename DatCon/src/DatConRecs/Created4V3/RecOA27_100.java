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
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class RecOA27_100 extends Record {
    public boolean frontDistanceIsValid() {
        if (OAfrontDistance != 39) {
            return true;
        }
        return false;
    }

    public short OAfrontDistance = 39;

    public RecOA27_100(ConvertDat convertDat) {
        super(convertDat, 100, 27);
    }

    private static Signal oAFrontDistanceSig = Signal.SeriesInt(
            "OA:frontDistance", "Obstace Avoidance Front Distance", null,
            Units.meters);

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            OAfrontDistance = payloadBB.get(4);
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(OAfrontDistance, oAFrontDistanceSig, "", lineT,
                    frontDistanceIsValid());
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
