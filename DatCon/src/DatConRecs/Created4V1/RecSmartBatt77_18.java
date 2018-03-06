/* Record30_18 class

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

package src.DatConRecs.Created4V1;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class RecSmartBatt77_18 extends Record {

    public static RecSmartBatt77_18 current = null;

    // 1 Hz
    // length 79
    public int batteryUsefulTime = 0;

    public boolean validBUT = false;

    public boolean validVP = false;

    public int voltagePercent = 0;

    public int goHomeBatt = 0;

    public boolean validGHB = false;

    public short batteryStatus = 0;

    public short batteryGoHomeStatus = 0;

    private boolean valid = false;

    public RecSmartBatt77_18(ConvertDat convertDat) {
        super(convertDat, 18, 77);
        current = this;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            batteryUsefulTime = payloadBB.getShort(0);
            if (!validBUT) {
                if (batteryUsefulTime > 0)
                    validBUT = true;
            }

            goHomeBatt = payloadBB.get(6);
            if (!validGHB) {
                if (goHomeBatt > 0) {
                    validGHB = true;
                }
            }
            batteryGoHomeStatus = payloadBB.get(14);
            batteryStatus = payloadBB.getShort(20);
            voltagePercent = payloadBB.get(72);
            if (!validVP) {
                if (voltagePercent > 0) {
                    validVP = true;
                }
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }

    static final String batGHStats[] = { "", "GoHome", "GoHomeAlready" };

    static final String batStats[] = { "ReqGoHome", "ReqLand", "ReqGoHome",
            "ReqLand", "LowGoHOme", "VoltageLowLand", "CellError",
            "CommunicateError", "VoltageLowNeedLand", "TempVoltageLow",
            "NotReady", "FirstChargeNotFull", "LimitOutputMax", "Dangerous",
            "DangerousWarning" };

    private String batStatString(short stat) {
        String retv = "";
        for (int i = 0; i < 16; i++) {
            if ((0x01 & (stat >> i)) == 0x01) {
                retv += batStats[i] + "|";
            }
        }
        return retv;
    }

    private String batGHStatString(short stat) {
        String retv = "";
        for (int i = 0; i < 16; i++) {
            if ((0x01 & (stat >> i)) == 0x01) {
                retv += batGHStats[i] + "|";
            }
        }
        return retv;
    }

    private static Signal batteryStatusSig = Signal.State("SMART_BATT:Status",
            "Battery Status", "");

    private static Signal batteryGHStatusSig = Signal
            .State("SMART_BATT:GHStatus", "Battery Go Home Status", "");

    public static Signal voltagePercentSig = Signal.SeriesInt(
            "SMART_BATT:voltage%", "Voltage Percentage", null,
            Units.percentage);

    @Override
    public void printCols(lineType lineT) {
        try {
            String batStat = batStatString(batteryStatus);
            String batGHStat = batGHStatString(batteryGoHomeStatus);
            printCsvValue(batStat, batteryStatusSig, "", lineT, valid);
            printCsvValue(batGHStat, batteryGHStatusSig, "", lineT, valid);
            printCsvValue(goHomeBatt, AxesAndSigs.battGoHome, "", lineT,
                    validGHB);
            printCsvValue(voltagePercent, voltagePercentSig, "", lineT,
                    validVP);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
