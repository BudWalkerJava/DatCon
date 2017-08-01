package src.DatConRecs;

public class Units {

    public final static Units metersPerSec = new Units("meters/Sec");

    public static Units metersPerSec2 = new Units("meters/Sec2");

    public static final Units degreesC = new Units("degrees C");

    public static final Units noUnits = new Units("");

    public static Units meters = new Units("meters");

    public static Units msec = new Units("milliSeconds");

    public static Units gpsHealth = new Units("0,5");

    public static Units rpm = new Units("rpm");

    public static Units volts = new Units("volts");

    public static Units percentage = new Units("%");

    public static Units degrees = new Units("degrees");

    public static Units degrees180 = new Units("degrees [-180,180]");

    public static Units degrees360 = new Units("degrees [0,360]");

    public static Units amps = new Units("Amperes");

    public static Units watts = new Units("Watts");

    public static Units degreesPerSec = new Units("degrees/Sec");

    public static Units aTesla = new Units("aTesla");

    public static Units G = new Units("G");

    public static Units controlStick = new Units("-10000,+10000");

    public static Units seconds = new Units("Seconds");

    public static Units mAh = new Units("milliAmpHours");

    String name = "";

    private Units(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
