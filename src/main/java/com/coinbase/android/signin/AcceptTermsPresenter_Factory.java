package com.coinbase.android.signin;

import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AcceptTermsPresenter_Factory implements Factory<AcceptTermsPresenter> {
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<AcceptTermsScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public AcceptTermsPresenter_Factory(Provider<AcceptTermsScreen> screenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> schedulerProvider) {
        this.screenProvider = screenProvider;
        this.authRouterProvider = authRouterProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.schedulerProvider = schedulerProvider;
    }

    public AcceptTermsPresenter get() {
        return provideInstance(this.screenProvider, this.authRouterProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.signInRouterProvider, this.mixpanelTrackingProvider, this.backgroundSchedulerProvider, this.schedulerProvider);
    }

    public static AcceptTermsPresenter provideInstance(Provider<AcceptTermsScreen> screenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> schedulerProvider) {
        return new AcceptTermsPresenter((AcceptTermsScreen) screenProvider.get(), (AuthRouter) authRouterProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (SignInRouter) signInRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (Scheduler) schedulerProvider.get());
    }

    public static AcceptTermsPresenter_Factory create(Provider<AcceptTermsScreen> screenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<Scheduler> schedulerProvider) {
        return new AcceptTermsPresenter_Factory(screenProvider, authRouterProvider, loginManagerProvider, snackBarWrapperProvider, signInRouterProvider, mixpanelTrackingProvider, backgroundSchedulerProvider, schedulerProvider);
    }

    public static AcceptTermsPresenter newAcceptTermsPresenter(AcceptTermsScreen screen, AuthRouter authRouter, LoginManager loginManager, SnackBarWrapper snackBarWrapper, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, Scheduler backgroundScheduler, Scheduler scheduler) {
        return new AcceptTermsPresenter(screen, authRouter, loginManager, snackBarWrapper, signInRouter, mixpanelTracking, backgroundScheduler, scheduler);
    }
}
