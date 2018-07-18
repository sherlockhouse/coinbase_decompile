package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyFormValidConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyFormValidConnectorFactory implements Factory<IdologyFormValidConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyFormValidConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyFormValidConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyFormValidConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyFormValidConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyFormValidConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyFormValidConnectorFactory(module);
    }

    public static IdologyFormValidConnector proxyProvidesIdologyFormValidConnector(CoinbaseEventsModule instance) {
        return (IdologyFormValidConnector) Preconditions.checkNotNull(instance.providesIdologyFormValidConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
