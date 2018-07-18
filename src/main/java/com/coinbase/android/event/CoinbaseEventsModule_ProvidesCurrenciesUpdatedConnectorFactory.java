package com.coinbase.android.event;

import android.app.Application;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseEventsModule_ProvidesCurrenciesUpdatedConnectorFactory implements Factory<CurrenciesUpdatedConnector> {
    private final Provider<Application> applicationProvider;
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesCurrenciesUpdatedConnectorFactory(CoinbaseEventsModule module, Provider<Application> applicationProvider) {
        this.module = module;
        this.applicationProvider = applicationProvider;
    }

    public CurrenciesUpdatedConnector get() {
        return provideInstance(this.module, this.applicationProvider);
    }

    public static CurrenciesUpdatedConnector provideInstance(CoinbaseEventsModule module, Provider<Application> applicationProvider) {
        return proxyProvidesCurrenciesUpdatedConnector(module, (Application) applicationProvider.get());
    }

    public static CoinbaseEventsModule_ProvidesCurrenciesUpdatedConnectorFactory create(CoinbaseEventsModule module, Provider<Application> applicationProvider) {
        return new CoinbaseEventsModule_ProvidesCurrenciesUpdatedConnectorFactory(module, applicationProvider);
    }

    public static CurrenciesUpdatedConnector proxyProvidesCurrenciesUpdatedConnector(CoinbaseEventsModule instance, Application application) {
        return (CurrenciesUpdatedConnector) Preconditions.checkNotNull(instance.providesCurrenciesUpdatedConnector(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
