package com.coinbase.android.signin;

import rx.functions.Action0;

final /* synthetic */ class EmailVerifyPresenter$$Lambda$3 implements Action0 {
    private final EmailVerifyPresenter arg$1;
    private final boolean arg$2;

    private EmailVerifyPresenter$$Lambda$3(EmailVerifyPresenter emailVerifyPresenter, boolean z) {
        this.arg$1 = emailVerifyPresenter;
        this.arg$2 = z;
    }

    public static Action0 lambdaFactory$(EmailVerifyPresenter emailVerifyPresenter, boolean z) {
        return new EmailVerifyPresenter$$Lambda$3(emailVerifyPresenter, z);
    }

    public void call() {
        EmailVerifyPresenter.lambda$cancel$2(this.arg$1, this.arg$2);
    }
}
