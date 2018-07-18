package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SellPresenter$$Lambda$16 implements Func1 {
    private static final SellPresenter$$Lambda$16 instance = new SellPresenter$$Lambda$16();

    private SellPresenter$$Lambda$16() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellPresenter.lambda$onShow$15((Pair) obj);
    }
}
