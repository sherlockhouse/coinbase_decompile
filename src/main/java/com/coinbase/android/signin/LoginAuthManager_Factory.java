package com.coinbase.android.signin;

import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class LoginAuthManager_Factory implements Factory<LoginAuthManager> {
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;

    public LoginAuthManager_Factory(Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.authRouterProvider = authRouterProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public LoginAuthManager get() {
        return provideInstance(this.loginManagerProvider, this.authRouterProvider, this.mainSchedulerProvider);
    }

    public static LoginAuthManager provideInstance(Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new LoginAuthManager((LoginManager) loginManagerProvider.get(), (AuthRouter) authRouterProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static LoginAuthManager_Factory create(Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new LoginAuthManager_Factory(loginManagerProvider, authRouterProvider, mainSchedulerProvider);
    }

    public static LoginAuthManager newLoginAuthManager(LoginManager loginManager, AuthRouter authRouter, Scheduler mainScheduler) {
        return new LoginAuthManager(loginManager, authRouter, mainScheduler);
    }
}
