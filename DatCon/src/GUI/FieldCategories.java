package src.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import src.Files.DatConLog;
import src.Files.Persist;

@SuppressWarnings("serial")
public class FieldCategories extends JMenu implements ActionListener {
    JRadioButtonMenuItem basicFields = new JRadioButtonMenuItem("Basic");

    JRadioButtonMenuItem experimentalFields = new JRadioButtonMenuItem(
            "Experimental");

    JRadioButtonMenuItem motorPowerFields = new JRadioButtonMenuItem(
            "MotorPower");

    JRadioButtonMenuItem magFields = new JRadioButtonMenuItem("Magnetometer");

    JRadioButtonMenuItem airCompFields = new JRadioButtonMenuItem("AirComp");

    JRadioButtonMenuItem inertialOnlyCalcsFields = new JRadioButtonMenuItem(
            "InetialOnlyCalcs");

    public FieldCategories(String label) {
        setText(label);
        add(basicFields);
        basicFields.setSelected(true);
        basicFields.setEnabled(true);
        basicFields.addActionListener(this);

        add(experimentalFields);
        experimentalFields.setSelected(Persist.EXPERIMENTAL_FIELDS);
        experimentalFields.addActionListener(this);

        add(motorPowerFields);
        motorPowerFields.setSelected(Persist.motorPowerCalcs);
        motorPowerFields.addActionListener(this);

        add(magFields);
        magFields.setSelected(Persist.magCalcs);
        magFields.addActionListener(this);

        add(airCompFields);
        airCompFields.setSelected(Persist.airComp);
        airCompFields.addActionListener(this);

        add(inertialOnlyCalcsFields);
        inertialOnlyCalcsFields.setSelected(Persist.airComp);
        inertialOnlyCalcsFields.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComponent source = (JComponent) (e.getSource());
            if (source == basicFields) {
                basicFields.setSelected(true);
            } else if (source == experimentalFields) {
                Persist.EXPERIMENTAL_FIELDS = experimentalFields.isSelected();
                Persist.save();
            } else if (source == motorPowerFields) {
                Persist.motorPowerCalcs = motorPowerFields.isSelected();
                Persist.save();
            } else if (source == magFields) {
                Persist.magCalcs = magFields.isSelected();
                Persist.save();
            } else if (source == airCompFields) {
                Persist.airComp = airCompFields.isSelected();
                Persist.save();
            } else if (source == inertialOnlyCalcsFields) {
                Persist.inertialOnlyCalcs = inertialOnlyCalcsFields
                        .isSelected();
                Persist.save();
            }

        } catch (Exception exception) {
            DatConLog.Exception(exception);
        }
    }
}
