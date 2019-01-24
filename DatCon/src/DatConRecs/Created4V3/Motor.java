/* Motor class

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that redistribution of source code include
the following disclaimer in the documentation and/or other materials provided
with the distribution.

THIS SOFTWARE IS PROVIDED BY ITS CREATOR "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE CREATOR OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package src.DatConRecs.Created4V3;

import src.DatConRecs.Record;
import src.DatConRecs.RecIMU;
import src.Files.AxesAndSigs;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.Signal;
import src.Files.Units;

public class Motor extends Record {

    protected static int numSamples = 50;

    protected boolean valid = false;

    double thrustTheta = 0.0;

    protected boolean PPMsend = false;

    protected double voltsPerMotor[][] = null;

    protected double currentPerMotor[][] = null;

    protected double wattSecsPerDTPerMotor[][] = null;

    protected double wattsPerMotor[] = null;

    protected double wattsAvgPerMotor[] = null;

    protected double wattsPerMotorLast[] = null;

    protected double wattSecsPerMotor[] = null;

    protected double wattSecsPerMotorLast[] = null;

    protected double wattSecsPerTotalDistPerMotor[] = null;

    private int currentNumSamples = 0;

    private int sampleNum = 0;

    protected short rfPPM_recv = 0;

    protected short lfPPM_recv = 0;

    protected short lbPPM_recv = 0;

    protected short rbPPM_recv = 0;

    protected int rfPPM_send = 0;

    protected int lfPPM_send = 0;

    protected int lbPPM_send = 0;

    protected int rbPPM_send = 0;

    protected int lsPPM_send = 0;

    protected int rsPPM_send = 0;

    protected float rfVolts = 0;

    protected float lfVolts = 0;

    protected float lbVolts = 0;

    protected float rbVolts = 0;

    protected float rfV_out = 0;

    protected float lfV_out = 0;

    protected float lbV_out = 0;

    protected float rbV_out = 0;

    protected short rfSpeed = 0;

    protected short lfSpeed = 0;

    protected short lbSpeed = 0;

    protected short rbSpeed = 0;

    protected float rfCurrent = 0;

    protected float lfCurrent = 0;

    protected float lbCurrent = 0;

    protected float rbCurrent = 0;

    protected short rfTemp = 0;

    protected short lfTemp = 0;

    protected short lbTemp = 0;

    protected short rbTemp = 0;

    protected short rfStatus = 0;

    protected short lfStatus = 0;

    protected short lbStatus = 0;

    protected short rbStatus = 0;

    public short lsPPM_recv = 0;

    public short rsPPM_recv = 0;

    public float lsVolts = 0;

    public float rsVolts = 0;

    public float lsV_out = 0;

    public float rsV_out = 0;

    public short lsSpeed = 0;

    public short rsSpeed = 0;

    public float lsCurrent = 0;

    public float rsCurrent = 0;

    protected short lsTemp = 0;

    protected short rsTemp = 0;

    public short lsStatus = 0;

    public short rsStatus = 0;

    static int maxNumMotors = 6;

    public Motor(ConvertDat convertDat, int id, int length) {
        super(convertDat, id, length);
        try {
            voltsPerMotor = new double[maxNumMotors][numSamples];
            currentPerMotor = new double[maxNumMotors][numSamples];
            wattSecsPerDTPerMotor = new double[maxNumMotors][numSamples];
            wattsPerMotor = new double[maxNumMotors];
            wattsAvgPerMotor = new double[maxNumMotors];
            wattsPerMotorLast = new double[maxNumMotors];
            wattSecsPerMotor = new double[maxNumMotors];
            wattSecsPerMotorLast = new double[maxNumMotors];
            wattSecsPerTotalDistPerMotor = new double[maxNumMotors];
            for (int i = 0; i < maxNumMotors; i++) {
                wattSecsPerMotor[i] = 0.0;
                wattSecsPerMotorLast[i] = 0.0;
                wattSecsPerTotalDistPerMotor[i] = 0.0;
            }
            currentNumSamples = 0;
            sampleNum = 0;
        } catch (Exception e) {
            RecordException(e);
        }
    }

    public void printCols(lineType lineT) {
        try {
            if (convertDat.getNumMotors() == 4) {
                printCsvValue(rfSpeed, AxesAndSigs.motorSpeedSig, "RFront",
                        lineT, valid);
                printCsvValue(lfSpeed, AxesAndSigs.motorSpeedSig, "LFront",
                        lineT, valid);
                printCsvValue(lbSpeed, AxesAndSigs.motorSpeedSig, "LBack",
                        lineT, valid);
                printCsvValue(rbSpeed, AxesAndSigs.motorSpeedSig, "RBack",
                        lineT, valid);

                printCsvValue(rfTemp, AxesAndSigs.motorEscTempSig, "RFront",
                        lineT, valid);
                printCsvValue(lfTemp, AxesAndSigs.motorEscTempSig, "LFront",
                        lineT, valid);
                printCsvValue(lbTemp, AxesAndSigs.motorEscTempSig, "LBack",
                        lineT, valid);
                printCsvValue(rbTemp, AxesAndSigs.motorEscTempSig, "RBack",
                        lineT, valid);

                printCsvValue(rfPPM_recv, AxesAndSigs.motorPPMrecvSig, "RFront",
                        lineT, valid);
                printCsvValue(lfPPM_recv, AxesAndSigs.motorPPMrecvSig, "LFront",
                        lineT, valid);
                printCsvValue(lbPPM_recv, AxesAndSigs.motorPPMrecvSig, "LBack",
                        lineT, valid);
                printCsvValue(rbPPM_recv, AxesAndSigs.motorPPMrecvSig, "RBack",
                        lineT, valid);

                if (PPMsend) {
                    printCsvValue(rfPPM_send, AxesAndSigs.motorPPMsendSig,
                            "RFront", lineT, valid);
                    printCsvValue(lfPPM_send, AxesAndSigs.motorPPMsendSig,
                            "LFront", lineT, valid);
                    printCsvValue(lbPPM_send, AxesAndSigs.motorPPMsendSig,
                            "LBack", lineT, valid);
                    printCsvValue(rbPPM_send, AxesAndSigs.motorPPMsendSig,
                            "RBack", lineT, valid);
                }

                printCsvValue(rfV_out, AxesAndSigs.motorVoutSig, "RFront",
                        lineT, valid);
                printCsvValue(lfV_out, AxesAndSigs.motorVoutSig, "LFront",
                        lineT, valid);
                printCsvValue(lbV_out, AxesAndSigs.motorVoutSig, "LBack", lineT,
                        valid);
                printCsvValue(rbV_out, AxesAndSigs.motorVoutSig, "RBack", lineT,
                        valid);

                printCsvValue(rfVolts, AxesAndSigs.motorVoltsSig, "RFront",
                        lineT, valid);
                printCsvValue(lfVolts, AxesAndSigs.motorVoltsSig, "LFront",
                        lineT, valid);
                printCsvValue(lbVolts, AxesAndSigs.motorVoltsSig, "LBack",
                        lineT, valid);
                printCsvValue(rbVolts, AxesAndSigs.motorVoltsSig, "RBack",
                        lineT, valid);

                printCsvValue(rfCurrent, AxesAndSigs.motorCurrentSig, "RFront",
                        lineT, valid);
                printCsvValue(lfCurrent, AxesAndSigs.motorCurrentSig, "LFront",
                        lineT, valid);
                printCsvValue(lbCurrent, AxesAndSigs.motorCurrentSig, "LBack",
                        lineT, valid);
                printCsvValue(rbCurrent, AxesAndSigs.motorCurrentSig, "RBack",
                        lineT, valid);

                printCsvValue(rfStatus, AxesAndSigs.motorStatusSig, "RFront",
                        lineT, valid);
                printCsvValue(lfStatus, AxesAndSigs.motorStatusSig, "LFront",
                        lineT, valid);
                printCsvValue(lbStatus, AxesAndSigs.motorStatusSig, "LBack",
                        lineT, valid);
                printCsvValue(rbStatus, AxesAndSigs.motorStatusSig, "RBack",
                        lineT, valid);

                printCsvValue(thrustTheta, AxesAndSigs.thrustThetaSig, "",
                        lineT, valid);
                printPower(lineT, valid);
            } else if (convertDat.getNumMotors() == 6) {
                printCsvValue(rfStatus, AxesAndSigs.motorStatusSig, "RFront",
                        lineT, valid);
                printCsvValue(lfStatus, AxesAndSigs.motorStatusSig, "LFront",
                        lineT, valid);
                printCsvValue(lsStatus, AxesAndSigs.motorStatusSig, "LSide",
                        lineT, valid);
                printCsvValue(lbStatus, AxesAndSigs.motorStatusSig, "LBack",
                        lineT, valid);
                printCsvValue(rbStatus, AxesAndSigs.motorStatusSig, "RBack",
                        lineT, valid);
                printCsvValue(rsStatus, AxesAndSigs.motorStatusSig, "RSide",
                        lineT, valid);

                printCsvValue(rfSpeed, AxesAndSigs.motorSpeedSig, "RFront",
                        lineT, valid);
                printCsvValue(lfSpeed, AxesAndSigs.motorSpeedSig, "LFront",
                        lineT, valid);
                printCsvValue(lsSpeed, AxesAndSigs.motorSpeedSig, "LSide",
                        lineT, valid);
                printCsvValue(lbSpeed, AxesAndSigs.motorSpeedSig, "LBack",
                        lineT, valid);
                printCsvValue(rbSpeed, AxesAndSigs.motorSpeedSig, "RBack",
                        lineT, valid);
                printCsvValue(rsSpeed, AxesAndSigs.motorSpeedSig, "RSide",
                        lineT, valid);

                printCsvValue(rfVolts, AxesAndSigs.motorVoltsSig, "RFront",
                        lineT, valid);
                printCsvValue(lfVolts, AxesAndSigs.motorVoltsSig, "LFront",
                        lineT, valid);
                printCsvValue(lsVolts, AxesAndSigs.motorVoltsSig, "LSide",
                        lineT, valid);
                printCsvValue(lbVolts, AxesAndSigs.motorVoltsSig, "LBack",
                        lineT, valid);
                printCsvValue(rbVolts, AxesAndSigs.motorVoltsSig, "RBack",
                        lineT, valid);
                printCsvValue(rsVolts, AxesAndSigs.motorVoltsSig, "RSide",
                        lineT, valid);

                printCsvValue(rfTemp, AxesAndSigs.motorEscTempSig, "RFront",
                        lineT, valid);
                printCsvValue(lfTemp, AxesAndSigs.motorEscTempSig, "LFront",
                        lineT, valid);
                printCsvValue(lsTemp, AxesAndSigs.motorEscTempSig, "LSide",
                        lineT, valid);
                printCsvValue(lbTemp, AxesAndSigs.motorEscTempSig, "LBack",
                        lineT, valid);
                printCsvValue(rbTemp, AxesAndSigs.motorEscTempSig, "RBack",
                        lineT, valid);
                printCsvValue(rsTemp, AxesAndSigs.motorEscTempSig, "RSide",
                        lineT, valid);

                printCsvValue(rfPPM_recv, AxesAndSigs.motorPPMrecvSig, "RFront",
                        lineT, valid);
                printCsvValue(lfPPM_recv, AxesAndSigs.motorPPMrecvSig, "LFront",
                        lineT, valid);
                printCsvValue(lsPPM_recv, AxesAndSigs.motorPPMrecvSig, "LSide",
                        lineT, valid);
                printCsvValue(lbPPM_recv, AxesAndSigs.motorPPMrecvSig, "LBack",
                        lineT, valid);
                printCsvValue(rbPPM_recv, AxesAndSigs.motorPPMrecvSig, "RBack",
                        lineT, valid);
                printCsvValue(rsPPM_recv, AxesAndSigs.motorPPMrecvSig, "RSide",
                        lineT, valid);
                if (PPMsend) {
                    printCsvValue(rfPPM_send, AxesAndSigs.motorPPMsendSig,
                            "RFront", lineT, valid);
                    printCsvValue(lfPPM_send, AxesAndSigs.motorPPMsendSig,
                            "LFront", lineT, valid);
                    printCsvValue(lsPPM_send, AxesAndSigs.motorPPMsendSig,
                            "LSide", lineT, valid);
                    printCsvValue(lbPPM_send, AxesAndSigs.motorPPMsendSig,
                            "LBack", lineT, valid);
                    printCsvValue(rbPPM_send, AxesAndSigs.motorPPMsendSig,
                            "RBack", lineT, valid);
                    printCsvValue(rsPPM_send, AxesAndSigs.motorPPMsendSig,
                            "RSide", lineT, valid);
                }

                printCsvValue(rfV_out, AxesAndSigs.motorVoutSig, "RFront",
                        lineT, valid);
                printCsvValue(lfV_out, AxesAndSigs.motorVoutSig, "LFront",
                        lineT, valid);
                printCsvValue(lsV_out, AxesAndSigs.motorVoutSig, "LSide", lineT,
                        valid);
                printCsvValue(lbV_out, AxesAndSigs.motorVoutSig, "LBack", lineT,
                        valid);
                printCsvValue(rbV_out, AxesAndSigs.motorVoutSig, "RBack", lineT,
                        valid);
                printCsvValue(rsV_out, AxesAndSigs.motorVoutSig, "RSide", lineT,
                        valid);

                printCsvValue(rfCurrent, AxesAndSigs.motorCurrentSig, "RFront",
                        lineT, valid);
                printCsvValue(lfCurrent, AxesAndSigs.motorCurrentSig, "LFront",
                        lineT, valid);
                printCsvValue(lsCurrent, AxesAndSigs.motorCurrentSig, "LSide",
                        lineT, valid);
                printCsvValue(lbCurrent, AxesAndSigs.motorCurrentSig, "LBack",
                        lineT, valid);
                printCsvValue(rbCurrent, AxesAndSigs.motorCurrentSig, "RBack",
                        lineT, valid);
                printCsvValue(rsCurrent, AxesAndSigs.motorCurrentSig, "RSide",
                        lineT, valid);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    protected double computeThrustTheta(short lbSpeed, short rfSpeed,
            short rbSpeed, short lfSpeed) {
        double lbrfDiff = lbSpeed - rfSpeed;
        double rblfDiff = rbSpeed - lfSpeed;
        double thrust1 = Math.toDegrees(Math.atan2(lbrfDiff, rblfDiff));
        double thrust2 = (thrust1 + 315.0) % 360.0;
        double thrustTheta = thrust2;
        if (thrust2 > 180.0) {
            thrustTheta = thrust2 - 360.0;
        }
        return thrustTheta;
    }

    boolean firstTime = true;

    double lastTime = 0.0;

    double lastDistanceTravelled = 0.0;

    protected void recordPower() {
        if (Persist.motorPowerCalcs) {
            double currentTime = convertDat.getTime();
            double currentDistanceTravelled = RecIMU.current
                    .getDistanceTravelled();
            if (currentNumSamples++ >= numSamples)
                currentNumSamples = numSamples;
            if (sampleNum >= numSamples)
                sampleNum = 0;
            voltsPerMotor[0][sampleNum] = rfVolts;
            voltsPerMotor[1][sampleNum] = lfVolts;
            voltsPerMotor[2][sampleNum] = lbVolts;
            voltsPerMotor[3][sampleNum] = rbVolts;
            currentPerMotor[0][sampleNum] = rfCurrent;
            currentPerMotor[1][sampleNum] = lfCurrent;
            currentPerMotor[2][sampleNum] = lbCurrent;
            currentPerMotor[3][sampleNum] = rbCurrent;
            wattsPerMotor[0] = rfVolts * rfCurrent;
            wattsPerMotor[1] = lfVolts * lfCurrent;
            wattsPerMotor[2] = lbVolts * lbCurrent;
            wattsPerMotor[3] = rbVolts * rbCurrent;
            if (!firstTime) {
                double deltaTime = currentTime - lastTime;
                double deltaDT = currentDistanceTravelled
                        - lastDistanceTravelled;
                for (int motorNum = 0; motorNum < convertDat
                        .getNumMotors(); motorNum++) {
                    wattSecsPerMotor[motorNum] += (0.5
                            * (wattsPerMotor[motorNum]
                                    + wattsPerMotorLast[motorNum]))
                            * deltaTime;
                    wattsPerMotorLast[motorNum] = wattsPerMotor[motorNum];
                    double wattSPM = 0.0;
                    if (deltaDT == 0.0) {
                        wattSPM = Double.NaN;
                    } else {
                        wattSPM = ((wattSecsPerMotor[motorNum]
                                - wattSecsPerMotorLast[motorNum]) / deltaDT);
                        wattSecsPerMotorLast[motorNum] = wattSecsPerMotor[motorNum];
                    }
                    if (currentDistanceTravelled > 0.0) {
                        wattSecsPerTotalDistPerMotor[motorNum] = wattSecsPerMotor[motorNum]
                                / currentDistanceTravelled;
                    }
                    wattSecsPerDTPerMotor[motorNum][sampleNum] = wattSPM;
                }
            }
            sampleNum++;
            lastTime = currentTime;
            lastDistanceTravelled = currentDistanceTravelled;
            firstTime = false;
        }
    }

    public static Signal motorVoltsAvgSig = Signal.SeriesFloat(
            "MotorPwrCalcs:Volts:Avg", "motor Volts",
            AxesAndSigs.motorVoltsAxis, Units.volts);

    public static Signal motorCurrentAvgSig = Signal.SeriesFloat(
            "MotorPwrCalcs:Current:Avg", "Motor Load",
            AxesAndSigs.motorCurrentAxis, Units.amps);

    public static Signal motorWattsAvgSig = Signal.SeriesFloat(
            "MotorPwrCalcs:Watts:Avg", "Motor Load", AxesAndSigs.motorWattsAxis,
            Units.watts);

    public static Signal motorWattsSecsSig = Signal.SeriesFloat(
            "MotorPwrCalcs:WattSecs", "Motor Watt Secs",
            AxesAndSigs.motorWattsSecsAxis, Units.wattsSecs);

    public static Signal motorWattsSecsPerDistSig = Signal.SeriesFloat(
            "MotorPwrCalcs:WattSecs/Dist", "Motor Watt Secs/Distance",
            AxesAndSigs.motorWattsSecsPerDistAxis, Units.wattsSecsPerDist);

    public static Signal motorWattSecsPerTotalDistSig = Signal.SeriesFloat(
            "MotorPwrCalcs:WattSecs/TotalDist",
            "Motor Watt Secs/Total Distance",
            AxesAndSigs.motorWattsSecsPerDistAxis, Units.wattsSecsPerDist);

    public static Signal motorWattsPerVelHSig = Signal.SeriesFloat(
            "MotorPwrCalcs:Watts/VelH", "Motor Watts Per Vel",
            AxesAndSigs.motorWattsPerVelAxis, Units.wattsPerVel);

    public static Signal motorWattsPerVelDSig = Signal.SeriesFloat(
            "MotorPwrCalcs:Watts/VelD", "Motor Watts Per Vel",
            AxesAndSigs.motorWattsPerVelAxis, Units.wattsPerVel);

    protected void printPower(lineType lineT, boolean valid) {
        if (Persist.motorPowerCalcs) {
            try {
                double[] voltsAvgPerMotor = { 0.0, 0.0, 0.0, 0.0 };
                double[] currentAvgPerMotor = { 0.0, 0.0, 0.0, 0.0 };
                double[] wattSecsPerDTAvgPerMotor = { 0.0, 0.0, 0.0, 0.0 };
                double avgVolts = 0.0f;
                double totalCurrent = 0.0f;
                if (currentNumSamples != 0) {
                    for (int motorNum = 0; motorNum < convertDat
                            .getNumMotors(); motorNum++) {
                        for (int sampleNum = 0; sampleNum < currentNumSamples; sampleNum++) {
                            voltsAvgPerMotor[motorNum] = voltsAvgPerMotor[motorNum]
                                    + voltsPerMotor[motorNum][sampleNum];
                            currentAvgPerMotor[motorNum] = currentAvgPerMotor[motorNum]
                                    + currentPerMotor[motorNum][sampleNum];
                            wattSecsPerDTAvgPerMotor[motorNum] += wattSecsPerDTPerMotor[motorNum][sampleNum];
                        }
                        voltsAvgPerMotor[motorNum] = voltsAvgPerMotor[motorNum]
                                / ((double) currentNumSamples);
                        currentAvgPerMotor[motorNum] = currentAvgPerMotor[motorNum]
                                / ((double) currentNumSamples);
                        wattSecsPerDTAvgPerMotor[motorNum] = wattSecsPerDTAvgPerMotor[motorNum]
                                / ((double) currentNumSamples);
                        avgVolts += voltsAvgPerMotor[motorNum];
                        totalCurrent += currentAvgPerMotor[motorNum];
                    }
                }
                avgVolts = avgVolts / ((double) convertDat.getNumMotors());
                printCsvValue(voltsAvgPerMotor[0], motorVoltsAvgSig, "RFront",
                        lineT, valid);
                printCsvValue(voltsAvgPerMotor[1], motorVoltsAvgSig, "LFront",
                        lineT, valid);
                printCsvValue(voltsAvgPerMotor[2], motorVoltsAvgSig, "LBack",
                        lineT, valid);
                printCsvValue(voltsAvgPerMotor[3], motorVoltsAvgSig, "RBack",
                        lineT, valid);
                printCsvValue(avgVolts, motorVoltsAvgSig, "All", lineT, valid);

                printCsvValue(currentAvgPerMotor[0], motorCurrentAvgSig,
                        "RFront", lineT, valid);
                printCsvValue(currentAvgPerMotor[1], motorCurrentAvgSig,
                        "LFront", lineT, valid);
                printCsvValue(currentAvgPerMotor[2], motorCurrentAvgSig,
                        "LBack", lineT, valid);
                printCsvValue(currentAvgPerMotor[3], motorCurrentAvgSig,
                        "RBack", lineT, valid);
                printCsvValue(totalCurrent, motorCurrentAvgSig, "All", lineT,
                        valid);
                for (int i = 0; i < convertDat.getNumMotors(); i++) {
                    wattsAvgPerMotor[i] = voltsAvgPerMotor[i]
                            * currentAvgPerMotor[i];
                }
                printCsvValue(wattsAvgPerMotor[0], motorWattsAvgSig, "RFront",
                        lineT, valid);
                printCsvValue(wattsAvgPerMotor[1], motorWattsAvgSig, "LFront",
                        lineT, valid);
                printCsvValue(wattsAvgPerMotor[2], motorWattsAvgSig, "LBack",
                        lineT, valid);
                printCsvValue(wattsAvgPerMotor[3], motorWattsAvgSig, "RBack",
                        lineT, valid);
                printCsvValue(avgVolts * totalCurrent, motorWattsAvgSig, "All",
                        lineT, valid);

                printCsvValue(wattSecsPerMotor[0], motorWattsSecsSig, "RFront",
                        lineT, valid);
                printCsvValue(wattSecsPerMotor[1], motorWattsSecsSig, "LFront",
                        lineT, valid);
                printCsvValue(wattSecsPerMotor[2], motorWattsSecsSig, "LBack",
                        lineT, valid);
                printCsvValue(wattSecsPerMotor[3], motorWattsSecsSig, "RBack",
                        lineT, valid);
                printCsvValue(
                        wattSecsPerMotor[0] + wattSecsPerMotor[1]
                                + wattSecsPerMotor[2] + wattSecsPerMotor[3],
                        motorWattsSecsSig, "All", lineT, valid);

                printCsvValue(wattSecsPerDTAvgPerMotor[0],
                        motorWattsSecsPerDistSig, "RFront", lineT,
                        !(Double.isNaN(wattSecsPerDTAvgPerMotor[0])));
                printCsvValue(wattSecsPerDTAvgPerMotor[1],
                        motorWattsSecsPerDistSig, "LFront", lineT,
                        !(Double.isNaN(wattSecsPerDTAvgPerMotor[1])));
                printCsvValue(wattSecsPerDTAvgPerMotor[2],
                        motorWattsSecsPerDistSig, "LBack", lineT,
                        !(Double.isNaN(wattSecsPerDTAvgPerMotor[2])));
                printCsvValue(wattSecsPerDTAvgPerMotor[3],
                        motorWattsSecsPerDistSig, "RBack", lineT,
                        !(Double.isNaN(wattSecsPerDTAvgPerMotor[3])));
                printCsvValue(
                        wattSecsPerDTAvgPerMotor[0]
                                + wattSecsPerDTAvgPerMotor[1]
                                + wattSecsPerDTAvgPerMotor[2]
                                + wattSecsPerDTAvgPerMotor[3],
                        motorWattsSecsPerDistSig, "All", lineT,
                        !(Double.isNaN(wattSecsPerDTAvgPerMotor[0]
                                + wattSecsPerDTAvgPerMotor[1]
                                + wattSecsPerDTAvgPerMotor[2]
                                + wattSecsPerDTAvgPerMotor[3])));

                printCsvValue(wattSecsPerTotalDistPerMotor[0],
                        motorWattSecsPerTotalDistSig, "RFront", lineT,
                        !(Double.isNaN(wattSecsPerTotalDistPerMotor[0])));
                printCsvValue(wattSecsPerTotalDistPerMotor[1],
                        motorWattSecsPerTotalDistSig, "LFront", lineT,
                        !(Double.isNaN(wattSecsPerTotalDistPerMotor[1])));
                printCsvValue(wattSecsPerTotalDistPerMotor[2],
                        motorWattSecsPerTotalDistSig, "LBack", lineT,
                        !(Double.isNaN(wattSecsPerTotalDistPerMotor[2])));
                printCsvValue(wattSecsPerTotalDistPerMotor[3],
                        motorWattSecsPerTotalDistSig, "RBack", lineT,
                        !(Double.isNaN(wattSecsPerTotalDistPerMotor[3])));
                printCsvValue(
                        wattSecsPerTotalDistPerMotor[0]
                                + wattSecsPerTotalDistPerMotor[1]
                                + wattSecsPerTotalDistPerMotor[2]
                                + wattSecsPerTotalDistPerMotor[3],
                        motorWattSecsPerTotalDistSig, "All", lineT,
                        !(Double.isNaN(wattSecsPerTotalDistPerMotor[0]
                                + wattSecsPerTotalDistPerMotor[1]
                                + wattSecsPerTotalDistPerMotor[2]
                                + wattSecsPerTotalDistPerMotor[3])));

                double VelH = RecIMU.current.getVh();
                printCsvValue(wattsAvgPerMotor[0] / VelH, motorWattsPerVelHSig,
                        "RFront", lineT, (valid && (VelH != 0.0)));
                printCsvValue(wattsAvgPerMotor[1] / VelH, motorWattsPerVelHSig,
                        "LFront", lineT, (valid && (VelH != 0.0)));
                printCsvValue(wattsAvgPerMotor[2] / VelH, motorWattsPerVelHSig,
                        "LBack", lineT, (valid && (VelH != 0.0)));
                printCsvValue(wattsAvgPerMotor[3] / VelH, motorWattsPerVelHSig,
                        "RBack", lineT, (valid && (VelH != 0.0)));
                printCsvValue(
                        (wattsAvgPerMotor[0] + wattsAvgPerMotor[1]
                                + wattsAvgPerMotor[2] + wattsAvgPerMotor[3])
                                / VelH,
                        motorWattsPerVelHSig, "All", lineT,
                        (valid && (VelH != 0.0)));

                double VelD = RecIMU.current.getVd();
                printCsvValue(wattsAvgPerMotor[0] / VelD, motorWattsPerVelDSig,
                        "RFront", lineT, (valid && (VelD != 0.0)));
                printCsvValue(wattsAvgPerMotor[1] / VelD, motorWattsPerVelDSig,
                        "LFront", lineT, (valid && (VelD != 0.0)));
                printCsvValue(wattsAvgPerMotor[2] / VelD, motorWattsPerVelDSig,
                        "LBack", lineT, (valid && (VelD != 0.0)));
                printCsvValue(wattsAvgPerMotor[3] / VelD, motorWattsPerVelDSig,
                        "RBack", lineT, (valid && (VelD != 0.0)));
                printCsvValue(
                        (wattsAvgPerMotor[0] + wattsAvgPerMotor[1]
                                + wattsAvgPerMotor[2] + wattsAvgPerMotor[3])
                                / VelD,
                        motorWattsPerVelDSig, "All", lineT,
                        (valid && (VelD != 0.0)));
            } catch (Exception e) {
                DatConLog.Exception(e);
            }
        }
    }
}
