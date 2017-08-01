/* Record163 class

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

public class Record163_70 extends Record {

    // 50Hz
    // payload length 41

    public Record163_70() {
        _type = 163;
        _subType = 70;
//        addCSVTerm("REC163:X1", cvsTermType.FLOAT4, 11);
//        addCSVTerm("REC163:X2", cvsTermType.FLOAT4, 15); //close to X1
//        addCSVTerm("REC163:X3", cvsTermType.FLOAT4, 19); //derivative of X1
//        addCSVTerm("REC163:X4", cvsTermType.FLOAT4, 23); // smoothed smoothed X1
//        addCSVTerm("REC163:X5", cvsTermType.FLOAT4, 27); //smoothed X1
//        addCSVTerm("REC163:X6", cvsTermType.FLOAT4, 31); // smoothed signal 
//        addCSVTerm("REC163:X7", cvsTermType.FLOAT4, 35); // "window" .5 when on, 1.04 when off,
//                                                         // but 0 if motors off
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }
}
