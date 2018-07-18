package com.coinbase.android.notifications.priceAlerts;

import android.app.Application;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.ui.CurrencyTabSelectorConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class PriceAlertsPresenter_Factory implements Factory<PriceAlertsPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CurrencyTabSelectorConnector> currencySelectorConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<PriceAlertsConnector> priceAlertsConnectorProvider;
    private final Provider<PriceAlertsRouter> priceAlertsRouterProvider;
    private final Provider<PriceAlertsScreen> screenProvider;
    private final Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider;

    public PriceAlertsPresenter_Factory(Provider<PriceAlertsScreen> screenProvider, Provider<Application> appProvider, Provider<CurrencyTabSelectorConnector> currencySelectorConnectorProvider, Provider<PriceAlertsConnector> priceAlertsConnectorProvider, Provider<PriceAlertsRouter> priceAlertsRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.currencySelectorConnectorProvider = currencySelectorConnectorProvider;
        this.priceAlertsConnectorProvider = priceAlertsConnectorProvider;
        this.priceAlertsRouterProvider = priceAlertsRouterProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.spotPriceUpdatedConnectorProvider = spotPriceUpdatedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public PriceAlertsPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.currencySelectorConnectorProvider, this.priceAlertsConnectorProvider, this.priceAlertsRouterProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.moneyFormatterUtilProvider, this.spotPriceUpdatedConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static PriceAlertsPresenter provideInstance(Provider<PriceAlertsScreen> screenProvider, Provider<Application> appProvider, Provider<CurrencyTabSelectorConnector> currencySelectorConnectorProvider, Provider<PriceAlertsConnector> priceAlertsConnectorProvider, Provider<PriceAlertsRouter> priceAlertsRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new PriceAlertsPresenter((PriceAlertsScreen) screenProvider.get(), (Application) appProvider.get(), (CurrencyTabSelectorConnector) currencySelectorConnectorProvider.get(), (PriceAlertsConnector) priceAlertsConnectorProvider.get(), (PriceAlertsRouter) priceAlertsRouterProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (SpotPriceUpdatedConnector) spotPriceUpdatedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static PriceAlertsPresenter_Factory create(Provider<PriceAlertsScreen> screenProvider, Provider<Application> appProvider, Provider<CurrencyTabSelectorConnector> currencySelectorConnectorProvider, Provider<PriceAlertsConnector> priceAlertsConnectorProvider, Provider<PriceAlertsRouter> priceAlertsRouterProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new PriceAlertsPresenter_Factory(screenProvider, appProvider, currencySelectorConnectorProvider, priceAlertsConnectorProvider, priceAlertsRouterProvider, loginManagerProvider, mixpanelTrackingProvider, moneyFormatterUtilProvider, spotPriceUpdatedConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static PriceAlertsPresenter newPriceAlertsPresenter(Object screen, Application app, CurrencyTabSelectorConnector currencySelectorConnector, PriceAlertsConnector priceAlertsConnector, PriceAlertsRouter priceAlertsRouter, LoginManager loginManager, MixpanelTracking mixpanelTracking, MoneyFormatterUtil moneyFormatterUtil, SpotPriceUpdatedConnector spotPriceUpdatedConnector, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new PriceAlertsPresenter((PriceAlertsScreen) screen, app, currencySelectorConnector, priceAlertsConnector, priceAlertsRouter, loginManager, mixpanelTracking, moneyFormatterUtil, spotPriceUpdatedConnector, mainScheduler, backgroundScheduler);
    }
}
