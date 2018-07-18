package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyRetryFormPresenterModule_ProvidesStateIdologyRetryFormScreenFactory implements Factory<StateIdologyRetryFormScreen> {
    private final StateIdologyRetryFormPresenterModule module;

    public StateIdologyRetryFormPresenterModule_ProvidesStateIdologyRetryFormScreenFactory(StateIdologyRetryFormPresenterModule module) {
        this.module = module;
    }

    public StateIdologyRetryFormScreen get() {
        return provideInstance(this.module);
    }

    public static StateIdologyRetryFormScreen provideInstance(StateIdologyRetryFormPresenterModule module) {
        return proxyProvidesStateIdologyRetryFormScreen(module);
    }

    public static StateIdologyRetryFormPresenterModule_ProvidesStateIdologyRetryFormScreenFactory create(StateIdologyRetryFormPresenterModule module) {
        return new StateIdologyRetryFormPresenterModule_ProvidesStateIdologyRetryFormScreenFactory(module);
    }

    public static StateIdologyRetryFormScreen proxyProvidesStateIdologyRetryFormScreen(StateIdologyRetryFormPresenterModule instance) {
        return (StateIdologyRetryFormScreen) Preconditions.checkNotNull(instance.providesStateIdologyRetryFormScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
