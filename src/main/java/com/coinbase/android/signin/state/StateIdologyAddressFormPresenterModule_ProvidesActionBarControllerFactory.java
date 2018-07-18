package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyAddressFormPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologyAddressFormPresenterModule module;

    public StateIdologyAddressFormPresenterModule_ProvidesActionBarControllerFactory(StateIdologyAddressFormPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologyAddressFormPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologyAddressFormPresenterModule_ProvidesActionBarControllerFactory create(StateIdologyAddressFormPresenterModule module) {
        return new StateIdologyAddressFormPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologyAddressFormPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
