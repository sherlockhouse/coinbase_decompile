package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyPresenter$$Lambda$9 implements Func1 {
    private static final BuyPresenter$$Lambda$9 instance = new BuyPresenter$$Lambda$9();

    private BuyPresenter$$Lambda$9() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyPresenter.lambda$onShow$8((Pair) obj);
    }
}
