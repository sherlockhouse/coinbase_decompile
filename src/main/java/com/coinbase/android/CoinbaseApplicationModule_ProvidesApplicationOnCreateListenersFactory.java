package com.coinbase.android;

import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.gdpr.GdprInitializer;
import com.coinbase.android.idology.IdologyUtils;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CacheFeatureFlagInitializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;
import rx.Scheduler;

public final class CoinbaseApplicationModule_ProvidesApplicationOnCreateListenersFactory implements Factory<Set<ApplicationOnCreateListener>> {
    private final Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider;
    private final Provider<Set<ApplicationSignOutListener>> applicationSignOutListenersProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<FetchAdvertisingIdOnCreateListener> fetchAdvertisingIdOnCreateListenerProvider;
    private final Provider<GdprInitializer> gdprInitializerProvider;
    private final Provider<IdologyUtils> idologyUtilsProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final CoinbaseApplicationModule module;
    private final Provider<SignOutConnector> signOutConnectorProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public CoinbaseApplicationModule_ProvidesApplicationOnCreateListenersFactory(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<Set<ApplicationSignOutListener>> applicationSignOutListenersProvider, Provider<FetchAdvertisingIdOnCreateListener> fetchAdvertisingIdOnCreateListenerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<GdprInitializer> gdprInitializerProvider) {
        this.module = module;
        this.loginManagerProvider = loginManagerProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.signOutConnectorProvider = signOutConnectorProvider;
        this.applicationSignOutListenersProvider = applicationSignOutListenersProvider;
        this.fetchAdvertisingIdOnCreateListenerProvider = fetchAdvertisingIdOnCreateListenerProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.featureFlagsProvider = featureFlagsProvider;
        this.appRateOnCreateListenerProvider = appRateOnCreateListenerProvider;
        this.cacheFeatureFlagInitializerProvider = cacheFeatureFlagInitializerProvider;
        this.idologyUtilsProvider = idologyUtilsProvider;
        this.gdprInitializerProvider = gdprInitializerProvider;
    }

    public Set<ApplicationOnCreateListener> get() {
        return provideInstance(this.module, this.loginManagerProvider, this.currenciesUpdatedConnectorProvider, this.backgroundSchedulerProvider, this.signOutConnectorProvider, this.applicationSignOutListenersProvider, this.fetchAdvertisingIdOnCreateListenerProvider, this.splitTestingProvider, this.featureFlagsProvider, this.appRateOnCreateListenerProvider, this.cacheFeatureFlagInitializerProvider, this.idologyUtilsProvider, this.gdprInitializerProvider);
    }

    public static Set<ApplicationOnCreateListener> provideInstance(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<Set<ApplicationSignOutListener>> applicationSignOutListenersProvider, Provider<FetchAdvertisingIdOnCreateListener> fetchAdvertisingIdOnCreateListenerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<GdprInitializer> gdprInitializerProvider) {
        return proxyProvidesApplicationOnCreateListeners(module, (LoginManager) loginManagerProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (SignOutConnector) signOutConnectorProvider.get(), (Set) applicationSignOutListenersProvider.get(), (FetchAdvertisingIdOnCreateListener) fetchAdvertisingIdOnCreateListenerProvider.get(), (SplitTesting) splitTestingProvider.get(), (FeatureFlags) featureFlagsProvider.get(), (AppRateOnCreateListener) appRateOnCreateListenerProvider.get(), (CacheFeatureFlagInitializer) cacheFeatureFlagInitializerProvider.get(), (IdologyUtils) idologyUtilsProvider.get(), (GdprInitializer) gdprInitializerProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesApplicationOnCreateListenersFactory create(CoinbaseApplicationModule module, Provider<LoginManager> loginManagerProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<Set<ApplicationSignOutListener>> applicationSignOutListenersProvider, Provider<FetchAdvertisingIdOnCreateListener> fetchAdvertisingIdOnCreateListenerProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<GdprInitializer> gdprInitializerProvider) {
        return new CoinbaseApplicationModule_ProvidesApplicationOnCreateListenersFactory(module, loginManagerProvider, currenciesUpdatedConnectorProvider, backgroundSchedulerProvider, signOutConnectorProvider, applicationSignOutListenersProvider, fetchAdvertisingIdOnCreateListenerProvider, splitTestingProvider, featureFlagsProvider, appRateOnCreateListenerProvider, cacheFeatureFlagInitializerProvider, idologyUtilsProvider, gdprInitializerProvider);
    }

    public static Set<ApplicationOnCreateListener> proxyProvidesApplicationOnCreateListeners(CoinbaseApplicationModule instance, LoginManager loginManager, CurrenciesUpdatedConnector currenciesUpdatedConnector, Scheduler backgroundScheduler, SignOutConnector signOutConnector, Set<ApplicationSignOutListener> applicationSignOutListeners, FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener, SplitTesting splitTesting, FeatureFlags featureFlags, AppRateOnCreateListener appRateOnCreateListener, CacheFeatureFlagInitializer cacheFeatureFlagInitializer, IdologyUtils idologyUtils, GdprInitializer gdprInitializer) {
        return (Set) Preconditions.checkNotNull(instance.providesApplicationOnCreateListeners(loginManager, currenciesUpdatedConnector, backgroundScheduler, signOutConnector, applicationSignOutListeners, fetchAdvertisingIdOnCreateListener, splitTesting, featureFlags, appRateOnCreateListener, cacheFeatureFlagInitializer, idologyUtils, gdprInitializer), "Cannot return null from a non-@Nullable @Provides method");
    }
}
