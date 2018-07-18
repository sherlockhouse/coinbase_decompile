package com.coinbase.android.dialog;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConfirmationDialogController$$Lambda$1 implements OnClickListener {
    private final ConfirmationDialogController arg$1;

    private ConfirmationDialogController$$Lambda$1(ConfirmationDialogController confirmationDialogController) {
        this.arg$1 = confirmationDialogController;
    }

    public static OnClickListener lambdaFactory$(ConfirmationDialogController confirmationDialogController) {
        return new ConfirmationDialogController$$Lambda$1(confirmationDialogController);
    }

    public void onClick(View view) {
        this.arg$1.onUserCancel();
    }
}
