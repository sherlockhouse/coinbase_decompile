package com.coinbase.android.buysell;

import android.content.Intent;
import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$2 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$2(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$2(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.completeBuyAfterSecure3D(((Integer) ((Pair) ((Pair) obj).second).first).intValue(), (Intent) ((Pair) ((Pair) obj).second).second);
    }
}
