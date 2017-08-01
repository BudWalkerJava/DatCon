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

import src.DatConRecs.Payload;
import src.DatConRecs.Record;
import src.DatConRecs.Record255_253;
import src.DatConRecs.Record255_255;
import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.Corrupted;
import src.Files.DatConLog;
import src.Files.FileEnd;

public class ConvertDatV3 extends ConvertDat {

    public DatFileV3 _datFile = null;

    //
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

            while (_datFile.getNextDatRec(true, true, true, true)) {
                int payloadType = _datFile._type;
                long payloadStart = _datFile._start;
                int payloadLength = _datFile._payloadLength;
                short subType = _datFile._subType;
                tickNo = _datFile._tickNo;
                if (tickNo > tickRangeUpper) {
                    throw _fileEnd;
                }
                for (int i = 0; i < records.size(); i++) {
                    if (records.get(i).isType(payloadType, subType)) {
                        Payload payload = new Payload(_datFile, payloadStart,
                                payloadLength, payloadType, subType, tickNo);
                        try {
                            ((Record) records.get(i)).process(payload);
                        } catch (Exception e) {
                            DatConLog.Exception(e, "Can't process record "
                                    + ((Record) records.get(i)));
                            throw new RuntimeException("Can't process record "
                                    + ((Record) records.get(i)));
                        }
                        processedPayload = true;
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
            //            e.printStackTrace();
            //            throw new RuntimeException(".DAT Corrupted");
        } catch (FileEnd e) {
        } finally {
            _datFile.close();
        }
        return _datFile.getResults();
    }

    public void createUserRecords() {
        throw new RuntimeException("createRecords in ConvertDatV3 called");
    }

    @Override
    public void createSystemRecords() {
        records.add(new Record255_255(this));
//        if (ConvertDat.EXPERIMENTAL_DEV) {
//            records.add(new Record255_253(this));
//        }
    }

}
