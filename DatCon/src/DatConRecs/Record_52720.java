package src.DatConRecs;

import src.Files.ConvertDat;
import src.Files.DatConLog;
import src.Files.Signal;
import src.Files.Units;
import src.Files.ConvertDat.lineType;

public class Record_52720 extends Record {
    protected boolean valid = false;

    protected int cpu_usage_pcnt = (int) 0;

    protected int cpu_usage_task_b_pcnt = (int) 0;

    protected int cpu_usage_task_a_pcnt = (int) 0;

    protected int uc_resv0 = (int) 0;

    protected int max_time_workqueue = (int) 0;

    protected int max_time_task_b = (int) 0;

    protected int max_time_task_a = (int) 0;

    protected int max_time_task_c = (int) 0;

    protected int max_time_task_d = (int) 0;

    protected int uc_resv1 = (int) 0;

    protected int stack_usage_irq_pcnt = (int) 0;

    protected int stack_usage_workqueue_pcnt = (int) 0;

    protected int stack_usage_b_pcnt = (int) 0;

    protected int stack_usage_a_pcnt = (int) 0;

    protected int stack_usage_c_pcnt = (int) 0;

    protected int stack_usage_d_pcnt = (int) 0;

    protected int uc_resv2 = (int) 0;

    protected int pend_cnt_a = (int) 0;

    protected int pend_cnt_b = (int) 0;

    protected int pend_cnt_c = (int) 0;

    protected int pend_cnt_d = (int) 0;

    public Record_52720(ConvertDat convertDat) {
        super(convertDat, 52720, 42);
    }

    @Override
    public void process(Payload _payload) {
        super.process(_payload);
        valid = true;

        cpu_usage_pcnt = _payload.getUnsignedShort(0);
        cpu_usage_task_b_pcnt = _payload.getUnsignedShort(2);
        cpu_usage_task_a_pcnt = _payload.getUnsignedShort(4);
        uc_resv0 = _payload.getUnsignedShort(6);
        max_time_workqueue = _payload.getUnsignedShort(8);
        max_time_task_b = _payload.getUnsignedShort(10);
        max_time_task_a = _payload.getUnsignedShort(12);
        max_time_task_c = _payload.getUnsignedShort(14);
        max_time_task_d = _payload.getUnsignedShort(16);
        uc_resv1 = _payload.getUnsignedShort(18);
        stack_usage_irq_pcnt = _payload.getUnsignedShort(20);
        stack_usage_workqueue_pcnt = _payload.getUnsignedShort(22);
        stack_usage_b_pcnt = _payload.getUnsignedShort(24);
        stack_usage_a_pcnt = _payload.getUnsignedShort(26);
        stack_usage_c_pcnt = _payload.getUnsignedShort(28);
        stack_usage_d_pcnt = _payload.getUnsignedShort(30);
        uc_resv2 = _payload.getUnsignedShort(32);
        pend_cnt_a = _payload.getUnsignedShort(34);
        pend_cnt_b = _payload.getUnsignedShort(36);
        pend_cnt_c = _payload.getUnsignedShort(38);
        pend_cnt_d = _payload.getUnsignedShort(40);
    }

    protected static Signal uc_monitorIntSig = Signal
            .SeriesIntExperimental("uc_monitor", "", null, Units.noUnits);

    public void printCols(lineType lineT) {
        try {

            printCsvValue(cpu_usage_pcnt, uc_monitorIntSig, "cpu_usage_pcnt",
                    lineT, valid);
            printCsvValue(cpu_usage_task_b_pcnt, uc_monitorIntSig,
                    "cpu_usage_task_b_pcnt", lineT, valid);
            printCsvValue(cpu_usage_task_a_pcnt, uc_monitorIntSig,
                    "cpu_usage_task_a_pcnt", lineT, valid);
            printCsvValue(uc_resv0, uc_monitorIntSig, "uc_resv0", lineT, valid);
            printCsvValue(max_time_workqueue, uc_monitorIntSig,
                    "max_time_workqueue", lineT, valid);
            printCsvValue(max_time_task_b, uc_monitorIntSig, "max_time_task_b",
                    lineT, valid);
            printCsvValue(max_time_task_a, uc_monitorIntSig, "max_time_task_a",
                    lineT, valid);
            printCsvValue(max_time_task_c, uc_monitorIntSig, "max_time_task_c",
                    lineT, valid);
            printCsvValue(max_time_task_d, uc_monitorIntSig, "max_time_task_d",
                    lineT, valid);
            printCsvValue(uc_resv1, uc_monitorIntSig, "uc_resv1", lineT, valid);
            printCsvValue(stack_usage_irq_pcnt, uc_monitorIntSig,
                    "stack_usage_irq_pcnt", lineT, valid);
            printCsvValue(stack_usage_workqueue_pcnt, uc_monitorIntSig,
                    "stack_usage_workqueue_pcnt", lineT, valid);
            printCsvValue(stack_usage_b_pcnt, uc_monitorIntSig,
                    "stack_usage_b_pcnt", lineT, valid);
            printCsvValue(stack_usage_a_pcnt, uc_monitorIntSig,
                    "stack_usage_a_pcnt", lineT, valid);
            printCsvValue(stack_usage_c_pcnt, uc_monitorIntSig,
                    "stack_usage_c_pcnt", lineT, valid);
            printCsvValue(stack_usage_d_pcnt, uc_monitorIntSig,
                    "stack_usage_d_pcnt", lineT, valid);
            printCsvValue(uc_resv2, uc_monitorIntSig, "uc_resv2", lineT, valid);
            printCsvValue(pend_cnt_a, uc_monitorIntSig, "pend_cnt_a", lineT,
                    valid);
            printCsvValue(pend_cnt_b, uc_monitorIntSig, "pend_cnt_b", lineT,
                    valid);
            printCsvValue(pend_cnt_c, uc_monitorIntSig, "pend_cnt_c", lineT,
                    valid);
            printCsvValue(pend_cnt_d, uc_monitorIntSig, "pend_cnt_d", lineT,
                    valid);
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }

}
