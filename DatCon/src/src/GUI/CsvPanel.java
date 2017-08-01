/* CsvPanel  class

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
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import src.Files.ConvertDat;
import src.Files.CsvWriter;
import src.Files.FileBeingUsed;
import src.Files.DatConLog;
import src.apps.DatCon;

public class CsvPanel extends JPanel
        implements ActionListener, PropertyChangeListener, IDatConPanel {

    DatCon datCon = null;

    public JRadioButton csvButton = null;

    public JRadioButton eventCsvButton = null;

    private JButton viewIt = new JButton("View It");

    SampRatePanel sampRatePanel = null;

    //public PrintStream csvPS = null;
    public CsvWriter csvWriter = null;

    public String csvFileName = "";

    public JFormattedTextField csvFile = null;

    public boolean csvEventLogOutput = false;

    public CsvPanel(DatCon datCon) {
        this.datCon = datCon;

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;
        JLabel label = new JLabel("CSV");
        Font font = new Font("Verdana", Font.BOLD, 16);
        label.setFont(font);
        add(label, gbc);
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        sampRatePanel = new SampRatePanel(datCon);
        add(sampRatePanel, gbc);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 1;
        gbc.gridy = 2;
        csvButton = new JRadioButton(".CSV");
        csvButton.addActionListener(this);
        add(csvButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;

        csvFile = new JFormattedTextField(Formatters.FileNameFormatter());
        csvFile.setColumns(15);
        csvFile.addPropertyChangeListener("value", this);
        add(csvFile, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        add(viewIt, gbc);
        viewIt.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        eventCsvButton = new JRadioButton("Event Log (column in .csv)");
        eventCsvButton.addActionListener(this);
        add(eventCsvButton, gbc);
        gbc.gridwidth = 1;

        gbc.fill = GridBagConstraints.BOTH;
    }

    public void updateAfterGo() {
        viewIt.setEnabled(csvButton.isSelected());
    }

    public void reset() {
        csvButton.setSelected(true);
        eventCsvButton.setSelected(false);
        csvFile.setText("");
        viewIt.setEnabled(false);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == csvFile) {
                csvFileName = csvFile.getText();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == viewIt) {
                Desktop.getDesktop().open(
                        new File(datCon.outputDirName + "/" + csvFileName));
            }
            if (source == eventCsvButton) {
                if (eventCsvButton.isSelected())
                    csvButton.setSelected(true);
            } else if (source == csvButton) {
                if (!csvButton.isSelected())
                    eventCsvButton.setSelected(false);
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void dontViewIt() {
        viewIt.setEnabled(false);
    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
        if (csvWriter != null) {
            try {
                csvWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            csvWriter = null;
        }
        if (csvButton.isSelected()) {
            datCon.log.Info("Csv file : " + outDirName + "\\" + csvFileName);
            if (csvFileName.length() > 0) {
                try {
                    csvWriter = new CsvWriter(outDirName + "/" + csvFileName);
                } catch (IOException e) {
                    throw new FileBeingUsed(outDirName + "/" + csvFileName);
                }
            }
        }
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        sampRatePanel.setArgs(convertDat);
        convertDat.csvEventLogOutput = eventCsvButton.isSelected();
        convertDat.csvWriter = csvWriter;
    }

    @Override
    public void closePrintStreams() {
        if (csvWriter != null)
            try {
                csvWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        csvFileName = flyFileNameRoot + ".csv";
        csvFile.setText(csvFileName);
    }
}
