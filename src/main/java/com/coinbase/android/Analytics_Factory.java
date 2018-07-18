package com.coinbase.android;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Analytics_Factory implements Factory<Analytics> {
    private final Provider<Application> applicationProvider;

    public Analytics_Factory(Provider<Application> applicationProvider) {
        this.applicationProvider = applicationProvider;
    }

    public Analytics get() {
        return provideInstance(this.applicationProvider);
    }

    public static Analytics provideInstance(Provider<Application> applicationProvider) {
        return new Analytics((Application) applicationProvider.get());
    }

    public static Analytics_Factory create(Provider<Application> applicationProvider) {
        return new Analytics_Factory(applicationProvider);
    }

    public static Analytics newAnalytics(Application application) {
        return new Analytics(application);
    }
}
