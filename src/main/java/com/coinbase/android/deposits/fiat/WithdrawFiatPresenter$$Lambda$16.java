package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$16 implements Func1 {
    private static final WithdrawFiatPresenter$$Lambda$16 instance = new WithdrawFiatPresenter$$Lambda$16();

    private WithdrawFiatPresenter$$Lambda$16() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return WithdrawFiatPresenter.lambda$hookUpLinkedAccounts$15((Pair) obj);
    }
}
