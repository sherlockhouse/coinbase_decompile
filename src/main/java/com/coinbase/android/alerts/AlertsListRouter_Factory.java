package com.coinbase.android.alerts;

import android.support.v7.app.AppCompatActivity;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AlertsListRouter_Factory implements Factory<AlertsListRouter> {
    private final Provider<AppCompatActivity> activityProvider;

    public AlertsListRouter_Factory(Provider<AppCompatActivity> activityProvider) {
        this.activityProvider = activityProvider;
    }

    public AlertsListRouter get() {
        return provideInstance(this.activityProvider);
    }

    public static AlertsListRouter provideInstance(Provider<AppCompatActivity> activityProvider) {
        return new AlertsListRouter((AppCompatActivity) activityProvider.get());
    }

    public static AlertsListRouter_Factory create(Provider<AppCompatActivity> activityProvider) {
        return new AlertsListRouter_Factory(activityProvider);
    }

    public static AlertsListRouter newAlertsListRouter(AppCompatActivity activity) {
        return new AlertsListRouter(activity);
    }
}
