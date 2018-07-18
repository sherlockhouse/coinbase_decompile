package com.coinbase.android.event;

import com.coinbase.android.ui.RefreshRequestedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesRefreshRequestedConnectorFactory implements Factory<RefreshRequestedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesRefreshRequestedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public RefreshRequestedConnector get() {
        return provideInstance(this.module);
    }

    public static RefreshRequestedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesRefreshRequestedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesRefreshRequestedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesRefreshRequestedConnectorFactory(module);
    }

    public static RefreshRequestedConnector proxyProvidesRefreshRequestedConnector(CoinbaseEventsModule instance) {
        return (RefreshRequestedConnector) Preconditions.checkNotNull(instance.providesRefreshRequestedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
