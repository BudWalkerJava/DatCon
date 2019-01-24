package src.DatConRecs;

import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class MotorControl extends Record {

    protected float pwm1 = (float) 0;

    protected float pwm2 = (float) 0;

    protected float pwm3 = (float) 0;

    protected float pwm4 = (float) 0;

    protected float pwm5 = (float) 0;

    protected float pwm6 = (float) 0;

    protected float pwm7 = (float) 0;

    protected float pwm8 = (float) 0;

    protected boolean valid = false;

    protected short motor_status = (short) 0;

    protected boolean status = false;

    public MotorControl(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
    }

    public void process(Payload _payload) {
        super.process(_payload);
    }

    public static Signal motorCtrlStatusSig = Signal.SeriesFloat(
            "MotorCtrl:Status", "Motor Ctrl Status", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {
            if (status) {
                printCsvValue(motor_status, motorCtrlStatusSig, "", lineT,
                        valid);
            }
            if (convertDat.getNumMotors() == 6) {
                printCsvValue(pwm1, AxesAndSigs.motorCtrlPWMSig, "RFront",
                        lineT, valid);
                printCsvValue(pwm2, AxesAndSigs.motorCtrlPWMSig, "LFront",
                        lineT, valid);
                printCsvValue(pwm3, AxesAndSigs.motorCtrlPWMSig, "LSide", lineT,
                        valid);
                printCsvValue(pwm4, AxesAndSigs.motorCtrlPWMSig, "LBack", lineT,
                        valid);
                printCsvValue(pwm5, AxesAndSigs.motorCtrlPWMSig, "RBack", lineT,
                        valid);
                printCsvValue(pwm6, AxesAndSigs.motorCtrlPWMSig, "RSide", lineT,
                        valid);
            } else {
                printCsvValue(pwm1, AxesAndSigs.motorCtrlPWMSig, "RFront",
                        lineT, valid);
                printCsvValue(pwm2, AxesAndSigs.motorCtrlPWMSig, "LFront",
                        lineT, valid);
                printCsvValue(pwm3, AxesAndSigs.motorCtrlPWMSig, "LBack", lineT,
                        valid);
                printCsvValue(pwm4, AxesAndSigs.motorCtrlPWMSig, "RBack", lineT,
                        valid);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
