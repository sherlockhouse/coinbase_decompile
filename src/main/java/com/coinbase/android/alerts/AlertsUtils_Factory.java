package com.coinbase.android.alerts;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AlertsUtils_Factory implements Factory<AlertsUtils> {
    private final Provider<SharedPreferences> sharedPrefsProvider;

    public AlertsUtils_Factory(Provider<SharedPreferences> sharedPrefsProvider) {
        this.sharedPrefsProvider = sharedPrefsProvider;
    }

    public AlertsUtils get() {
        return provideInstance(this.sharedPrefsProvider);
    }

    public static AlertsUtils provideInstance(Provider<SharedPreferences> sharedPrefsProvider) {
        return new AlertsUtils((SharedPreferences) sharedPrefsProvider.get());
    }

    public static AlertsUtils_Factory create(Provider<SharedPreferences> sharedPrefsProvider) {
        return new AlertsUtils_Factory(sharedPrefsProvider);
    }

    public static AlertsUtils newAlertsUtils(SharedPreferences sharedPrefs) {
        return new AlertsUtils(sharedPrefs);
    }
}
