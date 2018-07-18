package com.coinbase.android.signin;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class SignInPhoneNumberPresenter_Factory implements Factory<SignInPhoneNumberPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<SignInPhoneNumberRouter> routerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<SignInPhoneNumberScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public SignInPhoneNumberPresenter_Factory(Provider<SignInPhoneNumberScreen> screenProvider, Provider<SignInPhoneNumberRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<Scheduler> schedulerProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.authRouterProvider = authRouterProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.appProvider = appProvider;
        this.schedulerProvider = schedulerProvider;
    }

    public SignInPhoneNumberPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.signInRouterProvider, this.authRouterProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.appProvider, this.schedulerProvider);
    }

    public static SignInPhoneNumberPresenter provideInstance(Provider<SignInPhoneNumberScreen> screenProvider, Provider<SignInPhoneNumberRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<Scheduler> schedulerProvider) {
        return new SignInPhoneNumberPresenter((SignInPhoneNumberScreen) screenProvider.get(), (SignInPhoneNumberRouter) routerProvider.get(), (SignInRouter) signInRouterProvider.get(), (AuthRouter) authRouterProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Application) appProvider.get(), (Scheduler) schedulerProvider.get());
    }

    public static SignInPhoneNumberPresenter_Factory create(Provider<SignInPhoneNumberScreen> screenProvider, Provider<SignInPhoneNumberRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<Scheduler> schedulerProvider) {
        return new SignInPhoneNumberPresenter_Factory(screenProvider, routerProvider, signInRouterProvider, authRouterProvider, loginManagerProvider, mixpanelTrackingProvider, snackBarWrapperProvider, appProvider, schedulerProvider);
    }

    public static SignInPhoneNumberPresenter newSignInPhoneNumberPresenter(SignInPhoneNumberScreen screen, SignInPhoneNumberRouter router, SignInRouter signInRouter, AuthRouter authRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, Application app, Scheduler scheduler) {
        return new SignInPhoneNumberPresenter(screen, router, signInRouter, authRouter, loginManager, mixpanelTracking, snackBarWrapper, app, scheduler);
    }
}
