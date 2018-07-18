package com.coinbase.android.alerts;

import com.coinbase.api.internal.models.alerts.Data;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;

public final class AlertsListPresenter_Factory implements Factory<AlertsListPresenter> {
    private final Provider<List<Data>> alertsListProvider;
    private final Provider<AlertsUtils> alertsUtilsProvider;
    private final Provider<AlertsContainerScreen> parentScreenProvider;
    private final Provider<AlertsListRouter> routerProvider;
    private final Provider<AlertsListScreen> screenProvider;

    public AlertsListPresenter_Factory(Provider<AlertsListScreen> screenProvider, Provider<List<Data>> alertsListProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<AlertsContainerScreen> parentScreenProvider, Provider<AlertsListRouter> routerProvider) {
        this.screenProvider = screenProvider;
        this.alertsListProvider = alertsListProvider;
        this.alertsUtilsProvider = alertsUtilsProvider;
        this.parentScreenProvider = parentScreenProvider;
        this.routerProvider = routerProvider;
    }

    public AlertsListPresenter get() {
        return provideInstance(this.screenProvider, this.alertsListProvider, this.alertsUtilsProvider, this.parentScreenProvider, this.routerProvider);
    }

    public static AlertsListPresenter provideInstance(Provider<AlertsListScreen> screenProvider, Provider<List<Data>> alertsListProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<AlertsContainerScreen> parentScreenProvider, Provider<AlertsListRouter> routerProvider) {
        return new AlertsListPresenter((AlertsListScreen) screenProvider.get(), (List) alertsListProvider.get(), (AlertsUtils) alertsUtilsProvider.get(), (AlertsContainerScreen) parentScreenProvider.get(), (AlertsListRouter) routerProvider.get());
    }

    public static AlertsListPresenter_Factory create(Provider<AlertsListScreen> screenProvider, Provider<List<Data>> alertsListProvider, Provider<AlertsUtils> alertsUtilsProvider, Provider<AlertsContainerScreen> parentScreenProvider, Provider<AlertsListRouter> routerProvider) {
        return new AlertsListPresenter_Factory(screenProvider, alertsListProvider, alertsUtilsProvider, parentScreenProvider, routerProvider);
    }

    public static AlertsListPresenter newAlertsListPresenter(AlertsListScreen screen, List<Data> alertsList, AlertsUtils alertsUtils, AlertsContainerScreen parentScreen, AlertsListRouter router) {
        return new AlertsListPresenter(screen, alertsList, alertsUtils, parentScreen, router);
    }
}
