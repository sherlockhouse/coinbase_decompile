package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$17 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$17(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$17(depositFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.updatePaymentMethod((Data) ((Pair) obj).second);
    }
}
