package com.coinbase.android.dialog;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlertDialogController$$Lambda$1 implements OnClickListener {
    private final AlertDialogController arg$1;

    private AlertDialogController$$Lambda$1(AlertDialogController alertDialogController) {
        this.arg$1 = alertDialogController;
    }

    public static OnClickListener lambdaFactory$(AlertDialogController alertDialogController) {
        return new AlertDialogController$$Lambda$1(alertDialogController);
    }

    public void onClick(View view) {
        this.arg$1.onUserConfirm();
    }
}
