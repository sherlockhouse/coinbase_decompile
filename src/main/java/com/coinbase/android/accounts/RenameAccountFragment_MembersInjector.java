package com.coinbase.android.accounts;

import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class RenameAccountFragment_MembersInjector implements MembersInjector<RenameAccountFragment> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider;

    public RenameAccountFragment_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mRefreshRequestedConnectorProvider = mRefreshRequestedConnectorProvider;
    }

    public static MembersInjector<RenameAccountFragment> create(Provider<LoginManager> loginManagerProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider) {
        return new RenameAccountFragment_MembersInjector(loginManagerProvider, mRefreshRequestedConnectorProvider);
    }

    public void injectMembers(RenameAccountFragment instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMRefreshRequestedConnector(instance, (RefreshRequestedConnector) this.mRefreshRequestedConnectorProvider.get());
    }

    public static void injectLoginManager(RenameAccountFragment instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMRefreshRequestedConnector(RenameAccountFragment instance, RefreshRequestedConnector mRefreshRequestedConnector) {
        instance.mRefreshRequestedConnector = mRefreshRequestedConnector;
    }
}
