package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyPresenter$$Lambda$16 implements Func1 {
    private static final BuyPresenter$$Lambda$16 instance = new BuyPresenter$$Lambda$16();

    private BuyPresenter$$Lambda$16() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyPresenter.lambda$onShow$15((Pair) obj);
    }
}
