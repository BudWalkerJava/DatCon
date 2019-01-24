/*  CheckUpdates class

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
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

import src.Files.DatConLog;
import src.Files.Persist;
import src.apps.DatCon;

public class CheckUpdates implements ActionListener {

    DatCon datCon = null;

    public CheckUpdates(DatCon datCon) {
        this.datCon = datCon;
    }

    JDialog dialog = null;

    JButton okButton = new JButton("OK");

    JButton gotoButton = new JButton("Goto datfile.net/downloads");

    JButton dontShowAgain = new JButton("Don't show this again");

    private void createUpdateDialog() {
        dialog = new JDialog(DatCon.frame, "Newer Version Available", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocation(100, 100);
        // Set size
        dialog.setSize(400, 150);
        dialog.getContentPane().setBackground(Color.ORANGE);

        // Set some layout
        BoxLayout bl = new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS);
        dialog.setLayout(bl);
        JLabel label = new JLabel(
                "Newer Version Available at datfile.net/downloads");
        Font font = new Font("Verdana", Font.BOLD, 12);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(label);

        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(this);
        dialog.add(okButton);

        gotoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(gotoButton);
        gotoButton.addActionListener(this);

        dontShowAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(dontShowAgain);
        dontShowAgain.addActionListener(this);

        dialog.setVisible(true);
    }

    private boolean newVersionAvailable() {
        
        return false;
    }

    private int compareVersions(String v1, String v2) {
        String v1Tokens[] = v1.split("\\.");
        String v2Tokens[] = v2.split("\\.");
        if (v1Tokens.length > v2Tokens.length)
            return 1;
        if (v1Tokens.length < v2Tokens.length)
            return -1;
        for (int i = 0; i < v1Tokens.length; i++) {
            int compResult = v1Tokens[i].compareTo(v2Tokens[i]);
            if (compResult != 0)
                return compResult;
        }
        return 0;
    }

    public void checkForUpdates() {
        newerVersionOnServer = false;
        QueryServer fmTask = new QueryServer();
        fmTask.execute();
    }

    private boolean newerVersionOnServer = false;

    private class QueryServer extends SwingWorker<Object, Object> {
        QueryServer() {
        }

        protected Object doInBackground() throws Exception {
            newerVersionOnServer = newVersionAvailable();
            return null;
        }

        protected void done() {
            try {
                super.done();
                if (newerVersionOnServer && Persist.showNewVerAvail) {
                    createUpdateDialog();
                }
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }

    private ActionEvent lastEvent = null;

    public void actionPerformed(ActionEvent e) {
        if (lastEvent == e)
            return;
        lastEvent = e;
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == okButton) {
                dialog.dispose();
            } else if (source == gotoButton) {
                dialog.dispose();
                Desktop.getDesktop()
                        .browse(new URI("https://datfile.net/downloads.html"));
            } else if (source == dontShowAgain) {
                Persist.showNewVerAvail = false;
                datCon.menuBar.showWhenNewVerAvail.setSelected(false);
                dialog.dispose();
                Persist.save();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }
}
