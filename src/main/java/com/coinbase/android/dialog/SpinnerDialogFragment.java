package com.coinbase.android.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.coinbase.android.R;

public abstract class SpinnerDialogFragment<T> extends DialogFragment {
    public static final String SELECTED_INDEX = "SpinnerDialogFragment_Selected_Index";
    public static final String TITLE = "SpinnerDialogFragment_Title";

    public abstract String getOptionDisplayText(T t);

    public abstract T[] getOptions();

    public abstract void onChosenValue(T t);

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder alertBuilder = new Builder(getActivity());
        final T[] options = getOptions();
        String[] displayTexts = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            displayTexts[i] = getOptionDisplayText(options[i]);
        }
        Bundle bundle = getArguments();
        int defaultSelection = 0;
        if (bundle != null) {
            alertBuilder.setTitle(bundle.getString(TITLE));
            defaultSelection = bundle.getInt(SELECTED_INDEX);
        }
        alertBuilder.setSingleChoiceItems(displayTexts, defaultSelection, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertBuilder.setPositiveButton(R.string.ok, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SpinnerDialogFragment.this.onChosenValue(options[((AlertDialog) dialog).getListView().getCheckedItemPosition()]);
            }
        });
        alertBuilder.setNegativeButton(R.string.cancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SpinnerDialogFragment.this.onCancel();
            }
        });
        return alertBuilder.create();
    }

    public void onCancel() {
    }
}
