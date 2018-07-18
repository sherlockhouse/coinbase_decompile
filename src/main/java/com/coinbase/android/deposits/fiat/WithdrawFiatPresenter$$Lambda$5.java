package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$5 implements Func1 {
    private static final WithdrawFiatPresenter$$Lambda$5 instance = new WithdrawFiatPresenter$$Lambda$5();

    private WithdrawFiatPresenter$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return WithdrawFiatPresenter.lambda$hookUpKeypad$4((Pair) obj);
    }
}
