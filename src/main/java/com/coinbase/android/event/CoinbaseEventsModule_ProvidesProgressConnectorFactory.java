package com.coinbase.android.event;

import com.coinbase.android.idology.ProgressConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesProgressConnectorFactory implements Factory<ProgressConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesProgressConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public ProgressConnector get() {
        return provideInstance(this.module);
    }

    public static ProgressConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesProgressConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesProgressConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesProgressConnectorFactory(module);
    }

    public static ProgressConnector proxyProvidesProgressConnector(CoinbaseEventsModule instance) {
        return (ProgressConnector) Preconditions.checkNotNull(instance.providesProgressConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
