package src.Files;

import src.DatConRecs.Record;

public class RecClassSpec extends RecSpec {

    Class<Record> recClass = null;

    int lengths[] = null;

    @SuppressWarnings("unchecked")
    public RecClassSpec(Class recClass, int id, int length) {
        super(id, length);
        this.recClass = recClass;
        if (length == -1) {
            setRecType(RecType.STRING);
        }
    }

    @SuppressWarnings("unchecked")
    public RecClassSpec(Class recClass, int id, int... lengths) {
        super(id, -1);
        this.recClass = recClass;
        this.lengths = new int[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            this.lengths[i] = lengths[i];
        }
    }

    public boolean lengthOK(int l) {
        if (getRecType() == RecType.STRING) {
            return true;
        }
        if (l == getLength()) {
            return true;
        }
        if (lengths != null) {
            for (int i = 0; i < lengths.length; i++) {
                if (l == lengths[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public Class<Record> getRecClass() {
        return recClass;
    }

    public String toString() {
        return recClass.getName();
    }

}
