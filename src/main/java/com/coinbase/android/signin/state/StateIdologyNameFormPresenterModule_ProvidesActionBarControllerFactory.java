package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyNameFormPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologyNameFormPresenterModule module;

    public StateIdologyNameFormPresenterModule_ProvidesActionBarControllerFactory(StateIdologyNameFormPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologyNameFormPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologyNameFormPresenterModule_ProvidesActionBarControllerFactory create(StateIdologyNameFormPresenterModule module) {
        return new StateIdologyNameFormPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologyNameFormPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
