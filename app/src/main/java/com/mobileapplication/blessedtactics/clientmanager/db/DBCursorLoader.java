package com.mobileapplication.blessedtactics.clientmanager.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;


public class DBCursorLoader extends CursorLoader {

    private Cursor cursor;
    private String orderBy;
    private Context context;

    public DBCursorLoader(Context context, String orderBy) {
        super(context);
        this.context = context;
        this.orderBy = orderBy;
    }

    @Override
    public Cursor loadInBackground() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.query(DBHelper.DB_TABLE, new String[] { DBHelper.COLUMN_ID,
                        DBHelper.COLUMN_C_NAME, orderBy }, null, null, null, null,
                orderBy + " COLLATE NOCASE");
        return cursor;
    }

    public int getItemByPosition(int position) {
        cursor.moveToPosition(position);
        return cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
    }

}
