package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFailureRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StateIdologyRetryFormPresenterModule_ProvidesIdologyFailureRouterFactory implements Factory<IdologyFailureRouter> {
    private final StateIdologyRetryFormPresenterModule module;
    private final Provider<UpfrontKycIdologyFailureRouter> routerProvider;

    public StateIdologyRetryFormPresenterModule_ProvidesIdologyFailureRouterFactory(StateIdologyRetryFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public IdologyFailureRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static IdologyFailureRouter provideInstance(StateIdologyRetryFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return proxyProvidesIdologyFailureRouter(module, (UpfrontKycIdologyFailureRouter) routerProvider.get());
    }

    public static StateIdologyRetryFormPresenterModule_ProvidesIdologyFailureRouterFactory create(StateIdologyRetryFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return new StateIdologyRetryFormPresenterModule_ProvidesIdologyFailureRouterFactory(module, routerProvider);
    }

    public static IdologyFailureRouter proxyProvidesIdologyFailureRouter(StateIdologyRetryFormPresenterModule instance, UpfrontKycIdologyFailureRouter router) {
        return (IdologyFailureRouter) Preconditions.checkNotNull(instance.providesIdologyFailureRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
