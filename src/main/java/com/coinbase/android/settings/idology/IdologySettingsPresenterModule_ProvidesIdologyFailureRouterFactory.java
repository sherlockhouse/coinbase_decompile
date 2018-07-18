package com.coinbase.android.settings.idology;

import com.coinbase.android.idology.IdologyFailureRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class IdologySettingsPresenterModule_ProvidesIdologyFailureRouterFactory implements Factory<IdologyFailureRouter> {
    private final IdologySettingsPresenterModule module;
    private final Provider<InAppIdologyFailureRouter> routerProvider;

    public IdologySettingsPresenterModule_ProvidesIdologyFailureRouterFactory(IdologySettingsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public IdologyFailureRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static IdologyFailureRouter provideInstance(IdologySettingsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        return proxyProvidesIdologyFailureRouter(module, (InAppIdologyFailureRouter) routerProvider.get());
    }

    public static IdologySettingsPresenterModule_ProvidesIdologyFailureRouterFactory create(IdologySettingsPresenterModule module, Provider<InAppIdologyFailureRouter> routerProvider) {
        return new IdologySettingsPresenterModule_ProvidesIdologyFailureRouterFactory(module, routerProvider);
    }

    public static IdologyFailureRouter proxyProvidesIdologyFailureRouter(IdologySettingsPresenterModule instance, InAppIdologyFailureRouter router) {
        return (IdologyFailureRouter) Preconditions.checkNotNull(instance.providesIdologyFailureRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
