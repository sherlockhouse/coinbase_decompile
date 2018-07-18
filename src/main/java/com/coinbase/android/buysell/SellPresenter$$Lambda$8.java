package com.coinbase.android.buysell;

import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$8 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$8(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$8(sellPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to NumericKeypadConnector.KeypadType.RIGHT for getButtonClickedSubject()", (Throwable) obj);
    }
}
