package com.ultra.manager;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.ultra.manager.utils.SettingsPreferenceFragment;


public class SystemTweaker extends SettingsPreferenceFragment implements
        PreferenceScreen.OnPreferenceClickListener {

    private String KEY_TRAFFIC = "traffic";
    private PreferenceScreen mtraffic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_tweaker);
        mtraffic = (PreferenceScreen) findPreference(KEY_TRAFFIC);
        mtraffic.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        return false;
    }
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {

        return super.onPreferenceTreeClick(preference);
    }

}
