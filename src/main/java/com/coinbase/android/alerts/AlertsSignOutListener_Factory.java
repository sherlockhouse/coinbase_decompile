package com.coinbase.android.alerts;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class AlertsSignOutListener_Factory implements Factory<AlertsSignOutListener> {
    private final Provider<AlertsUtils> alertsUtilsProvider;

    public AlertsSignOutListener_Factory(Provider<AlertsUtils> alertsUtilsProvider) {
        this.alertsUtilsProvider = alertsUtilsProvider;
    }

    public AlertsSignOutListener get() {
        return provideInstance(this.alertsUtilsProvider);
    }

    public static AlertsSignOutListener provideInstance(Provider<AlertsUtils> alertsUtilsProvider) {
        return new AlertsSignOutListener((AlertsUtils) alertsUtilsProvider.get());
    }

    public static AlertsSignOutListener_Factory create(Provider<AlertsUtils> alertsUtilsProvider) {
        return new AlertsSignOutListener_Factory(alertsUtilsProvider);
    }

    public static AlertsSignOutListener newAlertsSignOutListener(AlertsUtils alertsUtils) {
        return new AlertsSignOutListener(alertsUtils);
    }
}
