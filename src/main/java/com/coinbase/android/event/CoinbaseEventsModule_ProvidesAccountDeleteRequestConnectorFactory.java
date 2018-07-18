package com.coinbase.android.event;

import com.coinbase.android.settings.AccountDeleteRequestConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountDeleteRequestConnectorFactory implements Factory<AccountDeleteRequestConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountDeleteRequestConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountDeleteRequestConnector get() {
        return provideInstance(this.module);
    }

    public static AccountDeleteRequestConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountDeleteRequestConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountDeleteRequestConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountDeleteRequestConnectorFactory(module);
    }

    public static AccountDeleteRequestConnector proxyProvidesAccountDeleteRequestConnector(CoinbaseEventsModule instance) {
        return (AccountDeleteRequestConnector) Preconditions.checkNotNull(instance.providesAccountDeleteRequestConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
