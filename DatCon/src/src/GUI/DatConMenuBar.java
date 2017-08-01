/* DatConMenuBar class

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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.apps.DatCon;

public class DatConMenuBar extends JMenuBar implements ActionListener {
    JMenuItem fileOpenMenuItem = null;

    JMenuItem userManualMenuItem = new JMenuItem("User Manual");

    JMenuItem reportBugMenuItem = new JMenuItem("Report a Bug");

    JMenuItem aboutMenuItem = new JMenuItem("About");

    JMenuItem checkUpdatesNow = new JMenuItem("Check for updates now");

    JRadioButtonMenuItem experimentalItem = new JRadioButtonMenuItem(
            "Show experimental fields");

    JRadioButtonMenuItem showUnitsItem = new JRadioButtonMenuItem(
            "Show units in column headings");

    JRadioButtonMenuItem checkUpdatesOnStartup = new JRadioButtonMenuItem(
            "Check for updates on startup");

    JRadioButtonMenuItem showWhenNewVerAvail = new JRadioButtonMenuItem(
            "Show when newer version is available");

    JRadioButtonMenuItem loadLastItem = new JRadioButtonMenuItem(
            "Load last .DAT on Startup");

    JRadioButtonMenuItem autoTranslateDJIAFiles = new JRadioButtonMenuItem(
            "Automatically extract .DAT(s) from DJI Assistant 2 files");

    private DatCon datCon = null;

    public DatConMenuBar(DatCon datCon) {
        this.datCon = datCon;
        this.datCon.menuBar = this;
        JMenu fileMenu = new JMenu("File");
        JMenu preferencesMenu = new JMenu("Preferences");
        JMenu helpMenu = new JMenu("Help");

        fileMenu.setMnemonic(KeyEvent.VK_A);
        this.add(fileMenu);

        fileMenu.add(checkUpdatesNow);
        checkUpdatesNow.addActionListener(this);

        fileMenu.add(checkUpdatesOnStartup);
        checkUpdatesOnStartup.setSelected(datCon.checkUpdts);
        checkUpdatesOnStartup.addActionListener(this);

        fileMenu.add(showWhenNewVerAvail);
        showWhenNewVerAvail.setSelected(datCon.showNewVerAvail);
        showWhenNewVerAvail.addActionListener(this);

        fileMenu.add(new JSeparator());

        fileMenu.add(loadLastItem);
        loadLastItem.setSelected(datCon.loadLastOnStartup);
        loadLastItem.addActionListener(this);

        this.add(preferencesMenu);
        preferencesMenu.add(autoTranslateDJIAFiles);
        autoTranslateDJIAFiles.setSelected(datCon.autoTransDJIAFiles);
        autoTranslateDJIAFiles.addActionListener(this);

        preferencesMenu.add(showUnitsItem);
        showUnitsItem.setSelected(ConvertDat.showUnits);
        showUnitsItem.addActionListener(this);
        
        preferencesMenu.add(experimentalItem);
        experimentalItem.setSelected(ConvertDat.EXPERIMENTAL_FIELDS);
        experimentalItem.addActionListener(this);

        this.add(helpMenu);
        helpMenu.add(userManualMenuItem);
        userManualMenuItem.addActionListener(this);

        helpMenu.add(reportBugMenuItem);
        reportBugMenuItem.addActionListener(this);

        helpMenu.add(new JSeparator());
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(this);
    }

    private void _userManual() {
        try {
            URI uri = new URI("https://datfile.net/Doc/DatCon%20Usage.pdf");
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            DatConLog.Exception(e);
        }
    }

    private void _bug() {
        JOptionPane.showMessageDialog(this,
                "" + "Report a bug by sending it to" + "\nbug@flylog.info"
                        + "\nPlease include the .dotdat file found in your home directory",
                "Report a Bug", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void _about() {
        JOptionPane.showMessageDialog(this,
                "DatCon Version " + ConvertDat.version
                        + "\nAll Rights Reserved",
                "About DatCon", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == userManualMenuItem) {
                _userManual();
            } else if (source == reportBugMenuItem) {
                _bug();
            } else if (source == aboutMenuItem) {
                _about();
            } else if (source == checkUpdatesOnStartup) {
                datCon.checkUpdts = checkUpdatesOnStartup.isSelected();
                datCon.persist.save();
            } else if (source == showWhenNewVerAvail) {
                datCon.showNewVerAvail = showWhenNewVerAvail.isSelected();
                datCon.persist.save();
            } else if (source == checkUpdatesNow) {
                datCon.checkUpdates.checkForUpdates();
            } else if (source == loadLastItem) {
                datCon.loadLastOnStartup = loadLastItem.isSelected();
                datCon.persist.save();
            } else if (source == autoTranslateDJIAFiles) {
                datCon.autoTransDJIAFiles = autoTranslateDJIAFiles.isSelected();
                datCon.persist.save();
            } else if (source == experimentalItem) {
                ConvertDat.EXPERIMENTAL_FIELDS = experimentalItem.isSelected();
                datCon.persist.save();
            } else if (source == showUnitsItem) {
                ConvertDat.showUnits = showUnitsItem.isSelected();
                datCon.persist.save();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }
}
