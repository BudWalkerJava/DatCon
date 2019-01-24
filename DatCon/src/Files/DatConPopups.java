package src.Files;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DatConPopups {

    public static boolean moreThanOne(JFrame frame) {
        Object paneBG = UIManager.get("OptionPane.background");
        Object msgFG = UIManager.get("OptionPane.messageForeground");
        UIManager.put("OptionPane.background", Color.ORANGE);
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        Object[] options = {
                "Yes, continue with just the first Flight Control entry",
                "No" };
        int n = JOptionPane.showOptionDialog(frame,
                "There is more than one Flight Control entry in this file.\n"
                        + "To extract the other Flight Control entries you'll need to first use ExtractDJI.\n"
                        + "Would you like to continue with the first Flight Control entry?",
                "There is more than one Flight Control entry in this file",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
        UIManager.put("OptionPane.background", paneBG);
        UIManager.put("OptionPane.messageForeground", msgFG);
        if (n == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void none(JFrame frame) {
        Object paneBG = UIManager.get("OptionPane.background");
        Object msgFG = UIManager.get("OptionPane.messageForeground");
        UIManager.put("OptionPane.background", Color.ORANGE);
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        // Object[] options = { "Yes, continue with just the first .DAT", "No"
        // };
        JOptionPane.showMessageDialog(frame,
                "There are no Flight Control entries in this file.\n"
                        + "When DJI Assistant 2 is run a Flight Control entry must be selected\n",
                "There are no Flight Control entries in this file.",
                JOptionPane.PLAIN_MESSAGE);
        // JOptionPane.INFORMATION_MESSAGE, null, options,
        // options[0]

        UIManager.put("OptionPane.background", paneBG);
        UIManager.put("OptionPane.messageForeground", msgFG);
    }

    public static void noACType(JFrame frame) {
        Object paneBG = UIManager.get("OptionPane.background");
        Object msgFG = UIManager.get("OptionPane.messageForeground");
        UIManager.put("OptionPane.background", Color.ORANGE);
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        JOptionPane.showMessageDialog(frame, "Unable to determine AC type.\n",
                "Unable to determine AC type.", JOptionPane.PLAIN_MESSAGE);
        UIManager.put("OptionPane.background", paneBG);
        UIManager.put("OptionPane.messageForeground", msgFG);
    }

    public static void noLogFile() {
        JFrame frame = new JFrame("No DatCon Log File");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object paneBG = UIManager.get("OptionPane.background");
        Object msgFG = UIManager.get("OptionPane.messageForeground");
        UIManager.put("OptionPane.background", Color.ORANGE);
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        JOptionPane.showMessageDialog(frame, "Can't create DatCon Log File.\n",
                "Can't create DatCon Log File..", JOptionPane.PLAIN_MESSAGE);
        UIManager.put("OptionPane.background", paneBG);
        UIManager.put("OptionPane.messageForeground", msgFG);
    }

}
