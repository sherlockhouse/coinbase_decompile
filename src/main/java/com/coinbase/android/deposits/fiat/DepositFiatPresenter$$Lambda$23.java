package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$23 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$23(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$23(depositFiatPresenter);
    }

    public void call(Object obj) {
        DepositFiatPresenter.lambda$previewDeposit$22(this.arg$1, (Pair) obj);
    }
}
