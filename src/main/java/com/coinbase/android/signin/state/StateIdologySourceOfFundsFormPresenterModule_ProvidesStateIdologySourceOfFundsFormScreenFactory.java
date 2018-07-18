package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologySourceOfFundsFormPresenterModule_ProvidesStateIdologySourceOfFundsFormScreenFactory implements Factory<StateIdologySourceOfFundsScreen> {
    private final StateIdologySourceOfFundsFormPresenterModule module;

    public StateIdologySourceOfFundsFormPresenterModule_ProvidesStateIdologySourceOfFundsFormScreenFactory(StateIdologySourceOfFundsFormPresenterModule module) {
        this.module = module;
    }

    public StateIdologySourceOfFundsScreen get() {
        return provideInstance(this.module);
    }

    public static StateIdologySourceOfFundsScreen provideInstance(StateIdologySourceOfFundsFormPresenterModule module) {
        return proxyProvidesStateIdologySourceOfFundsFormScreen(module);
    }

    public static StateIdologySourceOfFundsFormPresenterModule_ProvidesStateIdologySourceOfFundsFormScreenFactory create(StateIdologySourceOfFundsFormPresenterModule module) {
        return new StateIdologySourceOfFundsFormPresenterModule_ProvidesStateIdologySourceOfFundsFormScreenFactory(module);
    }

    public static StateIdologySourceOfFundsScreen proxyProvidesStateIdologySourceOfFundsFormScreen(StateIdologySourceOfFundsFormPresenterModule instance) {
        return (StateIdologySourceOfFundsScreen) Preconditions.checkNotNull(instance.providesStateIdologySourceOfFundsFormScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
