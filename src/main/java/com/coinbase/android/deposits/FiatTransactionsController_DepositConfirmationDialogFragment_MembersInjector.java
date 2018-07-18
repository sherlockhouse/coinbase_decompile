package com.coinbase.android.deposits;

import com.coinbase.android.deposits.FiatTransactionsController.DepositConfirmationDialogFragment;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class FiatTransactionsController_DepositConfirmationDialogFragment_MembersInjector implements MembersInjector<DepositConfirmationDialogFragment> {
    private final Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public FiatTransactionsController_DepositConfirmationDialogFragment_MembersInjector(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<LoginManager> mLoginManagerProvider) {
        this.mFiatTransactionsConnectorProvider = mFiatTransactionsConnectorProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<DepositConfirmationDialogFragment> create(Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<LoginManager> mLoginManagerProvider) {
        return new FiatTransactionsController_DepositConfirmationDialogFragment_MembersInjector(mFiatTransactionsConnectorProvider, mMoneyFormatterUtilProvider, mLoginManagerProvider);
    }

    public void injectMembers(DepositConfirmationDialogFragment instance) {
        DepositWithdrawConfirmationDialogFragment_MembersInjector.injectMFiatTransactionsConnector(instance, (FiatTransactionsConnector) this.mFiatTransactionsConnectorProvider.get());
        DepositWithdrawConfirmationDialogFragment_MembersInjector.injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMLoginManager(DepositConfirmationDialogFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
