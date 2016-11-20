package com.mobileapplication.blessedtactics.clientmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.mobileapplication.blessedtactics.clientmanager.db.DBCursorLoader;
import com.mobileapplication.blessedtactics.clientmanager.db.DBHelper;

public class SortActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int BY_TYPE = 1;
    private static final int BY_LAST_CALLS = 2;
    private static final int BY_NAMES = 3;

    private static final int LOADER_ID = 1;

    private SimpleCursorAdapter scAdapter;
    private static DBCursorLoader dbCursorLoader;

    private String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        Intent intent = getIntent();
        int sortBy = intent.getIntExtra("sortBy", BY_NAMES);
        String[] from;
        int[] to;

        switch (sortBy) {
            case BY_TYPE:
                from = new String[] { DBHelper.COLUMN_C_ACTIVITY_TYPE,
                        DBHelper.COLUMN_C_NAME };
                to = new int[] { R.id.tvQuery, R.id.tvName };
                orderBy = DBHelper.COLUMN_C_ACTIVITY_TYPE;
                break;
            case BY_LAST_CALLS:
                from = new String[] { DBHelper.COLUMN_C_LAST_CALL,
                        DBHelper.COLUMN_C_NAME };
                to = new int[] { R.id.tvQuery, R.id.tvName };
                orderBy = DBHelper.COLUMN_C_LAST_CALL;
                break;
            default:
                from = new String[] { DBHelper.COLUMN_C_NAME };
                to = new int[] { R.id.tvQuery };
                orderBy = DBHelper.COLUMN_C_NAME;
                
        }

        scAdapter = new SimpleCursorAdapter(this, R.layout.item_client, null,
                from, to, 0);

        ListView lvData = (ListView) findViewById(R.id.lvData);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent itemIntent = new Intent(SortActivity.this, ClientInfo.class);
                itemIntent.putExtra("id", dbCursorLoader.getItemByPosition(position));
                startActivity(itemIntent);
                finish();
            }
        });
        lvData.setAdapter(scAdapter);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        dbCursorLoader = new DBCursorLoader(this, orderBy);
        return dbCursorLoader;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }
}
