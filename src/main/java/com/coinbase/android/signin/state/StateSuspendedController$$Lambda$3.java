package com.coinbase.android.signin.state;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class StateSuspendedController$$Lambda$3 implements OnClickListener {
    private static final StateSuspendedController$$Lambda$3 instance = new StateSuspendedController$$Lambda$3();

    private StateSuspendedController$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        StateSuspendedController.lambda$showCancelDialog$2(dialogInterface, i);
    }
}
