package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$3 implements Action1 {
    private final SellConfirmationPresenter arg$1;

    private SellConfirmationPresenter$$Lambda$3(SellConfirmationPresenter sellConfirmationPresenter) {
        this.arg$1 = sellConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(SellConfirmationPresenter sellConfirmationPresenter) {
        return new SellConfirmationPresenter$$Lambda$3(sellConfirmationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to LinkedAccountConnector for getPaymentMethodSelectedSubject()");
    }
}
