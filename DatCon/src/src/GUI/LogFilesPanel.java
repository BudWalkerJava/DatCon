/* LogFilesPanel class

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
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import src.Files.ConvertDat;
import src.Files.FileBeingUsed;
import src.Files.DatConLog;
import src.apps.DatCon;

public class LogFilesPanel extends JPanel
        implements ActionListener, PropertyChangeListener, IDatConPanel {
    DatCon datCon = null;

    public JFormattedTextField eventFile = null;

    public JFormattedTextField configFile = null;

    public JRadioButton eventFileButton = null;

    public JRadioButton configFileButton = null;

    private JButton eventViewIt = new JButton("View It");

    private JButton configViewIt = new JButton("View It");

    private LoggingPanel logging = null;

    public String cloFileName;

    public String opFileName;

    //public PrintStream opPS = null;

    public PrintStream cloPS = null;

    public PrintStream eloPS = null;

    public String eloFileName;

    public LogFilesPanel(DatCon datCon) {
        this.datCon = datCon;
        logging = datCon.log;

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel label = new JLabel("Log Files");
        Font font = new Font("Verdana", Font.BOLD, 16);
        label.setFont(font);
        add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        gbc.gridx = 2;
        gbc.gridy = 3;
        eventFileButton = new JRadioButton("Event Log File");
        add(eventFileButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        eventFile = new JFormattedTextField(Formatters.FileNameFormatter());
        eventFile.setColumns(15);
        eventFile.addPropertyChangeListener("value", this);
        add(eventFile, gbc);
        gbc.weightx = 0.5;

        gbc.gridx = 4;
        gbc.gridy = 3;
        add(eventViewIt, gbc);
        eventViewIt.addActionListener(this);

        gbc.gridx = 2;
        gbc.gridy = 4;
        configFileButton = new JRadioButton("Config Log File");
        add(configFileButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        add(configViewIt, gbc);
        configViewIt.addActionListener(this);

        gbc.gridx = 3;
        gbc.gridy = 4;
        configFile = new JFormattedTextField(Formatters.FileNameFormatter());
        configFile.setColumns(15);
        configFile.addPropertyChangeListener("value", this);
        add(configFile, gbc);

        reset();
    }

    public void updateAfterGo() {
        eventViewIt.setEnabled(eventFileButton.isSelected());
        configViewIt.setEnabled(configFileButton.isSelected());
    }

    public void reset() {
        eventFileButton.setSelected(false);
        configFileButton.setSelected(false);
        eventViewIt.setEnabled(false);
        configViewIt.setEnabled(false);
        eventFile.setText("");
        configFile.setText("");
    }

    public void dontViewIt() {
        eventViewIt.setEnabled(false);
        configViewIt.setEnabled(false);
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == eventViewIt) {
                Desktop.getDesktop().open(
                        new File(datCon.outputDirName + "/" + eloFileName));
            }
            if (source == configViewIt) {
                Desktop.getDesktop().open(
                        new File(datCon.outputDirName + "/" + cloFileName));
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == eventFile) {
                eloFileName = eventFile.getText();
            } else if (source == configFile) {
                cloFileName = configFile.getText();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        convertDat.eloPS = eloPS;
        convertDat.cloPS = cloPS;
        //convertDat.opPS = opPS;
    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
        if (eventFileButton.isSelected()) {
            datCon.log.Info("eventLog : " + outDirName + "\\" + eloFileName);
            if (eloFileName.length() > 0) {
                eloPS = new PrintStream(outDirName + "/" + eloFileName);
            }
        }
        if (configFileButton.isSelected()) {
            datCon.log.Info("configLog : " + outDirName + "\\" + cloFileName);
            if (cloFileName.length() > 0) {
                cloPS = new PrintStream(outDirName + "/" + cloFileName);
            }
            //            if (ConvertDat.EXPERIMENTAL_DEV) {
            //                opPS = new PrintStream(outDirName + "/" + opFileName);
            //            }
        }
    }

    @Override
    public void closePrintStreams() {
        if (eloPS != null)
            eloPS.close();
        if (cloPS != null)
            cloPS.close();
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        eloFileName = flyFileNameRoot + ".log.txt";
        cloFileName = flyFileNameRoot + ".config.txt";
        opFileName = flyFileNameRoot + datCon.getDatFile().getACTypeString()
                + ".Op.txt";
        eventFile.setText(eloFileName);
        configFile.setText(cloFileName);
    }

}

