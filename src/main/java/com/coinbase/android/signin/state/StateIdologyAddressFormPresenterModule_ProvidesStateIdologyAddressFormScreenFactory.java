package com.coinbase.android.signin.state;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyAddressFormPresenterModule_ProvidesStateIdologyAddressFormScreenFactory implements Factory<StateIdologyAddressFormScreen> {
    private final StateIdologyAddressFormPresenterModule module;

    public StateIdologyAddressFormPresenterModule_ProvidesStateIdologyAddressFormScreenFactory(StateIdologyAddressFormPresenterModule module) {
        this.module = module;
    }

    public StateIdologyAddressFormScreen get() {
        return provideInstance(this.module);
    }

    public static StateIdologyAddressFormScreen provideInstance(StateIdologyAddressFormPresenterModule module) {
        return proxyProvidesStateIdologyAddressFormScreen(module);
    }

    public static StateIdologyAddressFormPresenterModule_ProvidesStateIdologyAddressFormScreenFactory create(StateIdologyAddressFormPresenterModule module) {
        return new StateIdologyAddressFormPresenterModule_ProvidesStateIdologyAddressFormScreenFactory(module);
    }

    public static StateIdologyAddressFormScreen proxyProvidesStateIdologyAddressFormScreen(StateIdologyAddressFormPresenterModule instance) {
        return (StateIdologyAddressFormScreen) Preconditions.checkNotNull(instance.providesStateIdologyAddressFormScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
