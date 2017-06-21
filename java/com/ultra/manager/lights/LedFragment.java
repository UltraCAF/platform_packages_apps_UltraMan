package com.ultra.manager.lights;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultra.manager.R;
import com.ultra.manager.utils.SettingsPreferenceFragment;

public class LedFragment extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

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
        mNotificationPulse = (SwitchPreference) getPreferenceScreen().findPreference(KEY_NOTIFICATION_PULSE);
        PreferenceGroup NotifGroup = (PreferenceGroup) findPreference(KEY_NOTIFICATION_PULSE_CATE);

        // Battery Lights Options
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
          try {
              mNotificationPulse.setChecked(Settings.System.getInt(getContext().getContentResolver(),
                      Settings.System.NOTIFICATION_LIGHT_PULSE) == 1);
          } catch (Settings.SettingNotFoundException snfe) {
              Log.e(TAG, Settings.System.NOTIFICATION_LIGHT_PULSE + " not found");
          }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
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
}

