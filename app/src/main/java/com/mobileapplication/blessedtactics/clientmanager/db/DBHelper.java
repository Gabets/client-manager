package com.mobileapplication.blessedtactics.clientmanager.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ClientManagerDB";
    public static final String DB_TABLE = "ClientTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_C_NAME = "clientName";
    public static final String COLUMN_C_ADDRESS = "clientAddress";
    public static final String COLUMN_C_CONTACT_NAME = "clientContactName";
    public static final String COLUMN_C_MOBILE = "clientMobile";
    public static final String COLUMN_C_ACTIVITY_TYPE = "clientActivityType";
    public static final String COLUMN_C_LAST_CALL = "clientLastCall";
    public static final String COLUMN_C_JURIDICAL_PHONE = "clientJuridicalPhone";
    public static final String COLUMN_C_EMAIL = "clientEmail";
    public static final String COLUMN_C_FAX = "clientFax";
    public static final String COLUMN_C_NOTES = "clientNotes";
    public static final String COLUMN_C_PRIORITY_LEVEL = "clientPriorityLevel";

    private static final String DB_CREATE = "create table " + DB_TABLE + "("
            + COLUMN_ID + " integer primary key autoincrement," + COLUMN_C_NAME + " text,"
            + COLUMN_C_ADDRESS + " text," + COLUMN_C_CONTACT_NAME + " text,"
            + COLUMN_C_MOBILE + " text," + COLUMN_C_ACTIVITY_TYPE + " text,"
            + COLUMN_C_LAST_CALL + " text," + COLUMN_C_JURIDICAL_PHONE + " text,"
            + COLUMN_C_EMAIL + " text," + COLUMN_C_FAX + " text," + COLUMN_C_NOTES + " text,"
            + COLUMN_C_PRIORITY_LEVEL + " text" + ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
