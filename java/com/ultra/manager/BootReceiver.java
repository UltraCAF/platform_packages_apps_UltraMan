package com.ultra.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ultra.manager.root.enablerService;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            context.startService(new Intent(context, enablerService.class));
        }
    }
}
