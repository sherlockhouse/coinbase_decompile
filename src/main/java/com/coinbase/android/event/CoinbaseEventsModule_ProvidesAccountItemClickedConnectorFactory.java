package com.coinbase.android.event;

import com.coinbase.android.accounts.AccountItemClickedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountItemClickedConnectorFactory implements Factory<AccountItemClickedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountItemClickedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountItemClickedConnector get() {
        return provideInstance(this.module);
    }

    public static AccountItemClickedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountItemClickedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountItemClickedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountItemClickedConnectorFactory(module);
    }

    public static AccountItemClickedConnector proxyProvidesAccountItemClickedConnector(CoinbaseEventsModule instance) {
        return (AccountItemClickedConnector) Preconditions.checkNotNull(instance.providesAccountItemClickedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
