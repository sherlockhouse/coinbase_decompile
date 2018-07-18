package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyPresenter$$Lambda$6 implements Func1 {
    private static final BuyPresenter$$Lambda$6 instance = new BuyPresenter$$Lambda$6();

    private BuyPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyPresenter.lambda$onShow$5((Pair) obj);
    }
}
