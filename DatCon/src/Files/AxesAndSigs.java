package src.Files;

public class AxesAndSigs {

    public static Axis motorSpeedAxis = new Axis("motorSpeed", "Motor Speed",
            Units.rpm);

    public static Axis motorVoltsAxis = new Axis("motorVolts", "Motor Volts",
            Units.volts);

    public static Axis motorEscTempAxis = new Axis("motorESCTemp",
            "Motor ESC Temp", Units.degrees);

    public static Axis motorPWMAxis = new Axis("motorCommanded",
            "Motor Commanded", Units.percentage);

    public static Axis motorVoutAxis = new Axis("motorVout", "Motor Vout",
            Units.volts);

    public static Axis motorPPMrecvAxis = new Axis("motorPPMrecv",
            "Motor PPM recv");

    public static Axis motorPPMsendAxis = new Axis("motorPPMsend",
            "Motor PPM send");

    public static Axis motorCurrentAxis = new Axis("motorCurrent",
            "Motor Current");

    public static Axis motorWattsAxis = new Axis("motorWatts", "Motor Watts");

    public static Axis motorWattsSecsAxis = new Axis("motorWattsSecs",
            "Motor Watts Secs");

    public static Axis motorWattsSecsPerDistAxis = new Axis(
            "motorWattsSecs/Dist", "Motor Watts Secs / Dist");

    public static Axis motorWattsPerVelAxis = new Axis("motorWatts/Vel",
            "Motor Watts Per Vel");

    public static Axis motorStatusAxis = new Axis("motorStatus",
            "Motor Status");

    public static Axis cellVoltsAxis = new Axis("cellVolts", "Cell Volts",
            Units.volts);

    public static Axis gyroAxis = new Axis("gyro", "Gyro", Units.degreesPerSec);

    public static Axis accelAxis = new Axis("accel", "Accelerometer", Units.G);

    public static Axis controlAxis = new Axis("control", "Control",
            Units.controlStick);

    public static Signal motorSpeedSig = Signal.SeriesInt("Motor:Speed",
            "Motor Speed", motorSpeedAxis, Units.rpm);

    public static Signal motorVoltsSig = Signal.SeriesFloat("Motor:Volts",
            "motor Volts", motorVoltsAxis, Units.volts);

    public static Signal motorEscTempSig = Signal.SeriesInt("Motor:EscTemp",
            "Motor ESC Temp", motorEscTempAxis, Units.degrees);

    public static Signal motorStatusSig = Signal.SeriesInt("Motor:Status",
            "Motor Status", motorStatusAxis, Units.noUnits);

    public static Signal motorCtrlPWMSig = Signal.SeriesFloat("MotorCtrl:PWM",
            "Motor Commanded", motorPWMAxis, Units.percentage);

    public static Signal motorVoutSig = Signal.SeriesFloat("Motor:V_out",
            "Motor V out", motorVoutAxis, Units.volts);

    public static Signal motorPPMrecvSig = Signal.SeriesInt("Motor:PPMrecv",
            "Motor PPMrecv", motorPPMrecvAxis, Units.noUnits);

    public static Signal motorPPMsendSig = Signal.SeriesInt("Motor:PPMsend",
            "Motor PPM send", motorPPMsendAxis, Units.noUnits);

    public static Signal motorCurrentSig = Signal.SeriesFloat("Motor:Current",
            "Motor Load", motorCurrentAxis, Units.amps);

    public static Signal motorWattsSig = Signal.SeriesFloat("Motor:Watts",
            "Motor Load", motorWattsAxis, Units.watts);

    public static Signal thrustThetaSig = Signal.SeriesDouble(
            "Motor:thrustAngle", "Thrust angle computed from motor speeds",
            null, Units.degrees180);

    public static Signal battGoHome = Signal.SeriesInt("SMART_BATT:goHome%",
            "Smart Battery computed go home %", null, Units.percentage);

    public static Signal battLand = Signal.SeriesInt("SMART_BATT:land%",
            "Smart Battery computed land %", null, Units.percentage);

    public static Signal battGoHomeTime = Signal.SeriesInt(
            "SMART_BATT:goHomeTime", "Smart Battery computed go home time",
            null, Units.seconds);

    public static Signal battLandTime = Signal.SeriesInt("SMART_BATT:landTime",
            "Smart Battery computed land time", null, Units.seconds);

    public static Signal battPercent = Signal.SeriesInt("Battery:battery%",
            "Battery Percentage", null, Units.percentage);

    //    public static Signal rcSigLevel = Signal.SeriesInt("rcSigLevel",
    //            "Signal Level of RC", null, Units.percentage);

    public final static Signal currentSig = Signal
            .SeriesFloat("Battery:current", "Current", null, Units.amps);

    public final static Signal cellVoltSig = Signal.SeriesFloat(
            "Battery:cellVolts", "Cell Volts", AxesAndSigs.cellVoltsAxis,
            Units.volts);

    public final static Signal batteryTempSig = Signal
            .SeriesFloat("Battery:Temp", "Battery Temp", null, Units.degreesC);

    public final static Signal batteryFCC = Signal.SeriesFloat("Battery:FullCC",
            "Battery Full Charge Capacity", null, Units.mAh);;

    public final static Signal batteryRemCap = Signal.SeriesFloat(
            "Battery:RemCap", "Battery Remaining Cap", null, Units.mAh);;

    public final static Signal voltsSig = Signal.SeriesFloat("Battery:volts",
            "Volts", null, Units.volts);

    public final static Signal wattsSig = Signal.SeriesFloat("Battery:watts",
            "Watts", null, Units.watts);

    public final static Signal longitudeSig = Signal.SeriesDouble("Longitude",
            "Longitude", null, Units.degrees180);

    public final static Signal latitudeSig = Signal.SeriesDouble("Latitude",
            "Latitude", null, Units.degrees180);

    public final static Signal absoluteHeightSig = Signal.SeriesDouble(
            "absoluteHeight", "Height above Launch HomePoint", null,
            Units.meters);

    public final static Signal numSatsSig = Signal.SeriesFloat("numSats",
            "Number of Satellites", null, Units.noUnits);

    public final static Signal barometerSig = Signal.SeriesFloat("Barometer",
            "Barometer", null, Units.meters);

    public final static Signal accelSig = Signal.SeriesFloat("Accel",
            "Accelerometer", AxesAndSigs.accelAxis, Units.G);

    public final static Signal gyroSig = Signal.SeriesFloat("Gyro", "Gyroscope",
            AxesAndSigs.gyroAxis, Units.degreesPerSec);

    public final static Signal magSig = Signal.SeriesFloat("Mag",
            "Magnetometer", null, Units.aTesla);

    public final static Signal velocitySig = Signal.SeriesFloat("Vel",
            "Velocity", null, Units.metersPerSec);

    public final static Signal rollSig = Signal.SeriesDouble("Roll", "Roll",
            null, Units.degrees180);

    public final static Signal pitchSig = Signal.SeriesDouble("Pitch", "Pitch",
            null, Units.degrees180);

    public final static Signal yawSig = Signal.SeriesDouble("Yaw", "Yaw", null,
            Units.degrees180);

    public final static Signal yaw360Sig = Signal.SeriesDouble("Yaw(360)",
            "Yaw 360 degrees scale", null, Units.degrees360);

    public final static Signal totalGyroSig = Signal.SeriesDouble("totalGyro",
            "Integrate and sum gyro values", null, Units.degrees);

    public final static Signal magYawSig = Signal.SeriesDouble("magYaw",
            "Yaw computed from magnetometers", null, Units.degrees180);

    public final static Signal magYawDiffSig = Signal.SeriesDouble("Yaw-magYaw",
            "Yaw magYaw diff", null, Units.degrees180);

    public final static Signal magYawSigInterval = Signal.SeriesDouble(
            "magYawErrorBound", "Error Bound", null, Units.degrees180);

    public final static Signal directionOfTravelSig = Signal.SeriesDouble(
            "directionOfTravel", "Direction of Travel", null, Units.degrees180);

    public final static Signal distanceTravelledSig = Signal.SeriesDouble(
            "distanceTravelled", "Distance Travelled", null, Units.meters);

    public final static Signal distanceHPSig = Signal.SeriesDouble("distanceHP",
            "Distance From HP", null, Units.meters);

    public final static Signal imuTempSig = Signal.SeriesDouble("ImuTemp",
            "IMU Temp", null, Units.degreesC);

    public final static Signal quaternionSig = Signal.SeriesDoubleExperimental(
            "quat", "Quaternion", null, Units.noUnits);

    public static Signal throttleSig = Signal.SeriesInt("RC:Throttle",
            "Throttle", controlAxis, Units.controlStick);

    public static Signal rudderSig = Signal.SeriesInt("RC:Rudder", "Rudder",
            controlAxis, Units.controlStick);

    public static Signal elevatorSig = Signal.SeriesInt("RC:Elevator",
            "Elevator", controlAxis, Units.controlStick);

    public static Signal aileronSig = Signal.SeriesInt("RC:Aileron", "Aileron",
            controlAxis, Units.controlStick);

    public static Signal rthHeightSig = Signal.SeriesDouble("HP:rthHeight",
            "Return To Home Height", null, Units.meters);

    public final static Signal hpLongitudeSig = Signal
            .SeriesDouble("HP:Longitude", "Longitude", null, Units.degrees180);

    public final static Signal hpLatitudeSig = Signal
            .SeriesDouble("HP:Latitude", "Longitude", null, Units.degrees180);

}
