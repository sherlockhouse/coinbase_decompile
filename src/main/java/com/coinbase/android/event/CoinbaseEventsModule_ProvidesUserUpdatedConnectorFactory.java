package com.coinbase.android.event;

import com.coinbase.android.settings.UserUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesUserUpdatedConnectorFactory implements Factory<UserUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesUserUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public UserUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static UserUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesUserUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesUserUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesUserUpdatedConnectorFactory(module);
    }

    public static UserUpdatedConnector proxyProvidesUserUpdatedConnector(CoinbaseEventsModule instance) {
        return (UserUpdatedConnector) Preconditions.checkNotNull(instance.providesUserUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
