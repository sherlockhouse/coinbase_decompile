package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$2 implements Action1 {
    private final SendSMSTask arg$1;

    private TwoFactorPresenter$$Lambda$2(SendSMSTask sendSMSTask) {
        this.arg$1 = sendSMSTask;
    }

    public static Action1 lambdaFactory$(SendSMSTask sendSMSTask) {
        return new TwoFactorPresenter$$Lambda$2(sendSMSTask);
    }

    public void call(Object obj) {
        this.arg$1.onSuccess();
    }
}
