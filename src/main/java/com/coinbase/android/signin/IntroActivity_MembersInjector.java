package com.coinbase.android.signin;

import com.coinbase.android.ScreenProtector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class IntroActivity_MembersInjector implements MembersInjector<IntroActivity> {
    private final Provider<AuthIntentManager> authIntentManagerProvider;
    private final Provider<AuthManager> authManagerAndMAuthManagerProvider;
    private final Provider<LaunchMessageModalRouter> mLaunchMessageModalRouterProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;

    public IntroActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<AuthManager> authManagerAndMAuthManagerProvider, Provider<AuthIntentManager> authIntentManagerProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<LaunchMessageModalRouter> mLaunchMessageModalRouterProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.authManagerAndMAuthManagerProvider = authManagerAndMAuthManagerProvider;
        this.authIntentManagerProvider = authIntentManagerProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
        this.mLaunchMessageModalRouterProvider = mLaunchMessageModalRouterProvider;
    }

    public static MembersInjector<IntroActivity> create(Provider<LoginManager> mLoginManagerProvider, Provider<AuthManager> authManagerAndMAuthManagerProvider, Provider<AuthIntentManager> authIntentManagerProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<LaunchMessageModalRouter> mLaunchMessageModalRouterProvider) {
        return new IntroActivity_MembersInjector(mLoginManagerProvider, authManagerAndMAuthManagerProvider, authIntentManagerProvider, mMainSchedulerProvider, mScreenProtectorProvider, mLaunchMessageModalRouterProvider);
    }

    public void injectMembers(IntroActivity instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectAuthManager(instance, (AuthManager) this.authManagerAndMAuthManagerProvider.get());
        injectAuthIntentManager(instance, (AuthIntentManager) this.authIntentManagerProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMLaunchMessageModalRouter(instance, (LaunchMessageModalRouter) this.mLaunchMessageModalRouterProvider.get());
        injectMAuthManager(instance, (AuthManager) this.authManagerAndMAuthManagerProvider.get());
    }

    public static void injectMLoginManager(IntroActivity instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectAuthManager(IntroActivity instance, AuthManager authManager) {
        instance.authManager = authManager;
    }

    public static void injectAuthIntentManager(IntroActivity instance, AuthIntentManager authIntentManager) {
        instance.authIntentManager = authIntentManager;
    }

    public static void injectMMainScheduler(IntroActivity instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMScreenProtector(IntroActivity instance, ScreenProtector mScreenProtector) {
        instance.mScreenProtector = mScreenProtector;
    }

    public static void injectMLaunchMessageModalRouter(IntroActivity instance, LaunchMessageModalRouter mLaunchMessageModalRouter) {
        instance.mLaunchMessageModalRouter = mLaunchMessageModalRouter;
    }

    public static void injectMAuthManager(IntroActivity instance, AuthManager mAuthManager) {
        instance.mAuthManager = mAuthManager;
    }
}
