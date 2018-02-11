/* LoggingPanel  class

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

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.apps.DatCon;

public class LoggingPanel extends JScrollPane {

    private static JTextPane logArea = null;

    private DatCon datCon = null;

    public static LoggingPanel instance;

    public LoggingPanel(DatCon datCon) {
        this.datCon = datCon;
        instance = this;
        setPreferredSize(new Dimension(400, 100));
        logArea = new JTextPane();
        setViewportView(logArea);
    }

    private static void appendTo(final String msg, final Color c) {
        if (SwingUtilities.isEventDispatchThread()) {
            appendInEDT(msg, c);
        } else {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    appendInEDT(msg, c);
                }
            });
        }
    }

    private static void appendInEDT(String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
                StyleConstants.Foreground, c);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily,
                "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment,
                StyleConstants.ALIGN_JUSTIFIED);

        int len = logArea.getDocument().getLength();
        logArea.setCaretPosition(len);
        logArea.setCharacterAttributes(aset, false);
        logArea.replaceSelection(msg);
    }

    public void Info(String msg) {
        if (msg.length() > 0) {
            appendTo(msg + "\n", Color.BLACK);
            src.Files.DatConLog.Log("MSG: " + msg);
        }
    }

    public void clear() {
        logArea.setText("");
    }

    public void Error(String error) {
        appendTo(error + "\n", Color.RED);
        DatConLog.Log("ERROR: " + error);
    }
}
