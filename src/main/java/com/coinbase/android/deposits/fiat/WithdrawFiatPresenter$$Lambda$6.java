package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$6 implements Func1 {
    private static final WithdrawFiatPresenter$$Lambda$6 instance = new WithdrawFiatPresenter$$Lambda$6();

    private WithdrawFiatPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return WithdrawFiatPresenter.lambda$hookUpKeypad$5((Pair) obj);
    }
}
