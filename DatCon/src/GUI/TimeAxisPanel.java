/* TimeAxisPanel class

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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import src.Files.ConvertDat;
import src.Files.DatFile;
import src.Files.FileBeingUsed;
import src.Files.Persist;
import src.Files.DatConLog;
import src.apps.DatCon;

public class TimeAxisPanel extends JPanel
        implements ActionListener, PropertyChangeListener, IDatConPanel {

    private static final long serialVersionUID = 1L;

    OffsetPanel offsetPanel = null;

    JRadioButton startRecording = new JRadioButton("Recording Start");

    JRadioButton motorStart = new JRadioButton("Motor Start");

    JRadioButton gpsLock = new JRadioButton("GPS Lock");

    JRadioButton motorStop = new JRadioButton("Motor Stop");

    JRadioButton stopRecording = new JRadioButton("Recording Stop");

    ButtonGroup lowerGroup = new ButtonGroup();

    ButtonGroup upperGroup = new ButtonGroup();

    public long tickLower = 0;

    public long tickUpper = 0;

    long offset = 0;

    public long highestTickNo = -1;

    public long lowestTickNo = -1;

    public long motorStartTick = 0;

    public long motorStopTick = -1;

    public long flightStartTick = -1;

    public long gpsLockTick = -1;

    private JFormattedTextField lowerTimeField = null;

    private JFormattedTextField upperTimeField = null;

    private JFormattedTextField lowerTickField = null;

    private JFormattedTextField upperTickField = null;

    DatCon datCon = null;

    DatFile datFile = null;

    public TimeAxisPanel(DatCon datCon) {
        this.datCon = datCon;
        lowerGroup.add(startRecording);
        lowerGroup.add(motorStart);
        lowerGroup.add(gpsLock);
        upperGroup.add(motorStop);
        upperGroup.add(stopRecording);

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 3;
        JLabel label = new JLabel("Time Axis");
        Font font = new Font("Verdana", Font.BOLD, 16);
        label.setFont(font);
        add(label, gbc);
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        offsetPanel = new OffsetPanel(this);
        add(offsetPanel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 2;
        gbc.gridy = 2;
        JLabel lowerLabel = new JLabel("Lower");
        add(lowerLabel, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        JLabel upperLabel = new JLabel("Upper");
        add(upperLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Time"), gbc);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 2;
        gbc.gridy = 3;
        lowerTimeField = new JFormattedTextField(Formatters.DoubleFormatter());
        lowerTimeField.setColumns(10);
        lowerTimeField.addPropertyChangeListener(this);
        add(lowerTimeField, gbc);

        gbc.gridx = 4;
        gbc.gridy = 3;
        upperTimeField = new JFormattedTextField(Formatters.DoubleFormatter());
        upperTimeField.setColumns(10);
        upperTimeField.addPropertyChangeListener(this);
        add(upperTimeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("TickNo"), gbc);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 2;
        gbc.gridy = 4;
        lowerTickField = new JFormattedTextField(Formatters.LongFormatter());
        lowerTickField.setColumns(10);
        lowerTickField.addPropertyChangeListener(this);
        add(lowerTickField, gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        upperTickField = new JFormattedTextField(Formatters.LongFormatter());
        upperTickField.setColumns(10);
        upperTickField.addPropertyChangeListener(this);
        add(upperTickField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        add(startRecording, gbc);
        startRecording.addActionListener(this);

        gbc.gridx = 4;
        gbc.gridy = 5;
        add(motorStop, gbc);
        motorStop.addActionListener(this);

        gbc.gridx = 2;
        gbc.gridy = 6;
        add(motorStart, gbc);
        motorStart.addActionListener(this);

        gbc.gridx = 4;
        gbc.gridy = 6;
        add(stopRecording, gbc);
        stopRecording.addActionListener(this);

        gbc.gridx = 2;
        gbc.gridy = 7;
        add(gpsLock, gbc);
        gpsLock.addActionListener(this);

        reset();
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        convertDat.tickRangeLower = tickLower;
        convertDat.tickRangeUpper = tickUpper;
        convertDat.timeOffset = offset;
    }

    public void reset() {
        lowerGroup.clearSelection();
        upperGroup.clearSelection();

        startRecording.setEnabled(false);
        startRecording.setSelected(false);
        motorStart.setEnabled(false);
        motorStart.setSelected(false);
        stopRecording.setEnabled(false);
        motorStop.setEnabled(false);
        gpsLock.setEnabled(false);
        offsetPanel.reset();
    }

    public void initFromDatFile(DatFile datFile) {
        this.datFile = datFile;
        lowestTickNo = datFile.lowestTickNo;
        highestTickNo = datFile.highestTickNo;
        motorStartTick = datFile.firstMotorStartTick;
        motorStopTick = datFile.lastMotorStopTick;
        flightStartTick = datFile.flightStartTick;
        gpsLockTick = datFile.gpsLockTick;
        offsetPanel.setFromDatFile(datFile);
        startRecording.setEnabled(true);
        setLower(lowestTickNo);
        if (highestTickNo != -1) {
            stopRecording.setEnabled(true);
            setUpper(highestTickNo);
            stopRecording.setSelected(true);
        }
        if (motorStartTick != 0) {
            motorStart.setEnabled(true);
            if (Persist.smartTimeAxis) {
                setLower(motorStartTick);
            }
        }
        if (gpsLockTick != -1) {
            gpsLock.setEnabled(true);
            if (Persist.smartTimeAxis) {
                setLower(gpsLockTick);
            }
        }
        if (motorStopTick != -1) {
            motorStop.setEnabled(true);
            if (Persist.smartTimeAxis) {
                motorStop.setSelected(true);
                setUpper(motorStopTick);
            }
        }
        datCon.checkState();
    }

    public void setLower(long lower) {
        long ltick = tickLower;
        boolean setIt = false;
        if (lower < lowestTickNo) {
            this.tickLower = lowestTickNo;
        } else {
            this.tickLower = lower;
        }
        lowerTickField.setText(("" + tickLower));
        lowerTimeField.setText(datFile.timeString(tickLower, offset));
        if (tickLower == lowestTickNo) {
            startRecording.setSelected(true);
            setIt = true;
        }
        if (tickLower == motorStartTick) {
            motorStart.setSelected(true);
            setIt = true;
        }
        if (tickLower == gpsLockTick) {
            gpsLock.setSelected(true);
            setIt = true;
        }
        if (!setIt) {
            lowerGroup.clearSelection();
        }
        if (ltick != tickLower) {
            datCon.dontViewIt();
        }
    }

    public void setUpper(long upper) {
        long utik = tickUpper;
        if (upper > highestTickNo) {
            this.tickUpper = highestTickNo;
        } else {
            this.tickUpper = upper;
        }
        upperTickField.setText(("" + tickUpper));
        upperTimeField.setText(datFile.timeString(tickUpper, offset));
        if (tickUpper == motorStopTick) {
            motorStop.setSelected(true);
        } else if (tickUpper == highestTickNo) {
            stopRecording.setSelected(true);
        } else {
            upperGroup.clearSelection();
        }
        if (utik != tickUpper) {
            datCon.dontViewIt();
        }
    }

    public void makeitDashware() {
        if (tickLower < gpsLockTick) {
            setLower(gpsLockTick);
        }
    }

    public void setUpperLower() {
        setLower(tickLower);
        setUpper(tickUpper);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == startRecording) {
                setLower(lowestTickNo);
            } else if (source == motorStart) {
                setLower(motorStartTick);
            } else if (source == gpsLock) {
                setLower(gpsLockTick);
            } else if (source == motorStop) {
                setUpper(motorStopTick);
            } else if (source == stopRecording) {
                setUpper(highestTickNo);
            } else if (source == offsetPanel.recordingStart) {
                offset = lowestTickNo;
                ;
            } else if (source == offsetPanel.motorStart) {
                offset = motorStartTick;
            } else if (source == offsetPanel.flightStart) {
                offset = flightStartTick;
            }
            setUpperLower();
            datCon.checkState();
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
            JComponent source = (JComponent) (evt.getSource());
            Object newValue = evt.getNewValue();
            if (newValue instanceof Number) {
                Number value = (Number) newValue;
                if (source == lowerTimeField) {
                    long lowerTick = datFile.getTickFromTime(value, offset);
                    setLower(lowerTick);
                }
                if (source == lowerTickField) {
                    long lowerTick = (Long) value;
                    setLower(lowerTick);
                }
                if (source == upperTimeField) {
                    long upperTick = datFile.getTickFromTime(value, offset);
                    setUpper(upperTick);
                }
                if (source == upperTickField) {
                    long upperTick = (Long) value;
                    setUpper(upperTick);
                }
                datCon.checkState();
            }
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
        // never called
    }

    @Override
    public void closePrintStreams() {
        // never called
    }

    public void setFromMarkers(DatFile datFile) {
        highestTickNo = datFile.highestTickNo;
        motorStartTick = datFile.firstMotorStartTick;
        if (datFile.lastMotorStopTick != -1) {
            highestTickNo = datFile.lastMotorStopTick;
        } else {
            highestTickNo = datFile.highestTickNo;
        }
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        // never called
    }

}
