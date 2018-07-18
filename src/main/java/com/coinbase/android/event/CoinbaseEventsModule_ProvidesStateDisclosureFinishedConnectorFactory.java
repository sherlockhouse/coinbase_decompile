package com.coinbase.android.event;

import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesStateDisclosureFinishedConnectorFactory implements Factory<StateDisclosureFinishedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesStateDisclosureFinishedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public StateDisclosureFinishedConnector get() {
        return provideInstance(this.module);
    }

    public static StateDisclosureFinishedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesStateDisclosureFinishedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesStateDisclosureFinishedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesStateDisclosureFinishedConnectorFactory(module);
    }

    public static StateDisclosureFinishedConnector proxyProvidesStateDisclosureFinishedConnector(CoinbaseEventsModule instance) {
        return (StateDisclosureFinishedConnector) Preconditions.checkNotNull(instance.providesStateDisclosureFinishedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
