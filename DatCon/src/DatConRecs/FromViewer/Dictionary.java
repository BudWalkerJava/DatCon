package src.DatConRecs.FromViewer;

import java.util.Vector;
import src.Files.RecClassSpec;

public class Dictionary {
    public static Vector<RecClassSpec> entries = new Vector<RecClassSpec>();
    static {
        entries.add(new RecClassSpec(Controller_0.class, 0, 43));
        entries.add(new RecClassSpec(ofdm_cnt_1999.class, 1999, 10));
        entries.add(new RecClassSpec(uart_cnt_2000.class, 2000, 32));
        entries.add(new RecClassSpec(IMU_TAIL_2.class, 2, 40));
        entries.add(new RecClassSpec(drv_log_65530.class, 65530, 0));
        entries.add(new RecClassSpec(asr_115.class, 115, 4));
        entries.add(new RecClassSpec(IMU_ATTI_1.class, 1, 120));
        entries.add(new RecClassSpec(IMU_EX_3.class, 3, 60));
        entries.add(new RecClassSpec(IMU_TAIL_00_2080.class, 2080, 24));
        entries.add(new RecClassSpec(IMU_ATTI_00_2048.class, 2048, 120));
        entries.add(new RecClassSpec(IMU_EX_00_2064.class, 2064, 40));
        entries.add(new RecClassSpec(IMU_TAIL_01_2081.class, 2081, 24));
        entries.add(new RecClassSpec(IMU_ATTI_01_2049.class, 2049, 120));
        entries.add(new RecClassSpec(IMU_EX_01_2065.class, 2065, 40));
        entries.add(new RecClassSpec(IMU_TAIL_02_2082.class, 2082, 24));
        entries.add(new RecClassSpec(IMU_ATTI_02_2050.class, 2050, 120));
        entries.add(new RecClassSpec(IMU_EX_02_2066.class, 2066, 40));
        entries.add(new RecClassSpec(compass_4.class, 4, 8));
        entries.add(new RecClassSpec(GPS_GLNS_5.class, 5, 68));
        entries.add(new RecClassSpec(GPS_SNR_11.class, 11, 65));
        entries.add(new RecClassSpec(PT3_GPS_SNR_97.class, 97, 66));
        entries.add(new RecClassSpec(IMU_21100_91.class, 91, 16));
        entries.add(new RecClassSpec(imu_raw28_92.class, 92, 28, 30));
        entries.add(new RecClassSpec(imu_init_6.class, 6, 54));
        entries.add(new RecClassSpec(osd_general_12.class, 12, 49));
        entries.add(new RecClassSpec(osd_home_13.class, 13, 26));
        entries.add(new RecClassSpec(fdi_26.class, 26, 21));
        entries.add(new RecClassSpec(vincent_32771.class, 32771, 240));
        entries.add(new RecClassSpec(fly_log_32768.class, 32768, 0));
        entries.add(new RecClassSpec(SD_logs_65280.class, 65280, 0));
        entries.add(new RecClassSpec(svn_info_65534.class, 65534, 0));
        entries.add(new RecClassSpec(imu_data_7.class, 7, 48));
        entries.add(new RecClassSpec(imu_data_00_2160.class, 2160, 48));
        entries.add(new RecClassSpec(imu_data_01_2161.class, 2161, 48));
        entries.add(new RecClassSpec(imu_data_02_2162.class, 2162, 48));
        entries.add(new RecClassSpec(imu_cali_data_8.class, 8, 43));
        entries.add(new RecClassSpec(sensor_cfg_temp_9.class, 9, 36));
        entries.add(new RecClassSpec(temp_ctl_data_10.class, 10, 56));
        entries.add(new RecClassSpec(temp_ctl_data_00_2176.class, 2176, 44));
        entries.add(new RecClassSpec(temp_ctl_data_01_2177.class, 2177, 44));
        entries.add(new RecClassSpec(temp_ctl_data_02_2178.class, 2178, 44));
        entries.add(new RecClassSpec(PWM_OUTPUT_20.class, 20, 16));
        entries.add(new RecClassSpec(temp_bias_data_21.class, 21, 29));
        entries.add(new RecClassSpec(temp_cali_data_22.class, 22, 84));
        entries.add(new RecClassSpec(app_temp_bias_data_24.class, 24, 29));
        entries.add(new RecClassSpec(app_temp_cali_data_25.class, 25, 84));
        entries.add(new RecClassSpec(temp_cali_data_00_2195.class, 2195, 84));
        entries.add(
                new RecClassSpec(app_temp_cali_data_00_2198.class, 2198, 84));
        entries.add(new RecClassSpec(temp_cali_data_01_2196.class, 2196, 84));
        entries.add(
                new RecClassSpec(app_temp_cali_data_01_2199.class, 2199, 84));
        entries.add(new RecClassSpec(temp_cali_data_02_2197.class, 2197, 80));
        entries.add(
                new RecClassSpec(app_temp_cali_data_02_2200.class, 2200, 80));
        entries.add(new RecClassSpec(mpu6500_raw_data_35.class, 35, 24));
        entries.add(new RecClassSpec(adxl278_raw_data_36.class, 36, 12));
        entries.add(new RecClassSpec(svo_debug_101.class, 101, 32));
        entries.add(new RecClassSpec(uc_monitor_52720.class, 52720, 42));
        entries.add(new RecClassSpec(rc_delay_52735.class, 52735, 4));
        entries.add(new RecClassSpec(taskb_info_52738.class, 52738, 8));
        entries.add(new RecClassSpec(taska_info_52742.class, 52742, 8));
        entries.add(new RecClassSpec(taskc_info_52744.class, 52744, 8));
        entries.add(new RecClassSpec(taskd_info_52745.class, 52745, 8));
        entries.add(new RecClassSpec(rc_replay_52726.class, 52726, 16));
        entries.add(new RecClassSpec(escm_52721.class, 52721, 76));
        entries.add(new RecClassSpec(sweep_52722.class, 52722, 2));
        entries.add(new RecClassSpec(mvo_14.class, 14, 20));
        entries.add(new RecClassSpec(usonic_16.class, 16, 4));
        entries.add(new RecClassSpec(console_52719.class, 52719, 0));
        entries.add(new RecClassSpec(battery_info45_17.class, 17, 45, 47));
        entries.add(new RecClassSpec(special_cmd_23.class, 23, 10));
        entries.add(new RecClassSpec(serial_api_inputs_60.class, 60, 25));
        entries.add(new RecClassSpec(ctrl_vert_50.class, 50, 68));
        entries.add(new RecClassSpec(ctrl_horiz_51.class, 51, 121));
        entries.add(new RecClassSpec(ctrl_atti_52.class, 52, 200));
        entries.add(new RecClassSpec(ctrl_ccpm_53.class, 53, 49));
        entries.add(new RecClassSpec(ctrl_motor_54.class, 54, 35));
        entries.add(new RecClassSpec(wp_curve_150.class, 150, 33));
        entries.add(new RecClassSpec(smart_battery_info_18.class, 18, 70));
        entries.add(new RecClassSpec(airport_limit_data_40.class, 40, 84));
        entries.add(new RecClassSpec(fmu_device_run_time_41.class, 41, 64));
        entries.add(new RecClassSpec(hp_data_42.class, 42, 56));
        entries.add(new RecClassSpec(follow_me_data_43.class, 43, 139));
        entries.add(new RecClassSpec(imu_data_status_19.class, 19, 2));
        entries.add(
                new RecClassSpec(aircraft_condition_monitor_70.class, 70, 39));
        entries.add(new RecClassSpec(aircraft_model_80.class, 80, 128));
        entries.add(new RecClassSpec(go_home_info_90.class, 90, 13));
        entries.add(new RecClassSpec(new_mvo_feedback_29.class, 29, 80));
        entries.add(new RecClassSpec(svo_avoid_obstacle_100.class, 100, 11));
        entries.add(new RecClassSpec(rtkData_53233.class, 53233, 60));
        entries.add(new RecClassSpec(gear_debug_info_110.class, 110, 18));
        entries.add(new RecClassSpec(svo_ctrl_debug_102.class, 102, 84));
        entries.add(new RecClassSpec(waypoint_debug_160.class, 160, 4));
    }
}
