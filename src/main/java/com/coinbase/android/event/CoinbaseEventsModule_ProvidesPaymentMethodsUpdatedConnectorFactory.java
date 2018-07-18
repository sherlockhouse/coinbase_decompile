package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPaymentMethodsUpdatedConnectorFactory implements Factory<PaymentMethodsUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPaymentMethodsUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PaymentMethodsUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static PaymentMethodsUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPaymentMethodsUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPaymentMethodsUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPaymentMethodsUpdatedConnectorFactory(module);
    }

    public static PaymentMethodsUpdatedConnector proxyProvidesPaymentMethodsUpdatedConnector(CoinbaseEventsModule instance) {
        return (PaymentMethodsUpdatedConnector) Preconditions.checkNotNull(instance.providesPaymentMethodsUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
