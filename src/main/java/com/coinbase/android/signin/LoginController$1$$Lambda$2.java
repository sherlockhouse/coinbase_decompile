package com.coinbase.android.signin;

import android.app.ProgressDialog;
import com.coinbase.android.utils.Utils;
import rx.functions.Action1;

final /* synthetic */ class LoginController$1$$Lambda$2 implements Action1 {
    private final ProgressDialog arg$1;

    private LoginController$1$$Lambda$2(ProgressDialog progressDialog) {
        this.arg$1 = progressDialog;
    }

    public static Action1 lambdaFactory$(ProgressDialog progressDialog) {
        return new LoginController$1$$Lambda$2(progressDialog);
    }

    public void call(Object obj) {
        Utils.dismissDialog(this.arg$1);
    }
}
