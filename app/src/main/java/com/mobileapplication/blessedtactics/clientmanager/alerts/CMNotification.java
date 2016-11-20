package com.mobileapplication.blessedtactics.clientmanager.alerts;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.NotificationCompat;

import com.mobileapplication.blessedtactics.clientmanager.MainActivity;
import com.mobileapplication.blessedtactics.clientmanager.R;
import com.mobileapplication.blessedtactics.clientmanager.db.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CMNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Date date = new Date();
        SimpleDateFormat simpleDFormat = new SimpleDateFormat("yyyy.MM.dd");
        String calendarString = simpleDFormat.format(date);

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.DB_TABLE, new String[] {DBHelper.COLUMN_C_LAST_CALL,
                        DBHelper.COLUMN_C_PRIORITY_LEVEL, DBHelper.COLUMN_C_NAME},
                DBHelper.COLUMN_C_PRIORITY_LEVEL + " = '" + context.getString(R.string.rbAction) +
                        "' AND " + DBHelper.COLUMN_C_LAST_CALL + " < '" + calendarString + "'",
                null, null, null, null);

        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                sb.append(cursor.getString(2)).append("; ");

            } while (cursor.moveToNext());

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setContentTitle(context.getResources().getString(R.string.notifContact))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentText(sb.toString());

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intentOnReceive = new Intent(context, MainActivity.class);
            PendingIntent pendingIntentOnReceive = PendingIntent.getActivity(context, 0, intentOnReceive, 0);
            mBuilder.setContentIntent(pendingIntentOnReceive);

            Notification notification = mBuilder.build();
            notification.flags |= Notification.FLAG_INSISTENT;
            notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;

            mNotificationManager.notify(1, notification);
        }

        cursor.close();
        db.close();
        dbHelper.close();
    }
}
