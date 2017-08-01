/* Motor class

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

import java.util.ArrayList;
import src.DatConRecs.Record;
import src.DatConRecs.Signal;
import src.DatConRecs.Units;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatHeader.AcType;
import src.Files.TypeSubType;
import src.V3.DatConRecs.Record68_17;
import src.V3.Files.ConvertDatV3;

public class Battery {
//search for record type 5000
    public static ArrayList<Record> create(ConvertDatV3 convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        Record battery1 = null;
        Record battery2 = null;
        if (convertDat.getDatFile().typeSubTypeExists(176, 136)) {
            battery1 = new Record176_136(convertDat);
            retv.add(battery1);
            DatConLog.Log("Battery found " + battery1);
        } else if (convertDat.getDatFile().typeSubTypeExists(103, 137)
                && convertDat.getDatFile().typeSubTypeExists(103, 138)) {
            battery1 = new Record103_137(convertDat);
            battery2 = new Record103_138(convertDat);
            DatConLog.Log("Battery found " + battery1 + "," + battery2);
            retv.add(battery1);
            retv.add(battery2);
        } else if (convertDat.getDatFile().typeSubTypeExists(103, 136)) {
            battery1 = new Record103_136(convertDat);
            retv.add(battery1);
            DatConLog.Log("Battery found " + battery1);
        } else if (convertDat.getDatFile().typeSubTypeExists(208, 35)) {
            battery1 = new Record208_35(convertDat);
            retv.add(battery1);
            DatConLog.Log("Battery found " + battery1);
        } else if (convertDat.getDatFile().typeSubTypeExists(68, 17)) {
            battery1 = new Record68_17(convertDat);
            retv.add(battery1);
            DatConLog.Log("Battery found " + battery1);
        } else {
            DatConLog.Log(
                    "Battery can't find Record, tried 176_136, 208_35, 103_136, 103_137, 103_138");
        }
        return retv;
    }

}
