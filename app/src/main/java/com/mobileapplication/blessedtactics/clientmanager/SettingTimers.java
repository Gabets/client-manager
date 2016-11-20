package com.mobileapplication.blessedtactics.clientmanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.mobileapplication.blessedtactics.clientmanager.alerts.DialogAddTimer;
import com.mobileapplication.blessedtactics.clientmanager.alerts.DialogDeleteTimer;
import com.mobileapplication.blessedtactics.clientmanager.alerts.SetAlarm;
import com.mobileapplication.blessedtactics.clientmanager.db.DBHelperTimers;

public class SettingTimers extends AppCompatActivity {

    private DBHelperTimers dbHelperTimers;
    private SQLiteDatabase db;
    private Cursor cursor;

    private SetAlarm sAlarm = new SetAlarm();

    private long idDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_timers);

        dbHelperTimers = new DBHelperTimers(this);
        dbShow();
    }

    private void dbShow() {
        db = dbHelperTimers.getReadableDatabase();

        String[] from = new String[] {DBHelperTimers.COLUMN_HOURS, DBHelperTimers.COLUMN_MINUTES};
        int[] to = new int[] {R.id.tvTimerHours, R.id.tvTimerMinutes};

        cursor = db.query(DBHelperTimers.DB_TABLE,
                new String[]{DBHelperTimers.COLUMN_ID, DBHelperTimers.COLUMN_HOURS, DBHelperTimers.COLUMN_MINUTES},
                null, null, null, null, DBHelperTimers.COLUMN_HOURS + ", " + DBHelperTimers.COLUMN_MINUTES);
        SimpleCursorAdapter scAdapter = new SimpleCursorAdapter(this, R.layout.item_timer, cursor, from, to, 0);


        ListView lvTimers = (ListView) findViewById(R.id.lvTimers);

        lvTimers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idDelete = id;
                DialogDeleteTimer deleteTimer = new DialogDeleteTimer();
                deleteTimer.show(getFragmentManager(), "deleteTimer");
            }
        });

        lvTimers.setAdapter(scAdapter);
    }

    public void onClickAddTimer(View view) {
        DialogAddTimer addTimer = new DialogAddTimer();
        addTimer.show(getFragmentManager(), "addTimer");
    }

    public void onClickSave(int hours, int minutes) {
        ContentValues cv = new ContentValues();
        db = dbHelperTimers.getWritableDatabase();
        if (hours < 10) { cv.put(DBHelperTimers.COLUMN_HOURS, "0" + hours); }
        else { cv.put(DBHelperTimers.COLUMN_HOURS, hours); }
        if (minutes < 10) { cv.put(DBHelperTimers.COLUMN_MINUTES, "0" + minutes); }
        else { cv.put(DBHelperTimers.COLUMN_MINUTES, minutes); }

        if (db.insert(DBHelperTimers.DB_TABLE, null, cv) != -1) {
            Toast.makeText(this, R.string.input_timer_success, Toast.LENGTH_SHORT)
                    .show();
        }
        sAlarm.setAlarm(getApplicationContext());
        dbShow();
    }

    public  void onClickDelete() {
        db.delete(DBHelperTimers.DB_TABLE, DBHelperTimers.COLUMN_ID + "=" + idDelete, null);
        Toast.makeText(this, R.string.timer_deleted_toast, Toast.LENGTH_SHORT)
                .show();
        dbShow();
        sAlarm.setAlarm(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        db.close();
        dbHelperTimers.close();
        super.onDestroy();
    }
}
