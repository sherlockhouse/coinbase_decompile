package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyQuestionsPresenterModule_ProvidesIdologyTrackingContextFactory implements Factory<String> {
    private final IdologyQuestionsPresenterModule module;

    public IdologyQuestionsPresenterModule_ProvidesIdologyTrackingContextFactory(IdologyQuestionsPresenterModule module) {
        this.module = module;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(IdologyQuestionsPresenterModule module) {
        return proxyProvidesIdologyTrackingContext(module);
    }

    public static IdologyQuestionsPresenterModule_ProvidesIdologyTrackingContextFactory create(IdologyQuestionsPresenterModule module) {
        return new IdologyQuestionsPresenterModule_ProvidesIdologyTrackingContextFactory(module);
    }

    public static String proxyProvidesIdologyTrackingContext(IdologyQuestionsPresenterModule instance) {
        return (String) Preconditions.checkNotNull(instance.providesIdologyTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
