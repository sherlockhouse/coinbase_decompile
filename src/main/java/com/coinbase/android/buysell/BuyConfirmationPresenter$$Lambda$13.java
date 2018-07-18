package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$13 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$13(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$13(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        BuyConfirmationPresenter.lambda$performQuote$14(this.arg$1, (Throwable) obj);
    }
}
