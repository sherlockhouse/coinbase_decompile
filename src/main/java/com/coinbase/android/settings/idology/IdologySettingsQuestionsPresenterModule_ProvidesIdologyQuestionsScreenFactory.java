package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory implements Factory<IdologySettingsQuestionsScreen> {
    private final IdologySettingsQuestionsPresenterModule module;

    public IdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(IdologySettingsQuestionsPresenterModule module) {
        this.module = module;
    }

    public IdologySettingsQuestionsScreen get() {
        return provideInstance(this.module);
    }

    public static IdologySettingsQuestionsScreen provideInstance(IdologySettingsQuestionsPresenterModule module) {
        return proxyProvidesIdologyQuestionsScreen(module);
    }

    public static IdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory create(IdologySettingsQuestionsPresenterModule module) {
        return new IdologySettingsQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(module);
    }

    public static IdologySettingsQuestionsScreen proxyProvidesIdologyQuestionsScreen(IdologySettingsQuestionsPresenterModule instance) {
        return (IdologySettingsQuestionsScreen) Preconditions.checkNotNull(instance.providesIdologyQuestionsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
