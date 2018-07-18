package com.coinbase.android.ui;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class MainControllerPresenterModule_ProvidesMainScreenFactory implements Factory<MainScreen> {
    private final MainControllerPresenterModule module;

    public MainControllerPresenterModule_ProvidesMainScreenFactory(MainControllerPresenterModule module) {
        this.module = module;
    }

    public MainScreen get() {
        return provideInstance(this.module);
    }

    public static MainScreen provideInstance(MainControllerPresenterModule module) {
        return proxyProvidesMainScreen(module);
    }

    public static MainControllerPresenterModule_ProvidesMainScreenFactory create(MainControllerPresenterModule module) {
        return new MainControllerPresenterModule_ProvidesMainScreenFactory(module);
    }

    public static MainScreen proxyProvidesMainScreen(MainControllerPresenterModule instance) {
        return (MainScreen) Preconditions.checkNotNull(instance.providesMainScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
