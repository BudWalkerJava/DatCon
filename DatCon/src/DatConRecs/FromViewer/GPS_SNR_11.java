package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class GPS_SNR_11 extends Record {
protected boolean valid = false;

protected short gps_snr1 = (short)0;
protected short gps_snr2 = (short)0;
protected short gps_snr3 = (short)0;
protected short gps_snr4 = (short)0;
protected short gps_snr5 = (short)0;
protected short gps_snr6 = (short)0;
protected short gps_snr7 = (short)0;
protected short gps_snr8 = (short)0;
protected short gps_snr9 = (short)0;
protected short gps_snr10 = (short)0;
protected short gps_snr11 = (short)0;
protected short gps_snr12 = (short)0;
protected short gps_snr13 = (short)0;
protected short gps_snr14 = (short)0;
protected short gps_snr15 = (short)0;
protected short gps_snr16 = (short)0;
protected short gps_snr17 = (short)0;
protected short gps_snr18 = (short)0;
protected short gps_snr19 = (short)0;
protected short gps_snr20 = (short)0;
protected short gps_snr21 = (short)0;
protected short gps_snr22 = (short)0;
protected short gps_snr23 = (short)0;
protected short gps_snr24 = (short)0;
protected short gps_snr25 = (short)0;
protected short gps_snr26 = (short)0;
protected short gps_snr27 = (short)0;
protected short gps_snr28 = (short)0;
protected short gps_snr29 = (short)0;
protected short gps_snr30 = (short)0;
protected short gps_snr31 = (short)0;
protected short gps_snr32 = (short)0;
protected short gln_snr1 = (short)0;
protected short gln_snr2 = (short)0;
protected short gln_snr3 = (short)0;
protected short gln_snr4 = (short)0;
protected short gln_snr5 = (short)0;
protected short gln_snr6 = (short)0;
protected short gln_snr7 = (short)0;
protected short gln_snr8 = (short)0;
protected short gln_snr9 = (short)0;
protected short gln_snr10 = (short)0;
protected short gln_snr11 = (short)0;
protected short gln_snr12 = (short)0;
protected short gln_snr13 = (short)0;
protected short gln_snr14 = (short)0;
protected short gln_snr15 = (short)0;
protected short gln_snr16 = (short)0;
protected short gln_snr17 = (short)0;
protected short gln_snr18 = (short)0;
protected short gln_snr19 = (short)0;
protected short gln_snr20 = (short)0;
protected short gln_snr21 = (short)0;
protected short gln_snr22 = (short)0;
protected short gln_snr23 = (short)0;
protected short gln_snr24 = (short)0;
protected short gln_snr25 = (short)0;
protected short gln_snr26 = (short)0;
protected short gln_snr27 = (short)0;
protected short gln_snr28 = (short)0;
protected short gln_snr29 = (short)0;
protected short gln_snr30 = (short)0;
protected short gln_snr31 = (short)0;
protected short gln_snr32 = (short)0;
protected short gln_cnt = (short)0;

      public GPS_SNR_11(ConvertDat convertDat) {
           super(convertDat, 11, 65);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

gps_snr1 = _payload.getUnsignedByte(0);
gps_snr2 = _payload.getUnsignedByte(1);
gps_snr3 = _payload.getUnsignedByte(2);
gps_snr4 = _payload.getUnsignedByte(3);
gps_snr5 = _payload.getUnsignedByte(4);
gps_snr6 = _payload.getUnsignedByte(5);
gps_snr7 = _payload.getUnsignedByte(6);
gps_snr8 = _payload.getUnsignedByte(7);
gps_snr9 = _payload.getUnsignedByte(8);
gps_snr10 = _payload.getUnsignedByte(9);
gps_snr11 = _payload.getUnsignedByte(10);
gps_snr12 = _payload.getUnsignedByte(11);
gps_snr13 = _payload.getUnsignedByte(12);
gps_snr14 = _payload.getUnsignedByte(13);
gps_snr15 = _payload.getUnsignedByte(14);
gps_snr16 = _payload.getUnsignedByte(15);
gps_snr17 = _payload.getUnsignedByte(16);
gps_snr18 = _payload.getUnsignedByte(17);
gps_snr19 = _payload.getUnsignedByte(18);
gps_snr20 = _payload.getUnsignedByte(19);
gps_snr21 = _payload.getUnsignedByte(20);
gps_snr22 = _payload.getUnsignedByte(21);
gps_snr23 = _payload.getUnsignedByte(22);
gps_snr24 = _payload.getUnsignedByte(23);
gps_snr25 = _payload.getUnsignedByte(24);
gps_snr26 = _payload.getUnsignedByte(25);
gps_snr27 = _payload.getUnsignedByte(26);
gps_snr28 = _payload.getUnsignedByte(27);
gps_snr29 = _payload.getUnsignedByte(28);
gps_snr30 = _payload.getUnsignedByte(29);
gps_snr31 = _payload.getUnsignedByte(30);
gps_snr32 = _payload.getUnsignedByte(31);
gln_snr1 = _payload.getUnsignedByte(32);
gln_snr2 = _payload.getUnsignedByte(33);
gln_snr3 = _payload.getUnsignedByte(34);
gln_snr4 = _payload.getUnsignedByte(35);
gln_snr5 = _payload.getUnsignedByte(36);
gln_snr6 = _payload.getUnsignedByte(37);
gln_snr7 = _payload.getUnsignedByte(38);
gln_snr8 = _payload.getUnsignedByte(39);
gln_snr9 = _payload.getUnsignedByte(40);
gln_snr10 = _payload.getUnsignedByte(41);
gln_snr11 = _payload.getUnsignedByte(42);
gln_snr12 = _payload.getUnsignedByte(43);
gln_snr13 = _payload.getUnsignedByte(44);
gln_snr14 = _payload.getUnsignedByte(45);
gln_snr15 = _payload.getUnsignedByte(46);
gln_snr16 = _payload.getUnsignedByte(47);
gln_snr17 = _payload.getUnsignedByte(48);
gln_snr18 = _payload.getUnsignedByte(49);
gln_snr19 = _payload.getUnsignedByte(50);
gln_snr20 = _payload.getUnsignedByte(51);
gln_snr21 = _payload.getUnsignedByte(52);
gln_snr22 = _payload.getUnsignedByte(53);
gln_snr23 = _payload.getUnsignedByte(54);
gln_snr24 = _payload.getUnsignedByte(55);
gln_snr25 = _payload.getUnsignedByte(56);
gln_snr26 = _payload.getUnsignedByte(57);
gln_snr27 = _payload.getUnsignedByte(58);
gln_snr28 = _payload.getUnsignedByte(59);
gln_snr29 = _payload.getUnsignedByte(60);
gln_snr30 = _payload.getUnsignedByte(61);
gln_snr31 = _payload.getUnsignedByte(62);
gln_snr32 = _payload.getUnsignedByte(63);
gln_cnt = _payload.getUnsignedByte(64);
} catch (Exception e) {RecordException(e);}}


    protected static Signal GPS_SNRIntSig = Signal
.SeriesInt("GPS_SNR", "", null, Units.noUnits);
    protected static Signal GPS_SNRFloatSig = Signal
.SeriesFloat("GPS_SNR", "", null, Units.noUnits);
    protected static Signal GPS_SNRDoubleSig = Signal
.SeriesDouble("GPS_SNR", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(gps_snr1, GPS_SNRIntSig, "gps_snr1",lineT, valid);
 printCsvValue(gps_snr2, GPS_SNRIntSig, "gps_snr2",lineT, valid);
 printCsvValue(gps_snr3, GPS_SNRIntSig, "gps_snr3",lineT, valid);
 printCsvValue(gps_snr4, GPS_SNRIntSig, "gps_snr4",lineT, valid);
 printCsvValue(gps_snr5, GPS_SNRIntSig, "gps_snr5",lineT, valid);
 printCsvValue(gps_snr6, GPS_SNRIntSig, "gps_snr6",lineT, valid);
 printCsvValue(gps_snr7, GPS_SNRIntSig, "gps_snr7",lineT, valid);
 printCsvValue(gps_snr8, GPS_SNRIntSig, "gps_snr8",lineT, valid);
 printCsvValue(gps_snr9, GPS_SNRIntSig, "gps_snr9",lineT, valid);
 printCsvValue(gps_snr10, GPS_SNRIntSig, "gps_snr10",lineT, valid);
 printCsvValue(gps_snr11, GPS_SNRIntSig, "gps_snr11",lineT, valid);
 printCsvValue(gps_snr12, GPS_SNRIntSig, "gps_snr12",lineT, valid);
 printCsvValue(gps_snr13, GPS_SNRIntSig, "gps_snr13",lineT, valid);
 printCsvValue(gps_snr14, GPS_SNRIntSig, "gps_snr14",lineT, valid);
 printCsvValue(gps_snr15, GPS_SNRIntSig, "gps_snr15",lineT, valid);
 printCsvValue(gps_snr16, GPS_SNRIntSig, "gps_snr16",lineT, valid);
 printCsvValue(gps_snr17, GPS_SNRIntSig, "gps_snr17",lineT, valid);
 printCsvValue(gps_snr18, GPS_SNRIntSig, "gps_snr18",lineT, valid);
 printCsvValue(gps_snr19, GPS_SNRIntSig, "gps_snr19",lineT, valid);
 printCsvValue(gps_snr20, GPS_SNRIntSig, "gps_snr20",lineT, valid);
 printCsvValue(gps_snr21, GPS_SNRIntSig, "gps_snr21",lineT, valid);
 printCsvValue(gps_snr22, GPS_SNRIntSig, "gps_snr22",lineT, valid);
 printCsvValue(gps_snr23, GPS_SNRIntSig, "gps_snr23",lineT, valid);
 printCsvValue(gps_snr24, GPS_SNRIntSig, "gps_snr24",lineT, valid);
 printCsvValue(gps_snr25, GPS_SNRIntSig, "gps_snr25",lineT, valid);
 printCsvValue(gps_snr26, GPS_SNRIntSig, "gps_snr26",lineT, valid);
 printCsvValue(gps_snr27, GPS_SNRIntSig, "gps_snr27",lineT, valid);
 printCsvValue(gps_snr28, GPS_SNRIntSig, "gps_snr28",lineT, valid);
 printCsvValue(gps_snr29, GPS_SNRIntSig, "gps_snr29",lineT, valid);
 printCsvValue(gps_snr30, GPS_SNRIntSig, "gps_snr30",lineT, valid);
 printCsvValue(gps_snr31, GPS_SNRIntSig, "gps_snr31",lineT, valid);
 printCsvValue(gps_snr32, GPS_SNRIntSig, "gps_snr32",lineT, valid);
 printCsvValue(gln_snr1, GPS_SNRIntSig, "gln_snr1",lineT, valid);
 printCsvValue(gln_snr2, GPS_SNRIntSig, "gln_snr2",lineT, valid);
 printCsvValue(gln_snr3, GPS_SNRIntSig, "gln_snr3",lineT, valid);
 printCsvValue(gln_snr4, GPS_SNRIntSig, "gln_snr4",lineT, valid);
 printCsvValue(gln_snr5, GPS_SNRIntSig, "gln_snr5",lineT, valid);
 printCsvValue(gln_snr6, GPS_SNRIntSig, "gln_snr6",lineT, valid);
 printCsvValue(gln_snr7, GPS_SNRIntSig, "gln_snr7",lineT, valid);
 printCsvValue(gln_snr8, GPS_SNRIntSig, "gln_snr8",lineT, valid);
 printCsvValue(gln_snr9, GPS_SNRIntSig, "gln_snr9",lineT, valid);
 printCsvValue(gln_snr10, GPS_SNRIntSig, "gln_snr10",lineT, valid);
 printCsvValue(gln_snr11, GPS_SNRIntSig, "gln_snr11",lineT, valid);
 printCsvValue(gln_snr12, GPS_SNRIntSig, "gln_snr12",lineT, valid);
 printCsvValue(gln_snr13, GPS_SNRIntSig, "gln_snr13",lineT, valid);
 printCsvValue(gln_snr14, GPS_SNRIntSig, "gln_snr14",lineT, valid);
 printCsvValue(gln_snr15, GPS_SNRIntSig, "gln_snr15",lineT, valid);
 printCsvValue(gln_snr16, GPS_SNRIntSig, "gln_snr16",lineT, valid);
 printCsvValue(gln_snr17, GPS_SNRIntSig, "gln_snr17",lineT, valid);
 printCsvValue(gln_snr18, GPS_SNRIntSig, "gln_snr18",lineT, valid);
 printCsvValue(gln_snr19, GPS_SNRIntSig, "gln_snr19",lineT, valid);
 printCsvValue(gln_snr20, GPS_SNRIntSig, "gln_snr20",lineT, valid);
 printCsvValue(gln_snr21, GPS_SNRIntSig, "gln_snr21",lineT, valid);
 printCsvValue(gln_snr22, GPS_SNRIntSig, "gln_snr22",lineT, valid);
 printCsvValue(gln_snr23, GPS_SNRIntSig, "gln_snr23",lineT, valid);
 printCsvValue(gln_snr24, GPS_SNRIntSig, "gln_snr24",lineT, valid);
 printCsvValue(gln_snr25, GPS_SNRIntSig, "gln_snr25",lineT, valid);
 printCsvValue(gln_snr26, GPS_SNRIntSig, "gln_snr26",lineT, valid);
 printCsvValue(gln_snr27, GPS_SNRIntSig, "gln_snr27",lineT, valid);
 printCsvValue(gln_snr28, GPS_SNRIntSig, "gln_snr28",lineT, valid);
 printCsvValue(gln_snr29, GPS_SNRIntSig, "gln_snr29",lineT, valid);
 printCsvValue(gln_snr30, GPS_SNRIntSig, "gln_snr30",lineT, valid);
 printCsvValue(gln_snr31, GPS_SNRIntSig, "gln_snr31",lineT, valid);
 printCsvValue(gln_snr32, GPS_SNRIntSig, "gln_snr32",lineT, valid);
 printCsvValue(gln_cnt, GPS_SNRIntSig, "gln_cnt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
