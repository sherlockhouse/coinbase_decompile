package com.coinbase.android.buysell;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$2 implements Action1 {
    private final SellConfirmationPresenter arg$1;

    private SellConfirmationPresenter$$Lambda$2(SellConfirmationPresenter sellConfirmationPresenter) {
        this.arg$1 = sellConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(SellConfirmationPresenter sellConfirmationPresenter) {
        return new SellConfirmationPresenter$$Lambda$2(sellConfirmationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.performQuote((Data) ((Pair) obj).second);
    }
}
