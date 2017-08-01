package src.DatConRecs;

public class AxesAndSigs {

    public static Axis motorSpeedAxis = new Axis("motorSpeed", "Motor Speed",
            Units.rpm);

    public static Axis motorVoltsAxis = new Axis("motorVolts", "Motor Volts",
            Units.volts);

    public static Axis motorEscTempAxis = new Axis("motorESCTemp",
            "Motor ESC Temp", Units.degrees);

    public static Axis motorPWMAxis = new Axis("motorCommanded",
            "Motor Commanded", Units.percentage);

    public static Axis motorPPMAxis = new Axis("motorPPM", "Motor PPM");

    public static Axis motorCurrentAxis = new Axis("motorCurrent",
            "Motor Current");

    public static Axis cellVoltsAxis = new Axis("cellVolts", "Cell Volts",
            Units.volts);

    public static Signal motorSpeedSig = Signal.SeriesInt("Motor:Speed",
            "Motor Speed", motorSpeedAxis, Units.rpm);

    public static Signal motorVoltsSig = Signal.SeriesFloat("Motor:Volts",
            "motor Volts", motorVoltsAxis, Units.volts);

    public static Signal motorEscTempSig = Signal.SeriesInt("Motor:EscTemp",
            "Motor ESC Temp", motorEscTempAxis, Units.degrees);

    public static Signal motorStatusSig = Signal.SeriesInt("Motor:Status",
            "Motor Status", null, Units.noUnits);

    public static Signal motorPWMSig = Signal.SeriesFloat("Motor:PWM",
            "Motor Commanded", motorPWMAxis, Units.percentage);

    public static Signal motorPPMSig = Signal.SeriesInt("Motor:PPM",
            "Motor PPM", motorPPMAxis, Units.noUnits);

    public static Signal motorCurrentSig = Signal.SeriesInt("Motor:Current",
            "Motor Load", motorCurrentAxis, Units.noUnits);

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
            "Longitude", null, Units.degrees180);

}
