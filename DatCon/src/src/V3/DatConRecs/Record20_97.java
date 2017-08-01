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

package src.V3.DatConRecs;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.ConvertDat.lineType;

public class Record20_97 extends Record {

    public String emergBrake = "Off";

    private boolean valid = false;

    public Record20_97(ConvertDat convertDat) {
        super(convertDat);
        _type = 20;
        _subType = 97;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;
        if (payloadBB.get(3) == 1) {
            emergBrake = "On";
        } else {
            emergBrake = "Off";
        }
    }

    private static Signal emergBrakeSig = Signal.State("OA:emergBrake",
            "Emergency Brake", "Off");

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(emergBrake, emergBrakeSig, "", lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
