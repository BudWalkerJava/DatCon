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
import src.Files.DatConLog;
import src.Files.DatFile;
import src.Files.FileBeingUsed;
import src.V3.Files.DatFileV3;
import src.apps.DatCon;

@SuppressWarnings("serial")
public class LogFilesPanel extends JPanel
        implements ActionListener, PropertyChangeListener, IDatConPanel {
    DatCon datCon = null;

    public static LogFilesPanel instance = null;

    public JFormattedTextField eventFile = null;

    public JFormattedTextField configFile = null;

    public JFormattedTextField recDefsFile = null;

    public JRadioButton eventFileButton = null;

    public JRadioButton configFileButton = null;

    public JRadioButton recDefsFileButton = null;

    private JButton eventViewIt = new JButton("View It");

    private JButton configViewIt = new JButton("View It");

    private JButton recDefsViewIt = new JButton("View It");

    public String cloFileName;

    public String eloFileName;

    public String recDefsFileName;

    public PrintStream recDefsPS = null;

    public PrintStream cloPS = null;

    public PrintStream eloPS = null;

    public LogFilesPanel(DatCon datCon) {
        this.datCon = datCon;
        instance = this;

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

        gbc.gridx = 2;
        gbc.gridy = 5;
        recDefsFileButton = new JRadioButton("RecDefs File");
        add(recDefsFileButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 5;
        add(recDefsViewIt, gbc);
        recDefsViewIt.addActionListener(this);

        gbc.gridx = 3;
        gbc.gridy = 5;
        recDefsFile = new JFormattedTextField(Formatters.FileNameFormatter());
        recDefsFile.setColumns(15);
        recDefsFile.addPropertyChangeListener("value", this);
        add(recDefsFile, gbc);

        reset();
    }

    public void updateAfterGo() {
        eventViewIt.setEnabled(eventFileButton.isSelected());
        configViewIt.setEnabled(configFileButton.isSelected());
        recDefsViewIt.setEnabled(recDefsFileButton.isSelected());
    }

    public void updateAfterPreAnalyze(DatFile datFile) {
        recDefsFileButton.setEnabled(datFile instanceof DatFileV3);
    }

    public void reset() {
        eventFileButton.setSelected(false);
        configFileButton.setSelected(false);
        recDefsFileButton.setSelected(false);
        eventViewIt.setEnabled(false);
        configViewIt.setEnabled(false);
        recDefsViewIt.setEnabled(false);
        eventFile.setText("");
        configFile.setText("");
        recDefsFile.setText("");
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
            if (source == recDefsViewIt) {
                Desktop.getDesktop().open(
                        new File(datCon.outputDirName + "/" + recDefsFileName));
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
            } else if (source == recDefsFile) {
                recDefsFileName = recDefsFile.getText();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        convertDat.eloPS = eloPS;
        convertDat.cloPS = cloPS;
        convertDat.recDefsPS = recDefsPS;
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
        }
        if (recDefsFileButton.isSelected()) {
            datCon.log.Info("recDefs : " + outDirName + "\\" + recDefsFileName);
            if (recDefsFileName.length() > 0) {
                recDefsPS = new PrintStream(outDirName + "/" + recDefsFileName);
            }
        }
    }

    @Override
    public void closePrintStreams() {
        if (eloPS != null)
            eloPS.close();
        if (cloPS != null)
            cloPS.close();
        if (recDefsPS != null)
            recDefsPS.close();
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        eloFileName = flyFileNameRoot + ".log.txt";
        cloFileName = flyFileNameRoot + ".config.txt";
        recDefsFileName = flyFileNameRoot + ".recDefs.txt";
        eventFile.setText(eloFileName);
        configFile.setText(cloFileName);
        recDefsFile.setText(recDefsFileName);
    }

}
