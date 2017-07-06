package com.ultra.manager;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ListAdapter;


import java.util.List;

import com.ultra.manager.R;

public class TweakerActivity extends PreferenceActivity {
    private static List<Header> _headers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(onIsMultiPane()) getIntent().putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, TweakerFragment.class.getName());
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean isValidFragment(String fragmentName) {
        return fragmentName.startsWith("com.ultra.manager");
    }
    @Override
    public void onBuildHeaders(List<Header> headers) {
        _headers = headers;
        loadHeadersFromResource(R.xml.headers_tweaker, headers);
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {

        if (adapter == null) {
            super.setListAdapter(null);
        } else {
            super.setListAdapter(new HeaderAdapter(this, _headers));
        }
    }
    @Override
    protected void onResume() {

        // Call super :
        super.onResume();

        // Select the displayed fragment in the headers (when using a tablet) :
        // This should be done by Android, it is a bug fix
        if(_headers != null) {

            final String displayedFragment = getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT);
            if (displayedFragment != null) {
                for (final Header header : _headers) {
                    if (displayedFragment.equals(header.fragment)) {
                        switchToHeader(header);
                        break;
                    }
                }
            }
        }
    }
}