package com.coinbase.android;

import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesMainHandlerFactory implements Factory<Handler> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesMainHandlerFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public Handler get() {
        return provideInstance(this.module);
    }

    public static Handler provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesMainHandler(module);
    }

    public static CoinbaseApplicationModule_ProvidesMainHandlerFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesMainHandlerFactory(module);
    }

    public static Handler proxyProvidesMainHandler(CoinbaseApplicationModule instance) {
        return (Handler) Preconditions.checkNotNull(instance.providesMainHandler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
