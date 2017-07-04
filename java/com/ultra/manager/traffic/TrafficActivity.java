package com.ultra.manager.traffic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.ultra.manager.R;

import com.android.settingslib.drawer.SettingsDrawerActivity;

public class TrafficActivity extends SettingsDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new Traffic())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commitAllowingStateLoss();
        getFragmentManager().executePendingTransactions();
    }
}
