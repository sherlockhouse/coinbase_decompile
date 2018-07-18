package com.coinbase.android.signin.state;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class StateLockedController$$Lambda$4 implements OnClickListener {
    private static final StateLockedController$$Lambda$4 instance = new StateLockedController$$Lambda$4();

    private StateLockedController$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        StateLockedController.lambda$showCancelDialog$3(dialogInterface, i);
    }
}
