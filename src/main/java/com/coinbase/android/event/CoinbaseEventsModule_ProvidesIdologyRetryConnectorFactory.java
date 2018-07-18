package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyRetryConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyRetryConnectorFactory implements Factory<IdologyRetryConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyRetryConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyRetryConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyRetryConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyRetryConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyRetryConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyRetryConnectorFactory(module);
    }

    public static IdologyRetryConnector proxyProvidesIdologyRetryConnector(CoinbaseEventsModule instance) {
        return (IdologyRetryConnector) Preconditions.checkNotNull(instance.providesIdologyRetryConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
