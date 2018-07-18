package com.coinbase.android.ui;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class MystiqueBottomNavigationPresenterModule_ProvidesScreenFactory implements Factory<MystiqueBottomNavigationScreen> {
    private final MystiqueBottomNavigationPresenterModule module;

    public MystiqueBottomNavigationPresenterModule_ProvidesScreenFactory(MystiqueBottomNavigationPresenterModule module) {
        this.module = module;
    }

    public MystiqueBottomNavigationScreen get() {
        return provideInstance(this.module);
    }

    public static MystiqueBottomNavigationScreen provideInstance(MystiqueBottomNavigationPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static MystiqueBottomNavigationPresenterModule_ProvidesScreenFactory create(MystiqueBottomNavigationPresenterModule module) {
        return new MystiqueBottomNavigationPresenterModule_ProvidesScreenFactory(module);
    }

    public static MystiqueBottomNavigationScreen proxyProvidesScreen(MystiqueBottomNavigationPresenterModule instance) {
        return (MystiqueBottomNavigationScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
