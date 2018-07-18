package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$3 implements Action1 {
    private final TwoFactorPresenter arg$1;
    private final SendSMSTask arg$2;

    private TwoFactorPresenter$$Lambda$3(TwoFactorPresenter twoFactorPresenter, SendSMSTask sendSMSTask) {
        this.arg$1 = twoFactorPresenter;
        this.arg$2 = sendSMSTask;
    }

    public static Action1 lambdaFactory$(TwoFactorPresenter twoFactorPresenter, SendSMSTask sendSMSTask) {
        return new TwoFactorPresenter$$Lambda$3(twoFactorPresenter, sendSMSTask);
    }

    public void call(Object obj) {
        TwoFactorPresenter.lambda$runSmsTask$2(this.arg$1, this.arg$2, (Throwable) obj);
    }
}
