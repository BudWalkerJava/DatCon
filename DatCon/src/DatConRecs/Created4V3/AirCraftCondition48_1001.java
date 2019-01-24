package src.DatConRecs.Created4V3;

import src.DatConRecs.AirCraftCondition;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class AirCraftCondition48_1001 extends AirCraftCondition {

    protected short launch_state = (short) 0;

    protected float launch_acc_dur = (float) 0;

    protected float launch_delta_v = (float) 0;

    protected float launch_free_fall_dur = (float) 0;

    protected float launch_free_fall_delta_v = (float) 0;

    protected short thrust = (short) 0;

    protected short gyro = (short) 0;

    protected float land_dur_press = (float) 0;

    protected float land_dur_sonic = (float) 0;

    protected short thrust_body = (short) 0;

    protected short thrust_gnd = (short) 0;

    protected short thrust_gnd_compen = (short) 0;

    protected short safe_tilt_raw = (short) 0;

    protected short safe_tilt = (short) 0;

    protected float sat_timer = (float) 0;

    public AirCraftCondition48_1001(ConvertDat convertDat) {
        super(convertDat, 1001, 48);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;
            intFlightState = _payload.getUnsignedByte(0);
            flightState = _payload.getUnsignedByte(1);
            lastFlightState = _payload.getUnsignedByte(2);
            nearGndState = _payload.getUnsignedByte(3);
            launch_state = _payload.getUnsignedByte(4);
            launch_acc_dur = _payload.getFloat(5);
            launch_delta_v = _payload.getFloat(9);
            launch_free_fall_dur = _payload.getFloat(13);
            launch_free_fall_delta_v = _payload.getFloat(17);
            landState = _payload.getUnsignedByte(21);
            thrust = _payload.getShort(22);
            gyro = _payload.getShort(24);
            land_dur_press = _payload.getFloat(26);
            land_dur_sonic = _payload.getFloat(30);
            thrust_body = _payload.getShort(34);
            thrust_gnd = _payload.getShort(36);
            thrust_gnd_compen = _payload.getShort(38);
            safe_tilt_raw = _payload.getShort(40);
            safe_tilt = _payload.getShort(42);
            sat_timer = _payload.getFloat(44);
            nearGrnd = (nearGndState != 0) ? "True" : "False";
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {
            super.printCols(lineT);
            printCsvValue(launch_state, aircraft_conditionIntSig,
                    "launch_state", lineT, valid);
            printCsvValue(launch_acc_dur, aircraft_conditionFloatSig,
                    "launch_acc_dur", lineT, valid);
            printCsvValue(launch_delta_v, aircraft_conditionFloatSig,
                    "launch_delta_v", lineT, valid);
            printCsvValue(launch_free_fall_dur, aircraft_conditionFloatSig,
                    "launch_free_fall_dur", lineT, valid);
            printCsvValue(launch_free_fall_delta_v, aircraft_conditionFloatSig,
                    "launch_free_fall_delta_v", lineT, valid);
            printCsvValue(thrust, aircraft_conditionIntSig, "thrust", lineT,
                    valid);
            printCsvValue(gyro, aircraft_conditionIntSig, "gyro", lineT, valid);
            printCsvValue(land_dur_press, aircraft_conditionFloatSig,
                    "land_dur_press", lineT, valid);
            printCsvValue(land_dur_sonic, aircraft_conditionFloatSig,
                    "land_dur_sonic", lineT, valid);
            printCsvValue(thrust_body, aircraft_conditionIntSig, "thrust_body",
                    lineT, valid);
            printCsvValue(thrust_gnd, aircraft_conditionIntSig, "thrust_gnd",
                    lineT, valid);
            printCsvValue(thrust_gnd_compen, aircraft_conditionIntSig,
                    "thrust_gnd_compen", lineT, valid);
            printCsvValue(safe_tilt_raw, aircraft_conditionIntSig,
                    "safe_tilt_raw", lineT, valid);
            printCsvValue(safe_tilt, aircraft_conditionIntSig, "safe_tilt",
                    lineT, valid);
            printCsvValue(sat_timer, aircraft_conditionFloatSig, "sat_timer",
                    lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
