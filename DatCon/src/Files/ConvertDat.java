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
//import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import src.DatConRecs.*;
import src.apps.DatCon;

public class ConvertDat {

    public DatFile _datFile = null;

    protected FileEnd _fileEnd = new FileEnd();

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

    public PrintStream recDefsPS = null;

    public PrintStream kmlPS = null;

    public CsvWriter csvWriter = null;

    public PrintStream tloPS = null;

    protected boolean printVersion;

    public float absoluteHeight = 0.0f;

    public boolean absoluteHeightValid = false;

    private double takeOffAlt = Double.NaN;

    private double relativeHeight = 0.0;

    private boolean relativeHeightOK = false;

    public ConvertDat(DatFile datFile) {
        _datFile = datFile;
    }

    public ConvertDat() {
    }

    public AnalyzeDatResults analyze(boolean printVersion) throws IOException {
        throw new RuntimeException("ConvertDat analyze called");
    }

    public enum lineType {
        HEADER, LINE, XML
    };

    //    public void printCsvValue(String header, float value, lineType lineT,
    //            boolean valid) throws IOException {
    //        if (lineT == lineType.HEADER) {
    //            csvWriter.print("," + header);
    //        } else {
    //            csvWriter.print(",");
    //            if (valid)
    //                csvWriter.print("" + value);
    //        }
    //    }

    //    public void printCsvValue(String header, double value, lineType lineT,
    //            boolean valid) throws IOException {
    //        if (lineT == lineType.HEADER) {
    //            csvWriter.print("," + header);
    //        } else {
    //            csvWriter.print(",");
    //            if (valid)
    //                csvWriter.print("" + value);
    //        }
    //    }

    //    public void printCsvValue(String header, int value, lineType lineT,
    //            boolean valid) throws IOException {
    //        if (lineT == lineType.HEADER) {
    //            csvWriter.print("," + header);
    //        } else {
    //            csvWriter.print(",");
    //            if (valid)
    //                csvWriter.print("" + value);
    //        }
    //    }
    //
    private void printCsvValue(String header, String value, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value.trim());
        }
    }

    //    public void printCsvValue(String header, boolean value, lineType lineT,
    //            boolean valid) throws IOException {
    //        if (lineT == lineType.HEADER) {
    //            csvWriter.print("," + header);
    //        } else {
    //            csvWriter.print(",");
    //            if (valid) {
    //                if (value) {
    //                    csvWriter.print("1");
    //                } else {
    //                    csvWriter.print("0");
    //                }
    //            }
    //        }
    //    }

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

    public void setRecords(Vector<Record> recs) {
        records = recs;
    }

    public void setSampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void createRecordParsers() {
        GoTxt_12.current = null;
        Vector<Record> rcrds = new Vector<Record>();
        try {
            int numRecParsers = 0;
            int numFound = 0;
            HashMap<Integer, RecSpec> recsInDat = (HashMap<Integer, RecSpec>) _datFile
                    .getRecsInDat().clone();

            Iterator<RecSpec> recInDatIter = recsInDat.values().iterator();
            while (recInDatIter.hasNext()) {
                RecSpec recInDat = recInDatIter.next();
                Record recordInst = getRecordInst(recInDat);
                if (recordInst != null) {
                    //                    if (recordInst.getRecType() != RecSpec.RecType.STRING) {
                    numRecParsers++;
                    int recInstLength = recordInst.getLength();
                    if (recInstLength <= recInDat.getLength()) { // recInstLength == -1 means it's a RecType.STRING
                        rcrds.addElement(recordInst);
                        numFound++;
                        DatConLog.Log("Add RecParser #" + numRecParsers + " "
                                + recordInst.getClassDescription());
                    } else {
                        DatConLog.Log(" Wrong length RecParser #"
                                + numRecParsers + " RecInDat Id/Length ="
                                + recInDat.getId() + "/" + recInDat.getLength()
                                + " RecInst/length =" + recordInst + "//"
                                + recInstLength);
                    }
                    //                    }
                } else {
                    DatConLog.Log("No RecParser #" + numRecParsers + " RecId "
                            + recInDat + "/" + recInDat.getLength());
                }
            }
            DatConLog.Log("CreateParsers found " + numFound);
            //now sort the records
            Vector<Record> otherRecs = new Vector<Record>();
            Iterator<Integer> iter = Dictionary.defaultOrder.iterator();
            while (iter.hasNext()) {
                int recId = iter.next().intValue();
                Record foundRecord = null;
                Iterator<Record> recordIter = rcrds.iterator();
                while (recordIter.hasNext()) {
                    Record rcrd = recordIter.next();
                    if (rcrd.getId() == recId) {
                        records.add(rcrd);
                        foundRecord = rcrd;
                    }
                }
                if (foundRecord != null) {
                    rcrds.remove(foundRecord);
                }
            }
            Iterator<Record> recordIter = rcrds.iterator();
            while (recordIter.hasNext()) {
                Record rcrd = recordIter.next();
                records.add(rcrd);
            }

        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

    protected Record getRecordInst(RecSpec recSpec) {
        throw new RuntimeException("ConvertDat.getRecordInst(RecInDat  called");
    }

    public DatFile getDatFile() {
        return _datFile;
    }

    protected void printCsvLine(lineType lineT) throws Exception {
        try {
            for (int i = 0; i < records.size(); i++) {
                records.get(i).printCols(lineT);
            }
            if (lineT == lineType.HEADER) {
                csvWriter.print(",Attribute|Value");
                if (printVersion) {
                    printCsvValue(this.getClass().getSimpleName(), "", lineT,
                            false);
                    if (_datFile.isTablet()) {
                        printCsvValue(DatCon.version + "-Tablet", "", lineT,
                                false);
                    } else {
                        printCsvValue(DatCon.version, "", lineT, false);
                    }
                }
            } else {
                processAttrValuesPairs();
            }
            csvWriter.print("\n");
        } catch (Exception e) {
            DatConLog.Exception(e, "ConvertDat error in printCsvLine");
            throw e;
        }
    }

    public void processCoords(double longitudeDegrees, double latitudeDegrees,
            float relativeHeight) {
        if (!gpsCoordsOK) {
            gpsCoordsOK = (longitudeDegrees != 0.0 && latitudeDegrees != 0.0
                    && relativeHeight != 0.0f);
        } else {
            this.relativeHeight = relativeHeight;
            this.relativeHeightOK = true;
        }
        if (kmlType >= 0 && tickRangeLower <= tickNo) {
            if (gpsCoordsOK) {
                float alt = relativeHeight;
                if (kmlType == 1) {
                    alt += homePointElevation;
                    absoluteHeight = alt;
                    absoluteHeightValid = true;
                }
                kmlPS.println("              " + longitudeDegrees + ","
                        + latitudeDegrees + "," + alt);
            }
        }
    }

    public void processCoordsNoGoTxt(double longitudeDegrees,
            double latitudeDegrees, float baroSmooth) {
        if (!gpsCoordsOK) {
            gpsCoordsOK = (longitudeDegrees != 0.0 && latitudeDegrees != 0.0);
        }
        if (!Double.isNaN(takeOffAlt)) {
            relativeHeight = baroSmooth - takeOffAlt;
            relativeHeightOK = true;
        }
        if (kmlType >= 0 && tickRangeLower <= tickNo) {
            if (gpsCoordsOK && relativeHeightOK) {
                float alt = (float) relativeHeight;
                if (kmlType == 1) {
                    alt += homePointElevation;
                    absoluteHeight = alt;
                    absoluteHeightValid = true;
                }
                kmlPS.println("              " + longitudeDegrees + ","
                        + latitudeDegrees + "," + alt);
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

    public void setTakeOffAlt(double takeOffAlt) {
        this.takeOffAlt = takeOffAlt;
    }

    public double getTakeOffAlt() {
        return takeOffAlt;
    }

    public boolean isRelativeHeightOK() {
        return relativeHeightOK;
    }

    public double getRelativeHeight() {
        return relativeHeight;
    }

    public float getAbsoluteHeight() {
        return absoluteHeight;
    }

    public void createXMLFile() throws IOException {
        createXMLHeader();
        createXMLGuts();
        createXMLFinal();
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

    public HashSet<Axis> axes = new HashSet<Axis>();

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
        Persist.EXPERIMENTAL_FIELDS = experimental;
    }

    public double getTime() {
        double time = _datFile.time(tickNo, 0);
        return time;
    }
}
