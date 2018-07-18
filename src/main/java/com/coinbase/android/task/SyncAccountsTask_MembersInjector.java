package com.coinbase.android.task;

import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class SyncAccountsTask_MembersInjector implements MembersInjector<SyncAccountsTask> {
    private final Provider<DatabaseManager> dbManagerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<AccountsUpdatedConnector> mAccountsUpdatedConnectorProvider;
    private final Provider<Scheduler> mBackgroundSchedulerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public SyncAccountsTask_MembersInjector(Provider<DatabaseManager> dbManagerProvider, Provider<AccountsUpdatedConnector> mAccountsUpdatedConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        this.dbManagerProvider = dbManagerProvider;
        this.mAccountsUpdatedConnectorProvider = mAccountsUpdatedConnectorProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mBackgroundSchedulerProvider = mBackgroundSchedulerProvider;
    }

    public static MembersInjector<SyncAccountsTask> create(Provider<DatabaseManager> dbManagerProvider, Provider<AccountsUpdatedConnector> mAccountsUpdatedConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        return new SyncAccountsTask_MembersInjector(dbManagerProvider, mAccountsUpdatedConnectorProvider, loginManagerProvider, mMoneyFormatterUtilProvider, mMainSchedulerProvider, mBackgroundSchedulerProvider);
    }

    public void injectMembers(SyncAccountsTask instance) {
        injectDbManager(instance, (DatabaseManager) this.dbManagerProvider.get());
        injectMAccountsUpdatedConnector(instance, (AccountsUpdatedConnector) this.mAccountsUpdatedConnectorProvider.get());
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMBackgroundScheduler(instance, (Scheduler) this.mBackgroundSchedulerProvider.get());
    }

    public static void injectDbManager(SyncAccountsTask instance, DatabaseManager dbManager) {
        instance.dbManager = dbManager;
    }

    public static void injectMAccountsUpdatedConnector(SyncAccountsTask instance, AccountsUpdatedConnector mAccountsUpdatedConnector) {
        instance.mAccountsUpdatedConnector = mAccountsUpdatedConnector;
    }

    public static void injectLoginManager(SyncAccountsTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMMoneyFormatterUtil(SyncAccountsTask instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMMainScheduler(SyncAccountsTask instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMBackgroundScheduler(SyncAccountsTask instance, Scheduler mBackgroundScheduler) {
        instance.mBackgroundScheduler = mBackgroundScheduler;
    }
}
