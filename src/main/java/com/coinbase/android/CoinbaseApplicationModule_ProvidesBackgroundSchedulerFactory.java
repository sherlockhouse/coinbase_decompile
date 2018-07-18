package com.coinbase.android;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.Scheduler;

public final class CoinbaseApplicationModule_ProvidesBackgroundSchedulerFactory implements Factory<Scheduler> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesBackgroundSchedulerFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public Scheduler get() {
        return provideInstance(this.module);
    }

    public static Scheduler provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesBackgroundScheduler(module);
    }

    public static CoinbaseApplicationModule_ProvidesBackgroundSchedulerFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesBackgroundSchedulerFactory(module);
    }

    public static Scheduler proxyProvidesBackgroundScheduler(CoinbaseApplicationModule instance) {
        return (Scheduler) Preconditions.checkNotNull(instance.providesBackgroundScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
