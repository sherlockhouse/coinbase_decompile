package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SignInVerifyPhoneNumberPresenter$$Lambda$5 implements Action1 {
    private final SignInVerifyPhoneNumberPresenter arg$1;

    private SignInVerifyPhoneNumberPresenter$$Lambda$5(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        this.arg$1 = signInVerifyPhoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        return new SignInVerifyPhoneNumberPresenter$$Lambda$5(signInVerifyPhoneNumberPresenter);
    }

    public void call(Object obj) {
        SignInVerifyPhoneNumberPresenter.lambda$verifyPhoneNumber$4(this.arg$1, (Pair) obj);
    }
}
