package com.coinbase.android.paymentmethods;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class PlaidPresenter_Factory implements Factory<PlaidPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<PlaidScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<StatusBarUpdater> statusBarUpdaterProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public PlaidPresenter_Factory(Provider<PlaidScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<Scheduler> schedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Application> appProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        this.screenProvider = screenProvider;
        this.successRouterProvider = successRouterProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.schedulerProvider = schedulerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.statusBarUpdaterProvider = statusBarUpdaterProvider;
        this.appProvider = appProvider;
        this.plaidOnExitConnectorProvider = plaidOnExitConnectorProvider;
    }

    public PlaidPresenter get() {
        return provideInstance(this.screenProvider, this.successRouterProvider, this.bankAccountsUpdatedConnectorProvider, this.schedulerProvider, this.loginManagerProvider, this.snackBarWrapperProvider, this.statusBarUpdaterProvider, this.appProvider, this.plaidOnExitConnectorProvider);
    }

    public static PlaidPresenter provideInstance(Provider<PlaidScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<Scheduler> schedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Application> appProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        return new PlaidPresenter((PlaidScreen) screenProvider.get(), (SuccessRouter) successRouterProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (Scheduler) schedulerProvider.get(), (LoginManager) loginManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (StatusBarUpdater) statusBarUpdaterProvider.get(), (Application) appProvider.get(), (PlaidOnExitConnector) plaidOnExitConnectorProvider.get());
    }

    public static PlaidPresenter_Factory create(Provider<PlaidScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<Scheduler> schedulerProvider, Provider<LoginManager> loginManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<Application> appProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        return new PlaidPresenter_Factory(screenProvider, successRouterProvider, bankAccountsUpdatedConnectorProvider, schedulerProvider, loginManagerProvider, snackBarWrapperProvider, statusBarUpdaterProvider, appProvider, plaidOnExitConnectorProvider);
    }

    public static PlaidPresenter newPlaidPresenter(PlaidScreen screen, SuccessRouter successRouter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, Scheduler scheduler, LoginManager loginManager, SnackBarWrapper snackBarWrapper, StatusBarUpdater statusBarUpdater, Application app, PlaidOnExitConnector plaidOnExitConnector) {
        return new PlaidPresenter(screen, successRouter, bankAccountsUpdatedConnector, scheduler, loginManager, snackBarWrapper, statusBarUpdater, app, plaidOnExitConnector);
    }
}
