package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory implements Factory<IdologyQuestionsScreen> {
    private final IdologyQuestionsPresenterModule module;

    public IdologyQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(IdologyQuestionsPresenterModule module) {
        this.module = module;
    }

    public IdologyQuestionsScreen get() {
        return provideInstance(this.module);
    }

    public static IdologyQuestionsScreen provideInstance(IdologyQuestionsPresenterModule module) {
        return proxyProvidesIdologyQuestionsScreen(module);
    }

    public static IdologyQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory create(IdologyQuestionsPresenterModule module) {
        return new IdologyQuestionsPresenterModule_ProvidesIdologyQuestionsScreenFactory(module);
    }

    public static IdologyQuestionsScreen proxyProvidesIdologyQuestionsScreen(IdologyQuestionsPresenterModule instance) {
        return (IdologyQuestionsScreen) Preconditions.checkNotNull(instance.providesIdologyQuestionsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
