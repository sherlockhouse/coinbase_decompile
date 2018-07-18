package com.coinbase.android.utils;

import com.coinbase.v2.models.paymentMethods.Data;
import java.util.Comparator;

final /* synthetic */ class PaymentMethodUtils$$Lambda$1 implements Comparator {
    private static final PaymentMethodUtils$$Lambda$1 instance = new PaymentMethodUtils$$Lambda$1();

    private PaymentMethodUtils$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return PaymentMethodUtils.lambda$filterPaymentMethods$0((Data) obj, (Data) obj2);
    }
}
