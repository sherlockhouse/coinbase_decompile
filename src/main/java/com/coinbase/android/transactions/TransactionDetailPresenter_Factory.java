package com.coinbase.android.transactions;

import android.app.Application;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class TransactionDetailPresenter_Factory implements Factory<TransactionDetailPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransactionDetailButtonConnector> transactionDetailButtonConnectorProvider;

    public TransactionDetailPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.applicationProvider = applicationProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.transactionDetailButtonConnectorProvider = transactionDetailButtonConnectorProvider;
        this.refreshRequestedConnectorProvider = refreshRequestedConnectorProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public TransactionDetailPresenter get() {
        return provideInstance(this.loginManagerProvider, this.applicationProvider, this.snackBarWrapperProvider, this.transactionDetailButtonConnectorProvider, this.refreshRequestedConnectorProvider, this.moneyFormatterUtilProvider, this.mainSchedulerProvider);
    }

    public static TransactionDetailPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new TransactionDetailPresenter((LoginManager) loginManagerProvider.get(), (Application) applicationProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (TransactionDetailButtonConnector) transactionDetailButtonConnectorProvider.get(), (RefreshRequestedConnector) refreshRequestedConnectorProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static TransactionDetailPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransactionDetailButtonConnector> transactionDetailButtonConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new TransactionDetailPresenter_Factory(loginManagerProvider, applicationProvider, snackBarWrapperProvider, transactionDetailButtonConnectorProvider, refreshRequestedConnectorProvider, moneyFormatterUtilProvider, mainSchedulerProvider);
    }

    public static TransactionDetailPresenter newTransactionDetailPresenter(LoginManager loginManager, Application application, SnackBarWrapper snackBarWrapper, TransactionDetailButtonConnector transactionDetailButtonConnector, RefreshRequestedConnector refreshRequestedConnector, MoneyFormatterUtil moneyFormatterUtil, Scheduler mainScheduler) {
        return new TransactionDetailPresenter(loginManager, application, snackBarWrapper, transactionDetailButtonConnector, refreshRequestedConnector, moneyFormatterUtil, mainScheduler);
    }
}
