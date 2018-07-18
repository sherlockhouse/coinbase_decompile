package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class EmailVerifyPresenter$$Lambda$2 implements Action1 {
    private final EmailVerifyPresenter arg$1;

    private EmailVerifyPresenter$$Lambda$2(EmailVerifyPresenter emailVerifyPresenter) {
        this.arg$1 = emailVerifyPresenter;
    }

    public static Action1 lambdaFactory$(EmailVerifyPresenter emailVerifyPresenter) {
        return new EmailVerifyPresenter$$Lambda$2(emailVerifyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't track email verify viewed event");
    }
}
