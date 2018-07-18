package com.coinbase.api.internal;

import com.coinbase.android.featureflag.FeatureFlags;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class CacheFeatureFlagInitializer_Factory implements Factory<CacheFeatureFlagInitializer> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CoinbaseInternal> clientProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;

    public CacheFeatureFlagInitializer_Factory(Provider<FeatureFlags> featureFlagsProvider, Provider<CoinbaseInternal> clientProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.featureFlagsProvider = featureFlagsProvider;
        this.clientProvider = clientProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public CacheFeatureFlagInitializer get() {
        return provideInstance(this.featureFlagsProvider, this.clientProvider, this.backgroundSchedulerProvider);
    }

    public static CacheFeatureFlagInitializer provideInstance(Provider<FeatureFlags> featureFlagsProvider, Provider<CoinbaseInternal> clientProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new CacheFeatureFlagInitializer((FeatureFlags) featureFlagsProvider.get(), (CoinbaseInternal) clientProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static CacheFeatureFlagInitializer_Factory create(Provider<FeatureFlags> featureFlagsProvider, Provider<CoinbaseInternal> clientProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new CacheFeatureFlagInitializer_Factory(featureFlagsProvider, clientProvider, backgroundSchedulerProvider);
    }

    public static CacheFeatureFlagInitializer newCacheFeatureFlagInitializer(FeatureFlags featureFlags, CoinbaseInternal client, Scheduler backgroundScheduler) {
        return new CacheFeatureFlagInitializer(featureFlags, client, backgroundScheduler);
    }
}
