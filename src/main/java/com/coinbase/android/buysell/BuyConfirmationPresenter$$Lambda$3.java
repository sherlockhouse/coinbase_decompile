package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$3 implements Func1 {
    private static final BuyConfirmationPresenter$$Lambda$3 instance = new BuyConfirmationPresenter$$Lambda$3();

    private BuyConfirmationPresenter$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyConfirmationPresenter.lambda$onShow$2((Pair) obj);
    }
}
