/* Persist class

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

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Persist {

    static File persistenceFile = null;

    public static boolean showUnits = false;

    public static boolean EXPERIMENTAL_FIELDS = false;

    public static boolean autoTransDJIAFiles = true;

    public static boolean loadLastOnStartup = false;

    public static boolean showNewVerAvail = true;

    public static boolean checkUpdts = true;

    public static String inputFileName = "";

    public static String outputDirName = "";

    public static boolean EXPERIMENTAL_DEV = false;

    public static boolean motorPowerCalcs = false;

    public static boolean inertialOnlyCalcs = false;

    public static boolean magCalcs = false;

    public static boolean airComp = false;

    public static boolean invalidStructOK = false;

    static public ParsingMode parsingMode = ParsingMode.JUST_ENGINEERED;

    public static Dimension datConSize = new Dimension(900, 950);

    public static int csvSampleRate = 30;

    public static boolean logPanelEFB = false;

    public static boolean logPanelCFB = false;

    public static boolean logPanelRDFB = false;

    public static boolean smartTimeAxis = true;

    static public enum ParsingMode {
        JUST_DAT, JUST_ENGINEERED, DAT_THEN_ENGINEERED, ENGINEERED_THEN_DAT, ENGINEERED_AND_DAT
    };

    public Persist() {
        String userHome = System.getProperty("user.home");
        if (userHome != null && userHome.length() > 0) {
            persistenceFile = new File(userHome + "/.datCon");
        }
        load();
    }

    public static void save() {
        try {
            PrintStream output = new PrintStream(persistenceFile);
            if (outputDirName != null) {
                output.println("outputDir:" + outputDirName);
            }
            if (inputFileName != null) {
                output.println("inputFile:" + inputFileName);
            }
            if (checkUpdts) {
                output.println("checkUpdates:true");
            } else {
                output.println("checkUpdates:false");
            }
            if (showNewVerAvail) {
                output.println("showNewVerAvail:true");
            } else {
                output.println("showNewVerAvail:false");
            }
            if (loadLastOnStartup) {
                output.println("loadLastOnStartup:true");
            } else {
                output.println("loadLastOnStartup:false");
            }
            if (autoTransDJIAFiles) {
                output.println("autoExtractDJIAFiles:true");
            } else {
                output.println("autoExtractDJIAFiles:false");
            }
            if (EXPERIMENTAL_FIELDS) {
                output.println("experimentalFields:true");
            } else {
                output.println("experimentalFields:false");
            }
            if (showUnits) {
                output.println("showUnits:true");
            } else {
                output.println("showUnits:false");
            }
            if (motorPowerCalcs) {
                output.println("motorPowerCalcs:true");
            } else {
                output.println("motorPowerCalcs:false");
            }
            if (magCalcs) {
                output.println("magCalcs:true");
            } else {
                output.println("magCalcs:false");
            }
            if (airComp) {
                output.println("airComp:true");
            } else {
                output.println("airComp:false");
            }
            if (inertialOnlyCalcs) {
                output.println("inertialOnlyCalcs:true");
            } else {
                output.println("inertialOnlyCalcs:false");
            }

            switch (parsingMode) {
            case DAT_THEN_ENGINEERED:
                output.println("parsingMode:DAT_THEN_DEFINED");
                break;
            case ENGINEERED_THEN_DAT:
                output.println("parsingMode:DEFINED_THEN_DAT");
                break;
            case ENGINEERED_AND_DAT:
                output.println("parsingMode:ENGINEERED_AND_DAT");
                break;
            case JUST_DAT:
                output.println("parsingMode:JUST_DAT");
                break;
            case JUST_ENGINEERED:
                output.println("parsingMode:JUST_DEFINED");
                break;
            default:
                break;
            }

            if (invalidStructOK) {
                output.println("invalidStructOK:true");
            } else {
                output.println("invalidStructOK:false");
            }
            if (datConSize != null) {
                output.println(String.format("datConSize:%d,%d",
                        (long) datConSize.getWidth(),
                        (long) datConSize.getHeight()));
            }
            output.println("csvSampleRate:" + csvSampleRate);

            output.println("logPanelEFB:" + logPanelEFB);
            output.println("logPanelCFB:" + logPanelCFB);
            output.println("logPanelRDFB:" + logPanelRDFB);
            output.println("smartTimeAxis:" + smartTimeAxis);

            output.close();
        } catch (FileNotFoundException e) {
        }
    }

    public static boolean load() {
        boolean success = true;
        FileReader input = null;
        String userHome = System.getProperty("user.home");
        if (userHome != null && userHome.length() > 0) {
            persistenceFile = new File(userHome + "/.datCon");
        }

        try {
            input = new FileReader(persistenceFile);
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (input != null) {
            BufferedReader br = new BufferedReader(input);
            String line = null;
            int index = 0;
            try {
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("outputDir:") == 0) {
                        index = line.indexOf(":") + 1;
                        String outDir = line.substring(index);
                        outputDirName = outDir;
                    }
                    if (line.indexOf("inputFile:") == 0) {
                        index = line.indexOf(":") + 1;
                        String inputF = line.substring(index);
                        inputFileName = inputF;
                    }
                    if (line.indexOf("checkUpdates:") == 0) {
                        checkUpdts = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            checkUpdts = true;
                        //                        } else {
                        //                            checkUpdts = false;
                        //                        }
                    }
                    if (line.indexOf("showNewVerAvail:") == 0) {
                        showNewVerAvail = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            showNewVerAvail = true;
                        //                        } else {
                        //                            showNewVerAvail = false;
                        //                        }
                    }
                    if (line.indexOf("loadLastOnStartup:") == 0) {
                        loadLastOnStartup = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            loadLastOnStartup = true;
                        //                        } else {
                        //                            loadLastOnStartup = false;
                        //                        }
                    }
                    if (line.indexOf("autoExtractDJIAFiles:") == 0) {
                        autoTransDJIAFiles = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            autoTransDJIAFiles = true;
                        //                        } else {
                        //                            autoTransDJIAFiles = false;
                        //                        }
                    }
                    if (line.indexOf("experimentalFields:") == 0) {
                        EXPERIMENTAL_FIELDS = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            EXPERIMENTAL_FIELDS = true;
                        //                        } else {
                        //                            EXPERIMENTAL_FIELDS = false;
                        //                        }
                    }
                    if (line.indexOf("showUnits:") == 0) {
                        showUnits = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            showUnits = true;
                        //                        } else {
                        //                            showUnits = false;
                        //                        }
                    }
                    if (line.indexOf("motorPowerCalcs:") == 0) {
                        motorPowerCalcs = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            motorPowerCalcs = true;
                        //                        } else {
                        //                            motorPowerCalcs = false;
                        //                        }
                    }
                    if (line.indexOf("magCalcs:") == 0) {
                        magCalcs = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            magCalcs = true;
                        //                        } else {
                        //                            magCalcs = false;
                        //                        }
                    }
                    if (line.indexOf("inertialOnlyCalcs:") == 0) {
                        inertialOnlyCalcs = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        inertialOnlyCalcs = state.equalsIgnoreCase("true");
                    }
                    if (line.indexOf("airComp:") == 0) {
                        airComp = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            airComp = true;
                        //                        } else {
                        //                            airComp = false;
                        //                        }
                    }
                    if (line.indexOf("parsingMode:") == 0) {
                        index = line.indexOf(":") + 1;
                        String mode = line.substring(index);
                        if (mode.equalsIgnoreCase("DAT_THEN_DEFINED")) {
                            parsingMode = ParsingMode.DAT_THEN_ENGINEERED;
                        } else if (mode.equalsIgnoreCase("DEFINED_THEN_DAT")) {
                            parsingMode = ParsingMode.ENGINEERED_THEN_DAT;
                        } else if (mode
                                .equalsIgnoreCase("ENGINEERED_AND_DAT")) {
                            parsingMode = ParsingMode.ENGINEERED_AND_DAT;
                        } else if (mode.equalsIgnoreCase("JUST_DAT")) {
                            parsingMode = ParsingMode.JUST_DAT;
                        } else if (mode.equalsIgnoreCase("JUST_DEFINED")) {
                            parsingMode = ParsingMode.JUST_ENGINEERED;
                        }
                    }
                    if (line.indexOf("invalidStructOK:") == 0) {
                        invalidStructOK = parseBoolean(line);
                        //                        index = line.indexOf(":") + 1;
                        //                        String state = line.substring(index);
                        //                        if (state.equalsIgnoreCase("true")) {
                        //                            invalidStructOK = true;
                        //                        } else {
                        //                            invalidStructOK = false;
                        //                        }
                    }
                    if (line.indexOf("datConSize:") == 0) {
                        index = line.indexOf(":") + 1;
                        String size = line.substring(index);
                        String[] tokens = size.split(",");
                        if (tokens.length == 2) {
                            datConSize = new Dimension(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]));
                        }
                    }
                    if (line.indexOf("csvSampleRate:") == 0) {
                        index = line.indexOf(":") + 1;
                        String rate = line.substring(index);
                        csvSampleRate = Integer.parseInt(rate);
                    }
                    if (line.indexOf("logPanelEFB:") == 0) {
                        logPanelEFB = parseBoolean(line);
                    }
                    if (line.indexOf("logPanelCFB:") == 0) {
                        logPanelCFB = parseBoolean(line);
                    }
                    if (line.indexOf("logPanelRDFB:") == 0) {
                        logPanelRDFB = parseBoolean(line);
                    }
                    if (line.indexOf("smartTimeAxis:") == 0) {
                        smartTimeAxis = parseBoolean(line);
                    }
                }
                input.close();
            } catch (IOException e) {
            }
        }

        return success;
    }

    private static boolean parseBoolean(String line) {
        int index = line.indexOf(":") + 1;
        String state = line.substring(index);
        return (state.equalsIgnoreCase("true"));
    }
}
