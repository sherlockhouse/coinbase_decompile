package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$27 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$27(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$27(sellPresenter);
    }

    public void call(Object obj) {
        SellPresenter.lambda$getPaymentMethods$26(this.arg$1, (Pair) obj);
    }
}
