package com.coinbase.android.signin;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class TwoFactorPresenter$SendSMSTask$$Lambda$1 implements OnSubscribe {
    private final SendSMSTask arg$1;

    private TwoFactorPresenter$SendSMSTask$$Lambda$1(SendSMSTask sendSMSTask) {
        this.arg$1 = sendSMSTask;
    }

    public static OnSubscribe lambdaFactory$(SendSMSTask sendSMSTask) {
        return new TwoFactorPresenter$SendSMSTask$$Lambda$1(sendSMSTask);
    }

    public void call(Object obj) {
        SendSMSTask.lambda$call$0(this.arg$1, (Subscriber) obj);
    }
}
