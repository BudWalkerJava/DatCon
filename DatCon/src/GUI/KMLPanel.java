
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import src.Files.ConvertDat;
import src.Files.ConvertDat.KmlType;
import src.Files.FileBeingUsed;
import src.Files.GoogleElevation;
import src.Files.DatConLog;
import src.apps.DatCon;

@SuppressWarnings("serial")
public class KMLPanel extends JPanel
        implements ActionListener, PropertyChangeListener, IDatConPanel {

    DatCon datCon = null;

    JRadioButton groundTrack = new JRadioButton("Ground Track");

    JRadioButton profile = null;

    private JFormattedTextField homePointElevationField = null;

    public JFormattedTextField kmlFileTextField = null;

    ButtonGroup buttonGroup = new ButtonGroup();

    private double homePointElevation = 0.0;

    private JButton viewIt = null;

    private LoggingPanel log = null;

    public KmlType kmlType = KmlType.NONE;

    boolean groundTrackSelected = false;

    boolean profileSelected = false;

    public PrintStream kmlPS;

    public File kmlFile;

    public String kmlFileName;

    public KMLPanel(DatCon datCon) {
        this.datCon = datCon;
        log = datCon.log;

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel label = new JLabel("KML");
        Font font = new Font("Verdana", Font.BOLD, 16);
        label.setFont(font);
        add(label, gbc);
        gbc.weightx = 0.5;

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(new JLabel("KML File"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        kmlFileTextField = new JFormattedTextField(
                Formatters.FileNameFormatter());
        kmlFileTextField.setColumns(15);
        kmlFileTextField.addPropertyChangeListener("value", this);
        add(kmlFileTextField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        viewIt = new JButton("View It");
        viewIt.addActionListener(this);
        add(viewIt, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(groundTrack, gbc);
        groundTrack.addActionListener(this);
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 3;
        profile = new JRadioButton("Profile");
        profile.setToolTipText("<html>To enable the Profile option<br>"
                + "You must obtain the Home Point Elevation from Google Earth<br>"
                + "and enter it here.</html>");
        add(profile, gbc);
        profile.addActionListener(this);

        gbc.gridx = 3;
        gbc.gridy = 3;
        JLabel metersLabel = new JLabel("Meters");
        add(metersLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        homePointElevationField = new JFormattedTextField(
                Formatters.DoubleFormatter());
        homePointElevationField.setColumns(15);
        homePointElevationField.addPropertyChangeListener("value", this);
        add(homePointElevationField, gbc);
        //
        // gbc.gridx = 2;
        // gbc.gridy = 4;
        // gbc.gridwidth = 2;
        // add(new JLabel("Enter Home Point Elevation from Google Earth"), gbc);

        reset();
    }

    public void updateAfterGo(ConvertDat convertDat) {
        viewIt.setEnabled(kmlType != KmlType.NONE);
        if (convertDat != null && convertDat.isHpValid()
                && homePointElevationField.getValue() == null) {
            GoogleElevation ge = new GoogleElevation(this,
                    convertDat.getHPLatDeg(), convertDat.getHPLongDeg());
            ge.execute();
        }
    }

    public void reset() {
        groundTrack.setEnabled(true);
        groundTrack.setSelected(false);
        profile.setEnabled(false);
        homePointElevationField.setValue(null);
        homePointElevationField.setText("Enter HP Elevation");
        homePointElevation = Double.NaN;
        groundTrackSelected = false;
        profileSelected = false;
        profile.setSelected(profileSelected);
        groundTrack.setSelected(groundTrackSelected);
        viewIt.setEnabled(false);
    }

    public void dontViewIt() {
        viewIt.setEnabled(false);
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        convertDat.kmlFileName = kmlFileTextField.getText();
        convertDat.homePointElevation = homePointElevation;
        convertDat.setTakeOffAlt(homePointElevation);
        convertDat.kmlPS = kmlPS;
        if (profile.isSelected()) {
            convertDat.kmlType = KmlType.PROFILE;
        } else if (groundTrack.isSelected()) {
            convertDat.kmlType = KmlType.GROUNDTRACK;
        } else {
            convertDat.kmlType = KmlType.NONE;
        }
    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
        if (profile.isSelected()) {
            kmlType = KmlType.PROFILE;
        } else if (groundTrack.isSelected()) {
            kmlType = KmlType.GROUNDTRACK;
        } else {
            kmlType = KmlType.NONE;
        }
        if (kmlType != KmlType.NONE) {
            log.Info("kml File : " + outDirName + "\\" + kmlFileName);
            if (kmlFileName.length() > 0) {
                kmlFile = new File(outDirName + "/" + kmlFileName);
                kmlPS = new PrintStream(kmlFile);
                kmlPrelude();
            }
        }
    }

    @Override
    public void closePrintStreams() {
        if (kmlPS != null) {
            kmlPS.print("       </coordinates>\n" + "    </LineString>\n"
                    + "  </Placemark>\n");
            kmlPS.print(" </Document>\n" + "</kml>\n");
            kmlPS.close();
        }
    }

    private void kmlPrelude() {
        String kmlFileNameRoot = kmlFileName.substring(0,
                kmlFileName.indexOf('.'));
        kmlPS.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + " <kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
                + "  <Document>\n" + "   <name>" + kmlFileNameRoot + "</name>\n"
                + "    <description>DatKML</description>\n"
                + "     <Style id=\"red\">\n" + "      <LineStyle>\n"
                + "       <color>ff0000ff</color>\n"
                + "       <width>3</width>\n" + "      </LineStyle>\n"
                + "      </Style>\n" + "     <Style id=\"green\">\n"
                + "      <LineStyle>\n" + "       <color>ff00ff00</color>\n"
                + "       <width>3</width>\n" + "      </LineStyle>\n"
                + "      </Style>\n" + "     <Style id=\"blue\">\n"
                + "      <LineStyle>\n" + "       <color>ffff0000</color>\n"
                + "       <width>3</width>\n" + "      </LineStyle>\n"
                + "      </Style>\n");
        kmlPS.print(
                "       <Placemark>\n" + "         <styleUrl>#red</styleUrl>\n"
                        + "          <LineString>\n");
        if (kmlType == KmlType.GROUNDTRACK) {
            kmlPS.print("           <tessellate>1</tessellate>\n");
        } else if (kmlType == KmlType.PROFILE) {
            kmlPS.print("           <altitudeMode>absolute</altitudeMode>\n");
        }
        kmlPS.print("            <coordinates>\n");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == groundTrack) {
                groundTrackSelected = !groundTrackSelected;
                if (groundTrackSelected)
                    profileSelected = false;
                viewIt.setEnabled(false);
            } else if (source == profile) {
                profileSelected = !profileSelected;
                if (profileSelected)
                    groundTrackSelected = false;
                viewIt.setEnabled(false);
            }
            profile.setSelected(profileSelected);
            groundTrack.setSelected(groundTrackSelected);
            if (source == viewIt) {
                try {
                    Desktop.getDesktop().open(kmlFile);
                } catch (IOException e) {
                    DatConLog.Exception(e);
                }
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            if (source == kmlFileTextField) {
            } else if (source == homePointElevationField) {
                Double newHPE = (Double) evt.getNewValue();
                if (newHPE != null) {
                    homePointElevation = newHPE;
                    profile.setEnabled(true);
                    profile.doClick();
                    viewIt.setEnabled(false);
                }
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        kmlFileName = flyFileNameRoot + ".kml";
        kmlFileTextField.setText(kmlFileName);
    }

    public void setHPelevation(double homePointElevation) {
        this.homePointElevation = homePointElevation;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                homePointElevationField.setValue(homePointElevation);
                profile.setEnabled(true);
                profile.doClick();
                viewIt.setEnabled(false);
            }
        });
    }

    public void resetHPelevation() {
        this.homePointElevation = 0.0;
        homePointElevationField.setText("Enter HP Elevation");
        profile.setEnabled(false);
        viewIt.setEnabled(false);
    }

}
