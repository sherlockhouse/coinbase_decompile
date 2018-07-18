package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologyRetryFormPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologyRetryFormPresenterModule module;

    public StateIdologyRetryFormPresenterModule_ProvidesActionBarControllerFactory(StateIdologyRetryFormPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologyRetryFormPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologyRetryFormPresenterModule_ProvidesActionBarControllerFactory create(StateIdologyRetryFormPresenterModule module) {
        return new StateIdologyRetryFormPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologyRetryFormPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
