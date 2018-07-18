package com.coinbase.android.transactions;

import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TransactionDetailLayout_MembersInjector implements MembersInjector<TransactionDetailLayout> {
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<TransactionDetailPresenter> mPresenterProvider;

    public TransactionDetailLayout_MembersInjector(Provider<TransactionDetailPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<TransactionDetailLayout> create(Provider<TransactionDetailPresenter> mPresenterProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new TransactionDetailLayout_MembersInjector(mPresenterProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(TransactionDetailLayout instance) {
        injectMPresenter(instance, (TransactionDetailPresenter) this.mPresenterProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMPresenter(TransactionDetailLayout instance, TransactionDetailPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMMoneyFormatterUtil(TransactionDetailLayout instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
