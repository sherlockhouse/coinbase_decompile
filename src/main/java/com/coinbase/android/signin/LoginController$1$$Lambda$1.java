package com.coinbase.android.signin;

import android.app.ProgressDialog;
import com.coinbase.android.signin.LoginController.AnonymousClass1;
import com.google.android.gms.auth.api.credentials.Credential;
import rx.functions.Action1;

final /* synthetic */ class LoginController$1$$Lambda$1 implements Action1 {
    private final AnonymousClass1 arg$1;
    private final ProgressDialog arg$2;

    private LoginController$1$$Lambda$1(AnonymousClass1 anonymousClass1, ProgressDialog progressDialog) {
        this.arg$1 = anonymousClass1;
        this.arg$2 = progressDialog;
    }

    public static Action1 lambdaFactory$(AnonymousClass1 anonymousClass1, ProgressDialog progressDialog) {
        return new LoginController$1$$Lambda$1(anonymousClass1, progressDialog);
    }

    public void call(Object obj) {
        AnonymousClass1.lambda$onClick$0(this.arg$1, this.arg$2, (Credential) obj);
    }
}
