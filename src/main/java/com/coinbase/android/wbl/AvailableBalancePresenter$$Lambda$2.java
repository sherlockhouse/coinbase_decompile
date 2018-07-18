package com.coinbase.android.wbl;

import rx.functions.Action1;

final /* synthetic */ class AvailableBalancePresenter$$Lambda$2 implements Action1 {
    private final AvailableBalancePresenter arg$1;

    private AvailableBalancePresenter$$Lambda$2(AvailableBalancePresenter availableBalancePresenter) {
        this.arg$1 = availableBalancePresenter;
    }

    public static Action1 lambdaFactory$(AvailableBalancePresenter availableBalancePresenter) {
        return new AvailableBalancePresenter$$Lambda$2(availableBalancePresenter);
    }

    public void call(Object obj) {
        AvailableBalancePresenter.lambda$onShow$1(this.arg$1, (Throwable) obj);
    }
}
