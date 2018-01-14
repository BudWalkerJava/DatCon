package src.Files;

public class RecSpec {

    int _iD = 0;

    public void setId(int iD) {
        _iD = iD;
    }

    public int getId() {
        return _iD;
    }

    public boolean isId(int id) {
        return (_iD == id);
    }

    private int _length = 0;

    protected void setLength(int l) {
        _length = l;
    }

    public int getLength() {
        return _length;
    }

    private String name = "N/A";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum RecType {
        BINARY, STRING
    }

    protected RecSpec.RecType _recType = RecSpec.RecType.BINARY;

    public RecSpec(int t, int length) {
        _iD = t;
        _length = length;
    }

    public RecSpec.RecType getRecType() {
        return _recType;
    }
    
    protected void setRecType(RecType recType) {
        _recType = recType;
    }

    public RecSpec() {
        // TODO Auto-generated constructor stub
    }

    public RecSpec(String name, int id, RecType recType) {
        this.name = name;
        this._iD = id;
        this._recType = recType;
    }

    @Override
    public boolean equals(Object z) {
        if (!(z instanceof RecSpec))
            return false;
        return (((RecSpec) z)._iD == _iD);
    }

    @Override
    public int hashCode() {
        return _iD;
    }

    public String toString() {
        return _iD + "/" + _length;
    }

    public String getDescription() {
        return "LO " + ((0XFF) & _iD) + " ID " + _iD + " length " + _length;
    }

}
