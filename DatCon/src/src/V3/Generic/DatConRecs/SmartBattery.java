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

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.DatHeader.AcType;
import src.Files.TypeSubType;
import src.V3.DatConRecs.Record157_176;
import src.V3.Files.ConvertDatV3;

public class SmartBattery {
    //search for record type 1712
    public static ArrayList<Record> create(ConvertDatV3 convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        Record battery = null;
        if (convertDat.getDatFile().typeSubTypeExists(157, 176)) {
            battery = new Record157_176(convertDat);
            retv.add(battery);
        }
        //        else if (convertDat.getDatFile().typeSubTypeExists(208, 35)) {
        //            battery = new Record208_35(convertDat);
        //            retv.add(battery);
        //      }
        else {
            DatConLog.Log("Can't find Smart Battery Record, tried 157_176");
        }
        return retv;
    }
}
