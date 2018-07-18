package com.coinbase.android.signin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import rx.functions.Action0;

final /* synthetic */ class SignInRouter$$Lambda$1 implements OnClickListener {
    private final SignInRouter arg$1;
    private final Action0 arg$2;

    private SignInRouter$$Lambda$1(SignInRouter signInRouter, Action0 action0) {
        this.arg$1 = signInRouter;
        this.arg$2 = action0;
    }

    public static OnClickListener lambdaFactory$(SignInRouter signInRouter, Action0 action0) {
        return new SignInRouter$$Lambda$1(signInRouter, action0);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SignInRouter.lambda$cancel$0(this.arg$1, this.arg$2, dialogInterface, i);
    }
}
