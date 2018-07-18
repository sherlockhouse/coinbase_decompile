package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory implements Factory<StateIdologyFailureScreen> {
    private final StateIdologyFailurePresenterModule module;

    public StateIdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory(StateIdologyFailurePresenterModule module) {
        this.module = module;
    }

    public StateIdologyFailureScreen get() {
        return provideInstance(this.module);
    }

    public static StateIdologyFailureScreen provideInstance(StateIdologyFailurePresenterModule module) {
        return proxyProvidesIdologyFailureScreen(module);
    }

    public static StateIdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory create(StateIdologyFailurePresenterModule module) {
        return new StateIdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory(module);
    }

    public static StateIdologyFailureScreen proxyProvidesIdologyFailureScreen(StateIdologyFailurePresenterModule instance) {
        return (StateIdologyFailureScreen) Preconditions.checkNotNull(instance.providesIdologyFailureScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
