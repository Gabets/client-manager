package com.mobileapplication.blessedtactics.clientmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mobileapplication.blessedtactics.clientmanager.db.DBHelper;

public class ClientInfo extends AppCompatActivity {

    private RadioButton rbLost, rbTransmit, rbWait, rbAction;
    private EditText etClientName, etClientAddress, etClientContactName, etClientMobile,
            etClientActivity, etClientLastCall, etClientJuridicalPhone, etClientMail,
            etClientFax, etClientNotes;

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        if (id == -1) {
            Toast.makeText(this, getResources().getString(R.string.data_not_found), Toast.LENGTH_LONG).show();
            finish();
        }

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        cursor = db.query(DBHelper.DB_TABLE, null, DBHelper.COLUMN_ID + " = ?", new String[] { "" + id }, null, null, null);
        cursor.moveToFirst();

        etClientName = (EditText) findViewById(R.id.etClientName);
        etClientName.setText(cursor.getString(1));
        etClientAddress = (EditText) findViewById(R.id.etClientAddress);
        etClientAddress.setText(cursor.getString(2));
        etClientContactName = (EditText) findViewById(R.id.etClientContactName);
        etClientContactName.setText(cursor.getString(3));
        etClientMobile = (EditText) findViewById(R.id.etClientMobile);
        etClientMobile.setText(cursor.getString(4));
        etClientActivity = (EditText) findViewById(R.id.etClientActivityType);
        etClientActivity.setText(cursor.getString(5));
        etClientLastCall = (EditText) findViewById(R.id.etClientLastCall);
        etClientLastCall.setText(cursor.getString(6));
        etClientJuridicalPhone = (EditText) findViewById(R.id.etClientJuridicalPhone);
        etClientJuridicalPhone.setText(cursor.getString(7));
        etClientMail = (EditText) findViewById(R.id.etClientEmail);
        etClientMail.setText(cursor.getString(8));
        etClientFax = (EditText) findViewById(R.id.etClientFax);
        etClientFax.setText(cursor.getString(9));
        etClientNotes = (EditText) findViewById(R.id.etClientNotes);
        etClientNotes.setText(cursor.getString(10));

        rbLost = (RadioButton) findViewById(R.id.rbLost);
        if (cursor.getString(11).equals(getString(R.string.rbLost))) {
            rbLost.setChecked(true);
        }
        rbTransmit = (RadioButton) findViewById(R.id.rbTransmit);
        if (cursor.getString(11).equals(getString(R.string.rbTransmit))) {
            rbTransmit.setChecked(true);
        }
        rbWait = (RadioButton) findViewById(R.id.rbWait);
        if (cursor.getString(11).equals(getString(R.string.rbWait))) {
            rbWait.setChecked(true);
        }
        rbAction = (RadioButton) findViewById(R.id.rbAction);
        if (cursor.getString(11).equals(getString(R.string.rbAction))) {
            rbAction.setChecked(true);
        }
    }

    public void onClickBtnSave(View view) {

        String clientName = "";
        if (null != etClientName.getText().toString()) {
            clientName = etClientName.getText().toString();
        }
        if (clientName.equalsIgnoreCase("")) {
            Toast.makeText(this, getResources().getString(R.string.input_name), Toast.LENGTH_LONG).show();
            return;
        }

        String clientAddress = "";
        if (null != etClientAddress.getText().toString()) {
            clientAddress = etClientAddress.getText().toString();
        }
        String clientContactName = "";
        if (null != etClientContactName.getText().toString()) {
            clientContactName = etClientContactName.getText().toString();
        }
        String clientMobile = "";
        if (null != etClientMobile.getText().toString()) {
            clientMobile = etClientMobile.getText().toString();
        }
        String clientActivity = "";
        if (null != etClientActivity.getText().toString()) {
            clientActivity = etClientActivity.getText().toString();
        }
        String clientLastCall = "";
        if (null != etClientLastCall.getText().toString()) {
            clientLastCall = etClientLastCall.getText().toString();
        }
        String clientJuridicalPhone = "";
        if (null != etClientJuridicalPhone.getText().toString()) {
            clientJuridicalPhone = etClientJuridicalPhone.getText()
                    .toString();
        }
        String clientMail = "";
        if (null != etClientMail.getText().toString()) {
            clientMail = etClientMail.getText().toString();
        }
        String clientFax = "";
        if (null != etClientFax.getText().toString()) {
            clientFax = etClientFax.getText().toString();
        }
        String clientNotes = "";
        if (null != etClientNotes.getText().toString()) {
            clientNotes = etClientNotes.getText().toString();
        }

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

        cv.put(DBHelper.COLUMN_C_NAME, clientName);
        cv.put(DBHelper.COLUMN_C_ADDRESS, clientAddress);
        cv.put(DBHelper.COLUMN_C_CONTACT_NAME, clientContactName);
        cv.put(DBHelper.COLUMN_C_MOBILE, clientMobile);
        cv.put(DBHelper.COLUMN_C_ACTIVITY_TYPE, clientActivity);
        cv.put(DBHelper.COLUMN_C_LAST_CALL, clientLastCall);
        cv.put(DBHelper.COLUMN_C_JURIDICAL_PHONE, clientJuridicalPhone);
        cv.put(DBHelper.COLUMN_C_EMAIL, clientMail);
        cv.put(DBHelper.COLUMN_C_FAX, clientFax);
        cv.put(DBHelper.COLUMN_C_NOTES, clientNotes);
        cv.put(DBHelper.COLUMN_C_PRIORITY_LEVEL, clientPriorityLevel);
        if (db.update(DBHelper.DB_TABLE, cv, DBHelper.COLUMN_ID + "= ?", new String[] { id + "" }) != -1) {
            Toast.makeText(this, getResources().getString(R.string.changes_saved), Toast.LENGTH_LONG)
                    .show();
            cv.clear();
        }
    }

    public void onClickBtnDelete(View view) {
        db.delete(DBHelper.DB_TABLE, DBHelper.COLUMN_ID + " = " + id, null);
        finish();
    }

    public void onClickBtnCall(View view) {
        if (null != etClientMobile.getText().toString()) {
            Intent intent;
            intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + etClientMobile.getText().toString()));
            startActivity(intent);
        }
    }

    public void onClickBtnSendSMS(View view) {
        if (null != etClientMobile.getText().toString()) {
            Intent intent;
            intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + etClientMobile.getText().toString()));
            intent.putExtra("sms_body", getResources().getString(R.string.write_message));
            startActivity(intent);
        }
    }

    public void onClickBtnSendMail(View view) {
        if (null != etClientMail.getText().toString()) {
            Intent intent;
            intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + etClientMail.getText().toString()));
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
        dbHelper.close();
    }
}
