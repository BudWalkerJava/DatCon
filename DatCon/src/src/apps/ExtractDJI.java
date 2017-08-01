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

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Extract.CheckUpdates;
import src.Extract.ExtractDJIMenuBar;
import src.Extract.LoggingPanel;
import src.Extract.Persist;

import src.Files.DatFile;

public class ExtractDJI extends JPanel
        implements ActionListener, MouseListener {
    public static JFrame frame = null;

    static public final String version = "1.0.0";

    public boolean checkUpdts = true;

    public boolean showNewVerAvail = true;

    public DatFile datFile = null;

    JPanel contentPanel = null;

    static JFileChooser fc;

    static JFileChooser dc;

    Color contentPaneBGColor = null;

    JButton dirViewIt = new JButton("View It");

    JButton goButton = new JButton("GO!");

    JTextField datFileTextField = new JTextField(
            "Click here to specify DJI Assistant 2 .DAT file");

    JTextField outputDirTextField = new JTextField(
            "Click here to specify output directory");

    public File inputFile = null;

    public File outputDir = null;

    public Persist persist;

    public CheckUpdates checkUpdates = null;

    public String outputDirName = "";

    public String inputFileName = "";

    String datFileName = "";

    public ExtractDJIMenuBar menuBar = null;

    public LoggingPanel log = null;

    private class OutPanel extends JPanel {
        private OutPanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setOpaque(true);
            JLabel outDirLabel = new JLabel("Output Dir  ");
            this.add(outDirLabel);
            add(outputDirTextField);
            add(dirViewIt);
        }
    }

    public ExtractDJI() {
        persist = new Persist(this);
        checkUpdates = new CheckUpdates(this);
    }

    public Container createContentPane() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        contentPanel.setOpaque(true);
        contentPaneBGColor = contentPanel.getBackground();
        log = new LoggingPanel(this);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel datFileLabel = new JLabel(".DAT file");
        contentPanel.add(datFileLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        datFileTextField.addMouseListener(this);
        contentPanel.add(datFileTextField, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        OutPanel op = new OutPanel();
        outputDirTextField.addMouseListener(this);
        dirViewIt.addActionListener(this);
        contentPanel.add(op, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        contentPanel.add(goButton, gbc);
        goButton.setEnabled(false);
        goButton.addActionListener(this);
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(log, gbc);
        gbc.gridwidth = 1;

        File outDirFile = new File(outputDirName);
        if (outDirFile.exists())
            setOutputDir(outDirFile);
        File inputFile = new File(inputFileName);
        if (inputFile.exists()) {
            setInputFile(inputFile);
        } else {
            File inputDir = inputFile.getParentFile();
            fc.setCurrentDirectory(inputDir);
        }
        checkState();
        return contentPanel;
    }

    private void getNewDatFile() {
        if (inputFile != null) {
            fc.setSelectedFile(inputFile);
        }
        try {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                inputFile = fc.getSelectedFile();
                setDatFile();
            }
        } catch (Exception e) {
            log.Exception(e);
        }
    }

    private void setDatFile() {
        try {
            datFileName = inputFile.getAbsolutePath();
            if (checkDatFile(datFileName)) {
                datFileTextField.setText(datFileName);
                checkState();
                persist.save();
            } else {
                log.Error(datFileName
                        + " is not a .DAT file produced by DJI Assistant 2");
            }

        } catch (Exception e) {
            log.Exception(e);
        }
    }

    private boolean checkDatFile(String datFileName) {
        byte arra[] = new byte[256];
        FileInputStream bfr = null;
        try {
            bfr = new FileInputStream(new File(datFileName));
            bfr.read(arra, 0, 256);
            if ((arra[0] == (byte) 0x78) && (arra[1] == (byte) 0x9C)
                    && (arra[2] == (byte) 0xE4)) {
                bfr.close();
                return true;
            }
        } catch (IOException e) {

        }
        return false;
    }

    Doit doit = null;

    private void go() {
        log.Info("Extracting from " + datFileName);
        doit = new Doit(datFileName, outputDirName, log);
        doit.execute();
    }

    private class Doit extends SwingWorker<Void, String> {

        static final int headerLen = 283;

        static final int bufSize = 1000;

        private String datFileName;

        private String outFileName;

        private LoggingPanel log;

        public Doit(String dfN, String odN, LoggingPanel log) {
            this.datFileName = dfN;
            this.outFileName = odN;
            this.log = log;
        }

        @Override
        protected Void doInBackground() throws Exception {
            startWaitCursor();
            goButton.setBackground(Color.BLUE);
            goButton.setForeground(Color.WHITE);
            goButton.setEnabled(false);
            goButton.setText("Extracting .DAT(s)");
            extract(datFileName, outFileName);
            return null;
        }

        @Override
        protected void done() {
            try {
                super.done();
                checkState();
                stopWaitCursor();
            } catch (Exception e) {
                log.Exception(e);
            }
        }

        @Override
        protected void process(List<String> msgs) {
            for (int i = 0; i < msgs.size(); i++) {
                log.Msg(msgs.get(i));
            }
        }

        public void extract(String datFileName, String outputDirName) {
            byte buffer[] = new byte[bufSize];
            byte header[] = new byte[headerLen];
            int len = 0;

            try {
                InputStream datFileStream = new FileInputStream(datFileName);
                Inflater inflater = new Inflater();
                InflaterInputStream inflaterInStream = new InflaterInputStream(
                        datFileStream, inflater, 1000);
                BufferedInputStream bufferedIS = new BufferedInputStream(
                        inflaterInStream);
                boolean eof = false;

                while (bufferedIS.read(header, 0, headerLen) == headerLen) {
                    long uncompressedFileLength = getUnsignedInt(header, 1);
                    String flyFileName = getString(header, 7);
                    if (flyFileName.indexOf("FLY") == 0) {
                        File flyFile = new File(
                                outputDirName + "/" + flyFileName);
                        publish("\nExtracting: " + flyFileName + " Length: "
                                + uncompressedFileLength + "  ");
                        FileOutputStream flyOS = new FileOutputStream(flyFile);
                        boolean done = false;
                        long outFileSize = 0;
                        int chunkLen = 0;
                        int chunkNum = 0;
                        while (!done) {
                            int bufLength = bufSize;
                            if (outFileSize
                                    + bufSize > uncompressedFileLength) {
                                bufLength = (int) (uncompressedFileLength
                                        - outFileSize);
                                done = true;
                            }
                            len = bufferedIS.read(buffer, 0, bufLength);
                            if (len < bufLength) {
                                eof = true;
                            }
                            flyOS.write(buffer, 0, len);
                            outFileSize += len;
                            chunkLen += len;
                            if (chunkLen > 1000000) {
                                publish(".");
                                chunkLen = 0;
                                chunkNum++;
                                if (chunkNum > 50) {
                                    chunkNum = 0;
                                    publish("\n");
                                }
                            }
                        }
                        publish("\nFinished Extracting " + datFileName + "!\n");
                        flyOS.close();
                    } else {
                        for (int i = 0; i < uncompressedFileLength; i++) {
                            bufferedIS.read();
                        }
                    }
                }
            } catch (IOException e) {
                log.Error("Error encountered " + e.getMessage() + "\n");
            }
        }
    }

    void setOutputDir(File file) {
        outputDir = file;
        outputDirName = outputDir.getAbsolutePath();
        outputDirTextField.setText(outputDirName);
    }

    public void setInputFile(File inFile) {
        inputFile = inFile;
        datFileTextField.setText(inputFile.getAbsolutePath());
        setDatFile();
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
                    checkState();
                    persist.save();
                }
            }
        } catch (Exception exception) {
            log.Exception(exception);
            ;
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == goButton) {
                go();
            } else if (source == dirViewIt) {
                Desktop.getDesktop().open(new File(outputDirName));
            }
        } catch (Exception exception) {
            log.Exception(exception);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

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

    private static void createAndShowMainGUI() {
        frame = new JFrame("Extract .DAT(s) from DJI Assistant 2 file");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT file",
                "DAT");
        fc = new JFileChooser(/* directory */);
        // Action folder = fc.getActionMap().get("New Folder");
        // folder.setEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filter);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        dc = new JFileChooser();
        dc.setAcceptAllFileFilterUsed(false);
        dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Create and set up the content pane.
        ExtractDJI extract = new ExtractDJI();
        frame.setJMenuBar(new ExtractDJIMenuBar(extract));
        frame.setContentPane(extract.createContentPane());

        // Display the window.
        frame.setSize(850, 550);
        frame.setVisible(true);
        // ImageIcon img = new ImageIcon("drone.jpg");
        // frame.setIconImage(img.getImage());
        if (extract.checkUpdts) {
            extract.checkUpdates.checkForUpdates();
        }
    }

    public void createAndShowGUI() {
        frame = new JFrame("Extract .DAT(s) from DJI Assistant 2 file");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        UIManager.put("ToolTip.background", Color.WHITE);
        UIManager.put("ToolTip.foreground", Color.BLACK);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("DAT file",
                "DAT");
        fc = new JFileChooser(/* directory */);
        // Action folder = fc.getActionMap().get("New Folder");
        // folder.setEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filter);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        dc = new JFileChooser();
        dc.setAcceptAllFileFilterUsed(false);
        dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Create and set up the content pane.
        ExtractDJI extract = new ExtractDJI();
        //frame.setJMenuBar(new ExtractDJIMenuBar(extract));
        frame.setContentPane(extract.createContentPane());

        // Display the window.
        frame.setSize(850, 550);
        frame.setVisible(true);
        // ImageIcon img = new ImageIcon("drone.jpg");
        // frame.setIconImage(img.getImage());
        // if (extract.checkUpdts) {
        // extract.checkUpdates.checkForUpdates();
        // }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowMainGUI();
            }
        });
    }

    public DatFile getDatFile() {
        return datFile;
    }

    private static long getUnsignedInt(byte[] header, int offset) {
        return (long) (0xff & header[offset])
                + (256 * (long) (0xff & header[offset + 1]))
                + (65536 * (long) (0xff & header[offset + 2]))
                + (65536 * 256 * (long) (0xff & header[offset + 3]));
    }

    private static String getString(byte[] header, int offset) {
        int length = 0;
        while (header[offset + length] != 0x00) {
            length++;
        }
        return new String(header, offset, length);
    }
}
