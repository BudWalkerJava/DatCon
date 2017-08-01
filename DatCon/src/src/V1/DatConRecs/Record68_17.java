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
package src.V1.DatConRecs;

import java.io.PrintStream;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.DatFile;
import src.Files.DatHeader;
import src.Files.DatHeader.AcType;

// 50 HZ
//length 47

public class Record68_17 extends Record {

    public static Record68_17 current = null;

    public Record68_17(ConvertDat convertDat) {
        super(convertDat);
        current = this;
        _type = 68;
        _subType = 17;
        //datFile = convertDat.getDatFile();
    }
    //    
    //    DatFile datFile = null;

    public float crrnt = (float) 0.0;

    public float volt1 = (float) 0.0;

    public float volt2 = (float) 0.0;

    public float volt3 = (float) 0.0;

    public float volt4 = (float) 0.0;

    public float volt5 = (float) 0.0;

    public float volt6 = (float) 0.0;

    public float temp = (float) 0.0;;

    public float remainingCapacity = (float) 0.0;

    public float ratedCapacity = (float) 0.0;

    public float totalVolts = (float) 0.0;

    public float maxVolts = (float) 0.0;

    public float minVolts = (float) 0.0;

    public float sumOfVolts = (float) 0.0;

    public float avgVolts = (float) 0.0;

    long sumOfCurrents = 0;

    long numSamples = 0;

    public float voltDiff = (float) 0.0;

    public float maxCurrent = (float) 0.0;

    public float minCurrent = (float) 0.0;

    public float avgCurrent = (float) 0.0;

    public float watts = (float) 0.0;

    public float maxWatts = (float) 0.0;

    public float minWatts = (float) 0.0;

    float sumOfWatts = (float) 0.0;

    public float avgWatts = (float) 0.0;

    public int relativeCapacity;

    private void init() {
        maxVolts = (float) -1.0;
        minVolts = Float.MAX_VALUE;
        minCurrent = Float.MAX_VALUE;
        avgCurrent = (float) 0.0;
        maxWatts = (float) -1.0;
        minWatts = Float.MAX_VALUE;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        if (numSamples == 0) { // first time
            init();
        }
        numSamples++;
        ratedCapacity = (float) (((float) (payloadBB.getShort(2))));
        remainingCapacity = (float) (((float) (payloadBB.getShort(4))));
        totalVolts = (float) (((float) (payloadBB.getShort(6))) / 1000.0);
        crrnt = -(float) (((float) (_payload.getUnsignedShort(8) - 65536))
                / 1000.0);
        relativeCapacity = payloadBB.get(11);
        temp = (float) (((float) (payloadBB.get(12))));
        volt1 = (float) (((float) (payloadBB.getShort(18))) / 1000.0);
        volt2 = (float) (((float) (payloadBB.getShort(20))) / 1000.0);
        volt3 = (float) (((float) (payloadBB.getShort(22))) / 1000.0);
        volt4 = (float) (((float) (payloadBB.getShort(24))) / 1000.0);
        volt5 = (float) (((float) (payloadBB.getShort(26))) / 1000.0);
        volt6 = (float) (((float) (payloadBB.getShort(28))) / 1000.0);
        if (_datFile.acType == DatHeader.AcType.I1) {
            float voltMax = Math.max(volt1, Math.max(volt2,
                    Math.max(volt3, Math.max(volt4, Math.max(volt5, volt6)))));
            float voltMin = Math.min(volt1, Math.min(volt2,
                    Math.min(volt3, Math.min(volt4, Math.min(volt5, volt6)))));
            voltDiff = voltMax - voltMin;
        } else {
            float voltMax = Math.max(volt1,
                    Math.max(volt2, Math.max(volt3, volt4)));
            float voltMin = Math.min(volt1,
                    Math.min(volt2, Math.min(volt3, volt4)));
            voltDiff = voltMax - voltMin;
        }
        if (totalVolts > maxVolts)
            maxVolts = totalVolts;
        if (totalVolts < minVolts)
            minVolts = totalVolts;
        sumOfVolts += totalVolts;
        avgVolts = sumOfVolts / (float) numSamples;

        if (crrnt > maxCurrent)
            maxCurrent = crrnt;
        if (crrnt < minCurrent)
            minCurrent = crrnt;
        sumOfCurrents += crrnt;
        avgCurrent = sumOfCurrents / (float) numSamples;

        watts = totalVolts * crrnt;
        if (watts > maxWatts)
            maxWatts = watts;
        if (watts < minWatts)
            minWatts = watts;
        sumOfWatts += watts;
        avgWatts = sumOfWatts / (float) numSamples;
    }

}
