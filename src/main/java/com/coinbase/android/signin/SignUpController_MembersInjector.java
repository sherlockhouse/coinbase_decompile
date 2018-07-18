package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.Analytics;
import com.coinbase.android.gdpr.OnboardingRouter;
import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class SignUpController_MembersInjector implements MembersInjector<SignUpController> {
    private final Provider<Analytics> mAnalyticsProvider;
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<AuthManager> mAuthManagerProvider;
    private final Provider<AuthRouter> mAuthRouterProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider;
    private final Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<OnboardingRouter> mOnboardingRouterProvider;
    private final Provider<OnboardingUpdatedConnector> mOnboardingUpdatedConnectorProvider;
    private final Provider<SignInRouter> mSignInRouterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public SignUpController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<Analytics> mAnalyticsProvider, Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider, Provider<AuthManager> mAuthManagerProvider, Provider<AuthRouter> mAuthRouterProvider, Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider, Provider<LoginManager> mLoginManagerProvider, Provider<SignInRouter> mSignInRouterProvider, Provider<OnboardingRouter> mOnboardingRouterProvider, Provider<OnboardingUpdatedConnector> mOnboardingUpdatedConnectorProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mAnalyticsProvider = mAnalyticsProvider;
        this.mEmailVerifiedConnectorProvider = mEmailVerifiedConnectorProvider;
        this.mAuthManagerProvider = mAuthManagerProvider;
        this.mAuthRouterProvider = mAuthRouterProvider;
        this.mCredentialsApiRxWrapperProvider = mCredentialsApiRxWrapperProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mSignInRouterProvider = mSignInRouterProvider;
        this.mOnboardingRouterProvider = mOnboardingRouterProvider;
        this.mOnboardingUpdatedConnectorProvider = mOnboardingUpdatedConnectorProvider;
    }

    public static MembersInjector<SignUpController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<Analytics> mAnalyticsProvider, Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider, Provider<AuthManager> mAuthManagerProvider, Provider<AuthRouter> mAuthRouterProvider, Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider, Provider<LoginManager> mLoginManagerProvider, Provider<SignInRouter> mSignInRouterProvider, Provider<OnboardingRouter> mOnboardingRouterProvider, Provider<OnboardingUpdatedConnector> mOnboardingUpdatedConnectorProvider) {
        return new SignUpController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mAnalyticsProvider, mEmailVerifiedConnectorProvider, mAuthManagerProvider, mAuthRouterProvider, mCredentialsApiRxWrapperProvider, mLoginManagerProvider, mSignInRouterProvider, mOnboardingRouterProvider, mOnboardingUpdatedConnectorProvider);
    }

    public void injectMembers(SignUpController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMAnalytics(instance, (Analytics) this.mAnalyticsProvider.get());
        injectMEmailVerifiedConnector(instance, (EmailVerifiedConnector) this.mEmailVerifiedConnectorProvider.get());
        injectMAuthManager(instance, (AuthManager) this.mAuthManagerProvider.get());
        injectMAuthRouter(instance, (AuthRouter) this.mAuthRouterProvider.get());
        injectMCredentialsApiRxWrapper(instance, (CredentialsApiRxWrapper) this.mCredentialsApiRxWrapperProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMSignInRouter(instance, (SignInRouter) this.mSignInRouterProvider.get());
        injectMOnboardingRouter(instance, (OnboardingRouter) this.mOnboardingRouterProvider.get());
        injectMOnboardingUpdatedConnector(instance, (OnboardingUpdatedConnector) this.mOnboardingUpdatedConnectorProvider.get());
    }

    public static void injectMAnalytics(SignUpController instance, Analytics mAnalytics) {
        instance.mAnalytics = mAnalytics;
    }

    public static void injectMEmailVerifiedConnector(SignUpController instance, EmailVerifiedConnector mEmailVerifiedConnector) {
        instance.mEmailVerifiedConnector = mEmailVerifiedConnector;
    }

    public static void injectMAuthManager(SignUpController instance, AuthManager mAuthManager) {
        instance.mAuthManager = mAuthManager;
    }

    public static void injectMAuthRouter(SignUpController instance, AuthRouter mAuthRouter) {
        instance.mAuthRouter = mAuthRouter;
    }

    public static void injectMCredentialsApiRxWrapper(SignUpController instance, CredentialsApiRxWrapper mCredentialsApiRxWrapper) {
        instance.mCredentialsApiRxWrapper = mCredentialsApiRxWrapper;
    }

    public static void injectMLoginManager(SignUpController instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMainScheduler(SignUpController instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMSignInRouter(SignUpController instance, SignInRouter mSignInRouter) {
        instance.mSignInRouter = mSignInRouter;
    }

    public static void injectMOnboardingRouter(SignUpController instance, OnboardingRouter mOnboardingRouter) {
        instance.mOnboardingRouter = mOnboardingRouter;
    }

    public static void injectMOnboardingUpdatedConnector(SignUpController instance, OnboardingUpdatedConnector mOnboardingUpdatedConnector) {
        instance.mOnboardingUpdatedConnector = mOnboardingUpdatedConnector;
    }
}
