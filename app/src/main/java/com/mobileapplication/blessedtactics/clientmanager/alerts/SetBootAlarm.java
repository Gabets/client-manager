package com.mobileapplication.blessedtactics.clientmanager.alerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class SetBootAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Setting Timers", "SetBootAlarm onReceive() begin");
        SetAlarm sAlarm = new SetAlarm();
        sAlarm.setAlarm(context);
        Log.e("Setting Timers", "SetBootAlarm onReceive() end");
    }
}
