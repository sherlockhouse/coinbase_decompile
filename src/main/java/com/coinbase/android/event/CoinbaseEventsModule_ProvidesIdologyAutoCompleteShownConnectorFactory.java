package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyAutoCompleteShownConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyAutoCompleteShownConnectorFactory implements Factory<IdologyAutoCompleteShownConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyAutoCompleteShownConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyAutoCompleteShownConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyAutoCompleteShownConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyAutoCompleteShownConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyAutoCompleteShownConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyAutoCompleteShownConnectorFactory(module);
    }

    public static IdologyAutoCompleteShownConnector proxyProvidesIdologyAutoCompleteShownConnector(CoinbaseEventsModule instance) {
        return (IdologyAutoCompleteShownConnector) Preconditions.checkNotNull(instance.providesIdologyAutoCompleteShownConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
