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

package src.DatConRecs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;

public class Record255_253 extends Record {

    ByteBuffer payload = null;

    protected String payloadString;

    // Pattern numberPattern = Pattern.compile("\\[\\s*(\\d+)\\s*\\]");
    //    PrintStream fios = null;

    public Record255_253(ConvertDat convertDat) {
        super(convertDat);
        _type = 255;
        _subType = 253;
    }

    public void process(Payload _payload) {
        super.process(_payload);
        payload = _payload.getBB();
        payloadString = _payload.getString();
        if (convertDat.opPS != null) {
            if (payloadString.length() > 0) {
                convertDat.opPS.println(payloadString.substring(3)
                        .replace('\n', '\t').replace('\r', '\t'));
            }
        }
    }

    @Override
    public void printCols(lineType lineT) {
        // nothing to do
    }

}
