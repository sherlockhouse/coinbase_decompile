package com.coinbase.android.settings.idology;

import com.coinbase.android.idology.IdologyFailureRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class IdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory implements Factory<IdologyFailureRouter> {
    private final IdologySettingsQuestionsPresenterModule module;
    private final Provider<InAppIdologyFailureRouter> routerProvider;

    public IdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory(IdologySettingsQuestionsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public IdologyFailureRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static IdologyFailureRouter provideInstance(IdologySettingsQuestionsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        return proxyProvidesIdologyFailureRouter(module, (InAppIdologyFailureRouter) routerProvider.get());
    }

    public static IdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory create(IdologySettingsQuestionsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        return new IdologySettingsQuestionsPresenterModule_ProvidesIdologyFailureRouterFactory(module, routerProvider);
    }

    public static IdologyFailureRouter proxyProvidesIdologyFailureRouter(IdologySettingsQuestionsPresenterModule instance, InAppIdologyFailureRouter router) {
        return (IdologyFailureRouter) Preconditions.checkNotNull(instance.providesIdologyFailureRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
