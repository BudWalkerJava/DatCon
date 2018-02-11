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

package src.Extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import src.apps.ExtractDJI;

public class Persist {

    File persistenceFile = null;

    ExtractDJI extract = null;

    public Persist(ExtractDJI extract) {
        this.extract = extract;
        FileReader input = null;
        String userHome = System.getProperty("user.home");
        if (userHome != null && userHome.length() > 0) {
            persistenceFile = new File(userHome + "/.extractDJI");
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
                        extract.outputDirName = outDir;
                    }
                    if (line.indexOf("inputFile:") == 0) {
                        index = line.indexOf(":") + 1;
                        String inputF = line.substring(index);
                        extract.inputFileName = inputF;
                    }
                    if (line.indexOf("checkUpdates:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            extract.checkUpdts = true;
                        } else {
                            extract.checkUpdts = false;
                        }
                    }
                    if (line.indexOf("showNewVerAvail:") == 0) {
                        index = line.indexOf(":") + 1;
                        String state = line.substring(index);
                        if (state.equalsIgnoreCase("true")) {
                            extract.showNewVerAvail = true;
                        } else {
                            extract.showNewVerAvail = false;
                        }
                    }
                    //					if (line.indexOf("loadLastOnStartup:") == 0) {
                    //						index = line.indexOf(":") + 1;
                    //						String state = line.substring(index);
                    //						if (state.equalsIgnoreCase("true")) {
                    //							extract.loadLastOnStartup = true;
                    //						} else {
                    //							extract.loadLastOnStartup = false;
                    //						}
                    //					}
                }
                input.close();
            } catch (IOException e) {
            }
        }
    }

    public void save() {
        try {
            PrintStream output = new PrintStream(persistenceFile);
            if (extract != null) {
                if (extract.outputDir != null) {
                    output.println(
                            "outputDir:" + extract.outputDir.getAbsolutePath());
                }
                if (extract.inputFile != null) {
                    output.println(
                            "inputFile:" + extract.inputFile.getAbsolutePath());
                }
                if (extract.checkUpdts) {
                    output.println("checkUpdates:true");
                } else {
                    output.println("checkUpdates:false");
                }
                if (extract.showNewVerAvail) {
                    output.println("showNewVerAvail:true");
                } else {
                    output.println("showNewVerAvail:false");
                }
                //				if (extract.loadLastOnStartup) {
                //					output.println("loadLastOnStartup:true");
                //				} else {
                //					output.println("loadLastOnStartup:false");
                //				}
            }
            output.close();
        } catch (FileNotFoundException e) {
        }
    }
}
