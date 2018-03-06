/* Record255_2 class

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

package src.DatConRecs.String;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class RecDefs_65533 extends Record {

    public static RecDefs_65533 current = null;

    String payloadString = "";

    public RecDefs_65533(ConvertDat convertDat) {
        super(convertDat, 0xFFFD, -1);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            payloadString = _payload.getString();
            if (convertDat.recDefsPS != null) {
                if (payloadString.length() > 0) {
                    convertDat.recDefsPS.println(payloadString
                            .replace('\t', ' ').replaceAll("\n", "\r\n"));
                }
            }
        } catch (Exception e) {
            RecordException(e);
        }
    }

    @Override
    public void printCols(lineType lineT) {
        try {

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
