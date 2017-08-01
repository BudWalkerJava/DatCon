/* Record127 class

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

import src.DatConRecs.Payload;
import src.DatConRecs.Record;

public class Record127_53 extends Record {

    // 50 hZ
    // length 51

    public Record127_53() {

        _type = 127;
        _subType = 53;
//        addCSVTerm("REC127:X1", cvsTermType.FLOAT4, 0);
//        addCSVTerm("REC127:X2", cvsTermType.FLOAT4, 4); // Left motors
//        addCSVTerm("REC127:X3", cvsTermType.FLOAT4, 8); // Back Motors
//        addCSVTerm("REC127:X4", cvsTermType.FLOAT4, 12); // derivative ??
//        addCSVTerm("REC127:X5", cvsTermType.FLOAT4, 16); // |X2| + |X3| ???
//        addCSVTerm("REC127:X6", cvsTermType.FLOAT4, 20); // slightly different that X2
//        addCSVTerm("REC127:X7", cvsTermType.FLOAT4, 24); // slightly different than X3
//        addCSVTerm("REC127:X8", cvsTermType.FLOAT4, 28);
//        addCSVTerm("REC127:X9", cvsTermType.FLOAT4, 32);
//        addCSVTerm("REC127:X10", cvsTermType.FLOAT4, 36);
//        addCSVTerm("REC127:X11", cvsTermType.FLOAT4, 40);
//        addCSVTerm("REC127:X12", cvsTermType.FLOAT4, 44);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

}
