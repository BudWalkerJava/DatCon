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
import src.Files.DatHeader.AcType;
import src.apps.DatCon;
import src.DatConRecs.RecDef.RecordDef;

public class ConvertDat {

    public DatFile _datFile = null;

    public long tickNo = 0;

    public long tickRangeLower = 0;

    public long tickRangeUpper = Long.MAX_VALUE;

    public float sampleRate = (float) 600.0;

    public long timeOffset = 0;

    public Vector<Record> records = new Vector<Record>();

    public enum KmlType {
        NONE, GROUNDTRACK, PROFILE
    };

    public KmlType kmlType = KmlType.NONE;

    public File kmlFile;

    public String kmlFileName;

    public double homePointElevation = Double.NaN;

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

    public int getNumMotors() {
        if (_datFile.acType == AcType.M600 || _datFile.acType == AcType.S900) {
            return 6;
        }
        return 4;
    }

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

    private double longitudeHPDegrees;

    public double getHPLongDeg() {
        return longitudeHPDegrees;
    }

    private double latitudeHPDegrees;

    public double getHPLatDeg() {
        return latitudeHPDegrees;
    }

    private boolean validHP = false;

    public boolean isHpValid() {
        return validHP;
    }

    private double geoDeclination = 0.0;

    public double getGeoDeclination() {
        return geoDeclination;
    }

    private double geoInclination = 0.0;

    public double getGeoInclination() {
        return geoInclination;
    }

    private double geoIntensity = 0.0;

    private float heightHP = 0.0f;

    public float getHPHeight() {
        return heightHP;
    }

    private double longitudeHP = 0.0;

    public double getHPLongRad() {
        return longitudeHP;
    }

    private double latitudeHP = 0.0;

    public double getHPLatRad() {
        return latitudeHP;
    }

    public void setRecords(Vector<Record> recs) {
        records = recs;
    }

    public void setSampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void createRecordParsers() {
        GoTxt50_12.current = null;
        Vector<Record> rcrds = new Vector<Record>();
        try {
            int numNoRecParsers = 0;
            int numCreatedParsers = 0;
            @SuppressWarnings("unchecked")
            HashMap<Integer, RecSpec> recsInDat = (HashMap<Integer, RecSpec>) _datFile
                    .getRecsInDat().clone();

            Iterator<RecSpec> recInDatIter = recsInDat.values().iterator();
            while (recInDatIter.hasNext()) {
                RecSpec recInDat = recInDatIter.next();
                Vector<Record> recordInstVec = getRecordInst(recInDat);
                if (recordInstVec != null && recordInstVec.size() > 0) {
                    for (int recordInstVecIndex = 0; recordInstVecIndex < recordInstVec
                            .size(); recordInstVecIndex++) {
                        Record recordInst = recordInstVec
                                .get(recordInstVecIndex);
                        int recInstLength = recordInst.getLength();
                        if (recInstLength <= recInDat.getLength()) { // recInstLength == -1 means it's a RecType.STRING
                            rcrds.addElement(recordInst);
                            numCreatedParsers++;
                            DatConLog.Log("Add RecParser #" + numCreatedParsers
                                    + " " + recordInst.getClassDescription());
                        } else {
                            DatConLog.Log(" Wrong length RecParser #"
                                    + numNoRecParsers + " RecInDat Id/Length ="
                                    + recInDat.getId() + "/"
                                    + recInDat.getLength() + " RecInst/length ="
                                    + recordInst.getName() + "//"
                                    + recInstLength);
                        }
                    }
                } else {
                    numNoRecParsers++;
                    DatConLog.Log("No RecParser #" + numNoRecParsers + " RecId "
                            + recInDat + "/" + recInDat.getLength());
                }
            }
            DatConLog.Log("Num of created parsers " + numCreatedParsers
                    + " Num of NoRecParsers " + numNoRecParsers);
            //now sort the records
            Iterator<Integer> iter = Dictionary.defaultOrder.iterator();
            while (iter.hasNext()) {
                int recId = iter.next().intValue();
                Record foundRecord = null;
                Iterator<Record> recordIter = rcrds.iterator();
                while (recordIter.hasNext()) {
                    Record rcrd = recordIter.next();
                    if (rcrd.getId() == recId && !(rcrd instanceof RecordDef)) {
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

    protected Vector<Record> getRecordInst(RecSpec recSpec) {
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
        if (kmlType != KmlType.NONE && tickRangeLower <= tickNo) {
            if (gpsCoordsOK) {
                float alt = relativeHeight;
                if (kmlType == KmlType.PROFILE) {
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
            double latitudeDegrees, float baroRaw) {
        if (!gpsCoordsOK) {
            gpsCoordsOK = (longitudeDegrees != 0.0 && latitudeDegrees != 0.0);
        }
        if (!Double.isNaN(takeOffAlt)) {
            relativeHeight = baroRaw - takeOffAlt;
            relativeHeightOK = true;
        }
        if (kmlType != KmlType.NONE && tickRangeLower <= tickNo) {
            if (gpsCoordsOK) {
                if (relativeHeightOK) {
                    float alt = (float) relativeHeight;
                    if (kmlType == KmlType.PROFILE) {
                        alt += homePointElevation;
                        absoluteHeight = alt;
                        absoluteHeightValid = true;
                    }
                    kmlPS.println("              " + longitudeDegrees + ","
                            + latitudeDegrees + "," + alt);
                } else {
                    kmlPS.println("              " + longitudeDegrees + ","
                            + latitudeDegrees);
                }
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
        if (geoDeclination == 0.0) {
            geoDeclination = geoMag.getDeclination(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoDeclination",
                    String.format("%1$2.2f degrees", geoDeclination));
            geoInclination = geoMag.getDipAngle(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoInclination",
                    String.format("%1$2.2f degrees", geoInclination));
            geoIntensity = geoMag.getIntensity(latitudeHPDegrees,
                    longitudeHPDegrees);
            addAttrValuePair("geoIntensity",
                    String.format("%1$2.2f nanoTesla", geoIntensity));
        }
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
