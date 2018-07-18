package com.coinbase.android.signin.state;

import com.coinbase.android.settings.idology.IdologySuccessRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StateIdologyRetryFormPresenterModule_ProvidesIdologySuccessRouterFactory implements Factory<IdologySuccessRouter> {
    private final Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider;
    private final StateIdologyRetryFormPresenterModule module;

    public StateIdologyRetryFormPresenterModule_ProvidesIdologySuccessRouterFactory(StateIdologyRetryFormPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        this.module = module;
        this.idologySuccessAuthRouterProvider = idologySuccessAuthRouterProvider;
    }

    public IdologySuccessRouter get() {
        return provideInstance(this.module, this.idologySuccessAuthRouterProvider);
    }

    public static IdologySuccessRouter provideInstance(StateIdologyRetryFormPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        return proxyProvidesIdologySuccessRouter(module, (IdologySuccessAuthRouter) idologySuccessAuthRouterProvider.get());
    }

    public static StateIdologyRetryFormPresenterModule_ProvidesIdologySuccessRouterFactory create(StateIdologyRetryFormPresenterModule module, Provider<IdologySuccessAuthRouter> idologySuccessAuthRouterProvider) {
        return new StateIdologyRetryFormPresenterModule_ProvidesIdologySuccessRouterFactory(module, idologySuccessAuthRouterProvider);
    }

    public static IdologySuccessRouter proxyProvidesIdologySuccessRouter(StateIdologyRetryFormPresenterModule instance, IdologySuccessAuthRouter idologySuccessAuthRouter) {
        return (IdologySuccessRouter) Preconditions.checkNotNull(instance.providesIdologySuccessRouter(idologySuccessAuthRouter), "Cannot return null from a non-@Nullable @Provides method");
    }
}
