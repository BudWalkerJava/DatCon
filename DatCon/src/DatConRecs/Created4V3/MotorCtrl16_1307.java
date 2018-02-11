package src.DatConRecs.Created4V3;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.AxesAndSigs;

public class MotorCtrl16_1307 extends MotorControl {
    //    protected boolean valid = false;
    //
    //    protected int pwm1 = (int) 0;
    //
    //    protected int pwm2 = (int) 0;
    //
    //    protected int pwm3 = (int) 0;
    //
    //    protected int pwm4 = (int) 0;
    //
    //    protected int pwm5 = (int) 0;
    //
    //    protected int pwm6 = (int) 0;
    //
    //    protected int pwm7 = (int) 0;
    //
    //    protected int pwm8 = (int) 0;

    public MotorCtrl16_1307(ConvertDat convertDat) {
        super(convertDat, 1307, 16);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            //            pwm1 = _payload.getUnsignedShort(0);
            //            pwm2 = _payload.getUnsignedShort(2);
            //            pwm3 = _payload.getUnsignedShort(4);
            //            pwm4 = _payload.getUnsignedShort(6);
            //            pwm5 = _payload.getUnsignedShort(8);
            //            pwm6 = _payload.getUnsignedShort(10);
            //            pwm7 = _payload.getUnsignedShort(12);
            //            pwm8 = _payload.getUnsignedShort(14);
            pwm1 = ((float) _payload.getUnsignedShort(0)) / 100.0f;
            pwm2 = ((float) _payload.getUnsignedShort(2)) / 100.0f;
            pwm3 = ((float) _payload.getUnsignedShort(4)) / 100.0f;
            pwm4 = ((float) _payload.getUnsignedShort(6)) / 100.0f;
            pwm5 = ((float) _payload.getUnsignedShort(8)) / 100.0f;
            pwm6 = ((float) _payload.getUnsignedShort(10)) / 100.0f;
            pwm7 = ((float) _payload.getUnsignedShort(12)) / 100.0f;
            pwm8 = ((float) _payload.getUnsignedShort(14)) / 100.0f;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    //    protected static Signal ctrl_horiz_motor_debugIntSig = Signal
    //            .SeriesInt("ctrl_horiz_motor_debug", "", null, Units.noUnits);
    //
    //    protected static Signal ctrl_horiz_motor_debugFloatSig = Signal
    //            .SeriesFloat("ctrl_horiz_motor_debug", "", null, Units.noUnits);
    //
    //    protected static Signal ctrl_horiz_motor_debugDoubleSig = Signal
    //            .SeriesDouble("ctrl_horiz_motor_debug", "", null, Units.noUnits);
    //
    //    public void printCols(lineType lineT) {
    //        try {
    //
    //            printCsvValue(pwm1, AxesAndSigs.motorPWMSig, "RFront", lineT,
    //                    valid);
    //            printCsvValue(pwm2, AxesAndSigs.motorPWMSig, "pwm2", lineT, valid);
    //            printCsvValue(pwm3, AxesAndSigs.motorPWMSig, "pwm3", lineT, valid);
    //            printCsvValue(pwm4, AxesAndSigs.motorPWMSig, "pwm4", lineT, valid);
    //            printCsvValue(pwm5, AxesAndSigs.motorPWMSig, "pwm5", lineT, valid);
    //            printCsvValue(pwm6, AxesAndSigs.motorPWMSig, "pwm6", lineT, valid);
    //        } catch (Exception e) {
    //            DatConLog.Exception(e);
    //        }
    //    }

}
