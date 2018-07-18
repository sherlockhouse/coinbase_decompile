package com.coinbase.android.settings;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpdateUserTask_MembersInjector implements MembersInjector<UpdateUserTask> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider;
    private final Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider;

    public UpdateUserTask_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider, Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mLocalUserDataUpdatedConnectorProvider = mLocalUserDataUpdatedConnectorProvider;
        this.mUserUpdatedConnectorProvider = mUserUpdatedConnectorProvider;
    }

    public static MembersInjector<UpdateUserTask> create(Provider<LoginManager> loginManagerProvider, Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider, Provider<UserUpdatedConnector> mUserUpdatedConnectorProvider) {
        return new UpdateUserTask_MembersInjector(loginManagerProvider, mLocalUserDataUpdatedConnectorProvider, mUserUpdatedConnectorProvider);
    }

    public void injectMembers(UpdateUserTask instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMLocalUserDataUpdatedConnector(instance, (LocalUserDataUpdatedConnector) this.mLocalUserDataUpdatedConnectorProvider.get());
        injectMUserUpdatedConnector(instance, (UserUpdatedConnector) this.mUserUpdatedConnectorProvider.get());
    }

    public static void injectLoginManager(UpdateUserTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMLocalUserDataUpdatedConnector(UpdateUserTask instance, LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector) {
        instance.mLocalUserDataUpdatedConnector = mLocalUserDataUpdatedConnector;
    }

    public static void injectMUserUpdatedConnector(UpdateUserTask instance, UserUpdatedConnector mUserUpdatedConnector) {
        instance.mUserUpdatedConnector = mUserUpdatedConnector;
    }
}
