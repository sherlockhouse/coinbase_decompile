package com.coinbase.android.notifications.priceAlerts;

import android.support.v7.app.AppCompatActivity;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PriceAlertsRouter_Factory implements Factory<PriceAlertsRouter> {
    private final Provider<AppCompatActivity> activityProvider;

    public PriceAlertsRouter_Factory(Provider<AppCompatActivity> activityProvider) {
        this.activityProvider = activityProvider;
    }

    public PriceAlertsRouter get() {
        return provideInstance(this.activityProvider);
    }

    public static PriceAlertsRouter provideInstance(Provider<AppCompatActivity> activityProvider) {
        return new PriceAlertsRouter((AppCompatActivity) activityProvider.get());
    }

    public static PriceAlertsRouter_Factory create(Provider<AppCompatActivity> activityProvider) {
        return new PriceAlertsRouter_Factory(activityProvider);
    }

    public static PriceAlertsRouter newPriceAlertsRouter(AppCompatActivity activity) {
        return new PriceAlertsRouter(activity);
    }
}
