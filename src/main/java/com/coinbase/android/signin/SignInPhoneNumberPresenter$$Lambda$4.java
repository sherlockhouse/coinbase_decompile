package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SignInPhoneNumberPresenter$$Lambda$4 implements Action1 {
    private final SignInPhoneNumberPresenter arg$1;

    private SignInPhoneNumberPresenter$$Lambda$4(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        this.arg$1 = signInPhoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        return new SignInPhoneNumberPresenter$$Lambda$4(signInPhoneNumberPresenter);
    }

    public void call(Object obj) {
        SignInPhoneNumberPresenter.lambda$getPhoneNumbersAndRouteToVerificationScreenIfNecessary$3(this.arg$1, (Pair) obj);
    }
}
