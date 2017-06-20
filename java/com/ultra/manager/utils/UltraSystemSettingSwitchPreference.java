
package com.ultra.manager.utils;

import android.content.Context;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

public class UltraSystemSettingSwitchPreference extends SwitchPreference {
    public UltraSystemSettingSwitchPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public UltraSystemSettingSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UltraSystemSettingSwitchPreference(Context context) {
        super(context, null);
    }

    @Override
    protected boolean persistBoolean(boolean value) {
        if (shouldPersist()) {
            if (value == getPersistedBoolean(!value)) {
                // It's already there, so the same as persisting
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean getPersistedBoolean(boolean defaultReturnValue) {
        if (!shouldPersist()) {
            return defaultReturnValue;
        }
        return true;
    }

    protected boolean isPersisted() {
        // Using getString instead of getInt so we can simply check for null
        // instead of catching an exception. (All values are stored as strings.)
        return true;
    }
}
