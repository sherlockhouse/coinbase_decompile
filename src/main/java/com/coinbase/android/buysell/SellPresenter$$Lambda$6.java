package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SellPresenter$$Lambda$6 implements Func1 {
    private static final SellPresenter$$Lambda$6 instance = new SellPresenter$$Lambda$6();

    private SellPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellPresenter.lambda$onShow$5((Pair) obj);
    }
}
