package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$4 implements Func1 {
    private static final PaymentMethodsPresenter$$Lambda$4 instance = new PaymentMethodsPresenter$$Lambda$4();

    private PaymentMethodsPresenter$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return PaymentMethodsPresenter.lambda$onResume$3((ClassConsumableEvent) obj);
    }
}
