package com.coinbase.android.deposits;

import com.coinbase.android.deposits.FiatTransactionsController.WithdrawConfirmationDialogFragment;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class FiatTransactionsController_WithdrawConfirmationDialogFragment_MembersInjector implements MembersInjector<WithdrawConfirmationDialogFragment> {
    private final Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public FiatTransactionsController_WithdrawConfirmationDialogFragment_MembersInjector(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<LoginManager> mLoginManagerProvider) {
        this.mFiatTransactionsConnectorProvider = mFiatTransactionsConnectorProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<WithdrawConfirmationDialogFragment> create(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<LoginManager> mLoginManagerProvider) {
        return new FiatTransactionsController_WithdrawConfirmationDialogFragment_MembersInjector(mFiatTransactionsConnectorProvider, mMoneyFormatterUtilProvider, mLoginManagerProvider);
    }

    public void injectMembers(WithdrawConfirmationDialogFragment instance) {
        DepositWithdrawConfirmationDialogFragment_MembersInjector.injectMFiatTransactionsConnector(instance, (FiatTransactionsConnector) this.mFiatTransactionsConnectorProvider.get());
        DepositWithdrawConfirmationDialogFragment_MembersInjector.injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(WithdrawConfirmationDialogFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
