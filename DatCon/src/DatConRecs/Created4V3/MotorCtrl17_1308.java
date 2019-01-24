package src.DatConRecs.Created4V3;

import src.DatConRecs.MotorControl;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class MotorCtrl17_1308 extends MotorControl {

    public MotorCtrl17_1308(ConvertDat convertDat) {
        super(convertDat, 1308, 17);
        status = true;
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            motor_status = _payload.getUnsignedByte(0);
            pwm1 = ((float) _payload.getUnsignedShort(1)) / 100.0f;
            pwm2 = ((float) _payload.getUnsignedShort(3)) / 100.0f;
            pwm3 = ((float) _payload.getUnsignedShort(5)) / 100.0f;
            pwm4 = ((float) _payload.getUnsignedShort(7)) / 100.0f;
            pwm5 = ((float) _payload.getUnsignedShort(9)) / 100.0f;
            pwm6 = ((float) _payload.getUnsignedShort(11)) / 100.0f;
            pwm7 = ((float) _payload.getUnsignedShort(13)) / 100.0f;
            pwm8 = ((float) _payload.getUnsignedShort(15)) / 100.0f;
        } catch (Exception e) {
            RecordException(e);
        }
    }

}
