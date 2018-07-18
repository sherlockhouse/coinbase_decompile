package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateSuspendedPresenterModule_ProvidesStateSuspendedScreenFactory implements Factory<StateSuspendedScreen> {
    private final StateSuspendedPresenterModule module;

    public StateSuspendedPresenterModule_ProvidesStateSuspendedScreenFactory(StateSuspendedPresenterModule module) {
        this.module = module;
    }

    public StateSuspendedScreen get() {
        return provideInstance(this.module);
    }

    public static StateSuspendedScreen provideInstance(StateSuspendedPresenterModule module) {
        return proxyProvidesStateSuspendedScreen(module);
    }

    public static StateSuspendedPresenterModule_ProvidesStateSuspendedScreenFactory create(StateSuspendedPresenterModule module) {
        return new StateSuspendedPresenterModule_ProvidesStateSuspendedScreenFactory(module);
    }

    public static StateSuspendedScreen proxyProvidesStateSuspendedScreen(StateSuspendedPresenterModule instance) {
        return (StateSuspendedScreen) Preconditions.checkNotNull(instance.providesStateSuspendedScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
