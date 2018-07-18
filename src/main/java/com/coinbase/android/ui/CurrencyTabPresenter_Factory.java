package com.coinbase.android.ui;

import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class CurrencyTabPresenter_Factory implements Factory<CurrencyTabPresenter> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<CurrencyTabScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<CurrencyTabSelectorConnector> tabSelectorConnectorProvider;

    public CurrencyTabPresenter_Factory(Provider<CurrencyTabScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrencyTabSelectorConnector> tabSelectorConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.tabSelectorConnectorProvider = tabSelectorConnectorProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public CurrencyTabPresenter get() {
        return provideInstance(this.screenProvider, this.snackBarWrapperProvider, this.tabSelectorConnectorProvider, this.currenciesUpdatedConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static CurrencyTabPresenter provideInstance(Provider<CurrencyTabScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrencyTabSelectorConnector> tabSelectorConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new CurrencyTabPresenter((CurrencyTabScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (CurrencyTabSelectorConnector) tabSelectorConnectorProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static CurrencyTabPresenter_Factory create(Provider<CurrencyTabScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrencyTabSelectorConnector> tabSelectorConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new CurrencyTabPresenter_Factory(screenProvider, snackBarWrapperProvider, tabSelectorConnectorProvider, currenciesUpdatedConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static CurrencyTabPresenter newCurrencyTabPresenter(CurrencyTabScreen screen, SnackBarWrapper snackBarWrapper, CurrencyTabSelectorConnector tabSelectorConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new CurrencyTabPresenter(screen, snackBarWrapper, tabSelectorConnector, currenciesUpdatedConnector, mainScheduler, backgroundScheduler);
    }
}
