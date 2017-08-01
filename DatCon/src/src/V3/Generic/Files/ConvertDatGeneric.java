/* ConvertDatMavic class

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

package src.V3.Generic.Files;

import src.Files.TypeSubType;
import src.V3.DatConRecs.Controller;
import src.V3.DatConRecs.GoTxt;
import src.V3.DatConRecs.RCStatus;
import src.V3.DatConRecs.RecMotor144_106;
import src.V3.Files.ConvertDatV3;
import src.V3.Files.DatFileV3;
import src.V3.Generic.DatConRecs.AirComp;
import src.V3.Generic.DatConRecs.Battery;
import src.V3.Generic.DatConRecs.HomePoint;
import src.V3.Generic.DatConRecs.MagRaw;
import src.V3.Generic.DatConRecs.Motor;
import src.V3.Generic.DatConRecs.ObstAvoid;
import src.V3.Generic.DatConRecs.Record207_0;
import src.V3.Generic.DatConRecs.Record255_1;
import src.V3.Generic.DatConRecs.SmartBattery;

public class ConvertDatGeneric extends ConvertDatV3 {

    private String setXMLFileName;

    public ConvertDatGeneric(DatFileV3 datFile) {
        super(datFile);
    }

    public ConvertDatGeneric() {
        super();
    }

    @Override
    public void createUserRecords() {
        if (_datFile.typeSubTypeExists(207, 0)) {
            records.add(new Record207_0(this));
        }
        TypeSubType tst = _datFile.getTypeSubType(12);
        if (tst != null) {
            GoTxt goTxtRec = new GoTxt(this);
            goTxtRec.setType(tst.getType());
            records.add(goTxtRec);
        }
        //        records.add(new Record157_176(this));// smart batt
        //
        records.addAll(Controller.create(this));
        records.addAll(RCStatus.create(this));
        records.addAll(MagRaw.create(this));
        records.addAll(HomePoint.create(this));
        records.addAll(Battery.create(this));
        records.addAll(SmartBattery.create(this));
        records.addAll(ObstAvoid.create(this));
        records.addAll(Motor.create(this));
        records.addAll(AirComp.create(this));
        records.add(new Record255_1(this));
    }

    public void setXMLFileName(String xmlFileName) {
        this.setXMLFileName = xmlFileName;
    }

}
