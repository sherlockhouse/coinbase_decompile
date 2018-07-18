package com.coinbase.android;

import com.coinbase.api.internal.CoinbaseInternal;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseNetworkModule_ProvidesCoinbaseInternalFactory implements Factory<CoinbaseInternal> {
    private final CoinbaseNetworkModule module;

    public CoinbaseNetworkModule_ProvidesCoinbaseInternalFactory(CoinbaseNetworkModule module) {
        this.module = module;
    }

    public CoinbaseInternal get() {
        return provideInstance(this.module);
    }

    public static CoinbaseInternal provideInstance(CoinbaseNetworkModule module) {
        return proxyProvidesCoinbaseInternal(module);
    }

    public static CoinbaseNetworkModule_ProvidesCoinbaseInternalFactory create(CoinbaseNetworkModule module) {
        return new CoinbaseNetworkModule_ProvidesCoinbaseInternalFactory(module);
    }

    public static CoinbaseInternal proxyProvidesCoinbaseInternal(CoinbaseNetworkModule instance) {
        return (CoinbaseInternal) Preconditions.checkNotNull(instance.providesCoinbaseInternal(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
