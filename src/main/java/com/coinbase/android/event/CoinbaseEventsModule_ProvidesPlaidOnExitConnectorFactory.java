package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.PlaidOnExitConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPlaidOnExitConnectorFactory implements Factory<PlaidOnExitConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPlaidOnExitConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PlaidOnExitConnector get() {
        return provideInstance(this.module);
    }

    public static PlaidOnExitConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPlaidOnExitConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPlaidOnExitConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPlaidOnExitConnectorFactory(module);
    }

    public static PlaidOnExitConnector proxyProvidesPlaidOnExitConnector(CoinbaseEventsModule instance) {
        return (PlaidOnExitConnector) Preconditions.checkNotNull(instance.providesPlaidOnExitConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
