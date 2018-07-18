package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateLockedPresenterModule_ProvidesStateLockedScreenFactory implements Factory<StateLockedScreen> {
    private final StateLockedPresenterModule module;

    public StateLockedPresenterModule_ProvidesStateLockedScreenFactory(StateLockedPresenterModule module) {
        this.module = module;
    }

    public StateLockedScreen get() {
        return provideInstance(this.module);
    }

    public static StateLockedScreen provideInstance(StateLockedPresenterModule module) {
        return proxyProvidesStateLockedScreen(module);
    }

    public static StateLockedPresenterModule_ProvidesStateLockedScreenFactory create(StateLockedPresenterModule module) {
        return new StateLockedPresenterModule_ProvidesStateLockedScreenFactory(module);
    }

    public static StateLockedScreen proxyProvidesStateLockedScreen(StateLockedPresenterModule instance) {
        return (StateLockedScreen) Preconditions.checkNotNull(instance.providesStateLockedScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
