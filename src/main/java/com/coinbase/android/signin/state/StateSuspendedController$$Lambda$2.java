package com.coinbase.android.signin.state;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class StateSuspendedController$$Lambda$2 implements OnClickListener {
    private final StateSuspendedController arg$1;

    private StateSuspendedController$$Lambda$2(StateSuspendedController stateSuspendedController) {
        this.arg$1 = stateSuspendedController;
    }

    public static OnClickListener lambdaFactory$(StateSuspendedController stateSuspendedController) {
        return new StateSuspendedController$$Lambda$2(stateSuspendedController);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.mPresenter.onConfirmCancelButtonClicked();
    }
}
