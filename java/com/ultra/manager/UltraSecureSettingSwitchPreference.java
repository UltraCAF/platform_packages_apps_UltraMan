
package com.ultra.manager;

import android.content.Context;
import android.preference.SwitchPreference;
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
}
