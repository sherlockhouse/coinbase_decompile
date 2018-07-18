package com.coinbase.android.paymentmethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod;
import java.util.Comparator;
import java.util.List;

final /* synthetic */ class PaymentMethodsPresenter$$Lambda$10 implements Comparator {
    private final List arg$1;

    private PaymentMethodsPresenter$$Lambda$10(List list) {
        this.arg$1 = list;
    }

    public static Comparator lambdaFactory$(List list) {
        return new PaymentMethodsPresenter$$Lambda$10(list);
    }

    public int compare(Object obj, Object obj2) {
        return PaymentMethodsPresenter.lambda$filterAvailablePaymentMethods$9(this.arg$1, (AvailablePaymentMethod) obj, (AvailablePaymentMethod) obj2);
    }
}
