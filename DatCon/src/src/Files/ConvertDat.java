/* ConvertDat class

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

package src.Files;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import src.DatConRecs.*;

public class ConvertDat {

    public static boolean EXPERIMENTAL_FIELDS = false;

    public static boolean showUnits = false;

    static public final String version = "2.6.2";

    public DatFile _datFile = null;

    protected FileEnd _fileEnd = new FileEnd();

    public ConvertDat(DatFile datFile) {
        _datFile = datFile;
    }

    public ConvertDat() {
    }

    public long tickNo = 0;

    public long tickRangeLower = 0;

    public long tickRangeUpper = Long.MAX_VALUE;

    public float sampleRate = (float) 600.0;

    public long timeOffset = 0;

    public Vector<Record> records = new Vector<Record>();

    public int kmlType = -1; // -1 = none, 0 = groundTrack, 1 = profile

    public File kmlFile;

    public String kmlFileName;

    public float homePointElevation = 0.0f;

    public boolean csvEventLogOutput = false;

    public PrintStream eloPS = null;

    public PrintStream cloPS = null;

    public PrintStream opPS = null;

    public PrintStream kmlPS = null;

    public CsvWriter csvWriter = null;

    public PrintStream tloPS = null;

    protected boolean printVersion;

    public float absoluteHeight = 0.0f;

    public boolean absoluteHeightValid = false;

    public AnalyzeDatResults analyze(boolean printVersion) throws IOException {
        throw new RuntimeException("ConvertDat analyze called");
    }

    protected void insertFWDateStr() {
        addAttrValuePair("Firmware Date", _datFile.getFirmwareDate());
        addAttrValuePair("ACType", _datFile.getACTypeString());
    }

    public enum lineType {
        HEADER, LINE, XML
    };

    public void printCsvValue(String header, float value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    public void printCsvValue(String header, double value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    public void printCsvValue(String header, int value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    public void printCsvValue(String header, String value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value.trim());
        }
    }

    public void printCsvValue(String header, boolean value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid) {
                if (value) {
                    csvWriter.print("1");
                } else {
                    csvWriter.print("0");
                }
            }
        }
    }

    LinkedList<AttrValuePair> attrVaulePairs = new LinkedList<AttrValuePair>();

    public void addAttrValuePair(String attr, String value) {
        attrVaulePairs.add(new AttrValuePair(attr, value));
    }

    protected void processAttrValuesPairs() throws IOException {
        csvWriter.print(",");
        if (attrVaulePairs.size() > 0) {
            AttrValuePair avp = attrVaulePairs.removeFirst();
            csvWriter.print(avp.getAttr() + "|" + avp.getValue());
        }
    }

    protected void printAttrValuePair(String attr, String value)
            throws IOException {
        csvWriter.print("," + attr + "," + value);
    }

    double lastLatRad = 0.0;

    double lastLongRad = 0.0;

    long lastTickNo = 0;

    //private boolean notFirstLine = false;

    public boolean gpsCoordsOK = false;

    public double longitudeHPDegrees;

    public double latitudeHPDegrees;

    public double declination = 0.0;

    public double inclination = 0.0;

    public double intensity = 0.0;

    public float heightHP = 0.0f;

    public double longitudeHP = 0.0;

    public double latitudeHP = 0.0;

    public boolean validHP = false;

    private void printCsvLine(PrintStream _csv, lineType lineT) {
        throw new RuntimeException("ConvertDat called");
    }

    protected double computeMagYaw(double pitch, double roll, int magX,
            int magY, int magZ, float magMod) {
        Quaternion qAcc = Quaternion.fromAngles(pitch, roll, 0.0);
        //Quaternion qAcc = Quaternion.fromAngles(pitch, 0.0, roll);
        Quaternion x = new Quaternion(0.0, magX, magY, magZ);
        Quaternion magXYPlane = qAcc.times(x).times(qAcc.conjugate());
        double X = magXYPlane.getX();
        double Y = magXYPlane.getY();
        return Math.toDegrees(-Math.atan2(Y, X));
    }

    public double computeMagYaw1(double pitch, double roll, int magX, int magY,
            int magZ, float magMod) {

        double xmag = magX / magMod;
        double ymag = magY / magMod;
        double zmag = magZ / magMod;
        double magYaw = Math.atan2(
                (-ymag * Math.cos(roll) + zmag * Math.sin(roll)),
                (xmag * Math.cos(pitch)
                        + ymag * Math.sin(pitch) * Math.sin(roll)
                        + zmag * Math.sin(pitch) * Math.cos(roll)));
        return Math.toDegrees(magYaw);
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

    public void setRecords(Vector<Record> recs) {
        records = recs;
    }

    public void setSampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void createUserRecords() {
        throw new RuntimeException("ConvertDat:createUserRecords called");
    }

    public void createSystemRecords() {
        throw new RuntimeException("ConvertDat:createSystemRecords called");
    }

    public DatFile getDatFile() {
        return _datFile;
    }

    protected void printCsvLine(ConvertDat.lineType lineT) {
        try {

            for (int i = 0; i < records.size(); i++) {
                records.get(i).printCols(lineT);
            }

            if (lineT == lineType.HEADER) {
                csvWriter.print(",Attribute|Value");
                if (printVersion) {
                    //                printCsvValue(_datFile.getACTypeString(), "", lineT, false);
                    printCsvValue(this.getClass().getSimpleName(), "", lineT,
                            false);
                    printCsvValue(version, "", lineT, false);
                }
            } else {
                processAttrValuesPairs();
            }
            //            if (csvEventLogOutput) {
            //                String noComma = Record255_1.getText().replaceAll(",", ".");
            //                printCsvValue("eventLog", noComma, lineT, true);
            //                Record255_1.current.text = "";
            //            }

            csvWriter.print("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processCoords(double longitude, double latitude, float height) {
        if (!gpsCoordsOK) {
            gpsCoordsOK = (longitude != 0.0 && latitude != 0.0
                    && height != 0.0f);
        }
        if (kmlType >= 0 && tickRangeLower <= tickNo) {
            if (gpsCoordsOK) {
                float alt = height;
                if (kmlType == 1) {
                    alt += homePointElevation;
                    absoluteHeight = alt;
                    absoluteHeightValid = true;
                }
                kmlPS.println("              " + longitude + "," + latitude
                        + "," + alt);
            }
        }
    }

    public static TSAGeoMag geoMag = new TSAGeoMag();

    public void processHomePoint(/*Record255_1 record255_1,*/
            double latitudeHPDegrees, double longitudeHPDegrees, float height) {
        this.latitudeHPDegrees = latitudeHPDegrees;
        this.longitudeHPDegrees = longitudeHPDegrees;
        heightHP = height;
        longitudeHP = Math.toRadians(longitudeHPDegrees);
        latitudeHP = Math.toRadians(latitudeHPDegrees);
        validHP = true;
        if (declination == 0.0) {
            declination = geoMag.getDeclination(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoDeclination",
                    String.format("%1$2.2f degrees", declination));
            inclination = geoMag.getDipAngle(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoInclination",
                    String.format("%1$2.2f degrees", inclination));
            intensity = geoMag.getIntensity(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoIntensity",
                    String.format("%1$2.2f nanoTesla", intensity));
        }
    }

    public double getHPLat() {
        return latitudeHPDegrees;
    }

    public double getHPLong() {
        return longitudeHPDegrees;
    }

    public boolean isHpValid() {
        return validHP;
    }

    public HashSet<Axis> axes = new HashSet<Axis>();

    public void createXMLFile() throws IOException {
        createXMLHeader();
        //        csvWriter.println("<?xml version=\"1.0\"?>");
        //        csvWriter.println("<signals>");
        //        csvWriter.println("<series>");
        //        csvWriter.println("<sigName>Tick#</sigName>");
        //        csvWriter.println("<colName>Tick#</colName>");
        //        csvWriter.println("<numType>int</numType>");
        //        csvWriter.println("<experimental>true</experimental>");
        //        csvWriter.println("</series>");
        //        csvWriter.println("<timeAxis>");
        //        csvWriter.println("<sigName>offsetTime</sigName>");
        //        csvWriter.println("<colName>offsetTime</colName>");
        //        csvWriter.println("<numType>float</numType>");
        //        csvWriter.println("</timeAxis>");
        //        axes.clear();
        //        for (int i = 0; i < records.size(); i++) {
        //            ((Record) records.get(i)).printCols(lineType.XML);
        //        }
        //        Iterator<Axis> iter = axes.iterator();
        //        while (iter.hasNext()) {
        //            Axis axis = iter.next();
        //            csvWriter.println("  <axis>");
        //            csvWriter.println("   <name>" + axis.getName() + "</name>");
        //            csvWriter.println("   <label>" + axis.getLabel() + "</label>");
        //            if (axis.getUnits() != null) {
        //                csvWriter.println("   <units>" + axis.getUnits() + "</units>");
        //            }
        //            csvWriter.println("  </axis>");
        //        }
        createXMLGuts();
        createXMLFinal();
        //        csvWriter.println("</signals>");
        //        csvWriter.close();
    }

    public void createXMLFinal() throws IOException {
        csvWriter.println("</signals>");
        csvWriter.close();
    }

    public void createXMLHeader() throws IOException {
        csvWriter.println("<?xml version=\"1.0\"?>");
        csvWriter.println("<signals>");
        csvWriter.println("<series>");
        csvWriter.println("<sigName>Tick#</sigName>");
        csvWriter.println("<colName>Tick#</colName>");
        csvWriter.println("<numType>int</numType>");
        csvWriter.println("<experimental>true</experimental>");
        csvWriter.println("</series>");
        csvWriter.println("<timeAxis>");
        csvWriter.println("<sigName>offsetTime</sigName>");
        csvWriter.println("<colName>offsetTime</colName>");
        csvWriter.println("<numType>float</numType>");
        csvWriter.println("</timeAxis>");
    }

    public void createXMLGuts() throws IOException {
        axes.clear();
        for (int i = 0; i < records.size(); i++) {
            ((Record) records.get(i)).printCols(lineType.XML);
        }
        Iterator<Axis> iter = axes.iterator();
        while (iter.hasNext()) {
            Axis axis = iter.next();
            csvWriter.println("  <axis>");
            csvWriter.println("   <name>" + axis.getName() + "</name>");
            csvWriter.println("   <label>" + axis.getLabel() + "</label>");
            if (axis.getUnits() != null) {
                csvWriter.println("   <units>" + axis.getUnits() + "</units>");
            }
            csvWriter.println("  </axis>");
        }
    }

    public void setCsvWriter(CsvWriter writer) {
        csvWriter = writer;
        for (int i = 0; i < records.size(); i++) {
            ((Record) records.get(i)).setCsvWriter(writer);
        }
    }

    public void setExperimentalFields(boolean experimental) {
        EXPERIMENTAL_FIELDS = experimental;
    }
}
