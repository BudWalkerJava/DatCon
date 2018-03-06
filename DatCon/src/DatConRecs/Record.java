/* RecordType class

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
package src.DatConRecs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import src.Files.Axis;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.CsvWriter;
import src.Files.DatConLog;
import src.Files.DatFile;
import src.Files.Persist;
import src.Files.RecSpec;
import src.Files.Signal;
import src.V3.Files.DatFileV3;

public abstract class Record extends RecSpec {

    protected ByteBuffer payloadBB = null;

    protected CsvWriter csvWriter = null;

    protected ConvertDat convertDat = null;

    protected DatFile _datFile;

    public void setDatFile(DatFile datFile) {
        _datFile = datFile;
    }

    protected int numRecExceptions = 0;

    public static int totalNumRecExceptions = 0;

    static DecimalFormat df = new DecimalFormat("000.#############",
            new DecimalFormatSymbols(Locale.US));

    public Record() {
        super();
    }

    public String getDescription() {
        return this.getClass().toString();
    }

    public Record(ConvertDat convertDat, int id, int length) {
        super(id, length);
        this.convertDat = convertDat;
        _datFile = this.convertDat.getDatFile();
        this.csvWriter = convertDat.csvWriter;
    }

    public Record(DatFileV3 datFile) {
        this._datFile = datFile;
    }

    public Record(ConvertDat convertDat2) {
        // TODO Auto-generated constructor stub
    }

    public Record(String name, int id, RecType recType) {
        super(name, id, recType);
    }

    public void process(Payload _record) {
        payloadBB = _record.getBB();
    }

    protected float minVolts(float[] volts) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] < min)
                min = volts[i];
        }
        return min;
    }

    protected float maxVolts(float[] volts) {
        float max = Float.MIN_VALUE;
        for (int i = 0; i < volts.length; i++) {
            if (volts[i] > max)
                max = volts[i];
        }
        return max;
    }

    public void printCols(lineType lineT) {
        throw new RuntimeException("printCols called in Record");
    }

    protected void printCsvValue(Number value, Signal signal, String suffix,
            lineType lineT, boolean valid) throws IOException {
        if (lineT == lineType.XML) {
            printXmlSig(signal.getName(), suffix, signal);
            return;
        }
        if (Persist.EXPERIMENTAL_FIELDS || !signal.isExperimental()) {
            if (lineT == lineType.HEADER) {
                csvWriter.print("," + signal.getName());
                if (suffix != null && !suffix.isEmpty()) {
                    csvWriter.print(":" + suffix);
                }
                if (Persist.showUnits && signal.hasUnits()) {
                    csvWriter.print("[" + signal.getUnitsNoComma() + "]");
                }
            } else if (lineT == lineType.LINE) {
                csvWriter.print(",");
                if (valid)
                    csvWriter.print("" + value);
            }
        }
    }

    protected void printCsvValue(String value, Signal signal, String suffix,
            lineType lineT, boolean valid) throws IOException {
        if (lineT == lineType.XML) {
            printXmlSig(signal.getName(), suffix, signal);
            return;
        }
        if (Persist.EXPERIMENTAL_FIELDS || !signal.isExperimental()) {
            if (lineT == lineType.HEADER) {
                csvWriter.print("," + signal.getName());
                if (suffix != "") {
                    csvWriter.print(":" + suffix);
                }
                if (Persist.showUnits && signal.hasUnits()) {
                    csvWriter.print("[" + signal.getUnitsNoComma() + "]");
                }
            } else if (lineT == lineType.LINE) {
                csvWriter.print(",");
                if (valid)
                    csvWriter.print("" + value);
            }
        }
    }

    protected void RecordException(Exception e) {
        if (numRecExceptions == 0) {
            String errMsg = "RecException filePos()=" + _datFile.getPos()
                    + " tickNo " + _datFile._tickNo + " type ="
                    + _datFile._type;
            if (Persist.EXPERIMENTAL_DEV) {
                System.out.println(errMsg);
                e.printStackTrace();
            } else {
                DatConLog.Exception(e, errMsg);
            }
        }
        numRecExceptions++;
        totalNumRecExceptions++;
    }

    // Following won't create entries in XML file
    //    protected void printCsvValue(boolean value, String header, lineType lineT,
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
    //
    //    protected void printCsvValue(int value, String header, lineType lineT,
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
    protected void printCsvValue(float value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.XML)
            return;
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    protected void printCsvValue(String value, String header, lineType lineT,
            boolean valid) throws IOException {
        if (lineT == lineType.HEADER) {
            csvWriter.print("," + header);
        } else {
            csvWriter.print(",");
            if (valid)
                csvWriter.print("" + value);
        }
    }

    private void printXmlSig(String name, String suffix, Signal signal)
            throws IOException {
        String colName = name;
        String description;
        if (suffix != null && !suffix.equalsIgnoreCase("")) {
            colName = name + ":" + suffix;
        }
        switch (signal.getType()) {
        case SERIES:
            csvWriter.println("<series>");
            csvWriter.println("  <sigName>" + colName + "</sigName>");
            csvWriter.println("  <colName>" + colName + "</colName>");
            Axis axis = signal.getAxis();
            if (axis != null) {
                csvWriter.println("  <axis>" + axis.getName() + "</axis>");
                convertDat.axes.add(axis);
            }
            switch (signal.getNumType()) {
            case DOUBLE:
                csvWriter.println("  <numType>double</numType>");
                break;
            case FLOAT4:
                csvWriter.println("  <numType>float</numType>");
                break;
            case INT:
                csvWriter.println("  <numType>int</numType>");
                break;
            case UNDEFINED:
                break;
            default:
                break;
            }
            description = signal.getDescription();
            if (description != null) {
                csvWriter.println(
                        "  <description>" + description + "</description>");
            }
            if (signal.isExperimental()) {
                csvWriter.println("  <experimental>true</experimental>");
            }
            if (signal.hasUnits()) {
                csvWriter.println("  <units>" + signal.getUnits() + "</units>");
            }
            csvWriter.println("</series>");
            break;
        case STATE:
            csvWriter.println("<state>");
            csvWriter.println("  <sigName>" + colName + "</sigName>");
            csvWriter.println("  <colName>" + colName + "</colName>");
            csvWriter.println("  <inverse></inverse>");
            description = signal.getDescription();
            if (description != null) {
                csvWriter.println(
                        "  <description>" + description + "</description>");
            }
            csvWriter.println("  <stateSpec>");
            csvWriter.println("     <stateName>" + signal.getDefaultState()
                    + "</stateName>");
            csvWriter.println("     <color>white</color>");
            csvWriter.println("  </stateSpec>");
            csvWriter.println("</state>");
            break;
        case TIMEAXIS:
            break;
        case UNDEFINED:
            break;
        default:
            break;
        }
    }

    public void setCsvWriter(CsvWriter writer) {
        csvWriter = writer;
    }

    public String getClassDescription() {
        return getClass().toString() + " /" + getLength();
    }

}
