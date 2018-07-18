package com.coinbase.android.accounts;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AccountsFragment_MembersInjector implements MembersInjector<AccountsFragment> {
    private final Provider<LoginManager> mLoginManagerProvider;

    public AccountsFragment_MembersInjector(Provider<LoginManager> mLoginManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<AccountsFragment> create(Provider<LoginManager> mLoginManagerProvider) {
        return new AccountsFragment_MembersInjector(mLoginManagerProvider);
    }

    public void injectMembers(AccountsFragment instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(AccountsFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
