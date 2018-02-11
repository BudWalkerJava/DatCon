package src.DatConRecs.RecDef;

import src.DatConRecs.RecDef.Field.FieldType;

public class Field {
    public enum FieldType {
        uint32_t, uint16_t, uint8_t, int32_t, int16_t, int8_t, fp32, duble, expr;

        public static FieldType lookup(String ftype) {
            for (FieldType d : FieldType.values()) {
                if (d.name().equalsIgnoreCase(ftype)) {
                    return d;
                }
            }
            return null;
        }
    }

    FieldType fType;

    String name = "";

    int initValue = 0;

    private String exprRHS = "";

    public Field(FieldType fType, String name) {
        if (fType == null) {
            throw new RuntimeException("Field(FieldType null, String name) ");
        }
        if (name == null || name == "") {
            throw new RuntimeException("Field(FieldType fType, String null) ");
        }
        this.fType = fType;
        this.name = name;
    }

    public Field(FieldType fieldType, String name, int defaultValue) {
        this.fType = fieldType;
        this.name = name;
        try {
            this.initValue = defaultValue;
        } catch (NumberFormatException e) {

        }
    }

    public Field(String name, String type) {
        this.fType = FieldType.lookup(type);
        this.name = name;

    }

    public Field(String name, FieldType type) {
        this.fType = type;
        this.name = name;

    }

    public Field(FieldType fieldType, String varName, String exprRHS) {
        this.fType = fieldType;
        this.name = varName;
        this.exprRHS = exprRHS;
    }

    public static FieldType getFieldType(String token) {
        FieldType fieldType = null;
        if (token.equalsIgnoreCase("Op.uint16_t")) {
            fieldType = FieldType.uint16_t;
        } else if (token.equalsIgnoreCase("Op.uint8_t")) {
            fieldType = FieldType.uint8_t;
        } else if (token.equalsIgnoreCase("Op.uint32_t")) {
            fieldType = FieldType.uint32_t;
        } else if (token.equalsIgnoreCase("Op.int32_t")) {
            fieldType = FieldType.int32_t;
        } else if (token.equalsIgnoreCase("Op.int16_t")) {
            fieldType = FieldType.int16_t;
        } else if (token.equalsIgnoreCase("Op.int8_t")) {
            fieldType = FieldType.int8_t;
        } else if (token.equalsIgnoreCase("Op.fp32")) {
            fieldType = FieldType.fp32;
        } else if (token.equalsIgnoreCase("Op.float")) {
            fieldType = FieldType.fp32;
        } else if (token.equalsIgnoreCase("Op.double")) {
            fieldType = FieldType.duble;
        } else if (token.equalsIgnoreCase("Op.fp64")) {
            fieldType = FieldType.duble;
        } else if (token.equalsIgnoreCase("Op.expr")) {
            fieldType = FieldType.expr;
        }
        return fieldType;
    }

    public int getSize() {
        switch (fType) {
        case duble:
            return 8;
        case fp32:
            return 4;
        case int16_t:
            return 2;
        case int32_t:
            return 4;
        case int8_t:
            return 1;
        case uint32_t:
            return 4;
        case uint16_t:
            return 2;
        case uint8_t:
            return 1;
        default:
            return 0;
        }
    }

    public String getJavaType() {
        switch (fType) {
        case duble:
            return "double";
        case fp32:
            return "float";
        case int16_t:
            return "short";
        case int32_t:
            return "int";
        case int8_t:
            return "short";
        case uint16_t:
            return "int";
        case uint32_t:
            return "long";
        case uint8_t:
            return "short";
        default:
            break;

        }
        return null;
    }

    public String toString() {
        return fType + " " + name;
    }

    public void setDefaultValue(int defaultValue) {
        initValue = defaultValue;
    }

    public String getJavaDeclaration() {
        String retv = "protected ";
        retv += getJavaType() + " ";
        retv += name + " = ";
        retv += "(" + getJavaType() + ")" + initValue + ";";
        return retv;
    }

    public String getJavaAssnFormat() {
        switch (fType) {
        case duble:
            return " %s = _payload.getDouble(%d);";
        case fp32:
            return " %s = _payload.getFloat(%d);";
        case int16_t:
            return " %s = _payload.getShort(%d);";
        case int32_t:
            return " %s = _payload.getInt(%d);";
        case int8_t:
            return " %s = _payload.getByte(%d);;";
        case uint16_t:
            return " %s = _payload.getUnsignedShort(%d);";
        case uint32_t:
            return " %s = _payload.getUnsignedInt(%d);";
        case uint8_t:
            return "%s = _payload.getUnsignedByte(%d);";
        default:
            return "";
        }
    }

    public String getJavaPrintCsvLineFormat() {
        switch (fType) {
        case duble:
            return " printCsvValue(%s, %sDoubleSig, \"%s\",lineT, valid);";
        case fp32:
            return " printCsvValue(%s, %sFloatSig, \"%s\",lineT, valid);";
        case int16_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        case int32_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        case int8_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        case uint16_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        case uint32_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        case uint8_t:
            return " printCsvValue(%s, %sIntSig, \"%s\",lineT, valid);";
        default:
            return "";
        }
    }

    public String getPayloadType() {
        switch (fType) {
        case duble:
            return "Double";
        case fp32:
            return "Float";
        case int16_t:
            return "Short";
        case int32_t:
            return "Int";
        case int8_t:
            return "Byte";
        case uint16_t:
            return "UnsignedShort";
        case uint32_t:
            return "UnsignedInt";
        case uint8_t:
            return "UnsignedByte";
        default:
            return "";
        }
    }

    public FieldType getType() {
        return fType;
    }

    public String getName() {
        return name;
    }

}
