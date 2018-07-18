package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PlaidPresenter$$Lambda$2 implements Action1 {
    private final PlaidPresenter arg$1;

    private PlaidPresenter$$Lambda$2(PlaidPresenter plaidPresenter) {
        this.arg$1 = plaidPresenter;
    }

    public static Action1 lambdaFactory$(PlaidPresenter plaidPresenter) {
        return new PlaidPresenter$$Lambda$2(plaidPresenter);
    }

    public void call(Object obj) {
        PlaidPresenter.lambda$createPlaidPaymentMethod$1(this.arg$1, (Throwable) obj);
    }
}
