package com.coinbase.android.event;

import com.coinbase.android.ui.CurrencySelectorConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesCurrencySelectorConnectorFactory implements Factory<CurrencySelectorConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesCurrencySelectorConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public CurrencySelectorConnector get() {
        return provideInstance(this.module);
    }

    public static CurrencySelectorConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesCurrencySelectorConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesCurrencySelectorConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesCurrencySelectorConnectorFactory(module);
    }

    public static CurrencySelectorConnector proxyProvidesCurrencySelectorConnector(CoinbaseEventsModule instance) {
        return (CurrencySelectorConnector) Preconditions.checkNotNull(instance.providesCurrencySelectorConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
