package src.apps;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import src.Files.AnalyzeDatResults;
import src.Files.ConvertDat;
import src.Files.CsvWriter;
import src.Files.DJIAssistantFile;
import src.Files.DJIAssistantFile.ExtractResult;
import src.Files.DatFile;
import src.Files.DatHeader;
import src.Files.DatHeader.AcType;
import src.Files.FileEnd;
import src.Files.NotDatFile;
import src.V3.Files.DatFileV3;

public class CLIDatCon {
    // private static String version = "2.3.2";

    public static void main(String[] args) {
        String datFileName = "";
        String csvFileName = "";
        String tempDirName = null;
        String msgs = "";
        if (args.length == 0) {
            System.out.println(ConvertDat.version);
            System.exit(0);
        }
        if (args.length < 2) {
            System.out.println("BadArgs");
            System.exit(0);
        }
        datFileName = args[0];
        csvFileName = args[1];
        if (args.length > 2) {
            tempDirName = args[2];
        }

        File csvFile = new File(csvFileName);
        DatFile datFile = null;
        long timeOffset = 0;
        try {
            if (DJIAssistantFile.isDJIDat(new File(datFileName))) {
                if (tempDirName != null) {
                    DJIAssistantFile.ExtractResult result = DJIAssistantFile
                            .extractFirst(datFileName, tempDirName);
                    if (result.none()) {
                        System.out.println(
                                "NotOK: No Flight Control Entries in DJIAssistantFile.DAT");
                        System.exit(1);
                    }
                    if (result.moreThanOne()) {
                        msgs += "moreThanOne";
                    }
                    datFileName = result.getFile().getAbsolutePath();
                } else {
                    System.out.println(
                            "NotOK: DJI Assistant 2 file but no temp dir");
                    System.exit(1);
                }
            }
            datFile = DatFile.createDatFile(datFileName);
            datFile.preAnalyze();
            ConvertDat convertDat = datFile.createConVertDat();
            // datFile.preAnalyze();
            if (datFile.firstMotorStartTick != 0) {
                timeOffset = datFile.firstMotorStartTick;
            }
            if (datFile.flightStartTick != -1) {
                timeOffset = datFile.flightStartTick;
            }
            convertDat.timeOffset = timeOffset;
            datFile.reset();
            convertDat.createUserRecords();
            convertDat.sampleRate = 30;
            CsvWriter csvPS = new CsvWriter(csvFile);
            convertDat.csvWriter = csvPS;
            convertDat.csvEventLogOutput = true;
            AnalyzeDatResults results = convertDat.analyze(true);
            csvPS.close();
            System.out.println("OK" + msgs);
        } catch (NotDatFile ndf) {
            if (datFile != null && datFile.acType == DatHeader.AcType.UNKNOWN) {
                System.out.println("NotOK: Can't determine type of AC");
                System.exit(1);
            }
        } catch (IOException e) {
            System.out.println("NotOK: IOException");
            e.printStackTrace();
            System.exit(1);
        } catch (FileEnd e) {
            System.out.println("NotOK: FileEnd");
            System.exit(1);
        }
    }

}
