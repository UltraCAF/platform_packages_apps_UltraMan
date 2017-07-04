package com.ultra.manager;

import android.os.Bundle;

import com.ultra.manager.utils.SettingsPreferenceFragment;

import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;

import com.ultra.manager.widgets.UltraSystemSettingSwitchPreference;

public class TweakerFragment extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SwitchPreference mScreenShutter;

    private static final String KEY_SCREENSHOT_SOUND = "screenshot_sound";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String settings;
        if(getArguments() != null) settings = getArguments().getString("settings");

        mScreenShutter = (UltraSystemSettingSwitchPreference) getPreferenceScreen().findPreference(KEY_SCREENSHOT_SOUND);
        mScreenShutter.setOnPreferenceChangeListener(this);
        mScreenShutter.setChecked(Settings.System.getInt(getActivity().getContentResolver(),
                 Settings.System.SCREENSHOT_SOUND, 0) == 1);
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mScreenShutter){
                boolean value = (Boolean) newValue;
                Settings.System.putInt(getContext().getContentResolver(),
                        Settings.System.SCREENSHOT_SOUND,
                        value ? 1 : 0);
                return true;
        }
        return false;
    }
}
