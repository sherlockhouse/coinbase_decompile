package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySuccessPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final IdologySuccessPresenterModule module;

    public IdologySuccessPresenterModule_ProvidesActionBarControllerFactory(IdologySuccessPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(IdologySuccessPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static IdologySuccessPresenterModule_ProvidesActionBarControllerFactory create(IdologySuccessPresenterModule module) {
        return new IdologySuccessPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(IdologySuccessPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
