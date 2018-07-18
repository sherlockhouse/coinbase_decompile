package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateSuspendedPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateSuspendedPresenterModule module;

    public StateSuspendedPresenterModule_ProvidesActionBarControllerFactory(StateSuspendedPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateSuspendedPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateSuspendedPresenterModule_ProvidesActionBarControllerFactory create(StateSuspendedPresenterModule module) {
        return new StateSuspendedPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateSuspendedPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
