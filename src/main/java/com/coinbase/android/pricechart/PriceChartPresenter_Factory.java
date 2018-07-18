package com.coinbase.android.pricechart;

import android.app.Application;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class PriceChartPresenter_Factory implements Factory<PriceChartPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<PriceChartDataUpdatedConnector> priceChartDataUpdatedConnectorProvider;
    private final Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider;
    private final Provider<PriceChartScreen> screenProvider;
    private final Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider;

    public PriceChartPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<PriceChartScreen> screenProvider, Provider<PriceChartDataUpdatedConnector> priceChartDataUpdatedConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.priceChartDataUpdatedConnectorProvider = priceChartDataUpdatedConnectorProvider;
        this.priceChartPeriodUpdatedConnectorProvider = priceChartPeriodUpdatedConnectorProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.spotPriceUpdatedConnectorProvider = spotPriceUpdatedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public PriceChartPresenter get() {
        return provideInstance(this.loginManagerProvider, this.applicationProvider, this.screenProvider, this.priceChartDataUpdatedConnectorProvider, this.priceChartPeriodUpdatedConnectorProvider, this.moneyFormatterUtilProvider, this.spotPriceUpdatedConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static PriceChartPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<PriceChartScreen> screenProvider, Provider<PriceChartDataUpdatedConnector> priceChartDataUpdatedConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new PriceChartPresenter((LoginManager) loginManagerProvider.get(), (Application) applicationProvider.get(), (PriceChartScreen) screenProvider.get(), (PriceChartDataUpdatedConnector) priceChartDataUpdatedConnectorProvider.get(), (PriceChartPeriodUpdatedConnector) priceChartPeriodUpdatedConnectorProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (SpotPriceUpdatedConnector) spotPriceUpdatedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static PriceChartPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<Application> applicationProvider, Provider<PriceChartScreen> screenProvider, Provider<PriceChartDataUpdatedConnector> priceChartDataUpdatedConnectorProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new PriceChartPresenter_Factory(loginManagerProvider, applicationProvider, screenProvider, priceChartDataUpdatedConnectorProvider, priceChartPeriodUpdatedConnectorProvider, moneyFormatterUtilProvider, spotPriceUpdatedConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static PriceChartPresenter newPriceChartPresenter(LoginManager loginManager, Application application, PriceChartScreen screen, PriceChartDataUpdatedConnector priceChartDataUpdatedConnector, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil, SpotPriceUpdatedConnector spotPriceUpdatedConnector, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new PriceChartPresenter(loginManager, application, screen, priceChartDataUpdatedConnector, priceChartPeriodUpdatedConnector, moneyFormatterUtil, spotPriceUpdatedConnector, mainScheduler, backgroundScheduler);
    }
}
