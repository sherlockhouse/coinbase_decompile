package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SignInPhoneNumberPresenter$$Lambda$2 implements Action1 {
    private final SignInPhoneNumberPresenter arg$1;
    private final String arg$2;

    private SignInPhoneNumberPresenter$$Lambda$2(SignInPhoneNumberPresenter signInPhoneNumberPresenter, String str) {
        this.arg$1 = signInPhoneNumberPresenter;
        this.arg$2 = str;
    }

    public static Action1 lambdaFactory$(SignInPhoneNumberPresenter signInPhoneNumberPresenter, String str) {
        return new SignInPhoneNumberPresenter$$Lambda$2(signInPhoneNumberPresenter, str);
    }

    public void call(Object obj) {
        SignInPhoneNumberPresenter.lambda$addPhoneNumber$1(this.arg$1, this.arg$2, (Pair) obj);
    }
}
