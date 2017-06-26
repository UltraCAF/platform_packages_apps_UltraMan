package com.ultra.manager.system.root;


import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;

import com.ultra.manager.R;
import com.ultra.manager.utils.CMDProcessor;
import com.ultra.manager.utils.SettingsPreferenceFragment;

import static com.ultra.manager.utils.TouchPanelChecker.TPanel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RootFragment extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SwitchPreference mdt2w;

    private String DOUBLETAP2WAKE = "dt2w";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perf_root);
        mdt2w = (SwitchPreference) findPreference(DOUBLETAP2WAKE);
        mdt2w.setOnPreferenceChangeListener(this);

        if (mdt2w != null){
            if (CMDProcessor.runSuCommand("cat " + TPanel().toString()).getStdout().contains("1")) {
                mdt2w.setChecked(true);
            }else {
                mdt2w.setChecked(false);
            }
        }
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mdt2w){
            boolean value = (Boolean) newValue;
            //if (value == 1){
            CMDProcessor.runSuCommand("echo " + (value ? 1 : 0) + " > " + TPanel());
            //}else{
            //CMDProcessor.runSuCommand("echo 0 > " + TPanel());
            //}
            return true;
        }
        return false;
    }
}

