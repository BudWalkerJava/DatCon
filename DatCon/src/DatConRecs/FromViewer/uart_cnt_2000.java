package src.DatConRecs.FromViewer;
import src.DatConRecs.*;
import src.Files.ConvertDat;
import src.Files.ConvertDat.lineType;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;


public class uart_cnt_2000 extends Record {
protected boolean valid = false;

protected int uart_tx1 = (int)0;
protected int uart_rx1 = (int)0;
protected int uart_tx2 = (int)0;
protected int uart_rx2 = (int)0;
protected int uart_tx3 = (int)0;
protected int uart_rx3 = (int)0;
protected int uart_tx4 = (int)0;
protected int uart_rx4 = (int)0;
protected int uart_tx5 = (int)0;
protected int uart_rx5 = (int)0;
protected int uart_tx6 = (int)0;
protected int uart_rx6 = (int)0;
protected int uart_tx7 = (int)0;
protected int uart_rx7 = (int)0;
protected int uart_tx8 = (int)0;
protected int uart_rx8 = (int)0;

      public uart_cnt_2000(ConvertDat convertDat) {
           super(convertDat, 2000, 32);
       }

@Override
  public void process(Payload _payload) {
      super.process(_payload);
        try {
      valid = true;

 uart_tx1 = _payload.getUnsignedShort(0);
 uart_rx1 = _payload.getUnsignedShort(2);
 uart_tx2 = _payload.getUnsignedShort(4);
 uart_rx2 = _payload.getUnsignedShort(6);
 uart_tx3 = _payload.getUnsignedShort(8);
 uart_rx3 = _payload.getUnsignedShort(10);
 uart_tx4 = _payload.getUnsignedShort(12);
 uart_rx4 = _payload.getUnsignedShort(14);
 uart_tx5 = _payload.getUnsignedShort(16);
 uart_rx5 = _payload.getUnsignedShort(18);
 uart_tx6 = _payload.getUnsignedShort(20);
 uart_rx6 = _payload.getUnsignedShort(22);
 uart_tx7 = _payload.getUnsignedShort(24);
 uart_rx7 = _payload.getUnsignedShort(26);
 uart_tx8 = _payload.getUnsignedShort(28);
 uart_rx8 = _payload.getUnsignedShort(30);
} catch (Exception e) {RecordException(e);}}


    protected static Signal uart_cntIntSig = Signal
.SeriesInt("uart_cnt", "", null, Units.noUnits);
    protected static Signal uart_cntFloatSig = Signal
.SeriesFloat("uart_cnt", "", null, Units.noUnits);
    protected static Signal uart_cntDoubleSig = Signal
.SeriesDouble("uart_cnt", "", null, Units.noUnits);

   public void printCols(lineType lineT) {
try {

 printCsvValue(uart_tx1, uart_cntIntSig, "uart_tx1",lineT, valid);
 printCsvValue(uart_rx1, uart_cntIntSig, "uart_rx1",lineT, valid);
 printCsvValue(uart_tx2, uart_cntIntSig, "uart_tx2",lineT, valid);
 printCsvValue(uart_rx2, uart_cntIntSig, "uart_rx2",lineT, valid);
 printCsvValue(uart_tx3, uart_cntIntSig, "uart_tx3",lineT, valid);
 printCsvValue(uart_rx3, uart_cntIntSig, "uart_rx3",lineT, valid);
 printCsvValue(uart_tx4, uart_cntIntSig, "uart_tx4",lineT, valid);
 printCsvValue(uart_rx4, uart_cntIntSig, "uart_rx4",lineT, valid);
 printCsvValue(uart_tx5, uart_cntIntSig, "uart_tx5",lineT, valid);
 printCsvValue(uart_rx5, uart_cntIntSig, "uart_rx5",lineT, valid);
 printCsvValue(uart_tx6, uart_cntIntSig, "uart_tx6",lineT, valid);
 printCsvValue(uart_rx6, uart_cntIntSig, "uart_rx6",lineT, valid);
 printCsvValue(uart_tx7, uart_cntIntSig, "uart_tx7",lineT, valid);
 printCsvValue(uart_rx7, uart_cntIntSig, "uart_rx7",lineT, valid);
 printCsvValue(uart_tx8, uart_cntIntSig, "uart_tx8",lineT, valid);
 printCsvValue(uart_rx8, uart_cntIntSig, "uart_rx8",lineT, valid);
 } catch (Exception e) {
DatConLog.Exception(e);
}
}

   }
