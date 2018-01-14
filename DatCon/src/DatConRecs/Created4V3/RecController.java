/* Record152_0 class

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

import java.util.ArrayList;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.RecSpec;
import src.Files.ConvertDat.lineType;

public class RecController extends Record {

    public short aileron = 0;

    public short elevator = 0;

    public short throttle = 0;

    public short rudder = 0;

    public boolean sticksValid = false;

    public RecController(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(aileron, AxesAndSigs.aileronSig, "", lineT,
                    sticksValid);
            printCsvValue(elevator, AxesAndSigs.elevatorSig, "", lineT,
                    sticksValid);
            printCsvValue(rudder, AxesAndSigs.rudderSig, "", lineT,
                    sticksValid);
            printCsvValue(throttle, AxesAndSigs.throttleSig, "", lineT,
                    sticksValid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
