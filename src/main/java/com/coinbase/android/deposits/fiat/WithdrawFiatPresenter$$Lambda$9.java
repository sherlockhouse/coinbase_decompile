package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$9 implements Func1 {
    private static final WithdrawFiatPresenter$$Lambda$9 instance = new WithdrawFiatPresenter$$Lambda$9();

    private WithdrawFiatPresenter$$Lambda$9() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return WithdrawFiatPresenter.lambda$hookUpKeypad$8((Pair) obj);
    }
}
