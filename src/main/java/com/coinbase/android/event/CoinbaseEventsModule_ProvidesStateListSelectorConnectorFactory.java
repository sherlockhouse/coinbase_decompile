package com.coinbase.android.event;

import com.coinbase.android.signin.StateListSelectorConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesStateListSelectorConnectorFactory implements Factory<StateListSelectorConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesStateListSelectorConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public StateListSelectorConnector get() {
        return provideInstance(this.module);
    }

    public static StateListSelectorConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesStateListSelectorConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesStateListSelectorConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesStateListSelectorConnectorFactory(module);
    }

    public static StateListSelectorConnector proxyProvidesStateListSelectorConnector(CoinbaseEventsModule instance) {
        return (StateListSelectorConnector) Preconditions.checkNotNull(instance.providesStateListSelectorConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
