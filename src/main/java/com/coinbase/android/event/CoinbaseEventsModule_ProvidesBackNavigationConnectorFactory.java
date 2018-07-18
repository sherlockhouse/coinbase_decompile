package com.coinbase.android.event;

import com.coinbase.android.ui.BackNavigationConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBackNavigationConnectorFactory implements Factory<BackNavigationConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBackNavigationConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public BackNavigationConnector get() {
        return provideInstance(this.module);
    }

    public static BackNavigationConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBackNavigationConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBackNavigationConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBackNavigationConnectorFactory(module);
    }

    public static BackNavigationConnector proxyProvidesBackNavigationConnector(CoinbaseEventsModule instance) {
        return (BackNavigationConnector) Preconditions.checkNotNull(instance.providesBackNavigationConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
