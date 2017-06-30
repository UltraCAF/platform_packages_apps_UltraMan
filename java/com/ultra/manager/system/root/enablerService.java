package com.ultra.manager.system.root;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.preference.PreferenceManager;

import com.ultra.manager.utils.CMDProcessor;
import com.ultra.manager.utils.rootChecker;

import static com.ultra.manager.utils.TouchPanelChecker.TPanel;

public class enablerService extends Service {

    private String DOUBLETAP2WAKE = "dt2w";
    private String ARCHPOWER = "archpower";
    private String MSMHOTPLUG = "msmhotplug";
    private String ALUCARD = "alucard";
    private String USBFASTCHARGE = "usbfastcharge";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
          if (rootChecker.isDeviceRooted() == true){
		if (prefs.getBoolean(DOUBLETAP2WAKE,false) == true) {
		    CMDProcessor.runSuCommand("echo 1 > " + TPanel());
		} else {
		    CMDProcessor.runSuCommand("echo 0 > " + TPanel());
		}
		if (prefs.getBoolean(ARCHPOWER,false) == true) {
		    CMDProcessor.runSuCommand("echo 1 > /sys/kernel/sched/arch_power");
		} else {
		    CMDProcessor.runSuCommand("echo 0 > /sys/kernel/sched/arch_power");
		}
		if (prefs.getBoolean(MSMHOTPLUG,false) == true) {
		    CMDProcessor.runSuCommand("echo 1 > /sys/module/msm_hotplug/msm_enabled");
		} else {
		    CMDProcessor.runSuCommand("echo 0 > /sys/module/msm_hotplug/msm_enabled");
		}
		if (prefs.getBoolean(ALUCARD,false) == true) {
		    CMDProcessor.runSuCommand("echo 1 > /sys/kernel/alucard_hotplug/hotplug_enable");
		} else {
		    CMDProcessor.runSuCommand("echo 0 > /sys/kernel/alucard_hotplug/hotplug_enable");
		}
		if (prefs.getBoolean(USBFASTCHARGE,false) == true) {
		    CMDProcessor.runSuCommand("echo 1 > /sys/kernel/fast_charge/force_fast_charge");
		} else {
		    CMDProcessor.runSuCommand("echo 0 > /sys/kernel/fast_charge/force_fast_charge");
		}
           }
        return START_NOT_STICKY;
    }

}
