package com.coinbase.android.task;

import com.coinbase.android.accounts.AccountUpdatedConnector;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class FetchAccountTask_MembersInjector implements MembersInjector<FetchAccountTask> {
    private final Provider<AccountUpdatedConnector> mAccountUpdatedConnectorProvider;
    private final Provider<Scheduler> mBackgroundSchedulerProvider;
    private final Provider<DatabaseManager> mDbManagerProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public FetchAccountTask_MembersInjector(Provider<DatabaseManager> mDbManagerProvider, Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider, Provider<AccountUpdatedConnector> mAccountUpdatedConnectorProvider) {
        this.mDbManagerProvider = mDbManagerProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mBackgroundSchedulerProvider = mBackgroundSchedulerProvider;
        this.mAccountUpdatedConnectorProvider = mAccountUpdatedConnectorProvider;
    }

    public static MembersInjector<FetchAccountTask> create(Provider<DatabaseManager> mDbManagerProvider, Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider, Provider<AccountUpdatedConnector> mAccountUpdatedConnectorProvider) {
        return new FetchAccountTask_MembersInjector(mDbManagerProvider, mLoginManagerProvider, mMoneyFormatterUtilProvider, mMainSchedulerProvider, mBackgroundSchedulerProvider, mAccountUpdatedConnectorProvider);
    }

    public void injectMembers(FetchAccountTask instance) {
        injectMDbManager(instance, (DatabaseManager) this.mDbManagerProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMBackgroundScheduler(instance, (Scheduler) this.mBackgroundSchedulerProvider.get());
        injectMAccountUpdatedConnector(instance, (AccountUpdatedConnector) this.mAccountUpdatedConnectorProvider.get());
    }

    public static void injectMDbManager(FetchAccountTask instance, DatabaseManager mDbManager) {
        instance.mDbManager = mDbManager;
    }

    public static void injectMLoginManager(FetchAccountTask instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMoneyFormatterUtil(FetchAccountTask instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMMainScheduler(FetchAccountTask instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMBackgroundScheduler(FetchAccountTask instance, Scheduler mBackgroundScheduler) {
        instance.mBackgroundScheduler = mBackgroundScheduler;
    }

    public static void injectMAccountUpdatedConnector(FetchAccountTask instance, AccountUpdatedConnector mAccountUpdatedConnector) {
        instance.mAccountUpdatedConnector = mAccountUpdatedConnector;
    }
}
