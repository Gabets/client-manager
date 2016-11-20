package com.mobileapplication.blessedtactics.clientmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperTimers extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "TimersDB";
    public static final String DB_TABLE = "TimersTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_MINUTES = "minutes";

    private static final String DB_CREATE = "create table " + DB_TABLE + "("
            + COLUMN_ID + " integer primary key autoincrement," + COLUMN_HOURS + " text,"
            + COLUMN_MINUTES + " text" + ");";

    public DBHelperTimers(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
