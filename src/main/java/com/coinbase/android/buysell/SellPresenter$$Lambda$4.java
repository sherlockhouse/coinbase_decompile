package com.coinbase.android.buysell;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class SellPresenter$$Lambda$4 implements Action1 {
    private final SellPresenter arg$1;

    private SellPresenter$$Lambda$4(SellPresenter sellPresenter) {
        this.arg$1 = sellPresenter;
    }

    public static Action1 lambdaFactory$(SellPresenter sellPresenter) {
        return new SellPresenter$$Lambda$4(sellPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DOT, NumericKeypadConnector.DOT);
    }
}
