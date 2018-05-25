package src.DatConRecs.Created4V3;

import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;

public class BatBox32_5004 extends BatBox32_500X {
    protected boolean valid = false;

    protected int time = (int) 0;

    protected short rec_data = (short) 0;

    protected short soc = (short) 0;

    protected int cell1 = (int) 0;

    protected int cell2 = (int) 0;

    protected int cell3 = (int) 0;

    protected int cell4 = (int) 0;

    protected int cell5 = (int) 0;

    protected int cell6 = (int) 0;

    protected short pro_curr = (short) 0;

    protected short current = (short) 0;

    protected int pack_vol = (int) 0;

    protected short temp1 = (short) 0;

    protected short temp2 = (short) 0;

    protected int flag = (int) 0;

    protected long user_def = (long) 0;

    public BatBox32_5004(ConvertDat convertDat) {
        super(convertDat, 5004, 1);
    }

//    @Override
//    public void process(Payload _payload) {
//        super.process(_payload);
//       
//    }

    
//
//    public void printCols(lineType lineT) {
//        try {
//
//            printCsvValue(time, bat2_boxIntSig, "time", lineT, valid);
//            printCsvValue(rec_data, bat2_boxIntSig, "rec_data", lineT, valid);
//            printCsvValue(soc, bat2_boxIntSig, "soc", lineT, valid);
//            printCsvValue(cell1, bat2_boxIntSig, "cell1", lineT, valid);
//            printCsvValue(cell2, bat2_boxIntSig, "cell2", lineT, valid);
//            printCsvValue(cell3, bat2_boxIntSig, "cell3", lineT, valid);
//            printCsvValue(cell4, bat2_boxIntSig, "cell4", lineT, valid);
//            printCsvValue(cell5, bat2_boxIntSig, "cell5", lineT, valid);
//            printCsvValue(cell6, bat2_boxIntSig, "cell6", lineT, valid);
//            printCsvValue(pro_curr, bat2_boxIntSig, "pro_curr", lineT, valid);
//            printCsvValue(current, bat2_boxIntSig, "current", lineT, valid);
//            printCsvValue(pack_vol, bat2_boxIntSig, "pack_vol", lineT, valid);
//            printCsvValue(temp1, bat2_boxIntSig, "temp1", lineT, valid);
//            printCsvValue(temp2, bat2_boxIntSig, "temp2", lineT, valid);
//            printCsvValue(flag, bat2_boxIntSig, "flag", lineT, valid);
//            printCsvValue(user_def, bat2_boxIntSig, "user_def", lineT, valid);
//        } catch (Exception e) {
//            DatConLog.Exception(e);
//        }
//    }

}
