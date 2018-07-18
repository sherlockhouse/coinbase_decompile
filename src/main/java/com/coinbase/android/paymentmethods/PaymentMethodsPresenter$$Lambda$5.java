package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$5 implements Action1 {
    private final PaymentMethodsPresenter arg$1;

    private PaymentMethodsPresenter$$Lambda$5(PaymentMethodsPresenter paymentMethodsPresenter) {
        this.arg$1 = paymentMethodsPresenter;
    }

    public static Action1 lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter) {
        return new PaymentMethodsPresenter$$Lambda$5(paymentMethodsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onBanksUpdated();
    }
}
