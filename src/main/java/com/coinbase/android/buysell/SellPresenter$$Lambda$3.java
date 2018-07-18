package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SellPresenter$$Lambda$3 implements Func1 {
    private static final SellPresenter$$Lambda$3 instance = new SellPresenter$$Lambda$3();

    private SellPresenter$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellPresenter.lambda$onShow$2((Pair) obj);
    }
}
