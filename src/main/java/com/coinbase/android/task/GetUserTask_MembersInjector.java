package com.coinbase.android.task;

import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class GetUserTask_MembersInjector implements MembersInjector<GetUserTask> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider;

    public GetUserTask_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mUserUpdatedConnectorProvider = mUserUpdatedConnectorProvider;
    }

    public static MembersInjector<GetUserTask> create(Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider) {
        return new GetUserTask_MembersInjector(loginManagerProvider, mMainSchedulerProvider, mUserUpdatedConnectorProvider);
    }

    public void injectMembers(GetUserTask instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMUserUpdatedConnector(instance, (UserUpdatedConnector) this.mUserUpdatedConnectorProvider.get());
    }

    public static void injectLoginManager(GetUserTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMMainScheduler(GetUserTask instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMUserUpdatedConnector(GetUserTask instance, UserUpdatedConnector mUserUpdatedConnector) {
        instance.mUserUpdatedConnector = mUserUpdatedConnector;
    }
}
