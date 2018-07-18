package com.coinbase.android.phone;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class VerifyPhoneHandler_MembersInjector implements MembersInjector<VerifyPhoneHandler> {
    private final Provider<LoginManager> mLoginManagerProvider;

    public VerifyPhoneHandler_MembersInjector(Provider<LoginManager> mLoginManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<VerifyPhoneHandler> create(Provider<LoginManager> mLoginManagerProvider) {
        return new VerifyPhoneHandler_MembersInjector(mLoginManagerProvider);
    }

    public void injectMembers(VerifyPhoneHandler instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(VerifyPhoneHandler instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
