package com.coinbase.android.signin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class SignInRouter$$Lambda$2 implements OnClickListener {
    private static final SignInRouter$$Lambda$2 instance = new SignInRouter$$Lambda$2();

    private SignInRouter$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SignInRouter.lambda$cancel$1(dialogInterface, i);
    }
}
