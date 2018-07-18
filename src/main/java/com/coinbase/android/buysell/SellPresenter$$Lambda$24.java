package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$24 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$24(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$24(sellPresenter);
    }

    public void call(Object obj) {
        SellPresenter.lambda$setPrimaryAccount$23(this.arg$1, (Throwable) obj);
    }
}
