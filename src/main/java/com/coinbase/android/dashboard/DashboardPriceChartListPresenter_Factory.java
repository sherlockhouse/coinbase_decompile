package com.coinbase.android.dashboard;

import android.app.Application;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DashboardPriceChartListPresenter_Factory implements Factory<DashboardPriceChartListPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider;
    private final Provider<DashboardPriceChartItemClickedConnector> itemClickedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<DashboardPriceChartListScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public DashboardPriceChartListPresenter_Factory(Provider<Application> applicationProvider, Provider<DashboardPriceChartListScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> itemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.itemClickedConnectorProvider = itemClickedConnectorProvider;
        this.dashboardRefreshConnectorProvider = dashboardRefreshConnectorProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public DashboardPriceChartListPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.snackBarWrapperProvider, this.currenciesUpdatedConnectorProvider, this.itemClickedConnectorProvider, this.dashboardRefreshConnectorProvider, this.moneyFormatterUtilProvider, this.mainSchedulerProvider);
    }

    public static DashboardPriceChartListPresenter provideInstance(Provider<Application> applicationProvider, Provider<DashboardPriceChartListScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> itemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DashboardPriceChartListPresenter((Application) applicationProvider.get(), (DashboardPriceChartListScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (DashboardPriceChartItemClickedConnector) itemClickedConnectorProvider.get(), (DashboardRefreshConnector) dashboardRefreshConnectorProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static DashboardPriceChartListPresenter_Factory create(Provider<Application> applicationProvider, Provider<DashboardPriceChartListScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<DashboardPriceChartItemClickedConnector> itemClickedConnectorProvider, Provider<DashboardRefreshConnector> dashboardRefreshConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DashboardPriceChartListPresenter_Factory(applicationProvider, screenProvider, snackBarWrapperProvider, currenciesUpdatedConnectorProvider, itemClickedConnectorProvider, dashboardRefreshConnectorProvider, moneyFormatterUtilProvider, mainSchedulerProvider);
    }

    public static DashboardPriceChartListPresenter newDashboardPriceChartListPresenter(Application application, DashboardPriceChartListScreen screen, SnackBarWrapper snackBarWrapper, CurrenciesUpdatedConnector currenciesUpdatedConnector, DashboardPriceChartItemClickedConnector itemClickedConnector, DashboardRefreshConnector dashboardRefreshConnector, MoneyFormatterUtil moneyFormatterUtil, Scheduler mainScheduler) {
        return new DashboardPriceChartListPresenter(application, screen, snackBarWrapper, currenciesUpdatedConnector, itemClickedConnector, dashboardRefreshConnector, moneyFormatterUtil, mainScheduler);
    }
}
