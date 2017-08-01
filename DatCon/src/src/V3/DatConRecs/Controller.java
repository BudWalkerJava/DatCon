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

import src.DatConRecs.Axis;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.TypeSubType;
import src.Files.ConvertDat.lineType;

public class Controller extends Record {
    // 50 hZ
    // length 45
    // referred to by 2_232, 246_232, 50_232, 213_232

    public short aileron = 0;

    public short elevator = 0;

    public short throttle = 0;

    public short rudder = 0;

    public boolean sticksValid = false;

    public static Axis controlAxis = new Axis("control", "Control",
            Units.controlStick);

    public static Signal throttleSig = Signal.SeriesInt("RC:Throttle",
            "Throttle", controlAxis, Units.controlStick);

    public static Signal rudderSig = Signal.SeriesInt("RC:Rudder", "Rudder",
            controlAxis, Units.controlStick);

    public static Signal elevatorSig = Signal.SeriesInt("RC:Elevator",
            "Elevator", controlAxis, Units.controlStick);

    public static Signal aileronSig = Signal.SeriesInt("RC:Aileron", "Aileron",
            controlAxis, Units.controlStick);

    public Controller(ConvertDat convertDat) {
        super(convertDat);
    }

    public static ArrayList<Record> create(ConvertDat convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        Record sticksRecord = getControllerSticksRecord(convertDat);
        if (sticksRecord != null) {
            retv.add(sticksRecord);
        }
        return retv;
    }

    private static Record getControllerSticksRecord(ConvertDat convertDat) {
        Controller retv = new Controller(convertDat);
        TypeSubType sticks = convertDat.getDatFile().getTypeSubType(232);
        if (sticks != null && sticks.getSubType() == 232) {
            retv = new Controller(convertDat);
            retv.setType(sticks.getType());
            retv.setSubType(232);
        } else {
            retv = new Controller(convertDat);
        }
        return retv;
    }

    //    private void setSubType(int st) {
    //        _subType = st;
    //    }
    //
    //    private void setType(int t) {
    //        _type = t;
    //    }

    public void process(Payload _payload) {
        super.process(_payload);
        if (_type == 9 && _subType == 232) { /// P4
            elevator = (short) payloadBB.getShort(20);
            aileron = (short) payloadBB.getShort(22);
            rudder = (short) payloadBB.getShort(24);
            throttle = (short) payloadBB.getShort(26);
            sticksValid = true;
            return;
        }
        elevator = (short) payloadBB.getShort(4);
        aileron = (short) payloadBB.getShort(6);
        rudder = (short) payloadBB.getShort(8);
        throttle = (short) payloadBB.getShort(10);
        sticksValid = true;
    }

    @Override
    public void printCols(lineType lineT) {
        try {
            printCsvValue(aileron, aileronSig, "", lineT, sticksValid);
            printCsvValue(elevator, elevatorSig, "", lineT, sticksValid);
            printCsvValue(rudder, rudderSig, "", lineT, sticksValid);
            printCsvValue(throttle, throttleSig, "", lineT, sticksValid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
