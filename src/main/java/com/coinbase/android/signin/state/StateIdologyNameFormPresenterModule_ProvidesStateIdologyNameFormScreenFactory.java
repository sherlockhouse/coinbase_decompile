package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyNameFormPresenterModule_ProvidesStateIdologyNameFormScreenFactory implements Factory<StateIdologyFormScreen> {
    private final StateIdologyNameFormPresenterModule module;

    public StateIdologyNameFormPresenterModule_ProvidesStateIdologyNameFormScreenFactory(StateIdologyNameFormPresenterModule module) {
        this.module = module;
    }

    public StateIdologyFormScreen get() {
        return provideInstance(this.module);
    }

    public static StateIdologyFormScreen provideInstance(StateIdologyNameFormPresenterModule module) {
        return proxyProvidesStateIdologyNameFormScreen(module);
    }

    public static StateIdologyNameFormPresenterModule_ProvidesStateIdologyNameFormScreenFactory create(StateIdologyNameFormPresenterModule module) {
        return new StateIdologyNameFormPresenterModule_ProvidesStateIdologyNameFormScreenFactory(module);
    }

    public static StateIdologyFormScreen proxyProvidesStateIdologyNameFormScreen(StateIdologyNameFormPresenterModule instance) {
        return (StateIdologyFormScreen) Preconditions.checkNotNull(instance.providesStateIdologyNameFormScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
