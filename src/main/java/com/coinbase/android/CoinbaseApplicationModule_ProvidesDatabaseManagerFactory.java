package com.coinbase.android;

import android.app.Application;
import com.coinbase.android.db.DatabaseManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoinbaseApplicationModule_ProvidesDatabaseManagerFactory implements Factory<DatabaseManager> {
    private final Provider<Application> applicationProvider;
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesDatabaseManagerFactory(CoinbaseApplicationModule module, Provider<Application> applicationProvider) {
        this.module = module;
        this.applicationProvider = applicationProvider;
    }

    public DatabaseManager get() {
        return provideInstance(this.module, this.applicationProvider);
    }

    public static DatabaseManager provideInstance(CoinbaseApplicationModule module, Provider<Application> applicationProvider) {
        return proxyProvidesDatabaseManager(module, (Application) applicationProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesDatabaseManagerFactory create(CoinbaseApplicationModule module, Provider<Application> applicationProvider) {
        return new CoinbaseApplicationModule_ProvidesDatabaseManagerFactory(module, applicationProvider);
    }

    public static DatabaseManager proxyProvidesDatabaseManager(CoinbaseApplicationModule instance, Application application) {
        return (DatabaseManager) Preconditions.checkNotNull(instance.providesDatabaseManager(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
