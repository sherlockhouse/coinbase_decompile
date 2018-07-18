package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.utils.GoogleApiClientWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.functions.Func0;

public final class GoogleClientApiModule_ProvidesGoogleApiClientFactory implements Factory<Func0<GoogleApiClientWrapper>> {
    private final Provider<Application> appProvider;
    private final GoogleClientApiModule module;

    public GoogleClientApiModule_ProvidesGoogleApiClientFactory(GoogleClientApiModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public Func0<GoogleApiClientWrapper> get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static Func0<GoogleApiClientWrapper> provideInstance(GoogleClientApiModule module, Provider<Application> appProvider) {
        return proxyProvidesGoogleApiClient(module, (Application) appProvider.get());
    }

    public static GoogleClientApiModule_ProvidesGoogleApiClientFactory create(GoogleClientApiModule module, Provider<Application> appProvider) {
        return new GoogleClientApiModule_ProvidesGoogleApiClientFactory(module, appProvider);
    }

    public static Func0<GoogleApiClientWrapper> proxyProvidesGoogleApiClient(GoogleClientApiModule instance, Application app) {
        return (Func0) Preconditions.checkNotNull(instance.providesGoogleApiClient(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
