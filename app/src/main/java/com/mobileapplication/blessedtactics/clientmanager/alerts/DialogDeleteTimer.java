package com.mobileapplication.blessedtactics.clientmanager.alerts;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mobileapplication.blessedtactics.clientmanager.R;
import com.mobileapplication.blessedtactics.clientmanager.SettingTimers;

public class DialogDeleteTimer extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_timer, null);

        Button btnDeleteDlgOk = (Button) view.findViewById(R.id.btnDeleteDlgOk);
        btnDeleteDlgOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SettingTimers) getActivity()).onClickDelete();
                dismiss();
            }
        });

        Button btnDeleteDlgCancel = (Button) view.findViewById(R.id.btnDeleteDlgCancel);
        btnDeleteDlgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
