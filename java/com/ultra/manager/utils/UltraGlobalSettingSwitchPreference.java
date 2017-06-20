
package com.ultra.manager.utils;

import android.content.Context;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.AttributeSet;

public class UltraGlobalSettingSwitchPreference extends SwitchPreference {
    public UltraGlobalSettingSwitchPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public UltraGlobalSettingSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UltraGlobalSettingSwitchPreference(Context context) {
        super(context, null);
    }

    @Override
    protected boolean persistBoolean(boolean value) {
        if (shouldPersist()) {
            if (value == getPersistedBoolean(!value)) {
                // It's already there, so the same as persisting
                return true;
            }
            Settings.Global.putInt(getContext().getContentResolver(), getKey(), value ? 1 : 0);
            return true;
        }
        return false;
    }

    @Override
    protected boolean getPersistedBoolean(boolean defaultReturnValue) {
        if (!shouldPersist()) {
            return defaultReturnValue;
        }
        return Settings.Global.getInt(getContext().getContentResolver(),
                getKey(), defaultReturnValue ? 1 : 0) != 0;
    }

    protected boolean isPersisted() {
        // Using getString instead of getInt so we can simply check for null
        // instead of catching an exception. (All values are stored as strings.)
        return Settings.Global.getString(getContext().getContentResolver(), getKey()) != null;
    }
}
