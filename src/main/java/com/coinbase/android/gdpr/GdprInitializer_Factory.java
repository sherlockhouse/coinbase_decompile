package com.coinbase.android.gdpr;

import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.settings.UserUpdatedConnector;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class GdprInitializer_Factory implements Factory<GdprInitializer> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;
    private final Provider<UserUpdatedConnector> userUpdatedConnectorProvider;

    public GdprInitializer_Factory(Provider<FeatureFlags> featureFlagsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.featureFlagsProvider = featureFlagsProvider;
        this.userUpdatedConnectorProvider = userUpdatedConnectorProvider;
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public GdprInitializer get() {
        return provideInstance(this.featureFlagsProvider, this.userUpdatedConnectorProvider, this.onboardingUpdatedConnectorProvider, this.backgroundSchedulerProvider);
    }

    public static GdprInitializer provideInstance(Provider<FeatureFlags> featureFlagsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new GdprInitializer((FeatureFlags) featureFlagsProvider.get(), (UserUpdatedConnector) userUpdatedConnectorProvider.get(), (OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static GdprInitializer_Factory create(Provider<FeatureFlags> featureFlagsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new GdprInitializer_Factory(featureFlagsProvider, userUpdatedConnectorProvider, onboardingUpdatedConnectorProvider, backgroundSchedulerProvider);
    }

    public static GdprInitializer newGdprInitializer(FeatureFlags featureFlags, UserUpdatedConnector userUpdatedConnector, OnboardingUpdatedConnector onboardingUpdatedConnector, Scheduler backgroundScheduler) {
        return new GdprInitializer(featureFlags, userUpdatedConnector, onboardingUpdatedConnector, backgroundScheduler);
    }
}
