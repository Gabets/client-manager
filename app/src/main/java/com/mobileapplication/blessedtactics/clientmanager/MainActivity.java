package com.mobileapplication.blessedtactics.clientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int BY_TYPE = 1;
    private static final int BY_LAST_CALLS = 2;
    private static final int BY_NAMES = 3;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnSort(View view) {
        intent = new Intent(this, SortActivity.class);
        switch (view.getId()) {
            case R.id.btnActivityType :
                intent.putExtra("sortBy", BY_TYPE);
                break;
            case R.id.btnLastCalls :
                intent.putExtra("sortBy", BY_LAST_CALLS);
                break;
            case R.id.btnCompanyName :
                intent.putExtra("sortBy", BY_NAMES);
                break;
        }
        startActivity(intent);
    }

    public void onClickBtnAddClient(View view) {
        intent = new Intent(this, AddClient.class);
        startActivity(intent);
    }

    public void onClickBtnSetTimers(View view) {
        intent = new Intent(this, SettingTimers.class);
        startActivity(intent);
    }

    public void onClickAboutApp(View view) {
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
