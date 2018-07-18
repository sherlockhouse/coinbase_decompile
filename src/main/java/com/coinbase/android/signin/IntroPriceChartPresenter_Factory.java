package com.coinbase.android.signin;

import android.app.Application;
import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IntroPriceChartPresenter_Factory implements Factory<IntroPriceChartPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider;
    private final Provider<IntroPriceChartScreen> screenProvider;

    public IntroPriceChartPresenter_Factory(Provider<IntroPriceChartScreen> screenProvider, Provider<Application> appProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.priceChartPeriodUpdatedConnectorProvider = priceChartPeriodUpdatedConnectorProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
    }

    public IntroPriceChartPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.priceChartPeriodUpdatedConnectorProvider, this.moneyFormatterUtilProvider);
    }

    public static IntroPriceChartPresenter provideInstance(Provider<IntroPriceChartScreen> screenProvider, Provider<Application> appProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new IntroPriceChartPresenter((IntroPriceChartScreen) screenProvider.get(), (Application) appProvider.get(), (PriceChartPeriodUpdatedConnector) priceChartPeriodUpdatedConnectorProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get());
    }

    public static IntroPriceChartPresenter_Factory create(Provider<IntroPriceChartScreen> screenProvider, Provider<Application> appProvider, Provider<PriceChartPeriodUpdatedConnector> priceChartPeriodUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new IntroPriceChartPresenter_Factory(screenProvider, appProvider, priceChartPeriodUpdatedConnectorProvider, moneyFormatterUtilProvider);
    }

    public static IntroPriceChartPresenter newIntroPriceChartPresenter(IntroPriceChartScreen screen, Application app, PriceChartPeriodUpdatedConnector priceChartPeriodUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil) {
        return new IntroPriceChartPresenter(screen, app, priceChartPeriodUpdatedConnector, moneyFormatterUtil);
    }
}
