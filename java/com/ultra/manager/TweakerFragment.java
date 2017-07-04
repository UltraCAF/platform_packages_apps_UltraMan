package com.ultra.manager;

import android.os.Bundle;

import com.ultra.manager.utils.SettingsPreferenceFragment;

public class TweakerFragment extends SettingsPreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String settings;
        if(getArguments() != null) settings = getArguments().getString("settings");
    }
}
