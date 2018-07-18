package com.coinbase.android.dashboard;

import android.app.Application;
import com.coinbase.android.alerts.AlertsUtils;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.modalAlerts.ModalRouterAggregator;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DashboardMainPresenter_Factory implements Factory<DashboardMainPresenter> {
    private final Provider<AlertsUtils> alertsUtilsProvider;
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<BuyRouter> buyRouterProvider;
    private final Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider;
    private final Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider;
    private final Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider;
    private final Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ModalRouterAggregator> modalRouterAggregatorProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<DashboardPriceChartItemClickedConnector> priceChartItemClickedConnectorProvider;
    private final Provider<DashboardMainScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<StatusBarUpdater> statusBarUpdaterProvider;

    public DashboardMainPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<DashboardMainScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> priceChartItemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<Application> appProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<ModalRouterAggregator> modalRouterAggregatorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.dashboardVerificationConnectorProvider = dashboardVerificationConnectorProvider;
        this.dashboardBalanceUpdatedConnectorProvider = dashboardBalanceUpdatedConnectorProvider;
        this.priceChartItemClickedConnectorProvider = priceChartItemClickedConnectorProvider;
        this.dashboardRefreshConnectorProvider = dashboardRefreshConnectorProvider;
        this.dashboardAlertsConnectorProvider = dashboardAlertsConnectorProvider;
        this.buyRouterProvider = buyRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.alertsUtilsProvider = alertsUtilsProvider;
        this.appProvider = appProvider;
        this.statusBarUpdaterProvider = statusBarUpdaterProvider;
        this.modalRouterAggregatorProvider = modalRouterAggregatorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public DashboardMainPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.snackBarWrapperProvider, this.dashboardVerificationConnectorProvider, this.dashboardBalanceUpdatedConnectorProvider, this.priceChartItemClickedConnectorProvider, this.dashboardRefreshConnectorProvider, this.dashboardAlertsConnectorProvider, this.buyRouterProvider, this.mixpanelTrackingProvider, this.moneyFormatterUtilProvider, this.alertsUtilsProvider, this.appProvider, this.statusBarUpdaterProvider, this.modalRouterAggregatorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static DashboardMainPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<DashboardMainScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> priceChartItemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<Application> appProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<ModalRouterAggregator> modalRouterAggregatorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new DashboardMainPresenter((LoginManager) loginManagerProvider.get(), (DashboardMainScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (DashboardVerificationConnector) dashboardVerificationConnectorProvider.get(), (DashboardBalanceUpdatedConnector) dashboardBalanceUpdatedConnectorProvider.get(), (DashboardPriceChartItemClickedConnector) priceChartItemClickedConnectorProvider.get(), (DashboardRefreshConnector) dashboardRefreshConnectorProvider.get(), (DashboardAlertsConnector) dashboardAlertsConnectorProvider.get(), (BuyRouter) buyRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (AlertsUtils) alertsUtilsProvider.get(), (Application) appProvider.get(), (StatusBarUpdater) statusBarUpdaterProvider.get(), (ModalRouterAggregator) modalRouterAggregatorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static DashboardMainPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<DashboardMainScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> priceChartItemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<Application> appProvider, Provider<StatusBarUpdater> statusBarUpdaterProvider, Provider<ModalRouterAggregator> modalRouterAggregatorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new DashboardMainPresenter_Factory(loginManagerProvider, screenProvider, snackBarWrapperProvider, dashboardVerificationConnectorProvider, dashboardBalanceUpdatedConnectorProvider, priceChartItemClickedConnectorProvider, dashboardRefreshConnectorProvider, dashboardAlertsConnectorProvider, buyRouterProvider, mixpanelTrackingProvider, moneyFormatterUtilProvider, alertsUtilsProvider, appProvider, statusBarUpdaterProvider, modalRouterAggregatorProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static DashboardMainPresenter newDashboardMainPresenter(LoginManager loginManager, DashboardMainScreen screen, SnackBarWrapper snackBarWrapper, DashboardVerificationConnector dashboardVerificationConnector, DashboardBalanceUpdatedConnector dashboardBalanceUpdatedConnector, DashboardPriceChartItemClickedConnector priceChartItemClickedConnector, DashboardRefreshConnector dashboardRefreshConnector, DashboardAlertsConnector dashboardAlertsConnector, BuyRouter buyRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, AlertsUtils alertsUtils, Application app, StatusBarUpdater statusBarUpdater, ModalRouterAggregator modalRouterAggregator, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new DashboardMainPresenter(loginManager, screen, snackBarWrapper, dashboardVerificationConnector, dashboardBalanceUpdatedConnector, priceChartItemClickedConnector, dashboardRefreshConnector, dashboardAlertsConnector, buyRouter, mixpanelTracking, moneyFormatterUtil, alertsUtils, app, statusBarUpdater, modalRouterAggregator, mainScheduler, backgroundScheduler);
    }
}
