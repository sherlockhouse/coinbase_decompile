package com.coinbase.android.transactions;

import android.app.Application;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class TransactionListPresenter_Factory implements Factory<TransactionListPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<DatabaseManager> dbManagerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider;
    private final Provider<TransactionListScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransactionDetailButtonConnector> transactionDetailButtonConectorProvider;

    public TransactionListPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<TransactionListScreen> screenProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.dbManagerProvider = dbManagerProvider;
        this.screenProvider = screenProvider;
        this.applicationProvider = applicationProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.refreshRequestedConnectorProvider = refreshRequestedConnectorProvider;
        this.transactionDetailButtonConectorProvider = transactionDetailButtonConectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public TransactionListPresenter get() {
        return provideInstance(this.loginManagerProvider, this.dbManagerProvider, this.screenProvider, this.applicationProvider, this.snackBarWrapperProvider, this.refreshRequestedConnectorProvider, this.transactionDetailButtonConectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static TransactionListPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<TransactionListScreen> screenProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TransactionListPresenter((LoginManager) loginManagerProvider.get(), (DatabaseManager) dbManagerProvider.get(), (TransactionListScreen) screenProvider.get(), (Application) applicationProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (RefreshRequestedConnector) refreshRequestedConnectorProvider.get(), (TransactionDetailButtonConnector) transactionDetailButtonConectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static TransactionListPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<TransactionListScreen> screenProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TransactionListPresenter_Factory(loginManagerProvider, dbManagerProvider, screenProvider, applicationProvider, snackBarWrapperProvider, refreshRequestedConnectorProvider, transactionDetailButtonConectorProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static TransactionListPresenter newTransactionListPresenter(LoginManager loginManager, DatabaseManager dbManager, TransactionListScreen screen, Application application, SnackBarWrapper snackBarWrapper, RefreshRequestedConnector refreshRequestedConnector, TransactionDetailButtonConnector transactionDetailButtonConector, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new TransactionListPresenter(loginManager, dbManager, screen, application, snackBarWrapper, refreshRequestedConnector, transactionDetailButtonConector, mainScheduler, backgroundScheduler);
    }
}
