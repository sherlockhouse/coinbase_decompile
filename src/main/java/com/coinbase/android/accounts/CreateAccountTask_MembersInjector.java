package com.coinbase.android.accounts;

import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CreateAccountTask_MembersInjector implements MembersInjector<CreateAccountTask> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider;

    public CreateAccountTask_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mRefreshRequestedConnectorProvider = mRefreshRequestedConnectorProvider;
    }

    public static MembersInjector<CreateAccountTask> create(Provider<LoginManager> loginManagerProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider) {
        return new CreateAccountTask_MembersInjector(loginManagerProvider, mRefreshRequestedConnectorProvider);
    }

    public void injectMembers(CreateAccountTask instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMRefreshRequestedConnector(instance, (RefreshRequestedConnector) this.mRefreshRequestedConnectorProvider.get());
    }

    public static void injectLoginManager(CreateAccountTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMRefreshRequestedConnector(CreateAccountTask instance, RefreshRequestedConnector mRefreshRequestedConnector) {
        instance.mRefreshRequestedConnector = mRefreshRequestedConnector;
    }
}
