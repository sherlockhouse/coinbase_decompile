package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyFailurePresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final IdologyFailurePresenterModule module;

    public IdologyFailurePresenterModule_ProvidesActionBarControllerFactory(IdologyFailurePresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(IdologyFailurePresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static IdologyFailurePresenterModule_ProvidesActionBarControllerFactory create(IdologyFailurePresenterModule module) {
        return new IdologyFailurePresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(IdologyFailurePresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
