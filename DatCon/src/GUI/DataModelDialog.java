package src.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.awt.event.WindowAdapter;

import javax.swing.*;

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
                Desktop.getDesktop().browse(new URI(
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
