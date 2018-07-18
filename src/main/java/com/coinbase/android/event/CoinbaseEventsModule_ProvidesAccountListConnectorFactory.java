package com.coinbase.android.event;

import com.coinbase.android.accounts.AccountListConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountListConnectorFactory implements Factory<AccountListConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountListConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountListConnector get() {
        return provideInstance(this.module);
    }

    public static AccountListConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountListConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountListConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountListConnectorFactory(module);
    }

    public static AccountListConnector proxyProvidesAccountListConnector(CoinbaseEventsModule instance) {
        return (AccountListConnector) Preconditions.checkNotNull(instance.providesAccountListConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
