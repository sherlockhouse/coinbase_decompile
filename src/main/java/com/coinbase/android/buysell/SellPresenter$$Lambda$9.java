package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class SellPresenter$$Lambda$9 implements Func1 {
    private static final SellPresenter$$Lambda$9 instance = new SellPresenter$$Lambda$9();

    private SellPresenter$$Lambda$9() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellPresenter.lambda$onShow$8((Pair) obj);
    }
}
