package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyPresenter$$Lambda$29 implements Action1 {
    private final BuyPresenter arg$1;

    private BuyPresenter$$Lambda$29(BuyPresenter buyPresenter) {
        this.arg$1 = buyPresenter;
    }

    public static Action1 lambdaFactory$(BuyPresenter buyPresenter) {
        return new BuyPresenter$$Lambda$29(buyPresenter);
    }

    public void call(Object obj) {
        BuyPresenter.lambda$performQuote$28(this.arg$1, (Throwable) obj);
    }
}
