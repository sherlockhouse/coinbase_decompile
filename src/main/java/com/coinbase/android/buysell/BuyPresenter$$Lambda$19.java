package com.coinbase.android.buysell;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class BuyPresenter$$Lambda$19 implements Func1 {
    private static final BuyPresenter$$Lambda$19 instance = new BuyPresenter$$Lambda$19();

    private BuyPresenter$$Lambda$19() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BuyPresenter.lambda$onShow$18((ClassConsumableEvent) obj);
    }
}
