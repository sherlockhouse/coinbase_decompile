package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$6 implements Func1 {
    private static final DepositFiatPresenter$$Lambda$6 instance = new DepositFiatPresenter$$Lambda$6();

    private DepositFiatPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return DepositFiatPresenter.lambda$hookUpKeypad$5((Pair) obj);
    }
}
