package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$9 implements Action1 {
    private static final PaymentMethodsPresenter$$Lambda$9 instance = new PaymentMethodsPresenter$$Lambda$9();

    private PaymentMethodsPresenter$$Lambda$9() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PaymentMethodsPresenter.lambda$getAvailablePaymentMethods$8((Throwable) obj);
    }
}
