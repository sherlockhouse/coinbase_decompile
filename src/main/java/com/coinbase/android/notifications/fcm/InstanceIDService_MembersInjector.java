package com.coinbase.android.notifications.fcm;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class InstanceIDService_MembersInjector implements MembersInjector<InstanceIDService> {
    private final Provider<LoginManager> mLoginManagerProvider;

    public InstanceIDService_MembersInjector(Provider<LoginManager> mLoginManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<InstanceIDService> create(Provider<LoginManager> mLoginManagerProvider) {
        return new InstanceIDService_MembersInjector(mLoginManagerProvider);
    }

    public void injectMembers(InstanceIDService instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(InstanceIDService instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
