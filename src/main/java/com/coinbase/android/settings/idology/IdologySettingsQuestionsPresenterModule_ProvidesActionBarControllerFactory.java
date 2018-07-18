package com.coinbase.android.settings.idology;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final IdologySettingsQuestionsPresenterModule module;

    public IdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory(IdologySettingsQuestionsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(IdologySettingsQuestionsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static IdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory create(IdologySettingsQuestionsPresenterModule module) {
        return new IdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(IdologySettingsQuestionsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
