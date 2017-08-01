package src.Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter extends FileWriter {
    File file = null;

    public CsvWriter(String name) throws IOException {
        this(new File(name));
    }

    public CsvWriter(File file) throws IOException {
        super(file);
        this.file = file;
    }

    public void print(String string) throws IOException {
        write(string, 0, string.length());
    }

    public void println(String string) throws IOException {
        print(string + "\n");
    }

}
