package src.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import src.Files.DatConLog;
import src.Files.Persist;

@SuppressWarnings("serial")
public class ParsingMode extends JMenu implements ActionListener {
    JRadioButtonMenuItem justDat = new JRadioButtonMenuItem("DatDefined Only");

    JRadioButtonMenuItem justEngineered = new JRadioButtonMenuItem(
            "Engineered Only");

    JRadioButtonMenuItem datThenEngineered = new JRadioButtonMenuItem(
            "DatDefined then Engineered");

    JRadioButtonMenuItem engineeredThenDat = new JRadioButtonMenuItem(
            "Engineered then DatDefined");

    JRadioButtonMenuItem invalidStructOK = new JRadioButtonMenuItem(
            "Invalid Structure OK");

    public ParsingMode(String label) {
        setText(label);
        ButtonGroup group = new ButtonGroup();
        group.add(justDat);
        group.add(justEngineered);
        group.add(datThenEngineered);
        group.add(engineeredThenDat);

        add(justEngineered);
        add(justDat);
        add(engineeredThenDat);
        add(datThenEngineered);
        add(new JSeparator());
        add(invalidStructOK);

        justDat.addActionListener(this);
        justEngineered.addActionListener(this);
        datThenEngineered.addActionListener(this);
        engineeredThenDat.addActionListener(this);
        invalidStructOK.addActionListener(this);

        invalidStructOK.setSelected(Persist.invalidStructOK);

        switch (Persist.parsingMode) {
        case DAT_THEN_ENGINEERED:
            datThenEngineered.setSelected(true);
            break;
        case ENGINEERED_THEN_DAT:
            engineeredThenDat.setSelected(true);
            break;
        case JUST_DAT:
            justDat.setSelected(true);
            break;
        case JUST_ENGINEERED:
            justEngineered.setSelected(true);
            break;
        default:
            break;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == justDat) {
                Persist.parsingMode = Persist.ParsingMode.JUST_DAT;
            } else if (source == justEngineered) {
                Persist.parsingMode = Persist.ParsingMode.JUST_ENGINEERED;
            } else if (source == datThenEngineered) {
                Persist.parsingMode = Persist.ParsingMode.DAT_THEN_ENGINEERED;
            } else if (source == engineeredThenDat) {
                Persist.parsingMode = Persist.ParsingMode.ENGINEERED_THEN_DAT;
            } else if (source == invalidStructOK) {
                Persist.invalidStructOK = invalidStructOK.isSelected();
            }
            Persist.save();
        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }
}
