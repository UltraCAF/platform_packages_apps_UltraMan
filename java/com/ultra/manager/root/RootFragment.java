package com.ultra.manager.root;


import android.os.Bundle;
import android.support.v7.preference.PreferenceCategory;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.Utils;

import com.ultra.manager.utils.CMDProcessor;
import com.ultra.manager.utils.rootChecker;

import java.io.File;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.internal.logging.MetricsLogger;

import static com.ultra.manager.utils.TouchPanelChecker.TPanel;

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
    private SwitchPreference mROOTENABLE;
    private ListPreference mGoverList;
    private PreferenceCategory mAPPCategory;

    private String DOUBLETAP2WAKE = "dt2w";
    private String ARCHPOWER = "archpower";
    private String MSMHOTPLUG = "msmhotplug";
    private String ALUCARD = "alucard";
    private String USBFASTCHARGE = "usbfastcharge";
    private String ROOT_ENABLER = "root_enable";
    private String SYSTEM_APP = "systemappremover_key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perf_root);

        mROOTENABLE = (SwitchPreference) findPreference(ROOT_ENABLER);

        mGoverList = (ListPreference) findPreference("govers");
        mGoverList.setOnPreferenceChangeListener(this);

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

        mAPPCategory = (PreferenceCategory) findPreference(SYSTEM_APP);

        if (rootChecker.isDeviceRooted() == true) {
            mROOTENABLE.setEnabled(true);
            if (mdt2w != null) {
                if (TPanel().toString() != null) {
                    if (CMDProcessor.runSuCommand("cat " + TPanel().toString()).getStdout().contains("1")) {
                        mdt2w.setChecked(true);
                    } else {
                        mdt2w.setChecked(false);
                    }
                } else {
                    mdt2w.setEnabled(false);
                }
            }

            if (mArchPower != null) {
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

            if (mMSMhotplug != null) {
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

            if (mMSMhotplug != null) {
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
            if (mFastCharge != null) {
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
            if (mGoverList != null) {
                mGoverList.setValue(CMDProcessor.runSuCommand("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor").getStdout());
            }
            mAPPCategory.setEnabled(true);
        }else{
            mdt2w.setEnabled(false);
            mArchPower.setEnabled(false);
            mAluCard.setEnabled(false);
            mMSMhotplug.setEnabled(false);
            mFastCharge.setEnabled(false);
            mGoverList.setEnabled(false);
            mROOTENABLE.setEnabled(false);
            mAPPCategory.setEnabled(false);
        }
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (rootChecker.isDeviceRooted() == true) {
            if (preference == mdt2w) {
                boolean value = (Boolean) newValue;
                CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > " + TPanel());
                return true;
            }
            if (preference == mArchPower) {
                boolean value = (Boolean) newValue;
                CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/sched/arch_power");
                return true;
            }
            if (preference == mMSMhotplug) {
                boolean value = (Boolean) newValue;
                CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/module/msm_hotplug/msm_enabled");
                if (value == true) {
                    mAluCard.setEnabled(false);
                } else {
                    mAluCard.setEnabled(true);
                }
                return true;
            }
            if (preference == mAluCard) {
                boolean value = (Boolean) newValue;
                CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/alucard_hotplug/hotplug_enable");
                if (value == true) {
                    mMSMhotplug.setEnabled(false);
                } else {
                    mMSMhotplug.setEnabled(true);
                }
                return true;
            }
            if (preference == mFastCharge) {
                boolean value = (Boolean) newValue;
                CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > /sys/kernel/fast_charge/force_fast_charge");
                return true;
            }
            if (preference == mGoverList){
                CMDProcessor.runSuCommand("echo " + newValue.toString() + " > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
                return true;
            }
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }
}

