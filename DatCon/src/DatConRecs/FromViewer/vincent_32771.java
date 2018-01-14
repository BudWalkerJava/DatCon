package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class vincent_32771 extends Record {
protected boolean valid = false;

protected float vincent_debug_data0 = (float)0;
protected float vincent_debug_data1 = (float)0;
protected float vincent_debug_data2 = (float)0;
protected float vincent_debug_data3 = (float)0;
protected float vincent_debug_data4 = (float)0;
protected float vincent_debug_data5 = (float)0;
protected float vincent_debug_data6 = (float)0;
protected float vincent_debug_data7 = (float)0;
protected float vincent_debug_data8 = (float)0;
protected float vincent_debug_data9 = (float)0;
protected float vincent_debug_data10 = (float)0;
protected float vincent_debug_data11 = (float)0;
protected float vincent_debug_data12 = (float)0;
protected float vincent_debug_data13 = (float)0;
protected float vincent_debug_data14 = (float)0;
protected float vincent_debug_data15 = (float)0;
protected float vincent_debug_data16 = (float)0;
protected float vincent_debug_data17 = (float)0;
protected float vincent_debug_data18 = (float)0;
protected float vincent_debug_data19 = (float)0;
protected float vincent_debug_data20 = (float)0;
protected float vincent_debug_data21 = (float)0;
protected float vincent_debug_data22 = (float)0;
protected float vincent_debug_data23 = (float)0;
protected float vincent_debug_data24 = (float)0;
protected float vincent_debug_data25 = (float)0;
protected float vincent_debug_data26 = (float)0;
protected float vincent_debug_data27 = (float)0;
protected float vincent_debug_data28 = (float)0;
protected float vincent_debug_data29 = (float)0;
protected float vincent_debug_data30 = (float)0;
protected float vincent_debug_data31 = (float)0;
protected float vincent_debug_data32 = (float)0;
protected float vincent_debug_data33 = (float)0;
protected float vincent_debug_data34 = (float)0;
protected float vincent_debug_data35 = (float)0;
protected float vincent_debug_data36 = (float)0;
protected float vincent_debug_data37 = (float)0;
protected float vincent_debug_data38 = (float)0;
protected float vincent_debug_data39 = (float)0;
protected float vincent_debug_data40 = (float)0;
protected float vincent_debug_data41 = (float)0;
protected float vincent_debug_data42 = (float)0;
protected float vincent_debug_data43 = (float)0;
protected float vincent_debug_data44 = (float)0;
protected float vincent_debug_data45 = (float)0;
protected float vincent_debug_data46 = (float)0;
protected float vincent_debug_data47 = (float)0;
protected float vincent_debug_data48 = (float)0;
protected float vincent_debug_data49 = (float)0;
protected float vincent_debug_data50 = (float)0;
protected float vincent_debug_data51 = (float)0;
protected float vincent_debug_data52 = (float)0;
protected float vincent_debug_data53 = (float)0;
protected float vincent_debug_data54 = (float)0;
protected float vincent_debug_data55 = (float)0;
protected float vincent_debug_data56 = (float)0;
protected float vincent_debug_data57 = (float)0;
protected float vincent_debug_data58 = (float)0;
protected float vincent_debug_data59 = (float)0;

      public vincent_32771(ConvertDat convertDat) {
           super(convertDat, 32771, 240);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 vincent_debug_data0 = _payload.getFloat(0);
 vincent_debug_data1 = _payload.getFloat(4);
 vincent_debug_data2 = _payload.getFloat(8);
 vincent_debug_data3 = _payload.getFloat(12);
 vincent_debug_data4 = _payload.getFloat(16);
 vincent_debug_data5 = _payload.getFloat(20);
 vincent_debug_data6 = _payload.getFloat(24);
 vincent_debug_data7 = _payload.getFloat(28);
 vincent_debug_data8 = _payload.getFloat(32);
 vincent_debug_data9 = _payload.getFloat(36);
 vincent_debug_data10 = _payload.getFloat(40);
 vincent_debug_data11 = _payload.getFloat(44);
 vincent_debug_data12 = _payload.getFloat(48);
 vincent_debug_data13 = _payload.getFloat(52);
 vincent_debug_data14 = _payload.getFloat(56);
 vincent_debug_data15 = _payload.getFloat(60);
 vincent_debug_data16 = _payload.getFloat(64);
 vincent_debug_data17 = _payload.getFloat(68);
 vincent_debug_data18 = _payload.getFloat(72);
 vincent_debug_data19 = _payload.getFloat(76);
 vincent_debug_data20 = _payload.getFloat(80);
 vincent_debug_data21 = _payload.getFloat(84);
 vincent_debug_data22 = _payload.getFloat(88);
 vincent_debug_data23 = _payload.getFloat(92);
 vincent_debug_data24 = _payload.getFloat(96);
 vincent_debug_data25 = _payload.getFloat(100);
 vincent_debug_data26 = _payload.getFloat(104);
 vincent_debug_data27 = _payload.getFloat(108);
 vincent_debug_data28 = _payload.getFloat(112);
 vincent_debug_data29 = _payload.getFloat(116);
 vincent_debug_data30 = _payload.getFloat(120);
 vincent_debug_data31 = _payload.getFloat(124);
 vincent_debug_data32 = _payload.getFloat(128);
 vincent_debug_data33 = _payload.getFloat(132);
 vincent_debug_data34 = _payload.getFloat(136);
 vincent_debug_data35 = _payload.getFloat(140);
 vincent_debug_data36 = _payload.getFloat(144);
 vincent_debug_data37 = _payload.getFloat(148);
 vincent_debug_data38 = _payload.getFloat(152);
 vincent_debug_data39 = _payload.getFloat(156);
 vincent_debug_data40 = _payload.getFloat(160);
 vincent_debug_data41 = _payload.getFloat(164);
 vincent_debug_data42 = _payload.getFloat(168);
 vincent_debug_data43 = _payload.getFloat(172);
 vincent_debug_data44 = _payload.getFloat(176);
 vincent_debug_data45 = _payload.getFloat(180);
 vincent_debug_data46 = _payload.getFloat(184);
 vincent_debug_data47 = _payload.getFloat(188);
 vincent_debug_data48 = _payload.getFloat(192);
 vincent_debug_data49 = _payload.getFloat(196);
 vincent_debug_data50 = _payload.getFloat(200);
 vincent_debug_data51 = _payload.getFloat(204);
 vincent_debug_data52 = _payload.getFloat(208);
 vincent_debug_data53 = _payload.getFloat(212);
 vincent_debug_data54 = _payload.getFloat(216);
 vincent_debug_data55 = _payload.getFloat(220);
 vincent_debug_data56 = _payload.getFloat(224);
 vincent_debug_data57 = _payload.getFloat(228);
 vincent_debug_data58 = _payload.getFloat(232);
 vincent_debug_data59 = _payload.getFloat(236);
} catch (Exception e) {RecordException(e);}}


    protected static Signal vincentIntSig = Signal
.SeriesInt("vincent", "", null, Units.noUnits);
    protected static Signal vincentFloatSig = Signal
.SeriesFloat("vincent", "", null, Units.noUnits);
    protected static Signal vincentDoubleSig = Signal
.SeriesDouble("vincent", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(vincent_debug_data0, vincentFloatSig, "vincent_debug_data0",lineT, valid);
 printCsvValue(vincent_debug_data1, vincentFloatSig, "vincent_debug_data1",lineT, valid);
 printCsvValue(vincent_debug_data2, vincentFloatSig, "vincent_debug_data2",lineT, valid);
 printCsvValue(vincent_debug_data3, vincentFloatSig, "vincent_debug_data3",lineT, valid);
 printCsvValue(vincent_debug_data4, vincentFloatSig, "vincent_debug_data4",lineT, valid);
 printCsvValue(vincent_debug_data5, vincentFloatSig, "vincent_debug_data5",lineT, valid);
 printCsvValue(vincent_debug_data6, vincentFloatSig, "vincent_debug_data6",lineT, valid);
 printCsvValue(vincent_debug_data7, vincentFloatSig, "vincent_debug_data7",lineT, valid);
 printCsvValue(vincent_debug_data8, vincentFloatSig, "vincent_debug_data8",lineT, valid);
 printCsvValue(vincent_debug_data9, vincentFloatSig, "vincent_debug_data9",lineT, valid);
 printCsvValue(vincent_debug_data10, vincentFloatSig, "vincent_debug_data10",lineT, valid);
 printCsvValue(vincent_debug_data11, vincentFloatSig, "vincent_debug_data11",lineT, valid);
 printCsvValue(vincent_debug_data12, vincentFloatSig, "vincent_debug_data12",lineT, valid);
 printCsvValue(vincent_debug_data13, vincentFloatSig, "vincent_debug_data13",lineT, valid);
 printCsvValue(vincent_debug_data14, vincentFloatSig, "vincent_debug_data14",lineT, valid);
 printCsvValue(vincent_debug_data15, vincentFloatSig, "vincent_debug_data15",lineT, valid);
 printCsvValue(vincent_debug_data16, vincentFloatSig, "vincent_debug_data16",lineT, valid);
 printCsvValue(vincent_debug_data17, vincentFloatSig, "vincent_debug_data17",lineT, valid);
 printCsvValue(vincent_debug_data18, vincentFloatSig, "vincent_debug_data18",lineT, valid);
 printCsvValue(vincent_debug_data19, vincentFloatSig, "vincent_debug_data19",lineT, valid);
 printCsvValue(vincent_debug_data20, vincentFloatSig, "vincent_debug_data20",lineT, valid);
 printCsvValue(vincent_debug_data21, vincentFloatSig, "vincent_debug_data21",lineT, valid);
 printCsvValue(vincent_debug_data22, vincentFloatSig, "vincent_debug_data22",lineT, valid);
 printCsvValue(vincent_debug_data23, vincentFloatSig, "vincent_debug_data23",lineT, valid);
 printCsvValue(vincent_debug_data24, vincentFloatSig, "vincent_debug_data24",lineT, valid);
 printCsvValue(vincent_debug_data25, vincentFloatSig, "vincent_debug_data25",lineT, valid);
 printCsvValue(vincent_debug_data26, vincentFloatSig, "vincent_debug_data26",lineT, valid);
 printCsvValue(vincent_debug_data27, vincentFloatSig, "vincent_debug_data27",lineT, valid);
 printCsvValue(vincent_debug_data28, vincentFloatSig, "vincent_debug_data28",lineT, valid);
 printCsvValue(vincent_debug_data29, vincentFloatSig, "vincent_debug_data29",lineT, valid);
 printCsvValue(vincent_debug_data30, vincentFloatSig, "vincent_debug_data30",lineT, valid);
 printCsvValue(vincent_debug_data31, vincentFloatSig, "vincent_debug_data31",lineT, valid);
 printCsvValue(vincent_debug_data32, vincentFloatSig, "vincent_debug_data32",lineT, valid);
 printCsvValue(vincent_debug_data33, vincentFloatSig, "vincent_debug_data33",lineT, valid);
 printCsvValue(vincent_debug_data34, vincentFloatSig, "vincent_debug_data34",lineT, valid);
 printCsvValue(vincent_debug_data35, vincentFloatSig, "vincent_debug_data35",lineT, valid);
 printCsvValue(vincent_debug_data36, vincentFloatSig, "vincent_debug_data36",lineT, valid);
 printCsvValue(vincent_debug_data37, vincentFloatSig, "vincent_debug_data37",lineT, valid);
 printCsvValue(vincent_debug_data38, vincentFloatSig, "vincent_debug_data38",lineT, valid);
 printCsvValue(vincent_debug_data39, vincentFloatSig, "vincent_debug_data39",lineT, valid);
 printCsvValue(vincent_debug_data40, vincentFloatSig, "vincent_debug_data40",lineT, valid);
 printCsvValue(vincent_debug_data41, vincentFloatSig, "vincent_debug_data41",lineT, valid);
 printCsvValue(vincent_debug_data42, vincentFloatSig, "vincent_debug_data42",lineT, valid);
 printCsvValue(vincent_debug_data43, vincentFloatSig, "vincent_debug_data43",lineT, valid);
 printCsvValue(vincent_debug_data44, vincentFloatSig, "vincent_debug_data44",lineT, valid);
 printCsvValue(vincent_debug_data45, vincentFloatSig, "vincent_debug_data45",lineT, valid);
 printCsvValue(vincent_debug_data46, vincentFloatSig, "vincent_debug_data46",lineT, valid);
 printCsvValue(vincent_debug_data47, vincentFloatSig, "vincent_debug_data47",lineT, valid);
 printCsvValue(vincent_debug_data48, vincentFloatSig, "vincent_debug_data48",lineT, valid);
 printCsvValue(vincent_debug_data49, vincentFloatSig, "vincent_debug_data49",lineT, valid);
 printCsvValue(vincent_debug_data50, vincentFloatSig, "vincent_debug_data50",lineT, valid);
 printCsvValue(vincent_debug_data51, vincentFloatSig, "vincent_debug_data51",lineT, valid);
 printCsvValue(vincent_debug_data52, vincentFloatSig, "vincent_debug_data52",lineT, valid);
 printCsvValue(vincent_debug_data53, vincentFloatSig, "vincent_debug_data53",lineT, valid);
 printCsvValue(vincent_debug_data54, vincentFloatSig, "vincent_debug_data54",lineT, valid);
 printCsvValue(vincent_debug_data55, vincentFloatSig, "vincent_debug_data55",lineT, valid);
 printCsvValue(vincent_debug_data56, vincentFloatSig, "vincent_debug_data56",lineT, valid);
 printCsvValue(vincent_debug_data57, vincentFloatSig, "vincent_debug_data57",lineT, valid);
 printCsvValue(vincent_debug_data58, vincentFloatSig, "vincent_debug_data58",lineT, valid);
 printCsvValue(vincent_debug_data59, vincentFloatSig, "vincent_debug_data59",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
