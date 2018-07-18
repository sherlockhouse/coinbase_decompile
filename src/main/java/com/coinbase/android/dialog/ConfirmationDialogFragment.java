package com.coinbase.android.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import com.coinbase.android.R;

public abstract class ConfirmationDialogFragment extends DialogFragment {
    public abstract String getMessage();

    public abstract void onUserConfirm();

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TextView message = new TextView(getActivity());
        message.setBackgroundColor(-1);
        message.setTextColor(-16777216);
        message.setTextSize(2, 18.0f);
        int paddingPx = (int) ((15.0f * getResources().getDisplayMetrics().density) + 0.5f);
        message.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
        message.setText(getMessage());
        Builder builder = new Builder(getActivity());
        builder.setView(message).setPositiveButton(getPositiveButtonText(), new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ConfirmationDialogFragment.this.onUserConfirm();
            }
        }).setNegativeButton(getNegativeButtonText(), new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ConfirmationDialogFragment.this.onUserCancel();
            }
        });
        return builder.create();
    }

    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(-1).setTextColor(getResources().getColor(R.color.primary));
        ((AlertDialog) getDialog()).getButton(-2).setTextColor(getResources().getColor(R.color.grey_button_text));
    }

    public void onUserCancel() {
    }

    protected int getPositiveButtonText() {
        return R.string.confirm;
    }

    protected int getNegativeButtonText() {
        return R.string.cancel;
    }
}
