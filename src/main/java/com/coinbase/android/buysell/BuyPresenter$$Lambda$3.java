package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyPresenter$$Lambda$3 implements Func1 {
    private static final BuyPresenter$$Lambda$3 instance = new BuyPresenter$$Lambda$3();

    private BuyPresenter$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyPresenter.lambda$onShow$2((Pair) obj);
    }
}
