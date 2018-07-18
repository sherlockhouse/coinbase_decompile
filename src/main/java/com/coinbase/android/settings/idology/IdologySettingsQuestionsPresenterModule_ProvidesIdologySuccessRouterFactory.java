package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class IdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory implements Factory<IdologySuccessRouter> {
    private final Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider;
    private final IdologySettingsQuestionsPresenterModule module;

    public IdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory(IdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        this.module = module;
        this.idologySuccessSettingsRouterProvider = idologySuccessSettingsRouterProvider;
    }

    public IdologySuccessRouter get() {
        return provideInstance(this.module, this.idologySuccessSettingsRouterProvider);
    }

    public static IdologySuccessRouter provideInstance(IdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        return proxyProvidesIdologySuccessRouter(module, (IdologySuccessSettingsRouter) idologySuccessSettingsRouterProvider.get());
    }

    public static IdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory create(IdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        return new IdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory(module, idologySuccessSettingsRouterProvider);
    }

    public static IdologySuccessRouter proxyProvidesIdologySuccessRouter(IdologySettingsQuestionsPresenterModule instance, IdologySuccessSettingsRouter idologySuccessSettingsRouter) {
        return (IdologySuccessRouter) Preconditions.checkNotNull(instance.providesIdologySuccessRouter(idologySuccessSettingsRouter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
