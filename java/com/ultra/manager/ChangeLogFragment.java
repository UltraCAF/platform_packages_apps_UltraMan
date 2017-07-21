package com.ultra.manager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.SystemProperties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLogFragment extends SettingsPreferenceFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pref_changelog, container, false);

        TextView txtRomName = (TextView) view.findViewById (R.id.rom_name);
        TextView changelog = (TextView) view.findViewById(R.id.changelog);
        TextView txtRomVersion = (TextView) view.findViewById (R.id.rom_version);
        TextView txtCAFVersion = (TextView) view.findViewById (R.id.caf_version);

        try {txtRomName.setText (SystemProperties.get("ro.rom.name","ERROR"));} catch (RuntimeException e) {}
        try {txtRomVersion.setText (SystemProperties.get("ro.rom.version","ERROR"));} catch (RuntimeException e) {}
        try {txtCAFVersion.setText (SystemProperties.get("ro.caf.version","ERROR"));} catch (RuntimeException e) {}

        String path = "/system/etc";
        File file = new File(path,"changelog");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            changelog.setText(text);
        }
        catch (IOException e) {
        }
        return view;
    }
    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CAF;
    }
}
