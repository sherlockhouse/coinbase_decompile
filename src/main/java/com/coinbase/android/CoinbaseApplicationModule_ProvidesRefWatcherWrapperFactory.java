package com.coinbase.android;

import com.coinbase.android.utils.ConfigUtils;
import com.coinbase.android.utils.RefWatcherWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseApplicationModule_ProvidesRefWatcherWrapperFactory implements Factory<RefWatcherWrapper> {
    private final Provider<ConfigUtils> configUtilsProvider;
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesRefWatcherWrapperFactory(CoinbaseApplicationModule module, Provider<ConfigUtils> configUtilsProvider) {
        this.module = module;
        this.configUtilsProvider = configUtilsProvider;
    }

    public RefWatcherWrapper get() {
        return provideInstance(this.module, this.configUtilsProvider);
    }

    public static RefWatcherWrapper provideInstance(CoinbaseApplicationModule module, Provider<ConfigUtils> configUtilsProvider) {
        return proxyProvidesRefWatcherWrapper(module, (ConfigUtils) configUtilsProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesRefWatcherWrapperFactory create(CoinbaseApplicationModule module, Provider<ConfigUtils> configUtilsProvider) {
        return new CoinbaseApplicationModule_ProvidesRefWatcherWrapperFactory(module, configUtilsProvider);
    }

    public static RefWatcherWrapper proxyProvidesRefWatcherWrapper(CoinbaseApplicationModule instance, ConfigUtils configUtils) {
        return (RefWatcherWrapper) Preconditions.checkNotNull(instance.providesRefWatcherWrapper(configUtils), "Cannot return null from a non-@Nullable @Provides method");
    }
}
