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

package src.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import src.Files.ConvertDat;
import src.apps.DatCon;

public class Persist {

    File persistenceFile = null;

    DatCon datCon = null;

    public Persist(DatCon datCon) {
        this.datCon = datCon;
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
                        datCon.outputDirName = outDir;
                    }
                    if (line.indexOf("inputFile:") == 0) {
                        index = line.indexOf(":") + 1;
                        String inputF = line.substring(index);
                        datCon.inputFileName = inputF;
                    }
                    if (line.indexOf("checkUpdates:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            datCon.checkUpdts = true;
                        } else {
                            datCon.checkUpdts = false;
                        }
                    }
                    if (line.indexOf("showNewVerAvail:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            datCon.showNewVerAvail = true;
                        } else {
                            datCon.showNewVerAvail = false;
                        }
                    }
                    if (line.indexOf("loadLastOnStartup:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            datCon.loadLastOnStartup = true;
                        } else {
                            datCon.loadLastOnStartup = false;
                        }
                    }
                    if (line.indexOf("autoExtractDJIAFiles:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            datCon.autoTransDJIAFiles = true;
                        } else {
                            datCon.autoTransDJIAFiles = false;
                        }
                    }
                    if (line.indexOf("experimentalFields:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            ConvertDat.EXPERIMENTAL_FIELDS = true;
                        } else {
                            ConvertDat.EXPERIMENTAL_FIELDS = false;
                        }
                    }
                    if (line.indexOf("showUnits:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            ConvertDat.showUnits = true;
                        } else {
                            ConvertDat.showUnits = false;
                        }
                    }
                }
                input.close();
            } catch (IOException e) {
            }
        }
    }

    public void save() {
        try {
            PrintStream output = new PrintStream(persistenceFile);
            if (datCon != null) {
                if (datCon.outputDir != null) {
                    output.println(
                            "outputDir:" + datCon.outputDir.getAbsolutePath());
                }
                if (datCon.inputFile != null) {
                    output.println(
                            "inputFile:" + datCon.inputFile.getAbsolutePath());
                }
                if (datCon.checkUpdts) {
                    output.println("checkUpdates:true");
                } else {
                    output.println("checkUpdates:false");
                }
                if (datCon.showNewVerAvail) {
                    output.println("showNewVerAvail:true");
                } else {
                    output.println("showNewVerAvail:false");
                }
                if (datCon.loadLastOnStartup) {
                    output.println("loadLastOnStartup:true");
                } else {
                    output.println("loadLastOnStartup:false");
                }
                if (datCon.autoTransDJIAFiles) {
                    output.println("autoExtractDJIAFiles:true");
                } else {
                    output.println("autoExtractDJIAFiles:false");
                }
                if (ConvertDat.EXPERIMENTAL_FIELDS) {
                    output.println("experimentalFields:true");
                } else {
                    output.println("experimentalFields:false");
                }
                if (ConvertDat.showUnits) {
                    output.println("showUnits:true");
                } else {
                    output.println("showUnits:false");
                }
            }
            output.close();
        } catch (FileNotFoundException e) {
        }
    }
}
