package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SellConfirmationPresenter$$Lambda$1 implements Func1 {
    private static final SellConfirmationPresenter$$Lambda$1 instance = new SellConfirmationPresenter$$Lambda$1();

    private SellConfirmationPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellConfirmationPresenter.lambda$onShow$0((Pair) obj);
    }
}
