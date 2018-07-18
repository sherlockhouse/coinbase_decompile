package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesLinkedAccountConnectorFactory implements Factory<LinkedAccountConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesLinkedAccountConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public LinkedAccountConnector get() {
        return provideInstance(this.module);
    }

    public static LinkedAccountConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesLinkedAccountConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesLinkedAccountConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesLinkedAccountConnectorFactory(module);
    }

    public static LinkedAccountConnector proxyProvidesLinkedAccountConnector(CoinbaseEventsModule instance) {
        return (LinkedAccountConnector) Preconditions.checkNotNull(instance.providesLinkedAccountConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
