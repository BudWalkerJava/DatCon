package src.Files;

public class TypeSubType {
    int _type = 0;

    private int _subType = 0;

    //int _length = 0;

    public TypeSubType(int t, int st) {
        _type = t;
        setSubType(st);
    }

    @Override
    public boolean equals(Object z) {
        if (!(z instanceof TypeSubType))
            return false;
        TypeSubType x = (TypeSubType) z;
        return (x._type == _type && x.getSubType() == getSubType());
    }

    @Override
    public int hashCode() {
        return _type * getSubType();
    }

    public String toString() {
        return _type + "_" + getSubType();
    }

    public boolean is(int t, int st) {
        return (t == _type && st == getSubType());
    }

    public int getSubType() {
        return _subType;
    }

    public void setSubType(int _subType) {
        this._subType = _subType;
    }

    public int getType() {
        return _type;
    }
}
