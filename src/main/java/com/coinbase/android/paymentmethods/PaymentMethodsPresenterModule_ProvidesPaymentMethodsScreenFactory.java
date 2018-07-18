package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PaymentMethodsPresenterModule_ProvidesPaymentMethodsScreenFactory implements Factory<PaymentMethodsScreen> {
    private final PaymentMethodsPresenterModule module;

    public PaymentMethodsPresenterModule_ProvidesPaymentMethodsScreenFactory(PaymentMethodsPresenterModule module) {
        this.module = module;
    }

    public PaymentMethodsScreen get() {
        return provideInstance(this.module);
    }

    public static PaymentMethodsScreen provideInstance(PaymentMethodsPresenterModule module) {
        return proxyProvidesPaymentMethodsScreen(module);
    }

    public static PaymentMethodsPresenterModule_ProvidesPaymentMethodsScreenFactory create(PaymentMethodsPresenterModule module) {
        return new PaymentMethodsPresenterModule_ProvidesPaymentMethodsScreenFactory(module);
    }

    public static PaymentMethodsScreen proxyProvidesPaymentMethodsScreen(PaymentMethodsPresenterModule instance) {
        return (PaymentMethodsScreen) Preconditions.checkNotNull(instance.providesPaymentMethodsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
