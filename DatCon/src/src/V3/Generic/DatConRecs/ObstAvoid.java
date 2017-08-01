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

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.V3.DatConRecs.Record116_100;
import src.V3.DatConRecs.Record20_97;
import src.V3.Files.ConvertDatV3;

public class ObstAvoid extends Record {

    public ObstAvoid(ConvertDatV3 convertDat) {
        super(convertDat);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    public static ArrayList<Record> create(ConvertDatV3 convertDat) {
        ArrayList<Record> retv = new ArrayList<Record>();
        if (convertDat.getDatFile().typeSubTypeExists(116, 100)) { // front Distance
            retv.add(new Record116_100(convertDat));
        }
        if (convertDat.getDatFile().typeSubTypeExists(20, 97)) { // emergency Brake
            retv.add(new Record20_97(convertDat));
        }
        return retv;
    }
}
