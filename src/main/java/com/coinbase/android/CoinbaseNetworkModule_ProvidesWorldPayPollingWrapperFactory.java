package com.coinbase.android;

import com.coinbase.android.paymentmethods.card.WorldPayPollingWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseNetworkModule_ProvidesWorldPayPollingWrapperFactory implements Factory<WorldPayPollingWrapper> {
    private final CoinbaseNetworkModule module;

    public CoinbaseNetworkModule_ProvidesWorldPayPollingWrapperFactory(CoinbaseNetworkModule module) {
        this.module = module;
    }

    public WorldPayPollingWrapper get() {
        return provideInstance(this.module);
    }

    public static WorldPayPollingWrapper provideInstance(CoinbaseNetworkModule module) {
        return proxyProvidesWorldPayPollingWrapper(module);
    }

    public static CoinbaseNetworkModule_ProvidesWorldPayPollingWrapperFactory create(CoinbaseNetworkModule module) {
        return new CoinbaseNetworkModule_ProvidesWorldPayPollingWrapperFactory(module);
    }

    public static WorldPayPollingWrapper proxyProvidesWorldPayPollingWrapper(CoinbaseNetworkModule instance) {
        return (WorldPayPollingWrapper) Preconditions.checkNotNull(instance.providesWorldPayPollingWrapper(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
