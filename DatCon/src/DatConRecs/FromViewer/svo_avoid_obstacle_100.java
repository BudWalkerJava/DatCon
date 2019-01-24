package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class svo_avoid_obstacle_100 extends Record {
protected boolean valid = false;

protected short SVO_stop_flag = (short)0;
protected int SVO_p_front = (int)0;
protected int SVO_p_right = (int)0;
protected int SVO_p_back = (int)0;
protected int SVO_p_left = (int)0;
protected short SVO_v_limit = (short)0;
protected short SVO_cnt = (short)0;

      public svo_avoid_obstacle_100(ConvertDat convertDat) {
           super(convertDat, 100, 11);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

SVO_stop_flag = _payload.getUnsignedByte(0);
 SVO_p_front = _payload.getUnsignedShort(1);
 SVO_p_right = _payload.getUnsignedShort(3);
 SVO_p_back = _payload.getUnsignedShort(5);
 SVO_p_left = _payload.getUnsignedShort(7);
SVO_v_limit = _payload.getUnsignedByte(9);
SVO_cnt = _payload.getUnsignedByte(10);
} catch (Exception e) {RecordException(e);}}


    protected static Signal svo_avoid_obstacleIntSig = Signal
.SeriesInt("svo_avoid_obstacle", "", null, Units.noUnits);
    protected static Signal svo_avoid_obstacleFloatSig = Signal
.SeriesFloat("svo_avoid_obstacle", "", null, Units.noUnits);
    protected static Signal svo_avoid_obstacleDoubleSig = Signal
.SeriesDouble("svo_avoid_obstacle", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(SVO_stop_flag, svo_avoid_obstacleIntSig, "SVO_stop_flag",lineT, valid);
 printCsvValue(SVO_p_front, svo_avoid_obstacleIntSig, "SVO_p_front",lineT, valid);
 printCsvValue(SVO_p_right, svo_avoid_obstacleIntSig, "SVO_p_right",lineT, valid);
 printCsvValue(SVO_p_back, svo_avoid_obstacleIntSig, "SVO_p_back",lineT, valid);
 printCsvValue(SVO_p_left, svo_avoid_obstacleIntSig, "SVO_p_left",lineT, valid);
 printCsvValue(SVO_v_limit, svo_avoid_obstacleIntSig, "SVO_v_limit",lineT, valid);
 printCsvValue(SVO_cnt, svo_avoid_obstacleIntSig, "SVO_cnt",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
