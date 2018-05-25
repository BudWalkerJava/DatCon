package src.DatConRecs.Created4V1;

import src.DatConRecs.AirCraftCondition;
import src.DatConRecs.Payload;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;

public class AirCraftCondition39_70 extends AirCraftCondition {
    //    protected boolean valid = false;

    protected short craft_flight_mode = (short) 0;

    //protected short near_gnd_state = (short) 0;

    protected float launch_acc_duration = (float) 0;

    protected float launch_delta_v = (float) 0;

    protected short launch_state = (short) 0;

    protected float thrust_proj_gnd = (float) 0;

    protected float thrust_proj_gnd_compen = (float) 0;

    protected float thrust_compensator = (float) 0;

    protected float hover_thrust = (float) 0;

    protected float dynamic_thrust = (float) 0;

    protected float cos_safe_tilt = (float) 0;

    protected float safe_tilt = (float) 0;

    public AirCraftCondition39_70(ConvertDat convertDat) {
        super(convertDat, 70, 39);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        try {
            valid = true;

            craft_flight_mode = _payload.getUnsignedByte(0);
            nearGndState = _payload.getUnsignedByte(1);
            launch_acc_duration = _payload.getFloat(2);
            launch_delta_v = _payload.getFloat(6);
            launch_state = _payload.getUnsignedByte(10);
            thrust_proj_gnd = _payload.getFloat(11);
            thrust_proj_gnd_compen = _payload.getFloat(15);
            thrust_compensator = _payload.getFloat(19);
            hover_thrust = _payload.getFloat(23);
            dynamic_thrust = _payload.getFloat(27);
            cos_safe_tilt = _payload.getFloat(31);
            safe_tilt = _payload.getFloat(35);
            nearGrnd = (nearGndState != 0) ? "True" : "False";
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {

            printCsvValue(craft_flight_mode, aircraft_conditionIntSig,
                    "craft_flight_mode", lineT, valid);
            printCsvValue(nearGrnd, nearGndSig, "", lineT, valid);
            printCsvValue(launch_acc_duration, aircraft_conditionFloatSig,
                    "launch_acc_duration", lineT, valid);
            printCsvValue(launch_delta_v, aircraft_conditionFloatSig,
                    "launch_delta_v", lineT, valid);
            printCsvValue(launch_state, aircraft_conditionIntSig,
                    "launch_state", lineT, valid);
            printCsvValue(thrust_proj_gnd, aircraft_conditionFloatSig,
                    "thrust_proj_gnd", lineT, valid);
            printCsvValue(thrust_proj_gnd_compen, aircraft_conditionFloatSig,
                    "thrust_proj_gnd_compen", lineT, valid);
            printCsvValue(thrust_compensator, aircraft_conditionFloatSig,
                    "thrust_compensator", lineT, valid);
            printCsvValue(hover_thrust, aircraft_conditionFloatSig,
                    "hover_thrust", lineT, valid);
            printCsvValue(dynamic_thrust, aircraft_conditionFloatSig,
                    "dynamic_thrust", lineT, valid);
            printCsvValue(cos_safe_tilt, aircraft_conditionFloatSig,
                    "cos_safe_tilt", lineT, valid);
            printCsvValue(safe_tilt, aircraft_conditionFloatSig, "safe_tilt",
                    lineT, valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
