package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyPresenter$$Lambda$27 implements Action1 {
    private final BuyPresenter arg$1;

    private BuyPresenter$$Lambda$27(BuyPresenter buyPresenter) {
        this.arg$1 = buyPresenter;
    }

    public static Action1 lambdaFactory$(BuyPresenter buyPresenter) {
        return new BuyPresenter$$Lambda$27(buyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.showMissingPaymentMethod();
    }
}