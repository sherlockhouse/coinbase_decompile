package com.coinbase.android.signin;

import rx.functions.Action0;

final /* synthetic */ class TwoFactorPresenter$$Lambda$4 implements Action0 {
    private final SendSMSTask arg$1;

    private TwoFactorPresenter$$Lambda$4(SendSMSTask sendSMSTask) {
        this.arg$1 = sendSMSTask;
    }

    public static Action0 lambdaFactory$(SendSMSTask sendSMSTask) {
        return new TwoFactorPresenter$$Lambda$4(sendSMSTask);
    }

    public void call() {
        this.arg$1.onFinally();
    }
}
