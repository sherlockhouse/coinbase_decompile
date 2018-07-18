package com.coinbase.android.signin;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class TwoFactorPresenter_Factory implements Factory<TwoFactorPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<LoginAuthManager> loginAuthManagerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<TwoFactorRouter> routerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<TwoFactorScreen> screenProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SignInRouter> signInRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public TwoFactorPresenter_Factory(Provider<TwoFactorScreen> screenProvider, Provider<TwoFactorRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginAuthManager> loginAuthManagerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> schedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.authRouterProvider = authRouterProvider;
        this.loginAuthManagerProvider = loginAuthManagerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.appProvider = appProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.schedulerProvider = schedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public TwoFactorPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.signInRouterProvider, this.authRouterProvider, this.loginAuthManagerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.appProvider, this.mixpanelTrackingProvider, this.sharedPreferencesProvider, this.schedulerProvider, this.backgroundSchedulerProvider);
    }

    public static TwoFactorPresenter provideInstance(Provider<TwoFactorScreen> screenProvider, Provider<TwoFactorRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginAuthManager> loginAuthManagerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> schedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TwoFactorPresenter((TwoFactorScreen) screenProvider.get(), (TwoFactorRouter) routerProvider.get(), (SignInRouter) signInRouterProvider.get(), (AuthRouter) authRouterProvider.get(), (LoginAuthManager) loginAuthManagerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Application) appProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SharedPreferences) sharedPreferencesProvider.get(), (Scheduler) schedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static TwoFactorPresenter_Factory create(Provider<TwoFactorScreen> screenProvider, Provider<TwoFactorRouter> routerProvider, Provider<SignInRouter> signInRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginAuthManager> loginAuthManagerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> appProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> schedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TwoFactorPresenter_Factory(screenProvider, routerProvider, signInRouterProvider, authRouterProvider, loginAuthManagerProvider, loginManagerProvider, snackBarWrapperProvider, appProvider, mixpanelTrackingProvider, sharedPreferencesProvider, schedulerProvider, backgroundSchedulerProvider);
    }

    public static TwoFactorPresenter newTwoFactorPresenter(TwoFactorScreen screen, TwoFactorRouter router, SignInRouter signInRouter, AuthRouter authRouter, LoginAuthManager loginAuthManager, LoginManager loginManager, SnackBarWrapper snackBarWrapper, Application app, MixpanelTracking mixpanelTracking, SharedPreferences sharedPreferences, Scheduler scheduler, Scheduler backgroundScheduler) {
        return new TwoFactorPresenter(screen, router, signInRouter, authRouter, loginAuthManager, loginManager, snackBarWrapper, app, mixpanelTracking, sharedPreferences, scheduler, backgroundScheduler);
    }
}
