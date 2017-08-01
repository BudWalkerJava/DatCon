/* Record168 class

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
package src.V1.DatRecords;

import java.io.PrintStream;
import java.nio.ByteBuffer;

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record168 extends Record {

    ByteBuffer payload = null;

    String message = "";

    public Record168() {
        _type = 168;
        _subType = 0;
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        payload = _payload.getBB();
        char characters[] = new char[29];
        for (int i = 0; i < 29; i++) {
            characters[i] = (char) payload.get(i);
        }
        message = new String(characters);
        //System.out.println("168 Message "+message);
    }
}
