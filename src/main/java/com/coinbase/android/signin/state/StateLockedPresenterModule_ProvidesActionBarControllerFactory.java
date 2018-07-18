package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateLockedPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateLockedPresenterModule module;

    public StateLockedPresenterModule_ProvidesActionBarControllerFactory(StateLockedPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateLockedPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateLockedPresenterModule_ProvidesActionBarControllerFactory create(StateLockedPresenterModule module) {
        return new StateLockedPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateLockedPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
