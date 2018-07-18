package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$9 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$9(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$9(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        BuyConfirmationPresenter.lambda$pollForWorldpayStatus$10(this.arg$1, (Throwable) obj);
    }
}
