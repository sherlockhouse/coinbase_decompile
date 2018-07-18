package com.coinbase.android.paymentmethods;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PlaidPresenter$$Lambda$1 implements Action1 {
    private final PlaidPresenter arg$1;

    private PlaidPresenter$$Lambda$1(PlaidPresenter plaidPresenter) {
        this.arg$1 = plaidPresenter;
    }

    public static Action1 lambdaFactory$(PlaidPresenter plaidPresenter) {
        return new PlaidPresenter$$Lambda$1(plaidPresenter);
    }

    public void call(Object obj) {
        PlaidPresenter.lambda$createPlaidPaymentMethod$0(this.arg$1, (Pair) obj);
    }
}
