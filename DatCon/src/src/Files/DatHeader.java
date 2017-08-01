package src.Files;

import java.io.UnsupportedEncodingException;

import src.Files.DatHeader.AcType;

public class DatHeader {

    public enum AcType {
        P3I1, P3AP, P3S, I1, P4, MAVIC, P4P, I2, M600, M100, SPARK, UNKNOWN
    }

    private DatFile datFile;

    public DatHeader(DatFile datFile) {
        this.datFile = datFile;
    }

    public DatHeader.AcType getAcType() {
        DatHeader.AcType acType;
        datFile.setClockRate(4500000.0);
        switch (datFile.memory.get(0)) {
        case 05: {
            acType = DatHeader.AcType.M100;
            datFile.setClockRate(85000000.0);
            break;
        }
        case 06: {
            acType = DatHeader.AcType.P3I1;
            break;
        }
        case 11: {
            acType = DatHeader.AcType.P4;
            break;
        }
        case 14: {
            acType = DatHeader.AcType.M600;
            break;
        }
        case 16: {
            acType = DatHeader.AcType.MAVIC;
            break;
        }
        case 17: {
            acType = DatHeader.AcType.I2;
            break;
        }
        case 18: {
            acType = DatHeader.AcType.P4P;
            break;
        }
        case 21: {
            acType = DatHeader.AcType.SPARK;
            break;
        }
        case 23: { // M600 Pro
            acType = DatHeader.AcType.M600;
            break;
        }
        default: {
            acType = DatHeader.AcType.UNKNOWN;
            break;
        }
        }
        return acType;
    }

    public static String toString(AcType acType) {
        switch (acType) {
        case I1:
            return "Inspire1";
        case I2:
            return "Inspire2";
        case M100:
            return "Matrice100";
        case M600:
            return "Matrice600";
        case MAVIC:
            return "MavicPro";
        case SPARK:
            return "Spark";
        case P3AP:
            return "P3Adv/Pro";
        case P3I1:
            break;
        case P3S:
            return "P3Standard";
        case P4:
            return "P4";
        case P4P:
            return "P4Pro";
        case UNKNOWN:
            break;
        default:
            break;
        }
        return "Unknown";
    }

    public String getFWDate() {
        byte x[] = new byte[13];
        int j = 0;
        for (int i = 21; i < 33; i++) {
            x[j++] = datFile.memory.get(i);
        }
        String retv;
        try {
            retv = new String(x, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            retv = "";
        }
        return retv;
    }

}
