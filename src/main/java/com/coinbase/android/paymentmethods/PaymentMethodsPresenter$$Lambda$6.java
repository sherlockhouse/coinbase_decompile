package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$6 implements Func1 {
    private static final PaymentMethodsPresenter$$Lambda$6 instance = new PaymentMethodsPresenter$$Lambda$6();

    private PaymentMethodsPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return PaymentMethodsPresenter.lambda$onResume$5((ClassConsumableEvent) obj);
    }
}
