package com.coinbase.android.event;

import com.coinbase.android.settings.AccountsUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountsUpdatedConnectorFactory implements Factory<AccountsUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountsUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountsUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static AccountsUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountsUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountsUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountsUpdatedConnectorFactory(module);
    }

    public static AccountsUpdatedConnector proxyProvidesAccountsUpdatedConnector(CoinbaseEventsModule instance) {
        return (AccountsUpdatedConnector) Preconditions.checkNotNull(instance.providesAccountsUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
