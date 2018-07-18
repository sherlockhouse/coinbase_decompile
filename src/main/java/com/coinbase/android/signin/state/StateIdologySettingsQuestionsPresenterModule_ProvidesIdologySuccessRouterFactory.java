package com.coinbase.android.signin.state;

import com.coinbase.android.settings.idology.IdologySuccessRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StateIdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory implements Factory<IdologySuccessRouter> {
    private final Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider;
    private final StateIdologySettingsQuestionsPresenterModule module;

    public StateIdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory(StateIdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        this.module = module;
        this.idologySuccessAuthRouterProvider = idologySuccessAuthRouterProvider;
    }

    public IdologySuccessRouter get() {
        return provideInstance(this.module, this.idologySuccessAuthRouterProvider);
    }

    public static IdologySuccessRouter provideInstance(StateIdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        return proxyProvidesIdologySuccessRouter(module, (IdologySuccessAuthRouter) idologySuccessAuthRouterProvider.get());
    }

    public static StateIdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory create(StateIdologySettingsQuestionsPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        return new StateIdologySettingsQuestionsPresenterModule_ProvidesIdologySuccessRouterFactory(module, idologySuccessAuthRouterProvider);
    }

    public static IdologySuccessRouter proxyProvidesIdologySuccessRouter(StateIdologySettingsQuestionsPresenterModule instance, IdologySuccessAuthRouter idologySuccessAuthRouter) {
        return (IdologySuccessRouter) Preconditions.checkNotNull(instance.providesIdologySuccessRouter(idologySuccessAuthRouter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
