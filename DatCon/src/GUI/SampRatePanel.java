/* SampRatePanel class

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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Files.ConvertDat;
import src.Files.FileBeingUsed;
import src.Files.Persist;
import src.apps.DatCon;

public class SampRatePanel extends JPanel implements IDatConPanel {

    private static final long serialVersionUID = 1L;

    //private DatCon datCon;

    public JComboBox<Integer> sampRateChooser = null;

    Vector<Integer> sampRateVector = new Vector<Integer>();

    int sampRates[] = { 1, 2, 5, 10, 20, 30, 50, 60, 100, 200, Integer.MAX_VALUE };

    public SampRatePanel() {
    }

    public SampRatePanel(DatCon datCon) {
        super();
        //this.datCon = datCon;
        for (int i = 0; i < sampRates.length; i++) {
            sampRateVector.add(new Integer(sampRates[i]));
        }
        //sampRateVector.add(Integer.MAX_VALUE);
        //        sampRateVector.add("MAX");
        sampRateChooser = new JComboBox<Integer>(sampRateVector);
        setRate(Persist.csvSampleRate);
        setLayout(new GridBagLayout());
        setOpaque(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Sample Rate"));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(sampRateChooser, gbc);
        sampRateChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Persist.csvSampleRate = getRate();
                Persist.save();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(new JLabel("Hz"), gbc);
    }

    public void setRate(int rate) {
        sampRateChooser.setSelectedItem(new Integer(rate));
        Persist.save();
    }

    public int getRate() {
        Object chosen = sampRateChooser.getSelectedItem();
        if (chosen instanceof Integer)
            return ((Integer) chosen).intValue();
        return Integer.MAX_VALUE;
    }

    @Override
    public void setArgs(ConvertDat convertDat) {
        convertDat.sampleRate = getRate();
    }

    @Override
    public void createPrintStreams(String outDirName)
            throws FileBeingUsed, FileNotFoundException {
        // Never called        
    }

    @Override
    public void closePrintStreams() {
        // Never called        
    }

    @Override
    public void createFileNames(String flyFileNameRoot) {
        // Never called                
    }
}
