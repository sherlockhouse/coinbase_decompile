package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFailureRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory implements Factory<IdologyFailureRouter> {
    private final StateIdologySettingsQuestionsPresenterModule module;
    private final Provider<UpfrontKycIdologyFailureRouter> routerProvider;

    public StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory(StateIdologySettingsQuestionsPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public IdologyFailureRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static IdologyFailureRouter provideInstance(StateIdologySettingsQuestionsPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return proxyProvidesIdologyFailureRouter(module, (UpfrontKycIdologyFailureRouter) routerProvider.get());
    }

    public static StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory create(StateIdologySettingsQuestionsPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return new StateIdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory(module, routerProvider);
    }

    public static IdologyFailureRouter proxyProvidesIdologyFailureRouter(StateIdologySettingsQuestionsPresenterModule instance, UpfrontKycIdologyFailureRouter router) {
        return (IdologyFailureRouter) Preconditions.checkNotNull(instance.providesIdologyFailureRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
