package com.coinbase.android.event;

import com.coinbase.android.ui.BottomNavigationConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBottomNavigationConnectorFactory implements Factory<BottomNavigationConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBottomNavigationConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public BottomNavigationConnector get() {
        return provideInstance(this.module);
    }

    public static BottomNavigationConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBottomNavigationConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBottomNavigationConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBottomNavigationConnectorFactory(module);
    }

    public static BottomNavigationConnector proxyProvidesBottomNavigationConnector(CoinbaseEventsModule instance) {
        return (BottomNavigationConnector) Preconditions.checkNotNull(instance.providesBottomNavigationConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
