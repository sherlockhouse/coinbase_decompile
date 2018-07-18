package com.coinbase.android.accounts;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class AccountCryptoAddressLayout_MembersInjector implements MembersInjector<AccountCryptoAddressLayout> {
    private final Provider<AccountCryptoAddressButtonConnector> mAccountCryptoAddressButtonConnectorProvider;
    private final Provider<AccountCryptoAddressPresenter> mPresenterProvider;

    public AccountCryptoAddressLayout_MembersInjector(Provider<AccountCryptoAddressButtonConnector> mAccountCryptoAddressButtonConnectorProvider, Provider<AccountCryptoAddressPresenter> mPresenterProvider) {
        this.mAccountCryptoAddressButtonConnectorProvider = mAccountCryptoAddressButtonConnectorProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<AccountCryptoAddressLayout> create(Provider<AccountCryptoAddressButtonConnector> mAccountCryptoAddressButtonConnectorProvider, Provider<AccountCryptoAddressPresenter> mPresenterProvider) {
        return new AccountCryptoAddressLayout_MembersInjector(mAccountCryptoAddressButtonConnectorProvider, mPresenterProvider);
    }

    public void injectMembers(AccountCryptoAddressLayout instance) {
        injectMAccountCryptoAddressButtonConnector(instance, (AccountCryptoAddressButtonConnector) this.mAccountCryptoAddressButtonConnectorProvider.get());
        injectMPresenter(instance, (AccountCryptoAddressPresenter) this.mPresenterProvider.get());
    }

    public static void injectMAccountCryptoAddressButtonConnector(AccountCryptoAddressLayout instance, AccountCryptoAddressButtonConnector mAccountCryptoAddressButtonConnector) {
        instance.mAccountCryptoAddressButtonConnector = mAccountCryptoAddressButtonConnector;
    }

    public static void injectMPresenter(AccountCryptoAddressLayout instance, AccountCryptoAddressPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
