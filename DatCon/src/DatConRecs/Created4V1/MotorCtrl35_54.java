package src.DatConRecs.Created4V1;

import src.DatConRecs.MotorControl;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;

public class MotorCtrl35_54 extends MotorControl {
  
    public MotorCtrl35_54(ConvertDat convertDat) {
        super(convertDat, 54, 35);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            pwm1 = ((float) _payload.getUnsignedShort(19)) / 100.0f;
            pwm2 = ((float) _payload.getUnsignedShort(21)) / 100.0f;
            pwm3 = ((float) _payload.getUnsignedShort(23)) / 100.0f;
            pwm4 = ((float) _payload.getUnsignedShort(25)) / 100.0f;
            pwm5 = ((float) _payload.getUnsignedShort(27)) / 100.0f;
            pwm6 = ((float) _payload.getUnsignedShort(29)) / 100.0f;
            pwm7 = ((float) _payload.getUnsignedShort(31)) / 100.0f;
            pwm8 = ((float) _payload.getUnsignedShort(33)) / 100.0f;
        } catch (Exception e) {
            RecordException(e);
        }
    }
    
}
