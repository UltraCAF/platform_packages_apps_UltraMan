package com.ultra.manager.system;

/**
 * Created by ahmedhady on 06/07/17.
 */

import android.os.Bundle;
import android.provider.Settings;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import android.os.SystemProperties;

import com.ultra.manager.widgets.UltraSystemSettingSwitchPreference;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.internal.logging.MetricsLogger;

public class SystemSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SwitchPreference mScreenShutter;
    private ListPreference mScrollingCachePref;

    private static final String KEY_SCREENSHOT_SOUND = "screenshot_sound";

    private static final String SCROLLINGCACHE_PREF = "pref_scrollingcache";
    private static final String SCROLLINGCACHE_PERSIST_PROP = "persist.sys.scrollingcache";

    private static final String SCROLLINGCACHE_DEFAULT = "2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.system_tweaks);
        mScreenShutter = (UltraSystemSettingSwitchPreference) getPreferenceScreen().findPreference(KEY_SCREENSHOT_SOUND);
        mScreenShutter.setOnPreferenceChangeListener(this);

        try {
           mScreenShutter.setChecked(Settings.System.getInt(getContext().getContentResolver(),
                   Settings.System.SCREENSHOT_SOUND) == 1);
        } catch (Settings.SettingNotFoundException snfe) {
        }

        mScrollingCachePref = (ListPreference) findPreference(SCROLLINGCACHE_PREF);
        mScrollingCachePref.setValue(SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP,
                SystemProperties.get(SCROLLINGCACHE_PERSIST_PROP, SCROLLINGCACHE_DEFAULT)));
        mScrollingCachePref.setOnPreferenceChangeListener(this);
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

        if (preference == mScrollingCachePref) {
            if (newValue != null) {
                SystemProperties.set(SCROLLINGCACHE_PERSIST_PROP, (String) newValue);
            }
            return true;
        }
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }

}
