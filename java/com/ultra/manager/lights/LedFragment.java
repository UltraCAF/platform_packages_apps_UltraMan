package com.ultra.manager.lights;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import com.ultra.manager.widgets.UltraSystemSettingSwitchPreference;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.internal.logging.MetricsLogger;

public class LedFragment extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SwitchPreference mNotificationPulse;
    private SwitchPreference mBatLight;
    public static final String BATTERY_LIGHT_ENABLED = "battery_light_enabled";
    private static final String KEY_NOTIFICATION_PULSE = "notification_pulse";
    private static final String KEY_NOTIFICATION_PULSE_CATE = "notification_pulse_category";

    private static final String TAG = "LightPerfs";

    public static LedFragment newInstance() {
        LedFragment lightsFragment = new LedFragment();
        return lightsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perf_lights);

        mBatLight = (SwitchPreference) getPreferenceScreen().findPreference(BATTERY_LIGHT_ENABLED);
        mBatLight.setOnPreferenceChangeListener(this);

        mNotificationPulse = (SwitchPreference) getPreferenceScreen().findPreference(KEY_NOTIFICATION_PULSE);
        mNotificationPulse.setOnPreferenceChangeListener(this);

        PreferenceGroup NotifGroup = (PreferenceGroup) findPreference(KEY_NOTIFICATION_PULSE_CATE);

        try {
           mBatLight.setChecked(Settings.System.getInt(getContext().getContentResolver(),
                   Settings.System.BATTERY_LIGHT_ENABLED) == 1);
        } catch (Settings.SettingNotFoundException snfe) {
            Log.e(TAG, Settings.System.BATTERY_LIGHT_ENABLED + " not found");
        }

        // Pulsing Lights Options
        if (!getResources()
                .getBoolean(com.android.internal.R.bool.config_intrusiveNotificationLed)) {
            if (NotifGroup != null) {
               NotifGroup.removePreference(mNotificationPulse);
               getPreferenceScreen().removePreference(NotifGroup);
            }
        } else {
              mNotificationPulse.setChecked(Settings.System.getInt(getActivity().getContentResolver(),
                     Settings.System.NOTIFICATION_LIGHT_PULSE, 0) == 1);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mBatLight){
                boolean value = (Boolean) newValue;
                Settings.System.putInt(getContext().getContentResolver(),
                        Settings.System.BATTERY_LIGHT_ENABLED,
                        value ? 1 : 0);
                return true;
        }
        if (preference == mNotificationPulse){
                boolean value = (Boolean) newValue;
                Settings.System.putInt(getContext().getContentResolver(),
                        Settings.System.NOTIFICATION_LIGHT_PULSE,
                        value ? 1 : 0);
                return true;
        }
        return false;
    }
    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }
}


