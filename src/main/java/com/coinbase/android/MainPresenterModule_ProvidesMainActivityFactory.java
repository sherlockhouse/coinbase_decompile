package com.coinbase.android;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class MainPresenterModule_ProvidesMainActivityFactory implements Factory<MainActivity> {
    private final MainPresenterModule module;

    public MainPresenterModule_ProvidesMainActivityFactory(MainPresenterModule module) {
        this.module = module;
    }

    public MainActivity get() {
        return provideInstance(this.module);
    }

    public static MainActivity provideInstance(MainPresenterModule module) {
        return proxyProvidesMainActivity(module);
    }

    public static MainPresenterModule_ProvidesMainActivityFactory create(MainPresenterModule module) {
        return new MainPresenterModule_ProvidesMainActivityFactory(module);
    }

    public static MainActivity proxyProvidesMainActivity(MainPresenterModule instance) {
        return (MainActivity) Preconditions.checkNotNull(instance.providesMainActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
