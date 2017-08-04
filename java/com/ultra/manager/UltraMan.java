package com.ultra.manager;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ListAdapter;


import java.util.List;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.Utils;

public class UltraMan extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);
    }
    protected int getMetricsCategory() {
            return MetricsEvent.CAF;
    }

}
