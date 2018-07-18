package com.coinbase.android.dialog;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConfirmationDialogController$$Lambda$2 implements OnClickListener {
    private final ConfirmationDialogController arg$1;

    private ConfirmationDialogController$$Lambda$2(ConfirmationDialogController confirmationDialogController) {
        this.arg$1 = confirmationDialogController;
    }

    public static OnClickListener lambdaFactory$(ConfirmationDialogController confirmationDialogController) {
        return new ConfirmationDialogController$$Lambda$2(confirmationDialogController);
    }

    public void onClick(View view) {
        this.arg$1.onUserConfirm();
    }
}
