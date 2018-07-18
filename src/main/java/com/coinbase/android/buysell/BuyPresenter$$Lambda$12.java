package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyPresenter$$Lambda$12 implements Action1 {
    private final BuyPresenter arg$1;

    private BuyPresenter$$Lambda$12(BuyPresenter buyPresenter) {
        this.arg$1 = buyPresenter;
    }

    public static Action1 lambdaFactory$(BuyPresenter buyPresenter) {
        return new BuyPresenter$$Lambda$12(buyPresenter);
    }

    public void call(Object obj) {
        BuyPresenter.lambda$onShow$11(this.arg$1, (Void) obj);
    }
}
