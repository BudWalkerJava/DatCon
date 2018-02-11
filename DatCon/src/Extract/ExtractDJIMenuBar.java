/* DatConMenuBar class

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

package src.Extract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import src.apps.ExtractDJI;

public class ExtractDJIMenuBar extends JMenuBar implements ActionListener {
	JMenuItem fileOpenMenuItem = null;

	// JMenuItem userManualMenuItem = new JMenuItem("User Manual");

	JMenuItem reportBugMenuItem = new JMenuItem("Report a Bug");

	JMenuItem aboutMenuItem = new JMenuItem("About");

	JMenuItem checkUpdatesNow = new JMenuItem("Check for updates now");

	JRadioButtonMenuItem checkUpdatesOnStartup = new JRadioButtonMenuItem(
			"Check for updates on startup");

	JRadioButtonMenuItem showWhenNewVerAvail = new JRadioButtonMenuItem(
			"Show when newer version is available");

	// JRadioButtonMenuItem loadLastItem = new JRadioButtonMenuItem(
	// "Load last .DAT on Startup");

	private ExtractDJI extract = null;

	public ExtractDJIMenuBar(ExtractDJI datCon) {
		this.extract = datCon;
		this.extract.menuBar = this;
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");

		fileMenu.setMnemonic(KeyEvent.VK_A);
		this.add(fileMenu);

		fileMenu.add(checkUpdatesNow);
		checkUpdatesNow.addActionListener(this);

		fileMenu.add(checkUpdatesOnStartup);
		checkUpdatesOnStartup.setSelected(datCon.checkUpdts);
		checkUpdatesOnStartup.addActionListener(this);

		fileMenu.add(showWhenNewVerAvail);
		showWhenNewVerAvail.setSelected(datCon.showNewVerAvail);
		showWhenNewVerAvail.addActionListener(this);

		fileMenu.add(new JSeparator());

		// fileMenu.add(loadLastItem);
		// loadLastItem.setSelected(datCon.loadLastOnStartup);
		// loadLastItem.addActionListener(this);

		this.add(helpMenu);
		// helpMenu.add(userManualMenuItem);
		// userManualMenuItem.addActionListener(this);

		helpMenu.add(reportBugMenuItem);
		reportBugMenuItem.addActionListener(this);

		helpMenu.add(new JSeparator());
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(this);
	}

	// private void _userManual() {
	// try {
	// Desktop.getDesktop().open(new File("DatCon Usage.pdf"));
	// } catch (IOException e) {
	// extract.log.Exception(e);
	// }
	// }

	private void _bug() {
		JOptionPane.showMessageDialog(this, ""
				+ "Report a bug by sending it to" + "\nbug@flylog.info",
				"Report a Bug", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void _about() {
		JOptionPane.showMessageDialog(this, "ExtractDJI Version "
				+ ExtractDJI.version + "\nAll Rights Reserved",
				"About ExtractDJI", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JComponent source = (JComponent) (e.getSource());
			// if (source == userManualMenuItem) {
			// _userManual();
			// } else
			if (source == reportBugMenuItem) {
				_bug();
			} else if (source == aboutMenuItem) {
				_about();
			} else if (source == checkUpdatesOnStartup) {
				extract.checkUpdts = checkUpdatesOnStartup.isSelected();
				extract.persist.save();
			} else if (source == showWhenNewVerAvail) {
				extract.showNewVerAvail = showWhenNewVerAvail.isSelected();
				extract.persist.save();
			} else if (source == checkUpdatesNow) {
				extract.checkUpdates.checkForUpdates();
			}
			// else if (source == loadLastItem) {
			// extract.loadLastOnStartup = loadLastItem.isSelected();
			// extract.persist.save();
			// }
		} catch (Exception exception) {
			extract.log.Exception(exception);
		}
	}
}
