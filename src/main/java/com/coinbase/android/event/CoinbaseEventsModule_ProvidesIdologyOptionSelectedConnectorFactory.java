package com.coinbase.android.event;

import com.coinbase.android.signin.IdologyOptionSelectedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyOptionSelectedConnectorFactory implements Factory<IdologyOptionSelectedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyOptionSelectedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyOptionSelectedConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyOptionSelectedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyOptionSelectedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyOptionSelectedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyOptionSelectedConnectorFactory(module);
    }

    public static IdologyOptionSelectedConnector proxyProvidesIdologyOptionSelectedConnector(CoinbaseEventsModule instance) {
        return (IdologyOptionSelectedConnector) Preconditions.checkNotNull(instance.providesIdologyOptionSelectedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
