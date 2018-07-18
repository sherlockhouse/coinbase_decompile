package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPaymentMethodsRefreshedConnectorFactory implements Factory<PaymentMethodsFetchedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPaymentMethodsRefreshedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PaymentMethodsFetchedConnector get() {
        return provideInstance(this.module);
    }

    public static PaymentMethodsFetchedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPaymentMethodsRefreshedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPaymentMethodsRefreshedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPaymentMethodsRefreshedConnectorFactory(module);
    }

    public static PaymentMethodsFetchedConnector proxyProvidesPaymentMethodsRefreshedConnector(CoinbaseEventsModule instance) {
        return (PaymentMethodsFetchedConnector) Preconditions.checkNotNull(instance.providesPaymentMethodsRefreshedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
