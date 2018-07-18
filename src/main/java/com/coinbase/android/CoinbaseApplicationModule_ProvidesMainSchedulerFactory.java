package com.coinbase.android;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.Scheduler;

public final class CoinbaseApplicationModule_ProvidesMainSchedulerFactory implements Factory<Scheduler> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesMainSchedulerFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public Scheduler get() {
        return provideInstance(this.module);
    }

    public static Scheduler provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesMainScheduler(module);
    }

    public static CoinbaseApplicationModule_ProvidesMainSchedulerFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesMainSchedulerFactory(module);
    }

    public static Scheduler proxyProvidesMainScheduler(CoinbaseApplicationModule instance) {
        return (Scheduler) Preconditions.checkNotNull(instance.providesMainScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
