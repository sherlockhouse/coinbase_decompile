package com.coinbase.android.signin.state;

import com.coinbase.android.settings.idology.IdologySettingsQuestionsScreen;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory implements Factory<IdologySettingsQuestionsScreen> {
    private final StateIdologySettingsQuestionsPresenterModule module;

    public StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(StateIdologySettingsQuestionsPresenterModule module) {
        this.module = module;
    }

    public IdologySettingsQuestionsScreen get() {
        return provideInstance(this.module);
    }

    public static IdologySettingsQuestionsScreen provideInstance(StateIdologySettingsQuestionsPresenterModule module) {
        return proxyProvidesIdologyQuestionsScreen(module);
    }

    public static StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory create(StateIdologySettingsQuestionsPresenterModule module) {
        return new StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(module);
    }

    public static IdologySettingsQuestionsScreen proxyProvidesIdologyQuestionsScreen(StateIdologySettingsQuestionsPresenterModule instance) {
        return (IdologySettingsQuestionsScreen) Preconditions.checkNotNull(instance.providesIdologyQuestionsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
