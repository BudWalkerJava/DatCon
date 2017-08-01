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
package src.V3.DatConRecs;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import src.DatConRecs.AxesAndSigs;
import src.DatConRecs.Axis;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.TypeSubType;
import src.Files.ConvertDat.lineType;
import src.V3.Generic.DatConRecs.Record75_164;

public class RCStatus extends Record {

    public enum DJIFlightFailsafeOperation {
        Hover(0), Landing(1), GoHome(2), Unknown(255);

        private int data;

        private DJIFlightFailsafeOperation(int n3) {
            this.data = n3;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int n2) {
            return this.data == n2;
        }

        public static DJIFlightFailsafeOperation find(int n2) {
            DJIFlightFailsafeOperation dJIFlightFailsafeOperation = Unknown;
            for (int i2 = 0; i2 < DJIFlightFailsafeOperation
                    .values().length; ++i2) {
                if (!DJIFlightFailsafeOperation.values()[i2]._equals(n2))
                    continue;
                dJIFlightFailsafeOperation = DJIFlightFailsafeOperation
                        .values()[i2];
                break;
            }
            return dJIFlightFailsafeOperation;
        }
    }

    protected boolean statusValid = false;

    public short fail_safe = 0;

    private String fSafe = "";

    public String data_lost = "";

    public String app_lost = "";

    protected short frame_lost = 0;

    public long rec_cnt = 0;

    public String connected = "";

    public RCStatus(ConvertDat convertDat) {
        super(convertDat);
        numGoodSig = 0;
        numRecs = 0;
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    public static ArrayList<Record> create(ConvertDat convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        Record statusRecord = getControllerStatusRecord(convertDat);
        if (statusRecord != null) {
            retv.add(statusRecord);
        }
        return retv;
    }

    private static Record getControllerStatusRecord(ConvertDat convertDat) {
        Class<?> recClass = null;
        Record retv = null;
        TypeSubType status = convertDat.getDatFile().getTypeSubType(164);
        if (status != null) {
            if (status.is(187, 164)) {
                recClass = Record187_164.class;
            } else if (status.is(112, 164)) {
                recClass = Record112_164.class;
            } else if (status.is(75, 164)) {
                recClass = Record75_164.class;
            } else if (status.is(235, 164)) {
                recClass = Record235_164.class;
            } else if (recClass == null) {
                DatConLog.Log(
                        "Can't find Controller Status Record for " + status);
                return null;
            }
            try {
                retv = (Record) recClass.getConstructor(ConvertDat.class)
                        .newInstance(convertDat);
            } catch (InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                DatConLog.Exception(e);
            }
        } else {
            retv = new RCStatus(convertDat);
        }
        return retv;
    }

    protected Signal frameLostSig = Signal.State("RC:frameLost", "Frame Lost",
            "");

    protected Signal sigStrengthSig = Signal.SeriesFloat("RC:sigStrength",
            "Sig Strength from Frames Lost", null, Units.percentage);

    protected Signal failSafeSig = Signal.State("RC:failSafe", "Fail Safe",
            "Hover");

    protected Signal dataLostSig = Signal.StateExperimental("RC:dataLost",
            "Data Lost", "");

    protected Signal appLostSig = Signal.State("RC:appLost", "App Lost", "");

    protected Signal connectedSig = Signal.State("RC:connected", "Connected",
            "Connected");

    private int numGoodSig = 0;

    private int numRecs = 0;

    float sStrength = 0.0f;

    protected void common() {
        if (frame_lost == 0) {
            numGoodSig++;
        }
        numRecs++;
        if (numRecs > 20) {
            sStrength = (float) (100.0
                    * ((float) numGoodSig / (float) numRecs));
            numRecs = 0;
            numGoodSig = 0;
        }
        DJIFlightFailsafeOperation fsOp = DJIFlightFailsafeOperation
                .find(fail_safe);
        fSafe = fsOp.name();
    }

    @Override
    public void printCols(lineType lineT) {
        try {

            printCsvValue(fSafe, failSafeSig, "", lineT, statusValid);
            printCsvValue(data_lost, dataLostSig, "", lineT, statusValid);
            printCsvValue(app_lost, appLostSig, "", lineT, statusValid);
            printCsvValue(sStrength, sigStrengthSig, "", lineT, statusValid);
            //printCsvValue(rec_cnt, rcDebugSig, "rec_cnt", lineT, statusValid);
            //            printCsvValue(connected, connectedSig, "", lineT,
            //                    statusValid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
