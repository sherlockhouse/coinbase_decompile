package com.coinbase.android.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class SignOutFragment$$Lambda$1 implements OnClickListener {
    private final SignOutFragment arg$1;

    private SignOutFragment$$Lambda$1(SignOutFragment signOutFragment) {
        this.arg$1 = signOutFragment;
    }

    public static OnClickListener lambdaFactory$(SignOutFragment signOutFragment) {
        return new SignOutFragment$$Lambda$1(signOutFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.mSignOutConnector.get().onNext(null);
    }
}
