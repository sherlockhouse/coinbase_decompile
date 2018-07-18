package com.coinbase.android.buysell;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class BuyConfirmationPresenter$$Lambda$1 implements Func1 {
    private static final BuyConfirmationPresenter$$Lambda$1 instance = new BuyConfirmationPresenter$$Lambda$1();

    private BuyConfirmationPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyConfirmationPresenter.lambda$onShow$0((Pair) obj);
    }
}
