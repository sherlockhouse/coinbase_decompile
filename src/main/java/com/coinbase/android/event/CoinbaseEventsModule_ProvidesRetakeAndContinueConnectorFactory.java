package com.coinbase.android.event;

import com.coinbase.android.identityverification.RetakeAndContinueConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesRetakeAndContinueConnectorFactory implements Factory<RetakeAndContinueConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesRetakeAndContinueConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public RetakeAndContinueConnector get() {
        return provideInstance(this.module);
    }

    public static RetakeAndContinueConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesRetakeAndContinueConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesRetakeAndContinueConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesRetakeAndContinueConnectorFactory(module);
    }

    public static RetakeAndContinueConnector proxyProvidesRetakeAndContinueConnector(CoinbaseEventsModule instance) {
        return (RetakeAndContinueConnector) Preconditions.checkNotNull(instance.providesRetakeAndContinueConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
