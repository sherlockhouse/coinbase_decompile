package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.VerifyPaymentMethodConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesVerifyPaymentMethodConnectorFactory implements Factory<VerifyPaymentMethodConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesVerifyPaymentMethodConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public VerifyPaymentMethodConnector get() {
        return provideInstance(this.module);
    }

    public static VerifyPaymentMethodConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesVerifyPaymentMethodConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesVerifyPaymentMethodConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesVerifyPaymentMethodConnectorFactory(module);
    }

    public static VerifyPaymentMethodConnector proxyProvidesVerifyPaymentMethodConnector(CoinbaseEventsModule instance) {
        return (VerifyPaymentMethodConnector) Preconditions.checkNotNull(instance.providesVerifyPaymentMethodConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
