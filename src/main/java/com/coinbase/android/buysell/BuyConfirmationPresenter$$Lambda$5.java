package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$5 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$5(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$5(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to LinkedAccountConnector for getPaymentMethodSelectedSubject()");
    }
}
