package com.coinbase.android.transfers;

import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class RequestMoneyTask_MembersInjector implements MembersInjector<RequestMoneyTask> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<TransferMadeConnector> mTransferMadeConnectorProvider;

    public RequestMoneyTask_MembersInjector(Provider<TransferMadeConnector> mTransferMadeConnectorProvider, Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mTransferMadeConnectorProvider = mTransferMadeConnectorProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<RequestMoneyTask> create(Provider<TransferMadeConnector> mTransferMadeConnectorProvider, Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new RequestMoneyTask_MembersInjector(mTransferMadeConnectorProvider, mLoginManagerProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(RequestMoneyTask instance) {
        injectMTransferMadeConnector(instance, (TransferMadeConnector) this.mTransferMadeConnectorProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMTransferMadeConnector(RequestMoneyTask instance, TransferMadeConnector mTransferMadeConnector) {
        instance.mTransferMadeConnector = mTransferMadeConnector;
    }

    public static void injectMLoginManager(RequestMoneyTask instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMoneyFormatterUtil(RequestMoneyTask instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
