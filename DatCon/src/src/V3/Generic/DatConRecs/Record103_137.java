/* Record68_17 class

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

import src.DatConRecs.AxesAndSigs;
import src.DatConRecs.Payload;
import src.DatConRecs.RecBattery;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.V3.Files.ConvertDatV3;
import src.Files.ConvertDat.lineType;

public class Record103_137 extends RecBattery {

    public Record103_137(ConvertDatV3 convertDat) {
        super(convertDat);
        _type = 103;
        _subType = 137;
    }

    public short batteryPercent = 0;

    public void process(Payload _payload) {
        super.process(_payload);
        if (numSamples == 0) { // first time
            init();
        }
        valid = true;
        numSamples++;
        totalVolts = (float) (((float) (payloadBB.getShort(0))) / 1000.0);
        crrnt = -(float) (((float) (_payload.getUnsignedShort(4) - 65536))
                / 1000.0);
        temp = (float) (((float) (payloadBB.getShort(16))));
        batteryPercent = payloadBB.get(18);
        for (int i = 0; i < numCells; i++) {
            volt[i] = (float) (((float) (payloadBB.getShort(19 + (2 * i))))
                    / 1000.0);
        }
        float voltMax = maxVolts(volt);
        float voltMin = minVolts(volt);
        voltDiff = voltMax - voltMin;
        processComputedBatt();
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            for (int i = 1; i <= 6; i++) {
                printCsvValue(volt[i - 1], AxesAndSigs.cellVoltSig,
                        ("" + i + "(1)"), lineT, valid);
            }
            printCsvValue(crrnt, AxesAndSigs.currentSig, "(1)", lineT, valid);
            printCsvValue(totalVolts, AxesAndSigs.voltsSig, "total(1)", lineT,
                    valid);
            //            printCsvValue(temp, AxesAndSigs.batteryTempSig, "(1)", lineT,
            //                    valid);
            printCsvValue(batteryPercent, AxesAndSigs.battPercent, "(1)", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
