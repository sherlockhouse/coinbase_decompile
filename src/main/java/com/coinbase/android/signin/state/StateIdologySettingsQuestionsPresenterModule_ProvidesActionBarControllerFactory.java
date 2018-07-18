package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final StateIdologySettingsQuestionsPresenterModule module;

    public StateIdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory(StateIdologySettingsQuestionsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(StateIdologySettingsQuestionsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static StateIdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory create(StateIdologySettingsQuestionsPresenterModule module) {
        return new StateIdologySettingsQuestionsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(StateIdologySettingsQuestionsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
