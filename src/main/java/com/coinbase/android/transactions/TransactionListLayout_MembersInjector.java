package com.coinbase.android.transactions;

import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TransactionListLayout_MembersInjector implements MembersInjector<TransactionListLayout> {
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<TransactionListPresenter> mPresenterProvider;

    public TransactionListLayout_MembersInjector(Provider<TransactionListPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<TransactionListLayout> create(Provider<TransactionListPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new TransactionListLayout_MembersInjector(mPresenterProvider, mBackgroundDimmerProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(TransactionListLayout instance) {
        injectMPresenter(instance, (TransactionListPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMPresenter(TransactionListLayout instance, TransactionListPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(TransactionListLayout instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }

    public static void injectMMoneyFormatterUtil(TransactionListLayout instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
