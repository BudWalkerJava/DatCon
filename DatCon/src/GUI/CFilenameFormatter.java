/* CFilenameFormatter class

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

package src.GUI;

import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CFilenameFormatter extends JFormattedTextField.AbstractFormatter {

    private static final long serialVersionUID = 1L;

    private final static int MAX_CHAR = 255;

    private final FilenameFilter filter = new FilenameFilter();

    private static final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t',
            '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };

    public CFilenameFormatter() {
    }

    private boolean isValid(final String string, final int selected) {
        if ((((getFormattedTextField().getText().length() - selected)
                + string.length()) > MAX_CHAR)) {
            invalidEdit();
            return false;
        }
        for (int i = 0; i < ILLEGAL_CHARACTERS.length; i++) {
            if (string.indexOf(ILLEGAL_CHARACTERS[i]) > -1) {
                invalidEdit();
                return false;
            }
        }
        return true;
    }

    @Override
    protected DocumentFilter getDocumentFilter() {
        return filter;
    }

    @Override
    public Object stringToValue(final String s) {
        return s;
    }

    @Override
    public String valueToString(final Object o) {
        if (o == null) {
            return null;
        }
        return o.toString();
    }

    private class FilenameFilter extends DocumentFilter {
        @Override
        public void insertString(final DocumentFilter.FilterBypass fb,
                final int offset, final String string, final AttributeSet attr)
                throws BadLocationException {
            if (isValid(string, 0)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(final DocumentFilter.FilterBypass fb,
                final int offset, final int length, final String string,
                final AttributeSet attr) throws BadLocationException {
            if (isValid(string, length)) {
                super.replace(fb, offset, length, string, attr);
            }
        }
    }
}
