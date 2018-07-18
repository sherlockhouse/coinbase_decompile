package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import com.coinbase.v2.models.paymentMethods.Data;
import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$17 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$17(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$17(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.updatePaymentMethod((Data) ((Pair) obj).second);
    }
}
