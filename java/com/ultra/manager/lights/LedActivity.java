package com.ultra.manager.lights;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.settingslib.drawer.SettingsDrawerActivity;

import com.ultra.manager.R;

public class LedActivity extends SettingsDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new LedFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }
}
