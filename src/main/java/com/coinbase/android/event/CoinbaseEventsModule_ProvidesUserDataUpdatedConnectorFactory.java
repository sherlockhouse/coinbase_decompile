package com.coinbase.android.event;

import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesUserDataUpdatedConnectorFactory implements Factory<LocalUserDataUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesUserDataUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public LocalUserDataUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static LocalUserDataUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesUserDataUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesUserDataUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesUserDataUpdatedConnectorFactory(module);
    }

    public static LocalUserDataUpdatedConnector proxyProvidesUserDataUpdatedConnector(CoinbaseEventsModule instance) {
        return (LocalUserDataUpdatedConnector) Preconditions.checkNotNull(instance.providesUserDataUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
