/* DashwarePanel class

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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import src.Files.DatConLog;
import src.apps.DatCon;

public class DashwarePanelX extends JPanel implements ActionListener {
    DatCon datCon = null;

    JTextField status = new JTextField(15);

    JButton makeItDashware;

    public DashwarePanelX(DatCon datCon) {
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
        JLabel label = new JLabel("Dashware");
        label.setFont(font);
        add(label, gbc);
        gbc.weightx = 0.5;
        ;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        status.setFont(font);
        add(status, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        makeItDashware = new JButton("Make It Dashware Compatible");
        makeItDashware.addActionListener(this);
        add(makeItDashware, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == makeItDashware) {
                datCon.timeAxisPanel.makeitDashware();
            }
            datCon.checkState();
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }

    public void reset() {
        if (datCon.datFile.gpsLockTick != -1) {
            makeItDashware.setEnabled(true);
        } else {
            makeItDashware.setEnabled(false);
        }
    }

    public void enableDashware(boolean enable) {
        if (enable) {
            status.setText("Dashware compatible");
            status.setBackground(Color.GREEN);
        } else {
            status.setText("Not Dashware compatible");
            status.setBackground(Color.RED);
        }
    }

}
