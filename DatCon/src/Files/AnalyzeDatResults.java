/* AnalyzeDatResults class

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
package src.Files;

public class AnalyzeDatResults {
    public enum ResultCode {
        NO_ERRORS, SOME_ERRORS, CORRUPTED, NOT_DAT
    }

    private ResultCode resultCode = ResultCode.NO_ERRORS;

    private String messages = "";

    public void setResultCode(ResultCode _resultCode) {
        resultCode = _resultCode;
    }

    //    public void addMessage(String msg) {
    //        messages += msg;
    //    }

    //    public void resetMessages() {
    //        messages = "";
    //    }
    //
    //    public ResultCode getResultCode() {
    //        return resultCode;
    //    }

    public String getMessages() {
        return messages;
    }

    public String toString() {
        String retv = "";
        switch (resultCode) {
        case NO_ERRORS:
            retv += "";
            break;
        case SOME_ERRORS:
            retv += "Warnings:\n";
            break;
        case CORRUPTED:
            retv += "Corrupted File\n";
            break;
        case NOT_DAT:
            retv += "Not a .DAT file\n";
            break;
        }
        retv += messages;
        return retv;
    }
}
