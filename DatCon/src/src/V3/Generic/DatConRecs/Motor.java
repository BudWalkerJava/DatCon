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

public class Motor {

    public static ArrayList<Record> create(ConvertDat convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        Record motorRec = null;
        if (convertDat.getDatFile().typeSubTypeExists(38, 106)) {
            motorRec = new RecMotor38_106(convertDat);
        } else if (convertDat.getDatFile().typeSubTypeExists(144, 106)) {
            if (convertDat._datFile.acType == AcType.M600) {
                motorRec = new Rec6Motor144_106(convertDat);
            } else {
                motorRec = new Rec4Motor144_106(convertDat);
            }
        } else if (convertDat.getDatFile().typeSubTypeExists(14, 106)) {
            motorRec = new RecMotor14_106(convertDat);
        }
        if (motorRec != null) {
            retv.add(motorRec);
        } else {
            DatConLog.Log(
                    "Can't find Motor Record, tried 38_106, 144_106 and 14_106");
        }
        return retv;
    }
}
