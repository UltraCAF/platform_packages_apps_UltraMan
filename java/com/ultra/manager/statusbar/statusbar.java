package com.ultra.manager.statusbar;

/**
 * Created by ahmedhady on 06/07/17.
 */

import android.os.Bundle;
import android.support.v7.preference.Preference;
import com.ultra.manager.R;

import com.ultra.manager.utils.SettingsPreferenceFragment;


public class statusbar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.statusbar_tweaker);
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

}
