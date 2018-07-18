package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TwoFactorPresenterModule_ProvidesScreenFactory implements Factory<TwoFactorScreen> {
    private final TwoFactorPresenterModule module;

    public TwoFactorPresenterModule_ProvidesScreenFactory(TwoFactorPresenterModule module) {
        this.module = module;
    }

    public TwoFactorScreen get() {
        return provideInstance(this.module);
    }

    public static TwoFactorScreen provideInstance(TwoFactorPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static TwoFactorPresenterModule_ProvidesScreenFactory create(TwoFactorPresenterModule module) {
        return new TwoFactorPresenterModule_ProvidesScreenFactory(module);
    }

    public static TwoFactorScreen proxyProvidesScreen(TwoFactorPresenterModule instance) {
        return (TwoFactorScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
