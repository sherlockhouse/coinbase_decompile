package com.coinbase.android.transfers;

import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DelayedTxSenderService_MembersInjector implements MembersInjector<DelayedTxSenderService> {
    private final Provider<DatabaseManager> mDbManagerProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public DelayedTxSenderService_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<DatabaseManager> mDbManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mDbManagerProvider = mDbManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<DelayedTxSenderService> create(Provider<LoginManager> mLoginManagerProvider, Provider<DatabaseManager> mDbManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new DelayedTxSenderService_MembersInjector(mLoginManagerProvider, mDbManagerProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(DelayedTxSenderService instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMDbManager(instance, (DatabaseManager) this.mDbManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMLoginManager(DelayedTxSenderService instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMDbManager(DelayedTxSenderService instance, DatabaseManager mDbManager) {
        instance.mDbManager = mDbManager;
    }

    public static void injectMMoneyFormatterUtil(DelayedTxSenderService instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
