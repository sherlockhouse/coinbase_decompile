package com.coinbase.android.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class SignOutFragment$$Lambda$2 implements OnClickListener {
    private static final SignOutFragment$$Lambda$2 instance = new SignOutFragment$$Lambda$2();

    private SignOutFragment$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SignOutFragment.lambda$onCreateDialog$1(dialogInterface, i);
    }
}
