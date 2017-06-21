package com.ultra.manager.lights;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.ultra.manager.R;

public class LedActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LedFragment()).commit();
    }
}
