package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class EmailVerifyPresenter$$Lambda$4 implements Action1 {
    private final EmailVerifyPresenter arg$1;

    private EmailVerifyPresenter$$Lambda$4(EmailVerifyPresenter emailVerifyPresenter) {
        this.arg$1 = emailVerifyPresenter;
    }

    public static Action1 lambdaFactory$(EmailVerifyPresenter emailVerifyPresenter) {
        return new EmailVerifyPresenter$$Lambda$4(emailVerifyPresenter);
    }

    public void call(Object obj) {
        EmailVerifyPresenter.lambda$getUser$5(this.arg$1, (Pair) obj);
    }
}
