package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$6 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$6(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$6(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        BuyConfirmationPresenter.lambda$commitBuy$5(this.arg$1, (Pair) obj);
    }
}
