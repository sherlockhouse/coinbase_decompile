package com.coinbase.android.buysell;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$12 implements Action1 {
    private final BuyConfirmationPresenter arg$1;
    private final Data arg$2;

    private BuyConfirmationPresenter$$Lambda$12(BuyConfirmationPresenter buyConfirmationPresenter, Data data) {
        this.arg$1 = buyConfirmationPresenter;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(BuyConfirmationPresenter buyConfirmationPresenter, Data data) {
        return new BuyConfirmationPresenter$$Lambda$12(buyConfirmationPresenter, data);
    }

    public void call(Object obj) {
        BuyConfirmationPresenter.lambda$performQuote$13(this.arg$1, this.arg$2, (Pair) obj);
    }
}
