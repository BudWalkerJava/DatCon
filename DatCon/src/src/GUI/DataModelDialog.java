/*  CheckUpdates class

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
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.WindowAdapter;

import javax.swing.*;

import src.Files.ConvertDat;
import src.apps.DatCon;

public class DataModelDialog implements ActionListener {

	public DataModelDialog() {
	}

	JDialog dialog = null;

	// JButton okButton = new JButton("OK");

	JButton gotoButton = new JButton(
			"Goto https://java.com/en/download/windows-64bit.jsp");

	private void createDataModelDialog() {
		JFrame frame = new JFrame("DataModelDialogxx");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		dialog = new JDialog(frame, "64 bit Java Required", true);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		dialog.setLocation(100, 100);
		// Set size
		dialog.setSize(500, 150);
		dialog.getContentPane().setBackground(Color.ORANGE);

		// Set some layout
		BoxLayout bl = new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS);
		dialog.setLayout(bl);
		JTextArea label = new JTextArea(
				"64 bit Java is required but a 32 bit Java has been detected.\n"
						+ "Press this button for instructions to download and install 64 bit Java");
		label.setBackground(Color.ORANGE);
		Font font = new Font("Verdana", Font.BOLD, 12);
		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		dialog.add(label);

		gotoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		dialog.add(gotoButton);
		gotoButton.addActionListener(this);

		dialog.setVisible(true);
	}

	private ActionEvent lastEvent = null;

	public void actionPerformed(ActionEvent e) {
		if (lastEvent == e)
			return;
		lastEvent = e;
		try {
			JComponent source = (JComponent) (e.getSource());
			if (source == gotoButton) {
				dialog.dispose();
				Desktop.getDesktop()
						.browse(new URI(
								"https://java.com/en/download/windows-64bit.jsp"));
				System.exit(0);
			}
		} catch (Exception exception) {
		}
	}

	public static void createAndShowDataModelDialog() {
		DataModelDialog dmd = new DataModelDialog();
		dmd.createDataModelDialog();
	}
}
