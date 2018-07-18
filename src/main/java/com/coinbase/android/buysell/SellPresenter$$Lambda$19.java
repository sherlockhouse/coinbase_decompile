package com.coinbase.android.buysell;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class SellPresenter$$Lambda$19 implements Func1 {
    private static final SellPresenter$$Lambda$19 instance = new SellPresenter$$Lambda$19();

    private SellPresenter$$Lambda$19() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SellPresenter.lambda$onShow$18((ClassConsumableEvent) obj);
    }
}
