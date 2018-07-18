package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class EmailVerifyPresenter$$Lambda$5 implements Action1 {
    private final EmailVerifyPresenter arg$1;

    private EmailVerifyPresenter$$Lambda$5(EmailVerifyPresenter emailVerifyPresenter) {
        this.arg$1 = emailVerifyPresenter;
    }

    public static Action1 lambdaFactory$(EmailVerifyPresenter emailVerifyPresenter) {
        return new EmailVerifyPresenter$$Lambda$5(emailVerifyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.cancelTimer();
    }
}
