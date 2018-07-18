package com.coinbase.android.buysell;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$4 implements Action1 {
    private final BuyConfirmationPresenter arg$1;

    private BuyConfirmationPresenter$$Lambda$4(BuyConfirmationPresenter buyConfirmationPresenter) {
        this.arg$1 = buyConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter) {
        return new BuyConfirmationPresenter$$Lambda$4(buyConfirmationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.performQuote((Data) ((Pair) obj).second);
    }
}
