
package com.ultra.manager.utils;

import android.content.Context;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.AttributeSet;

public class UltraSecureSettingSwitchPreference extends SwitchPreference {
    public UltraSecureSettingSwitchPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public UltraSecureSettingSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UltraSecureSettingSwitchPreference(Context context) {
        super(context, null);
    }

    @Override
    protected boolean persistBoolean(boolean value) {
        if (shouldPersist()) {
            if (value == getPersistedBoolean(!value)) {
                // It's already there, so the same as persisting
                return true;
            }
            Settings.Secure.putInt(getContext().getContentResolver(), getKey(), value ? 1 : 0);
            return true;
        }
        return false;
    }

    @Override
    protected boolean getPersistedBoolean(boolean defaultReturnValue) {
        if (!shouldPersist()) {
            return defaultReturnValue;
        }
        return Settings.Secure.getInt(getContext().getContentResolver(),
                getKey(), defaultReturnValue ? 1 : 0) != 0;
    }

    protected boolean isPersisted() {
        return Settings.Secure.getString(getContext().getContentResolver(), getKey()) != null;
    }
}
