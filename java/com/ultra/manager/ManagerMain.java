package com.ultra.manager;

import android.preference.PreferenceActivity;

import java.util.List;

public class ManagerMain extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.perf_headers, target);
    }

    @Override
    public boolean isValidFragment(String fragmentName) {
        return fragmentName.startsWith("com.ultra.manager.manager");
    }
}
