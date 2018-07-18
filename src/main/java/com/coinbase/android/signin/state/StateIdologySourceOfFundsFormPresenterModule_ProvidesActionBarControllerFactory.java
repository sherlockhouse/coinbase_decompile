package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologySourceOfFundsFormPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologySourceOfFundsFormPresenterModule module;

    public StateIdologySourceOfFundsFormPresenterModule_ProvidesActionBarControllerFactory(StateIdologySourceOfFundsFormPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologySourceOfFundsFormPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologySourceOfFundsFormPresenterModule_ProvidesActionBarControllerFactory create(StateIdologySourceOfFundsFormPresenterModule module) {
        return new StateIdologySourceOfFundsFormPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologySourceOfFundsFormPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
