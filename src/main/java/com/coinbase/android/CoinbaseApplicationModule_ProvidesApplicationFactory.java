package com.coinbase.android;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesApplicationFactory implements Factory<Application> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesApplicationFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public Application get() {
        return provideInstance(this.module);
    }

    public static Application provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesApplication(module);
    }

    public static CoinbaseApplicationModule_ProvidesApplicationFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesApplicationFactory(module);
    }

    public static Application proxyProvidesApplication(CoinbaseApplicationModule instance) {
        return (Application) Preconditions.checkNotNull(instance.providesApplication(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
