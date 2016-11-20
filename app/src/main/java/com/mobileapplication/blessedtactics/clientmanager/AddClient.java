package com.mobileapplication.blessedtactics.clientmanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mobileapplication.blessedtactics.clientmanager.db.DBHelper;

public class AddClient extends AppCompatActivity {

    private RadioButton rbLost, rbTransmit, rbWait, rbAction;
    private EditText etClientName, etClientAddress, etClientContactName,
            etClientMobile, etClientActivityType, etClientLastCall,
            etClientJuridicalPhone, etClientEmail, etClientFax,
            etClientNotes;

    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__client);

        etClientName = (EditText) findViewById(R.id.etClientName);
        etClientAddress = (EditText) findViewById(R.id.etClientAddress);
        etClientContactName = (EditText) findViewById(R.id.etClientContactName);
        etClientMobile = (EditText) findViewById(R.id.etClientMobile);
        etClientActivityType = (EditText) findViewById(R.id.etClientActivityType);
        etClientLastCall = (EditText) findViewById(R.id.etClientLastCall);
        etClientJuridicalPhone = (EditText) findViewById(R.id.etClientJuridicalPhone);
        etClientEmail = (EditText) findViewById(R.id.etClientEmail);
        etClientFax = (EditText) findViewById(R.id.etClientFax);
        etClientNotes = (EditText) findViewById(R.id.etClientNotes);

        rbLost = (RadioButton) findViewById(R.id.rbLost);
        rbTransmit = (RadioButton) findViewById(R.id.rbTransmit);
        rbWait = (RadioButton) findViewById(R.id.rbWait);
        rbAction = (RadioButton) findViewById(R.id.rbAction);

        dbHelper = new DBHelper(this);
    }

    public void onClickBtnCancel(View view) {
        finish();
    }

    public void onClickBtnSave(View view) {
        String clientName = etClientName.getText().toString();
        if (clientName.equalsIgnoreCase("")) {
            Toast.makeText(this, getResources().getString(R.string.input_name), Toast.LENGTH_LONG).show();
            return;
        }

        String clientAddress  = etClientAddress.getText().toString();
        String clientContactName = etClientContactName.getText().toString();
        String clientMobile = etClientMobile.getText().toString();
        String clientActivityType =  etClientActivityType.getText().toString();
        String clientLastCall = etClientLastCall.getText().toString();
        String clientJuridicalPhone =  etClientJuridicalPhone.getText().toString();
        String clientEmail =  etClientEmail.getText().toString();
        String clientFax =  etClientFax.getText().toString();
        String clientNotes = etClientNotes.getText().toString();

        String clientPriorityLevel = "";
        if (rbLost.isChecked()) {
            clientPriorityLevel = getString(R.string.rbLost);
        }

        if (rbTransmit.isChecked()) {
            clientPriorityLevel = getString(R.string.rbTransmit);
        }
        if (rbWait.isChecked()) {
            clientPriorityLevel = getString(R.string.rbWait);
        }
        if (rbAction.isChecked()) {
            clientPriorityLevel = getString(R.string.rbAction);
        }

        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put(DBHelper.COLUMN_C_NAME, clientName);
        cv.put(DBHelper.COLUMN_C_ADDRESS, clientAddress);
        cv.put(DBHelper.COLUMN_C_CONTACT_NAME, clientContactName);
        cv.put(DBHelper.COLUMN_C_MOBILE, clientMobile);
        cv.put(DBHelper.COLUMN_C_ACTIVITY_TYPE, clientActivityType);
        cv.put(DBHelper.COLUMN_C_LAST_CALL, clientLastCall);
        cv.put(DBHelper.COLUMN_C_JURIDICAL_PHONE, clientJuridicalPhone);
        cv.put(DBHelper.COLUMN_C_EMAIL, clientEmail);
        cv.put(DBHelper.COLUMN_C_FAX, clientFax);
        cv.put(DBHelper.COLUMN_C_NOTES, clientNotes);
        cv.put(DBHelper.COLUMN_C_PRIORITY_LEVEL, clientPriorityLevel);

        if (db.insert(DBHelper.DB_TABLE, null, cv) != -1) {
            Toast.makeText(this, getResources().getString(R.string.input_success), Toast.LENGTH_LONG)
                    .show();
            dbHelper.close();
        }

        etClientName.setText("");
        etClientAddress.setText("");
        etClientContactName.setText("");
        etClientMobile.setText("");
        etClientActivityType.setText("");
        etClientLastCall.setText("");
        etClientJuridicalPhone.setText("");
        etClientEmail.setText("");
        etClientFax.setText("");
        etClientNotes.setText("");

        rbLost.setChecked(false);
        rbTransmit.setChecked(false);
        rbWait.setChecked(false);
        rbAction.setChecked(false);
    }
}
