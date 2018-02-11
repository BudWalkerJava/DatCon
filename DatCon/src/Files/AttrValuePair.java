package src.Files;

public class AttrValuePair {
    public String attr;

    public String value;

    public AttrValuePair(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }

    public String getAttr() {
        return attr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
