package com.ultra.manager.about;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.ultra.manager.R;

public class AboutActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AboutFragment()).commit();
    }
}
