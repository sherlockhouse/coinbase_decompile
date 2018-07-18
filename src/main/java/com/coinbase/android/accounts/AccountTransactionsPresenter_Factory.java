package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.buysell.SellRouter;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.FetchAccountTask;
import com.coinbase.android.transfers.TransferMadeConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AccountTransactionsPresenter_Factory implements Factory<AccountTransactionsPresenter> {
    private final Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider;
    private final Provider<AccountUpdatedConnector> accountUpdatedConnectorProvider;
    private final Provider<Application> appProvider;
    private final Provider<BuyRouter> buyRouterProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<FetchAccountTask> fetchAccountTaskProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<AccountTransactionsRouter> routerProvider;
    private final Provider<AccountTransactionsScreen> screenProvider;
    private final Provider<SellRouter> sellRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<TransferMadeConnector> transferMadeConnectorProvider;

    public AccountTransactionsPresenter_Factory(Provider<AccountTransactionsScreen> screenProvider, Provider<Application> appProvider, Provider<FetchAccountTask> fetchAccountTaskProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<AccountUpdatedConnector> accountUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AccountTransactionsRouter> routerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.fetchAccountTaskProvider = fetchAccountTaskProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.accountUpdatedConnectorProvider = accountUpdatedConnectorProvider;
        this.accountCryptoAddressButtonConnectorProvider = accountCryptoAddressButtonConnectorProvider;
        this.transferMadeConnectorProvider = transferMadeConnectorProvider;
        this.buyRouterProvider = buyRouterProvider;
        this.sellRouterProvider = sellRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.routerProvider = routerProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.featureFlagsProvider = featureFlagsProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public AccountTransactionsPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.fetchAccountTaskProvider, this.snackBarWrapperProvider, this.accountUpdatedConnectorProvider, this.accountCryptoAddressButtonConnectorProvider, this.transferMadeConnectorProvider, this.buyRouterProvider, this.sellRouterProvider, this.mixpanelTrackingProvider, this.moneyFormatterUtilProvider, this.routerProvider, this.splitTestingProvider, this.featureFlagsProvider, this.loginManagerProvider, this.mainSchedulerProvider);
    }

    public static AccountTransactionsPresenter provideInstance(Provider<AccountTransactionsScreen> screenProvider, Provider<Application> appProvider, Provider<FetchAccountTask> fetchAccountTaskProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<AccountUpdatedConnector> accountUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AccountTransactionsRouter> routerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AccountTransactionsPresenter((AccountTransactionsScreen) screenProvider.get(), (Application) appProvider.get(), (FetchAccountTask) fetchAccountTaskProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (AccountUpdatedConnector) accountUpdatedConnectorProvider.get(), (AccountCryptoAddressButtonConnector) accountCryptoAddressButtonConnectorProvider.get(), (TransferMadeConnector) transferMadeConnectorProvider.get(), (BuyRouter) buyRouterProvider.get(), (SellRouter) sellRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (AccountTransactionsRouter) routerProvider.get(), (SplitTesting) splitTestingProvider.get(), (FeatureFlags) featureFlagsProvider.get(), (LoginManager) loginManagerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static AccountTransactionsPresenter_Factory create(Provider<AccountTransactionsScreen> screenProvider, Provider<Application> appProvider, Provider<FetchAccountTask> fetchAccountTaskProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<AccountUpdatedConnector> accountUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AccountTransactionsRouter> routerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new AccountTransactionsPresenter_Factory(screenProvider, appProvider, fetchAccountTaskProvider, snackBarWrapperProvider, accountUpdatedConnectorProvider, accountCryptoAddressButtonConnectorProvider, transferMadeConnectorProvider, buyRouterProvider, sellRouterProvider, mixpanelTrackingProvider, moneyFormatterUtilProvider, routerProvider, splitTestingProvider, featureFlagsProvider, loginManagerProvider, mainSchedulerProvider);
    }

    public static AccountTransactionsPresenter newAccountTransactionsPresenter(AccountTransactionsScreen screen, Application app, FetchAccountTask fetchAccountTask, SnackBarWrapper snackBarWrapper, AccountUpdatedConnector accountUpdatedConnector, AccountCryptoAddressButtonConnector accountCryptoAddressButtonConnector, TransferMadeConnector transferMadeConnector, BuyRouter buyRouter, SellRouter sellRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, AccountTransactionsRouter router, SplitTesting splitTesting, FeatureFlags featureFlags, LoginManager loginManager, Scheduler mainScheduler) {
        return new AccountTransactionsPresenter(screen, app, fetchAccountTask, snackBarWrapper, accountUpdatedConnector, accountCryptoAddressButtonConnector, transferMadeConnector, buyRouter, sellRouter, mixpanelTracking, moneyFormatterUtil, router, splitTesting, featureFlags, loginManager, mainScheduler);
    }
}
