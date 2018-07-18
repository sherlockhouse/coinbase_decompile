package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyFailurePresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologyFailurePresenterModule module;

    public StateIdologyFailurePresenterModule_ProvidesActionBarControllerFactory(StateIdologyFailurePresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologyFailurePresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologyFailurePresenterModule_ProvidesActionBarControllerFactory create(StateIdologyFailurePresenterModule module) {
        return new StateIdologyFailurePresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologyFailurePresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
