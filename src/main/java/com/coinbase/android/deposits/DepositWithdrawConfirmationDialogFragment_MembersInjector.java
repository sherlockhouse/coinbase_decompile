package com.coinbase.android.deposits;

import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DepositWithdrawConfirmationDialogFragment_MembersInjector implements MembersInjector<DepositWithdrawConfirmationDialogFragment> {
    private final Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public DepositWithdrawConfirmationDialogFragment_MembersInjector(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mFiatTransactionsConnectorProvider = mFiatTransactionsConnectorProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<DepositWithdrawConfirmationDialogFragment> create(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new DepositWithdrawConfirmationDialogFragment_MembersInjector(mFiatTransactionsConnectorProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(DepositWithdrawConfirmationDialogFragment instance) {
        injectMFiatTransactionsConnector(instance, (FiatTransactionsConnector) this.mFiatTransactionsConnectorProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMFiatTransactionsConnector(DepositWithdrawConfirmationDialogFragment instance, FiatTransactionsConnector mFiatTransactionsConnector) {
        instance.mFiatTransactionsConnector = mFiatTransactionsConnector;
    }

    public static void injectMMoneyFormatterUtil(DepositWithdrawConfirmationDialogFragment instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
