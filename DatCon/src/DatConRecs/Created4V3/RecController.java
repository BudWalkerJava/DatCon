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
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class RecController extends Record {

    public short ctrl_roll = 0;

    public short ctrl_pitch = 0;

    public short ctrl_thr = 0;

    public short ctrl_yaw = 0;

    protected short sig_level = (short) 0;

    protected short ctrl_level = (short) 0;

    public boolean valid = false;

    public RecController(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    protected static Signal ControllerIntSig = Signal.SeriesInt("Controller",
            "", null, Units.noUnits);

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(ctrl_roll, AxesAndSigs.aileronSig, "", lineT, valid);
            printCsvValue(ctrl_pitch, AxesAndSigs.elevatorSig, "", lineT,
                    valid);
            printCsvValue(ctrl_yaw, AxesAndSigs.rudderSig, "", lineT, valid);
            printCsvValue(ctrl_thr, AxesAndSigs.throttleSig, "", lineT, valid);
            printCsvValue(sig_level, ControllerIntSig, "gpsLevel", lineT,
                    valid);
            printCsvValue(ctrl_level, ControllerIntSig, "ctrl_level", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
