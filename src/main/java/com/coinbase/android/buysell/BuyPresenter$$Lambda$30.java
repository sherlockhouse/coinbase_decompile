package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class BuyPresenter$$Lambda$30 implements Action1 {
    private final BuyPresenter arg$1;

    private BuyPresenter$$Lambda$30(BuyPresenter buyPresenter) {
        this.arg$1 = buyPresenter;
    }

    public static Action1 lambdaFactory$(BuyPresenter buyPresenter) {
        return new BuyPresenter$$Lambda$30(buyPresenter);
    }

    public void call(Object obj) {
        BuyPresenter.lambda$previewBuy$29(this.arg$1, (Pair) obj);
    }
}
