package com.coinbase.android.signin.state;

import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class UpfrontKycIdentityProcessingPresenter_Factory implements Factory<UpfrontKycIdentityProcessingPresenter> {
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<UpfrontKycIdentityProcessingRouter> routerProvider;
    private final Provider<UpfrontKycIdentityProcessingScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public UpfrontKycIdentityProcessingPresenter_Factory(Provider<UpfrontKycIdentityProcessingScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityProcessingRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.authRouterProvider = authRouterProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.routerProvider = routerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public UpfrontKycIdentityProcessingPresenter get() {
        return provideInstance(this.screenProvider, this.loginManagerProvider, this.authRouterProvider, this.signInRouterProvider, this.routerProvider, this.mainSchedulerProvider);
    }

    public static UpfrontKycIdentityProcessingPresenter provideInstance(Provider<UpfrontKycIdentityProcessingScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityProcessingRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new UpfrontKycIdentityProcessingPresenter((UpfrontKycIdentityProcessingScreen) screenProvider.get(), (LoginManager) loginManagerProvider.get(), (AuthRouter) authRouterProvider.get(), (SignInRouter) signInRouterProvider.get(), (UpfrontKycIdentityProcessingRouter) routerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static UpfrontKycIdentityProcessingPresenter_Factory create(Provider<UpfrontKycIdentityProcessingScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityProcessingRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new UpfrontKycIdentityProcessingPresenter_Factory(screenProvider, loginManagerProvider, authRouterProvider, signInRouterProvider, routerProvider, mainSchedulerProvider);
    }

    public static UpfrontKycIdentityProcessingPresenter newUpfrontKycIdentityProcessingPresenter(UpfrontKycIdentityProcessingScreen screen, LoginManager loginManager, AuthRouter authRouter, SignInRouter signInRouter, UpfrontKycIdentityProcessingRouter router, Scheduler mainScheduler) {
        return new UpfrontKycIdentityProcessingPresenter(screen, loginManager, authRouter, signInRouter, router, mainScheduler);
    }
}
