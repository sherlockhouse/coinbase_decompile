package com.coinbase.android.paymentmethods;

import android.util.Pair;
import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$8 implements Action1 {
    private final PaymentMethodsPresenter arg$1;
    private final List arg$2;

    private PaymentMethodsPresenter$$Lambda$8(PaymentMethodsPresenter paymentMethodsPresenter, List list) {
        this.arg$1 = paymentMethodsPresenter;
        this.arg$2 = list;
    }

    public static Action1 lambdaFactory$(PaymentMethodsPresenter paymentMethodsPresenter, List list) {
        return new PaymentMethodsPresenter$$Lambda$8(paymentMethodsPresenter, list);
    }

    public void call(Object obj) {
        PaymentMethodsPresenter.lambda$getAvailablePaymentMethods$7(this.arg$1, this.arg$2, (Pair) obj);
    }
}
