package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$25 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$25(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$25(depositFiatPresenter);
    }

    public void call(Object obj) {
        DepositFiatPresenter.lambda$refreshPaymentMethods$24(this.arg$1, (Pair) obj);
    }
}
