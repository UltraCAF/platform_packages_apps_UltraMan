package com.ultra.manager.system.root;


import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;

import com.ultra.manager.R;
import com.ultra.manager.utils.CMDProcessor;
import com.ultra.manager.utils.SettingsPreferenceFragment;

import static com.ultra.manager.utils.TouchPanelChecker.TPanel;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragment extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SwitchPreference mdt2w;
    private SwitchPreference mArchPower;
    private SwitchPreference mMSMhotplug;
    private SwitchPreference mAluCard;
    private SwitchPreference mFastCharge;

    private String DOUBLETAP2WAKE = "dt2w";
    private String ARCHPOWER = "archpower";
    private String MSMHOTPLUG = "msmhotplug";
    private String ALUCARD = "alucard";
    private String USBFASTCHARGE = "usbfastcharge";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perf_root);

        mdt2w = (SwitchPreference) findPreference(DOUBLETAP2WAKE);
        mdt2w.setOnPreferenceChangeListener(this);

        mArchPower = (SwitchPreference) findPreference(ARCHPOWER);
        mArchPower.setOnPreferenceChangeListener(this);

        mMSMhotplug = (SwitchPreference) findPreference(MSMHOTPLUG);
        mMSMhotplug.setOnPreferenceChangeListener(this);

        mAluCard = (SwitchPreference) findPreference(ALUCARD);
        mAluCard.setOnPreferenceChangeListener(this);

        mFastCharge = (SwitchPreference) findPreference(USBFASTCHARGE);
        mFastCharge.setOnPreferenceChangeListener(this);

        if (mdt2w != null){
          if(TPanel().toString() != null){
            if (CMDProcessor.runSuCommand("cat " + TPanel().toString()).getStdout().contains("1")) {
                mdt2w.setChecked(true);
            }else {
                mdt2w.setChecked(false);
            }
           } else {
            mdt2w.setEnabled(false);
           }
        }

        if (mArchPower != null){
              if (new File("/sys/kernel/sched/arch_power").exists()) {
                 if (CMDProcessor.runSuCommand("cat /sys/kernel/sched/arch_power").getStdout().contains("1")) {
                    mArchPower.setChecked(true);
                 } else {
                    mArchPower.setChecked(false);
                 }
              } else {
               mArchPower.setEnabled(false);
              }
        }

        if (mMSMhotplug != null){
              if (new File("/sys/kernel/sched/arch_power").exists()) {
                 if (CMDProcessor.runSuCommand("cat /sys/module/msm_hotplug/msm_enabled").getStdout().contains("1")) {
                    mMSMhotplug.setChecked(true);
                 } else {
                    mMSMhotplug.setChecked(false);
                 }
              } else {
               mMSMhotplug.setEnabled(false);
              }
        }

        if (mMSMhotplug != null){
              if (new File("/sys/kernel/alucard_hotplug/hotplug_enable").exists()) {
                 if (CMDProcessor.runSuCommand("cat /sys/kernel/alucard_hotplug/hotplug_enable").getStdout().contains("1")) {
                    mAluCard.setChecked(true);
                 } else {
                    mAluCard.setChecked(false);
                 }
              } else {
               mAluCard.setEnabled(false);
              }
        }
        if (mFastCharge != null){
              if (new File("/sys/kernel/fast_charge/force_fast_charge").exists()) {
                 if (CMDProcessor.runSuCommand("cat /sys/kernel/fast_charge/force_fast_charge").getStdout().contains("1")) {
                    mFastCharge.setChecked(true);
                 } else {
                    mFastCharge.setChecked(false);
                 }
              } else {
               mFastCharge.setEnabled(false);
              }
        }
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mdt2w){
            boolean value = (Boolean) newValue;
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > " + TPanel());
            return true;
        }
        if (preference == mArchPower){
            boolean value = (Boolean) newValue;
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/sched/arch_power");
            return true;
        }
        if (preference == mMSMhotplug){
            boolean value = (Boolean) newValue;
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/module/msm_hotplug/msm_enabled");
            return true;
        }
        if (preference == mAluCard){
            boolean value = (Boolean) newValue;
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/alucard_hotplug/hotplug_enable");
            return true;
        }
        if (preference == mFastCharge){
            boolean value = (Boolean) newValue;
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/fast_charge/force_fast_charge");
            return true;
        }
        return false;
    }
}

