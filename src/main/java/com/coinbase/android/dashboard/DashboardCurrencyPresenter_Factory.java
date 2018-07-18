package com.coinbase.android.dashboard;

import android.app.Application;
import com.coinbase.android.accounts.AccountItemClickedConnector;
import com.coinbase.android.buysell.BuyRouter;
import com.coinbase.android.buysell.SellRouter;
import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DashboardCurrencyPresenter_Factory implements Factory<DashboardCurrencyPresenter> {
    private final Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider;
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<BuyRouter> buyRouterProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider;
    private final Provider<DashboardCurrencyScreen> screenProvider;
    private final Provider<SellRouter> sellRouterProvider;
    private final Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider;

    public DashboardCurrencyPresenter_Factory(Provider<Application> applicationProvider, Provider<DashboardCurrencyScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.tabPeriodSelectionConnectorProvider = tabPeriodSelectionConnectorProvider;
        this.priceChartPeriodUpdatedConnectorProvider = priceChartPeriodUpdatedConnectorProvider;
        this.accountItemClickedConnectorProvider = accountItemClickedConnectorProvider;
        this.buyRouterProvider = buyRouterProvider;
        this.sellRouterProvider = sellRouterProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public DashboardCurrencyPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.tabPeriodSelectionConnectorProvider, this.priceChartPeriodUpdatedConnectorProvider, this.accountItemClickedConnectorProvider, this.buyRouterProvider, this.sellRouterProvider, this.mixpanelTrackingProvider, this.moneyFormatterUtilProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static DashboardCurrencyPresenter provideInstance(Provider<Application> applicationProvider, Provider<DashboardCurrencyScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new DashboardCurrencyPresenter((Application) applicationProvider.get(), (DashboardCurrencyScreen) screenProvider.get(), (DashboardTabPeriodSelectionConnector) tabPeriodSelectionConnectorProvider.get(), (PriceChartPeriodUpdatedConnector) priceChartPeriodUpdatedConnectorProvider.get(), (AccountItemClickedConnector) accountItemClickedConnectorProvider.get(), (BuyRouter) buyRouterProvider.get(), (SellRouter) sellRouterProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static DashboardCurrencyPresenter_Factory create(Provider<Application> applicationProvider, Provider<DashboardCurrencyScreen> screenProvider, Provider<DashboardTabPeriodSelectionConnector> tabPeriodSelectionConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<AccountItemClickedConnector> accountItemClickedConnectorProvider, Provider<BuyRouter> buyRouterProvider, Provider<SellRouter> sellRouterProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new DashboardCurrencyPresenter_Factory(applicationProvider, screenProvider, tabPeriodSelectionConnectorProvider, priceChartPeriodUpdatedConnectorProvider, accountItemClickedConnectorProvider, buyRouterProvider, sellRouterProvider, mixpanelTrackingProvider, moneyFormatterUtilProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static DashboardCurrencyPresenter newDashboardCurrencyPresenter(Application application, DashboardCurrencyScreen screen, DashboardTabPeriodSelectionConnector tabPeriodSelectionConnector, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, AccountItemClickedConnector accountItemClickedConnector, BuyRouter buyRouter, SellRouter sellRouter, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new DashboardCurrencyPresenter(application, screen, tabPeriodSelectionConnector, priceChartPeriodUpdatedConnector, accountItemClickedConnector, buyRouter, sellRouter, mixpanelTracking, moneyFormatterUtil, mainScheduler, backgroundScheduler);
    }
}
