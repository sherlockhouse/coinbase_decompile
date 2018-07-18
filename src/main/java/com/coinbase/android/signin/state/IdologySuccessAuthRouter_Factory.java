package com.coinbase.android.signin.state;

import com.coinbase.android.signin.AuthRouter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologySuccessAuthRouter_Factory implements Factory<IdologySuccessAuthRouter> {
    private final Provider<AuthRouter> authRouterProvider;

    public IdologySuccessAuthRouter_Factory(Provider<AuthRouter> authRouterProvider) {
        this.authRouterProvider = authRouterProvider;
    }

    public IdologySuccessAuthRouter get() {
        return provideInstance(this.authRouterProvider);
    }

    public static IdologySuccessAuthRouter provideInstance(Provider<AuthRouter> authRouterProvider) {
        return new IdologySuccessAuthRouter((AuthRouter) authRouterProvider.get());
    }

    public static IdologySuccessAuthRouter_Factory create(Provider<AuthRouter> authRouterProvider) {
        return new IdologySuccessAuthRouter_Factory(authRouterProvider);
    }

    public static IdologySuccessAuthRouter newIdologySuccessAuthRouter(AuthRouter authRouter) {
        return new IdologySuccessAuthRouter(authRouter);
    }
}
