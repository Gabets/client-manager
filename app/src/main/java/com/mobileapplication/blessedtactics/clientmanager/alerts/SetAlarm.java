package com.mobileapplication.blessedtactics.clientmanager.alerts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mobileapplication.blessedtactics.clientmanager.db.DBHelperTimers;

import java.util.Calendar;


public class SetAlarm {

    public void setAlarm(Context context) {
        DBHelperTimers dbHelperTimers = new DBHelperTimers(context);
        SQLiteDatabase db = dbHelperTimers.getReadableDatabase();
        Cursor cursor = db.query(DBHelperTimers.DB_TABLE,
                new String[]{DBHelperTimers.COLUMN_ID, DBHelperTimers.COLUMN_HOURS, DBHelperTimers.COLUMN_MINUTES},
                null, null, null, null, DBHelperTimers.COLUMN_HOURS + ", " + DBHelperTimers.COLUMN_MINUTES);
        Log.e("Setting Timers", "SetAlarm setAlarm() " + cursor);

        if (cursor.moveToFirst()) {
            Log.e("Setting Timers", "SetAlarm cursor.moveToFirst");
            Calendar calendar;
            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            Intent intent = new Intent(context, CMNotification.class);
            int requestCode = 0;

            do {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(cursor.getString(1)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(cursor.getString(2)));
                calendar.set(Calendar.SECOND, 0);
                Log.e("Setting Timers", "SetAlarm calendar.set " + calendar.getTime());
                if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                    calendar.add(Calendar.DATE, 1);
                    Log.e("Setting Timers", "SetAlarm set getTimeInMillis " + calendar.getTime());
                }
                PendingIntent pi = PendingIntent.getBroadcast(context, ++requestCode, intent, 0);
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        dbHelperTimers.close();
    }
}
