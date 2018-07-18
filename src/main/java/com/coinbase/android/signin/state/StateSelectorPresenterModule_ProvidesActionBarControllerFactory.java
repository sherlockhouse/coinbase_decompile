package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateSelectorPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateSelectorPresenterModule module;

    public StateSelectorPresenterModule_ProvidesActionBarControllerFactory(StateSelectorPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateSelectorPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateSelectorPresenterModule_ProvidesActionBarControllerFactory create(StateSelectorPresenterModule module) {
        return new StateSelectorPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateSelectorPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
