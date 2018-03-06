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

package src.V3.Files;

import java.io.IOException;
import java.util.Vector;

import src.DatConRecs.Dictionary;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.RecDef.RecordDef;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatConLog;
import src.Files.FileEnd;
import src.Files.Persist;
import src.Files.RecSpec;

public class ConvertDatV3 extends ConvertDat {

    public DatFileV3 _datFile = null;

    @Override
    public DatFileV3 getDatFile() {
        return _datFile;
    }

    public ConvertDatV3(DatFileV3 datFile) {
        super(datFile);
        _datFile = datFile;
    }

    public ConvertDatV3() {
        super();
    }

    public AnalyzeDatResults analyze(boolean printVersion) throws IOException {
        insertFWDateStr();
        boolean processedPayload = false;
        this.printVersion = printVersion;
        final int sampleSize = (int) (_datFile.getClockRate() / sampleRate);

        try {
            _datFile.reset();
            // If there is a .csv being produced go ahead and output
            // the first row containing the column headings
            if (csvWriter != null) {
                csvWriter.print("Tick#,offsetTime");
                printCsvLine(lineType.HEADER);
            }
            long lastTickNoPrinted = -sampleSize;

            //while (_datFile.getNextDatRec(true, true, true, true)) {
            while (_datFile.getNextDatRec(true, true, true, false)) {
                int payloadType = _datFile._type;
                long payloadStart = _datFile._start;
                int payloadLength = _datFile._payloadLength;
                tickNo = _datFile._tickNo;
                if (tickNo > tickRangeUpper) {
                    throw new FileEnd();
                }
                for (int i = 0; i < records.size(); i++) {
                    if (records.get(i).isId(payloadType)) {
                        Payload payload = new Payload(_datFile, payloadStart,
                                payloadLength, payloadType, tickNo);
                        try {
                            ((Record) records.get(i)).process(payload);
                            processedPayload = true;
                        } catch (Exception e) {
                            String errMsg = "Can't process record "
                                    + ((Record) records.get(i)) + " tickNo="
                                    + tickNo + " filePos=" + _datFile.getPos();
                            if (Persist.EXPERIMENTAL_DEV) {
                                System.out.println(errMsg);
                                e.printStackTrace();
                            } else {
                                DatConLog.Exception(e, errMsg);
                            }
                            throw new RuntimeException(errMsg);
                        }
                    }
                }
                if (tickRangeLower <= tickNo) {
                    // if some payloads in this tick#Group were processed
                    // then output the .csv line
                    if ((csvWriter != null) && processedPayload
                            && tickNo >= lastTickNoPrinted + sampleSize) {
                        csvWriter.print(tickNo + ","
                                + _datFile.timeString(tickNo, timeOffset));
                        printCsvLine(lineType.LINE);
                        lastTickNoPrinted = tickNo;
                        processedPayload = true;
                    }
                }
            }
        } catch (Corrupted e) {
            DatConLog.Error(".DAT Corrupted");
            //e.printStackTrace();
            //            throw new RuntimeException(".DAT Corrupted");
        } catch (FileEnd e) {
        } catch (Exception e) {
        } finally {
            _datFile.close();
            DatConLog.Log("CRC Error Ratio "
                    + _datFile.getErrorRatio(Corrupted.Type.CRC));
            DatConLog.Log("Other Error Ratio "
                    + _datFile.getErrorRatio(Corrupted.Type.Other));
            DatConLog.Log(
                    "TotalNumRecExceptions = " + Record.totalNumRecExceptions);
        }
        return _datFile.getResults();
    }

    protected void insertFWDateStr() {
        addAttrValuePair("Firmware Date", _datFile.getFirmwareDate());
        addAttrValuePair("ACType", _datFile.getACTypeString());
    }

    @Override
    protected Record getRecordInst(RecSpec recInDat) {
        Record retv = null;
        retv = Dictionary.getRecordInst(
                src.DatConRecs.String.Dictionary.entries, recInDat, this, true);
        if (retv != null) {
            return retv;
        }
        switch (Persist.parsingMode) {
        case DAT_THEN_ENGINEERED:
            retv = getRecordInstFromDat(recInDat);
            if (retv != null) {
                return retv;
            }
            return getRecordInstEngineered(recInDat);
        case ENGINEERED_THEN_DAT:
            retv = getRecordInstEngineered(recInDat);
            if (retv != null) {
                return retv;
            }
            return getRecordInstFromDat(recInDat);
        case JUST_DAT:
            return getRecordInstFromDat(recInDat);
        case JUST_ENGINEERED:
            return getRecordInstEngineered(recInDat);
        default:
            return null;
        }
    }

    private Record getRecordInstEngineered(RecSpec recInDat) {
        Record retv = null;
        retv = Dictionary.getRecordInst(src.DatConRecs.Dictionary.entries,
                recInDat, this, true);
        if (retv != null) {
            return retv;
        }
        retv = Dictionary.getRecordInst(
                src.DatConRecs.Created4V3.Dictionary.entries, recInDat, this,
                true);
        return retv;
    }

    private Record getRecordInstFromDat(RecSpec recInDat) {
        Vector<RecordDef> recordDefs = _datFile.getRecordDefs();
        if (recordDefs != null) {
            for (int i = 0; i < recordDefs.size(); i++) {
                RecordDef recDef = recordDefs.get(i);
                if (recDef.getId() == recInDat.getId()
                        && recDef.getLength() <= recInDat.getLength()) {
                    recDef.init(this);
                    return recDef;
                }
            }
        }
        Record retv = null;
        if (null != (retv = Dictionary.getRecordInst(
                src.DatConRecs.FromOtherV3Dats.Dictionary.entries, recInDat,
                this, false))) {
            return retv;
        }

        return Dictionary.getRecordInst(
                src.DatConRecs.FromViewer.Dictionary.entries, recInDat, this,
                false);
    }
}
