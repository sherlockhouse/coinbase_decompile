package com.coinbase.android.accounts;

import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AccountRefreshListLayout_MembersInjector implements MembersInjector<AccountRefreshListLayout> {
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<AccountListPresenter> mPresenterProvider;

    public AccountRefreshListLayout_MembersInjector(Provider<AccountListPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<AccountRefreshListLayout> create(Provider<AccountListPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new AccountRefreshListLayout_MembersInjector(mPresenterProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(AccountRefreshListLayout instance) {
        injectMPresenter(instance, (AccountListPresenter) this.mPresenterProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMPresenter(AccountRefreshListLayout instance, AccountListPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMMoneyFormatterUtil(AccountRefreshListLayout instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
