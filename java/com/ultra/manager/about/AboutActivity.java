package com.ultra.manager.about;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentTransaction;

import com.ultra.manager.R;

import com.android.settingslib.drawer.SettingsDrawerActivity;

public class AboutActivity extends SettingsDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new AboutFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }
}
