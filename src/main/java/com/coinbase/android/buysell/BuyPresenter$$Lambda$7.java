package com.coinbase.android.buysell;

import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class BuyPresenter$$Lambda$7 implements Action1 {
    private final BuyPresenter arg$1;

    private BuyPresenter$$Lambda$7(BuyPresenter buyPresenter) {
        this.arg$1 = buyPresenter;
    }

    public static Action1 lambdaFactory$(BuyPresenter buyPresenter) {
        return new BuyPresenter$$Lambda$7(buyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DELETE, ' ');
    }
}
