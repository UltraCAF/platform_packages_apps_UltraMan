package com.ultra.manager.about;

import android.os.Bundle;
import android.os.SystemProperties;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.Utils;

public class AboutFragment extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String KEY_CAF_VERSION = "caf_version";

    private static final String TAG = "AboutPerfs";

    public static AboutFragment newInstance() {
        AboutFragment aboutFragment = new AboutFragment();
        return aboutFragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.perf_about);

        setValueSummary(KEY_CAF_VERSION, "ro.caf.version");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    private void setValueSummary(String preference, String property) {
        try {
            findPreference(preference).setSummary(
                    SystemProperties.get(property,
                            getResources().getString(R.string.device_info_default)));
        } catch (RuntimeException e) {
            // No recovery
        }
    }
    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }
}
