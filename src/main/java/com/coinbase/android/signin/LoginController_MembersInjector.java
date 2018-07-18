package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
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

public final class LoginController_MembersInjector implements MembersInjector<LoginController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<AuthManager> mAuthManagerProvider;
    private final Provider<AuthRouter> mAuthRouterProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider;
    private final Provider<DeviceVerifyConnector> mDeviceVerifyConnectorProvider;
    private final Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<LoginAuthManager> mLoginAuthManagerProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<SignInRouter> mSignInRouterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;
    private final Provider<TwoFactorRouter> mTwoFactorRouterProvider;

    public LoginController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<AuthManager> mAuthManagerProvider, Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider, Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider, Provider<DeviceVerifyConnector> mDeviceVerifyConnectorProvider, Provider<LoginManager> mLoginManagerProvider, Provider<LoginAuthManager> mLoginAuthManagerProvider, Provider<SignInRouter> mSignInRouterProvider, Provider<TwoFactorRouter> mTwoFactorRouterProvider, Provider<AuthRouter> mAuthRouterProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mAuthManagerProvider = mAuthManagerProvider;
        this.mCredentialsApiRxWrapperProvider = mCredentialsApiRxWrapperProvider;
        this.mEmailVerifiedConnectorProvider = mEmailVerifiedConnectorProvider;
        this.mDeviceVerifyConnectorProvider = mDeviceVerifyConnectorProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mLoginAuthManagerProvider = mLoginAuthManagerProvider;
        this.mSignInRouterProvider = mSignInRouterProvider;
        this.mTwoFactorRouterProvider = mTwoFactorRouterProvider;
        this.mAuthRouterProvider = mAuthRouterProvider;
    }

    public static MembersInjector<LoginController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<AuthManager> mAuthManagerProvider, Provider<CredentialsApiRxWrapper> mCredentialsApiRxWrapperProvider, Provider<EmailVerifiedConnector> mEmailVerifiedConnectorProvider, Provider<DeviceVerifyConnector> mDeviceVerifyConnectorProvider, Provider<LoginManager> mLoginManagerProvider, Provider<LoginAuthManager> mLoginAuthManagerProvider, Provider<SignInRouter> mSignInRouterProvider, Provider<TwoFactorRouter> mTwoFactorRouterProvider, Provider<AuthRouter> mAuthRouterProvider) {
        return new LoginController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mAuthManagerProvider, mCredentialsApiRxWrapperProvider, mEmailVerifiedConnectorProvider, mDeviceVerifyConnectorProvider, mLoginManagerProvider, mLoginAuthManagerProvider, mSignInRouterProvider, mTwoFactorRouterProvider, mAuthRouterProvider);
    }

    public void injectMembers(LoginController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMAuthManager(instance, (AuthManager) this.mAuthManagerProvider.get());
        injectMCredentialsApiRxWrapper(instance, (CredentialsApiRxWrapper) this.mCredentialsApiRxWrapperProvider.get());
        injectMEmailVerifiedConnector(instance, (EmailVerifiedConnector) this.mEmailVerifiedConnectorProvider.get());
        injectMDeviceVerifyConnector(instance, (DeviceVerifyConnector) this.mDeviceVerifyConnectorProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMLoginAuthManager(instance, (LoginAuthManager) this.mLoginAuthManagerProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMSignInRouter(instance, (SignInRouter) this.mSignInRouterProvider.get());
        injectMTwoFactorRouter(instance, (TwoFactorRouter) this.mTwoFactorRouterProvider.get());
        injectMAuthRouter(instance, (AuthRouter) this.mAuthRouterProvider.get());
    }

    public static void injectMAuthManager(LoginController instance, AuthManager mAuthManager) {
        instance.mAuthManager = mAuthManager;
    }

    public static void injectMCredentialsApiRxWrapper(LoginController instance, CredentialsApiRxWrapper mCredentialsApiRxWrapper) {
        instance.mCredentialsApiRxWrapper = mCredentialsApiRxWrapper;
    }

    public static void injectMEmailVerifiedConnector(LoginController instance, EmailVerifiedConnector mEmailVerifiedConnector) {
        instance.mEmailVerifiedConnector = mEmailVerifiedConnector;
    }

    public static void injectMDeviceVerifyConnector(LoginController instance, DeviceVerifyConnector mDeviceVerifyConnector) {
        instance.mDeviceVerifyConnector = mDeviceVerifyConnector;
    }

    public static void injectMLoginManager(LoginController instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMLoginAuthManager(LoginController instance, LoginAuthManager mLoginAuthManager) {
        instance.mLoginAuthManager = mLoginAuthManager;
    }

    public static void injectMMainScheduler(LoginController instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMSignInRouter(LoginController instance, SignInRouter mSignInRouter) {
        instance.mSignInRouter = mSignInRouter;
    }

    public static void injectMTwoFactorRouter(LoginController instance, TwoFactorRouter mTwoFactorRouter) {
        instance.mTwoFactorRouter = mTwoFactorRouter;
    }

    public static void injectMAuthRouter(LoginController instance, AuthRouter mAuthRouter) {
        instance.mAuthRouter = mAuthRouter;
    }
}
