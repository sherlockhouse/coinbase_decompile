package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyFailureRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StateIdologySourceOfFundsFormPresenterModule_ProvidesIdologyFailureRouterFactory implements Factory<IdologyFailureRouter> {
    private final StateIdologySourceOfFundsFormPresenterModule module;
    private final Provider<UpfrontKycIdologyFailureRouter> routerProvider;

    public StateIdologySourceOfFundsFormPresenterModule_ProvidesIdologyFailureRouterFactory(StateIdologySourceOfFundsFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public IdologyFailureRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static IdologyFailureRouter provideInstance(StateIdologySourceOfFundsFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return proxyProvidesIdologyFailureRouter(module, (UpfrontKycIdologyFailureRouter) routerProvider.get());
    }

    public static StateIdologySourceOfFundsFormPresenterModule_ProvidesIdologyFailureRouterFactory create(StateIdologySourceOfFundsFormPresenterModule module, Provider<UpfrontKycIdologyFailureRouter> routerProvider) {
        return new StateIdologySourceOfFundsFormPresenterModule_ProvidesIdologyFailureRouterFactory(module, routerProvider);
    }

    public static IdologyFailureRouter proxyProvidesIdologyFailureRouter(StateIdologySourceOfFundsFormPresenterModule instance, UpfrontKycIdologyFailureRouter router) {
        return (IdologyFailureRouter) Preconditions.checkNotNull(instance.providesIdologyFailureRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
