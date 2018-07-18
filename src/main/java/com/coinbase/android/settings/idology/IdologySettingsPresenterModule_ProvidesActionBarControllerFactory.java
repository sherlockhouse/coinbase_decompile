package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySettingsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final IdologySettingsPresenterModule module;

    public IdologySettingsPresenterModule_ProvidesActionBarControllerFactory(IdologySettingsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(IdologySettingsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static IdologySettingsPresenterModule_ProvidesActionBarControllerFactory create(IdologySettingsPresenterModule module) {
        return new IdologySettingsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(IdologySettingsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
