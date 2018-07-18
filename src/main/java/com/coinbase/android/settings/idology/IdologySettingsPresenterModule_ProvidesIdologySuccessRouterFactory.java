package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class IdologySettingsPresenterModule_ProvidesIdologySuccessRouterFactory implements Factory<IdologySuccessRouter> {
    private final Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider;
    private final IdologySettingsPresenterModule module;

    public IdologySettingsPresenterModule_ProvidesIdologySuccessRouterFactory(IdologySettingsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        this.module = module;
        this.idologySuccessSettingsRouterProvider = idologySuccessSettingsRouterProvider;
    }

    public IdologySuccessRouter get() {
        return provideInstance(this.module, this.idologySuccessSettingsRouterProvider);
    }

    public static IdologySuccessRouter provideInstance(IdologySettingsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        return proxyProvidesIdologySuccessRouter(module, (IdologySuccessSettingsRouter) idologySuccessSettingsRouterProvider.get());
    }

    public static IdologySettingsPresenterModule_ProvidesIdologySuccessRouterFactory create(IdologySettingsPresenterModule module, Provider<IdologySuccessSettingsRouter> idologySuccessSettingsRouterProvider) {
        return new IdologySettingsPresenterModule_ProvidesIdologySuccessRouterFactory(module, idologySuccessSettingsRouterProvider);
    }

    public static IdologySuccessRouter proxyProvidesIdologySuccessRouter(IdologySettingsPresenterModule instance, IdologySuccessSettingsRouter idologySuccessSettingsRouter) {
        return (IdologySuccessRouter) Preconditions.checkNotNull(instance.providesIdologySuccessRouter(idologySuccessSettingsRouter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
