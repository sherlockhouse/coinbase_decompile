package com.coinbase.android.signin;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.utils.ActivityPermissionCheckUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class EmailVerifyPresenter_Factory implements Factory<EmailVerifyPresenter> {
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<Application> appProvider;
    private final Provider<AuthRouter> authRouterProvider;
    private final Provider<EmailVerifiedConnector> emailVerifiedConnectorProvider;
    private final Provider<EmailVerifiedRouter> emailVerifiedRouterProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<EmailVerifyScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public EmailVerifyPresenter_Factory(Provider<EmailVerifyScreen> screenProvider, Provider<EmailVerifiedRouter> emailVerifiedRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<EmailVerifiedConnector> emailVerifiedConnectorProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider) {
        this.screenProvider = screenProvider;
        this.emailVerifiedRouterProvider = emailVerifiedRouterProvider;
        this.authRouterProvider = authRouterProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.emailVerifiedConnectorProvider = emailVerifiedConnectorProvider;
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.permissionCheckUtilsProvider = permissionCheckUtilsProvider;
        this.appProvider = appProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.schedulerProvider = schedulerProvider;
    }

    public EmailVerifyPresenter get() {
        return provideInstance(this.screenProvider, this.emailVerifiedRouterProvider, this.authRouterProvider, this.signInRouterProvider, this.mixpanelTrackingProvider, this.splitTestingProvider, this.emailVerifiedConnectorProvider, this.appCompatActivityProvider, this.permissionCheckUtilsProvider, this.appProvider, this.loginManagerProvider, this.schedulerProvider);
    }

    public static EmailVerifyPresenter provideInstance(Provider<EmailVerifyScreen> screenProvider, Provider<EmailVerifiedRouter> emailVerifiedRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<EmailVerifiedConnector> emailVerifiedConnectorProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider) {
        return new EmailVerifyPresenter((EmailVerifyScreen) screenProvider.get(), (EmailVerifiedRouter) emailVerifiedRouterProvider.get(), (AuthRouter) authRouterProvider.get(), (SignInRouter) signInRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SplitTesting) splitTestingProvider.get(), (EmailVerifiedConnector) emailVerifiedConnectorProvider.get(), (AppCompatActivity) appCompatActivityProvider.get(), (ActivityPermissionCheckUtils) permissionCheckUtilsProvider.get(), (Application) appProvider.get(), (LoginManager) loginManagerProvider.get(), (Scheduler) schedulerProvider.get());
    }

    public static EmailVerifyPresenter_Factory create(Provider<EmailVerifyScreen> screenProvider, Provider<EmailVerifiedRouter> emailVerifiedRouterProvider, Provider<AuthRouter> authRouterProvider, Provider<SignInRouter> signInRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<EmailVerifiedConnector> emailVerifiedConnectorProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<ActivityPermissionCheckUtils> permissionCheckUtilsProvider, Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider) {
        return new EmailVerifyPresenter_Factory(screenProvider, emailVerifiedRouterProvider, authRouterProvider, signInRouterProvider, mixpanelTrackingProvider, splitTestingProvider, emailVerifiedConnectorProvider, appCompatActivityProvider, permissionCheckUtilsProvider, appProvider, loginManagerProvider, schedulerProvider);
    }

    public static EmailVerifyPresenter newEmailVerifyPresenter(EmailVerifyScreen screen, EmailVerifiedRouter emailVerifiedRouter, AuthRouter authRouter, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, EmailVerifiedConnector emailVerifiedConnector, AppCompatActivity appCompatActivity, ActivityPermissionCheckUtils permissionCheckUtils, Application app, LoginManager loginManager, Scheduler scheduler) {
        return new EmailVerifyPresenter(screen, emailVerifiedRouter, authRouter, signInRouter, mixpanelTracking, splitTesting, emailVerifiedConnector, appCompatActivity, permissionCheckUtils, app, loginManager, scheduler);
    }
}
