package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$5 implements Action1 {
    private final SellConfirmationPresenter arg$1;

    private SellConfirmationPresenter$$Lambda$5(SellConfirmationPresenter sellConfirmationPresenter) {
        this.arg$1 = sellConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(SellConfirmationPresenter sellConfirmationPresenter) {
        return new SellConfirmationPresenter$$Lambda$5(sellConfirmationPresenter);
    }

    public void call(Object obj) {
        SellConfirmationPresenter.lambda$commitSell$4(this.arg$1, (Throwable) obj);
    }
}
