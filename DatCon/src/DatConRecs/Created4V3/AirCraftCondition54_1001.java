package src.DatConRecs.Created4V3;

import src.DatConRecs.AirCraftCondition;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class AirCraftCondition54_1001 extends AirCraftCondition {

    protected short launch_state = (short) 0;

    protected float launch_acc_dur = (float) 0;

    protected float launch_delta_v = (float) 0;

    protected float thrust = (float) 0;

    protected float gyro = (float) 0;

    protected float gyro_acc = (float) 0;

    protected float land_dur = (float) 0;

    protected float thrust_proj_gnd = (float) 0;

    protected float thrust_proj_gnd_compen = (float) 0;

    protected float thrust_compensator = (float) 0;

    protected float hover_thrust = (float) 0;

    protected float cos_safe_tilt = (float) 0;

    protected float safe_tilt = (float) 0;

    public AirCraftCondition54_1001(ConvertDat convertDat) {
        super(convertDat, 1001, 54);
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
            landState = _payload.getUnsignedByte(13);
            thrust = _payload.getFloat(14);
            gyro = _payload.getFloat(18);
            gyro_acc = _payload.getFloat(22);
            land_dur = _payload.getFloat(26);
            thrust_proj_gnd = _payload.getFloat(30);
            thrust_proj_gnd_compen = _payload.getFloat(34);
            thrust_compensator = _payload.getFloat(38);
            hover_thrust = _payload.getFloat(42);
            cos_safe_tilt = _payload.getFloat(46);
            safe_tilt = _payload.getFloat(50);
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
            printCsvValue(thrust, aircraft_conditionFloatSig, "thrust", lineT,
                    valid);
            printCsvValue(gyro, aircraft_conditionFloatSig, "gyro", lineT,
                    valid);
            printCsvValue(gyro_acc, aircraft_conditionFloatSig, "gyro_acc",
                    lineT, valid);
            printCsvValue(land_dur, aircraft_conditionFloatSig, "land_dur",
                    lineT, valid);
            printCsvValue(thrust_proj_gnd, aircraft_conditionFloatSig,
                    "thrust_proj_gnd", lineT, valid);
            printCsvValue(thrust_proj_gnd_compen, aircraft_conditionFloatSig,
                    "thrust_proj_gnd_compen", lineT, valid);
            printCsvValue(thrust_compensator, aircraft_conditionFloatSig,
                    "thrust_compensator", lineT, valid);
            printCsvValue(hover_thrust, aircraft_conditionFloatSig,
                    "hover_thrust", lineT, valid);
            printCsvValue(cos_safe_tilt, aircraft_conditionFloatSig,
                    "cos_safe_tilt", lineT, valid);
            printCsvValue(safe_tilt, aircraft_conditionFloatSig, "safe_tilt",
                    lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
