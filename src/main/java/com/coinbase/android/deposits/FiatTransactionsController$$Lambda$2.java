package com.coinbase.android.deposits;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class FiatTransactionsController$$Lambda$2 implements Func1 {
    private static final FiatTransactionsController$$Lambda$2 instance = new FiatTransactionsController$$Lambda$2();

    private FiatTransactionsController$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return FiatTransactionsController.lambda$onCreateView$1((ClassConsumableEvent) obj);
    }
}
