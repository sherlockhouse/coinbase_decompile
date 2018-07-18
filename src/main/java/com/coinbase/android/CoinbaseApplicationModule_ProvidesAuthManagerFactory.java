package com.coinbase.android;

import android.content.SharedPreferences;
import com.coinbase.android.signin.AuthManager;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import rx.Scheduler;

public final class CoinbaseApplicationModule_ProvidesAuthManagerFactory implements Factory<AuthManager> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final CoinbaseApplicationModule module;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public CoinbaseApplicationModule_ProvidesAuthManagerFactory(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        this.module = module;
        this.loginManagerProvider = loginManagerProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.splitTestingProvider = splitTestingProvider;
    }

    public AuthManager get() {
        return provideInstance(this.module, this.loginManagerProvider, this.sharedPreferencesProvider, this.mainSchedulerProvider, this.splitTestingProvider);
    }

    public static AuthManager provideInstance(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        return proxyProvidesAuthManager(module, (LoginManager) loginManagerProvider.get(), (SharedPreferences) sharedPreferencesProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesAuthManagerFactory create(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPreferencesProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new CoinbaseApplicationModule_ProvidesAuthManagerFactory(module, loginManagerProvider, sharedPreferencesProvider, mainSchedulerProvider, splitTestingProvider);
    }

    public static AuthManager proxyProvidesAuthManager(CoinbaseApplicationModule instance, LoginManager loginManager, SharedPreferences sharedPreferences, Scheduler mainScheduler, SplitTesting splitTesting) {
        return (AuthManager) Preconditions.checkNotNull(instance.providesAuthManager(loginManager, sharedPreferences, mainScheduler, splitTesting), "Cannot return null from a non-@Nullable @Provides method");
    }
}
