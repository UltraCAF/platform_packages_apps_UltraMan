package com.ultra.manager.statusbar;

/**
 * Created by ahmedhady on 06/07/17.
 */

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.ultra.manager.R;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.internal.logging.MetricsLogger;

import com.ultra.manager.utils.SettingsPreferenceFragment;


public class statusbar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private PreferenceScreen traffic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.statusbar_tweaker);
        traffic = (PreferenceScreen) findPreference("traffic");
        traffic.setOnPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == traffic){
            preference.performClick();
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }

}
