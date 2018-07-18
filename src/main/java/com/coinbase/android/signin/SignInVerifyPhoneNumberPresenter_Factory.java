package com.coinbase.android.signin;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class SignInVerifyPhoneNumberPresenter_Factory implements Factory<SignInVerifyPhoneNumberPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<SignInVerifyPhoneNumberScreen> signInVerifyPhoneNumberScreenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public SignInVerifyPhoneNumberPresenter_Factory(Provider<Application> appProvider, Provider<SignInVerifyPhoneNumberScreen> signInVerifyPhoneNumberScreenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> schedulerProvider) {
        this.appProvider = appProvider;
        this.signInVerifyPhoneNumberScreenProvider = signInVerifyPhoneNumberScreenProvider;
        this.authRouterProvider = authRouterProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.schedulerProvider = schedulerProvider;
    }

    public SignInVerifyPhoneNumberPresenter get() {
        return provideInstance(this.appProvider, this.signInVerifyPhoneNumberScreenProvider, this.authRouterProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.schedulerProvider);
    }

    public static SignInVerifyPhoneNumberPresenter provideInstance(Provider<Application> appProvider, Provider<SignInVerifyPhoneNumberScreen> signInVerifyPhoneNumberScreenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> schedulerProvider) {
        return new SignInVerifyPhoneNumberPresenter((Application) appProvider.get(), (SignInVerifyPhoneNumberScreen) signInVerifyPhoneNumberScreenProvider.get(), (AuthRouter) authRouterProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) schedulerProvider.get());
    }

    public static SignInVerifyPhoneNumberPresenter_Factory create(Provider<Application> appProvider, Provider<SignInVerifyPhoneNumberScreen> signInVerifyPhoneNumberScreenProvider, Provider<AuthRouter> authRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> schedulerProvider) {
        return new SignInVerifyPhoneNumberPresenter_Factory(appProvider, signInVerifyPhoneNumberScreenProvider, authRouterProvider, loginManagerProvider, mixpanelTrackingProvider, snackBarWrapperProvider, schedulerProvider);
    }

    public static SignInVerifyPhoneNumberPresenter newSignInVerifyPhoneNumberPresenter(Application app, SignInVerifyPhoneNumberScreen signInVerifyPhoneNumberScreen, AuthRouter authRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, Scheduler scheduler) {
        return new SignInVerifyPhoneNumberPresenter(app, signInVerifyPhoneNumberScreen, authRouter, loginManager, mixpanelTracking, snackBarWrapper, scheduler);
    }
}
