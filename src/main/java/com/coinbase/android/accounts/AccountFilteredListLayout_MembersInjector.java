package com.coinbase.android.accounts;

import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AccountFilteredListLayout_MembersInjector implements MembersInjector<AccountFilteredListLayout> {
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<AccountFilteredListPresenter> mPresenterProvider;

    public AccountFilteredListLayout_MembersInjector(Provider<AccountFilteredListPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<AccountFilteredListLayout> create(Provider<AccountFilteredListPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new AccountFilteredListLayout_MembersInjector(mPresenterProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(AccountFilteredListLayout instance) {
        injectMPresenter(instance, (AccountFilteredListPresenter) this.mPresenterProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMPresenter(AccountFilteredListLayout instance, AccountFilteredListPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMMoneyFormatterUtil(AccountFilteredListLayout instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
