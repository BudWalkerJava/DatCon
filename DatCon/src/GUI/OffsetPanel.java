/* OffsetPanel class

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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import src.Files.DatFile;
import src.Files.Persist;
import src.Files.DatConLog;

public class OffsetPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    JRadioButton recordingStart = new JRadioButton("Recording Start");

    JRadioButton motorStart = new JRadioButton("Motor Start");

    JRadioButton flightStart = new JRadioButton("Flight Start");

    ButtonGroup buttonGroup = new ButtonGroup();

    private TimeAxisPanel tap;

    public OffsetPanel(TimeAxisPanel tap) {
        this.tap = tap;

        buttonGroup.add(recordingStart);
        buttonGroup.add(motorStart);
        buttonGroup.add(flightStart);

        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 1, true));
        setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Offset - time axis 0 point "), gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(recordingStart, gbc);
        recordingStart.addActionListener(tap);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(motorStart, gbc);
        motorStart.addActionListener(tap);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(flightStart, gbc);
        flightStart.addActionListener(tap);
        reset();
    }

    public void reset() {
        tap.offset = 0;
        recordingStart.setEnabled(false);
        motorStart.setEnabled(false);
        flightStart.setEnabled(false);
    }

    private long motorStartTick = -1;

    private long flightStartTick = -1;

    public void setFromDatFile(DatFile datFile) {
        this.motorStartTick = datFile.firstMotorStartTick;
        this.flightStartTick = datFile.flightStartTick;
        recordingStart.setEnabled(true);
        recordingStart.setSelected(true);
        tap.offset = 0;
        if (motorStartTick != 0) {
            motorStart.setEnabled(true);
            if (Persist.smartTimeAxis) {
                motorStart.setSelected(true);
                tap.offset = datFile.firstMotorStartTick;
            }
        }
        if (flightStartTick != -1) {
            flightStart.setEnabled(true);
            if (Persist.smartTimeAxis) {
                flightStart.setSelected(true);
                tap.offset = datFile.flightStartTick;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == recordingStart) {
                tap.offset = 0;
            } else if (source == motorStart) {
                tap.offset = motorStartTick;
            } else if (source == flightStart) {
                tap.offset = flightStartTick;
            }
            tap.setUpperLower();
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }
}
