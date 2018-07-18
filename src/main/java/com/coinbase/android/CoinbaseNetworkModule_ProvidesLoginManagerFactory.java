package com.coinbase.android;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.Scheduler;

public final class CoinbaseNetworkModule_ProvidesLoginManagerFactory implements Factory<LoginManager> {
    private final Provider<Application> contextProvider;
    private final Provider<DatabaseManager> dbManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final CoinbaseNetworkModule module;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<SignOutConnector> signOutConnectorProvider;
    private final Provider<UserUpdatedConnector> userUpdatedConnectorProvider;

    public CoinbaseNetworkModule_ProvidesLoginManagerFactory(CoinbaseNetworkModule module, Provider<Application> contextProvider, Provider<DatabaseManager> dbManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<SignOutConnector> signOutConnectorProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
        this.dbManagerProvider = dbManagerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.userUpdatedConnectorProvider = userUpdatedConnectorProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.signOutConnectorProvider = signOutConnectorProvider;
    }

    public LoginManager get() {
        return provideInstance(this.module, this.contextProvider, this.dbManagerProvider, this.mainSchedulerProvider, this.userUpdatedConnectorProvider, this.sharedPrefsProvider, this.signOutConnectorProvider);
    }

    public static LoginManager provideInstance(CoinbaseNetworkModule module, Provider<Application> contextProvider, Provider<DatabaseManager> dbManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<SignOutConnector> signOutConnectorProvider) {
        return proxyProvidesLoginManager(module, (Application) contextProvider.get(), (DatabaseManager) dbManagerProvider.get(), (Scheduler) mainSchedulerProvider.get(), (UserUpdatedConnector) userUpdatedConnectorProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (SignOutConnector) signOutConnectorProvider.get());
    }

    public static CoinbaseNetworkModule_ProvidesLoginManagerFactory create(CoinbaseNetworkModule module, Provider<Application> contextProvider, Provider<DatabaseManager> dbManagerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<SignOutConnector> signOutConnectorProvider) {
        return new CoinbaseNetworkModule_ProvidesLoginManagerFactory(module, contextProvider, dbManagerProvider, mainSchedulerProvider, userUpdatedConnectorProvider, sharedPrefsProvider, signOutConnectorProvider);
    }

    public static LoginManager proxyProvidesLoginManager(CoinbaseNetworkModule instance, Application context, DatabaseManager dbManager, Scheduler mainScheduler, UserUpdatedConnector userUpdatedConnector, SharedPreferences sharedPrefs, SignOutConnector signOutConnector) {
        return (LoginManager) Preconditions.checkNotNull(instance.providesLoginManager(context, dbManager, mainScheduler, userUpdatedConnector, sharedPrefs, signOutConnector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
