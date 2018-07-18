package com.coinbase.android;

import com.coinbase.android.utils.ConfigUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesConfigUtilsFactory implements Factory<ConfigUtils> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesConfigUtilsFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public ConfigUtils get() {
        return provideInstance(this.module);
    }

    public static ConfigUtils provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesConfigUtils(module);
    }

    public static CoinbaseApplicationModule_ProvidesConfigUtilsFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesConfigUtilsFactory(module);
    }

    public static ConfigUtils proxyProvidesConfigUtils(CoinbaseApplicationModule instance) {
        return (ConfigUtils) Preconditions.checkNotNull(instance.providesConfigUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
