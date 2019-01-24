/* DatCon class

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

package src.apps;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.DJIAssistantFile;
import src.Files.DatConLog;
import src.Files.DatConPopups;
import src.Files.DatFile;
import src.Files.FileBeingUsed;
import src.Files.Persist;
import src.Files.WorkingDir;
import src.GUI.CheckUpdates;
import src.GUI.CsvPanel;
import src.GUI.DatConMenuBar;
import src.GUI.DataModelDialog;
import src.GUI.KMLPanel;
import src.GUI.LogFilesPanel;
import src.GUI.LoggingPanel;
import src.GUI.TimeAxisPanel;

@SuppressWarnings("serial")
public class DatCon extends JPanel
        implements ActionListener, ComponentListener, MouseListener {

    static public final String version = "3.5.0";

    public static JFrame frame = null;

    public DatFile datFile = null;

    JPanel contentPanel = null;

    static JFileChooser fc;

    static JFileChooser dc;

    public static DatCon instance = null;

    Color contentPaneBGColor = null;

    JButton dirViewIt = new JButton("View It");

    public JButton goButton = new JButton("GO!");

    JTextField datFileTextField = new JTextField(
            "Click here to specify .DAT file");

    JTextField outputDirTextField = new JTextField(
            "Click here to specify output directory");

    public File inputFile = null;

    public File outputDir = null;

    public static Persist persist;

    public CheckUpdates checkUpdates = null;

    public String outputDirName = "";

    public static int frameHeight = 900;

    public static int frameWidth = 950;

    //public static Dimension datConSize = new Dimension(800, 300);

    String datFileName = "";

    Go doit = null;

    public DatConMenuBar menuBar = null;

    public TimeAxisPanel timeAxisPanel = null;

    public KMLPanel kmlPanel = null;

    private CsvPanel csvPanel;

    public LoggingPanel log = null;

    private LogFilesPanel logFilesPanel = null;

    private Timer resizeTimer = null;

    public DatCon() {
        DatCon.instance = this;
        new Persist();
        checkUpdates = new CheckUpdates(this);
    }

    public Container createContentPane() {
        new WorkingDir(this);
        resizeTimer = new Timer(250, this);
        resizeTimer.setRepeats(false);
        contentPanel = new JPanel();
        contentPanel.addComponentListener(this);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        contentPanel.setOpaque(true);
        contentPaneBGColor = contentPanel.getBackground();
        log = new LoggingPanel();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel datFileLabel = new JLabel(".DAT file");
        contentPanel.add(datFileLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(datFileTextField, gbc);
        //        datFileTextField
        //                .setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        datFileTextField.addMouseListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel outDirLabel = new JLabel("Output Dir  ");
        contentPanel.add(outDirLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(outputDirTextField, gbc);
        outputDirTextField.addMouseListener(this);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(dirViewIt, gbc);
        dirViewIt.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        timeAxisPanel = new TimeAxisPanel(this);
        contentPanel.add(timeAxisPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        csvPanel = new CsvPanel(this);
        contentPanel.add(csvPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        logFilesPanel = new LogFilesPanel(this);
        contentPanel.add(logFilesPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        kmlPanel = new KMLPanel(this);
        contentPanel.add(kmlPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(goButton, gbc);
        goButton.setEnabled(false);
        goButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 6;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(log, gbc);

        createEmptyBox(1, 8, gbc);
        createEmptyBox(2, 8, gbc);
        createEmptyBox(3, 8, gbc);
        createEmptyBox(4, 8, gbc);

        outputDirName = Persist.outputDirName;
        File outDirFile = new File(outputDirName);
        if (outDirFile.exists())
            setOutputDir(outDirFile);
        File inputFile = new File(Persist.inputFileName);
        if (inputFile.exists() && Persist.loadLastOnStartup) {
            //setInputFile(inputFile);
            setDatFile(inputFile);
        } else {
            File inputDir = inputFile.getParentFile();
            fc.setCurrentDirectory(inputDir);
        }
        checkState();
        //contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        return contentPanel;
    }

    private void createEmptyBox(int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets.set(0, 0, 0, 0);
        contentPanel.add(Box.createRigidArea(new Dimension(50, 0)), gbc);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Persist.datConSize = frame.getSize();
        if (resizeTimer.isRunning()) {
            resizeTimer.restart();
        } else {
            resizeTimer.start();
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    private void getNewDatFile() {
        if (inputFile != null) {
            fc.setSelectedFile(inputFile);
        }
        try {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File iFile = fc.getSelectedFile();
                setDatFile(iFile);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    private void setDatFile(File iFile) {
        try {
            if (DatFile.isDatFile(iFile.getAbsolutePath())
                    || DJIAssistantFile.isDJIDat(iFile)
                    || Persist.invalidStructOK) {
                PreAnalyze fmTask = new PreAnalyze(iFile, this);
                fmTask.execute();
                inputFile = iFile;
                setInputFile(inputFile);
            } else {
                log.Error(
                        iFile.getAbsolutePath() + " is not a valid .DAT file");
            }
        } catch (IOException e) {
            log.Error(iFile.getAbsolutePath() + " is not a valid .DAT file");
        }
    }

    public void createFileNames() {
        String flyFileName = "";
        String flyFileNameRoot = "";
        File inputFile = new File(datFileName);
        flyFileName = inputFile.getName();
        flyFileNameRoot = flyFileName.substring(0,
                flyFileName.lastIndexOf('.'));
        csvPanel.createFileNames(flyFileNameRoot);
        logFilesPanel.createFileNames(flyFileNameRoot);
        kmlPanel.createFileNames(flyFileNameRoot);
    }

    private class PreAnalyze extends SwingWorker<Object, Object> {
        File iFile = null;

        private DatCon datCon;

        PreAnalyze(File iFile, DatCon datCon) {
            this.iFile = iFile;
            this.datCon = datCon;
        }

        @Override
        protected Object doInBackground() throws Exception {
            startWaitCursor();
            try {
                datFile = DatFile.createDatFile(iFile.getAbsolutePath(),
                        datCon);
                if (datFile != null) {
                    datFileName = datFile.getFile().getAbsolutePath();
                    datFileTextField.setText(datFileName);
                    //inputFile = datFile.getFile();
                    Persist.save();
                    goButton.setBackground(Color.YELLOW);
                    goButton.setForeground(Color.BLACK);
                    goButton.setEnabled(false);
                    goButton.setText("Pre Analyzing .DAT");
                    datFile.reset();
                    datFile.preAnalyze();
                    setFromMarkers();
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                            timeAxisPanel.initFromDatFile(datFile);
                            LogFilesPanel.instance
                                    .updateAfterPreAnalyze(datFile);
                            DatConLog.separator();
                            createFileNames();
                            checkState();
                            Persist.save();
                        }
                    });
                }
            } finally {
                stopWaitCursor();
            }
            return null;
        }
    }

    public void setFromMarkers() throws Exception {
        if (datFile != null) {
            timeAxisPanel.setFromMarkers(datFile);
        }
    }

    private void go() {
        ConvertDat convertDat = datFile.createConVertDat();
        try {
            log.Info("Converting " + datFileName);
            createPrintStreams();
            setArgs(convertDat);
            convertDat.createRecordParsers();
            //            convertDat.createSystemRecords();
            doit = new Go(convertDat);
            doit.execute();
        } catch (FileBeingUsed fbu) {
            log.Error("Can't convert because " + fbu.getFileName()
                    + " is being used");
        }
    }

    private class Go extends SwingWorker<AnalyzeDatResults, Void> {
        ConvertDat convertDat = null;

        AnalyzeDatResults results;

        Go(ConvertDat convertDat) {
            this.convertDat = convertDat;
        }
        //
        //        public void setAnalyzeDat(ConvertDat convertDat) {
        //            this.convertDat = convertDat;
        //        }
        //
        //        public Go() {
        //        }

        @Override
        protected AnalyzeDatResults doInBackground() throws Exception {
            try {
                startWaitCursor();
                goButton.setBackground(Color.BLUE);
                goButton.setForeground(Color.WHITE);
                goButton.setEnabled(false);
                goButton.setText("Converting .DAT");
                datFile.reset();
                results = convertDat.analyze(true);
            } catch (Exception e) {
                //                LoggingPanel.instance.Error("Can't Convert");
                DatConLog.Exception(e, "Can't Convert");
                stopWaitCursor();
            }
            return results;
        }

        @Override
        protected void done() {
            try {
                super.done();
                updateAfterGo();
                closePrintStreams();
                // datFile.close();
                log.Info(results.toString());
                checkState();
                stopWaitCursor();
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }

        private void updateAfterGo() {
            csvPanel.updateAfterGo();
            logFilesPanel.updateAfterGo();
            kmlPanel.updateAfterGo(convertDat);
        }
    }

    private void createPrintStreams() throws FileBeingUsed {
        try {
            csvPanel.createPrintStreams(outputDirName);
            logFilesPanel.createPrintStreams(outputDirName);
            kmlPanel.createPrintStreams(outputDirName);
        } catch (FileNotFoundException e) {
            String msg = e.getMessage();
            if (msg.indexOf(
                    "because it is being used by another process)") > 0) {
                String fileName = msg.substring(0, msg.indexOf(" ("));
                throw (new FileBeingUsed(fileName));
            }
        }
    }

    public void closePrintStreams() {
        csvPanel.closePrintStreams();
        logFilesPanel.closePrintStreams();
        kmlPanel.closePrintStreams();
    }

    void setOutputDir(File file) {
        outputDir = file;
        outputDirName = outputDir.getAbsolutePath();
        outputDirTextField.setText(outputDirName);
    }

    private void reset() {
        timeAxisPanel.reset();
        csvPanel.reset();
        //HPElevationPanel.reset();
        logFilesPanel.reset();
        kmlPanel.reset();
        //HPElevationPanel.reset();
    }

    public void dontViewIt() {
        csvPanel.dontViewIt();
        logFilesPanel.dontViewIt();
        kmlPanel.dontViewIt();
    }

    private void setArgs(ConvertDat convertDat) {
        timeAxisPanel.setArgs(convertDat);
        csvPanel.setArgs(convertDat);
        logFilesPanel.setArgs(convertDat);
        kmlPanel.setArgs(convertDat);
        //HPElevationPanel.setArgs(convertDat);
    }

    public void setInputFile(File inFile) {
        inputFile = inFile;
        String fName = inputFile.getAbsolutePath();
        Persist.inputFileName = fName;
        datFileTextField.setText(fName);
        setOutputDir(inputFile.getParentFile());
    }

    public void checkState() {
        String cantGo = "";
        if (outputDir != null && outputDirTextField.getText().length() > 0) {
            outputDirTextField.setBackground(Color.WHITE);
        } else {
            outputDirTextField.setBackground(Color.RED);
            cantGo += "OutputDir not specified,";
        }
        if (inputFile != null && datFileTextField.getText().length() > 0) {
            datFileTextField.setBackground(Color.WHITE);
        } else {
            datFileTextField.setBackground(Color.RED);
            cantGo += ".DAT file not specified,";
        }
        if (timeAxisPanel.tickLower >= timeAxisPanel.tickUpper) {
            cantGo += "Lower is greater than Upper";
        }
        // if (timeAxisPanel.gpsLockTick > 0
        // && timeAxisPanel.tickLower >= timeAxisPanel.gpsLockTick) {
        // dashwarePanel.enableDashware(true);
        // }
        // if (timeAxisPanel.gpsLockTick == -1
        // || timeAxisPanel.tickLower < timeAxisPanel.gpsLockTick) {
        // dashwarePanel.enableDashware(false);
        // }
        if (cantGo.length() > 0) {
            goButton.setBackground(Color.RED);
            goButton.setForeground(Color.BLACK);
            goButton.setEnabled(false);
            goButton.setText("Can't Go: " + cantGo);
        } else {
            goButton.setBackground(Color.GREEN);
            goButton.setEnabled(true);
            goButton.setText("GO!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == datFileTextField) {
                getNewDatFile();
            } else if (source == outputDirTextField) {
                if (outputDir != null)
                    dc.setSelectedFile(outputDir);
                int returnVal = dc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    setOutputDir(dc.getSelectedFile());
                    Persist.outputDirName = outputDirName;
                    Persist.save();
                    checkState();
                }
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
            ;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object source = (e.getSource());
            if (source == goButton) {
                go();
            } else if (source == dirViewIt) {
                Desktop.getDesktop().open(new File(outputDirName));
            } else if (source == resizeTimer) {
                Persist.save();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void startWaitCursor() {
        RootPaneContainer root = (RootPaneContainer) frame.getRootPane()
                .getTopLevelAncestor();
        root.getGlassPane()
                .setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        root.getGlassPane().addMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(true);
    }

    public void stopWaitCursor() {
        RootPaneContainer root = (RootPaneContainer) frame.getRootPane()
                .getTopLevelAncestor();
        root.getGlassPane()
                .setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        root.getGlassPane().removeMouseListener(mouseAdapter);
        root.getGlassPane().setVisible(false);
    }

    private final static MouseAdapter mouseAdapter = new MouseAdapter() {
    };

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("FileChooser.readOnly", Boolean.TRUE);
            UIManager.put("ToolTip.background", Color.WHITE);
            UIManager.put("ToolTip.foreground", Color.BLACK);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "DAT file", "DAT");
            fc = new JFileChooser(/* directory */);
            // Action folder = fc.getActionMap().get("New Folder");
            // folder.setEnabled(false);
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(filter);
            // fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            dc = new JFileChooser();
            dc.setAcceptAllFileFilterUsed(false);
            dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // Create and set up the content pane.
            DatCon datCon = new DatCon();
            frame = new JFrame("DatCon");
            //        frame.addComponentListener(new ComponentAdapter() {
            //            public void componentResized(ComponentEvent evt) {
            //                Component c = (Component) evt.getSource();
            //                int x = 1;
            //            }
            //        });
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.setJMenuBar(datCon.createMenuBar());
            frame.setJMenuBar(new DatConMenuBar(datCon));
            frame.setContentPane(datCon.createContentPane());

            // Display the window.
            frame.setSize(Persist.datConSize);
            frame.setVisible(true);
            ImageIcon img = new ImageIcon("drone.jpg");
            frame.setIconImage(img.getImage());
            if (Persist.checkUpdts) {
                datCon.checkUpdates.checkForUpdates();
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {

        DatConLog log = new DatConLog();
        if (!log.ok()) {
            DatConPopups.noLogFile();
            System.exit(1);
        }
        String dataModel = System.getProperty("sun.arch.data.model");
        if (dataModel.equals("64")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createAndShowGUI();
                }
            });
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener()
        {
            public void propertyChange(final PropertyChangeEvent e)
            {
                if (e.getNewValue() instanceof JTextField)
                {
                                        SwingUtilities.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            JTextField textField = (JTextField)e.getNewValue();
                            textField.selectAll();
                        }
                    });

                }
            }
        });
        } else {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DataModelDialog.createAndShowDataModelDialog();
                }
            });
        }
    }

    public DatFile getDatFile() {
        return datFile;
    }
}
