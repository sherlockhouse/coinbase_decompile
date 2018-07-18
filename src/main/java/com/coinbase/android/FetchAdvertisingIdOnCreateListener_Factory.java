package com.coinbase.android;

import android.app.Application;
import com.coinbase.api.internal.CoinbaseInternal;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class FetchAdvertisingIdOnCreateListener_Factory implements Factory<FetchAdvertisingIdOnCreateListener> {
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CoinbaseInternal> coinbaseInternalProvider;

    public FetchAdvertisingIdOnCreateListener_Factory(Provider<Application> appProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<CoinbaseInternal> coinbaseInternalProvider) {
        this.appProvider = appProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.coinbaseInternalProvider = coinbaseInternalProvider;
    }

    public FetchAdvertisingIdOnCreateListener get() {
        return provideInstance(this.appProvider, this.backgroundSchedulerProvider, this.coinbaseInternalProvider);
    }

    public static FetchAdvertisingIdOnCreateListener provideInstance(Provider<Application> appProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<CoinbaseInternal> coinbaseInternalProvider) {
        return new FetchAdvertisingIdOnCreateListener((Application) appProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (CoinbaseInternal) coinbaseInternalProvider.get());
    }

    public static FetchAdvertisingIdOnCreateListener_Factory create(Provider<Application> appProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<CoinbaseInternal> coinbaseInternalProvider) {
        return new FetchAdvertisingIdOnCreateListener_Factory(appProvider, backgroundSchedulerProvider, coinbaseInternalProvider);
    }

    public static FetchAdvertisingIdOnCreateListener newFetchAdvertisingIdOnCreateListener(Application app, Scheduler backgroundScheduler, CoinbaseInternal coinbaseInternal) {
        return new FetchAdvertisingIdOnCreateListener(app, backgroundScheduler, coinbaseInternal);
    }
}
