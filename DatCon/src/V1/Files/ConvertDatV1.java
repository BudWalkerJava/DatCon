package src.V1.Files;

import java.io.IOException;
import java.util.Vector;

import src.DatConRecs.Dictionary;
import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatConLog;
import src.Files.FileEnd;
import src.Files.Persist;
import src.Files.RecSpec;

public class ConvertDatV1 extends ConvertDat {

    public ConvertDatV1(DatFileV1 datFile) {
        _datFile = datFile;
    }

    public ConvertDatV1() {
    }

    public AnalyzeDatResults analyze(boolean printVersion) throws IOException {
        addAttrValuePair("ACType", _datFile.getACTypeString());
        this.printVersion = printVersion;
        int sampleSize = (int) (_datFile.getClockRate() / sampleRate);
        try {
            _datFile.reset();
            long fileLength = _datFile.getLength();
            // If there is a .csv being produced go ahead and output
            // the first row containing the column headings
            if (csvWriter != null) {
                csvWriter.print("Tick#,offsetTime");
                //printCsvLine(csvWriter, lineType.HEADER);
                printCsvLine(lineType.HEADER);
            }
            long lastTickNoPrinted = -sampleSize;
            // Main loop that gets a tick#Group and processes all the records in
            // that group
            while (true) {
                if (_datFile.getPos() > fileLength - 8) {
                    throw new FileEnd();
                }
                // Get the next tick#Group
                DatFileV1.tickGroup tG = ((DatFileV1) _datFile).getTickGroup();
                boolean processedSomePayloads = false;
                tickNo = tG.tickNo;
                if (tickNo <= tickRangeUpper) {
                    for (int tgIndex = 0; tgIndex < tG.numElements; tgIndex++) {
                        int payloadType = tG.payloadType[tgIndex];
                        long payloadStart = tG.start[tgIndex];
                        int payloadLength = tG.length[tgIndex];
                        //                        Payload payload2 = new Payload(_datFile, payloadStart,
                        //                                payloadLength, payloadType, tickNo);
                        //                        payload2.lookForString();
                        for (int i = 0; i < records.size(); i++) {
                            // For each record found in this tick#Group is it
                            // something
                            // that we want to process
                            if (records.get(i).isId(payloadType)) {
                                Payload payload = new Payload(_datFile,
                                        payloadStart, payloadLength,
                                        payloadType, tickNo);
                                ((Record) records.get(i)).process(payload);
                                processedSomePayloads = true;
                            }
                        }
                    }
                    if (tickRangeLower <= tickNo) {
                        // if some payloads in this tick#Group were processed
                        // then output the .csv line
                        if ((csvWriter != null) && processedSomePayloads
                                && tickNo >= lastTickNoPrinted + sampleSize) {
                            csvWriter.print(tickNo + ","
                                    + _datFile.timeString(tickNo, timeOffset));
                            printCsvLine(lineType.LINE);
                            lastTickNoPrinted = tickNo;
                        }
                    }
                }
            }
        } catch (FileEnd ex) {
        } catch (Corrupted ex) {
        } catch (Exception e) {
            if (Persist.EXPERIMENTAL_DEV) {
                e.printStackTrace();
            }
        } finally {
            _datFile.close();
            DatConLog.Log("CRC Error Ratio "
                    + _datFile.getErrorRatio(Corrupted.Type.CRC));
            DatConLog.Log("Other Error Ratio "
                    + _datFile.getErrorRatio(Corrupted.Type.Other));
        }
        return _datFile.getResults();
    }

    @Override
    protected Vector<Record> getRecordInst(RecSpec recInDat) {
        Vector<Record> retv = new Vector<Record>();
        Record rec = null;
        rec = Dictionary.getRecordInst(src.DatConRecs.String.Dictionary.entries,
                recInDat, this, true);
        if (rec != null) {
            retv.add(rec);
            return retv;
        }
        switch (Persist.parsingMode) {
        case DAT_THEN_ENGINEERED:
            rec = getRecordInstFromDat(recInDat);
            if (rec != null) {
                retv.add(rec);
            } else {
                rec = getRecordInstEngineered(recInDat);
                if (rec != null) {
                    retv.add(rec);
                }
            }
        case ENGINEERED_THEN_DAT:
            rec = getRecordInstEngineered(recInDat);
            if (rec != null) {
                retv.add(rec);
            } else {
                rec = getRecordInstFromDat(recInDat);
                if (rec != null) {
                    retv.add(rec);
                }
            }
            break;
        case JUST_DAT:
            rec = getRecordInstFromDat(recInDat);
            if (rec != null) {
                retv.add(rec);
            }
            break;
        case JUST_ENGINEERED:
            rec = getRecordInstEngineered(recInDat);
            if (rec != null) {
                retv.add(rec);
            }
            break;
        case ENGINEERED_AND_DAT:
            rec = getRecordInstEngineered(recInDat);
            if (rec != null) {
                retv.add(rec);
            }
            rec = getRecordInstFromDat(recInDat);
            if (rec != null) {
                retv.add(rec);
            }
            break;
        default:
            return retv;
        }
        return retv;
    }

    private Record getRecordInstEngineered(RecSpec recInDat) {
        Record retv = null;
        retv = Dictionary.getRecordInst(src.DatConRecs.Dictionary.entries,
                recInDat, this, true);
        if (retv != null) {
            return retv;
        }
        retv = Dictionary.getRecordInst(
                src.DatConRecs.Created4V1.Dictionary.entries, recInDat, this,
                true);
        return retv;
    }

    private Record getRecordInstFromDat(RecSpec recInDat) {
        Record retv = Dictionary.getRecordInst(
                src.DatConRecs.FromViewer.Dictionary.entries, recInDat, this,
                false);
        return retv;
    }

}
