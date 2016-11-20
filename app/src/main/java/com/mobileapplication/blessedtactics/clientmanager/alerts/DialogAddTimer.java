package com.mobileapplication.blessedtactics.clientmanager.alerts;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.mobileapplication.blessedtactics.clientmanager.R;
import com.mobileapplication.blessedtactics.clientmanager.SettingTimers;

import java.util.Calendar;

public class DialogAddTimer extends DialogFragment {

    private TimePicker timePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_timer, null);

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);

        Button btnTimerDlgSave = (Button) view.findViewById(R.id.btnTimerDlgSave);
        btnTimerDlgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SettingTimers) getActivity())
                        .onClickSave(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                dismiss();
            }
        });
        Button btnTimerDlgCancel = (Button) view.findViewById(R.id.btnTimerDlgCancel);
        btnTimerDlgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        builder.setView(view);
        return builder.create();
    }
}
