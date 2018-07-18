package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$3 implements Action1 {
    private final PaymentMethodsPresenter arg$1;

    private PaymentMethodsPresenter$$Lambda$3(PaymentMethodsPresenter paymentMethodsPresenter) {
        this.arg$1 = paymentMethodsPresenter;
    }

    public static Action1 lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter) {
        return new PaymentMethodsPresenter$$Lambda$3(paymentMethodsPresenter);
    }

    public void call(Object obj) {
        PaymentMethodsPresenter.lambda$refreshPaymentMethods$2(this.arg$1, (Throwable) obj);
    }
}
