package src.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.FileBeingUsed;
import src.apps.DatCon;

@SuppressWarnings("serial")
public class HPElevation extends JPanel
        implements PropertyChangeListener, IDatConPanel {
    DatCon datCon = null;

    private JFormattedTextField homePointElevationField = null;

    private double homePointElevation = 0.0;

    public HPElevation(DatCon datCon) {
        this.datCon = datCon;
        Font font = new Font("Verdana", Font.BOLD, 14);
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel label = new JLabel("Home Point Elevation");
        label.setFont(font);
        add(label, gbc);

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 3;
        homePointElevationField = new JFormattedTextField(
                Formatters.DoubleFormatter());
        homePointElevationField.setColumns(15);
        homePointElevationField.addPropertyChangeListener("value", this);
        add(homePointElevationField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(new JLabel("Enter Home Point Elevation from Google Earth"), gbc);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == homePointElevationField) {
                Double newHPE = (Double) evt.getNewValue();
                if (newHPE != null) {
                    homePointElevation = newHPE;
                    datCon.kmlPanel.setHPelevation(homePointElevation);
                }
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void reset() {
        homePointElevationField.setText("");
        homePointElevationField.setValue(null);
        homePointElevation = 0.0;
    }

    //    @Override
    //    public void setArgs(ConvertDat convertDat) {
    //        convertDat.homePointElevation = (float) homePointElevation;
    //    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
    }

    @Override
    public void closePrintStreams() {
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
    }

    public void setHPelevation(double elevation) {
        homePointElevationField.setValue(elevation);
        homePointElevation = elevation;
        datCon.kmlPanel.setHPelevation(elevation);
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        throw new RuntimeException("HPElevation.setArgs called");
    }

}
