package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.AddPaymentMethodConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAddPaymentMethodConnectorFactory implements Factory<AddPaymentMethodConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAddPaymentMethodConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AddPaymentMethodConnector get() {
        return provideInstance(this.module);
    }

    public static AddPaymentMethodConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAddPaymentMethodConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAddPaymentMethodConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAddPaymentMethodConnectorFactory(module);
    }

    public static AddPaymentMethodConnector proxyProvidesAddPaymentMethodConnector(CoinbaseEventsModule instance) {
        return (AddPaymentMethodConnector) Preconditions.checkNotNull(instance.providesAddPaymentMethodConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
