package com.coinbase.android;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesDefaultSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesDefaultSharedPreferencesFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public SharedPreferences get() {
        return provideInstance(this.module);
    }

    public static SharedPreferences provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesDefaultSharedPreferences(module);
    }

    public static CoinbaseApplicationModule_ProvidesDefaultSharedPreferencesFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesDefaultSharedPreferencesFactory(module);
    }

    public static SharedPreferences proxyProvidesDefaultSharedPreferences(CoinbaseApplicationModule instance) {
        return (SharedPreferences) Preconditions.checkNotNull(instance.providesDefaultSharedPreferences(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
