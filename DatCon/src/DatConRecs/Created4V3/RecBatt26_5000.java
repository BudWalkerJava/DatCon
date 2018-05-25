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
package src.DatConRecs.Created4V3;

import src.DatConRecs.Payload;
import src.DatConRecs.RecBatt;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

// 50 HZ
//length 47

public class RecBatt26_5000 extends RecBatt {

    public static RecBatt26_5000 current = null;

    public RecBatt26_5000(ConvertDat convertDat) {
        super(convertDat, 5000, 26, 0);
    }
//
//    public short batteryPercent = 0;

//    private float fcc = 0.0f;
//
//    private float remcap = 0.0f;

    private int status1 = 0;

    private int status2 = 0;

    private int status3 = 0;

    private int status4 = 0;

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            if (numSamples == 0) { // first time
                init();
            }
            valid = true;
            numSamples++;
            totalVolts = (float) (((float) (payloadBB.getShort(0))) / 1000.0);
            //        crrnt = -(float) (((float) (_payload.getUnsignedShort(2) - 65536))
            //                / 1000.0);
            crrnt = -(float) (((float) (payloadBB.getInt(2))) / 1000.0);
            fcc = (float) (((float) (payloadBB.getShort(6))));
            remcap = (float) (((float) (payloadBB.getShort(8))));
            temp = (float) (((float) (payloadBB.get(10))));
            batteryPercent = payloadBB.get(12);
            status1 = payloadBB.get(14);
            status2 = payloadBB.get(15);
            status3 = payloadBB.get(16);
            status4 = payloadBB.get(17);
            for (int i = 0; i < numCells; i++) {
                volt[i] = (float) (((float) (payloadBB.getShort(18 + (2 * i))))
                        / 1000.0);
            }
            float voltMax = maxVolt(volt);
            float voltMin = minVolt(volt);
            voltDiff = voltMax - voltMin;
            processComputedBatt();
        } catch (Exception e) {
            RecordException(e);
        }
    }

    //    name        battery_info
    //    type    5000
    //    Op.uint16_t     packVol 0
    //    Op.int32_t      current 0
    //    Op.uint16_t     fcc 0
    //    Op.uint16_t     remcap 0
    //    Op.int16_t      temp 0
    //    Op.uint16_t     soc 0
    //    Op.uint8_t      status1 0
    //    Op.uint8_t      status2 0
    //    Op.uint8_t      status3 0
    //    Op.uint8_t      status4 0
    //    Op.uint16_t     cellv1 0
    //    Op.uint16_t     cellv2 0
    //    Op.uint16_t     cellv3 0
    //    Op.uint16_t     debug 0

    @Override
    public void printCols(lineType lineT) {
        super.printCols(lineT);
        try {
            //            for (int i = 0; i < numCells; i++) {
            //                printCsvValue(volt[i], cellVoltSig, "" + (i + 1),
            //                        lineT, valid);
            //            }
            //            printCsvValue(crrnt, currentSig, "", lineT, valid);
            //            printCsvValue(totalVolts, voltsSig, "total", lineT,
            //                    valid);
            //            printCsvValue(batteryPercent, battPercent, "", lineT,
            //                    valid);
            //            printCsvValue(fcc, batteryFCC, "", lineT, valid);
            //            printCsvValue(remcap, batteryRemCap, "", lineT, valid);
            //            printCsvValue(temp, batteryTempSig, "", lineT, valid);
            printCsvValue(status1, statusSig, "status1", lineT, true);
            printCsvValue(status2, statusSig, "status2", lineT, true);
            printCsvValue(status3, statusSig, "status3", lineT, true);
            printCsvValue(status4, statusSig, "status4", lineT, true);
         
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
