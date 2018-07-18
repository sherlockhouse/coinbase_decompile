package com.coinbase.android.paymentmethods;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$2 implements Action1 {
    private final PaymentMethodsPresenter arg$1;

    private PaymentMethodsPresenter$$Lambda$2(PaymentMethodsPresenter paymentMethodsPresenter) {
        this.arg$1 = paymentMethodsPresenter;
    }

    public static Action1 lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter) {
        return new PaymentMethodsPresenter$$Lambda$2(paymentMethodsPresenter);
    }

    public void call(Object obj) {
        PaymentMethodsPresenter.lambda$refreshPaymentMethods$1(this.arg$1, (Pair) obj);
    }
}
