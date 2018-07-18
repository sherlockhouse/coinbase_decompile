package com.coinbase.android.signin;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class OAuthTask_MembersInjector implements MembersInjector<OAuthTask> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mBackgroundSchedulerProvider;

    public OAuthTask_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mBackgroundSchedulerProvider = mBackgroundSchedulerProvider;
    }

    public static MembersInjector<OAuthTask> create(Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        return new OAuthTask_MembersInjector(loginManagerProvider, mBackgroundSchedulerProvider);
    }

    public void injectMembers(OAuthTask instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMBackgroundScheduler(instance, (Scheduler) this.mBackgroundSchedulerProvider.get());
    }

    public static void injectLoginManager(OAuthTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMBackgroundScheduler(OAuthTask instance, Scheduler mBackgroundScheduler) {
        instance.mBackgroundScheduler = mBackgroundScheduler;
    }
}
