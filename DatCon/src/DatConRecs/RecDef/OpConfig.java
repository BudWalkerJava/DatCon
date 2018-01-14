package src.DatConRecs.RecDef;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Vector;

import src.DatConRecs.RecDef.Field.FieldType;
import src.Files.DatConLog;
import src.Files.Persist;
import src.Files.RecClassSpec;
import src.Files.RecSpec;

public class OpConfig {

    enum State {
        backup1, ConfigSeen, spin, NameSeen, TypeSeen, FtypeSeen, StringSeen, StringNameSeen, StringTypeSeen
    }

    enum RecType {
        normal, string, none;
    }

    private Vector<RecordDef> records = new Vector<RecordDef>();

    public static class ParseError extends Exception {

        private State _state = null;

        //        private String _token = "Unknown";

        private Line _line;

        private int _lineNum;

        public ParseError(Line line, int lineNum, State state) {
            _line = line;
            _lineNum = lineNum;
            _state = state;
        }

        public String toString() {
            return "State " + _state + " Line " + _line + " lineNum "
                    + _lineNum;
        }
    }

    public static class Line {
        Vector<String> tokens = new Vector<String>();

        public Line(String opLine) {
            String tkns[] = opLine.split("\\s+");
            for (int i = 0; i < tkns.length; i++) {
                addToken(tkns[i]);
            }
        }

        public void addToken(String token) {
            tokens.add(token);
        }

        public Vector<String> getTokens() {
            return tokens;
        }

        public String toString() {
            String retv = "";
            for (int i = 0; i < tokens.size(); i++) {
                retv = retv + tokens.get(i) + " ";
            }
            return retv;
        }
    }

    private int recId;

    HashSet<String> varNames = new HashSet<String>(200);

    int varNameExtension = 0;

    public OpConfig(Vector<Line> lines) {

        State state = State.backup1;
        String recName = "";
        FieldType fieldType = null;
        String varName = "";
        RecordDef record = null;
        Field field = null;
        Line line = null;
        Vector<String> tokens = null;
        String firstToken = null;
        int defaultValue = 0;
        int lineNum = 1;
        int numTokens = 0;
        try {
            while (lineNum < lines.size()) {
                line = lines.get(lineNum);
                tokens = line.getTokens();
                numTokens = tokens.size();
                if (numTokens > 0) {
                    firstToken = tokens.get(0);
                } else {
                    throw new ParseError(line, lineNum, state);
                }

                switch (state) {
                case backup1:
                    lineNum--;
                    line = lines.get(lineNum);
                    tokens = line.getTokens();
                    numTokens = tokens.size();
                    if (numTokens > 0) {
                        firstToken = tokens.get(0);
                        if (firstToken.equalsIgnoreCase("Op.[config]")) {
                            state = State.ConfigSeen;
                        } else if (firstToken.equalsIgnoreCase("Op.[string]")) {
                            state = State.StringSeen;
                        } else {
                            //System.out.println(tokens);
                            state = State.spin;
                        }
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    break;
                case spin:
                    if (firstToken.equalsIgnoreCase("Op.[config]")) {
                        state = State.ConfigSeen;
                    } else if (firstToken.equalsIgnoreCase("Op.[string]")) {
                        state = State.StringSeen;
                    } else if (firstToken.equalsIgnoreCase("Op.expr")) {
                        state = State.spin;
                    } else {
                        //System.out.println(tokens);
                        state = State.spin;
                    }
                    break;
                case ConfigSeen:
                    if (firstToken.equalsIgnoreCase("name") && numTokens > 1) {
                        state = State.NameSeen;
                        recName = tokens.get(1);
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    break;
                case NameSeen:
                    if (firstToken.equalsIgnoreCase("type")) {
                        if (isNumber(tokens.get(1))) {
                            recId = Integer.parseInt(tokens.get(1));
                            record = new RecordDef(recName, recId,
                                    RecSpec.RecType.BINARY);
                            varNames.clear();
                            varNameExtension = 0;
                            state = State.TypeSeen;
                            break;
                        } else {
                            //System.out.println(tokens);
                            state = State.spin;
                        }
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    break;

                case TypeSeen:
                    fieldType = Field.getFieldType(firstToken);
                    if (fieldType == null) {
                        records.add(record);
                        state = State.backup1;
                        break;
                    }
                    if (numTokens > 1) {
                        varName = normalizeVarName(tokens.get(1));
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    if (fieldType == FieldType.expr) {
                        if (numTokens == 3 && !isNumber(tokens.get(2))) {
                            String exprRHS = tokens.get(2);
                            field = new Field(fieldType, varName, exprRHS);
                        }
                        state = State.TypeSeen;
                        break;
                    }
                    if (numTokens > 2 && isNumber(tokens.get(2))) {
                        defaultValue = Integer.parseInt(tokens.get(2));
                    }
                    field = new Field(fieldType, varName, defaultValue);
                    record.addField(field);
                    state = State.TypeSeen;
                    break;
                case FtypeSeen:
                    fieldType = Field.getFieldType(firstToken);
                    if (fieldType == null) {
                        state = State.backup1;
                        break;
                    }
                    if (numTokens > 1) {
                        varName = normalizeVarName(tokens.get(1));
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    if (numTokens > 2 && isNumber(tokens.get(2))) {
                        defaultValue = Integer.parseInt(tokens.get(2));
                    }
                    break;
                case StringSeen:
                    if (firstToken.equalsIgnoreCase("name") && numTokens > 1) {
                        state = State.StringNameSeen;
                        recName = tokens.get(1);
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                    break;
                case StringNameSeen:
                    if (firstToken.equalsIgnoreCase("type")) {
                        if (isNumber(tokens.get(1))) {
                            recId = Integer.parseInt(tokens.get(1));
                            record = new RecordDef(recName, recId,
                                    RecSpec.RecType.STRING);
                            state = State.StringTypeSeen;
                            break;
                        }
                    } else {
                        throw new ParseError(line, lineNum, state);
                    }
                case StringTypeSeen:
                    records.add(record);
                    state = State.backup1;
                    break;
                default:
                    break;
                }
                lineNum++;
            }
            if (record != null && record.getNumFields() > 0) {
                records.add(record);
            }
        } catch (ParseError pe) {
            if (Persist.EXPERIMENTAL_DEV) {
                System.out.println("ParseError " + pe);
                pe.printStackTrace();
            } else {
                DatConLog.Exception(pe, "ParseError " + pe);
            }
            DatConLog.Exception(pe, "ParseError " + pe);
        }
    }

    private String normalizeVarName(String name) {
        String newName = name;
        newName = newName.replaceAll("\\(%\\)", "_pcnt");
        if (newName.indexOf("[") >= 0) {
            newName = newName.replaceAll("\\[", "_");
            newName = newName.replaceAll("\\]", "");
        }
        newName = newName.replaceAll("\\.", "_");
        newName = newName.replaceAll("^1", "One_");
        newName = newName.replaceAll("^2", "Two_");
        newName = newName.replaceAll("^3", "Three_");
        newName = newName.replaceAll("^4", "Four_");
        newName = newName.replaceAll("^5", "Five_");
        newName = newName.replaceAll("^6", "Six_");
        newName = newName.replaceAll("^7", "Seven_");
        newName = newName.replaceAll("^8", "Eight_");
        newName = newName.replaceAll("^9", "Nine_");
        newName = newName.replaceAll("^0", "Zero_");
        newName = newName.replaceAll("continue", "Continue");
        while (varNames.contains(newName)) {
            varNameExtension++;
            newName += "_" + varNameExtension;
        }
        varNames.add(newName);
        return newName;
    }

    public void printRecords() {
        for (int i = 0; i < records.size(); i++) {
            RecordDef record = records.get(i);
            System.out.println(record);
        }
    }

    public Vector<RecordDef> getRecords() {
        return records;
    }

    private static boolean isNumber(String token) {
        try {
            Integer number = Integer.parseInt(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
