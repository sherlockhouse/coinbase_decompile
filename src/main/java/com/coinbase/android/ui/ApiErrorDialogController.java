package com.coinbase.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.dialog.AlertDialogController;

public class ApiErrorDialogController extends AlertDialogController {
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_TITLE = "error_title";

    public ApiErrorDialogController(Bundle args) {
        super(args);
    }

    protected View inflateContent(LayoutInflater inflater, ViewGroup container) {
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject((AlertDialogController) this);
        return super.inflateContent(inflater, container);
    }

    public String getDialogTitle() {
        if (getArgs() != null) {
            return getArgs().getString(ERROR_TITLE);
        }
        return null;
    }

    public String getMessage() {
        String errorMessage = null;
        if (getArgs() != null) {
            errorMessage = getArgs().getString("error_message");
        }
        if (errorMessage != null) {
            return errorMessage;
        }
        return getApplicationContext().getString(R.string.an_error_occurred);
    }

    public void onUserConfirm() {
        dismiss();
    }
}
