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
package src.DatConRecs;

import src.DatConRecs.Created4V3.RecBatt;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatHeader;
import src.Files.RecSpec;
import src.Files.RecSpec.RecType;
import src.V3.Files.ConvertDatV3;
import src.Files.ConvertDat.lineType;

public class RecBatt45_17 extends RecBatt {

    public RecBatt45_17(ConvertDat convertDat) {
        super(convertDat, 17, 45);
    }

    protected float maxVolt(float... floatVolts) {
        float retv = -Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts > retv) {
                retv = volts;
            }
        }
        return retv;
    }

    protected float minVolt(float... floatVolts) {
        float retv = Float.MAX_VALUE;
        for (float volts : floatVolts) {
            if (volts < retv) {
                retv = volts;
            }
        }
        return retv;
    }

    public float remainingCapacity = (float) 0.0;

    public float ratedCapacity = (float) 0.0;

    public int relativeCapacity;

    protected int design_capacity = (int) 0;

    public void process(Payload _payload) {
        super.process(_payload);
        if (numSamples == 0) { // first time
            init();
        }
        valid = true;
        numSamples++;
        design_capacity = _payload.getUnsignedShort(0);
        ratedCapacity = (float) (((float) (payloadBB.getShort(2))));
        remainingCapacity = (float) (((float) (payloadBB.getShort(4))));
        totalVolts = (float) (((float) (payloadBB.getShort(6))) / 1000.0);
        crrnt = -(float) (((float) (_payload.getUnsignedShort(8) - 65536))
                / 1000.0);
        relativeCapacity = payloadBB.get(11);
        temp = (float) (((float) (payloadBB.get(12))));
        for (int i = 0; i < numCells; i++) {
            volt[i] = (float) (((float) (payloadBB.getShort(18 + (2 * i))))
                    / 1000.0);
        }
        float voltMax = maxVolt(volt);
        float voltMin = minVolt(volt);
        voltDiff = voltMax - voltMin;
        processComputedBatt();
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            for (int i = 0; i < numCells; i++) {
                printCsvValue(volt[i], AxesAndSigs.cellVoltSig, "" + (i + 1),
                        lineT, valid);
            }
            printCsvValue(crrnt, AxesAndSigs.currentSig, "", lineT, valid);
            printCsvValue(totalVolts, AxesAndSigs.voltsSig, "total", lineT,
                    valid);
            printCsvValue(temp, AxesAndSigs.batteryTempSig, "", lineT, valid);
            printComputedBattCols(lineT);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
