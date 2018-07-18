package com.coinbase.android.buysell;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$6 implements Action1 {
    private final SellConfirmationPresenter arg$1;
    private final Data arg$2;

    private SellConfirmationPresenter$$Lambda$6(SellConfirmationPresenter sellConfirmationPresenter, Data data) {
        this.arg$1 = sellConfirmationPresenter;
        this.arg$2 = data;
    }

    public static Action1 lambdaFactory$(SellConfirmationPresenter sellConfirmationPresenter, Data data) {
        return new SellConfirmationPresenter$$Lambda$6(sellConfirmationPresenter, data);
    }

    public void call(Object obj) {
        SellConfirmationPresenter.lambda$performQuote$5(this.arg$1, this.arg$2, (Pair) obj);
    }
}
