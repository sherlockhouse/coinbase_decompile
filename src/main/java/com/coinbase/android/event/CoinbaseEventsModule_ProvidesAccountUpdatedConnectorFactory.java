package com.coinbase.android.event;

import com.coinbase.android.accounts.AccountUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountUpdatedConnectorFactory implements Factory<AccountUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static AccountUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountUpdatedConnectorFactory(module);
    }

    public static AccountUpdatedConnector proxyProvidesAccountUpdatedConnector(CoinbaseEventsModule instance) {
        return (AccountUpdatedConnector) Preconditions.checkNotNull(instance.providesAccountUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
