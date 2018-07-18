package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$4 implements Action1 {
    private final SellConfirmationPresenter arg$1;

    private SellConfirmationPresenter$$Lambda$4(SellConfirmationPresenter sellConfirmationPresenter) {
        this.arg$1 = sellConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(SellConfirmationPresenter sellConfirmationPresenter) {
        return new SellConfirmationPresenter$$Lambda$4(sellConfirmationPresenter);
    }

    public void call(Object obj) {
        SellConfirmationPresenter.lambda$commitSell$3(this.arg$1, (Pair) obj);
    }
}
