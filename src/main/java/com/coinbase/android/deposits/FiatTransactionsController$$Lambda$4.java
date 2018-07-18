package com.coinbase.android.deposits;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class FiatTransactionsController$$Lambda$4 implements Func1 {
    private static final FiatTransactionsController$$Lambda$4 instance = new FiatTransactionsController$$Lambda$4();

    private FiatTransactionsController$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return FiatTransactionsController.lambda$onCreateView$3((ClassConsumableEvent) obj);
    }
}
