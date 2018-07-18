package com.coinbase.android.transfers;

import rx.functions.Action1;

final /* synthetic */ class SendPresenter$$Lambda$2 implements Action1 {
    private final SendPresenter arg$1;

    private SendPresenter$$Lambda$2(SendPresenter sendPresenter) {
        this.arg$1 = sendPresenter;
    }

    public static Action1 lambdaFactory$(SendPresenter sendPresenter) {
        return new SendPresenter$$Lambda$2(sendPresenter);
    }

    public void call(Object obj) {
        SendPresenter.lambda$onViewCreated$1(this.arg$1, (Throwable) obj);
    }
}
