package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;

public final class PaymentMethodsViewModule_ProvidesPaymentMethodDeletedResFactory implements Factory<Integer> {
    private final PaymentMethodsViewModule module;

    public PaymentMethodsViewModule_ProvidesPaymentMethodDeletedResFactory(PaymentMethodsViewModule module) {
        this.module = module;
    }

    public Integer get() {
        return provideInstance(this.module);
    }

    public static Integer provideInstance(PaymentMethodsViewModule module) {
        return Integer.valueOf(proxyProvidesPaymentMethodDeletedRes(module));
    }

    public static PaymentMethodsViewModule_ProvidesPaymentMethodDeletedResFactory create(PaymentMethodsViewModule module) {
        return new PaymentMethodsViewModule_ProvidesPaymentMethodDeletedResFactory(module);
    }

    public static int proxyProvidesPaymentMethodDeletedRes(PaymentMethodsViewModule instance) {
        return instance.providesPaymentMethodDeletedRes();
    }
}
