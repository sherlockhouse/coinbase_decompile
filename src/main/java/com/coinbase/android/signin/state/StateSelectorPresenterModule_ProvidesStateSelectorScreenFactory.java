package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateSelectorPresenterModule_ProvidesStateSelectorScreenFactory implements Factory<StateSelectorScreen> {
    private final StateSelectorPresenterModule module;

    public StateSelectorPresenterModule_ProvidesStateSelectorScreenFactory(StateSelectorPresenterModule module) {
        this.module = module;
    }

    public StateSelectorScreen get() {
        return provideInstance(this.module);
    }

    public static StateSelectorScreen provideInstance(StateSelectorPresenterModule module) {
        return proxyProvidesStateSelectorScreen(module);
    }

    public static StateSelectorPresenterModule_ProvidesStateSelectorScreenFactory create(StateSelectorPresenterModule module) {
        return new StateSelectorPresenterModule_ProvidesStateSelectorScreenFactory(module);
    }

    public static StateSelectorScreen proxyProvidesStateSelectorScreen(StateSelectorPresenterModule instance) {
        return (StateSelectorScreen) Preconditions.checkNotNull(instance.providesStateSelectorScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
