package com.coinbase.android.buysell;

import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$1 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$1(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$1(sellPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DIGIT, ((Character) obj).charValue());
    }
}
