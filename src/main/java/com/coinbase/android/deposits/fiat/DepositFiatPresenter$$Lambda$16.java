package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$16 implements Func1 {
    private static final DepositFiatPresenter$$Lambda$16 instance = new DepositFiatPresenter$$Lambda$16();

    private DepositFiatPresenter$$Lambda$16() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return DepositFiatPresenter.lambda$hookUpLinkedAccounts$15((Pair) obj);
    }
}
